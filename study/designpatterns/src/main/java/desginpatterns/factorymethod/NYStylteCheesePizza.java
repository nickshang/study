package desginpatterns.factorymethod;

/**
 * ����������ŦԼ��ζPizza
 * @author NICK
 *
 */
public class NYStylteCheesePizza extends Pizza {
	
	public NYStylteCheesePizza(){
		name = "NY Style sauce and Cheese Pizza";
		dough =  "Thin Crust Dough";
		sauce = "Marinara sauce";
		topping.add("Sharedded Mozzarella Cheese"); // ����������߼����ҡ�
		
	}
	
	void cut() {
		System.out.println(" Cutting pizza into square slices");
	}
}
