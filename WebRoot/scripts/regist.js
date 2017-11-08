//注册
$(function(){
  $("#regist_button").click(function(){
  	//清除以前的提示信息
  	$("#warning_1").hide();
  	$("#warning_2").hide();
  	$("#warning_3").hide();
  	$("#warning_4").hide();
  	//获取要提交的请求数据
  	var name = $("#regist_username").val().trim();
  	var nick = $("#nickname").val().trim();
  	var pwd = $("#regist_password").val().trim();
  	var repwd = $("#final_password").val().trim();
  	//检查数据格式
  	var ok = true;
  	if(name==""){//检测用户名是否为空
  	  ok = false;
  	  $("#warning_1").show();
  	  $("#warning_1 span").html("用户名不能为空");
  	}
  	if(nick==""){//检测昵称是否为空
  	  ok = false;
  	  $("#warning_4").show();
  	  $("#warning_4 span").html("昵称不能为空");
  	}
  	if(pwd.length<6){//检测密码
  	  ok = false;
  	  $("#warning_2").show();
  	  $("#warning_2 span").html("密码长度过短");
  	}
  	if(pwd!=repwd){//检测确认密码
  	  ok = false;
  	  $("#warning_3").show();
  	  $("#warning_3 span").html("与密码不一致");
  	}
  	//没问题发送ajax请求
  	if(ok){
	  	$.ajax({
	  		url:"user/regist.do",
	  		type:"post",
	  		data:{"name":name,
	  			"password":pwd,
	  			"nick":nick},
	  		dataType:"json",
	  		success:function(result){
	  		   //成功,提示然后跳转到登录界面
	  		   if(result.status==0){
	  		     alert(result.msg);
	  		     //触发"返回"按钮的单击处理
	  		     $("#back").click();
	  		   }else if(result.status==1){
	  		   	 //用户名重复,显示提示信息
	  		   	 $("#warning_1").show();
	  		   	 $("#warning_1 span").html(result.msg);
	  		   }
	  		},
	  		error:function(){
	  		   alert("注册发生异常");
	  		}
	  		
	  	});//ajax函数结束
  	};//if结束
  });
});