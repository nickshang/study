package java7.concurrency;

/**
 * 死锁的例子
 *
 * 两个对象、两个线程
 *
 * 两个保存地址对象:一个对象地址保存记录微博，另一个地址对象进行操作确认。
 *
 * 两个线程：
 *                A线程操作 local 保存地址对象  保存 "first"  微博记录 other 保存地址对象进行确认
 *                B线程操作 other 保存地址对象  保存 "second" 微博记录 local 保存地址对象进行确认
 * 死锁情况：
 *                当 A线程操作local 保存地址对象  保存 "first"  微博记录  （other 保存地址对象进行确认 未完成情况下） -> 获得local对象锁 -> 下一步获取other对象锁
 *                当 A线程操作local 保存地址对象  保存 "first"  微博记录  （local 保存地址对象进行确认 未完成情况下） -> other -> 下一步获取local对象锁
 *                发生竞争死锁情况。
 *
 * Think on 2016/6/25.
 */
public class MicroBlogNode implements SimpleMicroBlogNode {

    private final String ident;

    public MicroBlogNode(String _ident) {
        this.ident = _ident;
    }

    @Override
    public synchronized void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_) {
        System.out.println(ident + "保存记录:" + upd_.getUpdateText() + ", backup 备份确认地址:" + backup_.getIdent());
        backup_.confirmUpdate(this, upd_);
    }

    @Override
    public synchronized void confirmUpdate(SimpleMicroBlogNode other_, Update update_) {
        System.out.println(ident + ": recvd confirm :" + update_.getUpdateText() + " from " + other_.getIdent());
    }

    @Override
    public String getIdent() {
        return ident;
    }

    /**
     * 创建微博操作
     *
     * @param text
     * @return
     */
    public static Update getUpdate(String text) {
        Update.Builder b = new Update.Builder();
        return b.author(new Author("nick")).createTime(111).updateText(text).build();
    }


    public static void main(String[] args) {

        // 创建微博操作
        final MicroBlogNode local = new MicroBlogNode("localhost:8888");
        final MicroBlogNode other = new MicroBlogNode("localhost:8999");

        // 微博操作类
        final Update first = getUpdate("1");
        final Update second = getUpdate("2");

        for (int i = 0; i < 20 ; i++) {

            System.out.println("循环次数:" + i);

            new Thread(new Runnable() {
                @Override
                public void run() {
                    local.propagateUpdate(first, other);
                }
            }).start();

            new Thread(new Runnable() {
                @Override
                public void run() {
                    other.propagateUpdate(second, local);
                }
            }).start();
        }

    }

}
