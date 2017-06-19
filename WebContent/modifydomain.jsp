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
					var domains=${domains};
					var vm=new Vue({
						el:'#form',
						data:{
							domains:domains,
							id:1,
							domain:'',
							description:''
					    },
					    methods:{
						    modify:function(){
							    postJSON('<%=basePath%>'+'kbms/modifydomain',{"id":vm.id,"domain":vm.domain,"description":
									                                 vm.description,"user_id":<%=session.getAttribute("user_id")%>},function(){alert("修改成功");
                                            vm.description='';
                                            vm.domain='';
									});
							 }
						}
					});


					$('#select').change(function(){

                         getJSON('<%=basePath%>'+'finddomainsbyid',{"id":vm.id},function(data){
                        	 vm.domain=data.domain;
     						vm.description=data.description;

                             });
						
						
						//$('#select').val(vm.item.id);
				    });
		    });
        </script>
        
</head>
<body>

<div style="height: 500px;width:1000px">
<form id="form">
   		<div class="form-group">
            <label for="select">编号</label>
            <select  id="select" class="form-control" v-model="id" >
                 <option v-for="item in domains">{{ item.id }}</option>
            </select>
        </div>
         <div class="form-group">
            <label for="domain">领域名</label>
            <input type="text" class="form-control" id="domain" v-model="domain" ></input>
        </div>
         <div class="form-group">
            <label for="description">定义</label>
            <input type="text" class="form-control" id="name" v-model="description">
        </div>
        
        <button  class="btn btn-default" v-on:click="modify">确认修改</button>
   </form>

</div>

</body>
</html>