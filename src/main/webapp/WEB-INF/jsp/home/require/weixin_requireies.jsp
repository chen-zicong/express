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
	<meta http-equiv="description" content="微信端查询单号">
	<meta charset="UTF-8">
	<title>查询单件</title>
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
		.header{
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

		}
		.headerIcon{
			font-size: 30px;
			line-height: 60px;
			position: relative;
			top: -2px;
		}
		.requireDiv{
			width: 80%;
			margin: 200px 10%;
			height: 105px;
			position: absolute;
			box-sizing: border-box;
		}
		.requireInputDiv{
			width: 100%;
			height: 55px;
			position: absolute;
			border: 1px solid #2ECC71;
			box-sizing: border-box;
			border-radius: 5px;
		}
		.inputIcon{
			height: 100%;
			width: 18%;
			position: absolute;
			box-sizing: border-box;
		}
		.requireInput{
			height: 100%;
			width: 82%;
			border-left: 1px solid #2ECC71;
			border-right: 0;
			border-top: 0;
			border-bottom: 0;
			position: absolute;
			left: 18%;
			font-size: 22px;
			padding-left: 10px;
			box-sizing: border-box;
		}
		.requireSumbit{
			position: absolute;
			top:70px;
			width:150px;
			height: 45px;
			font-size: 18px;
			left: 0;
			right: 0;
			margin: 5px auto;
		}
	</style>
  </head>
  
  <body>
   	<header class="header">
	 	<h2><b><i class="icon iconfont icon-xiazai4 headerIcon"></i>单件查询</b></h2>
	</header>
	
	<div class="requireDiv">
	 <div class="requireInputDiv">
	 	<i class="inputIcon" ><i class="icon iconfont icon-chaxun" style="font-size: 40px;position: absolute;top:-5px;left:10px;color:#2ECC71; "></i></i>
	 	<input type="text" name="" class="requireInput" placeholder="输入查询单号">
	 </div>
	 <a href="javascript:;" class="weui-btn weui-btn_mini weui-btn_primary requireSumbit" id="requireSumbit" onclick="requireies.submit()">点击查询</a>
	 </div>
	
	<footer>
     <div class="weui-tabbar">
        <a href="#tab1" class="weui-tabbar__item weui-bar__item--on">
          	<p class="weui-tabbar__label tabbarContent"><i class="icon iconfont icon-xiazai4" style="font-size: 22px;"></i>&nbsp;单件查询</p>
        </a>
        <a href="#tab4" class="weui-tabbar__item">
         	<p class="weui-tabbar__label tabbarContent" ><i class="icon iconfont icon-jijian-copy" style="font-size: 22px;"></i>&nbsp;我要寄件</p>
        </a>
      </div>
    </footer>



	<script src="https://www.xwh511.cc/express/js/main/jquery-2.1.4.js"></script>
	<script src="https://www.xwh511.cc/express/js/main/fastclick.js"></script>
	<script src="https://www.xwh511.cc/express/js/main/jquery-weui.js"></script>
	<script src="https://www.xwh511.cc/express/js/validations/validateDialog.js"></script>
	<script type="text/javascript">
		
		$(function() {
		    FastClick.attach(document.body);
		 });
		
		var requireies={
			submit:function(){
				if($(".requireInput").val()==''){
					validateDialog.fadeDialog("请输入查询单号");
					return;
				}else{
					var goodOrderNumber = $(".requireInput").val();
					$.ajax({
						url:"/express/good/getTransportMessage?identify=wechat",
						data: {
						   goodOrderNumber:goodOrderNumber
				   		},
						dataType:"JSON",
						type:"POST",
						success:function(data){
						if(data["status"] == 1){
							$("#goodTransportProcessPosition").val(data["data"].goodTransportProcessPosition);
							$("#goodTransportProcessPosition").val(data["data"].goodTransportProcessTime);
							$("#resultWin").window('open');
							alert("success")
					   	}else{
							alert(data["message"]);
							window.location.reload();
					   	}
						}
					});
				}
			}
		}
	</script>
  </body>
</html>
