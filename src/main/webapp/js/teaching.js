var exampageIndex = 0;
var stuexampageIndex = 0;
var workpageIndex=0;
var signpageIndex=0;
var resoucepageIndex = 0;
var loading =false;
function loadData(){
	// 每次加载添加多少条目
	var pageSize = 7; 
	if(loading){
		return;
	}
	loading =true;
	$.ajax({
		url : ctx + "/exam/dolist?pageSize="+pageSize+"&pageIndex="+exampageIndex+"&classId=" + classId,
		success : function(data) {
			
			loading = false;
			if (data&& data.length) {
				exampageIndex++;
				// 删除加载提示符
				$('.infinite-scroll-preloader').remove();
				var evalText = doT.template($("#ksap").text());
			
				$('#exam_Arrangement .list-block').append(evalText(data))
				// 更新最后加载的序号
				lastIndex =$('#exam_Arrangement .list-block ul').length;
				$.refreshScroller();
			}else{
				$('.infinite-scroll-preloader').remove();
				if(exampageIndex < 1){
					$('#exam_Arrangement .list-block').html("<div style=\"text-align:center;\">暂无数据</div>");
				}
				// 加载完毕，则注销无限加载事件，以防不必要的加载
				$.detachInfiniteScroll($('.infinite-scroll'));
			}
		}
	});
}

/**
 * 学生查看考试安排
 */
function loadStuKsapData(){
	// 每次加载添加多少条目
	var pageSize = 7; 
	if(loading){
		return;
	}
	loading =true;
	$.ajax({
		url : ctx + "/exam/student/dolist?pageSize="+pageSize+"&pageIndex="+stuexampageIndex+"&studentId=" + studentId,
		success : function(data) {
			
			loading = false;
			if (data&& data.length) {
				stuexampageIndex++;
				// 删除加载提示符
				$('.infinite-scroll-preloader').remove();
				var evalText = doT.template($("#ksap").text());
			
				$('#stuKsapexam .list-block').append(evalText(data))
				// 更新最后加载的序号
				lastIndex =$('#stuKsapexam .list-block ul').length;
				$.refreshScroller();
			}else{
				$('.infinite-scroll-preloader').remove();
				if(stuexampageIndex < 1){
					$('#stuKsapexam .list-block').html("<div style=\"text-align:center;\">暂无数据</div>");
				}
				// 加载完毕，则注销无限加载事件，以防不必要的加载
				$.detachInfiniteScroll($('.infinite-scroll'));
			}
		}
	});
}
function loadHomeWorkData(){
	// 每次加载添加多少条目
	var pageSize = 7; 
	if(loading){
		return;
	}
	loading =true;
	$.ajax({
		url : ctx + "/work/student/dohomework?pageSize="+pageSize+"&pageIndex="+workpageIndex+"&studentId=" + studentId,
		success : function(data) {
			
			loading = false;
			if (data&& data.length) {
				workpageIndex++;
				// 删除加载提示符
				$('.infinite-scroll-preloader').remove();
				var evalText = doT.template($("#zylb").text());
			
				$('#homeworkpage .list-block').append(evalText(data))
				// 更新最后加载的序号
				lastIndex =$('#homeworkpage .list-block ul').length;
				$.refreshScroller();
			}else{
				$('.infinite-scroll-preloader').remove();
				if(workpageIndex < 1){
					$('#homeworkpage .list-block').html("<div style=\"text-align:center;\">暂无数据</div>");
				}
				// 加载完毕，则注销无限加载事件，以防不必要的加载
				$.detachInfiniteScroll($('.infinite-scroll'));
			}
		}
	});
}
function loadWorkListData(){
	// 每次加载添加多少条目
	var pageSize = 7; 
	if(loading){
		return;
	}
	loading =true;
	$.ajax({
		url : ctx + "/work/doworklist?pageSize="+pageSize+"&pageIndex="+workpageIndex+"&classId=" + classId,
		success : function(data) {
			
			loading = false;
			if (data&& data.length) {
				workpageIndex++;
				// 删除加载提示符
				$('.infinite-scroll-preloader').remove();
				var evalText = doT.template($("#zylb").text());
			
				$('#workListpage .list-block').append(evalText(data))
				// 更新最后加载的序号
				lastIndex =$('#workListpage .list-block ul').length;
				$.refreshScroller();
			}else{
				$('.infinite-scroll-preloader').remove();
				if(workpageIndex < 1){
					$('#workListpage .list-block').html("<div style=\"text-align:center;\">暂无数据</div>");
				}
				// 加载完毕，则注销无限加载事件，以防不必要的加载
				$.detachInfiniteScroll($('.infinite-scroll'));
			}
		}
	});
}
function loadResouceData() {
	// 每次加载添加多少条目 
	var pageSize = 7;
	if (loading) {
		return;
	}
	loading = true;
	var scid = $("#scid").val();
	var lmtype = $("#lmtype").val();
	$
			.ajax({
				url : ctx + "/resouce/doList?pageSize=" + pageSize
						+ "&pageIndex=" + resoucepageIndex + "&scid="
						+ scid + "&lmtype=" + lmtype,
				success : function(data) {

					loading = false;
					if (data && data.length) {
						resoucepageIndex++;
						// 删除加载提示符
						$('.infinite-scroll-preloader').remove();
						var evalText = doT.template($("#docSharingList").text());

						$('#resouceListpage .list-block').append(
								evalText(data))
						// 更新最后加载的序号
						lastIndex = $('#resouceListpage .list-block ul').length;
						$.refreshScroller();
					} else {
						$('.infinite-scroll-preloader').remove();
						if (resoucepageIndex < 1) {
							$('#resouceListpage .list-block')
									.html(
											"<div style=\"text-align:center;\">暂无数据</div>");
						}
						// 加载完毕，则注销无限加载事件，以防不必要的加载
						$.detachInfiniteScroll($('.infinite-scroll'));
					}
				}
			});
}


$(function() {
	//考试列表
	$(document).on('pageInit', '#exam_Arrangement', function(e, id, page) {
		loadData();
		$(page).on('infinite', function() {
			loadData();
		});

	});
	//学生考试安排
	$(document).on('pageInit', '#stuKsapexam', function(e, id, page) {
		loadStuKsapData();
		$(page).on('infinite', function() {
			loadStuKsapData();
		});

	});
	
	//学生作业列表
	$(document).on('pageInit', '#homeworkpage', function(e, id, page) {
		loadHomeWorkData();
		$(page).on('infinite', function() {
			loadHomeWorkData();
		});

	});
	//老师作业列表
	$(document).on('pageInit', '#workListpage', function(e, id, page) {
		loadWorkListData();
		$(page).on('infinite', function() {
			loadWorkListData();
		});

	});
	
	//添加考试安排
	$(document).on('pageInit','#addexam', function (e, id, page) {
		var date=  new Date();
		var month= date.getMonth()<10?'0'+date.getMonth():date.getMonth();
		var day = date.getDate()<10?'0'+date.getDate():date.getDate();
		var hour = date.getHours()<10?'0'+date.getHours():date.getHours();
		var min = date.getMinutes()<10?'0'+date.getMinutes():date.getMinutes();
		$("#endtime").datetimePicker({ value: [date.getFullYear(), month, day,hour, min]});
		$("#examtime").datetimePicker({ value: [date.getFullYear(),month, day, hour, min]});
		 $(page).on("touchend","#submit",function(){
			 //examtime
			   var examtime =  $("#examtime").val().trim();
			   $("#formexamtime").val(examtime);
			   var endtime =$("#endtime").val().trim();
			   if(!examtime || !endtime){
				   $.alert('考试时间未填写');
				   return false;
			   }
			   $("#formendtime").val(endtime)
				var description = $("#description").text().trim();
				$("#formDescription").val(description);
				var address = $("#address").text().trim();
				if(!address){
					   $.alert('考试地址未填写');
					   return false;
				   }
				$("#formAddress").val(address);
				
				$("#addexamform").submit();
		})
		
	});			
	/*
	 * 布置作业
	 * */
	$(document).on('pageInit','#addHomeWork', function (e, id, page) {
		$("#limittime").datetimePicker({});
		$(page).on("touchend",".leassonItem",function(){
				var $this = $(this);
				var uname = $this.text();
				var uId = $this.attr("data-id");
				$("#leassonName").text(uname);
				$("#msId").val(uId);
				$(".close-popup").click();
				
		});
		
		 $(page).on("touchend","#submit",function(){
			 //examtime
				var workinfo = $("#divworkinfo").text().trim();
				$("#formworkinfo").val(workinfo);
				$("#addformwork").submit();
		})
	});
	
	/*
	 * 资源或者知识
	 * */
	$(document).on('pageInit','#resouceListpage', function (e, id, page) {
		
		loadResouceData();
		$(page).on('infinite', function() {
			loadResouceData();
		});
		
		/*$(page).on("touchend",".leassonItem",function(){
				var $this = $(this);
				var uname = $this.text();
				var uId = $this.attr("data-id");
				$("#leassonName").text(uname);
				$("#msId").val(uId);
				$(".close-popup").click();
				
		});
		
		 $(page).on("touchend","#submit",function(){
			 //examtime
				var workinfo = $("#divworkinfo").text().trim();
				$("#formworkinfo").val(workinfo);
				$("#addformwork").submit();
		})*/
	});
	
	/*
	 * 资源详情
	 * */
	$(document).on('pageInit','#resouceInfo', function (e, id, page) {
		var count =0;// role="progressbar" aria-valuenow="0" aria-valuemin="0" aria-valuemax="100"
		var protpl ='<div id="pro{pid}" class="progress-bar item-inner" >'+
	        '<span id="show-filename">{filename}</span>'+
	        '<span class="del">'+
	        	// '<a  class="js_delFile" id="d{pid}" fileId="{fileId}" href="javascript:void(0);">删除</a>'+
	        	'<a  class="external js_downFile" id="d{pid}" href="'+path+'/index/downloadFile?fileId={fileId}">下载</a>'+
	        '</span>'+
	    '</div>';
		var path = $("#fileServer").val();
	    $.ajax({
	    	url:path+'/index/batchBusinessFileMsg',
	    	data:{
	    		systemCode:"ischool",
	    		businessKeys:'material'+"${entity.lmid}"
	    	},
	    	success:function(data){
	    		var $div = $("#flielist");
	    		$div.html("");
	    	//	console.log(data)
	    		var html = "";
	    		for(var i in data){
	    			//console.log(data[i])
	    			html += protpl.replace("{filename}",data[i].fileName)
	    			.replace(/{fileId}/g,data[i].fileId).replace(/{pid}/g,i);
					
	    		}
	    		$div.append(html)
	    	}
	    })
	});
	
	
	//学生签到信息查看
	/*
	$(document).on('pageInit','#signList', function (e, id, page) {
		
		loadSignData();
		$(page).on('infinite', function() {
			loadSignData();
		});
	  	
	});
	*/
	//学生信息bind
	$(document).on('pageInit','#stubind', function (e, id, page) {
		
		$(page).on("touchend",".classItem",function(){
				var $this = $(this);
				var className = $this.text();
				var classId = $this.attr("data-id");
				$("#className").text(className);
				$("#classId").val(classId);
				$(".close-popup").click();
				
		});
	  	
	});
	
	
	
})

