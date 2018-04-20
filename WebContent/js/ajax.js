/**
 * 初始化ajax
 */
// 公共变量定义
var xmlHttp = false;
var basepath = "/DMSByServlet";
var userpath = basepath +"/user";
var assessorpath = basepath +"/assessor";
var commonpath=basepath +"/common";
function initAjax() {
	if (window.XMLHttpRequest) {
		xmlHttp = new XMLHttpRequest();
	} else if (window.ActiveXObject) {
		try {
			xmlHttp = new ActiveXObject("Msxml2.XMLHTTP");
		} catch (e) {
			try {
				xmlHttp = new ActiveXObject("Microsoft.XMLHTTP");
			} catch (e) {
				window.alert("该浏览器不支持Ajax");
			}
		}
	}
	
}

/**
 * 刷新页面内容
 * 
 * @param url
 *            servlet路径+参数
 * @param view
 *            局部刷新视图的id
 * @returns
 */
function show(url,view="view") {
	xmlHttp.open("POST", url, true);
	xmlHttp.onreadystatechange = function() {
		if (xmlHttp.readyState == 4  ) {
			if(xmlHttp.status==200){
			document.getElementById(view).innerHTML = xmlHttp.responseText;
			}
			else{
				document.body.innerHTML = xmlHttp.responseText;
			}
	}
	}
	xmlHttp.send();	
}

