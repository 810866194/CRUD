package com.woniu.service;

import java.util.List;

import com.woniu.bean.Page;
import com.woniu.bean.User;
import com.woniu.dao.IUserDao;
import com.woniu.dao.UserDaoImpl;

public class UserServiceImpl implements IUserService {
	private IUserDao dao = new UserDaoImpl();
	@Override
	public void save(User user) {
		dao.save(user);

	}

	@Override
	public void delete(Integer id) {
		dao.delete(id);

	}

	@Override
	public void update(User user) {
		dao.update(user);

	}

	@Override
	public List<User> find() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public User find(Integer id) {	
		return dao.find(id);
	}

	@Override
	public Page findByPage(int p, int size) {
		List<User> list = dao.find();
		int rowCount = list.size();
		Page page = new Page(p,rowCount,size);
		List<User> list2 = dao.findByPage(page.getStartLine(),page.getSize()); 
		page.setList(list2);
		return page;
	}


}
