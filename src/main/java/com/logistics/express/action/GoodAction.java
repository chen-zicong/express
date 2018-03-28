package com.logistics.express.action;

import java.io.File;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.apache.shiro.authz.AuthorizationException;
import org.apache.shiro.authz.annotation.Logical;
import org.apache.shiro.authz.annotation.RequiresRoles;
import org.apache.shiro.util.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.logistics.express.entity.ApiResponse;
import com.logistics.express.entity.Consignor;
import com.logistics.express.entity.Driver;
import com.logistics.express.entity.GoodDetail;
import com.logistics.express.entity.GoodOrder;
import com.logistics.express.entity.GoodPay;
import com.logistics.express.entity.GoodTransport;
import com.logistics.express.entity.GoodTransportNode;
import com.logistics.express.entity.GoodTransportProcess;
import com.logistics.express.service.DriverService;
import com.logistics.express.service.GoodDetailService;
import com.logistics.express.service.GoodOrderService;
import com.logistics.express.service.GoodPayService;
import com.logistics.express.service.GoodTransportNodeService;
import com.logistics.express.service.GoodTransportProcessService;
import com.logistics.express.service.GoodTransportService;
import com.logistics.express.unity.DateUnti;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("good")
@Controller
public class GoodAction {
	
	@Autowired
	private GoodOrderService goodOrderService;
	
	@Autowired
	private GoodDetailService goodDetailService;
	
	@Autowired
	private GoodTransportService goodTransportService;
	
	@Autowired
	private GoodTransportNodeService goodTransportNodeService;
	
	@Autowired
	private GoodTransportProcessService goodTransportProcessService;
	
	@Autowired
	private GoodPayService goodPayService;
	
	@Autowired
	private DriverService driverService;
	
	
	private Logger logger = Logger.getLogger(GoodAction.class);
	
	/**
	 * 
	 * @param goodOrder
	 * @param goodDetail
	 * @return
	 * Description:货主录入订单
	 * @author:HAO
	 * @throws ParseException 
	 */
	@Transactional
	@RequestMapping(value="addGoodOrder",method=RequestMethod.POST)
	@ResponseBody
	public ApiResponse addGoodOrder(GoodOrder goodOrder,GoodDetail goodDetail,String deliverytime,String requireArrivetime,@RequestParam("primitiveImgs")MultipartFile[] primitiveImgs,@RequestParam("packImgs")MultipartFile[] packImgs,HttpServletRequest request) throws ParseException{
		ApiResponse response = null;
		GoodTransport goodTransport = new GoodTransport();
		GoodTransportProcess transportProcess = new GoodTransportProcess();
		//生成订单号  (把收件人的手机号码加上日期形成订单号
		StringBuilder sb = new StringBuilder(goodOrder.getConsigneePhone());
		sb.append(new Date().getTime());

		//SimpleDateFormat  可以把符合日期格式的String 类型转换成 date 类型 , 下面的String 没有包含秒 所以补一个
		deliverytime = deliverytime +":00";
		requireArrivetime = requireArrivetime + ":00";
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date deliveryTime = sdf.parse(deliverytime);//发送时间
		Date requireArriveTime = sdf.parse(requireArrivetime);//要求到达时间
		String orderNumber = sb.toString();
		goodOrder.setDeliveryTime(deliveryTime);
		goodOrder.setRequireArriveTime(requireArriveTime);
		goodOrder.setGoodOrderNumber(orderNumber);//初始化订单
		goodDetail.setGoodOrderNumber(orderNumber);//初始化货物细节信息
		goodTransport.setGoodOrderNumber(orderNumber);//初始化货物运输信息
		goodTransport.setGoodTransportDriverId(0);
		goodTransport.setGoodTransportConsigneeName(goodOrder.getConsigneeName());
		goodTransport.setGoodTransportConsigneePhone(goodOrder.getConsigneePhone());
		goodTransport.setGoodTransportDispatchWay(goodOrder.getDeliveryWay());
		transportProcess.setGoodOrderNumber(orderNumber);//初始化货物运输过程信息
		transportProcess.setGoodTransportProcessStatus(0);
		transportProcess.setGoodTransportProcessPosition("暂无位置信息");
		//封装图片路径
		goodDetail.setGoodDetailPrimitivePhoto(uploadFiles(primitiveImgs,request));//货物原始图片
		goodDetail.setGoodDetailPackPhoto(uploadFiles(packImgs,request));//货物打包图片
		try{
			int orderResult = goodOrderService.addGoodOrder(goodOrder);//新增货物订单
			int detailResult = goodDetailService.addGoodDetail(goodDetail);//新增货物细节信息
			int transportResult = goodTransportService.addGoodTransportMessage(goodTransport);//新增货物运输信息
			int transportProcessResult = goodTransportProcessService.addTransportProcessMessage(transportProcess);//新增货物运输过程信息
			if(orderResult==1 && detailResult==1 && transportResult==1 && transportProcessResult==1){
				response = new ApiResponse(1,"操作成功");
			}else{
				response = new ApiResponse(0,"操作失败");
			}
		}catch(Exception e){
			logger.error("创建订单时出现异常", e);
			response = new ApiResponse(-1,"内部错误");
		}
		return response;
	}
	
		//上传图片并返回图片路径
		public String uploadFiles(MultipartFile[] files,HttpServletRequest request){
			//上传路径
			String imgUploadUrl = "/express/"+"upload/files/good/";
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
		 * @param goodOrderNumber
		 * @return
		 * Description:删除订单 
		 * @author:HAO
		 */
		@Transactional
		@RequestMapping(value="deleteOrder",method=RequestMethod.POST)
		@ResponseBody
		@RequiresRoles(value={"2","3"},logical=Logical.OR)
		public ApiResponse deleteOrder(String goodOrderNumber){
			ApiResponse response = null;
			try{
				int result1 = goodOrderService.deleteOrder(goodOrderNumber);
				//把货物细节信息，货物运输信息,运输过程信息一并删除
				int result2 = goodDetailService.deleteDetail(goodOrderNumber);
				int result3 = goodTransportService.deleteTransportMessage(goodOrderNumber);
				int result4 = goodTransportProcessService.deleteProcessMessage(goodOrderNumber);
				if(result1 == 1 &&  result2 == 1 && result3 == 1 && result4 == 1){
					response = new ApiResponse(1,"操作成功");
				}else{
					response = new ApiResponse(0,"操作失败");
				}
			}catch(AuthorizationException e){
				response = new ApiResponse(-1,"无权限！");
			}catch(Exception e){
				response = new ApiResponse(-1,"未知错误！");
			}
			
			return response;
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
			String bootUrl="/usr";//保存的根目录
	        String uploadUrl="/express/"+"upload/files/good";
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
		 * 
		 * @param goodTransport
		 * @return
		 * Description:更新运输信息，如运输方式，运输单位和司机,货运价格等信息 
		 * @author:HAO
		 */
		@Transactional
		@RequestMapping(value="upTransportMessage",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse updateTransportMessage(GoodTransport goodTransport){
			ApiResponse response = null;
			String goodOrderNumber = goodTransport.getGoodOrderNumber();
			try{//若为派发业务接件人则需改变订单接件状态
				if(goodTransport.getGoodTransportConnectorPhone()!=null){
					GoodOrder goodOrder = new GoodOrder();
					goodOrder.setGoodOrderNumber(goodOrderNumber);
					goodOrder.setConnectStatus(1);//1表示已有业务员接件
					goodOrderService.updateOrder(goodOrder);
				}
				if(goodTransport.getAuditeStatus()==2){//为2表示审核通过
					//若订单审核通过则改变对应运输过程信息状态
					GoodTransportProcess goodTransportProcess = new GoodTransportProcess();
					goodTransportProcess.setAuditeStatus(1);//1表示审核已通过
					goodTransportProcess.setGoodOrderNumber(goodOrderNumber);
					goodTransportProcessService.updateTransportProcessMessage(goodTransportProcess);
				}
				int result = goodTransportService.updateTransportMessage(goodTransport);
				if(result == 1){
					response = new ApiResponse(1,"操作成功");
				}else{
					response = new ApiResponse(0,"操作失败");
				}
			}catch(Exception e){
				logger.error("更新运输信息时出现异常", e);
				response = new ApiResponse(-1,"未知错误");
			}
			return response;
		}
		
		/**
		 * @param page
		 * @param rows
		 * @return
		 * Description:获取业务接件信息
		 * @author:HAO
		 */
		@RequestMapping(value="getConnectMessage")
		@ResponseBody
		public Map<String,Object> getConnectMessage(int page,int rows,int auditeStatus,String goodOrderNumber){
			ApiResponse response = null;
			Map<String,Object> jo = new HashMap();
			Map<String,Object> map = new HashMap<>();
			//订单号不为空则为分页查询数据
			if(goodOrderNumber == null || goodOrderNumber.isEmpty()){
				if(page == 0){
					page = 1;
				}
				if(rows == 0){
					rows = 10;
				}
				map.put("start", (page-1)*10);//页数
				map.put("pagesize",rows);//每页行数
				map.put("auditeStatus", auditeStatus);//审核状态
				List<GoodTransport> goodTransportList = goodTransportService.getConnectMessage(map);//分页获取
				if(!CollectionUtils.isEmpty(goodTransportList)){
					for(int i=0;i<goodTransportList.size();i++){
						GoodTransport goodTransport = goodTransportList.get(i);
						Date requireArriveTime = goodOrderService.getRequireArrivetime(goodTransport.getGoodOrderNumber());
						goodTransport.setRequireArrivetime(DateUnti.dateToStr(requireArriveTime,12));//设置应送达时间（把date类型转换为string，方便前端展示）
						goodTransport.setGoodTransportPrice(goodTransport.getGoodTransportprice()+"");//设置货运价格
						int driverId = goodTransport.getGoodTransportDriverId();
						if(driverId != 0){//获取司机信息
							Driver driver = driverService.getDriverById(driverId);//设置司机名字和手机号
							goodTransport.setDriverName(driver.getDriverName());
							goodTransport.setDriverPhone(driver.getDriverPhone());
						}
					}
				}
				int count = goodTransportService.getConnectMessageCount(auditeStatus);
				jo.put("total",count);
				jo.put("rows",goodTransportList);
			}else{//订单号不为空则按号搜索
				List<GoodTransport> list = new ArrayList<>();
				GoodTransport goodTransport = goodTransportService.getOrderByNumber(goodOrderNumber);
				if(goodTransport != null){
					Date requireArriveTime = goodOrderService.getRequireArrivetime(goodTransport.getGoodOrderNumber());
					goodTransport.setRequireArrivetime(DateUnti.dateToStr(requireArriveTime,12));//应到达时间
					goodTransport.setGoodTransportPrice(goodTransport.getGoodTransportprice()+"");//货运价格
					int driverId = goodTransport.getGoodTransportDriverId();//司机id
					if(driverId != 0){//获取司机信息
						Driver driver = driverService.getDriverById(driverId);
						goodTransport.setDriverName(driver.getDriverName());
						goodTransport.setDriverPhone(driver.getDriverPhone());
					}
					list.add(goodTransport);
					jo.put("total",1);
					jo.put("rows",list);
				}else{
					jo.put("total",0);
					jo.put("rows",list);
				}
			}
			return jo;
		}
		
		/**
		 * Description:实时获取运输信息
		 * @param goodOrderNumber
		 * @return
		 */
		@RequestMapping(value="getTransportMessage",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse getGoodTransportMessage(String goodOrderNumber){
			ApiResponse response = null;
			GoodTransportProcess gtMsg = goodTransportProcessService.getTransportProcessMessage(goodOrderNumber);
			if(gtMsg == null){
				response = new ApiResponse(0,"无数据");
			}else{
				gtMsg.setGoodTransportProcessTime(DateUnti.dateToStr(gtMsg.getGoodTransportProcesstime(), 12));
				response = new ApiResponse(1,gtMsg,"获取成功");
			}
			return response;
		}
		
		/**
		 * Description:获取物流节点信息
		 * @param goodOrderNumber
		 * @return ApiResponse
		 */
		@RequestMapping(value="getTransportNodeMsg",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse getTransportNodeMessage(String goodOrderNumber){
			ApiResponse response = null;
			List<GoodTransportNode> list = goodTransportNodeService.getTransportNode(goodOrderNumber);
			if(CollectionUtils.isEmpty(list)){
				response = new ApiResponse(0,"无数据");
			}else{
				response = new ApiResponse(1,list,"获取成功");
			}
			return response;
		}
		
		
		/**
		 * 
		 * 
		 * @param page
		 * @param rows
		 * @return
		 * Description:获取货物订单列表
		 * @author:HAO
		 */
		@RequestMapping(value="getOrderList",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> getGoodOrderList(int page,int rows,String goodOrderNumber){
			Map<String,Object> jo = new HashMap();
			Map<String,Object> map = new HashMap<>();
			//分页查询
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			if(goodOrderNumber == null || goodOrderNumber.isEmpty()){//分页获取订单列表
				map.put("start", (page-1)*10);//页数
				map.put("pagesize",rows);//每页行数
				List<GoodOrder> goodOrderList = goodOrderService.getOrderList(map);
				if(!CollectionUtils.isEmpty(goodOrderList)){
					for(int i=0;i<goodOrderList.size();i++){
						GoodOrder order = goodOrderList.get(i);
						order.setDeliverytime(DateUnti.dateToStr(order.getDeliveryTime(),12));//发送时间
						order.setRequireArrivetime(DateUnti.dateToStr(order.getRequireArriveTime(),12));//应到时间
					}
				}
				int count = goodOrderService.getOrderCount();//订单数量
				jo.put("total",count);
				jo.put("rows",goodOrderList);
			}else{//不为null说明是按订单号搜索
				GoodOrder goodorder = goodOrderService.getOrderByNumber(goodOrderNumber);
				if(goodorder != null){
					goodorder.setDeliverytime(DateUnti.dateToStr(goodorder.getDeliveryTime(),12));//发送时间
					goodorder.setRequireArrivetime(DateUnti.dateToStr(goodorder.getRequireArriveTime(),12));//应到达时间
					List<GoodOrder> goodOrderList = new ArrayList<>();
					goodOrderList.add(goodorder);
					jo.put("total",1);
					jo.put("rows",goodOrderList);
				}else{
					jo.put("total",0);
					jo.put("rows",new ArrayList<>());
				}
			}
			return jo;
		}
		
		/**
		 * @param driverId
		 * @param goodOrderNumber
		 * @return
		 * Description:给司机分配发送任务
		 * @author:HAO
		 */
		@RequestMapping(value="addTaskToDriver",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse addTaskToDriver(int driverId,String goodOrderNumber){
			ApiResponse apiResponse = null;
			if(driverId!=0 && goodOrderNumber!=null){
				Integer drId = goodTransportService.getDriverIdByOrder(goodOrderNumber);//获取订单对应司机id
				if(drId != 0 && drId != null){
					apiResponse = new ApiResponse(0,"该订单已有司机负责配送");
					return apiResponse;
				}
				GoodTransport goodTransport = new GoodTransport();
				GoodTransportProcess gtp = new GoodTransportProcess();
				gtp.setGoodTransportDriverId(driverId);
				gtp.setGoodOrderNumber(goodOrderNumber);
				goodTransport.setGoodOrderNumber(goodOrderNumber);
				goodTransport.setGoodTransportDriverId(driverId);
				try{//更新运输信息和运输过程信息中的司机id
					if(goodTransportService.updateTransportMessage(goodTransport)==1
					&& goodTransportProcessService.updateTransportProcessMessage(gtp)==1){
						apiResponse = new ApiResponse(1,"操作成功");
					}else{
						apiResponse = new ApiResponse(0,"操作失败");
					}
				}catch(Exception e){
					logger.error("给司机分配发送任务时出现异常", e);
					apiResponse = new ApiResponse(-1,"内部错误");
				}
			}
			return apiResponse;
		}
		
		/**
		 * @param page
		 * @param rows
		 * @return
		 * Description:获取货物运输过程信息列表
		 * @author:HAO
		 */
		@RequestMapping(value="getTransProcessList",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> getTransProcessList(int page,int rows,String goodOrderNumber){
			Map<String,Object> jo = new HashMap();
			Map<String,Object> map = new HashMap<>();
			//订单号为空则为分页查询
			if(goodOrderNumber == null || goodOrderNumber.isEmpty()){
				if(page == 0){
					page = 1;
				}
				if(rows == 0){
					rows = 10;
				}
				map.put("start", (page-1)*10);
				map.put("pagesize",rows);
				List<GoodTransportProcess> goodTransProcessList = goodTransportProcessService.getTransportProcessList(map);//分页获取
				if(!CollectionUtils.isEmpty(goodTransProcessList)){
					for(int i=0;i<goodTransProcessList.size();i++){
						GoodTransportProcess goodTransportProcess = goodTransProcessList.get(i);
						goodTransportProcess.setGoodTransportProcessTime(DateUnti.dateToStr(goodTransportProcess.getGoodTransportProcesstime(),12));//设置运输时间（把date类型转换为string，方便前端展示）
						Driver driver = driverService.getDriverById(goodTransportProcess.getGoodTransportDriverId());
						goodTransportProcess.setDriverName(driver.getDriverName());//设置运输司机名字及手机号
						goodTransportProcess.setDriverPhone(driver.getDriverPhone());
					}
				}
				int count = goodTransportProcessService.getTransProcessListCount();
				jo.put("total",count);
				jo.put("rows",goodTransProcessList);
			}else{//订单号不为空则按号搜索
				List<GoodTransportProcess> goodTransProcessList = new ArrayList<>();
				GoodTransportProcess goodTransportProcess = goodTransportProcessService.getProcessByOrder(goodOrderNumber);//按订单号获取订单
				if(goodTransportProcess != null){
					goodTransportProcess.setGoodTransportProcessTime(DateUnti.dateToStr(goodTransportProcess.getGoodTransportProcesstime(),12));
					Driver driver = driverService.getDriverById(goodTransportProcess.getGoodTransportDriverId());//获取运输司机
					goodTransportProcess.setDriverName(driver.getDriverName());
					goodTransportProcess.setDriverPhone(driver.getDriverPhone());
					goodTransProcessList.add(goodTransportProcess);
					jo.put("total",1);
					jo.put("rows",goodTransProcessList);
				}else{
					jo.put("total",0);
					jo.put("rows",goodTransProcessList);
				}
			}
			
			return jo;
		} 
		
		/**
		 * @param goodOrderNumber
		 * @return
		 * Description:根据订单号获取货物细节信息
		 * @author:HAO
		 */
		@RequestMapping(value="getGoodDetail",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse getGoodDetail(String goodOrderNumber){
			ApiResponse apiResponse = null;
			if(goodOrderNumber!=null){
				GoodDetail goodDetail = goodDetailService.getGoodDetailByOrder(goodOrderNumber);
				if(goodDetail!=null){
					apiResponse = new ApiResponse(1,goodDetail,"获取成功");
				}else{
					apiResponse = new ApiResponse(0,"获取失败");
				}
			}else{
				apiResponse = new ApiResponse(0,"请输入完整数据");
			}
			return apiResponse;
		}
		
		
		/**
		 * @return
		 * Description:更新货物地址信息
		 * @author:HAO
		 */
		@RequestMapping(value="upProcess",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse upProcessMessage(String goodTransportProcessPosition,int driverId){
			ApiResponse apiResponse = null;
			try{
				List<GoodTransportProcess> list = goodTransportProcessService.getTransProcessByDriverId(driverId);//获取与该司机关联的所有运输信息
				if(!CollectionUtils.isEmpty(list)){
					for(int i=0;i<list.size();i++){
						GoodTransportProcess gtp = list.get(i);
						//设置地址和更新时间
						gtp.setGoodTransportProcessPosition(goodTransportProcessPosition);
						gtp.setGoodTransportProcesstime(new Date());
						goodTransportProcessService.updateTransportProcessMessage(gtp);
					}
					apiResponse = new ApiResponse(1,"发送地址成功");
				}else{
					apiResponse = new ApiResponse(0,"发送地址失败");
				}
				
			}catch(Exception e){
				logger.error("更新地址时出现异常", e);
				apiResponse = new ApiResponse(-1,"未知错误");
			}
			return apiResponse;
		}
		
		/**
		 * @param goodOrderNumber,type
		 * @return
		 * Description:获取货物图片路径
		 * @author:HAO
		 */
		@RequestMapping(value="getImg",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse getImgByOrder(String goodOrderNumber,int type,HttpServletRequest request){
			ApiResponse apiResponse = null;
			GoodDetail goodDetail = goodDetailService.getImgByOrder(goodOrderNumber);
			String img = "";
			if(goodDetail != null){
				//1表示货物原始图片，2表示包装图片
				if(type == 1){
					img = goodDetail.getGoodDetailPrimitivePhoto();
				}else if(type == 2){
					img = goodDetail.getGoodDetailPackPhoto();
				}
				if(img != null && img != "" && img.length() != 0){
					String[] imgUrl = getImgUrl(img,request);
					apiResponse = new ApiResponse(1,imgUrl,"获取成功");
				}else{
					apiResponse = new ApiResponse(0,"无图片");
				}
			}else{
				apiResponse = new ApiResponse(0,"无图片");
				return apiResponse;
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
		
		/**
		 * Description:新增交付信息
		 * @param goodPay
		 */
		@RequestMapping(value="addGoodPay",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse addGoodPay(GoodPay goodPay){
			ApiResponse apiResponse = null;
			if(goodPay != null){
				int result = goodPayService.addGoodPay(goodPay);
				if(result==1){
					apiResponse = new ApiResponse(1,"操作成功");
				}else{
					apiResponse = new ApiResponse(0,"操作失败");
				}
			}else{
				apiResponse = new ApiResponse(0,"请填写完整数据!");
			}
			return apiResponse;
		}
		
		
		/**
		 * Description:删除交付信息
		 */
		@Transactional
		@RequestMapping(value="deleteGoodPay",method=RequestMethod.POST)
		@ResponseBody
		@RequiresRoles(value={"2","3"},logical=Logical.OR)
		public ApiResponse deleteGoodPay(String goodOrderNumber){
			ApiResponse apiResponse = null;
			try{
				int result = goodPayService.deleteGoodPay(goodOrderNumber);
				goodOrderService.deleteOrder(goodOrderNumber);//删除与该订单有关的所有信息
				goodDetailService.deleteDetail(goodOrderNumber);//货物细节信息
				goodTransportService.deleteTransportMessage(goodOrderNumber);//货物运输信息
				goodTransportProcessService.deleteProcessMessage(goodOrderNumber);//货物运输过程信息
				if(result==1){
					apiResponse = new ApiResponse(1,"操作成功");
				}else{
					apiResponse = new ApiResponse(0,"操作失败");
				}
			}catch(AuthorizationException e){
				apiResponse = new ApiResponse(-1,"无权限！");
			}catch(Exception e){
				apiResponse = new ApiResponse(-1,"未知错误！");
			}
			
			return apiResponse;
		}
		
		
		/**
		 * Description:更新交付信息
		 * @param goodPay
		 */
		@RequestMapping(value="updateGoodPay",method=RequestMethod.POST)
		@ResponseBody
		public ApiResponse updateGoodPay(GoodPay goodPay){
			ApiResponse apiResponse = null;
			if(goodPay != null){
				int result = goodPayService.updateGoodPay(goodPay);
				if(result==1){
					apiResponse = new ApiResponse(1,"操作成功");
				}else{
					apiResponse = new ApiResponse(0,"操作失败");
				}
			}else{
				apiResponse = new ApiResponse(0,"请填写完整数据!");
			}
			return apiResponse;
		}
		
		/**
		 * Description:后台获取交付信息
		 * @param page
		 * @param rows
		 * @param goodOrderNumber
		 */
		@RequestMapping(value="getPayList",method=RequestMethod.POST)
		@ResponseBody
		public Map<String,Object> updateGoodPay(int page,int rows,String goodOrderNumber){
			Map<String,Object> jo = new HashMap();
			Map<String,Object> map = new HashMap<>();
			if(page == 0){
				page = 1;
			}
			if(rows == 0){
				rows = 10;
			}
			map.put("start", (page-1)*10);//分页查询
			map.put("pagesize",rows);
			if(goodOrderNumber != null){//若订单号不为空则按号搜索
				map.put("goodOrderNumber", goodOrderNumber);
			}
			List<GoodPay> goodPayList = goodPayService.getGoodPayList(map);
			int count = 0;
			if(goodOrderNumber != null){
				count = 1;
			}else{
				count = goodPayService.getCount();
			}
			jo.put("total",count);
			jo.put("rows",goodPayList);
			return jo;
		}
	
}
