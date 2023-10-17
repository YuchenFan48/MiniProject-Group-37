<%@ page import="java.util.List" %>
<%@ page import="vo.Rec" %>
<%@ page import="jdk.jfr.Recording" %>
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
    /* 容器样式 */
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
    <!-- 左侧导航栏内容 -->
    <li><a href="homepage">Homepage</a></li>
    <!-- 可根据需要添加其他栏目 -->
  </ul>
</div>
<div class="welcome">
  <img src="WelcomeBackground1.jpg" alt="Welcome Background">
  <h1>Welcome to the Car Management System!</h1>
</div>
<div class="container">
  <tr>
    <script>
      function refreshPage() {
        location.reload(); // 刷新当前页面
      }
    </script>
      <input type="SUBMIT" name="Refresh" value="Refresh"></a>
  </tr>
     <table>
       <thead>
         <tr>
           <th>Recording Id</th>
           <th>Runtime</th>
           <th>User id</th>
           <th>States</th>
           <th>Car id</th>
           <th>Video</th>
          </tr>
        </thead>
        <tbody>
          <!-- 列表数据 -->
          <%
            List<Rec> recording = (List<Rec>)request.getAttribute("recList");
            if(recording !=null){
              Rec temp=null;
              for(int x=0;x<recording.size();x++){
                temp =recording.get(x);
          %>
          <tr>
            <td><%=temp.getRecId()%></td>
            <td ><%=temp.getRuntime()%></td>
            <td><%=temp.getUserId()%></td>
            <td><%=temp.getStatus()%></td>
            <td><%=temp.getRobotId()%></td>
            <td>
              <video width="400" height="300" controls>
                <source src="<%=temp.getVideoURL()%>" type="video/mp4">
                Your browser does not support the video tag.
              </video>
            </td>

          </tr>
          <%}
          }else{
            response.sendRedirect("homepage");
          }%>
        </tbody>

      </table>
    </div>
  </body>
</html>