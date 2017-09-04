package thinkinjava.chapte10_inner;

/**
 * 
 * @类描述：
 * 闭包(closeure)：是一个可调用的对象，它记录了一些信息，这些信息来自创建的作用域。
 * 通过这个定义，可以看出内部类是面向对象的闭包，因为他不仅包含外围对象（创建内部的的作用域）
 * 的信息，还自动拥有一个执行此外围类的应用，在此作用域内，内部类有权操作所有的成员，包含
 * private成员。
 * 
 * 回调：人们认为Java应该包含某些类似指针的机制，以允许回调(callback)，通过回调
 * 对象能携带一些信息，这些信息允许他在稍后的某个时刻调用初始的对象。
 * 
 * 通过内部类提供闭包的功能是优良的解决方案，他比指针更灵活，更安全。
 * 
 * 
 * 两个例子展示了外围类实现一个借口与内部类实现此接口之间的区别。
 * 1.Callee1是简单的解决方式。
 * 2.Callee2继承自Myincrement，后者已经有了一个不同的increment()方法，并且
 * 与Incrementtable接口期待的increment()方法安全不相同。所以
 * callee2继承了myIncrement，就不能为了Incrementable的用途覆盖increment()方法，于是
 * 只能使用内部类实现incrementtable。
 * 
 * 还要注意,当创建一个内部类时，并没有在外围类的接口中添加东西，也没有添加外围类的接口。
 * 
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年5月16日 下午12:07:08
 * @修改人：NICK
 * @Copyright 北京清软创新科技股份有限公司
 */
public class Callbacks {
	
	
	public static void main(String[] args){
		Callee1 c1 = new Callee1();
		Callee2 c2 = new Callee2();
//		MyIncremnet.f(c2);
		Caller callee1 = new Caller(c1);
		Caller callee2 = new Caller(c2.getCallbackReference());
		callee1.go();
		callee1.go();
		callee2.go();
		callee2.go();
		
	}
}


interface Incrementtable{
	void increment();
}

class Callee1 implements Incrementtable{

	private int i = 0;
	
	@Override
	public void increment() {
			i++;
			System.out.println(i);
	}
	
}

class MyIncremnet {
	public void increment() { System.out.println("Other operation"); }
	static void f(MyIncremnet mi ) { mi.increment();}
}

class Callee2 extends MyIncremnet{
	private int i = 0;
	public void increment(){
		super.increment();
		i++;
		System.out.println(i);
	}
	
	private class Closure implements Incrementtable {
		@Override
		public void increment() {
			Callee2.this.increment();
		}
	}
	
	Incrementtable getCallbackReference(){
		return new Closure();
	}

}

class Caller {
	private Incrementtable callbackReference;
	Caller(Incrementtable cbh) { callbackReference = cbh; }
	void go() { callbackReference.increment();}
}




