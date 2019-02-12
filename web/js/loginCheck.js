
function loginCheck(){
       var url="ns/"+""+;
    //var xmlhttp=ajaxConnect();  
    var xhr = new XMLHttpRequest();
    xhr.open("POST",url,false);
    xhr.send();
    if(xhr.status !== 200){
            alert(xhr.status+': '+xhr.statusText);
    }else{
        var str= xhr.responseText;
        var fromPHP = xhr.responseText;
        if(!fromPHP["loginCheck"]){
            showLoginWindow();
        //	alert("loginCheck="+fromPHPobj["loginCheck"]);
        }else{
        //alert("loginCheck="+fromPHPobj["loginCheck"]);
             // Отключаем окно
            $('#loginWindow').hide();
            // Выключаем задник
            //if(document.getElementById("bgOverlay").style.display="block") 
            $('#bgOverlay').empty();
            second=0;
            clearInterval(setIntervalID);
            loadWorkers();
                //btnDisabled(true);
        }
    }
}
function showLoginWindow(count){
    
   // Отображаем и центрируем окно
    var loginWindow = $('#account');
    loginWindow.css(
    {
        position: 'absolute',
        left: ( $(document).width()  - loginWindow.outerWidth()  ) / 2,
        top:   300,
        'z-index': '100'
    });
    loginWindow.show();
	document.getElementById("login").focus();
    // включаем задник
    document.getElementById("bgOverlay").innerHTML= '<div id="TB_overlay"></div>';
	document.getElementById("delay_txt").innerHTML="<h3>Пожалуйста, введите логин и пароль!</h3>";
	if(count>0){
	document.getElementById("btnLogin").disabled=true;
	document.getElementById("delay_txt").innerHTML='<H3><font color="red">У Вас нет прав на вход!</font><br>Начать авторизацию можно через <span id="sec">'+delay+'</span>&nbsp;секунд.</H3>';
		clearInterval(setIntervalID);
		setIntervalID=setInterval('display()',1000);
	}else document.getElementById("btnLogin").disabled=false;
}
// Закрытие окна
function closeLoginWindow(){
	 // Отключаем окно
    $('#account').hide();
    // Выключаем задник
    if((document.getElementById("bgOverlay").style.display="block"))
    $('#bgOverlay').empty();
    clearInterval(setIntervalID);
}
function clickLoginButton(){
    var xhr = new XMLHttpRequest();
    data={};
    data.login=document.getElementById("login").value;
    data.pass=document.getElementById("pass").value;
    if(data.login!=='' && data.pass!==''){
        document.getElementById("btnLogin").disabled=true;
        xhr.open("POST","scr_orders.php?timeStamp="+new Date().getTime()+"&f=0",false);
        xhr.send("data="+JSON.stringify(data));
        if(xhr.status !== 200){
                alert(xhr.status+': '+xhr.statusText);
        }else{
            var fromPHP = xhr.responseText;
            if(!fromPHP["loginCheck"]){
                showLoginWindow();
            //	alert("loginCheck="+fromPHPobj["loginCheck"]);
            }else{
                if(fromPHP["levelM"]===-2)document.getElementById("nextPage").style.display="none";
                closeLoginWindow();
                setCurrentWeek();
            }
        }
    }
}