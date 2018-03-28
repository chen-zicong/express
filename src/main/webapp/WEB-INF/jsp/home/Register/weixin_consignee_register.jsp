<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    <base href="<%=basePath%>">
        
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="微信端进行个人注册">
	<meta charset="UTF-8">
	<title>司机注册</title>
	<meta name="viewport" content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0"/>
	<meta http-equiv="X-UA-Compatible" content="IE=edge">
	<meta name="format-detection" content="telephone=no">
	<link rel="stylesheet" type="text/css" href="https://www.xwh511.cc/express/css/main/reset.css">
	<link rel="stylesheet" href="https://www.xwh511.cc/express/css/main/weui.min.css">
	<link rel="stylesheet" href="https://www.xwh511.cc/express/css/main/jquery-weui.css">
	<link rel="stylesheet" href="https://www.xwh511.cc/express/logIconFont/iconfont.css">
	<style type="text/css">
		.tabbarContent{
			font-size: 15px;
			margin: 6px 0;
		}
		header{
			height:60px;
			width: 100%;
			background-color: #2ECC71;
			color: white;
			font-size: 25px;
			padding-left: 10px;
			box-sizing: border-box;
			font-weight: normal;
			letter-spacing: 2px;
			line-height: 60px;
			position: fixed;
			top: 0;
			left: 0;
			z-index: 9;
		}
		.headerIcon{
			font-size: 30px;
			line-height: 60px;
			position: relative;
			top: -2px;
		}
		.formDiv{
			position: absolute;
			top:58px;
		}
		.uploadDiv{
			width: 100%;
			height: 240px;
			position: absolute;
			top:0px;
		}
		.ulInput{
			padding-top: 10px;
			padding-bottom: 10px;
			width: 80%;
			padding-right: 8%;
			height: 200px;
			position: absolute;
			top: 35px;
			left: 0;
			right: 0;
			margin: auto;
			opacity: 0; 
			z-index: 99;
		}
		.ulImg{
			padding-top: 10px;
			padding-bottom: 10px;
			width: 80%;
			padding-right: 8%;
			height: 200px;
			position: absolute;
			top: 35px;
			left: 0;
			right: 0;
			margin: auto;
		}
		.sumbit{
			position: absolute;
			width:150px;
			height: 40px;
			font-size: 18px;
			left: 0;
			right: 0;
			margin: 5px auto;
			line-height: 40px;
		}
	</style>
  </head>
  
 <body style="height:100%;overflow-y: scroll;">
	 <header style="position: fixed;top: 0;">
	 	<h2><b><i class="icon iconfont icon-xiazai4 headerIcon"></i>司机注册</b></h2>
	 </header>

	 <div class="formDiv" >
	 <form enctype="multipart/form-data">
	<div class="weui-cells__title" style="font-size: 20px"><b>请输入司机信息</b></div>
    <div class="weui-cells weui-cells_form">
    	<div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">姓名</label></div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" type="text" placeholder="请输入姓名" name='' id="driverName">
	        </div>
      	</div>
	    <div class="weui-cell weui-cell_vcode">
	        <div class="weui-cell__hd">
	          <label class="weui-label">联系方式</label>
	        </div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" type="tel" placeholder="请输入手机号" name='' id="driverPhone">
	        </div>
	        
	    </div>
	   
     	<div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">身份证号码</label></div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" type="number" placeholder="请输入身份证号码" name='' id="driverIdCard">
	        </div>
     	</div>
      	<div class="weui-cell" style="height: 250px;">
      		<div class="weui-cell__hd" style="width: 100%;position: absolute;top:10px;"><label class="weui-label">身份证照片</label></div>
        	<div class="weui-cell__bd uploadDiv">
          		<input type="file" multiple class="ulInput" id="driverIdpic">
          		<img src="https://www.xwh511.cc/express/images/zhanwei.png" class="ulImg"> 
          	</div>
        </div>
        <div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">车牌号</label></div>
	        <div class="weui-cell__bd">
	          <input class="weui-input" type="text" placeholder="请输入车牌号" name='plateNumber' id="plateNumber">
	        </div>
     	</div>
     	<div class="weui-cell">
	        <div class="weui-cell__hd"><label class="weui-label">运输车类型</label></div>
	        <div class="weui-cell__bd">
	          <!--<input class="weui-input" type="text" placeholder="请输入运输车类型" name='carType' id="carType">-->
	          <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary sumbit" id="requireSumbit" onclick="requireies.submit()">点击提交</a>
	        </div>
     	</div>
      </div>
      <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary sumbit" id="requireSumbit" onclick="requireies.submit()">点击提交</a>
      </form>
    </div>
    </div>

	
	<footer style="position: fixed;bottom: 0;width: 100%;z-index: 9999;">
     <div class="weui-tabbar">
        <a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
          	<p class="weui-tabbar__label tabbarContent"><i class="icon iconfont icon-xiazai4" style="font-size: 22px;"></i>&nbsp;个人注册</p>
        </a>
        <a href="#tab4" class="weui-tabbar__item">
          <p class="weui-tabbar__label tabbarContent" > <i class="icon iconfont icon-jijian-copy" style="font-size: 22px;"></i>&nbsp;公司注册</p>
        </a>
     </div>
    </footer>



	<script src="https://www.xwh511.cc/express/js/main/jquery-2.1.4.js"></script>
	<script src="https://www.xwh511.cc/express/js/fastclick.js"></script>
	<script src="https://www.xwh511.cc/express/js/jquery-weui.js"></script>
	<script src="https://www.xwh511.cc/express/js/validations/validateDialog.js"></script>
	<script type="text/javascript">
		
		$(function() {
		    FastClick.attach(document.body);
		 });
		var requireies={
			submit:function(){
				if($("#driverPhone").val()==''
				|| $("#driverName").val()==''
				|| $("#driverIdCard").val()==''
				|| $("#driverIdpic").val()==''
				|| $("#plateNumber").val()==''
				|| $("#carType").val()==''){
					validateDialog.headDialog("请输入完整信息");
				}
			var driverName = $("#driverName").val();
			var driverIdCard = $("#driverIdCard").val();
			var driverIdpic = $("#driverIdpic").val();
			var driverPhone = $("#driverPhone").val();
			var plateNumber = $("#plateNumber").val();
			var carType = $("#carType").val();
			$.ajax({
				   type: "POST",
				   url: "/express/driver/register",
				   processData : false, // 告诉jQuery不要去处理发送的数据
				   data: {
					   driverName:driverName,
					   driverIdCard:driverIdCard,
					   driverPhone:driverPhone,
					   driverIdpic:driverIdpic,
					   plateNumber:plateNumber,
					   carType:carType
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						window.location.reload();
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
			}
		}
		$(".ulInput").each(function(){
			$(this).change(function() {
				var fr = new FileReader();
				newfile = this.files[0];
				mythis = $(this);
				fr.readAsDataURL(newfile);
				fr.onload = function(e) {
					mythis.next().attr("src", this.result);
				}
			})
	})
	</script>
</body>
</html>
