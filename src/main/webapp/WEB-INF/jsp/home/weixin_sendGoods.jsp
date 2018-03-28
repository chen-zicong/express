<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
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
<title>我要寄件</title>
<meta name="viewport"
	content="width=device-width,initial-scale=1.0,maximum-scale=1.0,user-scalable=0" />
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="format-detection" content="telephone=no">
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/main/reset.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main/weui.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/css/main/jquery-weui.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/logIconFont/iconfont.css">
<link rel="stylesheet" type="text/css" 
	href="${pageContext.request.contextPath}/insdep/easyui.css" />
<script type="text/javascript" 
	src="${pageContext.request.contextPath}/js/jquery.easyui.min.js"></script>
<style type="text/css">
.tabbarContent {
	font-size: 15px;
	margin: 6px 0;
}

.header {
	height: 60px;
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
	z-index: 99;
}

.headerIcon {
	font-size: 30px;
	line-height: 60px;
	position: relative;
	top: -2px;
}

.sendForm {
	width: 80%;
	margin: 120px 10%;
	height: 45%;
	position: absolute;
	box-sizing: border-box;
}

.requireDiv {
	width: 80%;
	margin: 200px 10%;
	height: 105px;
	position: absolute;
	box-sizing: border-box;
}

.ulInputDiv {
	width: 100%;
	height: 55px;
	position: absolute;
	border: 1px solid #2ECC71;
	box-sizing: border-box;
	border-radius: 5px;
}

.inputIcon {
	height: 100%;
	width: 18%;
	position: absolute;
	box-sizing: border-box;
}

.ulInput {
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

.requireSumbit {
	position: absolute;
	top: 70px;
	width: 150px;
	height: 45px;
	font-size: 18px;
	left: 0;
	right: 0;
	margin: 5px auto;
}

.formDiv {
	position: absolute;
	top: 58px;
	width: 100%;
}

.uploadDiv {
	width: 100%;
	height: 240px;
	position: absolute;
	top: 0px;
}

.ulInput {
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

.ulImg {
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

.weui-cell__bd {
	padding-left: 5%;
}

.sumbit {
	position: absolute;
	width: 150px;
	height: 40px;
	font-size: 18px;
	left: 0;
	right: 0;
	margin: 5px auto;
	line-height: 40px;
}

footer {
	width: 100%;
	height: 60px;
	position: fixed;
	bottom: 0;
	z-index: 99;
}
</style>
</head>

<body style="height:100%;overflow-y: scroll;">
	<header class="header">
	<h2>
		<b><i class="icon iconfont icon-xiazai4 headerIcon"></i>我要寄件</b>
	</h2>
	</header>

	<div class="formDiv">
		<form enctype="multipart/form-data" id="form1">
			<div class="weui-cells__title" style="font-size: 20px">
				<b>请输入货物信息</b>
			</div>
			<div class="weui-cells weui-cells_form" style="margin-bottom: 60px;">
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">名称</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货物名称"
							name='goodDetailName' id="goodDetailName">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">货主姓名</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货主姓名"
							name='consignorName' id="consignorName">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">货主手机</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货主手机"
							name='consignorPhone' id="consignorPhone">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收件人姓名</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入收件人姓名"
							name='consigneeName' id="consigneeName">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收件人手机号</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="number" placeholder="请输入收件人手机号"
							name='consigneePhone' id="consigneePhone">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收件人地址</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入收件人地址"
							name='consigneeAddress' id="consigneeAddress">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">发货时间</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input font-input time-input ma_expect_date" type="text" placeholder="请输入发货时间"
							name='deliverytime' id="datetime-picker">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">尺寸</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货物尺寸"
							name='goodDetailSize' id="goodDetailSize">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">重量</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货物重量"
							name='goodDetailWeight' id="goodDetailWeight">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">数量</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入货物数量"
							name='goodDetailAmount' id="goodDetailAmount">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">货物单价</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="number" placeholder="请输入货物单价"
							name='goodDetailValue' id="goodDetailValue">
					</div>
				</div>
				<!-- <div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">发货时间</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="date" placeholder="请输入发货时间"
							name='' id="deliveryTime">
					</div>
				</div> -->
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">要求到达时间</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入要求到达时间"
							name='requireArrivetime' id="datetime-picker2">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">发货方式(专运;零担;直达)</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入发货方式"
							name='deliveryWay' id="deliveryWay">
					</div>
				</div>
				<div class="weui-cell ">
					<div class="weui-cell__hd">
						<label class="weui-label">运输车类型</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入运输车类型"
							name='goodTransportCarType' id="goodTransportCarType">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">发货地点</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入发货地点"
							name='deliveryPlace' id="deliveryPlace">
					</div>
				</div>
				<div class="weui-cell">
					<div class="weui-cell__hd">
						<label class="weui-label">收货地点</label>
					</div>
					<div class="weui-cell__bd">
						<input class="weui-input" type="text" placeholder="请输入收货地点"
							name='receivePlace' id="receivePlace">
					</div>
				</div>
				<div class="weui-cell" style="height: 240px;">
					<div class="weui-cell__hd"
						style="width: 100%;position: absolute;top:10px;">
						<label class="weui-label">原始货物照片</label>
					</div>
					<div class="weui-cell__bd uploadDiv">
						<input type="file" multiple class="ulInput" id="primitiveImgs"
							name="primitiveImgs"> <img
							src="${pageContext.request.contextPath}/images/zhanwei.png"
							class="ulImg">
					</div>
				</div>
				<div class="weui-cell"
					style="height: 240px;padding-bottom: 0;border-bottom: 0;">
					<div class="weui-cell__hd"
						style="width: 100%;position: absolute;top:10px;">
						<label class="weui-label">包装照片</label>
					</div>
					<div class="weui-cell__bd uploadDiv">
						<input type="file" multiple class="ulInput" id="packImgs"
							name="packImgs"> <img
							src="${pageContext.request.contextPath}/images/zhanwei.png"
							class="ulImg">
					</div>
				</div>
				<div class="weui-cell" style="height: 40px;">
					<a href="javascript:;"
						class="weui-btn weui-btn_mini weui-btn_primary sumbit"
						id="requireSumbit" onclick="send.submit()">点击提交</a>
				</div>

			</div>
		</form>
	</div>

	
	<script src="https://cdn.bootcss.com/jquery/1.11.0/jquery.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/jquery-weui.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/swiper.min.js"></script>
	<script src="https://cdn.bootcss.com/jquery-weui/1.2.0/js/city-picker.min.js"></script>
	<script src="${pageContext.request.contextPath}/js/main/jquery-2.1.4.js"></script>
	<script src="${pageContext.request.contextPath}/js/main/fastclick.js"></script>
	<script src="${pageContext.request.contextPath}/js/main/jquery-weui.js"></script>
	<script src="/express/js/main/ajaxfileupload.js"></script>
	<link rel="stylesheet" href="https://cdn.bootcss.com/weui/1.1.2/style/weui.min.css">
	<link rel="stylesheet" href="https://cdn.bootcss.com/jquery-weui/1.2.0/css/jquery-weui.min.css">
	<script
		src="${pageContext.request.contextPath}/js/validations/validateDialog.js"></script>
	<script type="text/javascript">
		$(function() {
			FastClick.attach(document.body);
			$("#datetime-picker").datetimePicker();
			$("#datetime-picker2").datetimePicker();
		});

		var send = {

			submit : function() {
				if ($("#goodDetailName").val() == ''
						|| $("#consignorName").val() == ''
						|| $("#consignorPhone").val() == ''
						|| $("#consigneeName").val() == ''
						|| $("#consigneePhone").val() == ''
						|| $("#consigneeAddress").val() == ''
						|| $("#goodDetailSize").val() == ''
						|| $("#goodDetailWeight").val() == ''
						|| $("#goodDetailAmount").val() == ''
						|| $("#goodDetailValue").val() == ''
						|| $("#deliveryTime").val() == ''
						|| $("#requireArriveTime").val() == ''
						|| $("#deliveryWay").val() == ''
						|| $("#goodTransportCarType").val() == ''
						|| $("#primitiveImgs").val() == ''
						|| $("#packImgs").val() == ''
						|| $("#deliveryPlace").val() == ''
						|| $("#receivePlace").val() == '') {
					validateDialog.headDialog("请补充完整所需资料");
				}
				
				if ($("#goodDetailName").val() != ''
						&& $("#consignorName").val() != ''
						&& $("#consignorPhone").val() != ''
						&& $("#consigneeName").val() != ''
						&& $("#consigneePhone").val() != ''
						&& $("#consigneeAddress").val() != ''
						&& $("#goodDetailSize").val() != ''
						&& $("#goodDetailWeight").val() != ''
						&& $("#goodDetailAmount").val() != ''
						&& $("#goodDetailValue").val() != ''
						&& $("#deliveryTime").val() != ''
						&& $("#requireArriveTime").val() != ''
						&& $("#deliveryWay").val() != ''
						&& $("#goodTransportCarType").val() != ''
						&& $("#primitiveImgs").val() != ''
						&& $("#packImgs").val() != ''
						&& $("#deliveryPlace").val() != ''
						&& $("#receivePlace").val() != '') {
					var fd = new FormData($("#form1")[0]);
					
					$.ajax({
						url : "good/addGoodOrder?identify=wechat",
						type : "POST",
						data : fd,
						dataType : "json",
						processData : false, // 告诉jQuery不要去处理发送的数据
						contentType : false, // 告诉jQuery不要去设置Content-Type请求头
						success : function(data) {	
							if(data["status"] == 1){
								alert(data.message);
								window.location.reload();
							}else{
								alert(data["message"]);
								//alert("ssss");
								window.location.reload();
				   			}
				   		}
					});
				}
			},

		}

		$(".ulInput").each(function() {
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
