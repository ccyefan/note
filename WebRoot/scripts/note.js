function loadnotes(){
  	//设置当前li为选中状态
  	$("#book_list li a").removeClass("checked");
  	$(this).find("a").addClass("checked");
  	//发送ajax加载笔记列表
  	var bookId = $(this).data("bookId");
  	$.ajax({
  	  url:"http://localhost:8080/note/note/loadnotes.do",
  	  type:"post",
  	  data:{"bookId":bookId},
  	  dataType:"json",
  	  success:function(result){
  	  	var notes = result.data;
  	  	//将原有笔记列表清除
  	  	$("#note_list").empty();
  	  	//循环生成笔记列表
  	  	for(var i=0;i<notes.length;i++){
  	  		var noteId = notes[i].cn_note_id;//获取笔记ID
  	  		var noteTitle = notes[i].cn_note_title;//获取笔记标题
  	  		//生成li
			var s_li = '<li class="online">';
				s_li+= '<a>';
				s_li+= '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				s_li+= '</a>';
				s_li+= '<div class="note_menu" tabindex="-1">';
				s_li+= '	<dl>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
				s_li+= '	</dl>';
				s_li+= '</div>';
				s_li+= '</li>';
			var $li = $(s_li); 
			$li.data("noteId",noteId);
			//将li添加到笔记ul区域
			$("#note_list").append($li);
  	  	}
  	  	//将笔记列表区域显示
  	  	$("#pc_part_2").show();
  	  	$("#pc_part_6").hide();
  	  },
  	  error:function(){
  	  	alert("加载笔记列表失败");
  	  }
  	});
  };
  
//弹出笔记对话框
  function showAddNoteWindow(){
  	//显示背景色div
  	$(".opacity_bg").show();
  	//加载对话框内容显示
  	$("#can").load("alert/alert_note.html");
  };
  
//确认添加笔记
function sureAddNote(){
	//获取笔记名称
	var noteTitle = $("#input_note").val().trim();
	//笔记本ID
	var $book_li = $("#book_list li a.checked").parent();
	var bookId = $book_li.data("bookId");
	//TODO参数格式检查
	//发送ajax
	$.ajax({
	  url:"http://localhost:8080/note/note/add.do",
	  data:{"userId":userId,"bookId":bookId,"noteTitle":noteTitle},
	  dataType:"json",
	  type:"post",
	  success:function(result){
	    if(result.status==0){
	      //追加笔记列表的li
	        var noteId = result.data;//获取笔记ID
		    var s_li = '<li class="online">';
				s_li+= '<a>';
				s_li+= '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				s_li+= '</a>';
				s_li+= '<div class="note_menu" tabindex="-1">';
				s_li+= '	<dl>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_move" title="移动至..."><i class="fa fa-random"></i></button></dt>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_share" title="分享"><i class="fa fa-sitemap"></i></button></dt>';
				s_li+= '		<dt><button type="button" class="btn btn-default btn-xs btn_delete" title="删除"><i class="fa fa-times"></i></button></dt>';
				s_li+= '	</dl>';
				s_li+= '</div>';
				s_li+= '</li>';
			var $li = $(s_li); 
			$li.data("noteId",noteId);
			//将li添加到笔记ul区域
			$("#note_list").prepend($li);
	    //关闭对话框
	    closeWindow();
	      
	    }
	  },
	  error:function(){
	  	alert("创建笔记失败");
	  }
	});
};

//加载笔记信息显示
function loadNote(){
	//将当前笔记设置成选中
	$("#note_list li a").removeClass("checked");
	$(this).find("a").addClass("checked");
	//获取笔记ID
	var noteId = $(this).data("noteId");
	//发送ajax请求
	$.ajax({
	  url:"http://localhost:8080/note/note/load.do",
	  type:"post",
	  data:{"noteId":noteId},
	  dataType:"json",
	  success:function(result){
	  	if(result.status==0){
	  	    var noteTitle = result.data.cn_note_title;
	  	    var noteBody = result.data.cn_note_body;
	  	    //将标题显示到输入框
	  	    $("#input_note_title").val(noteTitle);
	  	    //将内容显示到编辑器
	  	    um.setContent(noteBody);
	  	}
	  },
	  error:function(){
	  	alert("加载笔记内容失败");
	  }
	});
};
  
//保存笔记处理
function saveNote(){
	//获取笔记ID
	var $note_li = $("#note_list li a.checked").parent();
	var noteId = $note_li.data("noteId");
	//获取笔记标题
	var noteTitle = $("#input_note_title").val().trim();
	//获取笔记内容
	var noteBody = um.getContent();
	//TODO格式检查
	//检查通过,发送ajax
	$.ajax({
		url:"http://localhost:8080/note/note/update.do",
		type:"post",
		data:{"noteId":noteId,"noteTitle":noteTitle,"noteBody":noteBody},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			    //如果标题改变,修改笔记列表li的标题
			    var oldTitle = $("#note_list li a.checked").text().trim();
				if(oldTitle!=noteTitle){
					//修改li的标题
					var str = '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+noteTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
					$("#note_list li a.checked").html(str);
				}
				//弹出提示
				alert(result.msg);
			}
		},
		error:function(){
			alert("保存笔记失败");
		}
	});
};

//弹出删除笔记确认框
function showConfirmRemoveWindow(){
	$("#can").load("alert/alert_delete_note.html");
	$(".opacity_bg").show();
};

//确认删除笔记
function sureRemoveNote(){
	//获取要删除的笔记ID
	var $note_li = $("#note_list li a.checked").parent();
	var noteId = $note_li.data("noteId");
	//发送ajax请求
	$.ajax({
		url:"http://localhost:8080/note/note/remove.do",
		type:"post",
		data:{"noteId":noteId},
		dataType:"json",
		success:function(result){
			if(result.status==0){
			   //关闭对话框
			   closeWindow();
			   //将列表中笔记li移除
			   $note_li.remove();
			   //提示删除成功
			   alert(result.msg);
			   //默认第一个笔记选中
			   $("#note_list li:first").click();//调用第一个笔记的单击
			}
		},
		error:function(){
			alert("删除笔记失败");
		}
	});
};

//发送搜索请求,将结果生成列表
function searchNote(){
	//获取请求要提交关键字
	var keyword = $("#search_note").val();
	var page = $("#page").val();
	//发送ajax请求
	$.ajax({
	  url:"http://localhost:8080/note/note/search.do",
	  type:"post",
	  data:{"title":keyword,"page":page},
	  dataType:"json",
	  success:function(result){
	  	if(result.status==0){
	  	    
	  		//获取搜索结果,生成列表
	  		var notes = result.data;
	  		for(i=0;i<notes.length;i++){
	  			var shareId = notes[i].cn_share_id;
	  			var shareTitle = notes[i].cn_share_title;
	  			//拼成li
	  			var s_li = '<li class="online">';
				s_li+= '<a>';
				s_li+= '<i class="fa fa-file-text-o" title="online" rel="tooltip-bottom"></i>'+shareTitle+'<button type="button" class="btn btn-default btn-xs btn_position btn_slide_down"><i class="fa fa-chevron-down"></i></button>';
				s_li+= '</a>';
				s_li+= '</li>';
				var $li = $(s_li);
				$li.data("shareId",shareId);
				//添加到搜索列表
				$("#search_list").append($li);
	  		}
			//将搜索区域显示
			$("#pc_part_6").show();
			$("#pc_part_2").hide();

	  	}
	  },
	  error:function(){
	  	alert("搜索笔记失败");
	  }
	});
}


  
  