<%@ page language="java" contentType="text/html; charset=utf-8"
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
        <script src=<%=basePath+"js/jsxnh.js"%>></script>
        <script src=<%= basePath+"js/vue.min.js"%>></script>
        <link rel="stylesheet" href=<%=basePath+"css/bootstrap.min.css" %> />
        
        <script type="text/javascript">

            $(document).ready(function(){
                var authorities=${authorities};

                var vm=new Vue({
						el:'#table',
						data:{
							authorities:authorities
						}

                    });
            });
        </script>
        
</head>
<body>
<div style="height:630px;width:1260px">

<table id="table" class="table table-bordered">
   <thead>
   		<tr>
   			<td>等级</td>
   			<td>权限</td>
   		</tr>
   </thead>
   <tbody>
   		<tr v-for="item in authorities">
   		<td>{{ item.grade }}</td>
   		<td>{{ item.authorities }}</td>
   		</tr>  		
   </tbody>
    
</table>

</div>
</body>
</html>