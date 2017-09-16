package desginpatterns.factorymethod;

import java.util.ArrayList;

/**
 * 功能描述：产品抽象
 * @author NICK
 *
 */
public abstract class Pizza {
	
	// 名称
	String name;
	
	// 生面团
	String dough;
	
	// 调味汁
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
	 * 烘焙
	 */
	void bake(){
		
	}
	
	/**
	 * 切片
	 */
	void cut(){
		System.out.println("Bake for 25 minutes at 350.");
	}
	
	/**
	 * 打包
	 */
	void box(){
		System.out.println("Cutting the pizza int diagonal slices.");
	}
	
	public String getName(){
		return this.getName();
	}
}
