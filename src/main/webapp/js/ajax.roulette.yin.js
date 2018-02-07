// A. 本程式運用Ajax，當點擊輪盤按鈕時，同時會向後端取資料，並給予適當之號碼。
// B. 本程式有用到jQuery語法，所以必須引用jquery.js才能啟用。
// C. 本程式另外引用jQueryRotate.2.2.js，可讓obj順利轉動。
// --------------------------------------------------------------
// 1. 設定好可以點擊的上限次數。
var rotate_times = 1;
// 2. 對lotteryBtn建立點擊聆聽。
$("#lotteryBtn").rotate({
	bind : {
		click : function() {
			// 3. 點擊後，啟動ajax，裡面置放json格式參數。{type:'?',url:'?',...}
			$.ajax({
				type : 'GET', // 請求方法為GET
				url : 'RouletteData_Ajax', // 請求連結至Controller
				success : function(obj) { // 連結成功後，會得到回應過來的obj(一樣是json格式)
					if (rotate_times-- > 0) { // 若點擊次數尚未消耗完，則可以執行轉動rotateFunc
						// 這邊將後端回應過來的obj內容傳給rotateFunc，來告訴他能轉到幾號抽到什麼獎品
						rotateFunc(obj.rouletteID, obj.angle, obj.content);
						// 轉動成功，把點擊次數剪掉一次
					}
					if (rotate_times <= 0) { // 點擊次數剩下0次，所以不讓他再轉了。
						timeOut();
					}
				}
			});
		}
	}
});

// 4. 設定轉動程式rotateFunc，可傳入參數，轉動到n號，角度為angle，禮品為name。
var rotateFunc = function(n, angle, name) {
	$('#lotteryBtn').stopRotate();
	$("#lotteryBtn").rotate({
		angle : 0,
		duration : 6000,
		animateTo : angle + 3600 * 5,
		callback : function() {
			$('#info').html('轉到' + n + '號: ' + '恭喜您獲得' + name + '：）');
			$('#info').css('background: #fcc;');
			// alert('轉到' + n + '號: ' + '恭喜您獲得' + name + '：）');
		}
	});
}
