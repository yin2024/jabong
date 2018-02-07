// 本檔案結合 Dropzone + Cropbox 兩個API，來完成圖片上傳剪裁等工作。 by Ying-Ray. 2016.4.4
// =========Dropzone=========
$(function() {
    // 0. 防止用戶按下Enter就送出表單。
    $(document).keypress(function(e) {
        if (e.keyCode == 13) {
            e.preventDefault();
        }
    });
    // 1. 建立Drop區塊的監聽，丟入或取代等動作。
    $('#dropzone').on('dragover', function() {
        $(this).addClass('hover');
    });
    $('#dropzone').on('dragleave', function() {
        $(this).removeClass('hover');
    });
    // 2. 上傳檔案欄位的input只要onChange的監聽器。
    $('#dropzone input').on('change', function(e) {
        var file = this.files[0];
        $('#dropzone').removeClass('hover');
        // 2-1 檔案必須符合在html時，dropzone標籤內定義的檔案類型，否則直接跳錯誤訊息。
        if (this.accept && $.inArray(file.type, this.accept.split(/, ?/)) == -1) {
            return alert('File type not allowed.');
        }
        // 2-2 成功後，將使用者想上傳的內容，在cropbox區塊進行預覽，若原本就有東西就remove並取代。
        $('#dropzone').addClass('dropped');
        $('#cropbox img').remove();
        // 2-3 檔案類型為圖檔gif|png|jpeg，讓他用img展現出來，其餘則顯示檔案名稱即可。
        if ((/^image\/(gif|png|jpeg)$/i).test(file.type)) {
            var reader = new FileReader(file);
            reader.readAsDataURL(file);
            reader.onload = function(e) {
                var data = e.target.result,
                    // 給予cropbox-img屬性值
                    // $img = $('<img />').attr('src', data).fadeIn();
                    // 預設本來會使用fadeIn()淡入之特效載入圖片，但是跟cropbox功能相衝，所以我取消了。
                    $img = $('<img />').attr({
                        'src': data,
                        'id': 'cropbox',
                        'class': 'img-responsive'
                    });
                // 將本標籤載入到postPic內。
                $('#postPic').html($img);
                // $('#d').val(data); 檢查data來源用。
                // 呼叫cropbox Jcrop方法，建立剪裁器。
                $('#cropbox').Jcrop({
                    aspectRatio: 1, // 剪裁的固定比例。
                    onSelect: updateCoords, // 選取一次就呼叫updateCoords方法。
                    bgOpacity: 0.5,
                    bgColor: 'white',
                    addClass: 'jcrop-light'
                }, function() {
                    var pw = parseInt($('#cropbox').css("width"));
                    var ph = parseInt($('#cropbox').css("height"));
                    var l;
                    var sx = 0;
                    var sy = 0;
                    if (pw > ph) {
                        l = ph;
                        sx = (pw - l) / 2;
                    } else if (pw < ph) {
                        l = pw;
                        sy = (ph - l) / 2;
                    } else {
                        l = ph;
                    }
                    api = this;
                    api.setSelect([sx, sy, l, l]);
                    api.setOptions({ bgFade: true });
                    api.ui.selection.addClass('jcrop-selection');
                });
            };
        } else {
            var ext = file.name.split('.').pop();
            $('#dropzone div').html(ext);
        }
    });
});
// =========Cropbox=========
// 3. 照片剪裁器的功能。
function updateCoords(c) {
    // 3-1 即時提供照片的長寬，因為他會隨著瀏覽器的螢幕大小而調整(RWD)。
    var pw = parseInt($('#cropbox').css("width"));
    var ph = parseInt($('#cropbox').css("height"));
    // 3-2 寫入input用。
    $('#x').val(c.x);
    $('#y').val(c.y);
    $('#w').val(c.w);
    $('#h').val(c.h);
    $('#pw').val(pw);
    $('#ph').val(ph);
};
// 4. 如果沒有選擇就要跳錯誤訊息。
function checkCoords() {
    if (parseInt($('#w').val())) return true;
    alert('Jabong-溫馨提醒：記得框起照片的區塊唷！');
    return false;
};
