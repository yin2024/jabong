$(document).ready(function() {
    $('.like').click( function(event) {
        $(this).html('<span class="glyphicon glyphicon-heart"></span>' + event.target.id);
    });
});


// function insertPostLike(postID) {
//     likePostTag = $(this);
//     $.ajax({
//         type: 'POST',
//         url: 'InsertPostLike_Ajax',
//         data: {
//             postID: postID
//         },
//         success: function(obj) {
//             var heart = '<span class="glyphicon glyphicon-heart"></span>&nbsp;&nbsp;';
//             // var likeEnd = '共' + obj.length + '人說這食物讚';
//             var likeEnd = '共人說這食物讚';

//             likePostTag.html(heart);
//             $.each(obj, function(index, element) {
//                 var p = element.userName + '&nbsp;&nbsp;';
//                 likePostTag.append(p);
//             });
//             likePostTag.append(likeEnd);
//         },
//         complete: function() {
//         }
//     });
// }

// function deletePostLike(postID) {
//     $.ajax({
//         type: 'POST',
//         url: 'DeletePostLike.do',
//         data: {
//             postID: postID
//         },
//         success: function(obj) {

//         },
//         complete: function() {

//         }
//     });
// }
