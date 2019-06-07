package com.woniu.dao;

import java.util.List;

import com.woniu.bean.User;
import com.woniu.util.BaseDAO;

public class UserDaoImpl implements IUserDao {
	BaseDAO<User> b = new BaseDAO<User>();
	@Override
	public void save(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(Integer id) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(User user) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<User> find() {
		String sql = "select * from user";
		Object[] obj = {};
		return b.select(sql,obj,User.class);
	}

	@Override
	public User find(Integer id) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<User> findByPage(int startLine, int size) {
		String sql = "select * from user limit ?,?";
		Object[] obj = {startLine,size};
		return b.select(sql,obj,User.class);
	}

}
