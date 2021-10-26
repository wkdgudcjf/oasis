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
<title>The Oasis - main page</title>
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
						<li><a href="friend.html"><span>Friend</span> </a>
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
				<!-- 
				<div id="page-home" name="Home" class="sectionwall"
					style="background-image: url(/uploads/images/backgrounds/banner-first-home.jpg);">
					<div class="section container_9">
						<a class="nobg">&nbsp;</a><a class="down" ref="#page-tedxyouth">&nbsp;</a>
					</div>
				</div> -->
			</div>
			<div id="page-tedxyouth" name="TEDxYouth@Bucharest"
				class="sectionwall"
				style="background-image: url(/uploads/images/backgrounds/banner-tedxyouth.jpg);">
				<div class="section container_9">
					<div class="grid_5 prefix_2 suffix_2" style="margin-bottom: 40px;">
						<center>
							<div class="grid_5 incubparttitle">
								<h2>Message</h2>
							</div>
						</center>

					</div>
					<myTags:messageTag id="${sessionScope.loginUser.id}">

						<div class="grid_5 prefix_2 suffix_2" style="margin-bottom: 40px;">
							<div class="dhtmlgoodies_question">
								<center>${name }님과의 쪽지</center>
							</div>
							<div class="dhtmlgoodies_answer" style="width: 530px;">
								<div>

									<table width="530" border="2" cellspacing="1" cellpadding="1">
										<tr>
											<td height="204" colspan="2">
												<ul>
													<c:forEach var="message" items="${list }">
														<c:if
															test="${sessionScope.loginUser.id eq  message.writer}">
															<li style="float: right;">${message.content}</li>
															<br />
														</c:if>
														<c:if
															test="${sessionScope.loginUser.id !=  message.writer}">
															<li><a href="personal.html?id=${message.writer }" style="font-style: normal; text-decoration: none; color: blue;">${name}</a> : ${message.content }</li>
														</c:if>
													</c:forEach>
												</ul>&nbsp;</td>
										</tr>
										<tr>
											<td width="55" height="44">${sessionScope.loginUser.name } : </td>
											<td>
											<form:form action="message.html">
											<input type="hidden" name="view" value="redirect:message.html"/>
											<input type="hidden" name ="friendId" value="${friendId }"/>
											<input type="text" name="content" size="57"></input><button type="submit" value="전송">전송</button> 
											</form:form>
											</td>
										</tr>
									</table>
								</div>

							</div>


						</div>
					</myTags:messageTag>


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