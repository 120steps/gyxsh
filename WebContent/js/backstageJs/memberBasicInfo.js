$(document).ready(function(){
	$.datetimepicker.setLocale('ch');
	$("#birthday").datetimepicker({
		lang:"ch",
		format:"Y-m-d",
		timepicker:false,
		yearStart:1990,
		yearEnd:2020,
	});
	
	
	var inputs=$(".form-group .inputDiv>input");
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
					Exp=RegExp(/^[\u4e00-\u9fa5]{1,5}(省|市|自治区)[\u4e00-\u9fa5]{1,5}(市|县|区)$/);
					emptyStr="请输入籍贯";
					formatStr="填写的籍贯格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 2:
					emptyStr="请输入出生日期";
					labelEle=$(inputs[index]).parent().parent().next();
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 3:
					Exp=RegExp(/^[\u4E00-\u9FA5]{2}\d{4}$/);
					emptyStr="请输入专业和班级";
					formatStr="填写的专业班级格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 4:
					Exp=RegExp(/^\d{13}$/);
					emptyStr="请输入学号";
					formatStr="填写的学号格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 5:
					Exp=RegExp(/^[\u4E00-\u9FA5]{1}\d{1}(-)\d{3}$/);
					emptyStr="请输入寝室号";
					formatStr="填写的寝室号格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 6:
					Exp=RegExp(/^1[3|4|5|7|8]\d{9}$/);
					emptyStr="请输入手机号码";
					formatStr="填写的手机号码格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 7:
					Exp=RegExp(/^[1-9][0-9]{4,9}$/);
					emptyStr="请输入QQ号";
					formatStr="填写的QQ号格式不正确";
					if(checkEmpty(ele,labelEle,emptyStr)){
						if(checkFormat(ele,Exp,labelEle,formatStr)){
							isCommit+=1;
						}
					}
					clearwarn(ele,labelEle);
					break;
				case 8:
					Exp=RegExp(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/);
					emptyStr="请输入邮箱";
					formatStr="填写的邮箱格式不正确";
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
		if(isCommit!=9){
			return false;
		}else{
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		if(checkForm()){
			document.memberBasicInfoForm.submit();
		}
	});
});
