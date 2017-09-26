package com.dt.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.dt.dao.EntityDao;
import com.dt.entity.User;
import com.dt.util.Paging;

/**
 * 用户service
 */
@Service("BaseInfoService")
@Transactional
public class BaseInfoService {

	@Autowired
	private EntityDao entityDao;
	
	/**
	 * 
	 * @param user
	 */
	public void saveOrUpdate(User user){
		entityDao.saveOrUpdate(user);
	}
	public Paging getList(Integer curPage,Integer pageSize){
		String hqlStr="from User";
		String countStr="select count(*) from User";
		Paging p = entityDao.createQueryPaging(hqlStr, countStr, "HQL", curPage, pageSize);
		return p;
	}
	public User getById(Integer id){
		return (User)entityDao.getObjectById(User.class, id);
	}
	public void del(Integer id){
		User user=getById(id);
		entityDao.delete(user);
	}
}
