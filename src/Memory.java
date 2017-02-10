import java.util.ArrayList;

// for managing partitions and Free/Busy Tables
public class Memory {
	private int size;
	private ArrayList<Partition> free;
	private ArrayList<Partition> busy;

	public Memory(int s, ArrayList<Partition> f, ArrayList<Partition> b) {
		size = s;
		free = f;
		busy = b;
	}
	
	
	// return
	public boolean manageJob(Job job){
		int jobSize = job.getSize();
		int optimalPartition = 0;
		boolean foundPartition = false;
		Partition current;
		for(int i = 0; i < free.size(); i ++){
			current = free.get(i);
			if(jobSize <= current.getSize() && free.get(optimalPartition).getSize() > current.getSize()){
				optimalPartition = i;
				foundPartition = true;
			}
		}
		
		// Assign Job to Partition and move to busy table
		if(foundPartition){
			job.setStatus("Running");
			free.get(optimalPartition).setCurrentJob(job);
			free.get(optimalPartition).setStatus("Busy");
			busy.add(free.get(optimalPartition));
			free.remove(optimalPartition);
			return true;
		}
		// Move Job to end of Job ArrayList
		else{
			return false;
		}
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
