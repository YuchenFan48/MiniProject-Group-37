<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html>
<head>
    <title>修改用户信息</title>
    <style>
        /*背景图片*/
        body{
            background-image: url("WelcomeBackground1.jpg");
            background-repeat:no-repeat;
            background-size:135%;
            opacity: 0.85;
        }
        /*导航栏样式*/
        .sidebar {
            background-color: #f1f1f1;
            width: 200px;
            height: 100%;
            position: fixed;
            top: 0;
            left: 0;
        }
        .sidebar ul {
            list-style-type: none;
            padding: 0;
            margin: 0;
        }
        .sidebar li {
            padding: 10px;
            border-bottom: 1px solid #ddd;
        }
        .sidebar li:hover {
            background-color: #ddd;
        }
        /* 顶部容器样式 */
        .top-container {
            background-color: #f1f1f1;
            padding: 10px;
            text-align: center;
            margin-bottom: 20px;
            overflow: hidden;
            position: relative;
        }
        .top-container img {
            max-width: 100%;
            height: auto;
        }
        .top-container h2 {
            font-size: 20px;
            margin: 0;
        }
        .top-container p {
            font-size: 12px;
            margin: 0;
        }
        /* 欢迎页面样式 */
        .welcome {
            text-align: center;
            margin: 50px auto;
            background-color: #f1f1f1;
            border-radius: 10px;
            padding: 20px;
            overflow: hidden;
            position: relative;
            width: calc(80% - 200px);
            margin-left: calc(5% + 250px);
        }
        .welcome img {
            position: absolute;
            top: 0;
            left: 0;
            object-fit: contain;
            width: 100%;
            height: auto;
            background-repeat: no-repeat;
            background-size: 70% 70%;
            background-position: center;
            animation: slideshow 10s linear infinite;
        }
        .welcome h1 {
            font-size: 24px;
            margin: 0;
        }
        /*容器样式*/
        .container {
            margin: 50px auto;
            width: calc(80% - 200px);
            border-radius: 10px;
            background-color: #f1f1f1;
            padding: 20px;
            margin-left: calc(5% + 250px);
        }
        /* 列表样式 */
        table {
            width: 100%;
            border-collapse: collapse;
        }
        th, td {
            padding: 10px;
            text-align: left;
            border-bottom: 1px solid #ddd;
        }
        th {
            background-color: #f1f1f1;
        }
        /* 图片样式 */
        .car-image img {
            width: 40px;
            height: 30px;
        }
    </style>
</head>
<body>
        <div class="sidebar">
            <div class="top-container">
                <img src="logo.gif" alt="Logo">
                <div>
                    <h2>BUPT&QMUL</h2>
                    <p>Automatic Car Management System</p>
                </div>
            </div>
            <ul>
                <li><a href="homepage">Homepage</a></li>
            </ul>
        </div>
        <form method="post" action="./ModifyUser">
        <h2 class="ui teal header item" onmouseover="this.style.cursor='pointer'" onclick="document.location='Add.jsp'"></h2>
        <div class="container">
            <table>
                <thread>
                    <tr>
                        <th>New Password<td colspan="100px" height="50px" align="center">
                        <input type="password" name="user_password1" placeholder="password"></td>
                    </tr>
                    <tr>
                        <th>Repeat Password<td colspan="100px" height="50px" align="center">
                        <input type="password" name="user_password2" placeholder="password"></td>
                    </tr>
                    <tr>
                        <th width="300px" height="60px" align="center">
                            <input type="SUBMIT" name="submit" value="Submit">
                        </th>
                    </tr>
                </thread>
            </table>
        </div>
    </form>
    <div align="center">${errorMessage}</div>shxi
</body>
</html>
