$(function(){
	$.config = {router: false}
  	$(document).on('click','.js_chooseName', function () {
	  $.popup('.popup-about');
	});	
  	
  	$(document).on('click','.js_chooseClass', function () {
  	  $.popup('.popup-about');
  	});	
  	
 });
$(".js_calendar").calendar({
    value: ['2016-07-21']
});

 var DateUtil = {
			
			format:function(time,fmt){
				
				var  d =null;
				if(!time){
					d = new Date();
				}
				if(typeof time == "number"){
					d = new Date(time);
				}else{
					d = time;
				}
				
				var strs =[];
				var f="-"
				if(fmt){
					var fmts="";
					if(fmt.indexOf("-")>0){
						fmts =fmt.replace(/-/g," ")
					}else if(fmt.indexOf("/")>0){
						fmts =fmt.replace(/\//g," ");
						f="/";
					}
					 fmts= fmts.replace(/:/g," ");
					strs = fmts.split(" ");
				}
				var r ="";
				for(var i in strs){
					switch (strs[i]){
						case "yyyy":
							r += f+d.getFullYear();
							break;
						case "MM":
							r += f+d.getMonth();
							break;
						case "DD":
						case "dd":
							r += f+d.getDate();
							break;
						case "HH":
						case "hh":
							r += " "+d.getHours();
							break;
						case "mm":
							r += ":"+d.getMinutes();
							break;
						case "SS":
						case "ss":
							r += ":"+d.getSeconds();
							break;
						default:
							break;
					}
				}
				//console.log(r.slice(1));
				
			}
		}
		// DateUtil.format(new Date(),"MM/DD/yyyy")
