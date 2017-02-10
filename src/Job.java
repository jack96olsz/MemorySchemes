// For individual job attributes
public class Job {
	private String name;
	private int size;
	private int time;
	private String status;

	public Job(String n, int s, int t) {
		name = n;
		size = s;
		time = t;
		status = "Not Running";
	}

	// Setters and Getters

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getSize() {
		return size;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public int getTime() {
		return time;
	}

	public void setTime(int time) {
		this.time = time;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
}
