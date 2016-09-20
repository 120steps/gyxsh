$(document).ready(function(){
	uls=$(".leftMenuCont>.nav ul");
	lis=$(".leftMenuCont>.nav>li").has("ul");
	
	
	
	lis.each(function(index){
		$(lis[index]).click(function(){
			var iEle=$(lis[index]).children("a").children("i");
			if($(lis[index]).children("ul").hasClass("in")){
				iEle.removeClass("fa-chevron-up").addClass("fa-chevron-down");
			}else{
				iEle.removeClass("fa-chevron-down").addClass("fa-chevron-up");
			}
		});
	});
	
//	for(var i=0;i<uls.length;i++){
//		if(hasActive(uls[i])){
//			$(uls[i]).prev().click();
//			break;
//		}else{
//			continue;
//		}
//	}
	
	
	function hasActive(ele){
		eleLength=($(ele).children(".active")).length;
		if(eleLength>0){
			return true;
		}else{
			return false;
		}
	}
//	function addActive(ele){
//		$(ele).addClass("in");
//	}
	
	
	/*监听浏览器窗口改变事件*/
	var leftMenuDiv=$(".leftMenu");
	var leftMenuCont=$(".leftMenuCont");
	var leftUl=$(".leftMenuCont .nav");
	leftMenuCont.width(leftMenuDiv.width()+30);
	leftMenuCont.css({"overflow-y":"scroll","height":"100%"});
	leftUl.css({"margin-right": "13px"});
	
	$(window).resize(function(){
		var leftWidth=leftMenuDiv.width();
		var contWidth=leftWidth+30;
		leftMenuCont.width(contWidth);
	});
});
