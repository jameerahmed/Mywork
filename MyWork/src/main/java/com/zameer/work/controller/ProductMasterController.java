package com.zameer.work.controller;

import java.util.ArrayList;
import java.util.List;

import org.dozer.DozerBeanMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zameer.work.bean.ProductMaster;
import com.zameer.work.constant.ResponseMessage;
import com.zameer.work.constant.UrlPath;
import com.zameer.work.service.ProductMasterService;
import com.zameer.work.util.Result;
import com.zameer.work.ws.WSProductMaster;

@RequestMapping(value = UrlPath.PRODUCT)
@Controller
public class ProductMasterController {
	
	private static final Logger LOG = LoggerFactory.getLogger(ProductMasterController.class);
	
	@Autowired
	private ProductMasterService productMasterService;
	
	
	@Autowired(required = true)
	@Qualifier(value = "mapper")
	private DozerBeanMapper mapper;
	
	@RequestMapping(value = UrlPath.INSERT, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE,  produces = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Result> insert(@RequestBody ProductMaster productMaster){
		LOG.info("inside insert method of Role Controller");
		String result = productMasterService.insert(productMaster);
		Result message = new Result();
		message.setResult(result);
		return new  ResponseEntity<Result>(message,HttpStatus.OK);
		
	}
	
	@RequestMapping(value = UrlPath.UPDATE, method = RequestMethod.POST, consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<? extends Result> update(@RequestBody ProductMaster productMaster){
		Result result = new Result();
		String resultUpdate = ResponseMessage.ERROR;
		
			resultUpdate = productMasterService.update(productMaster);
			result.setResult(resultUpdate);
		
		return new ResponseEntity<Result>(result, HttpStatus.OK);
	}
	@RequestMapping(value = UrlPath.GET_PRODUCT_LIST, method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ResponseEntity<WSProductMaster> getUserList(){
		List<ProductMaster> productList = new ArrayList<ProductMaster>();
		List<WSProductMaster> wsProductList = new ArrayList<WSProductMaster>();
		WSProductMaster wsProduct = new WSProductMaster();
		try{
			
			productList = productMasterService.getProductList();
			for(ProductMaster productMaster: productList){
			WSProductMaster wsProductMaster =  mapper.map(productMaster, WSProductMaster.class);
			wsProductList.add(wsProductMaster);
			}	
			wsProduct.setProductMasters(wsProductList);
			wsProduct.setResult(ResponseMessage.SUCCESS);
			return new ResponseEntity<WSProductMaster>(wsProduct, HttpStatus.OK);
		}catch(Exception e){
			LOG.error(e.getMessage());
		}
		wsProduct.setResult(ResponseMessage.ERROR);
		return new ResponseEntity<WSProductMaster>(wsProduct, HttpStatus.OK);
	}

}
