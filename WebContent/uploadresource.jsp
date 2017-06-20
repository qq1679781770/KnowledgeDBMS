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
				var domains=${domains};
				var vm=new Vue({
					el:'#form',
					data:{
						domains:domains,
						modules:[],
						domain:domains[0],
						module:'',
						resource:'',
						title:''
					},
					methods:{
						add:function(){

							if(this.resource==''||this.title==''){

                                     alert("填写完整");
								}
							
							postJSON('<%=basePath+"kbms/uploadresource"%>',{"user_id":<%=session.getAttribute("user_id")%>,"domain":this.domain
								       ,"module":this.module,"resource":this.resource,"title":this.title},
									function(data){

                                               alert("上传成功，等待审核");
                                                $('#resource').val('');
                                                $('#title').val('');                                             
											});
						}
					}
				});

				getJSON('<%=basePath+"kbms/findmodulebydomain"%>',{"domain":vm.domain},
						function(data){

                                 vm.modules=data;
                                 vm.module=data[0];                                         
								});
                 $('#select1').change(function(){
                	 getJSON('<%=basePath+"kbms/findmodulebydomain"%>',{"domain":vm.domain},
						function(data){

                                 vm.modules=data;
                                 vm.module=data[0];                                         
								});
                    });
                     
				
			});
        </script>
</head>
<body>
<div style="width:1000px">
	<form id="form">
	    <div class="form-group">
            <label for="title">主题</label>
            <input type="text" class="form-control" id="title" v-model="title" >
        </div>
		<div class="form-group">
            <label for="resource">资料</label>
            <textarea   id="resource" v-model="resource" rows="20" style="width:100%"></textarea>
        </div>
        <div class="form-group">
        	<label class="control-label  col-md-3" for="select1">选择领域</label>
        	<div class="col-md-3">
        	<select id="select1" v-model="domain">
        	    <option v-for="item in domains">{{ item }}</option>
        	</select>
        	</div>
        	<label class="control-label  col-md-3" for="select2">选择模块</label>
        	<div class="col-md-3">
        	<select id="select2" v-model="module">
        	    <option v-for="item in modules">{{ item }}</option>
        	</select>
        	</div>       
        	
        </div>
        <div class="form-group" style="margin-top: 10px">
        <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="add">确认上传</button>	
        </div>
	</form>
</div>
<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</body>
</html>