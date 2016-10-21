package com.zameer.work.ws;

import java.util.Date;
import java.util.List;


public class WSProductMaster {
	private Integer id;
	private String name;
	private Long price;
	private String description;
	private Integer createdBy;
	private Integer updatedBy;
	private Date createdDate;
	private Date updatedDate;
	
	private String result;

	private List<WSProductMaster> productMasters;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	
	public Long getPrice() {
		return price;
	}
	public void setPrice(Long price) {
		this.price = price;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getCreatedBy() {
		return createdBy;
	}
	public void setCreatedBy(Integer createdBy) {
		this.createdBy = createdBy;
	}
	public Integer getUpdatedBy() {
		return updatedBy;
	}
	public void setUpdatedBy(Integer updatedBy) {
		this.updatedBy = updatedBy;
	}
	public Date getCreatedDate() {
		return createdDate;
	}
	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}
	public Date getUpdatedDate() {
		return updatedDate;
	}
	public void setUpdatedDate(Date updatedDate) {
		this.updatedDate = updatedDate;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public List<WSProductMaster> getProductMasters() {
		return productMasters;
	}
	public void setProductMasters(List<WSProductMaster> productMasters) {
		this.productMasters = productMasters;
	}
	

}
