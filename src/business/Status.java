package business;

public class Status {
	private int id;
	private int description;
	public Status(int id, int description) {
		super();
		this.id = id;
		this.description = description;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getDescription() {
		return description;
	}
	public void setDescription(int description) {
		this.description = description;
	}
@Override
public String toString() {
	return "id = "+id+", description = "+description;
}
}
