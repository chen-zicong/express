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
		<a class="easyui-linkbutton" onclick="process.detailWin()" iconCls="icon-table-row-delete" plain='true'>查看货物详情</a>
		<a class="easyui-linkbutton" onclick="process.getImg(1)" iconCls="icon-search" plain='true'>查看货物原始图片</a>
		<a class="easyui-linkbutton" onclick="process.getImg(2)" iconCls="icon-search" plain='true'>查看货物包装图片</a>
		订单号 ：&nbsp<input id="goodOrderNumber" type="text" style="width:150px"/>
		<a id="search" style="margin-left: 20px;" href="#" class="easyui-linkbutton" data-options="iconCls:'icon-search'">查询</a>	
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
			url:"${proPath }" + '/express/good/getTransProcessList',
			title:'货物运输过程信息',
			columns:[[
				{field:'goodOrderNumber',title:'订单号',width:35},
				{field:'goodTransportProcessTime',title:'当前运输时间',width:35},
				{field:'goodTransportProcessPosition',title:'当前到达位置',width:50},
				{field:'driverName',title:'运输司机姓名',width:35},
				{field:'driverPhone',title:'运输司机手机',width:35}
			]],
			pagination:true,
			fitColumns:true,
			toolbar:"#meetingTb",
			queryParams: {
			    	auditeStatus:1
				}, 
		});
	});
	
	var process = {
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
		},
		
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
			
		}
	}
	
	//查询按钮的点击触发事件
	$("#search").click(function(){
		var goodOrderNumber  = $("#goodOrderNumber").val();
		$('#meeting').datagrid('load',{
			goodOrderNumber:goodOrderNumber,
			auditeStatus:1
		});
	})
  	</script>
</body>
</html>