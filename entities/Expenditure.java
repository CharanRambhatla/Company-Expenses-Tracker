package companyexpensestracker.entities;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "Expenditure")
public class Expenditure {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	public int id;

	@Column(name = "CCode")
	private String ccode;

	@Column(name = "DeptCode")
	private String deptcode;

	@Column(name = "Amount")
	private Double amount;

	@Column(name = "ExpDate")
	private LocalDate expdate;

	@Column(name = "AuthorizedBy")
	private String authorizedby;

	@Column(name = "Description")
	private String description;

	@Column(name = "Code")
	private String code;

	@Column(name = "Remarks")
	private String remarks;


	@ManyToOne(fetch = FetchType.LAZY) 
	@JsonIgnore
	@JoinColumn(name = "CCode", insertable = false, updatable = false)
	private Category category;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "Code", insertable = false, updatable = false)
	private PaymentMode paymentmod;

	
	@ManyToOne(fetch = FetchType.LAZY)
	@JsonIgnore
	@JoinColumn(name = "DeptCode", insertable = false, updatable = false)
	private Department department;

	
	@JsonIgnore
	public PaymentMode getpaymentmode() {
		return paymentmod;
	}

	public void setpaymentmode(PaymentMode paymentmod) {
		this.paymentmod = paymentmod;
	}

	@JsonIgnore
	public Department getDepartment() {
		return department;
	}

	public void setDepartment(Department department) {
		this.department = department;
	}

	@JsonIgnore
	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCCode() {
		return code;
	}

	public void setCCode(String code) {
		this.code = code;
	}

	public String getDeptcode() {
		return deptcode;
	}

	public void setDeptcode(String deptcode) {
		this.deptcode = deptcode;
	}

	public Double getAmount() {
		return amount;
	}

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public LocalDate getExpDate() {
		return expdate;
	}

	public void setExpDate(LocalDate expdate) {
		this.expdate = expdate;
	}

	public String getAuthorizedby() {
		return authorizedby;
	}

	public void setAuthorizedby(String authorizedby) {
		this.authorizedby = authorizedby;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	@Override
	public String toString() {
		return "Expenditure[id=" + id + ",Code=" + code + ",DeptCode=" + deptcode + ",Amount=" + amount + ",ExpDate="
				+ expdate + ",AuthorizedBy=" + authorizedby + ",Description=" + description + ",+ Code="
				+ code + ",Remarks=" + remarks + "]";
	}

}
