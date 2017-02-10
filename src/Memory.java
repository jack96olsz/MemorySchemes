import java.util.ArrayList;

// for managing partitions and Free/Busy Tables
public class Memory {
	private int size; // Size of memory
	private ArrayList<Partition> free; // free partition table
	private ArrayList<Partition> busy; // busy partition table

	// (size of memory, free table, busy table)
	public Memory(int s, ArrayList<Partition> f, ArrayList<Partition> b) {
		size = s;
		free = f;
		busy = b;
	}

	// Finds a partition that the current job can occupy
	public boolean manageJob(Job job) {
		int jobSize = job.getSize(); // size of job being managed
		int optimalPartition = 0; // default partition is the first one
		boolean foundPartition = false; // default: no partition found
		Partition current;

		// complicated way to set the default partition
		for (int i = 0; i < free.size(); i++) {
			// optimal partition's size is larger than the current partition
			if (free.get(optimalPartition).getSize() < free.get(i).getSize()) {
				optimalPartition = i; // set the new optimal partition as
										// the current partition
			}
		}

		// Loop through each partition in free table
		for (int i = 0; i < free.size(); i++) {
			current = free.get(i); // current partition in the loop
			// if the job being managed fits in the current partition
			if (jobSize <= current.getSize()) {
				// optimal partition's size is larger than the current partition
				if (free.get(optimalPartition).getSize() > current.getSize()) {
					optimalPartition = i; // set the new optimal partition as
											// the current partition
				}

				foundPartition = true; // usable partition was found

			}
		}
		// Assign Job to Partition and move to busy table
		if (foundPartition) {
			System.out.println("Added to partition " + free.get(optimalPartition).getNum() + " of size: "
					+ free.get(optimalPartition).getSize());
			job.setStatus("Running");
			free.get(optimalPartition).setCurrentJob(job);
			free.get(optimalPartition).setStatus("Busy");
			busy.add(free.get(optimalPartition));
			free.remove(optimalPartition);
			return true;
		}
		// Usable partition was not found
		else {
			return false;
		}
	}

	
	// Prints free and busy tables
	public void printTables(){
		System.out.println("\nFree Table:");
		for (Partition p : free){
			System.out.println("Partition #: " + p.getNum() + "\tSize: " + p.getSize() + "\tStatus: " + p.getStatus());
		}
		System.out.println("\nBusy Table:");
		for (Partition p : busy){
			System.out.println("Partition #: " + p.getNum() + "\tSize: " + p.getSize() + "\tStatus: " + p.getStatus() + "\tCurrent Job: " + p.getCurrentJob().getName());
		}
		System.out.println("\n");
	}
	// Setters and Getters

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public ArrayList<Partition> getFree() {
		return free;
	}

	public void setFree(ArrayList<Partition> free) {
		this.free = free;
	}

	public ArrayList<Partition> getBusy() {
		return busy;
	}

	public void setBusy(ArrayList<Partition> busy) {
		this.busy = busy;
	}

}
