w<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
         pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="ISO-8859-1">
    <title>Insert title here</title>
</head>
<body>
<div class="ui center aligned container">
    <div class="ui inverted section divider"></div>
    <br>
    <br>
    <div class="ui eight wide column grid divided stackable">
        <div class="eight wide column">
            <form class="ui large form" method="post" action="./login" onsubmit="return logincheck()">
                <table align="center" width="600px" bgcolor="#87cefa" height="320px">
                    <tr><th colspan="600px" height="120px" align="center"><font size="5px" color="black">you have entered wrong user name or password.</font></th></tr>
                </table>
            </form>
        </div>
    </div>
</div>
</body>
</html>