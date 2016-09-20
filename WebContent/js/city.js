$(document).ready(function(){
	var provinceEle=$("#province");
	console.log(provinceEle);
	var cityEle=$("#city");
	var countyEle=$("#county");
	var provinceHtml,cityHtml,countyHtml;
	var cityStr="请选择城市...";
	var countyStr="请选择区县...";
	
	$.getJSON("json/city.json",function(data){
		provinceHtml=getoptions(data);
		console.log(provinceHtml);
		provinceEle.append(provinceHtml);
		
		provinceEle.change(function(){
			clearOption(cityEle,cityStr);
			clearOption(countyEle,countyStr);
			var index=getIndex(provinceEle);
			console.log(index);
			if(index!=0){
				var cityData=data[index].city;
				console.log(cityData);
				cityHtml=getoptions(cityData);
				console.log(cityHtml);
				cityEle.append(cityHtml);
			}
		});
	});
	
	function getoptions(data){
		var html="";
		$.each(data, function(index,item) {
			html+="<option value='";
			html+=index+1;
			html+="'>";
			html+=item.name;
			html+="</option>";
		});
		return html;
	}
	
	function clearOption(ele,str){
		var firstOption="<option selected='selected' value='0'>"+str+"</option>";
		ele.empty();
		ele.append(firstOption);
	}
	function getIndex(provinceEle){
		return provinceEle.val();
	}
});

