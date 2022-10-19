$(function () {

	sliderHomepage();
	productDetailGallery(4000);
	productQuickViewGallery();
	menuSliding();
	productDetailSizes();
	utils();

});

/* slider homepage */

function sliderHomepage() {
	// if ($('#slider').length) {
	// 	var owl = $("#slider");

	// 	owl.owlCarousel({
	// 		autoPlay: 3000,
	// 		dots: true,
	// 		nav: false,
	// 		responsiveClass: true,
	// 		responsive: {
	// 			0: {
	// 				items: 2
	// 			},
	// 			600: {
	// 				items: 3
	// 			},
	// 			900: {
	// 				items: 4
	// 			}
	// 		},

	// 	});
	// }
	if ($('.hero-2-slider').length) {
		$('.hero-2-slider').owlCarousel({
			loop: true,
			items: 1,
			autoplay: true,
			autoplayHoverPause: true,
			navSpeed: 1000,
			navText: [
				"<i class='fa fa-angle-left'></i>",
				"<i class='fa fa-angle-right'></i>"
			],
			responsive: {
				0: {
					items: 1,
					nav: false,
					dots: true
				},
				600: {
					items: 1,
					nav: false,
					dots: true
				},
				1120: {
					items: 1,
					dots: false,
					nav: true
				}
			},
			responsiveClass: false
		});
	}

}

/* menu sliding */

function menuSliding() {


	$('.dropdown').on('show.bs.dropdown', function (e) {

			if ($(window).width() > 750) {
				$(this).find('.dropdown-menu').first().stop(true, true).slideDown();

			} else {
				$(this).find('.dropdown-menu').first().stop(true, true).show();
			}
		}

	);
	$('.dropdown').on('hide.bs.dropdown', function (e) {
		if ($(window).width() > 750) {
			$(this).find('.dropdown-menu').first().stop(true, true).slideUp();
		} else {
			$(this).find('.dropdown-menu').first().stop(true, true).hide();
		}
	});

}

/* picture zoom */

function pictureZoom() {

	$('.product .image, .post .image').each(function () {
		var imgHeight = $(this).find('img').height();
		$(this).height(imgHeight);
	});
}

// ------------------------------------------------------- //
// Open & Close Search Panel
// ------------------------------------------------------ //
$('.search').on('click', function () {
	$('.search-area').fadeIn();
});

$('.search-area .close-btn').on('click', function () {
	$('.search-area').fadeOut();
});

function utils() {

	/* tooltips */

	$('[data-toggle="tooltip"]').tooltip();

	/* click on the box activates the radio */

	$('#checkout').on('click', '.box.shipping-method, .box.payment-method', function (e) {
		var radio = $(this).find(':radio');
		radio.prop('checked', true);
	});
	/* click on the box activates the link in it */

	$('.box.clickable').on('click', function (e) {

		window.location = $(this).find('a').attr('href');
	});
	/* external links in new window*/

	$('.external').on('click', function (e) {

		e.preventDefault();
		window.open($(this).attr("href"));
	});
	/* animated scrolling */

	$('.scroll-to, .scroll-to-top').click(function (event) {

		var full_url = this.href;
		var parts = full_url.split("#");
		if (parts.length > 1) {

			scrollTo(full_url);
			event.preventDefault();
		}
	});

	function scrollTo(full_url) {
		var parts = full_url.split("#");
		var trgt = parts[1];
		var target_offset = $("#" + trgt).offset();
		var target_top = target_offset.top - 100;
		if (target_top < 0) {
			target_top = 0;
		}

		$('html, body').animate({
			scrollTop: target_top
		}, 1000);
	}
}

/* product detail gallery */

function productDetailGallery(confDetailSwitch) {
	$('#productMain .thumb:first').addClass('active');
	timer = setInterval(autoSwitch, confDetailSwitch);
	$("#productMain .thumb").click(function (e) {

		switchImage($(this));
		clearInterval(timer);
		timer = setInterval(autoSwitch, confDetailSwitch);
		e.preventDefault();
	});
	$('#productMain #mainImage').hover(function () {
		clearInterval(timer);
	}, function () {
		timer = setInterval(autoSwitch, confDetailSwitch);
	});

	function autoSwitch() {
		var nextThumb = $('#productMain .thumb.active').closest('div').next('div').find('.thumb');
		if (nextThumb.length == 0) {
			nextThumb = $('#productMain .thumb:first');
		}
		switchImage(nextThumb);
	}

	function switchImage(thumb) {

		$('#productMain .thumb').removeClass('active');
		var bigUrl = thumb.attr('href');
		thumb.addClass('active');
		$('#productMain #mainImage img').attr('src', bigUrl);
	}
}

function productQuickViewGallery() {

	$('.quick-view').each(function () {

		var element = $(this);

		element.find('.thumb:first').addClass('active');


		element.find(".thumb").click(function (e) {

			switchImage($(this));
			e.preventDefault();
		});

	});

	function switchImage(thumb) {

		thumb.parents('.quick-view').find('.thumb').removeClass('active');
		var bigUrl = thumb.attr('href');
		thumb.addClass('active');
		thumb.parents('.quick-view').find('.quick-view-main-image img').attr('src', bigUrl);
	}
}

/* product detail sizes */

function productDetailSizes() {
	$('.sizes a').click(function (e) {
		e.preventDefault();
		$('.sizes a').removeClass('active');
		$('.size-input').prop('checked', false);
		$(this).addClass('active');
		$(this).next('input').prop('checked', true)
	});
}


$.fn.alignElementsSameHeight = function () {
	$('.same-height-row').each(function () {

		var maxHeight = 0;
		var children = $(this).find('.same-height');
		children.height('auto');
		if ($(window).width() > 768) {
			children.each(function () {
				if ($(this).innerHeight() > maxHeight) {
					maxHeight = $(this).innerHeight();
				}
			});
			children.innerHeight(maxHeight);
		}

		maxHeight = 0;
		children = $(this).find('.same-height-always');
		children.height('auto');
		children.each(function () {
			if ($(this).innerHeight() > maxHeight) {
				maxHeight = $(this).innerHeight();
			}
		});
		children.innerHeight(maxHeight);
	});
}

// ------------------------------------------------------ //
// For demo purposes, can be deleted
// ------------------------------------------------------ //

var stylesheet = $('link#theme-stylesheet');
$("<link id='new-stylesheet' rel='stylesheet'>").insertAfter(stylesheet);
var alternateColour = $('link#new-stylesheet');

if ($.cookie("theme_csspath")) {
	alternateColour.attr("href", $.cookie("theme_csspath"));
}

$("#colour").change(function () {

	if ($(this).val() !== '') {

		var theme_csspath = 'css/style.' + $(this).val() + '.css';

		alternateColour.attr("href", theme_csspath);

		$.cookie("theme_csspath", theme_csspath, {
			expires: 365,
			path: document.URL.substr(0, document.URL.lastIndexOf('/'))
		});

	}

	return false;
});