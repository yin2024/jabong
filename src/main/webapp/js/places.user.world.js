// 本Javascript，負責將一個個人的打卡點呈現在地圖上。

$(document).ready(function() {

    var arr = $("#spotJson").val();
    var obj = JSON.parse(arr);
    var x = $('#spotCounts').val();

    $('#map_canvas').gmap({
        'center': obj[0].lat + "," + obj[0].lng
    }).bind(
        'init',
        function(ev, map) {
            $('#map_canvas').gmap('addControl', 'control', google.maps.ControlPosition.LEFT_TOP);
            $('#map_canvas').gmap('option', 'zoom', 12);
        });

    $.each(obj, function(index, coord) {

        // 取得圖的地方
        var image = new google.maps.MarkerImage(
            'getImage?id=' + coord.postID + '&type=post',
            undefined, undefined,
            undefined,
            new google.maps.Size(
                40, 40));

        // 打卡內容
        var content = '<div>' + '<img src="getImage?id=' + coord.postID + '&type=post" width="100px" style="border-radius: 20%;"><br>'
                      + '<a href="DisplayOnePost?id=' 
                      + coord.postID + '"><strong>' 
                      + coord.place + '</strong></a><br>' 
                      + coord.postTime + '</div>';


        $('#map_canvas')
            .gmap(
                'addMarker', {
                    'position': coord.lat + "," + coord.lng,
                    'bounds': false, // 會影響到地圖比例的大小
                    'icon': image,
                    'animation': google.maps.Animation.DROP
                })
            .click(
                function() {
                    $('#map_canvas')
                        .gmap(
                            'openInfoWindow', {
                                'content': content
                            }, this);
                });

    });

});
