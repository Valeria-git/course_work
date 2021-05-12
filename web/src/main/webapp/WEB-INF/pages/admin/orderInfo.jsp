<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
<head>
  <title>Order ${order.id}</title>
  <jsp:include page="header.jsp"/>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">

  <style>
    *{
      color: white;
    }
    body {
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
<h3>Номер заказа: <c:out value="${order.id}"/></h3>
<h3>Статус заказа: <c:out value="${order.status}"/></h3>
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
Цена заказа: <b>$ <c:out value="${order.subtotal}"/></b>
<br>
Цена доставки: <b>$ <c:out value="${order.deliveryPrice}"/></b>
<br>
Итог: <b>$ <c:out value="${order.totalPrice}"/></b>
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
Номер телефона: <c:out value="${order.contactPhoneNo}"/>
<br>
<br>
Дополнительная информация: <c:out value="${order.additionalInfo}"/>
<br>
<br>
<form action="${contextUrl}admin/orders" style="display:inline">
<input type="submit" value="Back"/>
</form>
<form action="${contextUrl}admin/orders/${order.id}?setStatus=2" method="post" style="display:inline">
<sec:csrfInput/>
<input type="submit" value="Delivered"/>
</form>
<form action="${contextUrl}admin/orders/${order.id}?setStatus=3" method="post" style="display:inline">
<sec:csrfInput/>
<input type="submit" value="Rejected"/>
</form>
</body>
</html>