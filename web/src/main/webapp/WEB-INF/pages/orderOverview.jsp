<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
  <title>Order</title>
  <c:url var="contextUrl" value = "/" />
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
  <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js"></script>
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
    textarea{
      color: black;
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
    img{
      border-radius: 20px;
    }
    br{
      text-align: left;
    }
  </style>
</head>
<body>
<h2>Благодарим за заказ!</h2>
<br>
<h3>Номер вашего заказа: <c:out value="${order.id}"/></h3>
<table border="1px">
    <thead>
      <tr>
        <td>Фирма</td>
        <td>Модель</td>
        <td>Цвета</td>
        <td>Размер дисплея</td>
        <td>Количество</td>
        <td>Цена</td>
      </tr>
    </thead>
    <c:forEach var="orderItem" items="${order.orderItems}" varStatus="loop">
      <tr>
        <td>${orderItem.phone.brand}</td>
        <td>${orderItem.phone.model}</td>
        <td>
          <c:forEach var="color" items="${orderItem.phone.colors}">
            <c:out value="${color.code}  " />
          </c:forEach>
        </td>
        <td>${orderItem.phone.displaySizeInches} inches</td>
        <td>${orderItem.quantity}</td>
        <td>$ ${orderItem.phone.price}</td>
      </tr>
    </c:forEach>
</table>
Цена: <b>$ <c:out value="${order.subtotal}"/></b>
<br>
Доставка: <b>$ <c:out value="${order.deliveryPrice}"/></b>
<br>
Итого: <b>$ <c:out value="${order.totalPrice}"/></b>
<br>
<br>
Имя: <c:out value="${order.firstName}"/>
<br>
<br>
Фамилия: <c:out value="${order.lastName}"/>
<br>
<br>
Адрес: <c:out value="${order.deliveryAddress}"/>
<br>
<br>
Телефон: <c:out value="${order.contactPhoneNo}"/>
<br>
<br>
Дополнительная информация: <c:out value="${order.additionalInfo}"/>
<br>
<br>
<a href="${contextUrl}productList">Back to shopping</a>
</body>
</html>