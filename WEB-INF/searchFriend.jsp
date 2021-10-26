<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
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

<link rel="stylesheet" href="css/tip-twitter.css" />

<script type="text/javascript">
	var i = document.getElementById("mamberName").value;
	function addFriend(){
	    if(confirm("친구 추가를 원하십니까?")) {
	        return true;
	    } else {
	        return false;
	    }
	}
	
	function accept(){
		alert("z");
		myForm.method="post";
		myForm.action = "responseAdd.html";
		myForm.submit();
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
						<li><a href="mypage.html"><span>My page</span> </a>
						</li>
						<li><a href="friend.html"><span>Friend</span> </a></li>
						<li><a href="webhard.html"><span>Webhard</span> </a>
						</li>
					</ul>
					<div class="hiddenMenu">
						<a onclick="Click()" class="hiddenSub" id="friend"
							href="friend.html#friend">Friend</a> <a onclick="Click()"
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

			<div class="mainsection ms-home"></div>
			<div class="mainsection ms-what-we-do">

				<div id="friend" name="What we do" class="sectionwall"
					style="background-image: url(/uploads/images/backgrounds/bg-what-we-do.jpg);">
					<div class="section container_9">
						<div class="grid_5 prefix_2 suffix_2">
							<center>
								<div class="grid_5 incubparttitle">
									<h2>searchFriend</h2>
								</div>
							</center>

							<div class="grid_5" style="padding-top: 30px;">
								<table  style="margin-left: 140px;">
									<c:forEach var="standGroup" items="${standByGroup }">
										<form:form method="post" modelAttribute="member"
											action="responseGroupAdd.html">
											<input type="hidden" id="receiver" name="receiver"
												value="${standGroup[0] }" />
											<input type="hidden" id="groupName" name="groupName"
												value="${standGroup[1] }" />
											<tr>
												<td>${standGroup[0]} 님이 ${standGroup[1]} 그룹에 초대하셨습니다.</td>
												<td></td>
											</tr>
											<tr>
												<td align="center" colspan="2">
												<button type="image" id="accept" name="accept" value="accept"
																		style="background-color: #0d2d53;">
																		<img src="image/accept.png" alt="request" /></button>
																		<button type="image" id="refuse" name="refuse" value="refuse"
																		style="background-color: #0d2d53;">
																		<img src="image/deny.png" alt="refuse" /></button>
											
													</td>
											</tr>
										</form:form>
									</c:forEach>
								</table>

								<table style="margin-left: 140px;">
									<c:forEach var="stand" items="${standByFriend}">
										<form:form method="post" action="responseAdd.html"
											modelAttribute="member">
											<input type="hidden" id="memberId" name="memberId"
												value="${stand[0]}" />
											<tr>
												<td  style="font-style: normal; font-size: 13px;"><a style="font-style: normal; font-size: 13px;"
													href="personal.html?id=${stand[0] }">${stand[1] }</a>
													님(${stand[0] })이 친구요청을 하였습니다 </td>
													<td></td>
											</tr><tr>
												<td align="center" colspan="2">
												<button type="image" id="accept" name="accept" value="accept"
																		style="background-color: #0d2d53;">
																		<img src="image/accept.png" alt="request" /></button>
																		<button type="image" id="refuse" name="refuse" value="refuse"
																		style="background-color: #0d2d53;">
																		<img src="image/deny.png" alt="refuse" /></button>
											
													</td>
											</tr>
											<br/>
										</form:form>
									</c:forEach>
								</table>
								<br/>
								<h1 style="font-size: 18px; color: navy;">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1>
								<form:form method="POST" action="searchFriend.html"
									modelAttribute="member">
									<br/>
									<select name="idName" style="margin-left: 120px; margin-top: 20px;">
										<option value="id" selected="selected">아이디로 검색</option>
										<option value="name">이름으로 검색</option>
									</select>
									<input type="text" name="id" id="id" class="searchBox">
									</input>
									<br/>
									<p align="center" style="margin-top: 10px;"><button type="image" id="Search" name="Search" value="Search"
																		style="background-color: #0d2d53;">
																		<img src="image/search.png" alt="Search" /></button></p>
									
								</form:form>
								<br/>
								<br/>
								<div class="grid_5">
									<table style="margin-left: 150px;">

										<c:forEach var="member" items="${searchList }">
											<form:form onsubmit="return addFriend()" method="POST"
												action="addFriend.html" modelAttribute="member">
												<input type="hidden" id="memberId" name="memberId"
													value="${member[0] }" />
												<input type="hidden" name="view"
													value="redirect:searchFriend.html" />
												<tr style="margin-bottom: 20px;">
													<c:if test="${member[0] != sessionScope.loginUser.id }">
														<td style="padding-bottom: 10px;" id="memberName" name="memberName"
															value="${member[1] }"><a style="font-style: normal; text-decoration: none;"  href="personal.html?id=${member[0] }">${member[1] } (${member[0] }) </a> </td>
														<c:if test="${member[2] eq '친구아님' }">
															<td >
															<button type="image" id="add" name="add" value="add"
																		style="background-color: #0d2d53;">
																		<img src="image/add.png" alt="add" /></button>
															</td>
														</c:if>
														<c:if test="${member[2] eq '이미요청' }">
															<td>이미 친구 요청한 회원입니다.</td>
														</c:if>
														<c:if test="${member[2] eq '친구' }">
															<td>이미 친구입니다.</td>
														</c:if>
													</c:if>
												</tr>
											
											</form:form>
										</c:forEach>

										<!--  
								<c:forEach var="member" items="${memberList}">
								<!-- 검색한 친구가 생겻을 때마다 form을 걸어 추가버튼을 선택시 addFriend.html로 요청보냄 -->
										<!-- 
							<form:form onsubmit="return addFriend()" method="POST" action="addFriend.html" modelAttribute="member">
								<input type="hidden" id="memberId" name="memberId" value="${member.id }" />
								<input type="hidden" id="memberName" name="memberName" value="${member.name }" />
								<tr>
									<td id="memberName" name="memberName" value="${member.name }">${member.name }</td>
					
									<td><input type="submit" value="추가" /></td></tr>
							</form:form>
							</c:forEach>
							 -->

									</table>
								</div>
							</div>
						</div>
					</div>

				</div>
			</div>
		</div>
	</div>
	</div>
	</div>

</body>
</html>