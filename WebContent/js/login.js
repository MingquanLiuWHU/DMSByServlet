/*
 * 登录相关处理
 */



function login(){
	
	var account = document.getElementById("account").value;
	var password = document.getElementById("password").value;
	var url = basepath+"/login?account="+account+"&password="+password;
	show(url,"main");
}


function exit(){
	var url = basepath+"/common/exit";
	show(url,"main");
}
