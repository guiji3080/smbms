package cn.bdqn.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import cn.bdqn.pojo.User;

public interface UserDao {
	
	int getToTalCount();
	
	User findByCode(@Param("userCode") String userCode);
	
	int updateUser(User user);

	List<User> findAll();

	int findCountByPage(@Param("userName")String userName,@Param("userRole") Integer userRole);

	List<User> findByPage(@Param("from")int from, @Param("pageSize")int pageSize, 
			@Param("userName")String userName,@Param("userRole") Integer userRole);

	User findById(Integer id);

	int addUser(User user);

	int delUserById( Integer id);


}
