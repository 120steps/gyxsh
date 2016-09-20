$(document).ready(function(){
	$.datetimepicker.setLocale('ch');
	$("#birthday").datetimepicker({
		lang:"ch",
		format:"Y-m-d",
		timepicker:false,
		yearStart:1990,
		yearEnd:2020,
	});
	
	/*返回顶部*/
//	$(".toTop span").on("click",function(){
//		$("html,body").animate({
//			scrollTop:0
//		},800)
//	})
	
	/*表单验证*/
	var inputs=$(".form-group .inputDiv>input");
	var textareas=$(".form-group textarea");
	var selects=$(".form-group select")
	function checkTextarea(isCommit){
		textareas.each(function(index){
			var ele=$(textareas[index]);
			var labelEle=ele.parent().next();
			var emptyStr;
			switch (index){
				case 0:
					emptyStr="请填写已报名或参加其他社团";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 1:
					emptyStr="请填写特长";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 2:
					emptyStr="请填写兴趣爱好";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 3:
					emptyStr="请填写工作经历";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 4:
					emptyStr="请填写对学生会的认识";
					if(checkEmpty(ele,labelEle,emptyStr)){
						isCommit+=1;
					}
					clearwarn(ele,labelEle);
					break;
				case 5:
					emptyStr="请填写工作展望";
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
	
	function checkSelect(isCommit){
		var dropdowns=$(".btn-group .dropdown-toggle");
		selects.each(function(index){
			var selectEle=$(selects[index]);
			var labelEle=selectEle.parent().parent().parent().children().last();
			var errorStr;
			switch (index){
				case 1:
					errorStr="请选择你的政治面貌";
					if(selectRight(selectEle,index,dropdowns,labelEle,errorStr)){
						isCommit+=1;
					}
					clearSelectInfo(dropdowns,index,labelEle);
					break;
				case 2:
					errorStr="请选择你的民族";
					console.log(selectEle.val());
					if(selectRight(selectEle,index,dropdowns,labelEle,errorStr)){
						isCommit+=1;
					}
					clearSelectInfo(dropdowns,index,labelEle);
					break;
				case 3:
					errorStr="请选择第一志愿";
					if(selectRight(selectEle,index,dropdowns,labelEle,errorStr)){
						isCommit+=1;
					}
					clearSelectInfo(dropdowns,index,labelEle);
					break;
				case 4:
					errorStr="请选择第二志愿";
					if(selectRight(selectEle,index,dropdowns,labelEle,errorStr)){
						isCommit+=1;
					}
					clearSelectInfo(dropdowns,index,labelEle);
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
				labelEle.html("");
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
	
	function clearSelectInfo(btns,index,labelEle){
		var fatherDiv=$(btns[index]).parent().parent().parent();
		$(btns[index]).click(function(){
			if($(this).hasClass("dropdown-info")){
				$(this).removeClass("dropdown-info");
			}
			if(fatherDiv.hasClass("danger-state")){
				fatherDiv.removeClass("danger-state");
				$(labelEle).html("");
			}
		});
	}
	
	var imgFile=$(".apImage .img-file");
	var imgBtn=$(".apImage .img-btn");
	var imgCont=$(".apImage img");
	var imgWarning=$(".apImage .warning-info");
	
	imgBtn.click(function(){
		imgWarning.html("");
		imgFile.click();
		imgFile.on('change',function(){
			var objUrl = getObjectURL(this.files[0]);  //获取图片的路径，该路径不是图片在本地的路径
      		if (objUrl) {
         		imgCont.attr("src", objUrl);      //将图片路径存入src中，显示出图片
       		}
		});
	});
	
	function getObjectURL(file) {
  		var url = null ;
  		if (window.createObjectURL!=undefined) { // basic
   			url = window.createObjectURL(file) ;
  		} else if (window.URL!=undefined) { // mozilla(firefox)
    		url = window.URL.createObjectURL(file) ;
  		} else if (window.webkitURL!=undefined) { // webkit or chrome
    		url = window.webkitURL.createObjectURL(file) ;
  		}
  		return url ;
	}
	
	function checkImg(imgFile,imgWarning,isCommit){
		var file=imgFile.val();
		var image = new Image();
     	image.src = file;
     	if(!/.(gif|jpg|jpeg|png|GIF|JPG)$/.test(file)&&file!=""){
			imgWarning.html("<i class='fa fa-exclamation-circle'></i> 图片格式不对");
			return isCommit;
		}
		else{
			isCommit +=1;
			return isCommit;
		}
	}
	
	function checkForm(){
		var isCommit=0;
		isCommit=checkImg(imgFile,imgWarning,isCommit);
		isCommit=checkInput(isCommit);
		isCommit=checkSelect(isCommit);
		isCommit=checkTextarea(isCommit);
		console.log(isCommit);
		if(isCommit!=20){
			$("html,body").animate({
				scrollTop:0
			},800);
			return false;
		}else{
			return true;
		}
		
	}
	
	$(".commitBtn").click(function(){
		if(checkForm()){
			document.modifyAptForm.submit();
		}
	});
});
