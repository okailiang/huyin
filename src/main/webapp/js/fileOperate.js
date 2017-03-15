/**
 * 文件的相关操作
 */
$(function() {
	// 用户改变上传打印文件高级搜索panel
	$("#expand-advanced-search").click(function() {
		var searchPanel = $("#advanced-search-panel");
		var advancedSearch = document.getElementById("advanced-search-panel");
		if (searchPanel.is(":hidden")) {
			searchPanel.show();
			advancedSearch.style.display = 'inline-block';
			searchPanel.attr("class", "glyphicon glyphicon-chevron-left");
		} else {
			searchPanel.hide();
			advancedSearch.style.display = 'none';
			searchPanel.attr("class", "glyphicon glyphicon-chevron-right");
		}
	});

	// 通过选中的多个文件id删除文件
	$("#del-select-file").click(
	function(e) {
		var fileIds = "";
		var idList;
		var currentPage = $(".currentPage").attr("value");
		var url = $("#page-div").attr("value");
		// 将id拼接为字符串方便传到后台
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
			//e.stopPropagation();
			
			var callback=function(){
				$.ajax({
					type : 'post',
					url : 'removeMultiFiles.do',
					data : 'fileIds=' + fileIds,
					cache : false,
					success : function(data) {
						var jsonData = JSON.parse(data);
						if (!jsonData.success) {
							alert(jsonData.message);
							return false;
						}
						window.location.href = url + "currentPage="
								+ currentPage + "";
					},
					error : function(data) {
						window.location.href = url + "currentPage="
								+ currentPage + "";
					}
				});
			};			
			confirm("确定删除？",callback);							
		}
	});
	
	// 通过文件id删除文件
$(".removeFileByIdUser").click(
	function(e) {
		e.stopPropagation();
		e.preventDefault();
		var currentPage = $(".currentPage").attr("value");
		var id = $(this).attr("value");
		var url = $("#page-div").attr("value");
		
		confirm("确认删除?",function(){
			$.ajax({
				cache : true,
				type : "post",
				async : true,
				url : "removeFile.do",
				data : {fileId: id},
				dataType : 'json',
				error : function(data) {
					window.location.href = url + "currentPage="
							+ currentPage + "";
				},
				success : function(data) {
					if (!data.success) {
						alert(data.message);
						return false;
					}
				    window.location.href = url + "currentPage="
				                   + currentPage + "";
				}
			});
		})
	});
	
	// 通过选中的多个文件id打印文件
	$("#print-select-file").click(function() {
		var html = '';
		var idList = new Array();
		$("input[type='checkbox']").each(function() {
			if (this.checked) {
				html += '<input type="text" class="form-control" value="'+ this.value +'" name="fileId">';
				idList.push(this.value);
			}
		});
		$("#select-print-fileid").append(html);
		if (idList == null || idList.length == 0) {
			$(".alert").alert("请选择要打印的文件！");
			alert("请选择要打印的文件！");
			return false;
		} else {
			$("#print-select-file-form").submit();
		}
	});
});