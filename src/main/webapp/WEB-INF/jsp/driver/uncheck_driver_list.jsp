<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String orgId = session.getAttribute("loginTreeId").toString();
//Object orgId = session.getAttribute("loginTreeId");

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
		<a class="easyui-linkbutton" onclick="driver.checkWin()" iconCls="icon-add1" plain='true'>通过审核</a>	
		<a class="easyui-linkbutton" onclick="driver.unCheckWin()" iconCls="icon-add1" plain='true'>拒绝审核</a>
		</shiro:hasAnyRoles>
		<a class="easyui-linkbutton" onclick="driver.getImg()" iconCls="icon-add1" plain='true'>查看身份证照片</a>
		<span style="margin-left:20px">司机名字 ：&nbsp<span><input id="sdriverName" type="text" style="width:70px;"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	<div id="checkWin" class="easyui-window" title="通过审核" modal="true" data-options="iconCls:'icon-save'" style="top:80px;width:360px;height:230px;padding-top:20px;" closed="true">
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:50px" onclick="driver.check(1)">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#checkWin').window('close')">取消</a>
		</div> 
	</div>  
	<div id="unCheckWin" class="easyui-window" title="拒绝审核" modal="true" data-options="iconCls:'icon-save'" style="top:80px;width:360px;height:230px;padding-top:20px;" closed="true">
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:50px" onclick="driver.check(0)">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#unCheckWin').window('close')">取消</a>
		</div> 
	</div> 
	<div id="imgWindow" class="easyui-window" title="身份证照片" modal="true" data-options="iconCls:'icon-save'" style="top:0px;width:550px;height:500px;padding:5px;" closed="true">
		
	</div>
    <script type="text/javascript">
		var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1200,
			url:"${proPath }" + '/express/driver/getDriver',
			title:'未审核列表',
			columns:[[
				{field:'id',title:'司机id',width:10},
				{field:'driverName',title:'司机名称',width:10},
				{field:'driverIdCard',title:'身份证号',width:20},
				{field:'driverPhone',title:'联系电话',width:15},
				{field:'plateNumber',title:'车牌号',width:20},
				{field:'carType',title:'运输车类型',width:20}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	status:'0'
				}, 
		});
	});
	
	var driver = {
		//通过审核窗口
		checkWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			$("#checkWin").window('open');
		},
		
		//拒绝审核窗口
		unCheckWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			$("#unCheckWin").window('open');
		},
		
		//审核司机
		check:function(status){
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/driver/check",
				   data: {
					   id:id,
					   status:status
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
		
		//查看身份证照片
		getImg:function(type){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-id")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/driver/getImg",
				   data: {
					   id:id
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						alert("操作成功");
						$("#imgWindow").empty();
						for(var i=0;i<data["data"].length;i++){
				   			var img = "<img style='width:530px;height:470px' src='"+data["data"][i]+"'>";
				   			$("#imgWindow").append(img);
				   		}
				   		$("#imgWindow").window("open");
						//window.location.reload();
				   	}else{
						alert(data["message"]);

				   	}
			   }
			});	
		}
	}
	
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var driverName  = $("#sdriverName").val();
		$('#meeting').datagrid('load',{
			status:'0',
			driverName:driverName
		});
	})
	
  	</script>
</body>
</html>