<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%
    String path = request.getContextPath();
    String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="description" content="">
    <meta name="keywords" content="">
    <meta name="viewport"
          content="width=device-width, initial-scale=1">
    <title>Hello Amaze UI</title>

    <!-- Set render engine for 360 browser -->
    <meta name="renderer" content="webkit">

    <!-- No Baidu Siteapp-->

    <link rel="icon" type="image/png" href="${pageContext.request.contextPath}/assets/i/favicon.png">

    <!-- Add to homescreen for Chrome on Android -->
    <meta name="mobile-web-app-capable" content="yes">
    <link rel="icon" sizes="192x192" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">

    <!-- Add to homescreen for Safari on iOS -->
    <meta name="apple-mobile-web-app-capable" content="yes">
    <meta name="apple-mobile-web-app-status-bar-style" content="black">
    <meta name="apple-mobile-web-app-title" content="Amaze UI"/>
    <link rel="apple-touch-icon-precomposed" href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
    <link href="http://amazeui.org/widgets/accordion/default/0?_ver=2.x" rel="canonical">
    <!-- Tile icon for Win8 (144x144 + tile color) -->
    <meta name="msapplication-TileImage" content="assets/i/app-icon72x72@2x.png">
    <meta name="msapplication-TileColor" content="#0e90d2">
    <script src="https://cdn.bootcss.com/jquery/3.3.1/jquery.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <script src="${pageContext.request.contextPath}/assets/js/amazeui.ie8polyfill.min.js"></script>
    <script src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
</head>
<body>
    <div>
        <span style="color: #949799;margin-left: 15px">订单号 : </span><span  id="goodOrderNumber" style="font-weight:bold"></span>
        <br>
        <span style="color: #949799;margin-left: 15px">国内承运人 : </span><span style="font-weight:bold">马赛物流</span>
    </div>


<hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
<section data-am-widget="accordion" class="am-accordion am-accordion-default" data-am-accordion='{ "multiple": true }'>

    <hr data-am-widget="divider" style="" class="am-divider am-divider-default" />
    <dl class="am-accordion-item am-active">
        <dt class="am-accordion-title">
           物流信息:
        </dt>
        <dd class="am-accordion-bd am-collapse ">
            <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
            <div class="am-accordion-content">
                打包成功  2018-03-07/22:08
            </div>
        </dd>
    </dl>
    <dl class="am-accordion-item am-active">
        <dt class="am-accordion-title">
             物流信息:
        </dt>
        <dd class="am-accordion-bd am-collapse ">
            <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
            <div class="am-accordion-content">
                准备出库  2018-03-06/21：09
            </div>
        </dd>
    </dl>
</section>
<script type="text/javascript">
   var goodOrderNumber=localStorage.getItem("goodOrderNumber");
    $(function () {
        console.log(goodOrderNumber);
        $('#goodOrderNumber').text(goodOrderNumber);

    })

</script>
</body>
</html>
