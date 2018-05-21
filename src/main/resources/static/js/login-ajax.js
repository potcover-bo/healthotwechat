$("#load").click(function ()
            {
	          $.ajax({
	          	type: "POST",//方法类型
                dataType: "json",//预期服务器返回的数据类型
                url: "/user/login" ,//url
                data : {
                	"phone":$("#user").val(),
                	"password":$("#pass").val(),
                },
                success: function (result) {
                    if (result.code == 666) {

                        var redirectUrl = result.url;
                        window.location.href=  "http://dou.natapp1.cc/"+redirectUrl;

                    }else {
                        var redirectUrl = result.url;
                        var message = result.message;
                        alert(message)
                        window.location.href=  "http://dou.natapp1.cc/"+redirectUrl;
                    }
                },
                error : function(result) {
                    alert(result.toString())
                },
	          	

	            })
	            return false;
            })