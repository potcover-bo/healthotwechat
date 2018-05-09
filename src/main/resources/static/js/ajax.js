function createXHR() {
    	var xhr = null;
    	if(window.XMLHttpRequest) {
    		xhr = new XMLHttpRequest(); //主流浏览器均支持；
    	}
    	else if(window.ActiveXObject) {
    		try {
    			//IE6+
    			xhr = new ActiveXObject('Msxml2.XMLHttp'); //不支持则会报一个异常，catch捕获
    		} catch(e) {
    			xhr = new ActiveXObject('Microsoft.XMLHttp');
    		   }
        }
    	return xhr;
    }

function BackFun(res) {
    	res = JSON.parse(res); //stringfy
    	var Aul = document.getElementById("hint");
    	if(res.length) {
    		var lis = [];
    		//或者使用字符串拼接；
    		for(var i = 0, len = res.length; i < len; i++) {
    			lis.push('<li>' + res[i] + '</li>');
    		}
    		Aul.innerHTML = lis.join('');
    		Aul.style.display = "block";
    	}
	}

function ajax(text1) {
    	 //1.创建
    	var xhr = null;
    	xhr = createXHR();
    	//2.初始化
    	xhr.open("GET", "http://localhost/test/test?test=" + text1, true);
    	//3.send操作请求
    	xhr.send(null);
    	//4.处理响应结果
    	xhr.onreadystatechange = function() { //事件监听-状态变化
    		if(xhr.readyState == 4) { //事件的状态
    			if(xhr.status >= 200 && xhr.status < 300 || xhr.status == 304) { //http的状态码--304表示缓存
    				BackFun(xhr.responseText);
    			}
    		}
    	}
    }