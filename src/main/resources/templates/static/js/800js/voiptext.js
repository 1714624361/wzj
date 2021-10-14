$(function(){ 
  var voip = new voipCall();
  // 登录返回，code =0登录成功，其它为失败
  voip.CallBack_login = function(code,message){
      console.log("——————————————登录操作————————————————");
      console.log(code);
      console.log(message);
	  if(code==0){
		  $("#heartdiv").html("运行状态: <span>连接成功</span>");
	  }else{
		  $("#heartdiv").html("运行状态: <span>连接失败</span>");
	  }
  };
  // 显示消息
  voip.ShowMsg = function(obj){
      console.log("——————————————显示消息————————————————");
      console.log(obj);
	$("#popupscreendiv").append('msg：'+obj+'<br>');
  };
  // 来电弹屏
  voip.CallBack_Call = function(kind,phone,obj){
      console.log("——————————————来电弹屏————————————————");
      console.log(kind);
      console.log(phone);
      console.log(obj);
	if(kind==2){
		$("#calleriddiv").html("来电号码: <span>"+phone+"</span>");
	}else{
		$("#calleriddiv").html("呼出号码: <span>"+phone+"</span>");
	}
  };
  //接听弹屏
  voip.CallBack_Answer = function(kind,phone,obj){
      console.log("——————————————接听弹屏————————————————");
      console.log(kind);
      console.log(phone);
      console.log(obj);
	if(kind==2){
		$("#calleriddiv").html("呼入接听：<span>"+phone+"</span>");
	}else{
		$("#calleriddiv").html("呼出接听：<span>"+phone+"</span>");
	}	 
  };
  //按键
  voip.CallBack_Key = function(keylist){
	  if (keylist && keylist.length > 0) {
		  for(var i=0;i<keylist.length;i++){
			  var item = keylist[i];
			  $("#popupscreendiv").append('电话：'+item.phone+',按键：'+item.key+'<br>');
		  }		  
	  }
  }
  //挂机弹屏
  voip.CallBack_HangUp = function(kind,phone,obj){
      console.log("——————————————挂机弹屏————————————————");
      console.log(kind);
      console.log(phone);
      console.log(obj);
	var peer = '呼出';
	if(obj.cdrPeer==2){
		peer = '呼入';
	}  
      console.log(peer);
	$("#popupscreendiv").append("录音文件："+obj.cdrRecordFile+"<br>,区域:"+obj.cdrPhoneArea+"<br>,接听时间："+obj.cdrSucessTime+			 "<br>,时长："+obj.cdrTalktime+'，方向：'+peer+"<br>");
	$("#calleriddiv").html('方向：'+peer+',电话：'+phone+'<span></span>');
  };
  //状态改变返回
  voip.CallBack_status = function(data){
      console.log("——————————————状态改变返回————————————————");
      console.log(data);
	  $("#devUserName").html("用户名称: <span>"+data.exteName+"</span>");
	  $("#deviceData").html("设备状态:"+data.statustext);
	  
  };
  //初始化数据
  voip.init("https://voip.800ing.com/");
  $('#d-url').keyup(function(){
	  voip.init($(this).val());
  });
  //开始登录
  $("#start").click(function(){
	var ll = setTimeout(function(){
	  voip.userlogin($('#d-name').val(),$('#d-pass').val());
      console.log("——————————————手动登录————————————————");	  
	},1000);    
  });
  //签入 签入后SIP话机可以收到来电电话
  $("#pause").click(function(){
	voip.setUserCheckInOut(0,function(data){
		if(data.code==0){
			$("#devicediv").html("状态: <span>签入</span>");
		}
	});   
  });
  //签出 签出后电话就不会在转入这个话机
  $("#unpause").click(function(){
	voip.setUserCheckInOut(1,function(data){
		if(data.code==0){
			$("#devicediv").html("状态: <span>签出</span>");
		}
	});  
  });
  //拔号
  $("#dial").click(function(){
	var phone = $("#phoneno").val();
	var paramvalue = $("#paramvalue").val();
	if(phone==null||phone.length<3){
		alert("号码不能为空");
	}
	voip.CallPhone(phone,paramvalue,function(data){
        console.log("——————————————手动拨号————————————————");
        console.log(data);
		if(data.code==0){
        console.log("——————————————拨号成功————————————————");
			$("#popupscreendiv").append("拔号成功："+$("#phoneno").val()+"<br>");
		}else{
        console.log("——————————————拨号失败————————————————");
			$("#popupscreendiv").append("拔号失败："+data.message+"<br>");
		}
	});  
  });
    
 
  //挂机
  $("#hangup").click(function(){
	voip.hangup($("#phoneno").val(),function(data){
        console.log("——————————————手动挂机————————————————");
        console.log(data);
		if(data.code==0){
        console.log("——————————————挂机成功————————————————");
			$("#popupscreendiv").append("挂机成功："+$("#phoneno").val()+"<br>");
		}else{
        console.log("——————————————挂机失败————————————————");
			$("#popupscreendiv").append("挂机失败："+$("#phoneno").val()+"<br>");
		}
	});   
  });
    

  //接听
  $("#Answer").click(function(){
	voip.answerPhone($("#phoneno").val(),function(data){
        console.log("——————————————手动接听————————————————");
        console.log(data);
		if(data.code==0){
        console.log("——————————————接听成功————————————————");
			$("#popupscreendiv").append("接听成功："+$("#phoneno").val()+"<br>");
		}else{
        console.log("——————————————接听失败————————————————");
			$("#popupscreendiv").append("接听失败："+$("#phoneno").val()+"<br>");
		}
	});   
  });
  //静音
  $("#StopPhone").click(function(){
	voip.StopPhone($("#phoneno").val(),function(data){
		if(data.code==0){
			$("#popupscreendiv").append("静音成功："+$("#phoneno").val()+"<br>");
		}else{
			$("#popupscreendiv").append("静音失败："+$("#phoneno").val()+"<br>");
		}
	});   
  });
  
  //转接 可以转接到坐席，传真，手机等---非坐席类电话，需要有空闲的线路
  $("#ivr").click(function(){
	  var phone = $("#phoneivr").val();
	  if(phone==null||phone.length<3){
		alert("号码不能为空");
	  }
	  voip.TransferUser(phone,function(data){
		if(data.code==0){
			$("#popupscreendiv").append("转接成功："+$("#phoneivr").val()+"<br>");
		}else{
			$("#popupscreendiv").append("转接失败："+$("#phoneivr").val()+"<br>");
		} 
	  });
  });
});