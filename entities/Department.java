package companyexpensestracker.entities;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "Department")
public class Department {

	@Id
	@Column(name = "DeptCode")
	private String deptcode;

	@Column(name = "DeptName")
	private String deptname;

	@Column(name = "HOD")
	private String hod;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "department")
	@JsonIgnore
	private List<Expenditure> expenditures = new ArrayList<>();

	@JsonIgnore
	public List<Expenditure> getExpenditure() {
		return expenditures;
	}

	public void setExpenditure(List<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public String getDeptname() {
		return deptname;
	}

	public void setDeptname(String deptname) {
		this.deptname = deptname;
	}

	public String getHod() {
		return hod;
	}

	public void setHod(String hod) {
		this.hod = hod;
	}

}
