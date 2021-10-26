<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="myTags" uri="simpleTags"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>

<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css"
	href="css/stylesheet_52_1301838816.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_50_1303205116.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_51_1301838195.css" media="all" />
<link rel="stylesheet" type="text/css" href="css/jisu.css" media="all" />

<script type="text/javascript" src="js/script.js"></script>
<script src="js/jquery.cycle.all.min.js"></script>
<script src="js/jquery.poshytip.min.js"></script>
<script src="js/jisu.js"></script>


<link rel="stylesheet" href="css/tip-twitter.css" />

<script type="text/javascript">
function AjaxTest(id, bbsNo, writer,isGood)
{
	$.ajax({
		   url:'CheckAgree.do',
		   type:'POST',
		   dataType:'text',
		   data:"id="+id.value+"&bbsNo="+bbsNo.value+"&writer="+writer.value+"&good="+isGood.value,
		   
		   success:function(msg){
			   if(msg=='fail')
				   alert('이미 게시물에 공감을 등록하셨습니다.');
			   else
			   {					   
				   if(isGood.value=='o')
					   document.getElementById("good"+bbsNo.value).innerHTML=msg;
				   else
					   document.getElementById("bad"+bbsNo.value).innerHTML=msg;
			   }
		    }
	});
	return false;
}
</script>

<script type="text/javascript">
	//function Click() {
	//	alert("눌림");
	//}
	$(document).ready(function() {

		$('#searchFriend').poshytip({
			content : '새로운 친구 찾기',
			className : 'tip-twitter',
			showTimeout : 1,
			alignTo : 'target',
			alignX : 'center',
			offsetY : 5,
			allowTipHover : false,
			fade : false,
			slide : false

		});
		$('#tip-twitter1').poshytip({
			content : '친구/그룹/Language Buddy',
			className : 'tip-twitter',
			showTimeout : 1,
			alignTo : 'target',
			alignX : 'center',
			offsetY : 5,
			allowTipHover : false,
			fade : false,
			slide : false

		});

		$('#tip-twitter1').click(function() {
			var i = 1;
			if (i == 1) {
				//$(this).children('a').slideToggle();
				$('.hiddenMenu').slideToggle();
				i = 0;
			} else {
				$('.hiddenMenu').addClass("hiddenSub");
				//$(this).children('a').addClass("hidden");
				i = 1;
			}
		});
	});
</script>
<title>The Oasis - main page</title>
</head>
<body>
	<div class="headcontainer">
		<div id="header" class="container_9">
			<div class="grid_9 header">
				<div class="grid_2 alpha logo">
					<a href="login.html"><img src="logo2.png" alt="InCub" /> </a>
					<div class="homehint">Homepage</div>
				</div>
				<div class="grid_3 menu">
					<ul>
						<li><a href="mypage.html"><span>My page</span> </a></li>
						<li><a href="friend.html"><span>Friend</span>
						</a>
						</li>
						<li><a href="webhard.html"><span>Webhard</span> </a></li>
					</ul>
					<div class="hiddenMenu">
						<a onclick="Click()" class="hiddenSub" id="friend"
							href="friend.html#friend2">Friend</a> <a onclick="Click()"
							class="hiddenSub" id="group" href="#friend.html#Group">Group</a>
						<a onclick="Click()" class="hiddenSub" id="lb"
							href="#friend.html#Languagebuddy">Language Buddy</a>
					</div>

				</div>
				<div class="grid_2 social">
					<ul>
						<li><a href="message.html"><span>Message</span> </a></li>
						<li id='searchFriend'><a href="searchFriend.html"><span>Search
									Friend</span> </a></li>
					</ul>
				</div>
				<div class="grid_1 profile">
					<img src="image/main.jpg" />

				</div>
				<div class="grid_1 subProfile">
					<a href="mypage.html">${sessionScope.loginUser.name }</a><br /> <a
						href="logout.html">logout</a>
				</div>


				<div class="grid_2 omega navigation">
					<a id="nav_next"></a><a id="nav_prev">&nbsp;</a>
				</div>
			</div>
			<div class="clear"></div>
		</div>
	</div>


	<div id="body">
		<div class="bodycontainer">

			<div class="mainsection ms-home">
				<!-- 친구 목록 -->
				<div id="page-home" name="Home" class="sectionwall"
					style="background-image: url(/uploads/images/backgrounds/banner-first-home.jpg);">
					<div class="section container_9">
						<br /> <br />
						<div class="grid_5 prefix_2 suffix_2"></div>
						<a class="nobg"></a><a class="down" ref="#page-tedxyouth"></a>
					</div>

				</div>
				<div class="clear"></div>
			</div>
			<div id="page-tedxyouth" name="TEDxYouth@Bucharest"
				class="sectionwall"
				style="background-image: url(/uploads/images/backgrounds/banner-tedxyouth.jpg);">

				<div class="section container_9">
					<myTags:mainTag id="${sessionScope.loginUser.id }">
						<div class="grid_5 prefix_2 suffix_2" style="margin-bottom: 40px;">
							<table style="word-break: break-all;" width="530" id="tb"
								border="1" cellpadding="0" cellspacing="0">
								<tr>
									<td width="116" height="120"><img src="image/taehee.jpg"
										width="116" height="116" alt="me" />
									</td>
									<td valign="top" id="subtb" colspan="6">${content }</td>
								</tr>
								<tr>
									<td height="30"><center>
											<a href="personal.html?id=${item.writer }"
												style="font-style: normal;">${writer}</a>
										</center>
									</td>
									<td></td>
									<form:form onsubmit="return AjaxTest(owner, bbsNo, writer,isGood)">
									<input type="hidden" name="isGood" value="o" />
												<input type="hidden" name="owner"
													value="${owner}" />
												<input type="hidden" name="bbsNo" value="${item.number }" />
												<input type="hidden" name="writer"
													value="${sessionScope.loginUser.id }" />
									<td width="75" align="right">
										<button type="image" onclick="goodcheck()" id="goodbtn"
											value="good">
											<img src="image/good.png" alt="good" />
										</button>
									</td>
									<td width="30" align="center"><p id="good${item.number }">${good }</p></td>
									</form:form>
									<form:form onsubmit="return AjaxTest(owner, bbsNo, writer,isGood)">
									<input type="hidden" name="isGood" value="x" />
												<input type="hidden" name="owner"
													value="${owner}" />
												<input type="hidden" name="bbsNo" value="${item.number }" />
												<input type="hidden" name="writer"
													value="${sessionScope.loginUser.id }" />
									<td width="75" align="right"><button type="image"
											onclick="badcheck()" id="badbtn" value="bad">
											<img src="image/bad.png" alt="bad" "/>
										</button>
									</td>
									<td width="30" align="center"><p id="bad${item.number }">${bad }</p></td>
									</form:form>

								</tr>
							</table>

							<table style="word-break: break-all; border-color: gray;"
								width="530" border="1" cellpadding="1" cellspacing="1">
								<tr>
									<td align="left">${date}</td>
								</tr>
							</table>
							<div class="dhtmlgoodies_question">
								<center>댓글</center>
							</div>
							<div class="dhtmlgoodies_answer">
								<div>
									<table width="530" border="2" cellspacing="1" cellpadding="1">
										<tr>
											<td height="204" colspan="2">
												<ul>
													<myTags:commentTag comments="${item.comments }">
														<li>${name }(<a
															href="personal.html?id=${comment.writer }">${comment.writer
																}</a>) : ${comment.content }</li>
													</myTags:commentTag>
												</ul></td>
										</tr>
										<tr>
											<form:form action="addComment.html" modelAttribute="comment">
												<input type="hidden" name="bbsNo" value="${item.number }" />
												<input type="hidden" name="id" value="${owner}" />
												<input type="hidden" name="view" value="redirect:login.html" />
												<td width="55" height="44">
													${sessionScope.loginUser.name }</td>
												<td><input type="text" name="content" size="57"></input><input
													type="submit" value="등록" />
												</td>
											</form:form>
										</tr>
									</table>
								</div>
							</div>
						</div>
					</myTags:mainTag>

				</div>

				<div class="nextpage grid_9">
					<a class="up" href="#page-home">&nbsp;</a><a class="down"
						href="#page-living-abroad">&nbsp;</a>
				</div>

			</div>
			<div class="clear"></div>
		</div>

	</div>
	</div>
	<script type="text/javascript">
		var _gaq = _gaq || [];
		_gaq.push([ '_setAccount', 'UA-2251793-43' ]);
		_gaq.push([ '_trackPageview' ]);

		(function() {
			var ga = document.createElement('script');
			ga.type = 'text/javascript';
			ga.async = true;
			ga.src = ('https:' == document.location.protocol ? 'https://ssl'
					: 'http://www')
					+ '.google-analytics.com/ga.js';
			var s = document.getElementsByTagName('script')[0];
			s.parentNode.insertBefore(ga, s);
		})();
	</script>
</body>
</html>