import java.util.ArrayList;

// for running the memory scheme
public class FixedPartitionMemoryScheme {
	public static void main(String[] args) {
		// mm with size 500, empty free table, empty busy table
		Memory mm = new Memory(500, new ArrayList<Partition>(), new ArrayList<Partition>());
		// Add partitions to free table
		mm.getFree().add(new Partition(1, 25));
		mm.getFree().add(new Partition(2, 50));
		mm.getFree().add(new Partition(3, 100));
		mm.getFree().add(new Partition(4, 25));
		mm.getFree().add(new Partition(5, 300));

		// Empty list for jobs to be run
		ArrayList<Job> jobs = new ArrayList<Job>();
		// Add jobs to job list
		jobs.add(new Job("Job1", 20, 5));
		jobs.add(new Job("Job2", 100, 5));
		jobs.add(new Job("Job3", 150, 5));
		jobs.add(new Job("Job4", 35, 5));
		jobs.add(new Job("Job5", 16, 5));
		jobs.add(new Job("Job6", 26, 5));
		jobs.add(new Job("Job7", 101, 5));
		jobs.add(new Job("Job8", 150, 5));
		jobs.add(new Job("Job9", 35, 5));
		jobs.add(new Job("Job10", 16, 5));

		// Empty job list by adding jobs to partitions according to the fixed
		// partition memory scheme
		// Busy table has jobs running or job list has jobs
		while (!mm.getBusy().isEmpty() || !jobs.isEmpty()) {
			mm.printTables();
			System.out.print("Job List: ");
			for (Job j : jobs){
				System.out.print(j.getName() + " ");
			}
			if (!jobs.isEmpty()) { // for every job in the list
				System.out.println("\n" + jobs.get(0).getName() + " size: " + jobs.get(0).getSize());
				// if there is a usable partition then add it to the busy table
				if (mm.manageJob(jobs.get(0))) {
					// and remove the job to the job from the list
					jobs.remove(jobs.get(0));
				}
				// if there is not a usable partition in the free table
				else if (!mm.manageJob(jobs.get(0))) {
					System.out.println(jobs.get(0).getName() + " has no suitable partition");
					jobs.add(jobs.get(0)); // add the job to the end of the job
											// list
					jobs.remove(jobs.get(0)); // remove the job from the front
												// of the
					// list
				}
			} else {
				System.out.println("No Jobs to Add");
			}

			// Keeps track of time and removing finished jobs
			ArrayList<Partition> partitionsRemoved = new ArrayList<Partition>();
			for (Partition p : mm.getBusy()) { // for every partition in the
												// busy table
				Job j = p.getCurrentJob(); // current job in the current
											// partition

				j.setTime(j.getTime() - 1); // set the time of the current job
											// to its current time minus 1

				// if the current jobs time is <= 0
				if (j.getTime() <= 0) {
					System.out.println("Job Finished: " + j.getName());
					p.setCurrentJob(null);
					p.setStatus("Free");
					partitionsRemoved.add(p);
					mm.getFree().add(p); // add the partition back to the free
											// table
				}
			}
			while (!partitionsRemoved.isEmpty()) {
				mm.getBusy().remove(partitionsRemoved.get(0)); // remove
																// partition
																// from the busy
																// table
				partitionsRemoved.remove(0);
			}
			System.out.println("______________________________________________________________________________");
		}
		System.out.println("All Jobs Completed");
	}
}
