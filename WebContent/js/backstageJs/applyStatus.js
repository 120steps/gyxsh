$(document).ready(function(){
	var statusLis=$(".status-content .status-step");
	statusLis.each(function(index){
		if($(this).hasClass("pass")){
			$(statusLis[index+1]).addClass("current");
		}
		onlyStatus($(this));
		checkStatus($(this));
	});
	
	function checkStatus(ele){
		if(ele.hasClass("current")){
			ele.append("<div class='tip'>正在审核</div>");
		}else if(ele.hasClass("fail")){
			ele.append("<div class='tip'>很遗憾，没有通过</div>");
		}
	}
	
	function onlyStatus(element){
		if(element.hasClass('current')&&element.hasClass('pass')){
			element.removeClass('current');
			element.children(".tip").remove();
		}else if(element.hasClass('current')&&element.hasClass('fail')){
			element.removeClass('current');
			
		}
	}
});
