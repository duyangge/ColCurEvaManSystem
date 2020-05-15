//手机号码校验
function isPoneAvailable(mobile){
	        var retel= /^(((13[0-9]{1})|(15[0-9]{1})|(18[0-9]{1}))+\d{8})$/; 
	        if(retel.test(mobile)) {
	           document.getElementById('telmes').innerText="";   //给label赋值用innerText
	           return true;
	         }else {
	         document.getElementById('telmes').innerText="请输入正确的手机号码！";
	         return false;
	        }
}

//邮箱校验
function isEmailAvailable(email) {
	 var reg = /^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/;
     if(reg.test(email)){
  	   document.getElementById('mailmes').innerText="";
  	   return true;
     }else{
       document.getElementById('mailmes').innerText="请输入正确的邮件地址！";
       return false;
     }
}
//两次密码是否一致
function checkpasswd(passwd,repasswd){
	 var pwdReg = /^(?![0-9]+$)(?![a-zA-Z]+$)[0-9A-Za-z]{8,16}$/;//8到16位数字与字母组合
	 if(!pwdReg.test(passwd)){
		 document.getElementById('passwdmes').innerText='密码为8到16位数字与字母组合！';
	     return false;
     }else{
    	 if(passwd == repasswd){
    		 document.getElementById('passwdmes').innerText='';
    		return true;
    	}else{
    		 document.getElementById('passwdmes').innerText='两次输入密码不一致';
    		return false;
    	}
     }
}