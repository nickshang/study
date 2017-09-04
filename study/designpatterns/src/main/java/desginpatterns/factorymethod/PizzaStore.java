package desginpatterns.factorymethod;

/**
 * ���������������̵�
 * @author NICK
 *
 */
public abstract class PizzaStore {
	
	/**
	 * Pizza����
	 * @author Think
	 */
	public enum Type { cheese }; 
	
	/**
	 * ����Pizza
	 * @param type
	 */
	public void orderPizza(Type type){
		Pizza pizza = CreatFactory(type);
		pizza.prepare();
	}
	
	public abstract Pizza CreatFactory(Type type);

}
