package com.logistics.express.action;

import java.io.File;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.Driver;
import com.logistics.express.service.DriverService;
import com.logistics.express.service.TransportCompanyService;

@RequestMapping("driver")
@Controller
public class DriverAction {
	
	@Autowired
	private DriverService driverService;
	
	@Autowired
	private TransportCompanyService transportCompanyService;
	
	private Logger logger = Logger.getLogger(DriverAction.class);
	
	/**
	 * Description:获取司机列表
	 * @param page
	 * @param rows
	 * @param status
	 * @return
	 */
	@RequestMapping(value="getDriver",method=RequestMethod.POST)
	@ResponseBody
	public Map<String,Object> getDriverList(int page,int rows,int status,String driverName){
		Map<String,Object> jo = new HashMap<>();
		Map<String,Object> map = new HashMap<>();
		System.out.println(page+"-----"+rows+"-----"+status);
		//司机名字为空则为分页查询数据
		if(driverName == null || driverName.isEmpty()){
			//分页查询
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("status", status);//0表示未审核，1表示已审核
			map.put("start", (page-1)*10);//页数
			map.put("pagesize",rows);//每页行数
			List<Driver> driverList = driverService.getDriver(map);
			if(!CollectionUtils.isEmpty(driverList)){
				for(int i=0;i<driverList.size();i++){
					Driver driver = driverList.get(i);
					if(driver.getComId()!=null && driver.getComId()!=0){//如果承运公司id不为空则设置所属公司名称
						int comId = driver.getComId();
						driver.setCompany(transportCompanyService.getNameById(comId));
					}
				}
			}
			int count = driverService.getDriverCount(status);//数量
			jo.put("total",count);
			jo.put("rows",driverList);
		}else{//司机名字不为空则按名字搜索
			map.put("status", status);
			map.put("driverName", driverName);
			List<Driver> driverList = driverService.getDriverByName(map);//按名获取司机信息
			if(!CollectionUtils.isEmpty(driverList)){
				for(int i=0;i<driverList.size();i++){
					Driver driver = driverList.get(i);
					if(driver.getComId()!=null && driver.getComId()!=0){
						int comId = driver.getComId();//如果公司id不为空则设置公司名称
						driver.setCompany(transportCompanyService.getNameById(comId));
					}
				}
			}
			jo.put("total",driverList.size());
			jo.put("rows",driverList);
		}

		return jo;
	}
	
	/**
	 * Description:审核司机
	 * @param driver
	 * @return
	 */
	@RequiresRoles(value={"2","3"},logical=Logical.OR)
	@RequestMapping(value="check",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse checkDriver(Driver driver){
		ApiResponse apiResponse = null;
		if(driver != null){
			//如果审核不通过则删除司机,1为通过,0为拒绝
			if(driver.getStatus()==0){
				if(driverService.deleteByPrimaryKey(driver.getId())==1){
					apiResponse = new ApiResponse(1,"操作成功");
				}else{
					apiResponse = new ApiResponse(0,"操作失败");
				}
			}
			else{
				try{
					if(driverService.checkDriver(driver)==1){//司机审核通过
						apiResponse = new ApiResponse(1,"操作成功");
					}else{
						apiResponse = new ApiResponse(0,"操作失败");
					}
				}catch(AuthorizationException e){
					apiResponse = new ApiResponse(-1,"无权限！");
				}catch(Exception e){
					logger.error("审核司机时出现异常", e);
					apiResponse = new ApiResponse(-1,"未知错误");
				}
			}
		}
		return apiResponse;
	}
	
	/**
	 * Description:微信端司机注册及后台公司录入司机信息
	 * @param driver
	 * @return
	 */
	@RequestMapping(value="register",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse driverRegister(Driver driver,@RequestParam("driverIdpic")MultipartFile[] driverIdpic,String comid,HttpServletRequest request){
		ApiResponse apiResponse = null;
		//封装司机身份证照片路径
		driver.setDriverIdPic(uploadFiles(driverIdpic,request));
		try{
			if(comid != null && !comid.equals("")){//公司id不为空表示为公司录入司机
				driver.setComId(Integer.parseInt(comid));
				driver.setStatus(1);//若公司录入司机则无需审核,为1为审核通过
			}else{
				driver.setStatus(0);//微信端注册需审核
			}
			if(driverService.driverRegister(driver)==1){//司机注册
				apiResponse = new ApiResponse(1,"操作成功");
			}else{
				apiResponse = new ApiResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("注册或录入司机时出现异常", e);
			apiResponse = new ApiResponse(-1,"未知错误"+e.getLocalizedMessage());
		}
		return apiResponse;
	}
	
	//上传图片并返回图片路径
	public String uploadFiles(MultipartFile[] files,HttpServletRequest request){
		//上传路径
		String imgUploadUrl = "/upload/files/driver/";
		StringBuilder sb = new StringBuilder();
		if(files != null && files.length > 0){
			for(int i = 0; i < files.length; i ++){
				MultipartFile file = files[i];
				saveFile(file,request);
				if(file == files[files.length-1]){
					sb.append(imgUploadUrl + file.getOriginalFilename());
				}else{
					sb.append(imgUploadUrl + file.getOriginalFilename()+",");
				}
			}
		}
		return sb.toString();
	}
	
	/**
	 * 
	 * @param file
	 * @return
	 * Description:保存文件到服务器
	 * @author:HAO
	 */
	public boolean saveFile(MultipartFile file,HttpServletRequest request) {  
		//保存到服务器的路径
		String bootUrl="/usr/local/tomcat7/webapps";//保存的根目录
        String uploadUrl="/upload/files/driver";
        String savedirpath = bootUrl + uploadUrl;
        if (!file.isEmpty()) {  
            try {  
            	File savedir = new File(savedirpath);
            	if(!savedir.exists()){
            		savedir.mkdirs();
            	}
                // 文件保存路径  
                String filePath = savedirpath + File.separator
                        + file.getOriginalFilename();  
                // 转存文件  
                file.transferTo(new File(filePath));  
             
                return true;  
            } catch (Exception e) {  
                e.printStackTrace();  
                logger.error("上传文件时出现异常", e);
            }  
        }  
        return false;    
    }  
	
	/**
	 * @param driverName
	 * @param driverPhone
	 * @return
	 * Description:微信端验证司机身份
	 * @author:HAO
	 */
	@RequestMapping(value="getDriverStatus",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse getDriverStatus(String driverName,String driverPhone,HttpServletRequest request){
		ApiResponse apiResponse = null;
		Map<String,Object> map = new HashMap<>();
		if(driverName != null && driverPhone != null){
			map.put("driverName", driverName);
			map.put("driverPhone", driverPhone);
			Driver driver = driverService.getDriverStatus(map);
			if(driver == null){
				apiResponse = new ApiResponse(0,"无该用户");
			}else{
				//判断司机是否已被审核,1表示审核通过
				if(driver.getStatus()==1){
					HttpSession session = request.getSession();
					session.setAttribute("status", 1);//审核状态
					session.setAttribute("id", driver.getId());//司机id
					apiResponse = new ApiResponse(1,"验证成功");
				}else{
					apiResponse = new ApiResponse(0,"验证失败");
				}
			}
		}else{
			apiResponse = new ApiResponse(0,"请填写完整数据");
		}
		return apiResponse;
	}
	
	/**
	 * @param id
	 * @return
	 * Description:获取司机身份证图片路径
	 * @author:HAO
	 */
	@RequestMapping(value="getImg",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse<String[]> getImgByOrder(int id, HttpServletRequest request){
		ApiResponse<String[]> apiResponse = null;
		String img = driverService.getImgById(id);
		if(img != null && img != "" && img.length() != 0){
			String[] imgUrl = getImgUrl(img,request);
			apiResponse = new ApiResponse<>(1,imgUrl,"获取成功");
		}else{
			apiResponse = new ApiResponse<String[]>(0,"无图片");
		}
		return apiResponse;
	}
	
	//封装图片路径，可直接访问
	private String[] getImgUrl(String img,HttpServletRequest request){
		//访问文件路径
		String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort();
		if(img.indexOf(",") != -1){
			//有多张图片情况
			String[] imgUrl = img.split(",");
			for(int i=0;i<imgUrl.length;i++){
				imgUrl[i] = basePath+imgUrl[i];
			}
			return imgUrl;
		}else{
			//只有一张图片情况
			String[] imgArray = new String[1];
			imgArray[0] = basePath+img;
			return imgArray;
		}
	}

}
