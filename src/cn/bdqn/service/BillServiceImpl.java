package cn.bdqn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.dao.BillDao;
import cn.bdqn.pojo.Bill;
import cn.bdqn.util.PageBean;
@Service
public class BillServiceImpl implements BillService {
	@Autowired
	private BillDao billDao;

	@Override
	public List<Bill> findAll() {
		// TODO Auto-generated method stub
		return billDao.findAll();
  }
	//分页
	@Override
	public PageBean<Bill> findBypage(int pageNo, int pageSize,
			String productName, Integer providerId, Integer isPayment) {
		PageBean<Bill> billPage=new PageBean<Bill>();
		billPage.setPageSize(pageSize);
		int totalCount=billDao.findCountByPage(productName, providerId, isPayment);
		billPage.setTotalCount(totalCount);
		billPage.setPageNo(pageNo);
		int from=(billPage.getPageNo()-1)*pageSize;
		List<Bill> pageList=billDao.findByPage(from, pageSize, productName, providerId, isPayment);
		billPage.setPageList(pageList);
		return billPage;
	}
	@Override
	public int addBill(Bill bill) {
		// TODO Auto-generated method stub
		return billDao.addBill(bill);
	}
	@Override
	public Bill findById(Integer billid) {
		// TODO Auto-generated method stub
		return billDao.finById(billid);
	}
	@Override
	public int delBill(Integer billid) {
		// TODO Auto-generated method stub
	 return billDao.delBill(billid);
	}
}