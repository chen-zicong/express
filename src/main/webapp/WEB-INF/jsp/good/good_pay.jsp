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
		<a class="easyui-linkbutton" onclick="pay.addWin()" iconCls="icon-table-row-delete" plain='true'>录入交付记录</a>
		<a class="easyui-linkbutton" onclick="pay.updateWin()" iconCls="icon-table-row-delete" plain='true'>更新交付记录</a>	
		<a class="easyui-linkbutton" onclick="pay.deleteWin()" iconCls="icon-table-row-delete" plain='true'>删除记录</a>	
		订单号 ：&nbsp<input id="goodOrderNumber" type="text" style="width:150px"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	
	<div id="addWin" class="easyui-window" title="录入交付记录" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:450px;height:360px;padding-top:20px;" closed="true">
		<form  id="form1">
		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">订单号:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="goodOrderNumber" id="agoodOrderNumber" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收件人姓名:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneeName" id="aconsigneeName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收件人手机:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneePhone" id="aconsigneePhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货主姓名:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consignorName" id="aconsignorName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货主手机:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consignorPhone" id="aconsignorPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收货地址:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneeAddress" id="aconsigneeAddress" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">交付费用:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="payPrice" id="apayPrice" /></td>
		 	</tr>
		 </table>
		</form>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="pay.add()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#updateWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="deleteWin" class="easyui-window" title="删除记录" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:450px;height:360px;padding-top:20px;" closed="true">
		<p style="font-size: 16px">警告:删除记录将会删除有关该订单的所有信息</p>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="pay.deleteRecord()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#deleteWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="updateWin" class="easyui-window" title="更新交付费用" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:450px;height:360px;padding-top:20px;" closed="true">

		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收件人姓名:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneeName" id="consigneeName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收件人手机:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneePhone" id="consigneePhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货主姓名:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consignorName" id="consignorName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货主手机:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consignorPhone" id="consignorPhone" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">收货地址:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="consigneeAddress" id="consigneeAddress" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">交付费用:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="payPrice" id="payPrice" /></td>
		 	</tr>
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="pay.update()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#updateWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="imgWindow" class="easyui-window" title="货物图片" modal="true" data-options="iconCls:'icon-save'" style="top:0px;width:550px;height:500px;padding:5px;" closed="true">
		
	</div>
    <script type="text/javascript">
		//var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1600,
			url:"${proPath }" + '/express/good/getPayList',
			title:'货物运输过程信息',
			columns:[[
				{field:'goodOrderNumber',title:'订单号',width:35},
				{field:'consigneeName',title:'收件人',width:35},
				{field:'consigneePhone',title:'收件人手机',width:50},
				{field:'consignorName',title:'货主姓名',width:35},
				{field:'consignorPhone',title:'货主手机',width:35},
				{field:'consigneeAddress',title:'收货地址',width:35},
				{field:'payPrice',title:'交付费用',width:35}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
				}, 
		});
	});
	
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var goodOrderNumber  = $("#goodOrderNumber").val();
		$('#meeting').datagrid('load',{
			goodOrderNumber:goodOrderNumber
		});
	})
	
	var pay = {
		//打开更新窗口
		updateWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			var consigneeName  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-consigneeName")).text();
			var consigneePhone  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-consigneePhone")).text();
			var consignorName  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-consignorName")).text();
			var consignorPhone  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-consignorPhone")).text();
			var consigneeAddress  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-consigneeAddress")).text();
			var payPrice  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-payPrice")).text();
			$("#consigneeName").val(consigneeName);
			$("#consigneePhone").val(consigneePhone);
			$("#consignorName").val(consignorName);
			$("#consignorPhone").val(consignorPhone);
			$("#consigneeAddress").val(consigneeAddress);
			$("#payPrice").val(payPrice);
			$("#updateWin").window('open');
		},
		
		//删除窗口
		deleteWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			$("#deleteWin").window('open');
		},
		
		//删除记录
		deleteRecord:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/good/deleteGoodPay",
				   data: {
				   	goodOrderNumber:goodOrderNumber,
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
		
		//录入窗口
		addWin:function(){
			$("#addWin").window('open');
		},
		
		//录入交付费用
		add:function(){
			if ($("#agoodOrderNumber").val() == ''
						|| $("#aconsigneeName").val() == ''
						|| $("#aconsigneePhone").val() == ''
						|| $("#aconsignorName").val() == ''
						|| $("#aconsignorPhone").val() == ''
						|| $("#aconsigneeAddress").val() == ''
						) {
					$.messager.alert('警告','请填写完整数据');
				}
			var fd = new FormData($("#form1")[0]);
			$.ajax({
				   type: "POST",
				   url: "/express/good/addGoodPay",
				   data:fd,
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
		},
		
		//更新交付费用
		update:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			var payPrice = $("#payPrice").val();
			var consigneeName = $("#consigneeName").val();
			var consigneePhone = $("#consigneePhone").val();
			var consignorName = $("#consignorName").val();
			var consignorPhone = $("#consignorPhone").val();
			var consigneeAddress = $("#consigneeAddress").val();
			$.ajax({
				   type: "POST",
				   url: "/express/good/updateGoodPay",
				   data: {
				   	goodOrderNumber:goodOrderNumber,
				   	payPrice:payPrice,
				   	consigneeName:consigneeName,
				   	consigneePhone:consigneePhone,
				   	consignorName:consignorName,
				   	consignorPhone:consignorPhone,
				   	consigneeAddress:consigneeAddress
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

  	</script>
</body>
</html>