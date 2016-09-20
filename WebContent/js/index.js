$(document).ready(function(){
	$("#fullpage").fullpage({
		verticalCentered: true,
		paddingTop: 50,
		anchors: ['首页','秘书处','文艺部','生活部','体育部','学习部','女生部','外联部'],
		fixedElements:"#header",
		navigation:true,
		navigationPosition: "right",
		navigationTooltips: ['首页','秘书处','文艺部','生活部','体育部','学习部','女生部','外联部'],
		showActiveTooltip: true,
		scrollOverflow: true,
		afterLoad:function(link,index){
			if(index==1){
				$('.indexTitle').animate({
					fontSize: '36px'
				},"2000");
				$('.indexText').animate({
					top: '150px'
				},"2000")
			}
		},
		onLeave: function(link,index){
			if(index==1){
				$('.indexTitle').css({
					"fontSize": "30px"
				})
				$('.indexText').css({
					"top": "500px"
				})
			}
		}
	})
});
