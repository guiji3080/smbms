package cn.bdqn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.pojo.Bill;

public interface BillDao {

	List<Bill> findAll();
	//获取总记录数
	int findCountByPage(@Param("productName") String productName,
			@Param("providerId") Integer providerId,
			@Param("isPayment")Integer isPayment);
	//获取页面类容
	List<Bill> findByPage(@Param("from")int from, 
			@Param("pageSize") int pageSize,
			@Param("productName") String productName,
			@Param("providerId") Integer providerId, 
			@Param("isPayment") Integer isPayment);
	int addBill(Bill bill);
	Bill finById(Integer billid);
	int delBill(Integer billid);

}
