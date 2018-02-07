package _03_post.model;

import java.io.InputStream;
import java.sql.SQLException;
import java.util.List;


public interface PostAccessDAO {
    // 1. AccessBean必要準備項目
    // 2. AccessBean次要準備項目
    // 3. 預設建構子：負責取得資料庫連線
    // 4. Setter：負責存取外部參數用

    // 5.1 MySQL：查詢 - 取得用戶好友及自己的文章
    public List<PostBean> getAllPosts() throws SQLException;

    // 5.2 MySQL：查詢 - 取得單一篇文章
    public PostBean getPost() throws SQLException;

    // 5.3 MySQL：查詢 - 取得單一篇文章的所有點讚
    public List<PostLikeBean> getPostLike() throws SQLException;

    // 5.4 MySQL：查詢 - 取得單一篇文章的所有回應
    public List<PostRespBean> getPostResp() throws SQLException;
    
    // 5.5 MySQL：查詢 - 取得文章的作者
    public int getWhoPost() throws SQLException;

    // 6.1 MySQL：新增 - 新增一篇文章
    public int insertPost(PostBean bean, InputStream is, long size) throws Exception;

    // 6.4 MySQL：新增 - 新增一篇文章的點讚
    public int insertPostLike(PostLikeBean plb) throws SQLException;

    // 6.5 MySQL：新增 - 新增一篇文章的回應
    public int insertPostResp(PostRespBean prb) throws SQLException;

    // 7.1 MySQL：修改 - 修改一篇文章
    public int updatePost(PostBean bean) throws SQLException;

    // 8.1 MySQL：刪除 - 刪除一篇文章
    public int deletePost() throws SQLException;

    // 8.2 MySQL：刪除 - 刪除一篇文章的點讚
    public void deletePostLike(PostLikeBean plb) throws SQLException;
}
