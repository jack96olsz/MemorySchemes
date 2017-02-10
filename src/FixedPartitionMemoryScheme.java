import java.util.ArrayList;

public class FixedPartitionMemoryScheme {
	public static void main(String[] args) {
		int time = 0;

		Memory mm = new Memory(500, new ArrayList<Partition>(), new ArrayList<Partition>());
		mm.getFree().add(new Partition(1, 25));
		mm.getFree().add(new Partition(2, 50));
		mm.getFree().add(new Partition(3, 100));
		mm.getFree().add(new Partition(4, 25));
		mm.getFree().add(new Partition(5, 300));

		ArrayList<Job> jobs = new ArrayList<Job>();
		jobs.add(new Job("Job1", 20, 5));
		jobs.add(new Job("Job2", 100, 5));
		jobs.add(new Job("Job3", 150, 5));
		jobs.add(new Job("Job4", 35, 5));
		jobs.add(new Job("Job5", 16, 5));

		while(!jobs.isEmpty()){
			if (!mm.manageJob(jobs.get(0))) {
				jobs.add(jobs.get(0));
				System.out.println(jobs.remove(0));
			} else {
				System.out.println(jobs.remove(0));
			}

			for (Partition p : mm.getBusy()) {
				Job j = p.getCurrentJob();
				j.setTime(j.getTime()-1);
				if(j.getTime() <= 0){
					mm.getBusy().remove(j);
					mm.getFree().add(p);
				}
			}
		}

	}
}
