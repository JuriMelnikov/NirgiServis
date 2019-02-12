<%-- 
    Document   : index
    Created on : 11.02.2019, 18:43:16
    Author     : jvm
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div id="iposition">
	 <div id="ititle">
	 Выберите роль:
	 </div>
	 <div id="forms">
		 <form id="form2" action="orders.php">
			<input type="submit" id="btn2" width="80px" value="Логистик">
		 </form>
		 <form id="form3" action="buch.php">
			<input type="submit" id="btn3" width="80px" value="Бухгалтер">
		 </form>
		 <form id="form4" action="seamstress.php">
			<input type="submit" id="btn4" width="80px" value="Швея">
		 </form>
	</div>

 
 </div>

<script type="text/javascript" src="./js/index.js"></script>
    </body>
</html>
