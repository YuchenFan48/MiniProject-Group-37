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
    <title>ICMS</title>    <style>
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
         .centered-header {
             text-align: center;
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
        <li><a href="Carpage">My Car</a></li>
        <li><a href="ModifyUser.jsp">Modify User Information</a></li>
        <li><a href="login.jsp">Log out</a></li>
        <!-- 可根据需要添加其他栏目 -->
    </ul>
</div>
<div class="welcome">
    <img src="WelcomeBackground1.jpg" alt="Background Image" style="margin: 0px;width: 100%;">
    <h1>Welcome to the Car Management System!</h1>
</div>
<div class="container">
    <h1>My Car<div class="centered-header"></div></h1>
    <table><!--My own car -->
        <tr>
            <th>Car ID</th>
            <th>Car name</th>
            <th>Color</th>
            <th>Number of Axes</th>
            <th>Number of Wheel</th>
            <th>Power</th>
            <th>User ID</th>
        </tr>
        <%
            List<Robot> carList = (List<Robot>)request.getAttribute("carList");
            Robot temp = null;

            for(int x=0;x<carList.size();x++){
                temp =carList.get(x);
        %>
        <tr>
            <td><%=temp.getId() %></td>
            <td><%=temp.getName() %></td>
            <td style="color:<%=temp.getColor() %>"><%=temp.getColor() %></td>
            <td><%=temp.getNumOfAxes() %></td>
            <td><%=temp.getNumOfWheel() %></td>
            <td><%=temp.getPower() %></td>
            <td><%=temp.getUserId()%></td>
            <td>
                <a href="ModifyCar.jsp">
                    <input type="SUBMIT" name="ModifyCar" value="Modify Car!">
                </a>
                <a href="DeletCar.jsp">
                    <input type="SUBMIT" name="DeleteCar" value="Delete Car!">
                </a>
            </td>
        </tr>
        </tr>
        <%}%>
    </table>
    <% if (carList.stream().noneMatch(car -> car.getUserId() == 1)) { %>
    <a href="NewCar.jsp">
        <input type="submit" name="NewCar" value="New Car!">
    </a>
    <% } %>
</div>
<div class="container">
    <h1>Ranking List<div class="centered-header"></div></h1>
    <table>
        <tr>
            <th>Average Time</th>
            <th>Car name</th>
            <th>Color</th>
            <th>Number of Axes</th>
            <th>Number of Wheel</th>
            <th>Power</th>
            <th>User Name</th>
        </tr>
        <!-- 使用 JSTL 循环遍历小车数据 -->
        <%
            List<Robot> carList1 = (List<Robot>)request.getAttribute("carList1");
            Robot temp1 = null;
            for(int x1=0;x1<carList1.size();x1++){
                temp1 =carList1.get(x1);
        %>

        <tr>
            <td><%=temp1.getAvgruntime() %></td>
            <td><%=temp1.getName() %></td>
            <td style="color:<%=temp1.getColor() %>"><%=temp1.getColor() %></td>
            <td><%=temp1.getNumOfAxes() %></td>
            <td><%=temp1.getNumOfWheel() %></td>
            <td><%=temp1.getPower() %></td>
            <td><%=temp1.getUserName()%></td>

        </tr>
        <%}%>
    </table>
</div>
</body>
</html>
