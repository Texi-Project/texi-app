jQuery(document).ready(function ($) {

	"use strict";

//------- Notifications Dropdowns
	$('.top-area > .setting-area > li > a').on("click", function () {
		var $parent = $(this).parent('li');
		$parent.siblings().children('div').removeClass('active');
		$(this).siblings('div').addClass('active');
		return false;
	});


//------- remove class active on body
	$("body *").not('.top-area > .setting-area > li > a').on("click", function () {
		$(".top-area > .setting-area > li > div").not('.searched').removeClass('active');

	});


//--- user setting dropdown on topbar	
	$('.user-img').on('click', function () {
		$('.user-setting').toggleClass("active");
	});

//--- side message box	
	/*$('.friendz-list > li, .chat-users > li').on('click', function () {
		$('.chat-box').addClass("show");
		return false;
	});
	$('.close-mesage').on('click', function () {
		$('.chat-box').removeClass("show");
		return false;
	});*/

//------ scrollbar plugin
	if ($.isFunction($.fn.perfectScrollbar)) {
		$('.dropdowns, .twiter-feed, .invition, .followers, .chatting-area, .peoples, #people-list, .chat-list > ul, .message-list, .chat-users, .left-menu').perfectScrollbar();
	}

	/*--- socials menu scritp ---*/
	$('.trigger').on("click", function () {
		$(this).parent(".menu").toggleClass("active");
	});

	/*--- emojies show on text area ---*/
	$('.add-smiles > span').on("click", function () {
		$(this).parent().siblings(".smiles-bunch").toggleClass("active");
	});

// delete notifications
	$('.notification-box > ul li > i.del').on("click", function () {
		$(this).parent().slideUp();
		return false;
	});

	/*--- socials menu scritp ---*/
	$('.f-page > figure i').on("click", function () {
		$(".drop").toggleClass("active");
	});

//===== Search Filter =====//
	(function ($) {
		// custom css expression for a case-insensitive contains()
		jQuery.expr[':'].Contains = function (a, i, m) {
			return (a.textContent || a.innerText || "").toUpperCase().indexOf(m[3].toUpperCase()) >= 0;
		};

		function listFilter(searchDir, list) {
			var form = $("<form>").attr({"class": "filterform", "action": "#"}),
				input = $("<input>").attr({
					"class": "filterinput",
					"type": "text",
					"placeholder": "Search Contacts..."
				});
			$(form).append(input).appendTo(searchDir);

			$(input)
				.change(function () {
					var filter = $(this).val();
					if (filter) {
						$(list).find("li:not(:Contains(" + filter + "))").slideUp();
						$(list).find("li:Contains(" + filter + ")").slideDown();
					} else {
						$(list).find("li").slideDown();
					}
					return false;
				})
				.keyup(function () {
					$(this).change();
				});
		}

//search friends widget
		$(function () {
			listFilter($("#searchDir"), $("#people-list"));
		});
	}(jQuery));

//progress line for page loader
	$('body').show();
	NProgress.start();
	setTimeout(function () {
		NProgress.done();
		$('.fade').removeClass('out');
	}, 2000);

//--- bootstrap tooltip	
	$(function () {
		$('[data-toggle="tooltip"]').tooltip();
	});

// Sticky Sidebar & header
	if ($(window).width() < 769) {
		jQuery(".sidebar").children().removeClass("stick-widget");
	}

	if ($.isFunction($.fn.stick_in_parent)) {
		$('.stick-widget').stick_in_parent({
			parent: '#page-contents',
			offset_top: 60,
		});


		$('.stick').stick_in_parent({
			parent: 'body',
			offset_top: 0,
		});

	}

	/*--- topbar setting dropdown ---*/
	$(".we-page-setting").on("click", function () {
		$(".wesetting-dropdown").toggleClass("active");
	});

	/*--- topbar toogle setting dropdown ---*/
	$('#nightmode').on('change', function () {
		if ($(this).is(':checked')) {
			// Show popup window
			$(".theme-layout").addClass('black');
		} else {
			$(".theme-layout").removeClass("black");
		}
	});

//chosen select plugin
	if ($.isFunction($.fn.chosen)) {
		$("select").chosen();
	}

//----- add item plus minus button
	if ($.isFunction($.fn.userincr)) {
		$(".manual-adjust").userincr({
			buttonlabels: {'dec': '-', 'inc': '+'},
		}).data({'min': 0, 'max': 20, 'step': 1});
	}

	if ($.isFunction($.fn.loadMoreResults)) {
		$('.loadMore').loadMoreResults({
			displayedItems: 3,
			showItems: 1,
			button: {
				'class': 'btn-load-more',
				'text': 'Load More'
			}
		});
	}
	//===== owl carousel  =====//
	if ($.isFunction($.fn.owlCarousel)) {
		$('.sponsor-logo').owlCarousel({
			items: 6,
			loop: true,
			margin: 30,
			autoplay: true,
			autoplayTimeout: 1500,
			smartSpeed: 1000,
			autoplayHoverPause: true,
			nav: false,
			dots: false,
			responsiveClass: true,
			responsive: {
				0: {
					items: 3,
				},
				600: {
					items: 3,

				},
				1000: {
					items: 6,
				}
			}

		});
	}

// slick carousel for detail page
	if ($.isFunction($.fn.slick)) {
		$('.slider-for-gold').slick({
			slidesToShow: 1,
			slidesToScroll: 1,
			arrows: false,
			slide: 'li',
			fade: false,
			asNavFor: '.slider-nav-gold'
		});

		$('.slider-nav-gold').slick({
			slidesToShow: 3,
			slidesToScroll: 1,
			asNavFor: '.slider-for-gold',
			dots: false,
			arrows: true,
			slide: 'li',
			vertical: true,
			centerMode: true,
			centerPadding: '0',
			focusOnSelect: true,
			responsive: [
				{
					breakpoint: 768,
					settings: {
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: true,
						vertical: false,
						centerMode: true,
						dots: false,
						arrows: false
					}
				},
				{
					breakpoint: 641,
					settings: {
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: true,
						vertical: true,
						centerMode: true,
						dots: false,
						arrows: false
					}
				},
				{
					breakpoint: 420,
					settings: {
						slidesToShow: 3,
						slidesToScroll: 1,
						infinite: true,
						vertical: false,
						centerMode: true,
						dots: false,
						arrows: false
					}
				}
			]
		});
	}

//---- responsive header

	$(function () {

		//	create the menus
		$('#menu').mmenu();
		$('#shoppingbag').mmenu({
			navbar: {
				title: 'General Setting'
			},
			offCanvas: {
				position: 'right'
			}
		});

		//	fire the plugin
		$('.mh-head.first').mhead({
			scroll: {
				hide: 200
			}

		});
		$('.mh-head.second').mhead({
			scroll: false
		});


	});

//**** Slide Panel Toggle ***//
	$("span.main-menu").on("click", function () {
		$(".side-panel").toggleClass('active');
		$(".theme-layout").toggleClass('active');
		return false;
	});

	$('.theme-layout').on("click", function () {
		$(this).removeClass('active');
		$(".side-panel").removeClass('active');
	});


// login & register form
	$('button.signup').on("click", function () {
		$('.login-reg-bg').addClass('show');
		return false;
	});

	$('.already-have').on("click", function () {
		$('.login-reg-bg').removeClass('show');
		return false;
	});

//----- count down timer		
	if ($.isFunction($.fn.downCount)) {
		$('.countdown').downCount({
			date: '11/12/2018 12:00:00',
			offset: +10
		});
	}

	/** Post a Comment **/
	jQuery(".post-comt-box textarea").on("keydown", function (event) {

		if (event.keyCode == 13) {


			// console.log("Value : "+v);
			var comment = jQuery(this).val();
			var currentTime=  new Date();
			var parent = jQuery(".showmore").parent("li");
			var comment_HTML = '	<li><div class="comet-avatar"></div><div class="we-comment"><div class="coment-head"><h5><a href="time-line.html" title="">Me</a></h5><span>'+currentTime+'</span><a class="we-reply" href="#" title="Reply"><i class="fa fa-reply"></i></a></div><p>' + comment + '</p></div></li>';

			$(comment_HTML).insertBefore(parent);
			jQuery(this).val('');
			let v = $(this).parent().find(".something").val();
			var commentjson={};
			commentjson["commentText"]=comment;
			commentjson["postId"]=v;
			$.ajax({
				type:"POST",
				contentType:"application/json",
				url:"/comment/add",
				data:JSON.stringify(commentjson),
				dataType:'json',
				cache:false,
				timeout:600000,
				success: function (data) {

				},
				error:function(e){

				}
			});
			// $(".commentForm").submit();


		}
	});

	// $('.like').on("click",function () {
	// 	let v = $(this).parent().find(".like").val();
	// 	$(this).val(v+1);
	// })

//inbox page 	
//***** Message Star *****//  
	$('.message-list > li > span.star-this').on("click", function () {
		$(this).toggleClass('starred');
	});


//***** Message Important *****//
	$('.message-list > li > span.make-important').on("click", function () {
		$(this).toggleClass('important-done');
	});


// Listen for click on toggle checkbox
	$('#select_all').on("click", function (event) {
		if (this.checked) {
			// Iterate each checkbox
			$('input:checkbox.select-message').each(function () {
				this.checked = true;
			});
		} else {
			$('input:checkbox.select-message').each(function () {
				this.checked = false;
			});
		}
	});


	$(".delete-email").on("click", function () {
		$(".message-list .select-message").each(function () {
			if (this.checked) {
				$(this).parent().slideUp();
			}
		});
	});

// change background color on hover
	$('.category-box').hover(function () {
		$(this).addClass('selected');
		$(this).parent().siblings().children('.category-box').removeClass('selected');
	});


//------- offcanvas menu 

	const menu = document.querySelector('#toggle');
	const menuItems = document.querySelector('#overlay');
	const menuContainer = document.querySelector('.menu-container');
	const menuIcon = document.querySelector('.canvas-menu i');

	function toggleMenu(e) {
		menuItems.classList.toggle('open');
		menuContainer.classList.toggle('full-menu');
		menuIcon.classList.toggle('fa-bars');
		menuIcon.classList.add('fa-times');
		e.preventDefault();
	}

	if (menu) {
		menu.addEventListener('click', toggleMenu, false);
	}

// Responsive nav dropdowns
	$('.offcanvas-menu li.menu-item-has-children > a').on('click', function () {
		$(this).parent().siblings().children('ul').slideUp();
		$(this).parent().siblings().removeClass('active');
		$(this).parent().children('ul').slideToggle();
		$(this).parent().toggleClass('active');
		return false;
	});
// new post box	
	$(".new-postbox").click(function () {
		$(".postoverlay").fadeIn(500);
	});
	$(".postoverlay").not(".new-postbox").click(function () {
		$(".postoverlay").fadeOut(500);
	});
	$("[type = submit]").click(function () {
		var post = $("textarea").val();
		$("<p class='post'>" + post + "</p>").appendTo("section");
	});
	$(".heart.fa").click(function() {
		$(this).toggleClass("fa-heart fa-heart-o");
	});

	(function() {
		// var ws = new SockJS("http://127.0.0.1:15674/stomp");
		let ws = new WebSocket('ws://127.0.0.1:15674/ws');
		let client = Stomp.over(ws);

		client.heartbeat.outgoing = 0;
		client.heartbeat.incoming = 0;

		let onDebug = function(m) {
			console.log("DEBUG", m);
		};

		let onConnect = function() {
			// subscribe to the exchange named "posts-exchange" and the routing key "posts"
			client.subscribe("/exchange/posts-exchange/posts", function(d) {
				console.log(JSON.parse(d.body));
				setTimeout(checkForNotifications, 1000)
			})
		};

		let onError = function(e) {
			console.log("ERROR", e);
			setTimeout(stompConnect, 5000);
			console.log('STOMP: Reconnecting in 5 seconds');
		};

		function stompConnect() {
			client.connect("guest", "guest", onConnect, onError, "/");
		}

		client.debug = onDebug;
		stompConnect()
	})();

	// setInterval(checkForNotifications, 10000);

	function checkForNotifications() {
		const endpoint = window.location.origin + "/notifications/new";

		$.ajax({
			url: endpoint,
			type: 'post',
		})
			.done(function (notifications) {
				console.log(notifications);

				if (notifications.length > 0) updateNotificationDisplay(notifications);

				$("#ctr1").html(notifications.length);
				$("#ctr2").html(notifications.length);
			})
			.fail(function (xhr, status, errorThrown) {
				console.log(`Status: ${status}\nError: ${errorThrown}`);
				// console.dir(xhr);
			});

		/*fetch(endpoint, {
			method: 'POST',
			headers: {
				'Content-Type': 'application/json'
			}
		})
			.then(res => res.json())
			.then((notifications) => {
				console.log(notifications)
			})
			.catch(err => {
				console.log(`Error: ${err}`);
			})*/
		return false;
	}

	function updateNotificationDisplay(notifications) {
		const div = $("div.dropdowns");
		const ul = $("ul.drops-menu");
		div.html("");
		div.append(`<span>${notifications.length} New Notifications</span>`);
		$.each(notifications, function(key, notification) {
			let owner = notification.owner;
			ul.html(""); // clear user list container first
			ul.append(`<li>
				<a href="/notifications/" title="">
				<img alt="" src="${owner.photoUrl}">
				<div class="mesg-meta">
				<h6></h6>
				<span>${owner.firstName} ${owner.lastName} just posted on Texi. </span>
				<i>A few seconds ago</i>
				</div>
				</a>
				<span class="tag green">New</span>
				</li>`)
		});
		div.append(ul);
		div.append(`<a class="more-mesg" href="/notifications/" title="">view more</a>`)
	}

	$(".heart.fa").click(function() {
		let v = $(this).parent().find("#count").val();
		$(this).toggleClass("fa-heart fa-heart-o");
		$(this).parent().find("#count").val(v+1);
	});

});//document ready end







