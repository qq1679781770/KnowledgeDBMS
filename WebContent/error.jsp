<%@ page language="java" contentType="text/html; utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<title>Insert title here</title>
        <script src=<%=basePath+"js/jquery.min.js"%>></script>
        <script src=<%=basePath+"js/bootstrap.min.js"%>></script>
         <link rel="stylesheet" href=<%=basePath+"css/bootstrap.min.css" %> />
</head>
<body>
 <div class="alert alert-danger text-center" role="alert" style="height:100px">           
               <h3>没有权限</h3>
  </div>
</body>
</html>