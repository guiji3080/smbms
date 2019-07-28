package cn.bdqn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.dao.UserDao;
import cn.bdqn.pojo.User;
import cn.bdqn.util.PageBean;
@Service("userService")
public class UserServiceImpl implements UserService {
	@Autowired
	private UserDao userDao;
	@Override
	public int getToTalCount() {
		// TODO Auto-generated method stub
		return userDao.getToTalCount();
	}
	@Override
	public User findBylogin(String userCode, String userPassword) {
		User user=userDao.findByCode(userCode);
		if(user!=null && user.getUserPassword().equals(userPassword)){
			return user;
		}else{
			return null;
		}
		
	}
	@Override
	public List<User> findAll() {
		
		return userDao.findAll();
	}
	@Override
	public PageBean<User> findByPage(int pageNo, int pageSize, String userName,
			Integer userRole) {
		PageBean<User> userPage =new PageBean<User>();
		userPage.setPageSize(pageSize);
		int totalCount=userDao.findCountByPage(userName,userRole);
		userPage.setTotalCount(totalCount);
		userPage.setPageNo(pageNo);
		int from=(userPage.getPageNo()-1)*pageSize;//标注起始页
		List<User> pageList=userDao.findByPage(from,pageSize,userName,userRole);
		userPage.setPageList(pageList);
		return userPage;
	}
	@Override
	public boolean chekUserCode(String userCode) {
		User user=userDao.findByCode(userCode);
		if(user!=null)
			return true;
		else
			return false;
	}
	@Override
	public User findById(Integer id) {
		
		return userDao.findById(id);
	}
	@Override
	public int updateUser(User user) {
		// TODO Auto-generated method stub
		return userDao.updateUser(user);
	}
	@Override
	public int addUser(User user) {
		// TODO Auto-generated method stub
		return userDao.addUser(user);
	}
	@Override
	public int delUserById(Integer id) {
		// TODO Auto-generated method stub
		return userDao.delUserById(id);
	}


}
