package com.woniu.web.action;
import org.apache.struts2.ServletActionContext;

import com.opensymphony.xwork2.ActionSupport;
import com.opensymphony.xwork2.ModelDriven;
import com.woniu.bean.Page;
import com.woniu.bean.User;
import com.woniu.service.IUserService;
import com.woniu.service.UserServiceImpl;
public class UserAction extends ActionSupport implements ModelDriven<User>{
	private User user = new User();
	private IUserService service = new UserServiceImpl(); 
	private int p; 
	
	
	public String find() {
		Page page = service.findByPage(p,5);
		ServletActionContext.getRequest().setAttribute("page", page);
		return "findUI";
	}
	public String editUI() { 
		ServletActionContext.getRequest().setAttribute("user", service.find(user.getUid()));
		return "editUI";
	}
	public String edit() { 
		service.update(user);
		return "find";
	}
	public String save() { 
		service.save(user);
		return "find";
	}
	public String delete() { 
		service.delete(user.getUid());
		return "find";
	}
	
	
	
	
	
	
	public void setP(int p) {
		this.p = p;
	}
	@Override
	public User getModel() {
		// TODO Auto-generated method stub
		return user;
	}

}
