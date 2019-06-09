package com.woniu.dao;

import java.util.List;

import com.woniu.bean.User;
import com.woniu.util.BaseDAO;

public class UserDaoImpl implements IUserDao {
	BaseDAO<User> b = new BaseDAO<User>();
	@Override
	public void save(User user) {
		String sql = "insert into user values(null,?,?,?)";
		Object[] obj = {user.getUname(),user.getUpwd(),user.getUdate()};
		b.update(sql, obj);
	}

	@Override
	public void delete(Integer id) {
		String sql = "delete from user where uid=?";
		Object[] obj = {id};
		b.update(sql, obj);
	}

	@Override
	public void update(User user) {
		String sql = "update user set uname=?,upwd=?,udate=? where uid=?";
		Object[] obj = {user.getUname(),user.getUpwd(),user.getUdate(),user.getUid()};
		b.update(sql, obj);
	}

	@Override
	public List<User> find() {
		String sql = "select * from user";
		Object[] obj = {};
		return b.select(sql,obj,User.class);
	}

	@Override
	public User find(Integer id) {
		String sql = "select * from user where uid=?";
		Object[] obj = {id};
		List<User> list = b.select(sql, obj, User.class);
		return list.size()==0?null:list.get(0);
	}

	@Override
	public List<User> findByPage(int startLine, int size) {
		String sql = "select * from user limit ?,?";
		Object[] obj = {startLine,size};
		return b.select(sql,obj,User.class);
	}

}
