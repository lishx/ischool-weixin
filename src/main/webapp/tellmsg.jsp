<%@ page language="java" contentType="text/html; utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html>
<c:set var="ctx" value="${pageContext.request.contextPath}" />
<head>
<meta charset="utf-8" />
<meta name="viewport"
	content="width=device-width,initial-scale= 1.0 ,minimum-scale=1.0 , maximum-scale=1.0,user-scalable= no">
	<meta content="telephone=no" name="format-detection" />
	<meta name="apple-mobile-web-app-capable" content="yes">
		<meta name="apple-mobile-web-app-status-bar-style" content="black">
			<title>交流互动</title>
			<link rel="stylesheet" href="css/sm.min.css" />
			<link rel="stylesheet" href="css/sm-extend.min.css" />
			<link rel="stylesheet" href="css/style.css" />
			<style type="text/css">
.tell-top {
	position: absolute;
	left: 0;
	top: 0;
	right: 0;
	bottom: 190px;
	overflow: auto;
}

.tell-man {
	float: left;
	width: 100%;
	margin-top: 8px;
}

.tell-man-img {
	float: left;
	margin-left: 10px;
	margin-right: 6px;
	margin-top: -1px;
}

.tell-man-name {
	color: #009900;
}

.tell-man-content {
	float: left;
	width: 94%;
	padding: 10px;
	padding-left: 35px;
}

.tell-btm {
	position: absolute;
	left: 0;
	right: 0;
	bottom: 34px;
	height: 150px;
	border-top: 1px solid #78CFF8;
}
</style>
			<script type="text/javascript" src="js/zepto.min.js"></script>
			<script type="text/javascript" src="js/sm.min.js"></script>
			<script type="text/javascript" src="js/sm-extend.min.js"></script>
			<script type="text/javascript" src="js/UUIDUtil.js"></script>
			<script type="text/javascript" src="js/strophe.min.js"></script>
			<script type="text/javascript" src="js/message.js"></script>
			<script type="text/javascript">
				//var path = '${path }';
				//model.addAttribute("scid", scid);
				/* model.addAttribute("user", user);
				model.addAttribute("fileServer", Config.getProperty("file_server_url"));
				model.addAttribute("server", Config.getProperty("xmpp.server"));
				model.addAttribute("bosh_service", Config.getProperty("xmpp.bosh_service"));
				model.addAttribute("room", Config.getProperty("xmpp.room")); */
				var scid = "${scid}";
				var filepath = '${fileServer}';
				var userId = '${user.suid}' || '${suid}';
				var userName = '${user.username}';
				var xmppServer = "${server}";
				// XMPP服务器BOSH地址
				var BOSH_SERVICE = '${bosh_service}';
				// 房间JID
				var ROOM_JID = '${scid}${room}';//'abc@conference.192.168.0.118';
				// XMPP连接
				var connection = null;
				// 当前状态是否连接
				var connected = false;
				// 当前登录的JID
				var jid = "";
				//	var loginId = '${user.loginId}';
				$(
						function() {
							if (!connected) {
								//server
								connection = new Strophe.Connection(
										BOSH_SERVICE);
								connection.connect(userId + "@" + xmppServer
										+ "/ischool-weixin", '123456',
										onConnect);
								jid = userId + "@" + xmppServer
										+ "/ischool-weixin";
							}

							$("#btn-cancel").on("click", function() {
								$(".sendmsg").html("");
								$(".sendmsg").val("");
							})

						})
			</script>
</head>
<body>
	<div class="page-group">
		<div class="page">
			<header class="bar bar-nav header_bg"> <a
				class="button button-link button-nav pull-left back external">
				<span class="icon icon-left col_w"></span> <!--返回-->
			</a>
			<h1 class="title col_w">交流互动</h1>
			</header>
			<div class="content" >
				<div id="tellmsg" class='tell-top' style="background-color: #fff;">

				</div>
				<form method="post">
				<div class='tell-btm' style="overflow-y: scroll;">
				<textarea id="ipt" class="sendmsg" rows="5" cols="100"></textarea>
<!-- 					<div id="ipt" class="sendmsg" contenteditable="true" style="height:100%; background-color: #fff;"></div> -->
					
						
					
					
				</div>
				</form>
				<div class="list-btm"
					style="bottom: 0px; position: absolute; right: 10px;">
					<div class="list-btm-div">
						<span onclick="$('#lefile').click();" style="cursor: pointer;"
							id="flie-submit" class="btn btn-submit"> <span
							class="btn-img"></span> <span class="btn-title">图片</span>
						</span> <span id="btn-submit" style="cursor: pointer;"
							class="btn btn-submit"> <span class="btn-img"></span> <span
							class="btn-title">发送</span>
						</span>
					</div>
				</div>
			</div>
		</div>
		<form id="fileform" enctype="multipart/form-data" method="post">
			<input id="lefile" type="file" accept="image/*" style="display: none" />
		</form>
		<script type="text/javascript" src="js/ajaxfile.js"></script>
		
		<script type="text/javascript" src="js/emoji-list-with-image.js"></script>
		<script type="text/javascript" src="js/emoji.js"></script>
		<script type="text/javascript" src="js/punycode.js"></script>
		<script type="text/javascript" src="js/punycode.min.js"></script>
		
		<!-- 1 -->
		<script type="text/javascript" src="js/w3cfuns_code.js"></script>
		<script type="text/javascript">
			// http://121.40.127.79:8082/upsoft-files/downloadFile?fileId=xx 下载 
			//http://121.40.127.79:8082/upsoft-files/deleteFile?fileId=xx 删除
			//http://121.40.127.79:8082/upsoft-files/uploadFile 上传 
			//http://121.40.127.79:8082/upsoft-files/batchBusinessFileMsg 文件列表 
			$(function() {
				 document.getElementById("ipt").wxImeEmojiFix(); // 原生用法
				$("#lefile")
						.on(
								"change",
								function() {
									var fileObj = document
											.getElementById("lefile").files[0]; // js 获取文件对象

									// 	var filepath='http://121.40.127.79:8082/upsoft-files';
									$.showPreloader();
									upladFile({
										fileid : "lefile",
										server : filepath
												+ '/index/uploadFile?systemCode=ischool-weixin',
										callback : function(data) {
											//$(".sendmsg").append('<img width="175" src="'+filepath+'/index/downloadFile?fileId='+data.fileId+'" />');
											sendmsg('<img width="175" src="'
													+ filepath
													+ '/index/downloadFile?fileId='
													+ data.fileId + '" />');//发送消息 
											$.hidePreloader();
										}
									});
								});

			})
		</script>
</body>
</html>
