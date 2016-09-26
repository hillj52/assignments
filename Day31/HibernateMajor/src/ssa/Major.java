package ssa;

import javax.persistence.*;

@Entity
@Table(name="major")
public class Major {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	@Column(name="id")
	private int id;
	@Column(name="description")
	private String description;
	@Column(name="req_sat")
	private int reqSat;
		
	public String toString() {
		return String.format("%-3s",this.id) + String.format("%-30s",this.description) + String.format("%-4s",this.reqSat);
	}
	
	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public int getReqSat() {
		return reqSat;
	}

	public void setReqSat(int reqSat) {
		this.reqSat = reqSat;
	}

	public int getId() {
		return id;
	}

	public Major() {}
	
	public Major(String description, int reqSat) {
		this.description = description;
		this.reqSat = reqSat;
	}
}
