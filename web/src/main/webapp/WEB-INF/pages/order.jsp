<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

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
    textarea{
      color: black;
    }
  </style>
</head>
<body>
<h2>Order</h2>
<p>
  <a href="${contextUrl}cart"> Back to cart</a>
</p>
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
    <c:forEach var="phoneEntry" items="${phoneEntries}" varStatus="loop">
      <tr>
        <td>${phoneEntry.key.brand}</td>
        <td>${phoneEntry.key.model}</td>
        <td>
          <c:forEach var="color" items="${phoneEntry.key.colors}">
            <c:out value="${color.code}  " />
          </c:forEach>
        </td>
        <td>${phoneEntry.key.displaySizeInches} inches</td>
        <td>${phoneEntry.value}</td>
        <td>$ ${phoneEntry.key.price}</td>
      </tr>
    </c:forEach>
</table>
Subtotal: <b>$ ${subtotal}</b>
<br>
Delivery price: <b>$ ${deliveryPrice}</b>
<br>
Total: <b>$ ${totalPrice}</b>
<br>
<br>
<b style="color:red">${errorMsg}</b>
<form:form method="POST" action="${contextUrl}order" modelAttribute="clientInformationForm">
  Имя* <form:input path="firstName"/>
  <br>
  <form:errors path="firstName" style="color:red" />
  <br>
  Фамилия* <form:input path="lastName"/>
  <br>
  <form:errors path="lastName" style="color:red" />
  <br>
  Адрес* <form:input path="deliveryAddress"/>
  <br>
  <form:errors path="deliveryAddress" style="color:red" />
  <br>
  Телефон* <form:input path="contactPhoneNo"/>
  <br>
  <form:errors path="contactPhoneNo" style="color:red" />
  <br>
  <form:textarea path="additionalInfo" rows="5" cols="30"/>
  <br>
  <input type="submit" value="Order" />
</form:form>
</body>
</html>