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

                var checkresources=${uncheckresources};
                var page=${page};
                var totalpage=${totalpage};
   				
				var checkvm=new Vue({el:'#checkmodal',data:{score:''}});
				var lookvm=new Vue({el:'#lookmodal',data:{resource:''}});

				var vm=new Vue({
					el:'#vm',
					data:{
						page:page,
						totalpage:totalpage,
						checkresources:checkresources,
						topage:1
					},
					methods:{
						previous:function(){
                            if(this.page==1){
                                return;
                             }
                            this.page=this.page-1;
                            location.assign('<%=basePath+"kbms/searchuncheck/"%>'+this.page);
                        },
                        next:function(){
                       	 if(this.page==this.totalpage){
                                return;
                             }
                            this.page=this.page+1;
                            location.assign('<%=basePath+"kbms/searchuncheck/"%>'+this.page);
                        },
                        topagef:function(){
                            if(this.topage>this.totalpage||this.topage<1){
                                return;
                                }
                       	 location.assign('<%=basePath+"kbms/searchuncheck/"%>'+this.topage);
                        },
                        look:function(item){
                            lookvm.resource=item.resource;
                        },
                        check:function(item){
                            $('#check').click(function(){
                            	postJSON('<%=basePath+"kbms/checkresource"%>',{"check_id":item.id,"user_id":<%=session.getAttribute("user_id")%>
                                ,"score":checkvm.score},function(data){
                                	alert("评价成功");
                                	location.assign('<%=basePath+"kbms/searchuncheck/"%>'+vm.page);
                                	
                                    });
                                });
                            
                            
                         }
					}
			    });
		   });
				

        </script>

</head>
<body>

<div style="height:600px;width:1200px">
	<div id="vm">
		<table class="table table-bordered">
			<thead>
				<tr>
					<td>主题</td>
					<td>领域</td>
					<td>模块</td>
					<td>评价次数</td>
					<td>操作</td>
				</tr>
			</thead>
		    <tbody>
		    	<tr v-for="item in checkresources">
		    	   <td>	{{ item.title }}</td>
		    	   <td>{{ item.domain }}</td>
		    	   <td>{{  item.module }}</td>
		    	   <td>{{ item.check_time }}</td>
		    	   <td>
		    	      <a v-on:click="look(item)" data-toggle="modal" data-target="#lookmodal">查看</a>
	                  <a v-on:click="check(item)"  data-toggle="modal" data-target="#checkmodal">评分</a>
		    	   </td>
		    	</tr>
		    </tbody>
		</table>
		
		<nav aria-label="...">
          <ul class="pager">
              <li id="previous"><a v-on:click="previous">Previous</a></li>
              <li>当前第{{ page }}页</li>
              <li><input type="text" v-model="topage"></input></li>
              <li><button type="button" v-on:click="topagef" id="topagef">转到</button></li>
              <li>总共{{ totalpage }}页</li>
              <li id="next"><a v-on:click="next">Next</a></li>
         </ul>
       </nav>
		
	</div>
	
	<div class="modal fade" id="lookmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
           <p>{{ resource }}</p>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="delete">确定</button>
      </div>
    </div>
  </div>
     </div>
     
     <div class="modal fade" id="checkmodal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel">
       <div class="modal-dialog" role="document">
       <div class="modal-content">
      <div class="modal-header">
        <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">&times;</span></button>
      </div>
      <div class="modal-body">
          <form id="modifyform">
   		<div class="form-group">
            <label for="score">评价(100分制)</label>
            <input  v-model="score" class="form-control" id="score" type="text">
        </div>
        </form>
      </div>
      <div class="modal-footer">
        <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
        <button type="button" class="btn btn-primary" id="check">确定</button>
      </div>
    </div>
  </div>
     </div>
	
</div>
<script count="150" src=<%=basePath+ "js/canvas-nest.js"%>></script>
</body>
</html>