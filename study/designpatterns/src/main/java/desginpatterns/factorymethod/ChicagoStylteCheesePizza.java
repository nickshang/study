package desginpatterns.factorymethod;

/**
 * 功能描述：纽约风味Pizza
 * @author NICK
 *
 */
public class ChicagoStylteCheesePizza extends Pizza {
	public ChicagoStylteCheesePizza(){
		name = "Chicago Style sauce and Cheese Pizza";
		dough =  "Thin Crust Dough";
		sauce = "Marinara sauce";
		topping.add("Greted Reggiano Cheese"); // 覆盖意大利高级干酪、
		
	}
}
