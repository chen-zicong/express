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
		<a class="easyui-linkbutton" onclick="good.detailWin()" iconCls="icon-table-row-delete" plain='true'>查看货物详情</a>
		<shiro:hasAnyRoles name="2,3">
		<a class="easyui-linkbutton" onclick="good.addConnectorWin()" iconCls="icon-table-row-delete" plain='true'>派出业务员</a>
		<a class="easyui-linkbutton" onclick="good.deleteOrder()" iconCls="icon-table-row-delete" plain='true'>删除订单</a>				
		</shiro:hasAnyRoles>
		<a class="easyui-linkbutton" onclick="good.getImg(1)" iconCls="icon-search" plain='true'>查看货物原始图片</a>
		<a class="easyui-linkbutton" onclick="good.getImg(2)" iconCls="icon-search" plain='true'>查看货物包装图片</a>
		订单号 ：&nbsp<input id="goodOrderNumber" type="text" style="width:150px"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>
	</div>
	<div id="addConnectorWin" class="easyui-window" title="派出业务人员" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:420px;height:320px;padding-top:20px;" closed="true">

		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">业务人姓名:</span></th>
		 		<td><input class="easyui-textbox" style="height:30px;width:200px;font-size:20px" type="text" name="goodTransportConnectorName" id="goodTransportConnectorName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">业务人手机:</span></th>
		 		<td><input class="easyui-textbox" style="height:30px;width:200px;font-size:20px" type="text" name="goodTransportConnectorPhone" id="goodTransportConnectorPhone" /></td>
		 	</tr>
		 	
		 </table>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="good.addConnector()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#addDriverWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="detailWin" class="easyui-window" title="查看货物详情" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:420px;height:320px;padding-top:20px;" closed="true">
		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货物名称:</span></th>
		 		<td><input style="height:30px;width:200px;font-size:20px" type="text" name="goodDetailName" id="goodDetailName" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">尺寸:</span></th>
		 		<td><input style="height:30px;width:200px;font-size:20px" type="text" name="goodDetailSize" id="goodDetailSize" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">重量:</span></th>
		 		<td><input style="height:30px;width:200px;font-size:20px" type="text" name="goodDetailWeight" id="goodDetailWeight" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">数量:</span></th>
		 		<td><input style="height:30px;width:200px;font-size:20px" type="text" name="goodDetailAmount" id="goodDetailAmount" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">货物单价:</span></th>
		 		<td><input style="height:30px;width:200px;font-size:20px" type="text" name="goodDetailValue" id="goodDetailValue" /></td>
		 	</tr>
		 </table>
	</div>  
	<div id="imgWindow" class="easyui-window" title="货物图片" modal="true" data-options="iconCls:'icon-save'" style="top:0px;width:550px;height:500px;padding:5px;" closed="true">
		
	</div>
    <script type="text/javascript">
		var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1600,
			url:"${proPath }" + '/express/good/getOrderList',
			title:'货物订单列表',
			columns:[[
				{field:'goodOrderNumber',title:'订单号',width:65},
				{field:'consigneeName',title:'收件人姓名',width:35},
				{field:'consigneePhone',title:'收件人手机',width:50},
				{field:'consigneeAddress',title:'收货地址',width:70},
				{field:'deliveryPlace',title:'发货地点',width:70},
				{field:'receivePlace',title:'收货地点',width:70},
				{field:'deliverytime',title:'发货时间',width:70},
				{field:'requireArrivetime',title:'要求到达时间',width:70}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	goodOrderNumber:null
				}, 
		});
	});
	
	var good = {
		
		//查看货物详情窗口
		detailWin:function(){
			var goodDetail = "";
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/good/getGoodDetail",
				   data: {
					   goodOrderNumber:goodOrderNumber,
				   },
				   success: function(data){
				   		if(data["status"] == 1){
				   		goodDetail = data["data"];
				   		$("#goodDetailName").val(goodDetail["goodDetailName"]);
				   		$("#goodDetailSize").val(goodDetail["goodDetailSize"]);
				   		$("#goodDetailWeight").val(goodDetail["goodDetailWeight"]);
				   		$("#goodDetailAmount").val(goodDetail["goodDetailAmount"]);
				   		$("#goodDetailValue").val(goodDetail["goodDetailValue"]);
				   		$("#detailWin").window('open');
				   	}else{
						alert(data["message"]);
						window.location.reload();
				   	}
			   }
			});	
			
		},
		
		//派出业务人员窗口
		addConnectorWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			$("#addConnectorWin").window('open');
		},
		//派出业务人员
		addConnector:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			var goodTransportConnectorName = $("#goodTransportConnectorName").val();
			var goodTransportConnectorPhone = $("#goodTransportConnectorPhone").val();
			if($("#goodTransportConnectorName").val()==''
				|| $("#goodTransportConnectorPhone").val()==''){
					$.messager.alert('警告','请输入完整数据');
					return;
				}
			$.ajax({
				   type: "POST",
				   url: "/express/good/upTransportMessage",
				   data: {
					   goodOrderNumber:goodOrderNumber,
					   goodTransportConnectorName:goodTransportConnectorName,
					   goodTransportConnectorPhone:goodTransportConnectorPhone
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
		
		//删除订单
		deleteOrder:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/good/deleteOrder",
				   data: {
					   goodOrderNumber:goodOrderNumber
				   },
				   success: function(data){
				   		if(data["status"] == 1){
						window.location.reload();
				   	}else{
						alert(data["message"]);
				   	}
			   }
			});	
		},
		
		//查看图片
		getImg:function(type){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行查看');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行查看');
				return;
			}
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			$.ajax({
				   type: "POST",
				   url: "/express/good/getImg",
				   data: {
					   goodOrderNumber:goodOrderNumber,
					   type:type
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
		var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
		$('#meeting').datagrid('load',{
			goodOrderNumber:goodOrderNumber
		});
	})
  	</script>
</body>
</html>