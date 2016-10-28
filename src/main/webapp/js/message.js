function dateTimeFormatter(value,pattern) {
	if (value == null || value == '') {
        return '';
    }
    var dt;
    if (value instanceof Date) {
        dt = value;
    } else {
        dt = new Date(value);
    }
    var format = pattern ? pattern : "yyyy-MM-dd hh:mm:ss";
    var o = {
	        "M+": dt.getMonth() + 1, // month
	        "d+": dt.getDate(), // day
	        "h+": dt.getHours(), // hour
	        "m+": dt.getMinutes(), // minute
	        "s+": dt.getSeconds(), // second
	        "q+": Math.floor((dt.getMonth() + 3) / 3), // quarter
	        "S": dt.getMilliseconds()
	    };
	    if (/(y+)/.test(format))
	        format = format.replace(RegExp.$1, (dt.getFullYear() + "")
	            .substr(4 - RegExp.$1.length));
	    for (var k in o)
	        if (new RegExp("(" + k + ")").test(format))
	            format = format.replace(RegExp.$1, RegExp.$1.length == 1 ? o[k] : ("00" + o[k]).substr(("" + o[k]).length));
    return format;
}

// 连接状态改变的事件
function onConnect(status) {
	//console.log("连接！"+status);
	//console.log(status)
	/*
	 * if (status == Strophe.Status.CONNFAIL) { alert("连接失败！"); } else if
	 * (status == Strophe.Status.AUTHFAIL) { alert("登录失败！"); } else
	 */
	if (status == Strophe.Status.DISCONNECTED) {
		connected = false;
	} else if (status == Strophe.Status.CONNECTED) {
		connection.send($pres().tree());
		if(console && console.log)
			console.log("连接成功，可以开始聊天了！");
		connected = true;
		// 当接收到<message>节，调用onMessage回调函数
		connection.addHandler(onMessage, null, 'message', null, null, null);
		
		try{
			var mes = $pres({
				from : jid,
				to : ROOM_JID + "/" + jid.substring(0, jid.indexOf("@"))
			});
			var iqmes = $iq({
				from : jid,
				to : ROOM_JID + "/" + jid.substring(0, jid.indexOf("@")),
				type:'set'
			}).c("query",{
				xmlns:'http://jabber.org/protocol/muc#owner'
			}).c('x',{
				xmlns:"jabber:x:data",
				type:"submit"
			}).c("field",{
				"var":'muc#roomconfig_persistentroom',
				 type:'boolean',
				 value:true
			}).up().c("field",{
				"var":'muc#roomconfig_passwordprotectedroom',
				type:'boolean',
				value:true
			}).up().c("field",{
				"var":'muc#roomconfig_roomsecret',
				 type:'hidden',
				 value:'000000'
			});
			
		    mes.c("x",{
				  xmlns : 'http://jabber.org/protocol/muc'
		    }).c("password").t("000000");
			  
			mes.c('x', {
				xmlns : 'http://jabber.org/protocol/muc'
			}).c("history",{
				maxstanzas:20
			});
			
			connection.send(mes.tree());
			connection.send(iqmes.tree());
		}catch(e){
			if(console && console.log){
				console.log(e);
			}
			
		}
		
	}
}

// 接收到<message>
function onMessage(msg) {
	//alert('ok')
   //console.log("msg---"+msg)
  // console.log(msg)
    // 解析出<message>的from、type属性，以及body子元素
	//console.log(msg)
    var from = msg.getAttribute('from');
    var type = msg.getAttribute('type');
    var elems = msg.getElementsByTagName('body');
    var delay = msg.getElementsByTagName('delay');
    if (type == "groupchat" && elems.length > 0) {
        var body = elems[0];
        var stamp =null;
        if(delay[0])
        stamp = delay[0].getAttribute("stamp");
        var strJson = Strophe.getText(body);
        var strmsg ;
        var template;
        try{
        	  if(strJson){
        		  strJson= Strophe.xmlunescape(strJson);
        		  //console.log(strJson)
              	strmsg = JSON.parse(strJson);
              }
        	  var msg ="";
              if("1" == strmsg.msgType){//文字
            	  msg = strmsg.msgContent;
            	  
              }else{
            	  msg= strmsg.msgContent||strmsg;
              }
              msg = ioNull.emoji.parse(msg);
				
             // alert(msg);
              if(from.substring(from.indexOf('/') + 1) == userId){
            	  template = $('<div class="tell-man" style="text-align:right;">'+
                      	'<div class="tell-man-name">'+strmsg.userName+'&nbsp;&nbsp;'+dateTimeFormatter(stamp?new Date(stamp):new Date(),'yyyy-MM-dd hh:mm:ss')+'</div>'+
                          '<div class="tell-man-content">'+msg+'</div>'+
                          '</div>');
              }else{
            	  template = $('<div class="tell-man">'+
                        	'<div class="tell-man-name">'+(strmsg.userName?strmsg.userName:from.substring(from.indexOf('/') + 1))+'&nbsp;&nbsp;'+dateTimeFormatter(stamp?new Date(stamp):new Date(),'yyyy-MM-dd hh:mm:ss')+'</div>'+
                            '<div class="tell-man-content">'+msg+'</div>'+
                            '</div>');
              }
               
        }catch(e){
        	//console.log([e]);
        	  template = $('<div class="tell-man">'+//<img src="/img/tell-man.png" width="18" class="tell-man-img"/>
                 	'<div class="tell-man-name">'+from.substring(from.indexOf('/') + 1)+'&nbsp;&nbsp;'+dateTimeFormatter(stamp?new Date(stamp):new Date(),'yyyy-MM-dd hh:mm:ss')+'</div>'+
                     '<div class="tell-man-content">'+strJson+'</div>'+
                     '</div>');
        	  $("#tellmsg").append(template);
              $("#tellmsg").scrollTop(parseInt($("#tellmsg")[0].scrollHeight)+50);
              throw e;
        }
        //console.log(template)
        $("#tellmsg").append(template);
        $("#tellmsg").scrollTop(parseInt($("#tellmsg")[0].scrollHeight)+50); 
       
    }
    return true;
}

$(document).ready(function() {
    // 发送消息
    $("#btn-submit").click(function() {
    		//alert('ok')
        	var msg =$.trim($(".sendmsg").html()) ||$.trim($(".sendmsg").val()) ;
        	if(!msg){
        		return ;
        	}
        	//alert(s+"s2");
        	//alert(msg)
        	sendmsg(msg);//发送消息
            $(".sendmsg").html("");
            $("#ipt").val("");
        
    });
});

function sendmsg(msg) {
	
	if (connected) {
		if (!msg) {
			return;
		}
		var json = {
			"eventId" : scid,
			"msgContent" : msg,
			"msgId" : UUIDUtil.getUUID().id,
			"roomId" : scid,
			"userId" : userId,
			"userName" : userName,
			"msgTime" : new Date().getTime(),
			"msgType" : 1,
			"status" : 1
		};
		// 创建一个<message>元素并发送
		var msg = $msg({
			to : ROOM_JID,
			from : jid,
			type : 'groupchat'
		});//.c("body").t($.toJSON(json))
		var body = Strophe.xmlGenerator().createElement("body");
		// console.log(JSON.stringify(json))
		body.appendChild(Strophe.xmlTextNode(JSON.stringify(json)));
		msg.node.appendChild(body);
		/* msg.c("status").t("Online");*/
		connection.send(msg.tree());
	} else {
		$.alert("连接服务器失败，请刷新后重试!");
	}
}
