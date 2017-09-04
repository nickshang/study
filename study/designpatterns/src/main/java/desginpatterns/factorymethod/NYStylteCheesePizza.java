package desginpatterns.factorymethod;

/**
 * 功能描述：纽约风味Pizza
 * @author NICK
 *
 */
public class NYStylteCheesePizza extends Pizza {
	
	public NYStylteCheesePizza(){
		name = "NY Style sauce and Cheese Pizza";
		dough =  "Thin Crust Dough";
		sauce = "Marinara sauce";
		topping.add("Sharedded Mozzarella Cheese"); // 覆盖意大利高级干酪、
		
	}
	
	void cut() {
		System.out.println(" Cutting pizza into square slices");
	}
}
