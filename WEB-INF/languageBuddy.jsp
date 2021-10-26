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
<style type="text/css">
.langbuintro {
	font-size: small;
}

.lbEx {
	position: relative;
	margin-top: 50px;
	margin-bottom: 20px;
}

.recommend {
	font-size: large;
	font-style: inherit
}

.languageBuddy {
	margin-top: 80px;
}

.my {
	position: relative;
	width: 200px;
	height: 100px;
	margin-left: -700px;
}
</style>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
	function breakLb(){
	    if(confirm("Language Buddy를 끊으시겠습니까?")) {
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
						<li><a href="friend.html"><span>Friends</span> </a></li>
						<li><a href="group.html"><span>Group</span> </a></li>
						<li><a href="languageBuddy.html"><span>Language
									Buddy</span> </a></li>
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
					<div class="section container_9"></div>
				</div>
				<div class="mainsection ms-what-we-do">

					<div id="page-what-we-do" name="What we do" class="sectionwall"
						style="background-image: url(/uploads/images/backgrounds/bg-what-we-do.jpg);">
						<div class="section container_9">
							<div class="grid_5 prefix_2 suffix_2">

								<center>
									<div class="grid_5 incubparttitle">
										<h2>Language Buddy</h2>
									</div>
								</center>
								<center>
									<c:if test="${enroll eq '미가입자' }">
										<div class="langbuintro">
											<form:form method="post" action="languageBuddy.html"
												modelAttribute="languageBuddyProfile">
												<input type="hidden" id="id" name="id"
													value=${sessionScope.loginUser.id }></input>
												<h1>LanguageBuddy 서비스에 오신걸 환영합니다!!!</h1>
												<h2>
													LanguageBuddy 서비스가 처음이시군요 <br /> 앞으로 Language Buddy 서비스를
													이용하시길 원하십니까? <br /> 원하신다면 프로필을 작성해주시고 이용을 선택해주세요~
												</h2>

												<table style="margin-top: 50px;">
													<tr>
														<th width="88px">자기소개</th>
														<td><textarea name="introduction"
																style="height: 200px; width: 440px;"></textarea>
														</td>
													</tr>
													<tr>
														<th>모국어</th>
														<td><select name="motherTongue">
																<option value="Korean">한국어</option>
																<option value="English">영어</option>
																<option value="Chinese">중국어</option>
																<option value="Japanese">일어</option>
																<option value="French">프랑스어</option>
																<option value="Russian">러시아어</option>
														</select></td>
													</tr>
													<tr>
														<th style="size: 10px;">배우고 싶은 언어</th>
														<td><select name="wannaLearn">
																<option value="Korean">한국어</option>
																<option value="English">영어</option>
																<option value="Chinese">중국어</option>
																<option value="Japanese">일어</option>
																<option value="French">프랑스어</option>
																<option value="Russian">러시아어</option>
														</select></td>
													</tr>
												</table>
												<input type="submit" name="enroll" value="서비스 이용"></input>
											</form:form>
										</div>
									</c:if>

									<c:if test="${enroll eq 'LanguageBuddy없음' }">
										<div class="my" style="margin-top: 5px; padding-top: 30px;">
											<p
												style="font-size: 15px; font-weight: bold; padding-top: -100px;">My
												Profile</p>
											<table
												style="border-style: solid; border-top-width: 5px; border-bottom-width: 5px; border-color: navy;">
												<tr>
													<th align="left" width="120" style="font-weight: normal;">모국어
													</th>
													<td style="color: navy;">${myMotherTongue }</td>
												</tr>
												<tr>
													<th align="left" style="font-weight: normal;">배우고싶은언어
													</th>
													<td style="color: navy;">${myWannalearn }</td>
												</tr>
											</table>
										</div>
										<div class="langbuintro" style="margin-top: 50px;">
											<br /> <br /> <br /> <br />
											<h1 style="font-size: 18px; color: navy;">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1>
											<h1 style="font-size: 18px; color: black;">
												<img src="image/requestlist.png" alt="" />
											</h1>
											<h1 style="font-size: 18px; color: navy;">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1>
											<br />
											<c:if test="${lbRequest eq '요청목록있음' }">
												<table>
													<c:forEach var="lbrequest2" items="${requestList}">
														<form:form method="post" action="responseLb.html">
															<input type="hidden" name="lb" value="${lbrequest2[0] }"></input>
															<tr>
																<td><img src="image/main.jpg" width="80"
																	height="100" /></td>
																<td width="300" style="padding-left: 20px; padding-top: 20px; font-size: 12px;">${lbrequest2[3] }</td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td align="center"><a style="font-style: normal; font-size: 12px;"
																	href="personal.html?id=${lbrequest2[0] }">${lbrequest2[1]
																		}</a>
																</td>
																<td align="right" colspan="3"><button type="image" id="accept" name="accept" value="accept"
																		style="background-color: #0d2d53;">
																		<img src="image/accept.png" alt="accept" /></button>
																		<button type="image" id="refuse" name="refuse" value="refuse"
																		style="background-color: #0d2d53;">
																		<img src="image/deny.png" alt="refuse" /></button>
																</td>
															</tr>
														</form:form>
													</c:forEach>
												</table>
											</c:if>
											<div class="lbEx">
												<h1 style="font-size: 18px; color: navy;">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1>
												<h1 style="font-size: 18px; color: black;">
													<img src="image/accommend.png" alt="" />
												</h1>
												<h1 style="font-size: 18px; color: navy;">━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━━</h1>
												<br />
												<h2 style="font-size: 12px;">해당 회원들은 회원님이 배우고자 하는 언어를</h2>
												<h2 style="font-size: 12px;">모국어로 사용하고 있으며,</h2>
												<h2 style="font-size: 12px;">회원님의 모국어를 배우고자 하는 회원들입니다.</h2>
												<h2></h2>
												<h2></h2>
											</div>
											<div class="recommend">
												<table>
													<c:forEach var="languageBuddy" items="${recommendList }">
														<form:form method="post" action="addLb.html">
															<input type="hidden" name="lbid"
																value="${languageBuddy.me.id }"></input>
															<input type="hidden" name="view"
																value="redirect:languageBuddy.html"></input>
															<tr>
																<td><img src="image/main.jpg" width="80"
																	height="100" /></td>
																<td width="300"
																	style="padding-left: 20px; padding-top: 20px; font-size: 12px;">${languageBuddy.profile.introduction
																	}</td>
																<td></td>
																<td></td>
															</tr>
															<tr>
																<td align="center"><a
																	style="font-style: normal; font-size: 12px;"
																	href="personal.html?id=${languageBuddy.me.id }">${languageBuddy.me.name}</a>
																</td>
																<td align="right" colspan="3">
																	<button type="image" id="request" name="request" value="request"
																		style="background-color: #0d2d53;">
																		<img src="image/request.png" alt="request" /></button>
																</td>
															</tr>
														</form:form>
													</c:forEach>
												</table>
											</div>


											<form:form method="post" action="modifyLbProfile.html"
												modelAttribute="profile">
												<table style="margin-top: 50px;">
													<tr>
														<th width="100"  align="left" style="font-size: 12px">자기소개</th>
														<td><textarea name="introduction"
																style="height: 200px; width: 280px;"></textarea>
														</td>
													</tr>
													<tr>
														<th  align="left"  style="font-size: 12px">모국어</th>
														<td><select name="motherTongue">
																<option value="Korean">한국어</option>
																<option value="English">영어</option>
																<option value="Chinese">중국어</option>
																<option value="Japanese">일어</option>
																<option value="French">프랑스어</option>
																<option value="Russian">러시아어</option>
														</select></td>
													</tr>
													<tr>
														<th  align="left" style="size: 10px;  font-size: 12px;">배우고 싶은 언어</th>
														<td><select name="wannaLearn">
																<option value="Korean">한국어</option>
																<option value="English">영어</option>
																<option value="Chinese">중국어</option>
																<option value="Japanese">일어</option>
																<option value="French">프랑스어</option>
																<option value="Russian">러시아어</option>
														</select></td>
													</tr>
												</table>
												<br/>
												<button type="image" id="modify" name="modify" value="modify"
																		style="background-color: #0d2d53;">
																		<img src="image/modify.png" alt="modify" /></button>
											</form:form>
										</div>
									</c:if>

									<c:if test="${enroll eq 'LanguageBuddy있음' }">

										<h1 style="font-size: 20px;"><img src="image/mylb.png" /></h1>
										<div class="languageBuddy">
											<form:form onsubmit="return breakLb()" method="post"
												action="breakLB.html">
												<input type="hidden" name="myLbId" value="${myLB.id }"></input>
												<input type="hidden" name="view"
													value="redirect:languageBuddy.html"></input>
												<table>
													<tr>
														<td><img src="image/main.jpg" width="80"
															height="100" /></td>
														<td width="300"
																	style="padding-left: 20px; padding-top: 20px; font-size: 12px;">${Lb.profile.introduction }</td>
														<td></td>
													</tr>
													<tr>
														<td align="center" ><a style="font-style: normal;" href="personal.html?id=${myLB.id}">${myLB.name}</a>
														</td>
														<td align="right" colspan="2"><button type="image" id="break" namevalue="break"
													style="background-color: #0d2d53; margin: 0px; background-image: url('image/remove.png'); background-repeat: no-repeat;">
													<img src="image/remove.png" alt="break" />
												</button></td>
													</tr>
												</table>
											</form:form>
										</div>
									</c:if>

								</center>
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