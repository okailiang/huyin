$(function() {
// 删除多个文件，报数据库错误
$("#del-file-all").click(function(){
	var fileIds = "";
	var idList;
	idList = new Array();
	$("input[type='checkbox']").each(function() {
		if (this.checked) {
			idList.push(this.value);
			fileIds = fileIds + this.value + ",";			
		}
	});
	if (fileIds.length > 0) {
		fileIds = fileIds.substr(0, fileIds.length - 1);
	}				
	if (idList == null || idList.length == 0) {
		alert("请选择要删除的数据！");
		return false;
	} else {	   
		var callback=function(){
			$.ajax({
				cache : false,
				type : "post",
				url : "admin_removeFiles.do",
				data :{ 
					fileIds : fileIds
					},
				dataType : "json",
				
				success : function(data){
					if(data.success){
						for(var i=0;i<idList.length;i++){
							$("#trd"+idList[i]).remove();
						}
					}else{
						alert(data.message);
					}
				},
			});
		};
		confirm("你真的要删除吗?",callback);	

	}			
});
});

//管理员删除单个文件
	function removeFile(fileId) {
		//当前页
		var currentPage = $(".currentPage").attr("value");
		var pageurl = $("#page-div").attr("value");
		
		confirm("确认删除?",function(){
			$.ajax({
				type : "post",
				url : "admin_removeFile.do",
				data : {
					fileId: fileId
					},
				dataType : 'json',
				error : function(data) {
					window.location.href = pageurl + "currentPage="
							+ currentPage + "";
				},
				success : function(data) {
					if (!data.success) {
						alert(data.message);
						return false;
					}
				    window.location.href = pageurl + "currentPage="
				                   + currentPage + "";
				}
			});
		});
	}	