public class SingleUserContiguous {
	public static void main(String[] args) {
		int[] jobs = { 20, 1, 19, 129, 50, 128, 24 };
		int memory = 128;
		boolean jobRunning = false;
		int i = 0;
		int currentJob = 0;

		System.out.println("Start Jobs\n");
		while (i < jobs.length) {
			if (!jobRunning) {
				if (memory - jobs[i] >= 0) {
					jobRunning = true;
					memory -= jobs[i];
					currentJob = jobs[i];
					System.out.println("Job Running: " + jobs[i]);
					System.out.println("Wasted Memory: " + memory);
				} else {
					System.out.println("Not Enough Memory for job: " + jobs[i] + "\n");
					i++;
				}
			} else {
				currentJob--;
				System.out.print(currentJob + " ");
				if (currentJob <= 0) {
					jobRunning = false;
					i++;
					memory = 128;
					System.out.println("\n");
				}
			}
		}
		System.out.println("Done Processing Jobs");
	}
}
