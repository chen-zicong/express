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
		<a class="easyui-linkbutton" onclick="connect.detailWin()" iconCls="icon-table-row-delete" plain='true'>查看货物详情</a>
		<a class="easyui-linkbutton" onclick="connect.updateWin()" iconCls="icon-table-row-delete" plain='true'>确定运输方式及价格</a>	
		<a class="easyui-linkbutton" onclick="connect.submitWin()" iconCls="icon-table-row-delete" plain='true'>提交审核</a>
		<a class="easyui-linkbutton" onclick="connect.getUnauditeList()" iconCls="icon-table-row-delete" plain='true'>查看未提交审核订单</a>
		<a class="easyui-linkbutton" onclick="connect.getdisauditeList()" iconCls="icon-table-row-delete" plain='true'>查看未通过审核订单</a>
		<a class="easyui-linkbutton" onclick="connect.getImg(1)" iconCls="icon-search" plain='true'>查看货物原始图片</a>
		<a class="easyui-linkbutton" onclick="connect.getImg(2)" iconCls="icon-search" plain='true'>查看货物包装图片</a>
		订单号 ：&nbsp<input id="goodOrderNumber" type="text" style="width:150px"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>	
	</div>
	<div id="updateWin" class="easyui-window" title="确定运输方式及价格" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:450px;height:360px;padding-top:20px;" closed="true">

		 <table align="center" width="400" border="0" cellspacing="20" >
		 	<tr>
		 		<th align="right" style="margin-right:20px"><span style="font-size:16px">货运价格:</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="goodTransportPrice" id="goodTransportPrice" /></td>
		 	</tr>
		 	<tr>
		 		<th align="right"><span style="font-size:16px">运输方式(专运，零担，直达):</span></th>
		 		<td><input  style="height:30px;width:200px;font-size:20px" type="text" name="goodTransportDispatchWay" id="goodTransportDispatchWay" /></td>
		 	</tr>
		 </table>
		 <p style="margin-left:40px;margin-top:25px;font-size:16px">确定运输单位和司机请在“查看承运人”页面操作</p>
		<div style="margin-top:50px;margin-bottom:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:100px;margin-left:100px" onclick="connect.update()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#updateWin').window('close')">取消</a>
		</div> 
	</div>  
	
	<div id="submitWin" class="easyui-window" title="提交审核" modal="true" data-options="iconCls:'icon-save'" style="top:20px;width:440px;height:250px;padding-top:20px;" closed="true">
		<div style="margin-top:50px">
			<a class="easyui-linkbutton" data-options="iconCls:'icon-ok'" style="margin-right:80px;margin-left:100px" onclick="connect.submit()">确定</a>
			<a class="easyui-linkbutton" data-options="iconCls:'icon-cancel'" onclick="$('#submitWin').window('close')">取消</a>
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
		//var TreeId='<%=session.getAttribute("loginTreeId")%>';
		$(function(){
		$("#meeting").datagrid({
			width:1600,
			url:"${proPath }" + '/express/good/getConnectMessage',
			title:'业务员接件信息列表',
			columns:[[
				{field:'goodOrderNumber',title:'订单号',width:75},
				{field:'goodTransportConsigneeName',title:'收件人姓名',width:50},
				{field:'goodTransportConsigneePhone',title:'收件人手机',width:50},
				{field:'goodTransportConnectorName',title:'业务员姓名',width:50},
				{field:'goodTransportConnectorPhone',title:'业务员手机',width:50},
				{field:'requireArrivetime',title:'要求到达时间',width:70},
				{field:'driverName',title:'运输司机姓名',width:70},
				{field:'driverPhone',title:'运输司机手机',width:70},
				{field:'goodTransportPrice',title:'货运价格',width:70},
				{field:'goodTransportDispatchWay',title:'运输方式',width:70},
				{field:'unAuditeReason',title:'未通过审核原因',width:70}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	auditeStatus:0
				}, 
		});
	});
	
	var connect = {
	
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

		//更新货运价格，运输方式窗口
		updateWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			var goodTransportPrice  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportPrice")).text();
			var goodTransportDispatchWay  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportDispatchWay")).text();
			$("#goodTransportDispatchWay").val(goodTransportDispatchWay);
			$("#goodTransportPrice").val(goodTransportPrice);
			$("#updateWin").window('open');
		},
		
		//提交审核窗口
		submitWin:function(){
			if($(".datagrid-row-selected").size()==0){
				$.messager.alert('警告','请选择一项进行操作');
				return;
			}else if($(".datagrid-row-selected").size()>1){
				$.messager.alert('警告','请只选择一项进行操作');
				return;
			}
			var driverName  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-driverName")).text();
			var goodTransportPrice  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportPrice")).text();
			var goodTransportDispatchWay  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportDispatchWay")).text();
			if(driverName == null || driverName == ""){
				$.messager.alert('警告','该订单无运输司机，请先前往查看承运人页面确定运输司机!');
				setTimeout(function () {window.location.reload();}, 5000);
			}else if(goodTransportPrice == "0.0" || goodTransportDispatchWay == "" || goodTransportDispatchWay == null){
				$.messager.alert('警告','请先确定货运价格和发货方式!');
				return;
			}else{
				$("#submitWin").window('open');
				return;
			}
			
		},
		
		//更新运输及价格
		update:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			var goodTransportPrice = $("#goodTransportPrice").val();
			var goodTransportDispatchWay = $("#goodTransportDispatchWay").val();
			if($("#goodTransportPrice").val()==''
				|| $("#goodTransportDispatchWay").val()==''){
					$.messager.alert('警告','请输入完整数据');
				return;
				}
			$.ajax({
				   type: "POST",
				   url: "/express/good/upTransportMessage",
				   data: {
				   	goodOrderNumber:goodOrderNumber,
				   	goodTransportPrice:goodTransportPrice,
				   	goodTransportDispatchWay:goodTransportDispatchWay
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
		
		//提交审核
		submit:function(){
			var goodOrderNumber  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodOrderNumber")).text();
			var goodTransportPrice  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportPrice")).text();
			var goodTransportDispatchWay  = $(".datagrid-row-selected").find($(".datagrid-cell-c1-goodTransportDispatchWay")).text();
			var auditeStatus = 1;
			$.ajax({
				   type: "POST",
				   url: "/express/good/upTransportMessage",
				   data: {
				   	goodOrderNumber:goodOrderNumber,
				   	auditeStatus:auditeStatus,
				   	goodTransportPrice:goodTransportPrice,
				   	goodTransportDispatchWay:goodTransportDispatchWay
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
		
		//查看未通过审核订单
		getUnauditeList:function(){
			$('#meeting').datagrid('load',{
			auditeStatus:0
			});
		},
		
		//查看未提交审核订单
		getdisauditeList:function(){
			$('#meeting').datagrid('load',{
			auditeStatus:3
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
		var goodOrderNumber  = $("#goodOrderNumber").val();
		$('#meeting').datagrid('load',{
			goodOrderNumber:goodOrderNumber,
			auditeStatus:0
		});
	})
  	</script>
</body>
</html>