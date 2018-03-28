/*js对当前input进行正则表达式验证,需要传本身对象*/

var validation = {
	/*手机号码验证*/
	phone: function(object) {
		var phoneNumber=object.value;            
		if(!(/^1[34578]\d{9}$/.test(phoneNumber))){
			object.style.borderColor='#E74C3C';
			object.focus();
		}else
			object.style.borderColor='#3498DB';
		
	},
	/*电子邮箱验证*/
	mail:function(object) {
		var mail=object.value;            
		if(!( /^([a-zA-Z0-9_-])+@([a-zA-Z0-9_-])+(.[a-zA-Z0-9_-])+/.test(mail))){
			object.style.borderColor='#E74C3C'; 
			object.focus();
		}else
			object.style.borderColor='#1ABC9C';
	},
	/*检验是否为空*/
	isNull:function(object){
		var content=object.value;
		if(content==''){
			object.style.borderColor='#E74C3C'; 
			object.focus();
		}else
			object.style.borderColor='#1ABC9C';
	}
}


	

