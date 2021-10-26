<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="myTags" uri="simpleTags"%>
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
<link rel="stylesheet" type="text/css" href="css/hyungchul.css"
	media="all" />
<link rel="stylesheet" type="text/css" href="css/jisu.css" media="all" />

<script type="text/javascript" src="js/script.js"></script>
<script src="js/jquery.cycle.all.min.js"></script>
<script src="js/jquery.poshytip.min.js"></script>
<script src="js/jisu.js"></script>
<script src="js/jquery.js"></script>
<script type="text/javascript">

	function change()
	{
		$('#modifyprofile').removeClass("md");
		$('#change').hide();
		$('#profile').addClass("md");
	}
	function Delete()
	{
		if(confirm("게시물을 삭제하시겠습니까?"))
			return true;
		else
			return false;
	}
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
<style type="text/css">
.md {
	display: none;
}
</style>
<script type="text/javascript">
$(document).ready(function(){
	$('#modifyprofile').addClass("md");
	});

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
						<li><a href="friend.html#page-what-we-do"><span>Friend</span>
						</a></li>
						<li><a href="webhard.html"><span>Webhard</span> </a>
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

				<div class="mainsection ms-what-we-do">

					<div id="page-what-we-do" id="What we do" class="sectionwall"
						style="background-image: url(/uploads/images/backgrounds/bg-what-we-do.jpg);">
						<div class="section container_9">
							<div class="grid_5"
								style="margin-top: 40px; margin-bottom: 40px;">
								<table id="intro" width="950" border="1">
									<tr>
										<td style="padding: 10px;"><img src="image/main.jpg"
											alt="이미지" width="150" height="150" />
										</td>
										<td id="profile"
											style="padding: 10px; font-size:12px; padding-left: 300px; border-style: dotted; overflow: scroll;">
											<c:forEach var="ls" items="${prof}">
												<span style="">${ls.infoName} : </span> ${ls.infoData}<br/>
											</c:forEach>
										</td>
										<td id="modifyprofile"
											style="padding: 10px; font-size:12px; padding-left: 300px; border-style: dotted;" rowspan="2">
											<table>
												<form:form onsubmit="mypage.html">
													<input type="hidden" name="id"
														value="${sessionScope.loginUser.id}" />
													<c:forEach var="ls" items="${prof}" varStatus="loop">
														<tr>
														<td>${ls.infoName} :</td>
														<td>
															<input type="text"  size="15" name="${loop.count}"
																placeholder="${ls.infoData}" style="font-size:12px; border:thin; border-color: navy;" />
															<br/></td><td></td><td></td>
														</tr>
													</c:forEach>
													<tr>
													<td colspan="4" align="right"><input type="image" src="image/profile.png"
														alt="modify" /></td></tr>
												</form:form>
											</table>
										</td>
									</tr>
									<tr id="name">
										<td width="200" height="50">
											<center>
												<a href="mypage.html"
													style="font-style: normal; font-size: 17px; font-weight: bold; text-decoration: none; margin-left: -30px;">${sessionScope.loginUser.name}</a>
											</center>
										</td>
										<!--	<td align="right">
											<form method='post' action='imageupload.do' enctype='multipart/form-data'>
												<input type='hidden' name='id' value="${sessionScope.loginUser.id}"/>
												<input type='file' name='mainimage' width="50"/>
												<button type='image'><img src="image/profileimage.png" alt="good" /></button>
											</form>
										</td>  -->
										<td align="right"><input type="image" id="change"
											src="image/profile.png" alt="modify" onclick="change()"></input>
										</td>
									</tr>
								</table>
								<div class="grid_5 prefix_2 suffix_2">
									<div class="dhtmlgoodies_question" style="margin-left: -10px;">
										<center>게시물 추가하기</center>
									</div>
									<div class="dhtmlgoodies_answer"
										style="width: 530px; margin-left: -10px;">
										<div>

											<table width="530" border="2" cellspacing="1" cellpadding="1">
												<form:form action="enrollMine.html" modelAttribute="item">
													<input type="hidden" name="writer"
														value="${sessionScope.loginUser.id}" />
													<tr>
														<td><input type="file" style="width: 20px;" /></td>
													</tr>
													<tr>
														<td><textarea name="content"
																style="height: 200px; width: 440px;"></textarea>
														</td>
														<td><input type="submit" value="등록" />
														</td>
													</tr>
												</form:form>
											</table>

										</div>
									</div>
								</div>
							</div>
							<br /> <br />
							<myTags:listTag id="${sessionScope.loginUser.id }">
								<div class="grid_5 prefix_2 suffix_2"
									style="margin-bottom: 40px;">
									<table style="word-break: break-all; border-color: gray;"
										width="530" id="tb" border="1" cellpadding="1" cellspacing="1">
										<tr>
											<td width="116" height="120"><img src="image/taehee.jpg"
												width="116" height="116" alt="me" /></td>
											<td colspan="6">${content }</td>
										</tr>
										<tr>
											<td height="30"><center>
													<a href="personal.html?id=${item.writer }"
														style="font-style: normal;">${writer}</a>
												</center></td>
											<td></td>
											<form:form
												onsubmit="return AjaxTest(owner, bbsNo, writer, isGood)">
												<input type="hidden" name="isGood" value="o" />
												<input type="hidden" name="owner"
													value="${sessionScope.loginUser.id }" />
												<input type="hidden" name="bbsNo" value="${item.number }" />
												<input type="hidden" name="writer"
													value="${sessionScope.loginUser.id }" />
												<td width="75" align="right">
													<button type="image" onclick="goodcheck()" id="goodbtn"
														value="good">
														<img src="image/good.png" alt="good" />
													</button></td>
												<td width="30" align="center"><p
														id="good${item.number }">${good }</p></td>
											</form:form>
											<form:form
												onsubmit="return AjaxTest(owner, bbsNo, writer,isGood)">
												<input type="hidden" name="isGood" value="x" />
												<input type="hidden" name="owner"
													value="${sessionScope.loginUser.id }" />
												<input type="hidden" name="bbsNo" value="${item.number }" />
												<input type="hidden" name="writer"
													value="${sessionScope.loginUser.id }" />
												<td width="75" align="right"><button type="image"
														onclick="badcheck()" id="badbtn" value="bad">
														<img src="image/bad.png" alt="bad" "/>
													</button></td>
												<td width="30" align="center"><p
														id="bad${item.number }">${bad }</p>
												</td>
											</form:form>

										</tr>
									</table>
									<table style="word-break: break-all; border-color: gray;"
										width="530" border="1" cellpadding="1" cellspacing="1">
										<form:form onsubmit="return Delete()" action="deleteBBS.html">
											<input type="hidden" name="owner"
												value="${sessionScope.loginUser.id }" />
											<input type="hidden" name="bbsNo" value="${item.number}" />
											<tr>
												<td align="left">${date}</td>
												<td align="right" width="183" colspan="2"><button
														type="image" id="sharebtn" value="delete"
														style="background-color: #0d2d53;">
														<img src="image/delete.png" alt="delete" />
													</button></td>
											</tr>
										</form:form>
									</table>
									<div class="dhtmlgoodies_question" style="font-family: 새굴림">
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
														</ul>
													</td>
												</tr>
												<tr>
													<form:form action="addComment.html"
														modelAttribute="comment">
														<input type="hidden" name="bbsNo" value="${item.number }" />
														<input type="hidden" name="id"
															value="${sessionScope.loginUser.id }" />
														<input type="hidden" name="view"
															value="redirect:mypage.html" />
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
							</myTags:listTag>

							<div class="nextpage grid_9">
								<a class="up" href="#page-home"></a><a class="down"
									href="#page-living-abroad"></a>
							</div>

						</div>
					</div>
					<div class="clear"></div>
				</div>
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