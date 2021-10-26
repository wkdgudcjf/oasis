<%@ page language="java" contentType="text/html; charset=utf-8"
    pageEncoding="utf-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="en" lang="en">
<head>
<title>The Oasis - main page</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />

<link rel="stylesheet" type="text/css" href="css/wfm.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_52_1301838816.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_50_1303205116.css" media="all" />
<link rel="stylesheet" type="text/css"
	href="css/stylesheet_51_1301838195.css" media="all" />

<script src="js/script.js" type="text/javascript"></script>
<script src="js/jquery.cycle.all.min.js"></script>
<script src="js/jquery.poshytip.min.js"></script>
<script src="js/jisu.js"></script>

<link rel="stylesheet" type="text/css" href="css/tree.css" />

<script src='js/jquery.ui.core.js' type="text/javascript"></script> 
<script src='js/jquery.cookie.js' type="text/javascript"></script> 
<script type="text/javascript">
	
	function deletef(i,j)
	{
		var id = document.getElementById("getid").value;
		$.ajax({
			url:'ajax.del',
			type:'POST',
			data:"location="+i+"&name="+j+"&id="+id,
			dataType:'json',
			success:function(msg){
//					for(var i in msg)
//					{					
//						alert(msg[i].a);
//						injection(msg[i].a);
//					}
				injections(msg.a);
				neos(msg.b);
				lists(msg.c);
				}
	      	 ,
	      	 error:function()
	      	 {
	      		 alert("ajax 에러입니다.");
	      	 },
	      	 complete:function()
	      	 {
	      		$('#tree_menu').find('li:has("ul")').prepend('<a href="#" class="control"><img src="image/folder-closed.png" /></a>');
	      		$('#tree_menu').find('li:last-child').addClass('end');
	      		tree_init('close');
	      	 }
		   });
	}
	function createf(location,name)
	{
		var id = document.getElementById("getid").value;
		var newname = prompt("폴더명을 입력하세요.","");
		if(newname.length==0)
		{
			return false;
		}
		else
		{
			$.ajax({
				url:'ajax.create',
				type:'POST',
				data:"location="+location+"/"+name+"&newname="+newname+"&id="+id,
				dataType:'json',
				success:function(msg){
						injections(msg.a);
						neos(msg.b);
						lists(msg.c);
					}
		      	 ,
		      	 error:function()
		      	 {
		      		 alert(".과/는 사용할 수 없습니다.");
		      	 },
		      	 complete:function()
		      	 {
		      		$('#tree_menu').find('li:has("ul")').prepend('<a href="#" class="control"><img src="image/folder-closed.png" /></a>');
		      		$('#tree_menu').find('li:last-child').addClass('end');
		      		tree_init('close');
		      		checked();
		      	 }
			});
		}
	}
	function moved(i,j)
	{
		var id = document.getElementById("getid").value;
		$.ajax({
			url:'ajax.neo',
			type:'POST',
			data:"location="+i+"&name="+j+"&id="+id,
			dataType:'json',
			success:function(msg){
					neos(msg.a);
					lists(msg.b);
				}
	      	 ,
	      	 error:function()
	      	 {
	      		 alert("ajax 에러입니다.");
	      	 },
	      	 complete:function()
	      	 {
	      		
	      	 }
		   });
	}
	function count(location,name)
	{
		var id = document.getElementById("getid").value;
		$.ajax({
			url:'ajax.neo',
			type:'POST',
			data:"location="+location+"&name="+name+"&id="+id,
			dataType:'json',
			success:function(msg){
//					for(var i in msg)
//					{					
//						alert(msg[i].a);
//						injection(msg[i].a);
//					}
					neos(msg.a);
					lists(msg.b);
				}
	      	 ,
	      	 error:function()
	      	 {
	      		 alert("ajax 에러입니다.");
	      	 },
	      	 complete:function()
	      	 {
	      		
	      	 }
		   });
	}
	function neos(path)
	{
		$('#path').children().remove();
		$('#path').append(path);
	}
	function lists(content)
	{
		$('.dirlistrow').children().remove();
		$('.dirlistrow').append(content);
	}
	function injections(node)
	{
		$('#tree_menu').children().remove();
		$('#tree_menu').append(node);
	}
	function injection(node)
	{
		$('#tree_menu').append(node);
	}
	function neo(path)
	{
		$('#path').append(path);
	}
	function list(content)
	{
		$('.dirlistrow').append(content);
	}
	$(document).ready(function(){
	var id = document.getElementById("getid").value;
	$.ajax({
		url:'ajax.init',
		type:'POST',
		data:"id="+id,
		dataType:'json',
		success:function(msg)
		{
//			for(var i in msg)
//			{					
//				alert(msg[i].a);
//				injection(msg[i].a);
//			}
			injection(msg.a);
			neo(msg.b);
			list(msg.c);
		}
      	 ,
      	 error:function()
      	 {
      		 alert("ajax 에러입니다.");
      	 },
      	 complete:function()
      	 {
      		$('#tree_menu').find('li:has("ul")').prepend('<a href="#" class="control"><img src="image/folder-closed.png" /></a>');
      		$('#tree_menu').find('li:last-child').addClass('end');
      		tree_init('close');
      	 }
	   });
	});

	
		
	function tree_init(status){
		if (status == 'close'){
			$('#tree_menu').find('ul').hide();
			$('a.control').find('img').attr('src', 'image/folder.png');
		} else if (status == 'open'){
			$('#tree_menu').find('ul').show();
			$('a.control').find('img').attr('src', 'image/folder-closed.png');
		}
	}
	
	/* OPEN & CLOSE */
	$(document).ready(function(){
			$('#all').toggle(function(){
			tree_init('open');
			$(this).text('ALL CLOSE');
		},function(){
			tree_init('close');
			$(this).text('ALL OPEN');
		});
	});
	$(document).ready(function(){
		alert("웹하드 서비스를 이용해주셔서 감사합니다.");
		function checked()
		{
			alert("살아나ㅠㅠ");
		}
		$('.control').click(function(){
			var temp_el = $(this).parent().find('>ul');
			if (temp_el.css('display') == 'none'){
				temp_el.slideDown();
				$(this).find('img').attr('src', 'image/folder-closed.png');
				return false;
			} else {
				temp_el.slideUp();
				$(this).find('img').attr('src', 'image/folder.png');
				return false;
			}
		});
	});

 </script>

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
						<a href="mypage.html">${loginUser.name}</a><br /> <a
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
				<div class="grid_3 prefix_2 suffix_2"
					style="overflow: auto; width: 220px; height: 600px; margin-left: 100px; margin-top: 100px; text-align:left">
					<div>
					<ul id="tree_menu">
					
					</ul>
					<hr />
					<button id="all">ALL OPEN</button>
					</div>	
				</div>
				<div class="grid_9" style="overflow: auto; width: 800px;">
					<div id="FileListContainer">
						<div id="FileList">
						<div id="path" width="750px" style='display:inline;'>
						</div>	
							<table id="FLHeader" class="FLCols">
								<tr>
									<td id="testwnd">이름 </td>
									<td>크기(KB)</td>
									<td>등록 날짜</td>
									<td>주인ID</td>
									<td>다운받기</td>
									<td>삭제하기</td>
								</tr>
							</table>

							<div id="FLBody">
								<table id="FLBTable" class="FLCols">
									<tbody class="dirlistrow">
										
									</tbody>
								</table>
							</div>
						</div>

					</div>
				</div>
			</div>
		</div>
	</div>
<input type="hidden" id="getid" value='${loginUser.id}' />
</body>
</html>
