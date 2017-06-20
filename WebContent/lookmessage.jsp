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
				var name='${name}';
				var age=${age};
				var sex=${sex};
				if(sex==1){
					sex='男';
			    }else{
				    sex='女';
				 }
				 var tel=${tel};
				 var email='${email}';
				 var grade=${grade};
				 var domain='${domain}';

                  
				 var vm=new Vue({
					 el:'#user',
					 data:{
						 user_id:<%=session.getAttribute("user_id")%>,
						 name:name,
						 sex:sex,
						 age:age,
						 tel:tel,
						 email:email,
						 grade:grade,
						 domain:domain
				    }
				     
			    });
		    });
        </script>
 
 
</head>
<body>

<div id="user"  class="text-center jumbotron" style="width:1200px">
	<p><h3>账号:{{ user_id }}</h3><p>
	<p>姓名:{{ name }}</p>
	<p>性别:{{ sex }}</p>
	<p>年龄:{{ age }}</p>
	<p>电话:{{ tel }}</p>
	<p>邮箱:{{ email }}</p>
	<p>等级:{{ grade }}级</p>
	<p>领域:{{ domain }}</p>


</div>
<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</body>
</html>