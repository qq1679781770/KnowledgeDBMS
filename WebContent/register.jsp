<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; utf-8">
<title>注册</title>
<meta name="viewport" content="width=device-width, initial-scale=1">
        <script src="js/jquery.min.js"></script>
        <script src="js/bootstrap.min.js"></script>
        <script src="js/jsxnh.js"></script>
        <script src="js/vue.min.js"></script>
        <link rel="stylesheet" href="css/bootstrap.min.css" />
        <style>
           body{
             filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=red,endcolorstr=blue,gradientType=0);
               -ms-filter:alpha(opacity=100 finishopacity=50 style=1 startx=0,starty=0,finishx=0,finishy=150) progid:DXImageTransform.Microsoft.gradient(startcolorstr=red,endcolorstr=blue,gradientType=0);/*IE8*/	
               background:#00BFFF; /* 一些不支持背景渐变的浏览器 */  
               background:-moz-linear-gradient(top, #00BFFF,white);  
               background:-webkit-gradient(linear, 0 0, 0 bottom, from(#00BFFF), to(white));  
               background:-o-linear-gradient(top, #00BFFFF,white); 
             }
        </style>
<script type="text/javascript">


    
    

	$(document).ready(function(){

        

		
		function initdomain(domains){
        var vm=new Vue({
            el:'#domainselect',
            data:{
                domains:domains,
                domain:domains[0]
             }
        });}

        var domains=${domains};
        initdomain(domains);
		
		$('#user_idlabel').hide();
        $('#agelabel').hide();
        $('#tellabel').hide();
        $('#emaillabel').hide();
        $('#password2label').hide();
        $('#agree').prop("checked",true);
        $('#user_id').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            var num=$(this).val();
            if(!isNaN(num)){
                 
                 getJSON('finduser',{user_id:num},function(data){
                     res=data;
                 if(res=='Y'){
                	if($(this).parent().hasClass('has-success'))
                         $(this).parent().removeClass('has-success');
                    if($('#user_id').parent().hasClass('has-ok'))
                        $('#user_id').parent().removeClass('has-ok');
                    $('#user_id').parent().addClass('has-error');
                    if($('#user_idspan').hasClass('glyphicon-ok'))
                        $('#user_idspan').removeClass('glyphicon-ok');
                    $('#user_idspan').addClass('glyphicon-remove');
                    $('#user_idlabel').text('用户名已有');
                    $('#user_idlabel').show();
                 }
                 else{
                    if($('#user_id').parent().hasClass('has-error'))
                        $('#user_id').parent().removeClass('has-error');
                    $('#user_idspan').addClass('glyphicon-ok');
                    $('#user_id').parent().addClass('has-success');
                    if($('#user_idspan').hasClass('glyphicon-remove'))
                        $('#user_idspan').removeClass('glyphicon-remove');
                    $('#user_idspan').addClass('glyphicon-ok');
                    $('#user_idlabel').hide();
                }
                 });
            }else{
                if($(this).parent().hasClass('has-ok'))
                      $(this).parent().removeClass('has-ok');
                $(this).parent().addClass('has-error');
                if($('#user_idspan').hasClass('glyphicon-ok'))
                     $('#user_idspan').removeClass('glyphicon-ok');
                $('#user_idspan').addClass('glyphicon-remove');
                $('#user_idlabel').text('id必须为数字');
                $('#user_idlabel').show();
            }
        });
        $('#password1').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            $(this).parent().addClass('has-success');
            $('#password1span').addClass('glyphicon-ok');
        });
        $('#password2').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            if($(this).val()==$('#password1').val()){
                 if($(this).parent().hasClass('has-error'))
                      $(this).parent().removeClass('has-error');
                 $(this).parent().addClass('has-success');
                 if($('#password2span').hasClass('glyphicon-remove'))
                     $('#password2span').removeClass('glyphicon-remove');
                 $('#password2span').addClass('glyphicon-ok');
                 $('#password2label').hide();
            }else{
                 if($(this).parent().hasClass('has-success'))
                      $(this).parent().removeClass('has-success');
                 $(this).parent().addClass('has-error');
                 if($('#password2span').hasClass('glyphicon-ok'))
                     $('#password2span').removeClass('glyphicon-ok');
                 $('#password2span').addClass('glyphicon-remove');
                 $('#password2label').show();
            }
        });
        $('#name').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            $(this).parent().addClass('has-success');
            $('#namespan').addClass('glyphicon-ok');
        });
        $('#age').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            var num=$(this).val();
            if(!isNaN(num)){
                if($(this).parent().hasClass('has-error'))
                      $(this).parent().removeClass('has-error');
                $(this).parent().addClass('has-success');
                if($('#agespan').hasClass('glyphicon-remove'))
                     $('#agespan').removeClass('glyphicon-remove');
                $('#agespan').addClass('glyphicon-ok');
                $('#agelabel').hide();
            }else{
                if($(this).parent().hasClass('has-success'))
                      $(this).parent().removeClass('has-success');
                $(this).parent().addClass('has-error');
                if($('#agespan').hasClass('glyphicon-ok'))
                     $('#agespan').removeClass('glyphicon-ok');
                $('#agespan').addClass('glyphicon-remove');
                $('#agelabel').show();
           }
        });

        $('#tel').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            if(!$(this).val().match(/^1[3|4|5|8][0-9]\d{4,8}$/)){
              if($(this).parent().hasClass('has-success'))
                    $(this).parent().removeClass('has-success');
              $(this).parent().addClass('has-error');
              if($('#telspan').hasClass('glyphicon-ok'))
                   $('#telspan').removeClass('glyphicon-ok');
              $('#telspan').addClass('glyphicon-remove');
              $('#tellabel').show();
            }else{
            	 if($(this).parent().hasClass('has-error'))
                     $(this).parent().removeClass('has-error');
               $(this).parent().addClass('has-success');
               if($('#telspan').hasClass('glyphicon-remove'))
                    $('#telspan').removeClass('glyphicon-remove');
               $('#telspan').addClass('glyphicon-ok');
               $('#tellabel').hide();
            }
        });

        $('#email').mouseleave(function(){
            if( $(this).val()=="")
                 return;
            if(!$(this).val().match(/^\w+((-\w+)|(\.\w+))*\@[A-Za-z0-9]+((\.|-)[A-Za-z0-9]+)*\.[A-Za-z0-9]+$/)){
                if($(this).parent().hasClass('has-success'))
                      $(this).parent().removeClass('has-success');
                $(this).parent().addClass('has-error');
                if($('#emailspan').hasClass('glyphicon-ok'))
                     $('#emailspan').removeClass('glyphicon-ok');
                $('#emailspan').addClass('glyphicon-remove');
                $('#emaillabel').show();
              }else{
              	 if($(this).parent().hasClass('has-error'))
                       $(this).parent().removeClass('has-error');
                 $(this).parent().addClass('has-success');
                 if($('#emailspan').hasClass('glyphicon-remove'))
                      $('#emailspan').removeClass('glyphicon-remove');
                 $('#emailspan').addClass('glyphicon-ok');
                 $('#emaillabel').hide();
              }
        });
        $('#agree').click(function(){
            if($(this).prop("checked") == true){
                 $('#register').removeAttr('disabled');
            }
            else{
                 $('#register').attr('disabled',true);
            }
       });
        $('#register').click(function(){
            if($('#password2span').hasClass('glyphicon-remove')||$('#user_idspan').hasClass('glyphicon-remove')
                    ||$('#agespan').hasClass('glyphicon-remove')){
                    alert('请正确填写');
                    return;
                }
            var user_id=parseInt($('#user_id').val());          
            var password=$('#password2').val();
            var tel=$('#tel').val();         
            var age=parseInt($('#age').val());
            var email=$('#email').val();
            var name=$('#name').val();
            var sex=parseInt($('#sex').val());
            var domain=$('#select').val();
            
            postJSON('kbms/register',{"id":user_id,"name":name,"password":password,
                "tel":tel,"email":email,"age":age,"domain":domain,"sex":sex},function(){
                         alert('注册成功');
                         var url=location.pathname;
                     	  location.assign(url);
                    });
             
       });
       

        
	})


</script>

</head>
<body>
<div class="container">
        <div class="alert alert-danger text-center" role="alert">           
               <h3>欢迎注册</h3>
         </div>
         <div class="row">
            <div class="col-md-12">
                <div>
                    <table  frame=lhs>
                        <tr>
                            <td><h2 class="text-primary"><em><a href="register">注册</a></em></h2></td>
                            <td><h2 class="text-primary"><em><a href="login">登录</a></em></h2></td>
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
                           <label class="col-sm-3 control-label" id="user_idlabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>用户名已有</label>
                       </div>
                       <div class=" form-group has-feedback">
                           <label class="control-label  col-sm-3" for="password1">密码</label>
                           <div class="col-sm-6 ">
                               <input type="password" class="form-control"  id="password1" placeholder="输入密码"/>
                               <span class="glyphicon form-control-feedback" id="password1span"aria-hidden="true"></span>
                           </div>
                       </div>
                       <div class=" form-group  has-feedback">
                           <label class="control-label  col-sm-3" for="password2">确认密码</label>
                           <div class="col-sm-6 ">
                               <input type="password" class="form-control"  id="password2" placeholder="确认密码"/>
                               <span class="glyphicon  form-control-feedback" id="password2span"aria-hidden="true"></span>
                           </div>
                           <label class="col-sm-3 control-label" id="password2label"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>输入密码不一致</label>
                       </div>
                       <div class=" form-group  has-feedback">
                           <label class="control-label  col-sm-3" for="age">年龄</label>
                           <div class="col-sm-6 ">
                               <input type="text" class="form-control"  id="age" placeholder="请输入年龄"/>
                               <span class="glyphicon  form-control-feedback" id="agespan" aria-hidden="true"></span>
                           </div> 
                            <label class="col-sm-3 control-label" id="agelabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>填写数字</label>                         
                       </div>
                       <div class=" form-group  has-feedback">
                           <label class="control-label  col-sm-3" for="name">姓名</label>
                           <div class="col-sm-6 ">
                               <input type="text" class="form-control"  id="name" placeholder="请输入真实姓名"/>
                               <span class="glyphicon  form-control-feedback" id="namespan" aria-hidden="true"></span>
                           </div>
                       </div>
                       <div class=" form-group has-feedback">
                           <label class="control-label  col-sm-3" for="tel">手机</label>
                           <div class="col-sm-6 ">
                               <input type="text" class="form-control"  id="tel" placeholder="请输入tel"/>
                               <span class="glyphicon  form-control-feedback" id="telspan" aria-hidden="true"></span>
                           </div>
                           <label class="col-sm-3 control-label" id="tellabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>填写正确格式</label> 
                       </div>
                       <div class=" form-group has-feedback">
                           <label class="control-label  col-sm-3" for="email">email</label>
                           <div class="col-sm-6 ">
                               <input type="text" class="form-control"  id="email" placeholder="请输入email"/>
                               <span class="glyphicon  form-control-feedback" id="emailspan" aria-hidden="true"></span>
                           </div>
                           <label class="col-sm-3 control-label" id="emaillabel"><span class="glyphicon glyphicon-exclamation-sign" aria-hidden="true"></span>填写正确格式</label> 
                       </div>
                       <div class=" form-group has-feedback">
                            <label class="control-label  col-sm-3" for="">性别</label>
                            <div class="col-sm-6">
                       		<label class="radio-inline">
                             	<input type="radio" name="inlineRadioOptions" id="sex" value="1"> 男
                       	 	</label>
                       	 	<label class="radio-inline">
                             	<input type="radio" name="inlineRadioOptions" id="sex" value="2"> 女
                       	 	</label>
                       	 	</div>
                        </div>
                       <div class=" form-group has-feedback" id="domainselect">
                       		 <label class="control-label  col-sm-3" for="">领域</label>
                       		 <div class="col-sm-6 ">
                               <select class="form-control" v-model="domain" id="select">
                               		<option v-for="item in domains">{{ item }}</option>
                               </select>
                           </div>
                       </div>
                       
                       
                       <div class="col-sm-6 col-sm-offset-3">
                           <label>
                               <input type="checkbox" id="agree" checked="checked"/>我已阅读并同意相关服务条款和隐私政策
                           </label>
                       </div>
                       <div class="col-sm-6 col-sm-offset-3">
                           <button type="button" class="btn btn-primary btn-lg btn-block" id="register">立即注册</button>
                       </div>
                   </form>
               </div>
            </div>

        </div>

</div>
 <script count="300" src="js/canvas-nest.js"></script>
</body>
</html>