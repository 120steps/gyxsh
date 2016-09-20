$(document).ready(function(){
	var inputs=$(".form-group .inputDiv>input");
	
	function checkInput(isCommit){
		inputs.each(function(index){
			var ele=$(inputs[index]);
			var labelEle=ele.parent().next();
			var emptyStr,formatStr;
			switch (index){
				case 0:
					emptyStr="请输入原来的密码";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 1:
					emptyStr="请输入新的密码";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 2:
					emptyStr="请确认新的密码";
					formatStr="两次输入的密码不一致"
					if(checkEmpty(ele,labelEle,emptyStr)&&checkSame(inputs,labelEle,formatStr)){
						isCommit+=1;
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
	function checkSame(inputs,labelEle,warningInfo){
		var newPwd=$.trim($(inputs[1]).val());
		var confirmPwd=$.trim($(inputs[2]).val());
		var fatherDiv=labelEle.parent();
		if(newPwd==confirmPwd){
			fatherDiv.removeClass("danger-state");
			return true;
		}else{
			labelEle.html("<i class='fa fa-exclamation-circle'></i> "+warningInfo);
			fatherDiv.addClass("danger-state");
			return false;
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
	function checkForm(){
		var isCommit=0;
		isCommit=checkInput(isCommit);
		if(isCommit!=3){
			return false;
		}else{
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		if(checkForm()){
			document.changePasswordForm.submit();
		}
	});
});
