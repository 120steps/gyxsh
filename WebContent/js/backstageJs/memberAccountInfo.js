$(document).ready(function(){
	var inputs=$(".form-group .inputDiv>input");
	
	function checkInput(isCommit){
		inputs.each(function(index){
			var ele=$(inputs[index]);
			var labelEle=ele.parent().next();
			var emptyStr,formatStr;
			switch (index){
				case 0:
					Exp=RegExp(/^\d{13}$/);
					emptyStr="请输入账号";
					formatStr="填写的账号格式不正确，请填写13位数字";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 1:
					emptyStr="请输入密码";
					if(checkEmpty(ele,labelEle,emptyStr)){
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
	
	function checkFormat(e,Exp,labelEle,warningInfo){
		var fatherDiv=labelEle.parent();
		if(Exp.test($.trim(e.val()))){
			fatherDiv.removeClass("danger-state");
			return true;
		}else{
			labelEle.html("<i class='fa fa-exclamation-circle'></i> "+warningInfo);
			fatherDiv.addClass("danger-state");
			return false;
		}
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
	
	function checkForm(){
		var isCommit=0;
		isCommit=checkInput(isCommit);
		console.log(isCommit);
		if(isCommit!=2){
			return false;
		}else{
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		if(checkForm()){
			document.AccountForm.submit();
		}
	});
});
