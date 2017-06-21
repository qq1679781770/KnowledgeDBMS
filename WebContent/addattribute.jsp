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

				$('#attribute').mouseleave(function(){
		            if( $(this).val()=="")
		                 return;

		            getJSON('<%=basePath%>'+'findattributeexist', {"attribute":$('#attribute').val()}, function(data){
			            if(data=='Y'){
				            alert('已有属性');
				        }
			        });
	                 
		        });

				$('#add').click(function(){
					postJSON('<%=basePath%>'+'kbms/addattributes',{"create_id":<%=session.getAttribute("user_id")%>,"attribute":$('#attribute').val()}
					,function(data){
                               alert('添加成功');
                               
						});
					
				});
			});
        </script>
</head>
<body>
<div style="height: 500px;width:1000px">

<form id="form">
   		<div class="form-group">
            <label for="attribute">属性名</label>
            <input type="text" class="form-control" id="attribute" >
        </div>  
        <button  class="btn btn-default" id="add">确认添加</button>
   </form>

</div>
</body>
</html>