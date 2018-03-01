package desginpatterns.factorymethod;

/**
 * 功能描述：芝加哥Pizza商店
 * @author NICK
 *
 */
public class ChicagoPizzaStroe  extends PizzaStore{

	@Override
	public Pizza CreatFactory(Type type) {
		Pizza pizza = null;
		if(type.equals(Type.cheese)){
			pizza = new ChicagoStylteCheesePizza();
		}
		return pizza;
	}

}
