$(document).ready(function(){
	$.datetimepicker.setLocale('ch');
	$("#beginTime").datetimepicker({
		lang:"ch",
		format:"Y-m-d H:i",
		timepicker:true,
		yearStart:2016,
		yearEnd:2020,
	});
	
	$("#stopTime").datetimepicker({
		lang:"ch",
		format:"Y-m-d H:i",
		timepicker:true,
		yearStart:2016,
		yearEnd:2020,
	});
	
	var timeBtn=$(".timeItem>p>button");
	timeBtn.click(function(){
		$('#myModal').modal({
			backdrop: 'static',
			keyboard: false
		});
	});
	
	$('#myModal').on('hidden.bs.modal', function (e) {
    	var formGroup=$(".form-group");
    	var inputs=$(".form-group input");
    	formGroup.removeClass("danger-state");
    	inputs.val("");
	});
	
	var inputs=$(".form-group input");
	function checkInput(isCommit){
		inputs.each(function(index){
			var ele=$(inputs[index]);
			var labelEle=ele.parent().next();
			var emptyStr;
			switch (index){
				case 0:
					emptyStr="请输入开始日期";
					labelEle=$(inputs[index]).parent().parent().next();
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 1:
					emptyStr="请输入结束日期";
					var errorTimeInfo="开始时间不能大于结束时间";
					var start=$(inputs[0]);
					var end=$(inputs[1]);
					labelEle=$(inputs[index]).parent().parent().next();
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkTime(start,end,labelEle,errorTimeInfo)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				default:
					break;
			}
		});
		return isCommit;
	}
	function checkEmpty(e,labelEle,warningInfo){
		var fatherDiv=labelEle.parent();
		if($.trim(e.val())==""){
			labelEle.html("<i class='fa fa-exclamation-circle'></i> "+warningInfo);
			fatherDiv.addClass("danger-state");
			return false;
		}else{
			fatherDiv.removeClass("danger-state");
			return true;
		}
	}
	function clearwarn(ele,labelEle){
		ele.focus(function(){
			var fatherDiv=labelEle.parent();
			if(fatherDiv.hasClass("danger-state")){
				fatherDiv.removeClass("danger-state");
				labelEle.html("");
			}
		});
	}
	function checkTime(time1,time2,labelEle,errorTimeInfo){
		var fatherDiv=labelEle.parent();
		var beginTime=time1.val();
		var begin=new Date(beginTime.replace("-","/").replace("-","/"));
	
		var endTime=time2.val();
		var end=new Date(endTime.replace("-","/").replace("-","/"));
		if(begin>end){
			labelEle.html("<i class='fa fa-exclamation-circle'></i> "+errorTimeInfo);
			fatherDiv.addClass("danger-state");
			return false;
		}else{
			fatherDiv.removeClass("danger-state");
			return true;
		}
	}
	function checkForm(){
		var isCommit=0;
		isCommit=checkInput(isCommit);
		if(isCommit!=2){
			return false;
		}else{
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		if(checkForm()){
			document.timeForm.submit();
		}
	});
	
});
