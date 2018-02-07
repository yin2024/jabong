var workerTimeOut;
var msg = [];
var count = 0;
$(document).ready(function worker() {
    $.ajax({
        type: 'GET',
        url: 'TopNotify',

        success: function(obj) {

            if (count == 0 || obj[0]['notifyID'] !== msg[0]['notifyID']) {
                msg = [];
                msg = obj;

                $.each(msg, function(index, element) {

                    // 1. 通知數 - 數字 放入#notify_num內：
                    $("#notify_num").html('');
                    if (obj.length == 0) {
                        $("#notify_num").append('');
                    } else if (obj.length > msg.length) {
                        $("#notify_num").append('<span class="badge" style="background:orange;">' + obj.length + '</span>');
                    }
                    $("#notify_num").append('<a><span class="glyphicon glyphicon-envelope" style="color:#888;"></span><span class="caret" style="color:#888;"></span></a>');

                    // 2. 通知項 - 內容 放入#notify_info內：

                    var s1 = '<li role="presentation" class="dropdown-header"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;Unread</li>';
                    var s2 = '<li><a href="AcceptFriends"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 向您提出 好友邀請</a></li>';
                    var s3 = '<li><a href="DisplayPosts?id' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 說您的文章讚</a></li>';
                    var s4 = '<li><a href="DisplayPosts?id' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 回應您的文章</a></li>';
                    var s5 = '<li class="divider"></li><li><a href="#">查詢所有通知</a></li>';
                    console.log(element.name);
                    $("#notify_info").html(s1);
                    if (element.type == 1) {
                        $("#notify_info").append(s2 + s5);
                    } else if (element.type == 2) {
                        $("#notify_info").append(s3 + s5);
                    } else if (element.type == 3) {
                        $("#notify_info").append(s4 + s5);
                    }
                });
            }

            count++;
            console.log(count);

        },

        complete: function() {
            workerTimeOut = setTimeout(worker, 5000);
        }
    });
});
// <li role="presentation" class="dropdown-header"><span
//                             class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;Unread</li>

//                         <li class="divider"></li>
//                         <li><a href="#">查詢所有通知</a></li>
