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
                var users=${users};
                var is_modify=${is_modify};
                var is_manage=${is_manage};
                var is_delete=${is_delete};
                var is_show=is_delete|is_manage|is_modify;
                var totalpage=${totalpage};
				var page=${page};
				
				var vm=new Vue({
					el:'#vm',
					data:{
						users:users,
						page:page,
						totalpage:totalpage,
						is_show:is_show,
						is_manage:is_manage,
						is_modify:is_modify,
						is_delete:is_delete,
						topage:1
				    },
				    methods:{
					    manage:function(user){

					    	
					    	$('#select2').change(function(){
								if(managevm.manage=='增加'){
									getJSON('<%=basePath%>'+'kbms/findadduserauthority',{"user_id":user.id},function(data){managevm.authorities=data;});
								}else{
									getJSON('<%=basePath%>'+'kbms/finddeleteuserauthority',{"user_id":user.id},function(data){managevm.authorities=data;});
								}
							});

							$('#manage').click(function(){
								if(managevm.manage=='增加'){
									postJSON('<%=basePath%>'+'kbms/adduserauthority',{"create_id":<%=session.getAttribute("user_id")%>
									,"user_id":user.id,"authority":managevm.authority},function(data){alert("增加成功");});
							   }else{
								   postJSON('<%=basePath%>'+'kbms/deleteuserauthority',{"create_id":<%=session.getAttribute("user_id")%>
									,"user_id":user.id,"authority":managevm.authority},function(data){alert("删除成功");});
								 }
							});
							
						 },
                        modify:function(user){
                        	modifyvm.grade=user.grade;
                            getJSON('<%=basePath+"kbms/listallgrade"%>',function(data){
                                modifyvm.grades=data;
                             });
                            $('#modify').click(function(){
                                getJSON('<%=basePath+"kbms/updategrade"%>',{"user_id":user.id,"grade":modifyvm.grade},function(data){alert("修改成功");});
                             });
                         },
                         deleteuser:function(user){
                             $('#delete').click(function(){
                                     getJSON('<%=basePath%>'+'kbms/deleteuser/'+user.id,function(){
                                            location.assign('<%=basePath+"kbms/showalluser/1"%>');
                                         });
                                 });
                         },
                         previous:function(){
                             if(this.page==1){
                                 return;
                              }
                             this.page=this.page-1;
                             location.assign('<%=basePath+"kbms/showalluser/"%>'+this.page);
                         },
                         next:function(){
                        	 if(this.page==this.totalpage){
                                 return;
                              }
                             this.page=this.page+1;
                             location.assign('<%=basePath+"kbms/showalluser/"%>'+this.page);
                         },
                         topagef:function(){
                             if(this.topage>this.totalpage||this.topage<1){
                                 return;
                                 }
                        	 location.assign('<%=basePath+"kbms/showalluser/"%>'+this.topage);
                         }
						 
				    }
				});



				//$('#next').click(function(){
				//	if(vm.page==vm.totalpage){
                //        return;
               //      }
               //     vm.page=vm.page+1;
                
				//});

		         //$('#priev')
				
				
				var modifyvm=new Vue({
					el:'#modifyform',
					data:{
						grade:'',
				        grades:[]
				    }

					});

				var managevm=new Vue({
                    el:'#manageform',
                    data:{
                        manages:["增加","删除"],
                        manage:'增加',
                        authorities:[],
                        authority:''
                        }
					});


				
			});
        </script>
</head>
<body>
<div style="height:600px;width:1250px">

<div><h3 class="text-center">用户管理</h3></div>
<div id="vm">
	<table class="table table-bordered">
		<thead>
			<tr>
				<td>账号</td>
				<td>姓名</td>
				<td>等级</td>
				<td>领域</td>
				<td v-if="is_show">操作</td>
			</tr>
		</thead>
		<tbody>
		<tr v-for="item in users">
			<td>{{ item.id }}</td>
			<td>{{ item.name }}</td>
			<td>{{ item.grade }}</td>
			<td>{{ item.domain }}</td>
			<td>
	            <a v-on:click="manage(item)" v-id="is_manage" data-toggle="modal" data-target="#managemodal">管理权限</a>
	            <a v-on:click="modify(item)" v-if="is_modify" data-toggle="modal" data-target="#modifymodal">修改等级</a>
	            <a v-on:click="deleteuser(item)" v-if="is_delete" data-toggle="modal" data-target="#deletemodal">删除用户</a>
	       </td>
		</tr>
		</tbody>
	</table>
	
	
	
	<nav aria-label="...">
          <ul class="pager">
              <li id="previous"><a v-on:click="previous">Previous</a></li>
              <li>当前第{{ page }}页</li>
              <li><input type="text" v-model="topage"></input></li>
              <li><button type="button" v-on:click="topagef" id="topage">转到</button></li>
              <li>总共{{ totalpage }}页</li>
              <li id="next"><a v-on:click="next">Next</a></li>
         </ul>
       </nav>
	
</div>
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
     
     <div class="modal fade" id="managemodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
            <form id="manageform">
   		<div class="form-group">
            <label for="select2">选择操作</label>
            <select  id="select2" class="form-control" v-model="manage" >
                 <option v-for="item in manages">{{ item }}</option>
            </select>
        </div>
        <div class="form-group">
            <label for="select3">选择权限</label>
            <select  id="select3" class="form-control" v-model="authority" >
                 <option v-for="item in authorities">{{ item }}</option>
            </select>
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="manage">确定</button>
      </div>
    </div>
  </div>
     </div>
     
     <div class="modal fade" id="modifymodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
          <form id="modifyform">
   		<div class="form-group">
            <label for="select1">选择等级</label>
            <select  id="select1" class="form-control" v-model="grade" >
                 <option v-for="item in grades">{{ item }}</option>
            </select>
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