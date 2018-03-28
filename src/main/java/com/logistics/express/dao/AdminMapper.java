package com.logistics.express.dao;

import java.util.List;
import java.util.Map;

import com.logistics.express.entity.Admin;

public interface AdminMapper {
    int deleteByPrimaryKey(Integer adminId);

    int insert(Admin record);

    int insertSelective(Admin record);

    Admin selectByPrimaryKey(Integer adminId);

    int updateByPrimaryKeySelective(Admin record);

    int updateByPrimaryKey(Admin record);
    
    Admin login(Map<String,Object> map);
    
    int editPassword(Map<String,Object> map);
    
    Admin getAdminByPhone(String phone);
    
    List<Admin> getAdminListByRoleId(Map<String,Object> maps);
    
    int getAdminCount(int roleId);
    
    List<Admin> getAdminByName(Map<String,Object> map);
    
    int changePassword(Map<String,Object> map);
    
    Admin checkAdmin(Map<String,Object> map);
}