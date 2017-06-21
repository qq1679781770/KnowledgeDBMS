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

				var resource_id=${resource_id};
				var resource='${resource}';
				var domain='${domain}';
				var module='${module}';
				var title='${title}';
				var attributes=${attributes};
				var domains=${domains};
                var modules=${modules};
                
				var vm=new Vue({
					el:'#vm',
					data:{
						title:title,
						resource:resource,
						attributes:attributes,
						domains:domains,
						domain:domain,
						modules:modules,
						module:module,
						addattributes:[],
						addattribute:'',
						value:'',
						modifyvalue:''
					},
					methods:{
						edit:function(item){
							$('#modify').click(function(){
								postJSON('<%=basePath%>'+'kbms/updateattributevalue',{"value":vm.modifyvalue,"id":item.id,"create_id":
									<%=session.getAttribute("user_id")%>},function(data){});
								location.assign("<%=basePath%>"+"kbms/showmodifyresource/"+resource_id);
							});
						},
						deleteitem:function(item){
							$('#delete').click(function(){
								getJSON('<%=basePath%>'+'kbms/deleterattribute/'+item.id,function(data){
	                                  location.assign("<%=basePath%>"+"kbms/showmodifyresource/"+resource_id);
	                                  });
								});							
						},
						modify:function(){
							postJSON('<%=basePath%>'+'kbms/updateresourcemodelanddomain',{"domain":this.domain,"module":this.module,
								"resource_id":resource_id,"create_id":<%=session.getAttribute("user_id")%>},function(data){
									location.assign("<%=basePath%>"+"kbms/showmodifyresource/"+resource_id);
								});
						},
						add:function(){
                               getJSON('<%=basePath%>'+'kbms/findaddattributes',{"resource_id":resource_id},function(data){
                                   vm.addattributes=data;
                                   });
                            
							$('#add').click(function(){
								postJSON('<%=basePath%>'+"kbms/addrattribute",{"value":vm.value,"create_id":<%=session.getAttribute("user_id")%>
								,"attribute":vm.addattribute,"resource_id":resource_id},function(data){
									location.assign("<%=basePath%>"+"kbms/showmodifyresource/"+resource_id);
								});
							});
					    }
						
					}
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

<div style="width:1200px;height: 600px" id="vm">
	<h3>{{ title }}</h3>
    <p>内容:{{ resource }}</p>
    <h5>属性</h5>
    <ul>
       <li v-for="item in attributes">{{ item.key }} :{{ item.value }}  操作:
       <a v-on:click="edit(item)" data-toggle="modal" data-target="#editmodal">编辑</a> 
       <a v-on:click="deleteitem(item)" data-toggle="modal" data-target="#deletemodal">删除</a>
       </li>
    </ul>
    <form>
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
        <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="modify">确认修改</button>	
        </div>
        <button type="button" class="btn btn-primary btn-lg btn-block" v-on:click="add" data-toggle="modal" data-target="#addmodal">添加属性</button>	
	</form>
	
	<div class="modal fade" id="deletemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
           确认删除
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="delete">确定</button>
      </div>
    </div>
  </div>
     </div>
     
      
     <div class="modal fade" id="addmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
            <form id="manageform">
   		<div class="form-group">
            <label for="select3">选择属性</label>
            <select  id="select3" class="form-control" v-model="addattribute" >
                 <option v-for="item in addattributes">{{ item }}</option>
            </select>
        </div>
        <div class="form-group">
            <label for="value">属性值</label>
            <input  v-model="value" class="form-control" id="value" type="text">
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="add">确定添加</button>
      </div>
    </div>
  </div>
     </div>
     
     <div class="modal fade" id="editmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
          <form id="modifyform">
   		<div class="form-group">
            <label for="modifyvalue">修改为</label>
            <input  v-model="modifyvalue" class="form-control" id="modifyvalue" type="text">
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="modify">确定</button>
      </div>
    </div>
  </div>
     </div>
     
	
</div>


</body>
</html>