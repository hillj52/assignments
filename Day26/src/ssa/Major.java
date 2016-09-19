package ssa;

public class Major {
	
	private int id;
	private String description;
	private int minSat;
	
	public int getId() {
		return id;
	}

	private void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	private void setDescription(String description) {
		this.description = description;
	}

	public int getMinSat() {
		return minSat;
	}

	private void setMinSat(int minSat) {
		this.minSat = minSat;
	}

	public Major(int id, String description, int minSat) {
		this.setId(id);
		this.setDescription(description);
		this.setMinSat(minSat);
	}
}
