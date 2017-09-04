package desginpatterns.factorymethod;

import java.util.ArrayList;

/**
 * ������������Ʒ����
 * @author NICK
 *
 */
public abstract class Pizza {
	
	// ����
	String name;
	
	// ������
	String dough;
	
	// ��ζ֭
	String sauce;
	
	ArrayList topping = new ArrayList();
	
	void prepare() {
		System.out.println( "Preparing " + name );
		System.out.println( "Tossing dough ... " );
		System.out.println( "Adding sauce ... " );
		System.out.println( "Adding topping ... " );
		for (int i = 0; i < topping.size(); i++){
			System.out.println(" " + topping.get(i));
		}
	}
	
	
	/**
	 * �決
	 */
	void bake(){
		
	}
	
	/**
	 * ��Ƭ
	 */
	void cut(){
		System.out.println("Bake for 25 minutes at 350.");
	}
	
	/**
	 * ���
	 */
	void box(){
		System.out.println("Cutting the pizza int diagonal slices.");
	}
	
	public String getName(){
		return this.getName();
	}
}
