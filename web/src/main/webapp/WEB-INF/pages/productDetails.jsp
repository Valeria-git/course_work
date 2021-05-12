<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<html>
  <head>
    <title>${phone.model}</title>
    <jsp:include page="header.jsp"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
    <style>
    table, th, td {
        border: 1px solid black;
        border-collapse: collapse;
        margin: 5px;
    }
    th, td {
        padding: 5px;
        text-align: left;
        border: white 1px;
    }
    input {
      border-radius: 100px;
      background-color: white;
      color: black;
    }
    *{
      color: white;
    }
    body{
      background-color: darkgray;
    }
    table{
      background-color: black;
    }
    img{
      border-radius: 20px;
    }
    </style>
</head>
<body>
<p>
  <a href="${contextUrl}productList">Back to product list</a>
</p>
<p>
<h2>
  <b> ${phone.brand} ${phone.model} </b>
</h2>
 <table align="right">
 <caption>Display</caption>
   <tr>
     <th>Size</th>
     <td>${phone.displaySizeInches} inches</td>
   </tr>
   <tr>
     <th>Resolution</th>
     <td>${phone.displayResolution}</td>
   </tr>
   <tr>
     <th>Technology</th>
     <td>${phone.displayTechnology}</td>
   </tr>
   <tr>
     <th>Pixel density</th>
     <td>${phone.pixelDensity}</td>
   </tr>
 </table>

 <table align="right">
   <caption>Dimensions & weight</caption>
     <tr>
       <th>Length</th>
       <td>${phone.lengthMm} mm</td>
     </tr>
     <tr>
       <th>Width</th>
       <td>${phone.widthMm} mm</td>
     </tr>
     <tr>
       <th>Colors</th>
       <td>
         <c:forEach var="color" items="${colors}">
           <c:out value = "${color.code}  " />
         </c:forEach>
       </td>
     </tr>
     <tr>
       <th>Weight</th>
       <td>${phone.weightGr} gr</td>
     </tr>
   </table>
</p>
<p>
 <img src="https://raw.githubusercontent.com/andrewosipenko/phoneshop-ext-images/master/${phone.imageUrl}">
</p>
<p>
<table align="right">
 <caption>Камера</caption>
   <tr>
     <th>Фронтальная камера</th>
     <td>${phone.frontCameraMegapixels} megapixels</td>
   </tr>
   <tr>
     <th>Основная камера</th>
     <td>${phone.backCameraMegapixels} megapixels</td>
   </tr>
 </table>
 <table align="right">
  <caption>Батарея</caption>
    <tr>
      <th>Talk time</th>
      <td>${phone.talkTimeHours} hours</td>
    </tr>
    <tr>
      <th>Stand by time</th>
      <td>${phone.standByTimeHours} hours</td>
    </tr>
    <tr>
      <th>Емкость батареи</th>
      <td>${phone.batteryCapacityMah} mAh</td>
    </tr>
  </table>
</p>
<p style="width:30%">
<b> Description: </b>
${phone.description}
</p>
<p>
<b>  Цена: $ ${phone.price} </b>
<form class="addPhone">
  <input type="text"  name="quantity" />
  <input type="hidden" name="phoneId" value="${phone.id}" />
  <input type="button" value="Add to cart" onclick="submitForm(this.form)" />
  <br>
  <div style="text-align: left;">
    <span style="color:red;" id="error${phone.id}"></span>
  </div>
</form>
</p>
<p>
<table align="right">
 <caption>Другое</caption>
   <tr>
     <th>Тип устройства</th>
     <td>${phone.deviceType}</td>
   </tr>
   <tr>
     <th>OС</th>
     <td>${phone.os}</td>
   </tr>
   <tr>
     <th>Bluetooth</th>
     <td>${phone.bluetooth}</td>
   </tr>
   <tr>
     <th>Internal storage</th>
     <td>${phone.internalStorageGb} Gb</td>
   </tr>
    <tr>
        <th>RAM</th>
        <td>${phone.ramGb} Gb</td>
      </tr>
 </table>
</p>
</body>

