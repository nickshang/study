package java7.concurrency;

import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 用ReentranLock重写死锁
 */
public class DeadlockWithLockMicroBlogNode implements SimpleMicroBlogNode {

  private static Update getUpdate(String s) {
    Update.Builder b = new Update.Builder();
    b.updateText(s).author(new Author("Ben"));

    return b.build();
  }

  private final String ident;

  private final Lock lock = new ReentrantLock();

  public DeadlockWithLockMicroBlogNode(String ident_) {
    ident = ident_;
  }

  public String getIdent() {
    return ident;
  }

  @Override
  public void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_) {

    // 每个现场都先锁住自己的锁
    lock.lock();

    try {
      System.out.println(ident + ": recvd: " + upd_.getUpdateText()
          + " ; backup: " + backup_.getIdent());

      // 调用confirmUpate知悉其他线程
      backup_.confirmUpdate(this, upd_);
    } finally {
      lock.unlock();
    }
  }

  @Override
  public void confirmUpdate(SimpleMicroBlogNode other_, Update upd_) {

    // 尝试锁住其他线程
    lock.lock();
    try {
      System.out.println(ident + ": recvd confirm: " + upd_.getUpdateText()
          + " from " + other_.getIdent());
    } finally {
      lock.unlock();
    }
  }

  public static void main(String[] a) {
    final DeadlockWithLockMicroBlogNode local = new DeadlockWithLockMicroBlogNode(
        "localhost:8888");
    final DeadlockWithLockMicroBlogNode other = new DeadlockWithLockMicroBlogNode(
        "localhost:8988");
    final Update first = getUpdate("1");
    final Update second = getUpdate("2");

    new Thread(new Runnable() {
      public void run() {
        local.propagateUpdate(first, other);
      }
    }).start();

    new Thread(new Runnable() {
      public void run() {
        other.propagateUpdate(second, local);
      }
    }).start();
  }

}