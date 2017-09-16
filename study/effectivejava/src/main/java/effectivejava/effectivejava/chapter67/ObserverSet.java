package effectivejava.effectivejava.chapter67;

import effectivejava.effectivejava.chapter16.ForwardingSet;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

/**
 * 功能描述:可以观察到集合包装（set wrapper）
 *
 * @author NICK
 *
 * @param <E>
 *            集合元素
 */
public class ObserverSet<E> extends ForwardingSet<E> {

	public ObserverSet(Set<E> s) {
		super(s);
	}

	private final List<SetObserver<E>> setObservers = new ArrayList<SetObserver<E>>();

	public boolean addObserver(SetObserver<E> e) {
		synchronized (setObservers) {
			return setObservers.add(e);
		}
	}

	public boolean removeObserver(SetObserver<E> e) {
		synchronized (setObservers) {
			return setObservers.remove(e);
		}
	}

	//通知每一个变量
	private void notifyElementsAdded(E e) {
		synchronized (setObservers) {
			for (SetObserver<E> element : setObservers) {
				element.added(this, e);
			}
		}
	}

	@Override
	public boolean add(E e) {
		boolean added =  super.add(e);
		if ( added )
			notifyElementsAdded(e);
		return  added;
	}

	@Override
	public boolean addAll(Collection<? extends E> c) {
		boolean added = false;
		for (E e : c) {
			added = add(e);
		}
		return added;
	}

}
