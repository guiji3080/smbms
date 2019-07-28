package cn.bdqn.service;

import java.util.List;

import cn.bdqn.pojo.Bill;
import cn.bdqn.util.PageBean;

public interface BillService {

	List<Bill> findAll();
	
	PageBean<Bill> findBypage(int pageNo,int pageSize,String productName,Integer providerId,Integer isPayment );

	int addBill(Bill bill);

	Bill findById(Integer billid);

	int delBill(Integer billid);

}
