// 提交反馈信息
var submitSaveFeedInfo = function(){		
	var feedInfo = $('textarea[name="feedInfo"]').val();
	if(!feedInfo){
		alert("你还没有添加反馈信息！");
	}
	else{
		$.ajax({
			type:'post',
			url:'saveFeedInfo.do',
			datatype:'json',
			data:{feedInfo:feedInfo},
			success:function(data){ 
				var info=JSON.parse(data).success;
				if(info){
					$('#feedback-form').replaceWith('<h3>非常感谢您的反馈，我们会认真考虑您的建议！</h3>');
				}
			},		
		});
	}
};

// 保存反馈信息
$('#feedInfo-submit').click(function(e){
	// 阻止默认事件
	e.preventDefault();
	submitSaveFeedInfo();
});