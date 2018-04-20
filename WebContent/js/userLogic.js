/**
 * 业务逻辑前期处理
 */

function queryList(deleted) {
	var url = userpath + "/queryList?deleted=" + deleted;
	show(url, "view");
}

/**
 * 删除文档
 * 
 * @param id
 * @returns
 */

function deleteDoc(id) {
	var url = userpath + "/deleteDoc" + "?id=" + id;
	show(url, "view");

}

function moveDoc(id, deleted) {
	var url = userpath + "/moveDoc" + "?id=" + id + "&deleted=" + deleted;
	show(url, "view");
}

function setDeleteDoc(id) {
	
	moveDoc(id, "true");
	
}

function restoreDoc(id) {
	moveDoc(id, "false");
}

function openEditJsp() {
	var url = userpath + "/insertDoc.jsp";
	show(url, "view");
}
/**
 * 新增文档
 * 
 * @returns
 */
function insertDoc() {
	var title = document.getElementById("title").value;
	var type = document.getElementById("type").value;
	var content = document.getElementById("content").value;
	var url = userpath + "/insertDoc" + "?title=" + title + "&type=" + type
			+ "&content=" + content;
	show(url, "view");
}

/**
 * 显示id对应的文档
 * 
 * @param id
 * @returns
 */
function showDoc(id) {
	var url = commonpath + "/showContent?id=" + id;
	show(url, "view");

}

function editDoc(id) {
	var url = userpath + "/editDoc?id=" + id;
	show(url, "view");
}

function saveDoc(id) {
	var title = document.getElementById("title").value;
	var type = document.getElementById("type").value;
	var content = document.getElementById("content").value;
	var url = userpath + "/modifyDoc" + "?title=" + title + "&type=" + type
			+ "&content=" + content;
	show(url, "view");
}
/**
 * 显示修改区域
 * 
 * @returns
 */
function changeEditArea() {
	var edit = document.getElementById("edit");
	var btn = document.getElementById("change");
	if (edit.style.display == "none") {
		btn.innerText = "关闭修改栏";
		edit.style.display = "block";
	} else {
		btn.innerText = "打开修改栏";
		edit.style.display = "none";
	}
}
function showInfo() {
	var url = commonpath + "/info.jsp";
	show(url, "view");
}
function saveInfo() {
	var username = document.getElementById("username").value;
	var password = document.getElementById("password").value;
	var url = commonpath + "/modifyInfo?username=" + username + "&password="
			+ password;
	show(url, "view");

}

function readArea() {
	var url = userpath + "/docsPublished";
	show(url, "view");
}
function submit(id) {
	var url = userpath + "/submitDoc?id=" + id;
	show(url, "view");
}