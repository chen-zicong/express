var validateDialog={
	fadeDialog:function(str){      //底部出现文字提示
		var fadeDiv=document.createElement('div')
		fadeDiv.innerHTML='<h1 style="text-align:center;line-height:45px;">'+str+'</h1>';
		fadeDiv.style.cssText='height:45px;width:160px;background-color:#000000;background-color: rgba(0,0,0,0.8);position:fixed;bottom:25%;left:0;right:0;margin:auto;background-opacity:0.4;color:white;border-radius:5px;'
		document.body.appendChild(fadeDiv);
		fadeDiv.style.display='none';
		$(fadeDiv).fadeIn();
		var timer1=window.setTimeout(function(){
			$(fadeDiv).fadeOut('2000',function(){
				window.clearTimeout(timer1) ; 
				document.body.removeChild(fadeDiv);	
			});
			
		},1500);
	},
	headDialog:function(str){
		var fadeDiv=document.createElement('div')
		fadeDiv.innerHTML='<h1 style="text-align:center;line-height:45px;">'+str+'</h1>';
		fadeDiv.style.cssText='height:45px;width:160px;width:100%;background-color: rgba(231,76,60,0.9);position:fixed;top:60px;left:0;right:0;margin:auto;color:white;font-size:4vw'
		document.body.appendChild(fadeDiv);
		fadeDiv.style.display='none';
		$(fadeDiv).slideDown();
		var timer1=window.setTimeout(function(){
			$(fadeDiv).slideUp("2000",function(){
				window.clearTimeout(timer1) ; 
				document.body.removeChild(fadeDiv);
			});
			
		},1500);
	}
}

		