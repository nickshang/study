package desginpatterns.factorymethod;


/**
 * 功能描述：纽约Pizza商店
 * @author Think
 *
 */
public class NYPizzaStroe  extends PizzaStore{

	@Override
	public Pizza CreatFactory(Type type) {
		Pizza pizza = null;
		if(type.equals(Type.cheese)){
			pizza = new NYStylteCheesePizza();
		}
		return pizza;
	}

}
