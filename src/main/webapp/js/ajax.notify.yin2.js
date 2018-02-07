var workerTimeOut;
var msg = [];
var count = 0;
$(document).ready(function worker() {
    $.ajax({
        type: 'GET',
        url: 'TopNotify',

        success: function(obj) {

            // 1. 通知項號碼
            if (count == 0) {
                $("#notify_num").empty();
                var $select = $('<a>').appendTo($('#notify_num'));
                if (obj.length > msg.length) {
                    $select.append('<span class="badge" style="background:orange;">' + obj.length + '</span>');
                }
                $select.append('<span class="glyphicon glyphicon-envelope"></span>');
                $select.append('<span class="caret"></span>');
                $select.append('</a>');
            } else if (obj.length > msg.length) {
                $("#notify_num").empty();
                var $select = $('<a>').appendTo($('#notify_num'));
                if (obj.length > msg.length) {
                    $select.append('<span class="badge" style="background:orange;">' + obj.length + '</span>');
                }
                $select.append('<span class="glyphicon glyphicon-envelope" style="color:#888;"></span>');
                $select.append('<span class="caret" style="color:#888;"></span>');
                $select.append('</a>');
            }

            // 2. 通知項內容

            if (count == 0 || obj[0]['notifyID'] > msg[0]['notifyID']) {
                msg = [];
                msg = obj;
                
                $.each(msg, function(index, element) {
                    var s1 = '<li role="presentation" class="dropdown-header"><span class="glyphicon glyphicon-question-sign"></span>&nbsp;&nbsp;Unread</li>';
                    var s2 = '<li><a href="AcceptFriends"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 向您提出 好友邀請</a></li>';
                    var s3 = '<li><a href="DisplayPosts?id' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 說您的文章讚</a></li>';
                    var s4 = '<li><a href="DisplayPosts?id' + element.typeID + '"><img src="getImage?id=' + element.sID + '&type=user" height="20px" width="20px" class="img-circle">&nbsp;&nbsp;' + element.name + ' 回應您的文章</a></li>';
                    var s5 = '<li class="divider"></li><li><a href="#">查詢所有通知</a></li>';
                    console.log(element.name);
                    $("#notify").html(s1);
                    if (element.type == 1) {
                        $("#notify").append(s2 + s5);
                    } else if (element.type == 2) {
                        $("#notify").append(s3 + s5);
                    } else if (element.type == 3) {
                        $("#notify").append(s4 + s5);
                    }
                });
            }

            count++;

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
