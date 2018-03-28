<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="initial-scale=1.0, user-scalable=no, width=device-width">
    <title>发送位置</title>
    <link rel="stylesheet" href="https://cache.amap.com/lbs/static/main1119.css"/>
    <script type="text/javascript" src="https://webapi.amap.com/maps?v=1.4.2&key=92e0ca3160074551ce921b07f4f953e6"></script>
    <script type="text/javascript" src="https://cache.amap.com/lbs/static/addToolbar.js"></script>
    <script type="text/javascript" src="/express/js/jquery.min.js"></script>
<body>
<div id='container'></div>
<div id="tip"></div>
<script type="text/javascript">
	//获取司机审核状态和id
	var status='<%=session.getAttribute("status")%>';
	var driverId='<%=session.getAttribute("id")%>';

		$(function() {
			alert(driverId);
		    if(status != 1){
		    	alert("无权访问该页面");
				location.href="${pageContext.request.contextPath}/Base/goURL/home/audite_driver?identify=wechat";
		  }
		 });
    var map, geolocation,DLng,DLat;
    //加载地图，调用浏览器定位服务
    map = new AMap.Map('container', {
       resizeEnable: true
    });
    map.plugin('AMap.Geolocation', function() {
        geolocation = new AMap.Geolocation({
            enableHighAccuracy: true,//是否使用高精度定位，默认:true
            timeout: 10000,          //超过10秒后停止定位，默认：无穷大
            buttonOffset: new AMap.Pixel(10, 20),//定位按钮与设置的停靠位置的偏移量，默认：Pixel(300,5000)
            zoomToAccuracy: true,      //定位成功后调整地图视野范围使定位位置及精度范围视野内可见，默认：false
            buttonPosition:'RB'
        });
        map.addControl(geolocation);
		 geolocation.getCurrentPosition();

            //返回定位出错信息
       
        var t=self.setInterval(function(){
          map.addControl(geolocation);
		 geolocation.getCurrentPosition();
        //AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
       // AMap.event.addListener(geolocation, 'error', onError);      //返回定位出错信息
	//alert("0.0");
	
	},15000)
        AMap.event.addListener(geolocation, 'complete', onComplete);//返回定位信息
        AMap.event.addListener(geolocation, 'error', onError); 

    });


    //解析定位结果
    function onComplete(data) {
        var str=['定位成功'];
        str.push('经度：' + data.position.getLng());
        str.push('纬度：' + data.position.getLat());
        str.push('地理信息：' + data.formattedAddress);
        if(data.accuracy){
             str.push('精度：' + data.accuracy + ' 米');
        }//如为IP精确定位结果则没有精度信息
        str.push('是否经过偏移：' + (data.isConverted ? '是' : '否'));
        document.getElementById('tip').innerHTML = str.join('<br>');
        postLocation(driverId,data.formattedAddress);
    }
    //解析定位错误信息
    function onError(data) {
        document.getElementById('tip').innerHTML = '定位失败';
         postLocation(0,0);
    }
	
	function postLocation(driverId,goodTransportProcessPosition){
        $.ajax({
        	url:"/express/good/upProcess?identify=wechat",
        	dataType:'json',
        	type:'post',
        	data:{
	        	driverId:driverId,
        		goodTransportProcessPosition:goodTransportProcessPosition
        		},
        			 success: function(data){
				   		if(data["status"] == 1){
						alert(data["message"]);
						//window.location.reload();
				   	}else{
						alert(data["message"]);
						//window.location.reload();
				   	}
			   }
        });
        }
</script>
</body>
</html>