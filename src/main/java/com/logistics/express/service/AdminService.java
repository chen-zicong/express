package com.logistics.express.service;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Admin;

public interface AdminService {
	
	//管理员登录
	public Admin login(Map<String,Object> map);
	
	//添加管理员
	public int addAdmin(Admin admin);
	
	//修改管理员信息
	public int editAdmin(Admin admin);
	
	//删除管理员
	public int deleteAdmin(int adminId);
	
	//修改管理员密码
	public int editAdminPassword(Map<String,Object> map);
	
	//根据手机号查询管理员
	public Admin getAdminByPhone(String phone);
	
	//根据角色Id获取管理员列表
	public List<Admin> getAdminListByRoleId(Map<String,Object> map);
	
	//根据角色id获取管理员数量
	public int getAdminCount(int roleId);
	
	//根据姓名获取管理员信息
	public List<Admin> getAdminByName(Map<String,Object> map);
	
	//修改密码
	public int changePassword(Map<String,Object> map);
	
	//修改密码时验证原密码是否正确
	public Admin checkAdmin(Map<String,Object> map);

}
