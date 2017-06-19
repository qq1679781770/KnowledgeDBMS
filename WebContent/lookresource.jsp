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

        <style type="text/css">
        ul li{
           list-style-type:none;
        }
        </style>
        <script type="text/javascript">
			$(document).ready(function(){
                 var title='${title}';
                 var resource='${resource}';
                 var domain='${domain}';
                 var module='${module}';
                 var attributes=${attributes};
                 var last_time='${last_time}';
                 var create_id=${create_id};
                 var vm=new Vue({
                     el:'#vm',
                     data:{
                         title:title,
                         resource:resource,
                         attributes:attributes,
                         domain:domain,
                         module:module,
                         last_time,
                         create_id:create_id

                     }
                  });
				
				
		    });
        </script>

</head>
<body>

<div style="width:1230px;height: 600px" id="vm">
	<h3>{{ title }}</h3>
    <p>内容:{{ resource }}</p>
    <ul>
       <li v-for="item in attributes">{{ item.key }} :{{ item.value }}</li>
    </ul>
    <p>领域:{{ domain }} 模块:{{ module }} 最后修改时间:{{ last_time }} 最后修改者:{{ create_id }}<p>
	
</div>

</body>
</html>