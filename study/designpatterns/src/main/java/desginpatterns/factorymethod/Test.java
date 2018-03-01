package desginpatterns.factorymethod;

public class Test {
	public static void main(String[] args) {
		
		//定纽约风味Pizza
		PizzaStore nyStore = new NYPizzaStroe();
		nyStore.orderPizza(PizzaStore.Type.cheese);
		
		//定芝加哥风味Pizza
		PizzaStore chicagoStore = new ChicagoPizzaStroe();
		chicagoStore.orderPizza(PizzaStore.Type.cheese);
	}
}
