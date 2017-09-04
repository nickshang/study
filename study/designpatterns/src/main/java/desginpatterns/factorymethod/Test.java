package desginpatterns.factorymethod;

public class Test {
	public static void main(String[] args) {
		
		//��ŦԼ��ζPizza
		PizzaStore nyStore = new NYPizzaStroe();
		nyStore.orderPizza(PizzaStore.Type.cheese);
		
		//��֥�Ӹ��ζPizza
		PizzaStore chicagoStore = new ChicagoPizzaStroe();
		chicagoStore.orderPizza(PizzaStore.Type.cheese);
	}
}
