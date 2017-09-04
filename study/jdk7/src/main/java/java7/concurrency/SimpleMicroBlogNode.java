package java7.concurrency;

/**
 * 简单微博记录接口
 */
public interface SimpleMicroBlogNode {
    void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_);

    void confirmUpdate(SimpleMicroBlogNode other_, Update update_);

    String getIdent();
}
