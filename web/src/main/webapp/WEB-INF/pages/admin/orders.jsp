<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Orders</title>
  <jsp:include page="header.jsp"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <style>
    *{
      color: white;
    }
    body {
      background-image:<c:url value="../../resources/10.png" />;
      text-align: center;
      background-color: darkgray;
    }
    h1   {color: white;}
    p    {color: white;}
    input {
      border-radius: 100px;
      background-color: white;
      color: black;
    }
    input: hover {
      background-color: gold;
    }
    .firstColomn td{
      color: white;
    }
    td {
      background-color: black;
      text-align: center;
      color: white;
      border: 1px white;
    }
    .headTable{
      text-align: center;
      justify-content: center;
    }
    img{
      border-radius: 20px;
    }
  </style>
</head>
<body>
<h2>Меню администратора</h2>
<table border="1px">
    <thead>
      <tr>
        <td>Номер заказа</td>
        <td>Имя клиента</td>
        <td>Номер</td>
        <td>Адрес</td>
        <td>Цена</td>
        <td>Статус</td>
        <td>Дата</td>
      </tr>
    </thead>
    <c:forEach var="order" items="${ordersList}" varStatus="loop">
      <tr>
        <td><a href="${contextUrl}admin/orders/${order.id}">${order.id}</a></td>
        <td>${order.firstName} ${order.lastName}</td>
        <td>${order.contactPhoneNo}</td>
        <td>${order.deliveryAddress}</td>
        <td>$ ${order.totalPrice}</td>
        <td>${order.status}</td>
        <td>${order.date}</td>
      </tr>
    </c:forEach>
</table>
<br>
<a href="${contextUrl}productList">Leave admin area</a>
</body>
</html>