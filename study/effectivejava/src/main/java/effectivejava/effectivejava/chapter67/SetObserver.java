package effectivejava.effectivejava.chapter67;


/**
 * 集合观察者接口
 * @author NICK
 *
 */
public interface SetObserver<E> {

	public void added(ObserverSet<E> setObservers, E elements);

}
