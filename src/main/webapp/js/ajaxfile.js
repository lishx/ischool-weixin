//{fileid:"fileid",server:"/xxx/xxx",formdata:{name:"xxx",code:"xxx"},callback:func,progress:fun}
function upladFile(config) {
	var fileObj = document.getElementById(config.fileid).files[0]; // js 获取文件对象
	// FormData 对象
	var form = new FormData();
	if(config.formdata){
		$.each(config.formdata,function(i,n){
			form.append(i,n);
		});
	}
	form.append("file", fileObj); // 文件对象
	// XMLHttpRequest 对象
	var xhr = new XMLHttpRequest();
	xhr.open("post", config.server, true);
	//xhr.upload.onload = config.callback;
	xhr.onreadystatechange =function(){
		if(xhr.readyState==4){
			if(xhr.status==200){
				 if(xhr.responseText){
					var json = JSON.parse(xhr.responseText);
					 config.callback(json);
				 }
			}
		}
	}
	if(config.progress){
		xhr.upload.addEventListener("progress", config.progress, false);
	}
	xhr.send(form);
}


/*
 * 下载 ${file_server+'/index/downloadMarking?systemCode=ischool&businessKey=templetfile&marking=sclass'}
 * 
 * upladFile({
				fileid: "lefile",
				server: urls.fileupload,
				callback:function(){
					$("#progress").addClass("hide");
					ajaxCollegeMajors({pageNum: 1, pageSize: 20, orderBy: "cm.majcode asc"});
				},
				progress:function(evt){
					var $div = $("#progress div.progress-bar");
					if (evt.lengthComputable) {
						$div.attr("aria-valuenow", evt.loaded);
						$div.attr("aria-valuemax", evt.total);
						var finishrate = Math.round(evt.loaded / evt.total * 100)+ "%";
						$div.css("width", finishrate);
						$div.find("span.sr-only").text(finishrate);
					}
				}
			});
 * 
 * */
