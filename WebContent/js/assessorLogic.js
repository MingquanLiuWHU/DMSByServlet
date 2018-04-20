function assessDoc(id,published){
	var url = assessorpath+"/assessDoc?id="+id+"&published="+published;
	show(url,"view");
}

function docsSubmitted(){
	var url = assessorpath+"/docsSubmitted";
	show(url,"view");
}