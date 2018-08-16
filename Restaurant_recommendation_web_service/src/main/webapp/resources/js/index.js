$(function() {
    var lon = -123.08;
    var lat = 38.38;
    var currentUser;
    function getUser(){
        var url = '/restaurant_recommendation/getuser';
        $.ajax({
            url:url,
            type:"get",
            success:function(data){
                if(data.success){
                    var username = $('#username');
                    var html = 'Welcome  ' + data.currentUser.firstName + '  ' + data.currentUser.lastName + ' ~';
                    username.html(html);
                    currentUser = data.currentUser;
                } else {
                    alert('Fail to load your accoumt information,please retry to login');
                    window.location.href = '/restaurant_recommendation/login';
                }
            }
        })
    }
    getUser();
    getLocationFromIP();
    function getLocationFromIP() {
        // Get location from http://ipinfo.io/json
        var url = 'http://ipinfo.io/json';
        $.ajax({
			url:url,
			type:"get",
			success:function(data){
                if ('loc' in data) {
                    var loc = data.loc.split(',');
                    lat = loc[0];
                    lon = loc[1];
                    loadcarouselImg();
                    searchItems();
                } else {
                    console.warn('Getting location by IP failed.');
                }
			}
		});
    }
    $('#nearby a').click(function () {
        $('#nearby').attr("class", "active");
        $('#myFavorite').attr("class", "#");
        $('#recommendation').attr("class", "#");
        searchItems();
    });

    $('#myFavorite a').click(function () {
        $('#nearby').attr("class", "#");
        $('#myFavorite').attr("class", "active");
        $('#recommendation').attr("class", "#");
        loadFavoriteItems();
    });


    $('#recommendation a').click(function () {
        $('#nearby').attr("class", "#");
        $('#myFavorite').attr("class", "#");
        $('#recommendation').attr("class", "active");
        loadRecommendationItems();
    });
    function loadcarouselImg(){
        var carouse = $('#carousel-inner');
        $.ajax({
            url:"/restaurant_recommendation/carouselImg?lat=" + lat + "&lon=" + lon,
            type:"get",
            dataType:"json",
            success: function(data){
                if(data.success){
                    var html = '';
                    for(var i = 0; i < data.itemList.length;i++){
                        var item = data.itemList[i];
                        var tempUrl = '<div class="carousel-item';
                        if(i == 0){
                            tempUrl += ' active';
                        }
                        tempUrl += ('"' + '><a href="' + item.url + '" ><img src="' + item.imageUrl
                            + '"></a></div>');
                        html += tempUrl;
                    }
                    carouse.html(html);
                } else {
                    alert(data.errMsg);
                }
            }
        })
    }
    function searchItems(){
        var items_container = $('#items_container');
        items_container.html('<p class="notice"><i class="fa fa-spinner fa-spin"></i> '+ '<h1>loading nearby re' +
            'staurant</h1>' + '</p>');
    	$.ajax({
			url:"/restaurant_recommendation/search?lat=" + lat + "&lon=" + lon,
			type:"get",
			dataType:"json",
			success: function(data){
				if(data.success){
                    var html = '';
					for(var i = 0; i < data.itemList.length; i++){
						var item = data.itemList[i];
                        var tempHtml = '<li class="items_list"><div class="col-sm-6 col-md-4"><div class="thumbnail">'
                            + '<div class="thumbnail">' + '<a href="' + item.url + '">' +
							'<img src="' + item.imageUrl + '"></a><div class="caption"><h4>'
							+ '<a class="item-name" href="' + item.url +'">' + item.name + '</a>' +
							'</h4><p class="item-category">' + item.categories.join(', ')
                            + '</p><div class="stars">' + generateStars(item.rating) + '</div><p class="item-address">' + item.address
                            + '<br/></p><p>' + ' <div align="left" style="float:left">'
                            + '<button type="button" onclick="unsetFavorite(this)" class="btn btn-default btn-lg" id="dislikebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> </button></div>'
                            + '<div align="right">'
                            + '<button type="button" onclick="setFavorite(this)" class="btn btn-default btn-lg" id="likebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> </button></div>'
                            +'</div></p>'
							+ '</div></div></div></li>';
                        html += tempHtml;
					}
                    items_container.html(html);
				}
			}
		});
	}
	function loadFavoriteItems(){
        var items_container = $('#items_container');
        items_container.html('<p class="notice"><i class="fa fa-spinner fa-spin"></i> '+ '<h1>loading your favorite re' +
            'staurant</h1>' + '</p>');
        $.ajax({
            url:"/restaurant_recommendation/getFavorite",
            type:"get",
            dataType:"json",
            success: function(data){
                if(data.success){
                    var html = '';
                    for(var i = 0; i < data.itemList.length; i++){
                        var item = data.itemList[i];
                        var tempHtml = '<li class="items_list"><div class="col-sm-6 col-md-4"><div class="thumbnail">'
                            + '<div class="thumbnail">' + '<a href="' + item.url + '">' +
                            '<img src="' + item.imageUrl + '"></a><div class="caption"><h4>'
                            + '<a class="item-name" href="' + item.url +'">' + item.name + '</a>' +
                            '</h4><p class="item-category">' + item.categories.join(', ')
                            + '</p><div class="stars">' + generateStars(item.rating) + '</div><p class="item-address">' + item.address
                            + '<br/></p><p>' + ' <div align="left" style="float:left">'
                            + '<button type="button" onclick="unsetFavorite(this)" class="btn btn-default btn-lg" id="dislikebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> </button></div>'
                            + '<div align="right">'
                            + '<button type="button" onclick="setFavorite(this)" class="btn btn-danger btn-lg" id="likebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> </button></div>'
                            +'</div></p>'
                            + '</div></div></div></li>';
                        html += tempHtml;
                    }
                    items_container.html(html);
                }
            }
        });
    }

    function loadRecommendationItems(){
        var items_container = $('#items_container');
        items_container.html('<p class="notice"><i class="fa fa-spinner fa-spin"></i> '+ '<h1>Recommeding restaurants for you</h1>' +
            '</p>');
        $.ajax({
            url:"/restaurant_recommendation/recommendation?lat=" + lat + "&lon=" + lon,
            type:"get",
            dataType:"json",
            success: function(data){
                if(data.success){
                    var html = '';
                    for(var i = 0; i < data.itemList.length; i++){
                        var item = data.itemList[i];
                        var tempHtml = '<li class="items_list"><div class="col-sm-6 col-md-4"><div class="thumbnail">'
                            + '<div class="thumbnail">' + '<a href="' + item.url + '">' +
                            '<img src="' + item.imageUrl + '"></a><div class="caption"><h4>'
                            + '<a class="item-name" href="' + item.url +'">' + item.name + '</a>' +
                            '</h4><p class="item-category">' + item.categories.join(', ')
                            + '</p><div class="stars">' + generateStars(item.rating) + '</div><p class="item-address">' + item.address
                            + '<br/></p><p>' + ' <div align="left" style="float:left">'
                            + '<button type="button" onclick="unsetFavorite(this)" class="btn btn-default btn-lg" id="dislikebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-remove" aria-hidden="true"></span> </button></div>'
                            + '<div align="right">'
                            + '<button type="button" onclick="setFavorite(this)" class="btn btn-default btn-lg" id="likebutton_' + item.itemId
                            + '" item_id = "' + item.itemId + '"><span class="glyphicon glyphicon-heart" aria-hidden="true"></span> </button></div>'
                            +'</div></p>'
                            + '</div></div></div></li>';
                        html += tempHtml;
                    }
                    items_container.html(html);
                }
            }
        });
    }
    function generateStars(num){
        var tempHtml = ''
        for(var i = 0; i < num; i++){
            tempHtml += ' <i class="fa fa-star"></i>';
        }
        if (('' + num).match(/\.5$/)) {
            tempHtml += '<i class="fa fa-star-half-o"></i>';
        }
        return tempHtml;
    }

})
