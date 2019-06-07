package com.woniu.util;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import com.sun.jmx.snmp.Timestamp;
public class BaseDAO<T> {
	Connection c;
	PreparedStatement ps;
	ResultSet rs;
	public void update(String sql,Object[] objs){
		try {
			c = JdbcUtils.start();
			ps = c.prepareStatement(sql);
			for(int i=0;i<objs.length;i++){
				ps.setObject(i+1,objs[i]);
			}
			ps.executeUpdate();		
		} catch (SQLException e) {
			e.printStackTrace();
		} finally{
			JdbcUtils.close(null, ps, c);
		}
	}
	public List<T> select(String sql,Object[] objs,Class<T> cl){
		List<T> list = new ArrayList<T>();
		try {
			c = JdbcUtils.start();
			ps = c.prepareStatement(sql);
			for(int i=0;i<objs.length;i++){
				ps.setObject(i+1,objs[i]);
			}
			rs = ps.executeQuery();
			while(rs.next()){
				T t = cl.newInstance();
				Method[] method = cl.getDeclaredMethods();
				for(Method m:method){
					if(m.getName().startsWith("set")){
						String str = m.getName().substring(3);
						Class[] cc = m.getParameterTypes();
						if(cc[0]==Integer.class){
							m.invoke(t,rs.getInt(str));
						}else if(cc[0]==String.class){
							m.invoke(t,rs.getString(str));
						}else if(cc[0]==Double.class){
							m.invoke(t,rs.getDouble(str));
						}else if(cc[0]==Float.class){
							m.invoke(t,rs.getFloat(str));
						}else if(cc[0]==Date.class){
							m.invoke(t,rs.getDate(str));
						}else if(cc[0]==double.class){
							m.invoke(t,rs.getDouble(str));
						}else if(cc[0]==Timestamp.class){
							m.invoke(t,rs.getTimestamp(str));
						}
						
					}
				}
				list.add(t);		
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InvocationTargetException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally{
			JdbcUtils.close(rs, ps, c);
		}
		return list;
	}
}
