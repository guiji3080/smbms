package cn.bdqn.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import cn.bdqn.dao.ProviderDao;
import cn.bdqn.pojo.Provider;
@Service("providerService")
public class ProviderServiceImpl implements ProviderService{
	@Autowired
	private  ProviderDao providerDao;
	@Override
	public List<Provider> findAll() {
		// TODO Auto-generated method stub
		return providerDao.findAll();
	}

}
