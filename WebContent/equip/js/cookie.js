      Vue.prototype.setCookie = function (name, value, day) {
        if (day !== 0) {
		//当设置的时间等于0时，不设置expires属性，cookie在浏览器关闭后删除
            var curDate = new Date();
			//curDate是当前的时间包括年、月、日和星期，还有时分秒
            var curTamp = curDate.getTime();
			//curTamp是距今的总毫秒数
            //getTime() 方法可返回距 1970 年 1 月 1 日之间的毫秒数
            var curWeeHours = new Date(curDate.toLocaleDateString()).getTime() - 1;
			//curWeeHours是距当日凌晨的毫秒数
			//toLocaleDateString() 方法可根据本地时间把 Date 对象的日期部分转换为字符串，并返回结果。
            var passedTamp = curTamp - curWeeHours;
			//passedTamp当日的毫秒数
            var leftTamp = day * 24 * 60 * 60 * 1000 - passedTamp;
            console.log(leftTamp);
			//leftTamp距离一天结束的时间
            var leftTime = new Date();
            leftTime.setTime(leftTamp + curTamp);
		//escape() 函数可对字符串进行编码，这样就可以在所有的计算机上读取该字符串。
		//toUTCString() 方法可根据世界时 (UTC) 把 Date对象转换为字符串，并返回结果
            document.cookie = name + "=" + escape(value) + ";expires=" + leftTime.toUTCString();
        } else {
            document.cookie = name + "=" + escape(value);
        }
    }

 
    Vue.prototype.getCookie = function (name) {
    var arr;
	//^n量词匹配任何开头为 n 的字符串。
	//n$ 量词匹配任何结尾为 n 的字符串。
	//n? 量词匹配任何包含零个或一个 n 的字符串。
	//n+ 量词匹配包含至少一个 n 的任何字符串
	//n* 量词匹配包含零个或多个 n 的任何字符串。
    var reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
	//unescape() 函数可对通过 escape() 编码的字符串进行解码。
	//match()匹配
    if (arr = document.cookie.match(reg)){
	 var cookieString=unescape(arr[2]);
		if(cookieString.length!=0){
		 var cookies=cookieString.split(";");
		 return cookies;
		}
    }
    else
        return null;
		document.cookie = name + "=" + escape(value);
    }