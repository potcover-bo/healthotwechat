$("#load").click(function ()
            {
	          $.ajax({
		      type:"get",
		      url:"http://localhost:8080/weddingmall/servlet/UserLoginServlet",
		      data:{
			            "userName":$("#user").val(),
			            "userPassword":$("#pass").val(),
		          }, 
		      dataType:"json",
		      success:function (data)
		           {
			             if(data.isLogin=="true")
			          {
			             location="index.html";
				         alert("登录成功");
			          }
			            else                                                                                      
			          {
			          	 alert("密码或用户名错误！");
			          } 
		           },
		          error:function ()
		         {
		             	alert("服务器出错");
		         },
	            })
	            return false;
            })