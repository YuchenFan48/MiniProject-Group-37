<%@ page language="java" contentType="text/html;charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="your.package.CarDAO" %>
<%@ page import="your.package.Car" %>
<html>
<head>
    <!-- 省略其他头部内容 -->
</head>
<body>
<div class="container">
    <table>
        <tr>
            <th>小车名字</th>
            <th>小车ID</th>
            <th>外观图片</th>
            <th>尝试走迷宫数量</th>
            <th>成功走通迷宫数量</th>
            <th>平均通过时间</th>
            <th>操作</th>
        </tr>
        <!-- 使用Java代码调用CarDAO并生成表格行 -->
        <%
            CarDAO carDAO = new CarDAO();
            List<Car> carList = carDAO.getCarList();

            for (Car car : carList) {
        %>
        <tr>
            <td><%= car.getName() %></td>
            <td><%= car.getId() %></td>
            <td class="car-image"><img src="<%= car.getImageUrl() %>" alt="<%= car.getName() %>"></td>
            <td><%= car.getMazeAttempts() %></td>
            <td><%= car.getMazeSuccesses() %></td>
            <td><%= car.getAverageTime() %></td>
            <td><a href="car.jsp?id=<%= car.getId() %>">查看详情</a></td>
        </tr>
        <% } %>
    </table>
</div>
</body>
</html>
