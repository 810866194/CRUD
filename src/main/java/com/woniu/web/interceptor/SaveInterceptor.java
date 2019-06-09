package com.woniu.web.interceptor;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.Interceptor;
import com.woniu.bean.User;

public class SaveInterceptor implements Interceptor{
	@Override
	public void destroy() {
	}
	@Override
	public void init() {
	}
	@Override
	public String intercept(ActionInvocation invocation) throws Exception {
		
		Object obj = invocation.getAction();
		Class clazz = obj.getClass();
		Method[] methods = clazz.getDeclaredMethods();
		for(Method m:methods) {
			System.out.println(m.getName());
			if("save".equals(m.getName())) {
				System.out.println("1111");
				String uname = ServletActionContext.getRequest().getParameter("uname");
			}else {
				invocation.invoke();
			}
				
		}
		return null;
	}

}
