package desginpatterns.factorymethod;

/**
 * ����������֥�Ӹ�Pizza�̵�
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
