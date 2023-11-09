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
@Table(name = "Category")
public class Category {

	@Id
	@Column(name = "CCode")
	private String code;

	@Column(name = "CategoryName")
	private String categoryname;

	@JsonIgnore
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Expenditure> expenditures = new ArrayList<>();


	public void setCategoryName(String categoryname) {
		this.categoryname = categoryname;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCategoryname() {
		return categoryname;
	}

	public void setCategoryname(String categoryname) {
		this.categoryname = categoryname;
	}


	@JsonIgnore
	public List<Expenditure> getExpenditure() {
		return expenditures;
	}

	public void setExpenditure(List<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}
}
