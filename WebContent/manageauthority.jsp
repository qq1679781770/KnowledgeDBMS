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

				var grades=${grades};

				var vm=new Vue({
					el:'#form',
					data:{
						grades:grades,
						grade:grades[0],
						manages:['增加','删除'],
						manage:'增加',
						authorities:[],
						authority:''
					},
					methods:{
						modify:function(){
							if(this.manage=='增加'){
								postJSON('<%=basePath+"kbms/addauthority"%>',{"grade":this.grade,"authority":this.authority,"user_id":
									   <%=session.getAttribute("user_id")%>},function(data){
										   alert("成功添加");
										   location.assign('<%=basePath+"kbms/showmanageauthority"%>');
										})
						    }else{
						    	postJSON('<%=basePath+"kbms/deleteauthority"%>',{"grade":this.grade,"authority":this.authority},function(data){
										   alert("成功删除");
										   location.assign('<%=basePath+"kbms/showmanageauthority"%>');
										})
							 }
						}
					}
			    });


			    $('#grade').change(function(){
				    if(vm.manage=='增加'){
					    getJSON('<%=basePath+"kbms/findaddauthoritybygrade"%>',{"id":vm.grade},function(data){
						    vm.authorities=data;
						   });
					}else{
						getJSON('<%=basePath+"kbms/finddeleteauthoritybygrade"%>',{"id":vm.grade},function(data){
						    vm.authorities=data;
						   });
					}
				 });

                $('#manage').change(function(){
                	if(vm.manage=='增加'){
					    getJSON('<%=basePath+"kbms/findaddauthoritybygrade"%>',{"id":vm.grade},function(data){
						    vm.authorities=data;
						   });
					}else{
						getJSON('<%=basePath+"kbms/finddeleteauthoritybygrade"%>',{"id":vm.grade},function(data){
						    vm.authorities=data;
						   });
					}
                 });


                if(vm.manage=='增加'){
				    getJSON('<%=basePath+"kbms/findaddauthoritybygrade"%>',{"id":vm.grade},function(data){
					    vm.authorities=data;
					   });
				}else{
					getJSON('<%=basePath+"kbms/finddeleteauthoritybygrade"%>',{"id":vm.grade},function(data){
					    vm.authorities=data;
					   });
				}
		    });

        </script>
</head>
<body>

<div style="height: 500px;width:1000px">

<form id="form">
   		<div class="form-group">
            <label for="grade">选择等级</label>
            <select id="grade" class="form-control" v-model="grade">
            	<option v-for="item in grades">{{ item }}</option>
            </select>
        </div>
        <div class="form-group">
            <label for="manage">选择操作</label>
             <select id="manage" class="form-control" v-model="manage">
            	<option v-for="item in manages">{{ item }}</option>
            </select>
        </div>
        <div class="form-group">
            <label for="authority">选择权限</label>
            <select id="authority" class="form-control" v-model="authority">
            	<option v-for="item in authorities">{{ item }}</option>
            </select>
        </div>            
        <button  class="btn btn-default" v-on:click="modify">确认修改</button>
   </form>

</div>

</body>
</html>