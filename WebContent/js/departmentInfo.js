$(document).ready(function(){
	var timeMenu=$(".time-menu");
	var $lis=$(".time-menu li");
	var $items=$(".time-content");
	var screenHeight=$(window).height();
	var itemHeight=$items.height();
	var heightGap=screenHeight-itemHeight;
	var titleTop=getTop(timeMenu);
	
	menuFix()
	active($items);
	
	$(window).scroll(function(){
		menuFix()
		active($items);
	});
	
	$lis.each(function(index){
		$(this).click(function(){
			var contentTop = $($items[index]).offset().top;
			setTimeout(function(){
				$(window).scrollTop(contentTop-10);
			});
		})
	});
	
	function active($ele){
		$ele.each(function(index){
			var scrollTop=$(window).scrollTop();
			var itemTop=getTop($(this));
			if(itemTop-scrollTop<heightGap+100){
				removeActive($lis);
				$($lis[index]).addClass("active");
			}else{
				$($lis[index]).removeClass("active");
			}
		})
	}
	function menuFix(){
		if($(window).scrollTop()>=titleTop-10){
			timeMenu.css({
				"position":"fixed",
				"top":"10px"
				},2000);
		}else{
			timeMenu.css({"position":""});
		}
	}
	
	$(".toTop span").on("click",function(){
		$("html,body").animate({
			scrollTop:0
		},800)
	})
	
});
function getTop(ele){
	return ele.offset().top;
}
function removeActive($ele){
	$ele.each(function(index){
		$(this).removeClass("active");
	});
}
