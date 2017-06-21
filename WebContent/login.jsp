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
<title>登录</title>

        <meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jsxnh.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <script type="text/javascript">

        $(document).ready(function(){

        	$('#user_idlabel').hide();
        	 $('#user_id').mouseleave(function(){
                 if( $(this).val()=="")
                      return;
                 var num=$(this).val();
                 if(!isNaN(num)){                                        
                         if($('#user_id').parent().hasClass('has-error'))
                             $('#user_id').parent().removeClass('has-error');
                         $('#user_idspan').addClass('glyphicon-ok');
                         $('#user_id').parent().addClass('has-success');
                         if($('#user_idspan').hasClass('glyphicon-remove'))
                             $('#user_idspan').removeClass('glyphicon-remove');
                         $('#user_idspan').addClass('glyphicon-ok');
                         $('#user_idlabel').hide();
                 }else{
                     if($(this).parent().hasClass('has-ok'))
                           $(this).parent().removeClass('has-ok');
                     $(this).parent().addClass('has-error');
                     if($('#user_idspan').hasClass('glyphicon-ok'))
                          $('#user_idspan').removeClass('glyphicon-ok');
                     $('#user_idspan').addClass('glyphicon-remove');
                     $('#user_idlabel').show();
                 }
             });

        	 $('#password1').mouseleave(function(){
                 if( $(this).val()=="")
                      return;
                 $(this).parent().addClass('has-success');
                 $('#password1span').addClass('glyphicon-ok');
             });

        	 $('#login').click(function(){
                 var user_id=$('#user_id').val();          
                 var password=$('#password1').val();
                 getJSON('kbms/login',{"id":user_id,"password":password},function(data){
                              if(data=='Y'){
                                  alert("登录成功");
                            	  var url='<%=basePath%>';
                              	  location.assign(url);
                              }else{
                                  alert("不存在用户或密码错误");
                                  var url=location.pathname;
                             	  location.assign(url);
                              }
                              
                         });
                  
            });
        });

        </script>
</head>
<body>
<div class="container">
         <div class="alert alert-danger text-center" role="alert">           
               <h3>登录</h3>
         </div>
          <div class="row">
            <div class="col-md-12">
                <div>
                    <table   frame=lhs>
                         <tr>
                            <td><h2 class="text-primary"><em><a href="register">注册</a></em></h2></td>
                            <td style="margin-left: 30px"><h2 class="text-primary"><em><a href="login">登录</a></em></h2></td>
                        </tr>
                    </table>
                </div>
               <div style="margin:10px 0 0 0">
                   <form class="form-horizontal">
                       <div class=" form-group has-feedback">
                           <label class="control-label  col-sm-3" for="user_id">用户id</label>
                           <div class="col-sm-6 ">
                               <input type="text" class="form-control"  id="user_id" placeholder="请输入ID"/>
                               <span class="glyphicon  form-control-feedback" id="user_idspan" aria-hidden="true"></span>
                           </div>
                           <label class="col-sm-3 control-label" id="user_idlabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>用户id为数字</label>
                       </div>
                       <div class=" form-group has-feedback">
                           <label class="control-label  col-sm-3" for="password1">密码</label>
                           <div class="col-sm-6 ">
                               <input type="password" class="form-control"  id="password1" placeholder="输入密码"/>
                               <span class="glyphicon form-control-feedback" id="password1span"aria-hidden="true"></span>
                           </div>
                       </div>
                       
                       <div class="col-sm-6 col-sm-offset-3">
                           <button type="button" class="btn btn-primary btn-lg btn-block" id="login">立即登录</button>
                       </div>
                   </form>
                </div>
            </div>
          </div>
</div>
<script count="300" src="js/canvas-nest.js"></script>            

</body>
</html>