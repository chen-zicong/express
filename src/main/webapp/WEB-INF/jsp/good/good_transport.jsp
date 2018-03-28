<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
//String orgId = session.getAttribute("loginTreeId").toString();
//Object orgId = session.getAttribute("loginTreeId");

%>

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
		<a class="easyui-linkbutton" onclick="admin.deleteWin()" iconCls="icon-table-row-delete" plain='true'>删除</a>	
	</div>
	
    <script type="text/javascript">
		var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1500,
			url:"${proPath }" + '/express/good/getOrderList',
			title:'货物订单列表',
			columns:[[
				{field:'goodOrderNumber',title:'订单号',width:35},
				{field:'consigneeName',title:'收件人姓名',width:35},
				{field:'consigneePhone',title:'收件人手机',width:50},
				{field:'consigneeAddress',title:'收货地址',width:70},
				{field:'deliveryPlace',title:'发货地点',width:70},
				{field:'receivePlace',title:'收货地点',width:70},
				{field:'deliverytime',title:'发货时间',width:70},
				{field:'requireArrivetime',title:'要求到达时间',width:70},
				{field:'deliveryWay',title:'发货方式',width:35},
				{field:'goodTransportCarType',title:'运输车类型',width:35},
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	
				}, 
		});
	});
	
	var admin = {

		//打开删除窗口
		deleteWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行删除');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行删除');
				return;
			}
			$("#deletewin").window('open');
		},
		//删除会议
		deleteAdmin:function(){
			var id  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-adminId")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/admin/deleteAdmin",
				   data: {adminId:id},
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
  	</script>
</body>
</html>