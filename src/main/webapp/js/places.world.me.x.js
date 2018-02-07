function initMap() {

    var map = new google.maps.Map(document.getElementById('map'), {
        center: {
            lat: 25.043533,
            lng: 121.532969
        },
        zoom: 13
    });

    // 抓用戶現在位置START
    var infoWindow = new google.maps.InfoWindow({ map: map });
    // Try HTML5 geolocation.
    if (navigator.geolocation) {
        navigator.geolocation.getCurrentPosition(function(position) {
            var pos = {
                lat: position.coords.latitude,
                lng: position.coords.longitude
            };

            infoWindow.setPosition(pos);
            infoWindow.setContent('您目前位置.');
            map.setCenter(pos);
        }, function() {
            handleLocationError(true, infoWindow, map.getCenter());
        });
    } else {
        // Browser doesn't support Geolocation
        handleLocationError(false, infoWindow, map.getCenter());
    }
    // 抓用戶現在位置END
    getJson();
}

function getJson() {
    // 讀取並塞點
    var arr = $("#spotJson").val();
    var obj = JSON.parse(arr);
    var x = $('#spotCounts').val();

    $.each(obj, function(index, coord) {

        var uluru = {lat: coord.lat, lng: coord.lng};

        var image = new google.maps.MarkerImage('getImage?id=' + coord.postID + '&type=post', 
                                                undefined, 
                                                undefined, 
                                                undefined, 
                                                new google.maps.Size(50, 50));

        var contentString = '<div><a href="DisplayOnePost?id=' 
                            + coord.postID + '"><strong>' 
                            + coord.place + '</strong></a><br>' 
                            + coord.postTime; 

        var infowindow = new google.maps.InfoWindow({
            content: contentString
        });

        var marker = new google.maps.Marker({
            position: uluru,
            map: map,
            bounds: false,
            title: 'Uluru (Ayers Rock)'
        });

        marker.addListener('click', function() {
            infowindow.open(map, marker);
        });

    });
}

function handleLocationError(browserHasGeolocation, infoWindow, pos) {
    infoWindow.setPosition(pos);
    infoWindow.setContent(browserHasGeolocation ?
        'Error: The Geolocation service failed.' :
        'Error: Your browser doesn\'t support geolocation.');
}
