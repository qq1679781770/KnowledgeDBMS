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
<title>资源库系统</title>
<script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jsxnh.js"></script>
        <script src="js/vue.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <style type="text/css">
        ul li{
           list-style-type:none;
        }
        </style>
        
        <script type="text/javascript">
			$(document).ready(function(){

				
                 getJSON('<%=basePath+"resourcenum"%>',function(data){
                     $('#domainnum').text("已有"+data+"篇条目");
                 });
                 <% if(session.getAttribute("user_id")==null){ %>
 				$('#register').show();
 				$('#login').show();
 				$('#user').text(' 未登录');
 				if($('#userli').hasClass("col-md-8")){
 					$('#userli').removeClass("col-md-8")
 	 			}
 				$('#userli').addClass("col-md-5");
 				$('#logout').hide();
 			  <% } else{%>
 			    var user=${sessionScope.user_id};
                 $('#user').text("  已登录用户  " +user);
                 if($('#userli').hasClass("col-md-5")){
  					$('#userli').removeClass("col-md-5")
  	 			}
                 $('#userli').addClass("col-md-8");
                 $('#register').hide();
 				$('#login').hide();
 				$('#logout').show();
 			  <% } %>

                $('#logout').click(function(){
                    getJSON("kbms/logout",function(){
                      var url=location.pathname;
                   	  location.assign(url);
                    })
                });
 			  

				$('#modifymessage').click(function(){
                    if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
					$('#iframe').attr("src","kbms/modifymessage");
				});

				$('#lookmessage').click(function(){
					 if($('#user').text()==' 未登录'){
	                        alert("请先登录");
	                        return;
	                    }
					 $('#iframe').attr("src","kbms/lookmessage");
		        });


	 			$('#addresource').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showaddresource");
		 	    });

	 			$('#uploadresource').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showuploadresource");
		 	    });

	 			$('#searchresource').click(function(){
	 				$('#iframe').attr("src","searchresource");
		 	    });

	 			$('#adddomain').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showadddomain");
		 	    });

	 			$('#modifydomain').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showmodifydomain");
		 	    });

	 			$('#addmodule').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showaddmodule");
		 	    });

	 			$('#modifymodule').click(function(){
	 				if($('#user').text()==' 未登录'){
                        alert("请先登录");
                        return;
                    }
	 				$('#iframe').attr("src","kbms/showmodifymodule");
		 	    });
		 	    
			});
			
        </script>
        
</head>
<body>
<div class="row">
	<div  class="col-lg-2" >
	<div style="background-color:#F0F0F0;height:50px;width:150px;margin-left:50px;"></div>
	<div style="background-color:#FFFFFF;width:150px;margin-left:50px;
	       border-left-style: solid;border-right-style: solid;border-left-color: #F0F0F0;border-right-color: #F0F0F0;border-top-color: #F0F0F0;
	       border-top-style: solid;">
	    <h5>个人信息管理</h5>
	    <div>
		<ul>
			<li><a href="javascript:(0)" id="modifymessage">信息修改</a></li>
			<li><a href="javascript:(0)" id="lookmessage">查看信息</a></li>
		</ul>
		</div>
		
		<h5>资料管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)" id="searchresource">搜索</a></li>
			<li><a href="javascript:(0)" id="uploadresource">上传</a></li>
			<li><a href="javascript:(0)" id="addresource">添加</a></li>
			<li><a href="javascript:(0)">审核</a></li>
			</ul>
		</div>
		
		<h5>领域管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)" id="adddomain">添加</a></li>
			<li><a href="javascript:(0)" id="modifydomain">修改</a></li>
			</ul>
		</div>
		
		<h5>模块管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)" id="addmodule">添加</a></li>
			<li><a href="javascript:(0)"  id="modifymodule">修改</a></li>
			</ul>
		</div>
		
		<h5>权限管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)">所有权限</a></li>
			<li><a href="javascript:(0)">所有等级</a></li>
			<li><a href="javascript:(0)">权限和等级</a><li>
			</ul>
		</div>
		
		<h5>用户管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)">所有用户</a></li>
			</ul>
		</div>
		
		<h5>属性管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)">添加</a></li>
			<li><a href="javascript:(0)">修改</a></li>
			<li><a href="javascript:(0)">查看</a></li>
			</ul>
		</div>
		
		<h5>关系管理</h5>
		<div>
			<ul>
			<li><a href="javascript:(0)">添加</a></li>
			<li><a href="javascript:(0)">修改</a></li>
			<li><a href="javascript:(0)">查看</a></li>
			</ul>
		</div>
		
		
	</div>
	
	</div>
	<div class="col-lg-10">
	    <div class="col-md-3 col-md-offset-9 nav">
		<ul>
			<li class="col-md-5 col-md-offset-1 " id="userli"><span class="glyphicon glyphicon-user" aria-hidden="true" id="user"> 未登录</span></li>
			<li class="col-md-3" id="register"><a href="register" >注册</a></li>
			<li class="col-md-3" id="login"><a href="login" >登录</a></li>
			<li class="col-md-3" id="logout"><a href="javascript:(0)">登出</a></li>
		</ul>
		</div>
		<div  style="height: 200px;margin-top: 30px;background-color:#F8F8F8" >
		    <br>
			<p><h2 class="text-center" style="font-style: blue">知识库管理系统</h2></p>
			<p class="text-center" >用户可自行添加，修改资源</p>
			<p class="text-center" >海纳百川，有容乃大</p>
			<p class="text-center" id="domainnum"></p>
			<p class="text-right"><a  href="hanpl">分词系统</a></p>
		</div>
		
		<div id="manage" style="margin-top: 20px">
		  <iframe src="" style="height:630px;width:100%" id="iframe"  frameborder=0></iframe>
		</div>
	</div>
</div>
</body>
</html>