<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<jsp:useBean id="url" scope="request" type="java.lang.String"/>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>二维码</title>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    <meta name="viewport" content="width=device-width,initial-scale=1,user-scalable=no" />
    <script type="text/javascript" src="../js/jquery.min.js"></script>
    <script type="text/javascript" src="../js/qrcode.js"></script>
</head>
<body>
<div id="qrcode" style="width:100px; height:100px; margin-top:15px;">
    <script>
       new QRCode(document.getElementById("qrcode"),"${url}")
       console.info("${url}")
    </script>
</div>
</body>
</html>