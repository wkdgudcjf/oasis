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
.makeGroup {
	position: relative;
	top: 100px;
	left: -200px;
	width: 300px;
	height: 100px;
}
</style>
<script type="text/javascript" src="js/script.js"></script>
<script type="text/javascript">
	
	function removeFriend(){
	    if(confirm("친구를 끊으시겠습니까?")) {
	        return true;
	    } else {
	        return false;
	    }
	}
	var check=0;
	function Check()
	{
		var name = document.getElementById("groupName").value;
		if(name.length ==0)
		{
			alert("그룹 명을 입력해주세요.");
			return false;
		}
		if(check==0)
		{
			alert("그룹 명 중복 확인을 해주세요.");
			return false;
		}
		return true;
	}
	function CheckGroupName() {
		//var id = prompt("사용할 아이디를 입력하세요","");
		var name = document.getElementById("groupName").value;
		if (name.length == 0) {
			alert("그룹 명을 입력해주세요.");
			return;
		}
		$.ajax({
			url : 'groupName.oasis',
			type : 'POST',
			data : "command=" + name,
			dataType : 'text',

			success : function(msg) {
				if (msg == 'fail') {
					alert("이미 사용중인 그룹명입니다.");
					check=0;
				} else {
					check=1;
					alert("사용 가능한 그룹명입니다.");
					
					document.getElementById("groupName").innerHTML = name;
				}
			}
		});
		return false;
	}
	
	function f_checkAllorNot(chk_obj){
	    var chk = document.getElementsByName(chk_obj.name);
	  
	    if(chk_obj.checked==true){
	        for (i = 1; i < chk.length; i++){
	            chk[i].checked = true;
	        }
	    }
	    else{
	        for (i = 1; i < chk.length; i++){
	            chk[i].checked = false;
	        }
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
									<div class="grid_5 incubparttitle">
										<h2>Group</h2>
									</div>
								</center>
								<div class="makeGroup"
									style="margin-left: -30px; margin-top: 0px; z-index: 999;">
									<br />
									<table
										style="border-style: solid; border-bottom-width: 5px; border-color: navy;">
										<tr>
											<td align="center"
												style="margin-left: 60px; font-size: 15px; font-weight: bold; padding-bottom: 5px; border-style: solid; border-bottom-width: 5px; border-color: navy;"
												colspan="2">그룹 만들기</td>
										</tr>
										<form:form method="post"
											action="addGroup.html" modelAttribute="member">
											<c:forEach var="friend" items="${friendList}">

												<input type="hidden" id="memberId" name="memberId"
													value="${friend.id }" />

												<tr>
													<td style="width: 10; padding-top: 10px; z-index: 999;"><input
														type="checkbox" name="chkItem" id="${friend.id}"
														value="${friend.id}" /></td>
													<td align="left" height="33px"
														style="padding-top: 10px; z-index: 999;"><a
														href="personal.html?id=${friend.id }"
														style="font-style: normal;">${friend.name}</a>(${friend.id
														})</td>
												</tr>
											</c:forEach>
											<tr
												style="border-style: solid; border-top-width: 5px; border-color: navy;">
												<td colspan="2" align="center" height="33px"
													style="padding-top: 10px; margin-bottom: 10px;"><input
													type="text" id="groupName" name="groupName"></input>
												</td>
											</tr>
											<tr>
												<td height="20px"><button type="button" 
														onclick="CheckGroupName()" id="groupNameCheck"
														name="groupNameCheck" value="good"
														style="background-color: #0d2d53;">
														<img src="image/check.png" alt="good" />
													</button>
												</td>
												<td><button type="submit" id="makeGroup"
														name="makeGroup" value="good"
														style="background-color: #0d2d53;" onclick="Check()">
														<img src="image/make.png" alt="good" />
													</button>
												</td>
											</tr>
										</form:form>
									</table>

								</div>
								<br /> <br /> <br /> <br /> <br /> <br />
								<div style="position: relative; margin-left: 70px;">

									<c:forEach var="group" items="${groupList}">
										<table width="434"
											style="margin-left: 0px; border-style: solid; border-top-width: 2px;">
											<input type="hidden" id="memberId" name="memberId"
												value="${group.groupName }" />
											<tr>
												<td width="110" height="86"><img src="image/group.png"
													width="185" height="75" /></td>
												<td align="right" width="308" colspan="2"
													style="padding-left: 10px; padding-top: 10px;">
													<p>그룹 회원들</p> <c:forEach var='friend'
														items="${group.memberList}">
														<a href="personal.html?id=${friend.id }"
															style="font-style: normal;">${friend.name}</a>
													</c:forEach></td>
											</tr>
											<tr>
												<form:form method="post" action="removeGroup.html"
													modelAttribute="group">
													<input type="hidden" id="groupName" name="groupName"
														value="${group.groupName }"></input>
													<input type="hidden" id="view" name="view"
														value="redirect:group.html"></input>
													<td align="center" height="33"><a href="#"
														style="font-style: normal;">${group.groupName}</a></td>
													<td align="right" width="308" rowspan="2"><button
															type="image" id="removeGroup" name="removeGroup"
															value="good" style="background-color: #0d2d53;">
															<img src="image/bye.png" alt="good" />
														</button></td>
												</form:form>
											</tr>
									

									</table>
									</c:forEach>
								</div>

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