<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN"
"http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>InCub</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/favicon.ico" type="image/x-icon" />
<link rel="Bookmark" href="/favicon.ico" type="image/x-icon" />
<link rel="icon" href="/favicon.ico" type="image/x-icon" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_52_1301838816.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_50_1303205116.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_51_1301838195.css" media="all" />

<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
	function removeFriend(){
	    if(confirm("정말 친구를 끊으시겠습니까?")) {
	        return true;
	    } else {
	        return false;
	    }
	}
</script>
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
						<li><a href="friend.html"><span>Friends</span> </a>
						</li>
						<li><a href="group.html"><span>Group</span> </a>
						</li>
						<li><a href="languageBuddy.html"><span>Language
									Buddy</span> </a>
						</li>
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
						<li><a href="message.html"><span>Message</span> </a>
						</li>
						<li id='searchFriend'><a href="searchFriend.html"><span>Search
									Friend</span> </a>
						</li>
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
					<div class="section container_9"></div>
				</div>
				<div class="mainsection ms-what-we-do">

					<div id="page-what-we-do" name="What we do" class="sectionwall"
						style="background-image: url(/uploads/images/backgrounds/bg-what-we-do.jpg);">
						<div class="section container_9">
							<div class="grid_5 prefix_2 suffix_2">
								<center>
									<div class="grid_5 incubparttitle" style="margin-bottom: 50px;">
										<h2>Friends</h2>
									</div>
								</center>

								<table width="434" border="1" style="margin-top: 150px; margin-left: 150px;">

									<c:forEach var="friend" items="${friendList}">
										<form:form onsubmit="return removeFriend()" method="post"
											action="removeFriend.html" modelAttribute="member">
											<input type="hidden" id="memberId" name="memberId"
												value="${friend.id }" />
											<tr style="margin-top: 20px;">
												<td width="110" height="86"><img src="image/main.jpg"
													width="80" height="100" /></td>
												<td width="308" rowspan="2" style="padding-left: 50px;"><br />
												<br />
												<button type="image" id="remove" name="remove"
														value="remove"
														style="background-color: #0d2d53; margin: 0px; background-image: url('image/remove.png'); background-repeat: no-repeat;">
														<img src="image/remove.png" alt="remove" />
													</button>
												</td>
											</tr>
											<tr>
												<td height="33" align="center"><a
													href="personal.html?id=${friend.id }"
													style="font-style: normal;">${friend.name}</a>
												</td>
												<td width="308" rowspan="2"></td>
											</tr>
										</form:form>
									</c:forEach>

								</table>

							</div>
						</div>
						<div class="clear"></div>
					</div>



					<div id="page-gscience" name="GScience" class="sectionwall">
						<div class="section container_9">
							<div class="wwdcontent wwdimg grid_3"></div>
							<div class="wwdcontent grid_4 suffix_2"></div>
							<div class="wwdcontent grid_4"
								style="padding-top: 0; padding-bottom: 40px;">
								<!-- content -->
							</div>
							<div class="wwdcontent grid_2">
								<div class="sharebox">
									<!-- AddThis Button BEGIN -->
									<div class="addthis_toolbox addthis_default_style "
										addthis:url="http://incub.ro/#page-gscience"
										addthis:title="GScience" addthis:description="GScience">
										<a class="addthis_button_facebook"></a> <a
											class="addthis_button_twitter"></a>
									</div>
									<script type="text/javascript">
									var addthis_config = {
										"data_track_clickback" : true
									};
								</script>
									<script type="text/javascript"
										src="http://s7.addthis.com/js/250/addthis_widget.js#pubid=gabidumitru"></script>
									<!-- AddThis Button END -->
								</div>
							</div>
							<div class="nextpage grid_9">
								<a class="up" href="#page-bucharest-night-run">&nbsp;</a>
							</div>
							<div class="clear"></div>
							<div class="container_9"></div>
							<div class="clear"></div>
						</div>
						<div class="clear"></div>
					</div>
				</div>
			</div>
		</div>
	</div>

</body>
</html>