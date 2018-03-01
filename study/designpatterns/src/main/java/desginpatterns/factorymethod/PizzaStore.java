package desginpatterns.factorymethod;

/**
 * 功能描述：披萨商店
 * @author NICK
 *
 */
public abstract class PizzaStore {
	
	/**
	 * Pizza类型
	 * @author Think
	 */
	public enum Type { cheese }; 
	
	/**
	 * 订单Pizza
	 * @param type
	 */
	public void orderPizza(Type type){
		Pizza pizza = CreatFactory(type);
		pizza.prepare();
	}
	
	public abstract Pizza CreatFactory(Type type);

}
