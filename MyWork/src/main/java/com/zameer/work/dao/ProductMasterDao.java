package com.zameer.work.dao;

import java.util.List;

import com.zameer.work.bean.ProductMaster;

public interface ProductMasterDao {
	String insert(ProductMaster productMaster);
	List<ProductMaster> getProductList();
	String update(ProductMaster productMaster);
	String delete(ProductMaster productMaster);

}
