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


            	 var vm=new Vue({
                        el:'#form',
                        data:{domain:'',description:''},
                        methods:{add:function(){
                                 postJSON('<%=basePath+"kbms/adddomain"%>',{"domain":this.domain,"description":this.description,"user_id"
                                     :<%=session.getAttribute("user_id")%>},function(data){
                                         alert("添加成功");
                                     });
                                 this.domain="";
                                 this.description="";
                            }}

                	 });
                 
              });
               
        </script>
        
</head>
<body>
<div style="height: 500px;width:1000px">

<form id="form">
   		<div class="form-group">
            <label for="domain">领域名</label>
            <input type="text" class="form-control" id="domain" v-model="domain">
        </div>
        <div class="form-group">
            <label for="description">定义</label>
            <input type="text" class="form-control" id="description" v-model="description">
        </div>       
        <button  class="btn btn-default" v-on:click="add">确认添加</button>
   </form>
<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</div>
</body>
</html>