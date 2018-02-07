var selectMarkerType = 0;

$(document).ready(function() {
    // 朋友選單 START
    $('#selectFriend').multiselect({
        includeSelectAllOption: true,
        enableFiltering: true
    });

    $('#selectTime').multiselect({});

    $('#selectPic').multiselect({});
    // 朋友選單 END
    // 目前地點的地圖初始化 START
    var mapID = '#map_canvas';
    var centerX = 25.047240;
    var centerY = 121.517080;
    navigator.geolocation.getCurrentPosition(function(position) {
        centerX = position.coords.latitude;
        centerY = position.coords.longitude;
    }, function() {
        handleLocationError(true, infoWindow, map.getCenter());
    });
    $(mapID).gmap({
        'center': centerX + "," + centerY
    });
    $(mapID).gmap().bind(
        'init',
        function(ev, map) {
            $(mapID).gmap('addControl', 'control',
                google.maps.ControlPosition.LEFT_TOP);
            $(mapID).gmap('option', 'zoom', 12);
            $(mapID).gmap('addMarker', {
                'position': centerX + "," + centerY,
                'bounds': false, // true 會影響到地圖比例的大小
            }).click(function() {
                $(mapID).gmap('openInfoWindow', {
                    'content': '您目前所在地!'
                }, this);
            });
        });

    // 目前地點的地圖初始化 END
    // 選取朋友時用AJAX抓取 START
    $('#selectPic').change(function() {
        $(mapID).gmap('clear', 'markers');
        if ($(this).val() == 'post') {
            selectMarkerType = 0;
        } else {
            selectMarkerType = 1;
        }
    });

    $('#selectFriend').change(function() {

        var friendUserID = $(this).val();

        $.ajax({
            type: 'POST',
            url: 'DisplayFriendPlaces',
            data: // 模擬要向Server傳送資料, 
            {
                friendUserID: '' + friendUserID
            },
            success: function(obj) {
                
                $(mapID).gmap('clear', 'markers');

                $.each(obj, function(index, coord) {

                    if (selectMarkerType == 0) {

                        var image = new google.maps.MarkerImage('getImage?id=' + coord.postID + '&type=post', undefined, undefined, undefined, new google.maps.Size(50, 50));

                    } else {

                        var image = new google.maps.MarkerImage('getImage?id=' + coord.userID + '&type=user', undefined, undefined, undefined, new google.maps.Size(50, 50));

                    }

                    $(mapID).gmap('addMarker', {
                        position: coord.lat + "," + coord.lng,
                        bounds: false, // 會影響到地圖比例的大小
                        icon: image,
                    }).click(
                        function() {
                            $(mapID).gmap(
                                'openInfoWindow', {
                                    'content': '<div><a href="DisplayOnePost?id=' + coord.postID + '"><strong>' + coord.place + '</strong></a><br>' + coord.postTime
                                }, this);
                        });
                });
            },
            complete: function() {}
        });
    });
    // 選取朋友時用AJAX抓取 END
});
