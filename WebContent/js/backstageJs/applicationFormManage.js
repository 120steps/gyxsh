$(document).ready(function(){
	/*过程菜单状态切换
	var lis=$(".manage-menu li");
	var as=$(".manage-menu a");
	var manageConts=$(".manage-cont");
	as.each(function(index){
		$(this).click(function(){
			manageConts.css({"display":"none"});
			lis.removeClass("active");
			$(this).parent().addClass("active");
			$(manageConts[index]).css({"display":"block"});
		});
	});*/
	
	/*状态切换 开始*/
//	var manageConts=$(".manage-cont");
//	manageConts.css({"display":"none"})
//	lis.each(function(index){
//		if($(this).hasClass("active")){
//			$(manageConts[index]).css({"display":"block"});
//		}
//	});
//	
	/*状态切换 结束*/
	
	
	/*一键通过/一键淘汰 开始*/
	/*var singleCheckbox=$(".manage-cont .table tbody input");
	var allCheckbox=$(".manage-cont .table thead input");
	var untreatedBtn=$(".manage-cont .btnCont p button");
	
	allCheckbox.change(function(){
		allSelect(singleCheckbox,allCheckbox);
		toggleDisable(singleCheckbox,untreatedBtn);
	});
	singleCheckbox.each(function(index){
		$(this).change(function(){
			toggleDisable(singleCheckbox,untreatedBtn);
		});
	});
	
	function allSelect(singleCheckbox,allCheckbox){
		var isSelected=allCheckbox.is(":checked");
		if(isSelected){
			singleCheckbox.each(function(index){
				$(this).prop("checked",true);
			});
		}else{
			singleCheckbox.each(function(index){
				$(this).prop("checked",false);
			});
		}
	}
	function hasSelect(singleCheckbox){
		for(var i=0;i<singleCheckbox.length;i++){
			if($(singleCheckbox[i]).prop("checked")){
				return true;
			}else{
				continue;
			}
			
		}
		return false;
	}
	function toggleDisable(singleCheckbox,btns){
		if(hasSelect(singleCheckbox)){
			btns.removeClass("disabled");
		}else{
			btns.addClass("disabled");
		}
	}*/
	
	/*在同一个页面时要加上的代码
	var passedSingleCheckbox=$(".passed .table tbody input");
	var passedAllCheckbox=$(".passed .table thead input");
	var passedMailBtn=$(".passed .btnCont p button");
	
	passedAllCheckbox.change(function(){
		allSelect(passedSingleCheckbox,passedAllCheckbox);
		toggleDisable(passedSingleCheckbox,passedMailBtn);
	});
	passedSingleCheckbox.each(function(index){
		$(this).change(function(){
			toggleDisable(passedSingleCheckbox,passedMailBtn);
		});
	});
	
	var unpassedSingleCheckbox=$(".unpassed .table tbody input");
	var unpassedAllCheckbox=$(".unpassed .table thead input");
	var unpassedMailBtn=$(".unpassed .btnCont p button");
	
	unpassedAllCheckbox.change(function(){
		allSelect(unpassedSingleCheckbox,unpassedAllCheckbox);
		toggleDisable(unpassedSingleCheckbox,unpassedMailBtn);
	});
	unpassedSingleCheckbox.each(function(index){
		$(this).change(function(){
			toggleDisable(unpassedSingleCheckbox,unpassedMailBtn);
		});
	});*/
	/*一键通过/一键淘汰 结束*/
	
	/*分页 开始*/
	/*
	var untreatedDiv=$(".untreatedPage");
//	var untreatedPages=$(".untreatedPage a");
	var untreatedPageEle=newPages(untreatedDiv);
	var lastPage=10;
	console.log(untreatedDiv.children().get(0));
	
	$(untreatedPageEle).each(function(index){
		var pageNumEle=$(this);
		pageNumEle.click(function(){
			$(untreatedPageEle).removeClass("active");
			activeChange(pageNumEle,lastPage);
			togglePage(untreatedDiv,untreatedPageEle,lastPage);
		});
	});
	
	
	function newPages(container){
		var pages=container.children();
		var pageEle=[];
		for(var i=2;i<pages.length-2;i++){
			pageEle.push(pages[i]);
		}
		return pageEle;
	}
	
	function activeIndex(pageEle){
		for(var i=0;i<pageEle.length;i++){
			if($(pageEle[i]).hasClass('active')){
				return i;
			}else{
				continue;
			}
		}
	}
	function togglePage(container,pageEle,lastPage){
		var aIndex=activeIndex(pageEle);
		var aPageNum=parseInt($(pageEle[aIndex]).text());
		if(aPageNum==1){
			$(container.children().get(0)).replaceWith("<span>首页</span>");
			$(container.children().get(1)).replaceWith("<span>上一页</span>");
		}else if(aPageNum>1&&aPageNum<lastPage){
			$(container.children().get(0)).replaceWith("<a href='#'>首页</a>");
			$(container.children().get(1)).replaceWith("<a href='#'>上一页</a>");
			$(container.children().get(7)).replaceWith("<a href='#'>下一页</a>");
			$(container.children().get(8)).replaceWith("<a href='#'>尾页</a>");
		}else{
			$(container.children().get(7)).replaceWith("<span>下一页</span>");
			$(container.children().get(8)).replaceWith("<span>尾页</span>");
		}
	}
	function activeChange(pageNumEle,lastPage){
		var pageNum=parseInt(pageNumEle.text());
		if(pageNum<=3){
			$(untreatedPageEle).each(function(index){
				$(this).text(index+1);
			});
		}else if(pageNum>3&&pageNum<lastPage-2){
			var i=pageNum-2;
			$(untreatedPageEle).each(function(index){
				$(this).text(index+i);
			});
		}else{
			var j=lastPage-4;
			$(untreatedPageEle).each(function(index){
				$(this).text(index+j);
			});
		}
		activeFormatNum($(untreatedPageEle),pageNum);
	}
	function activeFormatNum(pageEle,activeNum){
		for(var i=0;i<pageEle.length;i++){
			num=parseInt($(pageEle[i]).text());
			if(num==activeNum){
				$(pageEle[i]).addClass("active");
				break;
			}else{
				continue;
			}
		}
	}*/
	/*分页 结束*/
	
	/*警告框*/
	var alertDiv=$(".form-table .alert");
	var alertNum=$(".form-table .alert strong");
	var num=parseInt(alertNum.text());
	if(num<1){
		alertDiv.css({"display":"none"});
	}
});
