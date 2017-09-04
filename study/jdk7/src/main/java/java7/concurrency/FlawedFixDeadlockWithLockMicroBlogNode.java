package java7.concurrency;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 尝试修复死锁：有缺陷的解决死锁的问题的尝试
 */
public class FlawedFixDeadlockWithLockMicroBlogNode implements
        SimpleMicroBlogNode {

  private static Update getUpdate(String s) {
    Update.Builder b = new Update.Builder();
    b.updateText(s).author(new Author("Ben"));

    return b.build();
  }

  private final String ident;

  private final Lock lock = new ReentrantLock();

  public FlawedFixDeadlockWithLockMicroBlogNode(String ident_) {
    ident = ident_;
  }

  public String getIdent() {
    return ident;
  }

  @Override
  public void propagateUpdate(Update upd_, SimpleMicroBlogNode backup_) {
    boolean acquired = false;

    while (!acquired) {
      try {
        int wait = (int) (Math.random() * 10);

        // 尝试与锁定-》 超时时长随机
        acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
        if (acquired) {
          System.out.println(ident + ": recvd: " + upd_.getUpdateText()
              + " ; backup: " + backup_.getIdent());

          // 在其他现场上确认
          backup_.confirmUpdate(this, upd_);
        } else {
          Thread.sleep(wait);
        }
      } catch (InterruptedException e) {
      } finally {
        if (acquired)
          lock.unlock();
      }
    }
  }

  @Override
  public void confirmUpdate(SimpleMicroBlogNode other_, Update upd_) {
    boolean acquired = false;

    while (!acquired) {
      try {
        int wait = (int) (Math.random() * 10);

        // 尝试与锁定-》 超时时长随机
        acquired = lock.tryLock(wait, TimeUnit.MILLISECONDS);
        if (acquired) {
          System.out.println(ident + ": recvd confirm: " + upd_.getUpdateText()
              + " from " + other_.getIdent());
        } else {
          Thread.sleep(wait);
        }
      } catch (InterruptedException e) {
      } finally {
        if (acquired)
          lock.unlock();
      }
    }
  }

  public static void main(String[] a) {
    final FlawedFixDeadlockWithLockMicroBlogNode local = new FlawedFixDeadlockWithLockMicroBlogNode(
        "localhost:8888");
    final FlawedFixDeadlockWithLockMicroBlogNode other = new FlawedFixDeadlockWithLockMicroBlogNode(
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