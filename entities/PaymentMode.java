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
@Table(name = "PaymentMode")
public class PaymentMode {

	@Id
	@Column(name = "Code")
	private String code;

	@Column(name = "Name")
	private String name;

	@Column(name = "Remarks")
	private String remarks;

	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "paymentmod")
	@JsonIgnore
	private List<Expenditure> expenditures = new ArrayList<>();
	
	@JsonIgnore
	public List<Expenditure> getExpenditure() {
	return expenditures;
	}

	public void setExpenditure(List<Expenditure> expenditures) {
		this.expenditures = expenditures;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

}
