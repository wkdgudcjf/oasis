/* 
	
	James Richardson javascript functions
	
-------------------------------------------------------------------------------------------------- */

/*
 * jQuery Smooth Scroll plugin
 * Version 1.2  (July 6, 2010)
 * @requires jQuery v1.3+
 *
 * Dual licensed under the MIT and GPL licenses (just like jQuery):
 * http://www.opensource.org/licenses/mit-license.php
 * http://www.gnu.org/licenses/gpl.html
 *
 */
eval(function(p,a,c,k,e,d){e=function(c){return(c<a?'':e(parseInt(c/a)))+((c=c%a)>35?String.fromCharCode(c+29):c.toString(36))};if(!''.replace(/^/,String)){while(c--){d[e(c)]=k[c]||e(c)}k=[function(e){return d[e]}];e=function(){return'\\w+'};c=1};while(c--){if(k[c]){p=p.replace(new RegExp('\\b'+e(c)+'\\b','g'),k[c])}}return p}('(b($){9 C=\'1.2\';9 z=D(12.11);$.k.p({i:b(){9 i=[],x=h;8.14(b(){7(8==13||8==1b){j}9 d=$(8);7(d.s()>0){i=[8];j h}d.s(1);x=d.s()>0;d.s(0);7(x){i=[8];j h}});j 8.1a(i)},c:b(g){9 5=$.p({},$.k.c.q,g);8.16(\'S.10\').17(\'S.10\',b(P){9 6=8,$6=$(8),K=((12.A===6.A)||!6.A),Q=5.f||(D(6.11)||z)===z,F=6.Y&&\'#\'+6.Y.o(\'#\',\'\'),e=18;7(!5.f&&(!K||!Q||F.r==1)){e=h}L{9 l=5.l,H=0,d=l.r;M(e&&H<d){7($6.19(l[H++])){e=h}}9 m=5.m,G=0,R=m.r;M(e&&G<R){7($6.15(m[G++]).r){e=h}}}7(e){5.f=5.f||F;5.6=6;P.1s();$.c(5)}});j 8}});$.c=b(g,O){9 5,w,t=\'J\';7(1r g===\'1q\'){5=$.k.c.q;w=g}L{5=$.p({},$.k.c.q,g||{});7(5.n){t=\'y\';7(5.n.N(\'y\')==\'1t\'){5.n.N(\'y\',\'1c\')}}w=O||($(5.f)[t]()&&$(5.f)[t]()[5.I])||0}5=$.p({6:v},5);9 $T=5.n||$(\'1w, 1v\').i(),X={V:\'1o\',\'1h\':\'1g\'},B={};B[\'1f\'+X[5.I]]=w+5.J;$T.1d(B,{1e:5.U,E:5.E,1i:b(){7(5.u&&$.1n(5.u)){5.u.1m(5.6,5)}}})};$.c.C=C;$.k.c.q={l:[],m:[],J:0,I:\'V\',n:v,f:v,u:v,E:\'1k\',U:1x};b D(W){j W.o(/^\\//,\'\').o(/(1l|1j).[a-1u-Z]{3,4}$/,\'\').o(/\\/$/,\'\')}})(1p);',62,96,'|||||opts|link|if|this|var||function|smoothScroll|el|include|scrollTarget|options|false|scrollable|return|fn|exclude|excludeWithin|scrollElement|replace|extend|defaults|length|scrollTop|offPos|afterScroll|null|scrollTargetOffset|scrolled|position|locationPath|hostname|aniprops|version|filterPath|easing|thisHash|ewlCounter|elCounter|direction|offset|hostMatch|else|while|css|px|event|pathMatch|ewl|click|scroller|speed|top|string|dirs|hash||smoothscroll|pathname|location|document|each|closest|die|live|true|is|pushStack|window|relative|animate|duration|scroll|Left|left|complete|default|swing|index|call|isFunction|Top|jQuery|number|typeof|preventDefault|static|zA|body|html|400'.split('|'),0,{}))

/* Easings
-------------------------------------------------------------------------------------------------- */
$.easing.easeInOutExpo = function (x, t, b, c, d) {
	if (t==0) return b;
	if (t==d) return b+c;
	if ((t/=d/2) < 1) return c/2 * Math.pow(2, 10 * (t - 1)) + b;
	return c/2 * (-Math.pow(2, -10 * --t) + 2) + b;
};
$.easing.easeOutQuint = function(x,t,b,c,d){
	return c*((t=t/d-1)*t*t*t*t + 1) + b;
};

/* Binding clicks for navs + animations
-------------------------------------------------------------------------------------------------- */
var levelOpen = 'level1';
var t;
var r = false;
var view = $(window);
var isIE67 = false;
if($('html').is('.iefix')) { 
	isIE67 = true; 
}

$('#inner').click(function(e){

	if(!r){
	
		r = true;
		setTimeout(function(){
			r = false;
		}, 800);
	
		if($(e.target).parents('#nav01').length || $(e.target).parents('#level2').length){
				
				var _this = $(e.target);
				
				if(_this.get(0).tagName == 'A'){
					var _link = _this;
					var elem = _this.attr('href').split('#')[1];
				}
				else{
					var _link = _this.parents('a');
					var elem = _this.parents('a').attr('href').split('#')[1];
				}
				
				if(view.scrollTop() == 0){
					setAbsolute();
				}
				
				// Level 01 nav
				if(_this.parents('#nav01').length){
					
					// Open menu:
					switch(levelOpen){
						case 'level1':
							levelOpen = 'level2';
							_link.addClass('on');
							$('#container').stop().animate({ 'left':342 }, {duration: 400, easing: 'easeOutQuint', complete: function(){
								$('#'+elem).stop().fadeIn(200, function(){
									forceShow($(this));
									$('#wrap').height('auto');	
									// ---- setFixed ------
									setFixed();		
									// ---- /setFixed ------
									//r = false;
								});
							}});
							// ---- show image ------
							pauseCycle();
							// ---- /show image ------
							return false;
							break;
						case 'level2':
							if(!_link.hasClass('on')){
								var fn = function(){
									$('#nav01 a').removeClass('on');
									_link.addClass('on');
									$('.nav02:visible').stop().fadeOut(200, function(){
										$('#'+elem).stop().fadeIn(200, function(){
											forceShow($(this));
											// ---- setFixed ------
											setFixed();		
											// ---- /setFixed ------
										});
									});
								};
								scrollToTop(fn);
								
							}
							else{
								levelOpen = 'level1';
								$('#nav01 a').removeClass('on');
								$('#wrap').height('100%');
								
								var fn = function(){
									setAbsolute();
									$('#container').stop().animate({ 'left': 790 }, {duration: 400, easing: 'easeOutQuint', complete: function(){
										$('#'+elem).hide();	
									}});
								};
								scrollToTop(fn);
								
								// ---- show image ------
								resetCycle();
								// ---- /show image ------
								
							}
							return false;
							break;
						default:
							if(!_link.hasClass('on') && levelOpen == 'restore'){
								
								Cufon.replace('#level2 strong', {color: '#bcbcb9'});
								
								$('.article:visible, .nav02:visible').hide();
								$('#nav01 a, #level2 a').removeClass('on');
								$('#wrap').height('auto');
								_link.addClass('on');
								$('#container').stop().animate({
									'left':342
								}, 200, function(){
									$('#'+elem).fadeIn(200);
								});
								$('#level2').stop().animate({
									'margin-left':0
								}, 200).find('.image').show();
								levelOpen = 'level2';
								
								// ---- show image ------
								pauseCycle();
								// ---- /show image ------
							}
							else if(!_link.hasClass('on')){
								
								Cufon.replace('#level2 strong', {color: '#bcbcb9'});
								
								var fn = function(){
									levelOpen = 'level2';
									$('#nav01 a, #level2 a').removeClass('on');
									_link.addClass('on');
									$('.article:visible').fadeOut(200, function(){
										//$('#wrap').height('100%');
										$('#level2').animate({
											'margin-left':0
										}, 200);
										$('#container').stop().animate({
											'left':342
										}, 200, function(){
											$('.nav02:visible').stop().fadeOut(200, function(){
												$(this).find('.image').show();
												$('#'+elem).fadeIn(200, function(){
													forceShow($(this));
													// ---- setFixed ------
													setFixed();		
													// ---- /setFixed ------
												});
											});
										});											  
									});
								};
								scrollToTop(fn);
							}
							else if(levelOpen == 'restore'){
								levelOpen = 'level3';
								$('#wrap').height('auto');
								$('#container').stop().animate({
									'left':191
								}, {duration: 400, easing: 'easeOutQuint', complete: setFixed });
								
								// ---- show image ------
								pauseCycle();
								// Check what section it was by looking at nav02 for 'on' class
								var resume = $('.nav02').find('.on').attr('href');
								resume = resume.split('#')[1];
								if(hoverable){
									swapImage(resume);
								}
								// ---- /show image ------
							}
							else{
								checkRestoreScroll();
								$('#container').stop().animate({
									'left':788
								}, {duration: 400, easing: 'easeOutQuint',  complete: function(){
									$('#wrap').height('100%');
									$('#nav01').css('top', 'auto');
									setFixed();	
								}});
		
								levelOpen = 'restore';
								
								// ---- show image ------
								resetCycle();
								// ---- /show image ------
							}
							return false;
							
					}
				}
				else{
					
					// Clicking on _any_ level 2 will pause cycle, even if already paused.
					pauseCycle();
					
					// Level 02 nav
					switch(levelOpen){
						case 'level2':
							if(!_link.attr('href').match(/http/)){
								var fn = function(){
									levelOpen = 'level3';
									_link.addClass('on');
									$('.nav02:visible').find('.image').fadeOut(200, function(){
										$('#level2').stop().animate({ 'margin-left':-191 }, {duration: 400, easing: 'easeOutQuint'});
										$('#container').stop().animate({ 'left':191 }, {duration: 400, easing: 'easeOutQuint', complete: function(){
											
											$('#'+elem).stop().fadeIn(200, function(){
												$('#wrap').height('auto');
												// ---- setFixed ------
												setFixed();		
												// ---- /setFixed ------
												if($('html').is('.ie6')){
													$('#footer').css('position','relative');
												}
												//r = false;
											});
											
											// ---- show image ------
											// NB: elem = id
											if(hoverable){
												swapImage(elem);
											}
											// ---- /show image ------
											
										}});
									});
								};
								scrollToTop(fn);
								
								if(_link.parents('li').hasClass('cfn')){
									if(_link.parents('#dutyFreeRetail').length){
										var title = $(this).children('strong');
										Cufon.replace(title, {color: '#b8231a'});
									}
									else{
										var _title = _link.children('strong');
										Cufon.replace(_title, {color: '#525342'});
									}
								}
								return false;
							}
							break;
							
						default:
							
							if(!_link.hasClass('on')){
								if(!_link.attr('href').match(/http/)){
									var fn = function(){
										$('#level2 a').removeClass('on');
										_link.addClass('on');
										Cufon.replace('#level2 strong');
										$('.article:visible').fadeOut(200, function(){
											$('#'+elem).fadeIn(200);
											// ---- setFixed ------
											setFixed();		
											// ---- /setFixed ------
											//r = false;
											// ---- show image ------
											// NB: elem = id
											if(hoverable){
												swapImage(elem);
											}
											// ---- /show image ------
										});
									};
									scrollToTop(fn);
									return false;
								}
							}
							else{
								//r = false;
								return false;
							}
							break;
					}
				}
			
		} // end: if($(e.target))
	
	}
	else{
		return false;
	}// end: if(!r)
	
});// end: click(fn);

/* Level 2 nav hover
-------------------------------------------------------------------------------------------------- */
$('#level2 a').hover(function(){
	// change title
	if($(this).parent('li').hasClass('cfn')){
		if($(this).parents('#dutyFreeRetail').length){
			var title = $(this).children('strong');
			Cufon.replace(title, {color: '#b8231a'});
		}
		else{
			var title = $(this).children('strong');
			Cufon.replace(title, {color: '#525342'});
		}
	}
	// arrow button
	if($(this).find('.arrow').hasClass('more')){
		$(this).find('.arrow').stop().animate({
			'margin-left': -64
		}, 100);
	}
	else{
		$(this).find('.arrow').stop().animate({
			'margin-left': -84
		}, 100);
	}
	
	if(levelOpen == 'level2'){
		// ---- show image ------
		var hoverId = $(this).attr('href');
		if(hoverId.match(/http/)){
			hoverId = $(this).attr('id'); 
		}
		else{
			hoverId = hoverId.split('#')[1];
		}
		t = setTimeout(function(){
			swapImage(hoverId);
		}, 500);
		// ---- /show image ------
	}
	
	$(this).children('.image').addClass('over');
	
}, function(){
	// change title
	if($(this).parent('li').hasClass('cfn') && !$(this).hasClass('on')){
		var title = $(this).children('strong');
		Cufon.replace(title, {color: '#bcbcb9'});
	}
	// arrow button
	$(this).find('.arrow').stop().animate({
		'margin-left': -23
	}, 100);
	
	if(levelOpen == 'level2'){
		clearTimeout(t); 
	}
	
	$(this).children('.image').removeClass('over');
	
});

/* Bind h1
-------------------------------------------------------------------------------------------------- */
$('h1').click(function(){
	if((levelOpen != 'level1') || !levelOpen.match(/restore/)){
		if(levelOpen == 'level2'){
			levelOpen = 'level1';
			$('#nav01 a').removeClass('on');
			$('.nav02').hide();
		}
		else{ // level3
			levelOpen = 'restore';
		}
		setAbsolute();
		checkRestoreScroll();
		$('#container').stop().animate({
			'left':790
		}, {duration: 400, easing: 'easeOutQuint',  complete: function(){
			$('#wrap').height('100%');
			$('#nav01').css('top', 'auto');
		}});
		
		// ---- show image ------
		resetCycle();
		// ---- /show image ------
	}
});

/* Cufon calls
-------------------------------------------------------------------------------------------------- */
Cufon.replace('#footer', { hover:true });
Cufon.replace('#level2 .cfn strong, #level2 .arrow');

/* Window load
-------------------------------------------------------------------------------------------------- */
$(window).load(function(){
	setContentHeight();	
});

/* Window resizing
-------------------------------------------------------------------------------------------------- */
view.resize(function(){
	setContentHeight();
	
	if(!$('html').is('.iefix')){
	
		if(levelOpen != 'level1'){
			//$('#wrap').height('100%');
			view.css('overflow','hidden');
			// get scrollTop, then assign to absolute
			var viewTop = view.scrollTop();
			setAbsolute();
			$('#nav01').css({'top':viewTop});
		}
		
		// while it's resizing, if trigger bound, remove it.
		if(this.resizeTO) { clearTimeout(this.resizeTO); }
		// yet, bind it again, in case resizing stops.
		this.resizeTO = setTimeout(function() {
			$(this).trigger('resizeEnd');
		}, 0);
	}
});

/* Bind resizeEnd actions
-------------------------------------------------------------------------------------------------- */
view.bind('resizeEnd', function() {
	if(levelOpen != 'level1'){
		view.css('overflow','visible');
		
		// needs another way for calc position
		var xpos = $('#container').offset().left;
		$('#nav01').css({
			'position':'fixed',
			'top': 'auto',
			'left': xpos
		});
	}
});

/* Set content height
-------------------------------------------------------------------------------------------------- */
function setContentHeight(){
	var windowHeight = view.height();
	if(windowHeight - 50 > 768){
		windowHeight -= 102;
	}
	if($('html').is('.ie6')){
		$('#content').css('height', windowHeight + 'px');	
	}
	else{
		$('#content').css('min-height', windowHeight + 'px');	
	}
}


/* Image loading
-------------------------------------------------------------------------------------------------- */
// First lets define our arrays.
var homeSet = ['/images/backgrounds/fabric.jpg', '/images/backgrounds/dutyFree.jpg'];
var subSet = [ 
				  '/images/backgrounds/about.jpg',
				  '/images/backgrounds/history.jpg',
				  '/images/backgrounds/careers.jpg',
				  '/images/backgrounds/contact.jpg',
				  '/images/backgrounds/sit.jpg',
				  '/images/backgrounds/work.jpg',
				  '/images/backgrounds/weave.jpg',
				  '/images/backgrounds/lmi.jpg',
				  '/images/backgrounds/aboutDF.jpg',
				  '/images/backgrounds/dfAu.jpg',
				  '/images/backgrounds/dfNz.jpg',
				  '/images/backgrounds/dfWatch.jpg',
				  '/images/backgrounds/dfIl.jpg'
				  ];
// NB: Be careful of image order in subSet array - it DOES matter!

var subSetLoadIndex = 0;
var hoverable = false;

$(window).load(function(){
	loadHomeSet(); // does this need to be in own fn?
});


function loadHomeSet(){
	// load second image
	var img1 = new Image();
	$(img1).attr({'src': homeSet[0] + '?random=' + (new Date()).getTime() }).load(function(){
		$('#homeSet').append('<img style="display:none" src="'+homeSet[0]+'" />');
		
		// load third image
		var img2  = new Image();
		$(img2).attr({'src': homeSet[1] + '?random=' + (new Date()).getTime() }).load(function(){
			$('#homeSet').append('<img style="display:none" src="'+homeSet[1]+'" />');
			
			$('#homeSet').cycle({
				fx:'fade', 
				speed: 1000,
				timeout: 3000
			});

			// start loading the rest.
			loadSubset();
		});
	});
}

function loadSubset(){
	if(subSetLoadIndex < subSet.length){
		var img = $('<img>');
		img.attr({'src': subSet[subSetLoadIndex] + '?random=' + (new Date()).getTime()}).load(function(){																											  
			$('#subSet').append('<img style="display:none" src="'+ subSet[subSetLoadIndex] +'" id="subImg'+subSetLoadIndex+'" />');
			subSetLoadIndex++;
			loadSubset();
		});
	}
	else{
		hoverable = true;
	}
}

function swapImage(id){
	var imgID;
	switch(id){
		case 'about':
			imgID = 'subImg0'; // About JR/Group
			break;
		case 'history':
			imgID = 'subImg1'; // History
			break;
		case 'careers':
			imgID = 'subImg2'; // Careers
			break;
		case 'contact':
			imgID = 'subImg3'; // Contact
			break;
		case 'n02s01':
			imgID = 'subImg4'; // JR/Sit
			break;
		case 'n02s02':
			imgID = 'subImg5'; // JR/Work
			break;
		case 'n02s03':
			imgID = 'subImg6'; // JR/Weave
			break;
		case 'n02s04':
			imgID = 'subImg7'; // JR/Lmi
			break;
		case 'aboutDutyFree':
			imgID = 'subImg8'; // About Duty Free
			break;
		case 'n03s02':
			imgID = 'subImg9'; // Duty Free AU
			break;
		case 'n03s03':
			imgID = 'subImg10'; // Duty Free NZ
			break;
		case 'n03s04':
			imgID = 'subImg11'; // Duty Free Watch
			break;
		case 'n03s05':
			imgID = 'subImg12'; // Duty Free IL
			break;
		default:
			break;
	}
	
	if($('#'+imgID).is(':hidden')){
		$('#subSet img:visible').fadeOut(400);
		$('#'+imgID).fadeIn(400);
		$('#homeSet:visible').fadeOut(100, function(){
			$(this).css({'height' : $(this).height()});
		});
	}
}

function pauseCycle(){
	$('#homeSet').cycle('pause');
}

function resetCycle(){
	$('#subSet img').fadeOut(400);
	$('#homeSet:hidden').css('height', 823).fadeIn(400).cycle('resume');
}

// Force show
function forceShow(elem){
	if(elem.css('opacity') < 1){
		elem.css('opacity',1);
	}
}

// Set Fixed
function setFixed(){
	if(!isIE67){
		var xpos = $('#container').offset().left;
		$('#nav01').css({'position':'fixed', 'left': xpos});
	}
}

// Set Absolute
function setAbsolute(){
	if(!isIE67){
		$('#nav01').css({'position':'absolute', 'left': 'auto'});
	}
}

// Scroll To Top
function scrollToTop(fn){
	var viewTop = view.scrollTop();
	if(viewTop > 0){
		$.smoothScroll({
			scrollTarget: '#wrap',
			speed: 400,
			easing: 'easeOutQuint',
			afterScroll: function(){
				setAbsolute();
				fn();
			}
		});	
	}
	else{
		fn();
	}
}

// Check restore scroll
function checkRestoreScroll(){
	if(view.scrollTop() > 0){
		if(!isIE67){
			$('#nav01').css({
				'position': 'absolute',
				'left': 'auto',
				'top': view.scrollTop()
			});
		}
		return true;
	}
}