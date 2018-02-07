package _00_init;

public class TimeDemo {

    public static void main(String[] args)  {

	// 從資料庫取得的一筆時間字串
	String dateString1 = "2014-02-24 08:27:35.53"; // 年
	System.out.println(TimeService.getRelativeTime(dateString1));

	String dateString2 = "2016-01-21 07:23:45.01"; // 月
	System.out.println(TimeService.getRelativeTime(dateString2));

	String dateString3 = "2016-03-23 20:47:18.27"; // 週
	System.out.println(TimeService.getRelativeTime(dateString3));

	String dateString4 = "2016-04-16 14:01:07.34"; // 日
	System.out.println(TimeService.getRelativeTime(dateString4));

	String dateString5 = "2016-04-21 18:17:27.19"; // 時
	System.out.println(TimeService.getRelativeTime(dateString5));

	String dateString6 = "2016-04-21 21:05:57.22"; // 分
	System.out.println(TimeService.getRelativeTime(dateString6));
	
	// String dateString7 = "請看一下自己的時間測試XD"; //秒
	// TimeService.getRelativeTime(dateString7);

    }

}
