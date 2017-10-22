//加载笔记本列表
function getbooklist(){
    //发送ajax
    $.ajax({
      url:"http://localhost:8080/note/notebook/loadbooks.do",
      type:"post",
      data:{"userId":userId},
      dataType:"json",
      success:function(result){
         if(result.status==0){//成功
            //循环result.data生成笔记本列表
            var books = result.data;
            for(var i=0;i<books.length;i++){
               var bookName = books[i].cn_notebook_name;//获取笔记本名
               var bookId = books[i].cn_notebook_id;//获取笔记本ID
               //拼成一个li
               var s_li = '<li class="online">';
				 s_li += '<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+bookName+'</a></li>';
            	 //将bookId绑定到li元素上
            	 var $li = $(s_li);//将s_li字符串转成jQuery对象
            	 $li.data("bookId",bookId);//绑定bookId值
            	 //将li添加到id=book_list的<ul>
            	 $("#book_list").append($li);
            }
         }
      },
      error:function(){
        alert("加载笔记本失败");
      }
    });
};

//弹出笔记本对话框
function showAddBookWindow(){
	//显示背景色div
	$(".opacity_bg").show();
	//加载对话框内容显示
	$("#can").load("alert/alert_notebook.html");
};

//关闭弹出的对话框
function closeWindow(){
	//取消背景色
	$(".opacity_bg").hide();
	//清空对话框div
	$("#can").empty();
};

function sureAddBook(){
   //获取笔记本名称
   var bookName = $("#input_notebook").val().trim();
   //TODO笔记本名称格式检查
   //发送ajax
   $.ajax({
     url:"http://localhost:8080/note/notebook/add.do",
     type:"post",
     data:{"userId":userId,"bookName":bookName},
     dataType:"json",
     success:function(result){
     	if(result.status==0){
     	   //获取新笔记本的ID
     	   var bookId = result.data;
     	   //追加一个笔记本列表li
        var s_li = '<li class="online">';
			 s_li += '<a><i class="fa fa-book" title="online" rel="tooltip-bottom"></i>'+bookName+'</a></li>';
     	 //将bookId绑定到li元素上
 	   var $li = $(s_li);//将s_li字符串转成jQuery对象
 	   $li.data("bookId",bookId);//绑定bookId值
 	   //将li添加到id=book_list的<ul>
 	   $("#book_list").prepend($li);
     	   //关闭对话框
     	   closeWindow();
     	}
     },
     error:function(){
     	alert("创建笔记本失败");
     }
   });

};

