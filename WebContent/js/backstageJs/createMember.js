$(document).ready(function(){
	var inputs=$(".form-group input");
	var selects=$(".form-group select")
	
	function checkInput(isCommit){
		inputs.each(function(index){
			var ele=$(inputs[index]);
			var labelEle=ele.parent().next();
			var emptyStr,formatStr;
			switch (index){
				case 0:
					Exp=RegExp(/^[\u4e00-\u9fa5]+$/);
					emptyStr="请输入姓名";
					formatStr="填写的姓名不是有效的中文名";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 1:
					emptyStr="请输入专业和班级";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 2:
					Exp=RegExp(/^\d{13}$/);
					emptyStr="请输入账号";
					formatStr="填写的账号格式不正确，格式为13位数字";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
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
	
	function checkSelect(isCommit){
		var dropdowns=$(".btn-group .dropdown-toggle");
		selects.each(function(index){
			var selectEle=$(selects[index]);
			var labelEle=selectEle.parent().parent().parent().children().last();
			var errorStr;
			switch (index){
				case 0:
					errorStr="请选择账号的权限";
					if(selectRight(selectEle,index,dropdowns,labelEle,errorStr)){
						isCommit+=1;
					}
					clearSelectInfo(dropdowns,index);
					break;
				default:
					break;
			}
		})
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
			}
		});
	}
	function selectRight(ele,index,btns,labelEle,warningInfo){
		var fatherDiv=labelEle.parent();
		
		if(ele.val()=="0"){
			labelEle.html("<i class='fa fa-exclamation-circle'></i> "+warningInfo);
			fatherDiv.addClass("danger-state");
			$(btns[index]).addClass("dropdown-info");
			return false;
		}else{
			$(btns[index]).removeClass("dropdown-info");
			fatherDiv.removeClass("danger-state");
			return true;
		}
	}
	
	function clearSelectInfo(btns,index){
		var fatherDiv=$(btns[index]).parent().parent().parent();
		$(btns[index]).click(function(){
			if($(this).hasClass("dropdown-info")){
				$(this).removeClass("dropdown-info");
			}
			if(fatherDiv.hasClass("danger-state")){
				fatherDiv.removeClass("danger-state")
			}
		});
	}
	function checkForm(){
		var isCommit=0;
		isCommit=checkInput(isCommit);
		isCommit=checkSelect(isCommit);
		if(isCommit!=4){
			return false;
		}else{
			$("#myModal").modal("show");
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		checkForm();
	});
	
	$(".checkBtn").click(function(){
		document.createForm.submit();
	});
});
