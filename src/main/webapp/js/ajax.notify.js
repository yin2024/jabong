// AJAX - NOTIFICATION by yin
// 1. 準備參數：重複工作、執行次數、現在的通知列
var workerTimeOut;
var times = 1;
var now = [];

// 2. 載入頁面：執行notifyWorker()
$(document).ready(function notifyWorker() {
    $.ajax({
        type: 'GET',
        url: 'TopNotify',
        success: function(obj) {
            // 第一次執行 與 接收進來的obj與用戶螢幕上的now不一樣，就要更新頁面
            if (times == 1 || obj != now) {
                updateList(obj);
            }
            times++;
            console.log(times);
        },
        complete: function() {
            workerTimeOut = setTimeout(notifyWorker, 5000);
        }
    });
});

var updateList = function updateList(obj) {

	now = obj;
    var mail = '<a><span class="glyphicon glyphicon-envelope" style="color:#888;"></span><span class="caret" style="color:#888;"></span></a>';
    var title = '<li role="presentation" class="dropdown-header"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;Unread</li>';
    var divider = '<li class="divider"></li><li><a href="Notification">查詢所有通知</a></li>';

    // 先清空notify_num並放入信封圖像
    $('#notify_num').html(mail);
    if (obj.length == 0) {
        $("#notify_info").html(title);
        $("#notify_info").append(divider);
    } else {
    	var num = '<span class="badge" style="background:orange;">' + obj.length + '</span>';
    	$('#notify_num').prepend(num);
    	$("#notify_info").html(title);
        $.each(obj, function(index, element) {
            var friend = '<li><a href="AcceptFriends"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 向您提出 好友邀請</a></li>';
            var like = '<li><a href="DisplayOnePost?id=' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 說您的文章讚</a></li>';
            var resp = '<li><a href="DisplayOnePost?id=' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 回應您的文章</a></li>';
            if (element.type == 1) {
                $("#notify_info").append(friend);
            } else if (element.type == 2) {
                $("#notify_info").append(like);
            } else if (element.type == 3) {
                $("#notify_info").append(resp);
            }
        });
        $("#notify_info").append(divider);
    }
}
