<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html;utf-8">
        <title>Insert title here</title>
        <script src=<%=basePath+"js/jquery.min.js"%>></script>
        <script src=<%=basePath+"js/bootstrap.min.js"%>></script>
        <script src=<%=basePath+"js/jsxnh.js"%>></script>
        <script src=<%= basePath+"js/vue.min.js"%>></script>
        <link rel="stylesheet" href=<%=basePath+"css/bootstrap.min.css" %> />
        
        <script type="text/javascript">
        $(document).ready(function(){
            var name='${name}';
            var sex=${sex};
            var age=${age};
            var domain='${domain}';
            var domains=${domains};

            var vm=new Vue({
                  el:'#form',
                  data:{
                      name:name,
                      sex:sex,
                      age:age,
                      domain:domain,
                      domains:domains
                 },
                 methods:{
                     modify:function(){
                         if(this.domain==domain){
                         }else{
                             getJSON('<%=basePath+"kbms/updatedomain"%>',{"user_id":<%=session.getAttribute("user_id")%>,"domain":this.domain},function(data){});
                         }
                         getJSON('<%=basePath+"kbms/updatemessage"%>',{"user_id":<%=session.getAttribute("user_id")%>,"sex":this.sex,"name":this.name,"age":this.age}
                         ,function(){});
                         
                     }
                 }

            });
            
        });
        </script>
        
</head>
<body>
<div style="height: 500px;width:1000px">

   <form id="form">
   		<div class="form-group">
            <label for="name">姓名</label>
            <input type="text" class="form-control" id="name" v-model="name">
        </div>
        <div class="form-group">
            <label for="age">年龄</label>
            <input type="text" class="form-control" id="name" v-model="age">
        </div>
        <div class="form-group">
            <label >性别</label>
            <div>
                  <label class="radio-inline">
                          <input type="radio" name="inlineRadioOptions" id="sex" v-model="sex" value="1"> 男
                  </label>
                   <label class="radio-inline">
                           <input type="radio" name="inlineRadioOptions" id="sex" v-model="sex" value="2"> 女
                   </label>
              </div>
        </div>
        <div class="form-group">
            <label for="name">领域</label>
            <select v-model="domain" id="select" class="form-control" >
                 <option v-for="item in domains">{{ item }}</option>
            </select>
        </div>
        
        <button  class="btn btn-default" v-on:click="modify">确认修改</button>
   </form>


</div>
<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</body>
</html>