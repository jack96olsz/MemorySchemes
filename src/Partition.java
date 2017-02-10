// Partition of the Memory
public class Partition {
	private int num; // Partition #
	private int size; // Size of the partition
	private String status; // Free or Busy
	private Job currentJob; // Current job occupying partition

	
	// (partition #, Size of partition)
	public Partition(int n, int s) {
		num = n;
		size = s;
		status = "Free"; // default
	}

	// Setters and Getters

	public int getNum() {
		return num;
	}

	public void setNum(int num) {
		this.num = num;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Job getCurrentJob() {
		return currentJob;
	}

	public void setCurrentJob(Job currentJob) {
		this.currentJob = currentJob;
	}
}
