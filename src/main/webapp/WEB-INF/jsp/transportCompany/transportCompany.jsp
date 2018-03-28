<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<%@ taglib prefix="shiro" uri="http://shiro.apache.org/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<meta name="copyright" content="All Rights Reserved, Copyright (C) 2013, Wuyeguo, Ltd." />
	<title>马赛物流</title>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/icon.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui_animation.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/icon.css"/>
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/insdep/easyui_plus.css" />
	<link rel="stylesheet" type="text/css" href="${pageContext.request.contextPath}/css/wu.css" />
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/js/jquery.insdep-extend.min.js"></script>
	<script type="text/javascript" src="${pageContext.request.contextPath}/easyui/1.3.4/locale/easyui-lang-zh_CN.js"></script>
<style type="text/css">
	#deleteWin p{
		margin-top:20px;
		text-align:center;
	}
	#deleteWin a{
		margin-left:20px;
		margin-top:40px;
	}
</style>
</head>
<body class="easyui-layout">
	<div id="content" region="center"style="width:1095px;height:800px">
		<table id="meeting" singleSelect="true"></table>
	</div>
	<div id="meetingTb" style="padding-top:0px">
		<shiro:hasAnyRoles name="2,3">
		<a class="easyui-linkbutton" onclick="company.addCompanyWin()" iconCls="icon-add1" plain='true'>新增</a>	
		<a class="easyui-linkbutton" onclick="company.addDriverWin()" iconCls="icon-add1" plain='true'>录入司机</a>	
		</shiro:hasAnyRoles>
		<span style="margin-left:20px">公司名称 ：&nbsp<span><input id="stransportCompanyName" type="text" style="width:70px;"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	
	<div id="addWin" class="easyui-window" title="新增运输公司" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:600px;height:500px;padding-top:20px;" closed="true">

		 <table align="center" width="500" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">公司名称:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="transportCompanyName" id="transportCompanyName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">公司地址:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="transportCompanyAddress" id="transportCompanyAddress" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">公司联系人:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="transportCompanyContact" id="transportCompanyContact" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">公司联系人电话:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="transportCompanyContactPhone" id="transportCompanyContactPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">员工人数:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="transportCompanyCrewNumber" id="transportCompanyCrewNumber" /></td>
		 	</tr>
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="company.addCompany()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="addDriverWin" class="easyui-window" title="录入司机" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:600px;height:500px;padding-top:20px;" closed="true">
		<form enctype="multipart/form-data" id="form1">
		 <table align="center" width="500" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">司机名称:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="driverName" id="driverName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">身份证号:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="driverIdCard" id="driverIdCard" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">身份证照片:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="file" name="driverIdpic" id="driverIdpic" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">联系电话:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="driverPhone" id="driverPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">车牌号:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="plateNumber" id="plateNumber" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">运输车类型:</span></th>
		 		<td><input  style="height:30px;width:300px;font-size:20px" type="text" name="carType" id="carType" /></td>
		 	</tr>
		 </table>
		 <input type="hidden" name="comid" id="comid">
		</form>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="company.addDriver()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addDriverWin').window('close')">取消</a>
		</div> 
	</div>  
    <script type="text/javascript">
		var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1200,
			url:"${proPath }" + '/express/company/getCompanyList',
			title:'运输公司列表',
			columns:[[
				{field:'id',title:'公司id',width:15},
				{field:'transportCompanyName',title:'公司名称',width:50},
				{field:'transportCompanyAddress',title:'公司地址',width:65},
				{field:'transportCompanyContact',title:'公司联系人',width:20},
				{field:'transportCompanyContactPhone',title:'联系人电话',width:23},
				{field:'transportCompanyCrewNumber',title:'员工人数',width:20}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	
				}, 
		});
	});
	
	var company = {
		//新增公司窗口
		addCompanyWin:function(){
			$("#addWin").window('open');
		},
		
		//录入司机窗口
		addDriverWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一 个公司进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一个公司进行操作');
				return;
			}
			$("#addDriverWin").window('open');
		},
		
		//新增公司
		addCompany:function(){
			if($("#transportCompanyName").val()==''
				|| $("#transportCompanyAddress").val()==''
				|| $("#transportCompanyContact").val()==''
				|| $("#transportCompanyContactPhone").val()==''
				|| $("#transportCompanyCrewNumber").val()==''){
					$.messager.alert('警告','请输入完整数据');
				return;
				}
			//获取表单数据
			var transportCompanyName = $("#transportCompanyName").val();
			var transportCompanyAddress = $("#transportCompanyAddress").val();
			var transportCompanyContact = $("#transportCompanyContact").val();
			var transportCompanyContactPhone = $("#transportCompanyContactPhone").val();
			var transportCompanyCrewNumber = $("#transportCompanyCrewNumber").val();
			$.ajax({
				   type: "POST",
				   url: "/express/company/addCompany",
				   data: {
					   transportCompanyName:transportCompanyName,
					   transportCompanyAddress:transportCompanyAddress,
					   transportCompanyContact:transportCompanyContact,
					   transportCompanyContactPhone:transportCompanyContactPhone,
					   transportCompanyCrewNumber:transportCompanyCrewNumber
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
			
		},
		
		//录入司机
		addDriver:function(){
			var comId  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			alert(comId);
			$("#comid").val(comId);
			if($("#driverName").val()==''
				|| $("#driverIdCard").val()==''
				|| $("#driverPhone").val()==''
				|| $("#plateNumber").val()==''
				|| $("#carType").val()==''
				|| $("#driverIdPic").val()==''
				){
					$.messager.alert('警告','请输入完整数据');
					return;
				}
			//获取表单数据
			//var driverName = $("#driverName").val();
			//var driverIdCard = $("#driverIdCard").val();
			//var driverPhone = $("#driverPhone").val();
			//var plateNumber = $("#plateNumber").val();
			//var carType = $("#carType").val();
			var fd = new FormData($("#form1")[0]);
			$.ajax({
				   type: "POST",
				   url: "/express/driver/register",
				   data: fd,
				   processData : false, // 告诉jQuery不要去处理发送的数据
				   contentType : false, // 告诉jQuery不要去设置Content-Type请求头
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
	
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var stransportCompanyName  = $("#stransportCompanyName").val();
		$('#meeting').datagrid('load',{
			transportCompanyName:stransportCompanyName
		});
	})
	
  	</script>
</body>
</html>