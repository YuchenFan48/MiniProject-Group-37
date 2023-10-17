<%@ page language="java" contentType="text/html;charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<! DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Please Login...</title>
    <style>
        body{
            background-image: url("background.gif");
            background-repeat:no-repeat;
            background-size:100%
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
            height: 300px;
            border-radius: 10px 10px 10px 10px;
            opacity: 0.85;
            object-fit: cover;
        }
        p
        {
            font-size:15px;				/* ???? */
            color:Black;				/* ???? */
        }
        p span
        {
            font-size:30px;				/* ???? */
            float:left;					/* ???? */
            padding-right:5px;			/* ?????? */
            font-weight:bold;			/* ??? */
            color:BLACK;				/* ???? */
        }
    </style>
</head>
<body>
<h2 class="ui teal header item" onmouseover="this.style.cursor='pointer'" onclick="document.location='login.jsp'"><img src="logo(1).png" height="150" width="250"></h2>
<form method="post" action="./login">
    <table bgcolor="#87cefa" class="box">
        <tr><th colspan="600px" height="120px" align="center"><font size="5px" color="black">Login System</font></th></tr>
        <tr><td colspan="620px" height="60px" align="center"><p>username:<input type="text" name="username" placeholder="username"></p><br/></td></tr>
        <tr><td colspan="620px" height="60px" align="center"><p>password:<input type="text" name="password" placeholder="password"></p><br/></td></tr>
        <tr><th width="260px" height="50px" align="center"><input type="SUBMIT" name="submit" value="Submit"></th>
            <th width="260px" align="center"><input type="reset" value="Reset"></th></tr>
        <div class="ui message">
            <tr><p><th width="260px" height="40px" align="center">NEW? <a href="Register.jsp">Register!</a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</th></p>
            <tr><th height="60px"></th></tr>
        </div>
    </table>
</form>
</body>
</html>