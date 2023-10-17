<%@ page import="vo.Robot" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sunda
  Date: 2023/6/28
  Time: 19:48
  To change this template use File | Settings | File Templates.
--%>
<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<html>
<head>
    <title>智能小车管理网站</title>
    <style>
        /* 导航栏样式 */
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
            /*border-radius: 10px;*/
            text-align: center;
            margin-bottom: 20px;
            overflow: hidden;
            position: relative;
        }
        .top-container img {
            /*position: absolute;*/
            max-width: 100%;
            height: 10%;
            /*margin-bottom: 20px;*/
            object-fit: contain;
            /*animation: slideshow 10s linear infinite;*/
        }
        @keyframes slideshow {
            0% {
                opacity: 1;
                z-index: 2;
            }
            25% {
                opacity: 0;
                z-index: 1;
            }
            50% {
                opacity: 0;
                z-index: 1;
            }
            75% {
                opacity: 1;
                z-index: 2;
            }
            100% {
                opacity: 1;
                z-index: 2;
            }
        }
        /* 欢迎页面样式 */
        .welcome {
            text-align: center;
            margin: 50px auto; /* 设置为居中 */
            background-color: #f1f1f1;
            border-radius: 10px;
            padding: 20px;
            overflow: hidden;
            position: relative;
            width: calc(80% - 200px); /* 减去左侧导航栏的宽度 */
            margin-left: calc(5% + 250px); /* 左侧导航栏的宽度 */
        }
        .welcome background-image {
            position: absolute;
            top: 0;
            left: 0;
            object-fit: contain;
            width: 100%;
            height: 10%;
            background-repeat: no-repeat;
            background-size: 70% 70%;
            background-position: center;
            animation: slideshow 10s linear infinite;
        }
        .welcome h1 {
            font-size: 24px;
            margin: 0;
        }
        /* 容器样式 */
        .container {
            margin: 50px auto;
            width: calc(80% - 200px); /* 减去左侧导航栏的宽度 */
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
        <img src="logo.gif" alt="Logo" style="width: 100%; height: 10%; margin: 5px;">
        <div>
            <h2 style="font-size: 20px; margin: 0;">BUPT&QMUL</h2>
            <p style="font-size: 12px; margin: 0;">Automatic Car Management System</p>
        </div>
    </div>
    <ul>
        <!-- 左侧导航栏内容 -->
        <li><a href="Carpage">MyCar</a></li>
        <li><a href="homepage">Homepage</a></li>
        <!-- 可根据需要添加其他栏目 -->
    </ul>
</div>
<form method="post" action="./DeletCar">
<div class="welcome">
    <img src="WelcomeBackground1.jpg" alt="Background Image 1" style="margin: 0px;width: 100%;">
    <h1>Welcome to the Car Management System!</h1>
</div>
<div class="container">
    <br>
    <br>
    <div class="ui eight wide column grid divided stackable">
        <div class="eight wide column">
            <form class="ui large form" method="post" action="./homepage">
                <table align="center" width="600px" bgcolor="#87cefa" height="320px">
                    <tr><th colspan="600px" height="120px" align="center"><font size="5px" color="black">ARE YOU SURE? </font></th></tr>
                </table>
                <a href="homepage.jsp">
                    <input type="SUBMIT" name="Sure" value="Sure!"></a>

            </form>
        </div>
    </div>
</div>
</form>
</body>
</html>
