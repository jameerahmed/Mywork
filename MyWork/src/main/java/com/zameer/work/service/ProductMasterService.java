package com.zameer.work.service;

import java.util.List;

import com.zameer.work.bean.ProductMaster;

public interface ProductMasterService {
	String insert(ProductMaster productMaster);
	List<ProductMaster> getProductList();
	String update(ProductMaster productMaster);
	String delete(ProductMaster productMaster);
}
