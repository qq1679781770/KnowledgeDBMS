<%@ page language="java" contentType="text/html;utf-8"
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
           function search(page){
                 if(vm.condition=='领域'){

                     getJSON('<%=basePath%>'+'searchresource/domain/'+page,{"domain":vm.attribute},function(data){                           
                            //console.log(data);
                            vm2.resources=data.resources;
                            if(vm2.resources.length>0){
                        	    vm2.is_show=true;
                             }else{
                                 alert("无记录");
                             }                    
                            vm2.totalpage=data.totalpage;
                            vm2.is_modify=data.is_modify;
                            vm2.is_delete=data.is_delete;
                         });
                  }

                 if(vm.condition=='模块'){
                	 getJSON('<%=basePath%>'+'searchresource/module/'+page,{"module":vm.attribute},function(data){                           
                         //console.log(data);
                         vm2.resources=data.resources;
                         if(vm2.resources.length>0){
                        	    vm2.is_show=true;
                             }else{
                                 alert("无记录");
                             }     
                     
                         vm2.totalpage=data.totalpage;
                         vm2.is_modify=data.is_modify;
                         vm2.is_delete=data.is_delete;
                      });

                 }
                if(vm.condition=='属性'){

                	getJSON('<%=basePath%>'+'searchresource/attribute/'+page,{"attribute":vm.attribute,"value":vm.value},function(data){                           
                        //console.log(data);
                        vm2.resources=data.resources;
                        if(vm2.resources.length>0){
                       	    vm2.is_show=true;
                            }else{
                                alert("无记录");
                            }     
                    
                        vm2.totalpage=data.totalpage;
                        vm2.is_modify=data.is_modify;
                        vm2.is_delete=data.is_delete;
                     });
                }
                 
           }
            
            var vm=new Vue({
                el:'#form',
                data:{
                    conditions:['模块','领域',"属性"],
                    condition:'领域',
                    attributes:[],
                    attribute:'',
                    value:''
                    
                },
                methods:{
                    search:function(){
                        search(1);
                    }
               }
            });


            getJSON('<%=basePath+"finddomains"%>',
					function(data){

                             vm.attributes=data;
                             vm.attribute=data[0];                                         
			});
            $('#select1').change(function(){

                if(vm.condition=='领域'){
                	getJSON('<%=basePath+"finddomains"%>',
    						function(data){

                                     vm.attributes=data;
                                     vm.attribute=data[0];                                         
    				});
                }
                if(vm.condition=='模块'){
                	getJSON('<%=basePath+"findmodules"%>',
    						function(data){

                                     vm.attributes=data;
                                     vm.attribute=data[0];                                         
    				});
                }
                if(vm.condition=='属性'){
                	getJSON('<%=basePath+"findattributes"%>',
    						function(data){

                                     vm.attributes=data;
                                     vm.attribute=data[0];                                         
    				});
                }
            });

            var vm2=new Vue({
                el:'#table',
                data:{
                    is_show:false,
                    is_modify:false,
                    is_delete:false,
                    resources:[{}],
                    page:1,
                    totalpage:1,
                    topage:1
                },
                methods:{
                     look:function(item){
          				location.assign('<%=basePath%>'+'lookresource/'+item.id);
                     },
                     modify:function(item){
                         //$('#myModal').show();
                     },
                     deleteresource:function(item){
                         $('#delete').click(function(){
                                     getJSON('<%=basePath%>'+'kbms/deleteresource/'+item.id,function(data){});
                                     alert("删除成功");
                                     search(vm2.page);
                             });
                     },
                     previous:function(){
                         if(this.page==1){
                             return;
                         }
                         this.page=this.page-1;
                         search(this.page);
                     },
                     next:function(){
                         if(this.page==this.totalpage){
                             return;
                         }
                         this.page=this.page+1;
                         search(this.page);
                     }
                }

                
            });
        });
        </script>
        
</head>
<body>
<div style="height:630px;width:1260px">
	<form id="form" class="form-inline">
		<div class="form-group" style="margin-left: 400px">
           <label for="select1">搜索条件</label>
           <select id="select1" v-model="condition">
           	   <option v-for="item in conditions">{{ item }}</option>
           </select>
        </div>
        <div class="form-group" style="margin-left: 20px">
           <label  for="select2">搜索属性</label>
           <select id="select2" v-model="attribute">
           	   <option v-for="item in attributes">{{ item }}</option>
           </select>
        </div>
        <div class="form-group" style="margin-left: 20px">
             <label  for="value">搜索值</label>
             <input type="text" class="form-control" id="value" v-model="value">
       </div>
        <button type="button" class="btn btn-default" v-on:click="search"><span class="glyphicon glyphicon-search" aria-hidden="true"></span></button>
	</form>
	
	<div id="table" v-if="is_show">
	 <table class="table table-bordered" >
	     <thead>
	     	<tr>
               <th>主题</th>
               <th>领域</th>
               <th>模块</th>
               <th>创建者</th>
               <th>最后修改时间</th>	
               <th>操作</th>     	
	     	</tr>
	     </thead>
	     <tbody>
	     <tr v-for="item in resources">
	     		<td>{{ item.title }}</td>
	            <td>{{ item.domain }}</td>
	            <td>{{ item.module }}</td>
	            <td>{{ item.create_id }}</td>
	            <td>{{ item.last_time }}</td>
	            <td>
	               <a v-on:click="look(item)">查看</a>
	               <a v-on:click="modify(item)" v-if="is_modify" >修改</a>
	               <a v-on:click="deleteresource(item)" v-if="is_delete" data-toggle="modal" data-target="#myModal">删除</a>
	            </td>
	     </tr>
	     </tbody>
	 </table>
	
	  <div class="modal fade" id="myModal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
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
	  <nav aria-label="...">
          <ul class="pager">
              <li id="previous"><a v-on:click="previous">Previous</a></li>
              <li>当前第{{ page }}页</li>
              <li><input type="text" v-model="topage"></input></li>
              <li><button type="button">转到</button></li>
              <li>总共{{ totalpage }}页</li>
              <li id="next"><a v-on:click="next">Next</a></li>
         </ul>
       </nav>
	</div>
	
	
</div>

</body>
</html>