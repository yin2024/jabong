$(document).ready(function() {
    // 朋友選單 START
    $('#selectFriend').multiselect({
        includeSelectAllOption: true,
        enableFiltering: true
    });

    $('#selectTime').multiselect({
        includeSelectAllOption: true,
        enableFiltering: true
    });
    // 朋友選單 END

    $('#selectFriend').change(function() {
        var s = $(this).val();
        for (var v in $(this).val()) {
            var arr = $("#spotJson" + s[v]).val();
            var obj = JSON.parse(arr);
            var x = $('#spotCounts').val();

            $.each(obj, function(index, coord) {
                var image = {
                    url: 'getImage?id=' + coord.postID + '&type=post',
                    // This marker is 20 pixels wide by 32 pixels high.
                    size: new google.maps.Size(50, 50),
                    // The origin for this image is (0, 0).
                    origin: new google.maps.Point(0, 0),
                    // The anchor for this image is the base of the flagpole at (0, 32).
                    anchor: new google.maps.Point(0, 0)
                };
                $('#map_canvas').gmap('addMarker', {
                    'position': coord.lat + "," + coord.lng,
                    'bounds': false, // 會影響到地圖比例的大小
                    'icon': new google.maps.MarkerImage('getImage?id=' + coord.postID + '&type=post', undefined, undefined, undefined, new google.maps.Size(50, 50)),
                }).click(
                    function() {
                        $('#map_canvas').gmap(
                            'openInfoWindow', {
                                'content': '<div><a href="DisplayOnePost?id=' + coord.postID + '"><strong>' + coord.place + '</strong></a><br>' + coord.postTime
                            }, this);
                    });
            });
        }
    });
    // 朋友選單 END

});
var centerX;
var centerY;
// var centerX = 25.047240;
// var centerY = 121.517080;

navigator.geolocation.getCurrentPosition(success, error, options);

var options = {
    enableHighAccuracy: true,
    timeout: 5000,
    maximumAge: 0
};

function success(pos) {

    var crd = pos.coords;
    centerX = crd.latitude;
    centerY = crd.longitude;

    $('#map_canvas').gmap({
        'center': centerX + "," + centerY
    });
    $('#map_canvas').gmap().bind(
        'init',
        function(ev, map) {
            $('#map_canvas').gmap('addControl', 'control',
                google.maps.ControlPosition.LEFT_TOP);
            $('#map_canvas').gmap('option', 'zoom', 12);
            $('#map_canvas').gmap('addMarker', {
                'position': centerX + "," + centerY,
                'bounds': false, // true 會影響到地圖比例的大小
            }).click(function() {
                $('#map_canvas').gmap('openInfoWindow', {
                    'content': '您目前所在地!'
                }, this);
            });
        });


}

function error(err) {
    console.warn('ERROR(' + err.code + '): ' + err.message);
}
