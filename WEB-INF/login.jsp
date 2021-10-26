<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ page session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">
 
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<meta http-equiv="imagetoolbar" content="no" />
<title>The Oasis</title>
<link rel="stylesheet" type="text/css" href="css/core2.css" />
<style type="text/css">
   .nav02 #stores{
   position : relative;
   padding-top: 1000px;
   margin-top:1000px;
   margin-left: 500px;
   }
	</style>
<script type="text/javascript">
	var _gaq = _gaq || [];
	_gaq.push([ '_setAccount', 'UA-8670534-5' ]);
	_gaq.push([ '_trackPageview' ]);

	(function() {
		var ga = document.createElement('script');
		ga.type = 'text/javascript';
		ga.async = true;
		ga.src = ga.js;
		var s = document.getElementsByTagName('script')[0];
		s.parentNode.insertBefore(ga, s);
	})();
	function Login() {
		if (id.value.length == 0) {
			alert("이메일 주소를 입력해주세요");
			document.getElementById("id").focus();
			return false;
		} else if ((password.value.length < 4) || (password.value.length > 8)) {
			alert("비밀번호는 4자 이상 8자 이하로 입력해주세요");
			document.getElementById("password").focus();
			return false;
		}
	}
	var check=0;
	function CheckId()
	 {
		//var id = prompt("사용할 아이디를 입력하세요","");
		var id = document.getElementById("idf").value;
		if(id.length==0)
	     {
	     return ;
	     }
	  $.ajax({
	   url:'join.oasis',
	   type:'POST',
	   data:"command="+id,
	   dataType:'text',
	   
	   success:function(msg){
	   if(msg=='fail'){
		   alert("이미 존재하는 아이디 입니다.");
	   }
	   else{
		   alert("사용가능한 아이디입니다.");
		   check=1;
		   document.getElementById("idf").innerHTML=id;
	   	}
	   }
	  });
	  return false;
	 }
		//   alert
	function Join() {
		if ((document.getElementById("idf").value.length < 6)
				|| (document.getElementById("idf").value.length > 30)){
			alert("아이디는 6자에서 30자 이내로 입력해주세요"); //골뱅이입력
			return false;
		}
		if(check==0){
			alert("아이디 중복 확인을 해주세요.");
			return false;
		}
		else if ((document.getElementById("passwordf").value.length < 5)
				|| (document.getElementById("passwordf").value.length > 14)){
			alert("비밀번호는 6자에서 14자 이내로 입력해주세요");
			return false;
	   }
		else if (document.getElementById("passwordf").value != document
				.getElementById("password2").value){
			alert("비밀번호가 잘못 입력되었습니다.");
		return false;
	    }
		else if (document.getElementById("namef").value.length == 0){
			alert("이름을 입력해주세요");
			return false;
		}
		else if ((document.getElementById("birthday1").value.length ==0)
				||(document.getElementById("birthday2").value.length ==0)||
				(document.getElementById("birthday3").value.length ==0)){
			alert("생년월일을 확인해주세요");
			return false;
		}
		else if (isNaN(document.getElementById("birthday1").value)
				|| isNaN(document.getElementById("birthday2").value)
				|| isNaN(document.getElementById("birthday3").value)){
			alert("생년월일은 숫자만 입력 가능합니다");
			return false;
		}
		else if ((document.getElementById("birthday1").value.length > 2013)
				||(document.getElementById("birthday2").value.length >12)||
				(document.getElementById("birthday3").value.length >31)){
			alert("잘못된 생년월일입니다.");
			return false;
		}
		else if (isNaN(document.getElementById("phone1").value)
				|| isNaN(document.getElementById("phone2").value)
				|| isNaN(document.getElementById("phone3").value)){
			alert("전화번호는 숫자만 입력 가능합니다");
			return false;
		}
		else if ((document.getElementById("phone1").value.length != 3)
				|| (document.getElementById("phone2").value.length != 4)
				|| (document.getElementById("phone3").value.length != 4)){
			alert("전화번호를 확인해주세요");
			return false;
		}
		else if (document.getElementById("question").value.length == 0){
			alert("질문을 입력해주세요");
			return false;
		}
		else if (document.getElementById("answer").value.length == 0){
			alert("답변을 입력해주세요");
			return false;
		}
		else
			return true;
	}
</script>
<script type="text/javascript" src="js/jquery.js"></script>
</head>

<body>

	<div id="wrap">

		<h1>
			The Oasis<span></span>
		</h1>

		<div id="container">

			<div id="inner">

				<div class="level1" id="nav01">
					<ul>
						<li class="navStores"><a id="n01" href="#stores"><span>Stores</span>
						</a></li>
						<li class="navProducts"><a id="n02" href="#products"><span>Products</span>
						</a></li>
					</ul>
				</div>
				<!-- /nav01 -->

				<div id="content">


					<div id="level2">
						<form:form name="login" modelAttribute="user">
							<div class="nav02" id="stores">
								<table>
									<tr>
										<th>ID</th>
										<td><form:input path="id" cssClass="id" /> <font
											color="red">
											<form:errors path="id" /></font></td>
									</tr>
									<tr>
										<th>Password</th>
										<td><form:password path="password" cssClass="password" />
											<font color="red">
											<form:errors path="password" /></font></td>
									</tr>
									<tr>
										<td colspan="2"><input type="submit" value="Login"
											onclick="Login()" />
										</td>
									</tr>
								</table>
							</div>
						</form:form>
						<!-- /stores -->

						<form onsubmit="return Join()" method="post" action="join.oasis">
						<div class="nav02" id="products">
							<table>
								<tr>
									<th>ID</th>
									<td><input type="text" name="idf" id="idf" maxlength="30"
										size="30" onfocus="check()"/>
									</td>
									<td><input type="button" name="check" value="중복체크" onclick="CheckId()"></input></td>
								</tr>
								
								<tr>
									<th>비밀번호</th>
									<td><input type="password" name="passwordf" id="passwordf"
										maxlength="14" />(6자~14자)</td>
								</tr>
								<tr>
									<th>비밀번호 확인</th>
									<td><input type="password" name="password2" id="password2"
										maxlength="10" /></td>
								</tr>
								<tr>
									<th>이름</th>
									<td><input type="text" name="namef" id="namef" /></td>
								</tr>
								
								<tr>
									<th>생일</th>
									<td><input type="text" name="birthday1" id="birthday1"
										maxlength="4" size="4" />/
										<input type="text"
										name="birthday2" id="birthday2" maxlength="2" size="2" />/<input type="text"
										name="birthday3" id="birthday3" maxlength="2" size="2" /></td>
								</tr>
								
								<tr>
									<th>전화번호</th>
									<td><input type="text" name="phone1" id="phone1"
										maxlength="3" size="4" /> - <input type="text" name="phone2"
										id="phone2" maxlength="4" size="4" />- <input type="text"
										name="phone3" id="phone3" maxlength="4" size="4" /></td>
								</tr>
								<tr>
									<th>질문</th>
									<td><input type="text" name="question" id="question" />
									</td>
								</tr>
								<tr>
									<th>답변</th>
									<td><input type="text" name="answer" id="answer" />
									</td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" value="등록" />
										 <input type="reset" value="취소"
										onclick="Clear()" />
									</td>
								</tr>
							</table>
						</div>
						</form>
						<!-- /products -->
					</div>
					<!-- /level2 -->

				</div>
				<!-- /content -->
			</div>
			<!-- /inner -->
		</div>
		<!-- /container -->

		<div id="background">
			<div id="bgImg">
				<div class="bgGroup" id="homeSet">
					<img id="image01" src="logo2.png" alt="" />
					<!-- 로고부분 -->
				</div>
				<div class="bgGroup" id="subSet"></div>
			</div>
		</div>
		<!-- /background -->
	</div>
	<!-- /wrap -->

	<div id="footer">

		<ul>
			<li><a href="http://www.jamesrichardson.com.au"><span>Copyright
						The Oasis 2013 
			</a></span></li>
			<li><a href="#">Contact Us</a></li>
		</ul>
	</div>
	<!-- /footer -->

	<script type="text/javascript" src="js/cufon.js"></script>
	<script type="text/javascript" src="js/jquery.cycle.all.min.js"></script>
	<script type="text/javascript" src="js/main.js"></script>

</body>
</html>

