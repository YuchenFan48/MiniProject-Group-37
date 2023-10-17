<%@ page contentType="text/html;charset=ISO-8859-1" language="java" pageEncoding="ISO-8859-1"%>
<! DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Register</title>
    <style>
        body{
            width:100%;
            height:100%;
            background-image: url("background.jpg");
            background-size:100% 100%;
        }
        .bjimg {
            position: fixed;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            z-index: -10;
            object-fit: cover;
        }
        .box{
            align-self: center;
            margin: auto;
            top: 0;
            left: 0;
            right: 0;
            bottom: 0;
            width: 600px;
            height: 250px;
            border-radius: 10px 10px 10px 10px;
            opacity: 0.85;
            object-fit: cover;
        }
        p
        {
            font-size:20px;
            color:Black;
        }
        p span
        {
            font-size:60px;
            float:left;
            padding-right:5px;
            font-weight:bold;
            color:BLACK;
        }
    </style>
</head>
<body>
<h2 class="ui teal header item" onmouseover="this.style.cursor='pointer'" onclick="document.location='Register.jsp'"><img src="register.jpg" height="150" width="250"></h2>
<form method="post" action="./Register">
    <table bgcolor="#87cefa" class="box">
        <tr><th colspan="600px" height="120px" align="center"><font size="5px" color="white">Welcome to Register!</font></th></tr>
        <tr><td colspan="600px" height="50px" align="center"><p>Username:<input type="text" name="user_name" placeholder="username"></p></td></tr>
        <tr><td colspan="600px" height="50px" align="center"><p>Password:<input type="password" name="passwordinput1" placeholder="password"></p></td></tr>
        <tr><td colspan="600px" height="50px" align="center"><p>Confirm the password:<input type="password" name="passwordinput2" placeholder="reinput password"></p></td></tr>
        <tr><th width="260px" height="40px" align="center"><input type="SUBMIT" name="submit1" value="Submit"></th>
            <th width="118px" align="center"><input type="reset" value="Reset"></th></tr>

        <%
            String str = "";
            if(request.getAttribute("errorMessageNull")!=null)
                str = (String) request.getAttribute("errorMessageNull");
        %>
        <tr><th align="center"><font size="5px" color="white"><%=str%></font></th></tr>

        <tr><th colspan="600px" height="120px" align="center"><font size="5px" color="white"> </font></th></tr>
    </table>
</form>
<div align="center">${errorMessage}</div>shxi
</body>
</html>
