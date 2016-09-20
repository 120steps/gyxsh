$(document).ready(function(){
	var department=$(".form-group ul.dropdown-menu")[0];
	console.log(department);
	var jurisdiction=$(".form-group ul.dropdown-menu")[1];
	var departmentSelect=$("#department");
	var jurisdictionSelect=$("#jurisdiction");
	if(departmentSelect.val()=="0"){
			hideOption(jurisdiction,1,5);
		}else if(departmentSelect.val()=="1"){
			hideOption(jurisdiction,3,5);
		}else{
			hideOption(jurisdiction,1,2);
		}
	
	departmentSelect.change(function(){
		var selectValue=departmentSelect.val();
		var jurisdictionLis=$(jurisdiction).children();
		jurisdictionLis.show();
		jurisdictionLis.removeClass("selected");
		$(jurisdictionLis[0]).addClass("selected");
		$(jurisdiction).parent().prev().children().first().text("请选择职位...");
		jurisdictionSelect.val("0");
		if(selectValue=="0"){
			hideOption(jurisdiction,1,5);
		}else if(selectValue=="1"){
			hideOption(jurisdiction,3,5);
		}else{
			hideOption(jurisdiction,1,2);
		}
	});
	
	function hideOption(element,begin,end){
		var lis=$(element).children();
		for(var i=begin;i<=end;i++){
			$(lis[i]).hide();
		}
	}
});
