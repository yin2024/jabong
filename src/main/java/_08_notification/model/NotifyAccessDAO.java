package _08_notification.model;

import java.sql.SQLException;
import java.util.Collection;

public interface NotifyAccessDAO {
    // 1. AccessBean必要準備項目
    // 2. AccessBean次要準備項目
    // 3. 預設建構子：負責取得資料庫連線
    // 4. Setter：負責存取外部參數用

    // 5. 查詢 - 取得所有通知
    public Collection<NotifyBean> getAllNotify() throws SQLException;

    // 6. 新增 - 發生互動，新增一筆通知到對方帳戶
    public void insertNotify() throws SQLException;

    // 7. 修改 - 通知已讀，修改這筆通知的click欄位
    public void clickNotify() throws SQLException;

}
