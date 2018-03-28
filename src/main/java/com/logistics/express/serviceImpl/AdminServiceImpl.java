package com.logistics.express.serviceImpl;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.logistics.express.dao.AdminMapper;
import com.logistics.express.entity.Admin;
import com.logistics.express.service.AdminService;

@Service("adminService")
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminMapper adminMapper;
	@Override
	public Admin login(Map<String, Object> map) {
		
		return adminMapper.login(map);
	}
	@Override
	public int addAdmin(Admin admin) {
		
		return adminMapper.insertSelective(admin);
	}
	@Override
	public int editAdmin(Admin admin) {
		
		return adminMapper.updateByPrimaryKeySelective(admin);
	}
	@Override
	public int editAdminPassword(Map<String,Object> map) {
		
		return adminMapper.editPassword(map);
	}
	@Override
	public Admin getAdminByPhone(String phone) {
		
		return adminMapper.getAdminByPhone(phone);
	}
	@Override
	public List<Admin> getAdminListByRoleId(Map<String,Object> map) {
	
		return adminMapper.getAdminListByRoleId(map);
	}
	@Override
	public int getAdminCount(int roleId) {
		
		return adminMapper.getAdminCount(roleId);
	}
	@Override
	public int deleteAdmin(int adminId) {
		
		return adminMapper.deleteByPrimaryKey(adminId);
	}
	@Override
	public List<Admin> getAdminByName(Map<String, Object> map) {
		
		return adminMapper.getAdminByName(map);
	}
	@Override
	public int changePassword(Map<String, Object> map) {
		
		return adminMapper.changePassword(map);
	}
	@Override
	public Admin checkAdmin(Map<String, Object> map) {
		
		return adminMapper.checkAdmin(map);
	}

}
