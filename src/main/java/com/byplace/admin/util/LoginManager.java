package com.byplace.admin.util;

import java.util.Collection;
import java.util.Enumeration;
import java.util.Hashtable;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;

import com.byplace.admin.dao.AdminUserDAO;

public class LoginManager implements HttpSessionBindingListener{
	private static LoginManager loginManager = null;
	private static Hashtable loginUsers = new Hashtable();
	
	public static synchronized LoginManager getInstance() {
		if(loginManager == null)
			loginManager = new LoginManager();
		return loginManager;
	}
	
	public void valueBound(HttpSessionBindingEvent event) {
		loginUsers.put(event.getSession(), event.getName());
		AdminUserDAO dao = new AdminUserDAO();
		dao.login(event.getName());
	}
	
	public void valueUnbound(HttpSessionBindingEvent event) {
		loginUsers.remove(event.getSession());
		AdminUserDAO dao = new AdminUserDAO();
		dao.logout(event.getName());
	}
	
	public void removeSession(String userId) {
		Enumeration e = loginUsers.keys();
		HttpSession session = null;
		while(e.hasMoreElements()) {
			session = (HttpSession)e.nextElement();
			if(loginUsers.get(session).equals(userId)) {
				session.invalidate();
			}
		}
	}
	
	public boolean isUsing(String userId) {
		return loginUsers.containsValue(userId);
	}
	
	public int getUserCount() {
		return loginUsers.size();
	}
	
	public Collection LoginUserList() {
		Collection collection = loginUsers.values();
		return collection;
	}
	
	public void setSession(HttpSession session, String userId) {
		session.setAttribute(userId, this);
	}
}
