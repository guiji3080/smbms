package cn.bdqn.service;

import java.util.List;

import cn.bdqn.pojo.User;
import cn.bdqn.util.PageBean;

public interface UserService {
	
	
	int getToTalCount();

	User findBylogin(String userCode, String userPassword);

	List<User> findAll();
	//分页
	PageBean<User> findByPage(int pageNo,int pageSize,String userName,Integer userRole);

	boolean chekUserCode(String userCode);

	User findById(Integer id);

	int updateUser(User user);

	int addUser(User user);



	int delUserById(Integer id);

}
