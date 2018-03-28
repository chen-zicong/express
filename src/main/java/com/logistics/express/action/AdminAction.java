package com.logistics.express.action;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.express.entity.Admin;
import com.logistics.express.entity.ApiResponse;
import com.logistics.express.service.AdminService;
import com.logistics.express.unity.MD5;

@RequestMapping("admin")
@Controller
public class AdminAction {

	@Autowired
	private AdminService adminService;

	private  Logger logger = Logger.getLogger(AdminAction.class);
	
	/**
	 * 
	 * @param admin
	 * @return
	 * Description:添加管理员
	 * @author:HAO
	 */
	@RequiresRoles(value={"2","3"},logical=Logical.OR)
	@RequestMapping(value="add",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse addAdmin(Admin admin){
		ApiResponse response = null;
		//检验数据是否为空
		if(admin.getAdminName()!=null && admin.getAdminPassword()!=null && admin.getAdminPhone()!=null && admin.getAdminRoleId()!=0){
			//检测电话是否重复
 			if(adminService.getAdminByPhone(admin.getAdminPhone())!=null){
				return new ApiResponse(0,"该用户已经存在，请勿重复添加");
			}
			//密码加密
			admin.setAdminPassword(MD5.stringMD5(admin.getAdminPassword()));
			try{
				 //添加进数据库
				int result = adminService.addAdmin(admin);
				if(result == 1){
					response = new ApiResponse(1,"添加成功");
				}else{
					response = new ApiResponse(0,"添加失败");
				}
			}catch(AuthorizationException e){
				response = new ApiResponse(-1,"无权限！");
			}catch(Exception e){
				logger.error("添加管理员时出现异常", e);
				response = new ApiResponse(-1,"未知异常");
			}
		}else{
			return new ApiResponse(0,"请完整填写数据");
		}
		return response;
	}
	
	/**
	 * 
	 * @param admin
	 * @return
	 * Description:修改管理员信息
	 * @author:HAO
	 */
	@RequestMapping(value="edit")
	@ResponseBody
	public ApiResponse editAdmin(Admin admin){
		ApiResponse response = null;
		try{
			 //修改admin的信息
			if(adminService.editAdmin(admin)==1){
				response = new ApiResponse(1,"修改成功");
			}else{
				response = new ApiResponse(0,"修改失败");
			}
		}catch(Exception e){
			logger.error("修改信息时出现异常", e);
			response = new ApiResponse(-1,"未知异常");
		}
		return response;
	}
	
	
	/**
	 * Description:删除管理员
	 * @param adminId
	 * @return
	 */
	@RequiresRoles(value={"2","3"},logical=Logical.OR)
	@RequestMapping(value="deleteAdmin",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse deleteAdmin(int adminId){
		ApiResponse response = null;
		try{
			if(adminService.deleteAdmin(adminId)==1){
				response = new ApiResponse(1,"删除成功");
			}else{
				response = new ApiResponse(0,"删除失败");
			}
		}catch(AuthorizationException e){
			response = new ApiResponse(-1,"无权限！");
		}catch(Exception e){
			logger.error("删除管理员时出现异常", e);
			response = new ApiResponse(-1,"未知异常");
		}
		return response;
	}
	
	/**
	 * 
	 * @param roleId    管理员的id
	 * @param page 	 查询的页数
	 * @param rows	 每一页的行数
	 * @return
	 * Description:获取管理员列表
	 * @author:HAO
	 */
	@RequestMapping(value="getAdminList",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getAdminList(int roleId,int page,int rows,String adminName){
		Map<String,Object> jo = new HashMap();
		Map<String,Object> map = new HashMap<>();
		//admin为Null则分页查询管理员列表
		if(adminName == null || adminName.isEmpty()){
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("roleId", roleId);//角色id
			map.put("start", (page-1)*10);//页数  查第一页就从0开始, 第二页就从10开始  ,这个10 应该改为rows
			map.put("pagesize",rows);//每页行数
			List<Admin> adminList = adminService.getAdminListByRoleId(map);//获取管理员列表
			int count = adminService.getAdminCount(roleId);//数量
			jo.put("total",count);
			jo.put("rows",adminList);
		}else{//admin不为空则搜索
			map.put("adminName", adminName);
			map.put("roleId", roleId);
			List<Admin> adminList = adminService.getAdminByName(map);//搜索管理员
			jo.put("total",adminList.size());//总数
			jo.put("rows",adminList); // 添加列表
		}
		return jo;
	}
	
	/**
	 * 
	 * @param adminPhone
	 * @param oldPassword
	 * @param newPassword
	 * @return
	 * Description:修改密码
	 * @author:HAO
	 */
	@RequestMapping(value="changePassword",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse changePassword(String newPassword,String oldPassword,String adminPhone){
		ApiResponse response = null;
		if(StringUtils.isBlank(oldPassword)){
			return new ApiResponse(0,"原密码不能为空");
		}
		if(StringUtils.isBlank(newPassword)){
			return new ApiResponse(0,"新密码不能为空");
		}
		Map<String,Object> map1 = new HashMap<>();
		Map<String,Object> map2 = new HashMap<>();
		map1.put("adminPassword",  MD5.stringMD5(oldPassword));
		map1.put("adminPhone", adminPhone);
		Admin admin = adminService.checkAdmin(map1);//判断原密码是否正确
		if(admin != null){//原密码正确，可以修改密码
			map2.put("adminPassword", MD5.stringMD5(newPassword));
			map2.put("adminPhone", adminPhone);
			int result = adminService.changePassword(map2);
			if(result==1){
				return new ApiResponse(1,"操作成功");
			}else{
				return new ApiResponse(0,"操作失败");
			}
		}else{//原密码不正确
			return new ApiResponse(0,"原密码不正确，无法修改密码");
		}
	}
	
	
}
