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
			   var modules=${modules};

			   var vm=new Vue({
                        el:'#form',
                        data:{
                            domains:domains,
                            modules:modules,
                            id:modules[0],
                            module:'',
                            description:'',
                            domain:''
                        },
                        methods:{
                          modify:function(){

                              postJSON('<%=basePath+"kbms/modifymodule"%>',{"module":this.module,"domain":this.domain
                                      ,"description":this.description,"id":this.id,"user_id":<%=session.getAttribute("user_id")%>},function(data){
                                          alert("修改成功");
                                          location.assign('<%=basePath+"kbms/showmodifymodule"%>');
                                  });
                            }
                        }
                    
				   });


			   $('#select1').change(function(){
				      getJSON('<%=basePath%>'+'findmodulebyid',{"id":vm.id},function(data){
					          vm.module=data.module;
					          vm.description=data.description;
					          vm.domain=data.domain;
					      });
				   });
			   
		});
        </script>
        
</head>
<body>

<div style="height: 500px;width:1000px">
<form id="form">
   		<div class="form-group">
            <label for="select1">编号</label>
            <select  id="select1" class="form-control" v-model="id" >
                 <option v-for="item in modules">{{ item }}</option>
            </select>
        </div>
         <div class="form-group">
            <label for="module">模块名</label>
            <input type="text" class="form-control" id="module" v-model="module" ></input>
        </div>
         <div class="form-group">
            <label for="description">定义</label>
            <input type="text" class="form-control" id="name" v-model="description">
        </div>
        <div class="form-group">
            <label for="select2">所属领域</label>
            <select  id="select2" class="form-control" v-model="domain" >
                 <option v-for="item in domains">{{ item }}</option>
            </select>
        </div>
        <button  type="button" class="btn btn-default" v-on:click="modify">确认修改</button>
   </form>

</div>

<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</body>
</html>