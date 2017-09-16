package thinkinjava.chapter15_generator.c5;

import net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @�����������÷��͹������ӵ�ģ��
 * ����һ�����۵꣬�������ȡ����ܺ���Ʒ��
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��28�� ����10:32:13
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class Stroe extends ArrayList<Aisle> {
	private ArrayList<CheckOutStand> checkouts = 
			new ArrayList<>();
	
	private Coffee coffee = new Coffee();
	
	public Stroe(int nAiles, int nShelves, int nProducts){
		for( int i = 0; i < nAiles; i++){
			add(new Aisle(nShelves, nProducts));
		}
	}
	
	public String toString(){
		StringBuilder result = new StringBuilder();
		for(Aisle a : this )
			for(Shelf s : a ){
				for(Product p : s){
//					result.append( "����:" );
//					result.append(  a );
//					result.append( " ����:" );
//					result.append(  s );
					result.append( " produce:" );
					result.append( p.getName() );
					result.append( " produceID:" );
					result.append( p.getId());
					result.append( "\n");
				}
			}
		return result.toString();
	}
	
	
	public static void main(String[] args) {
		System.out.println(  (new Stroe(3, 2, 20)).toString());
	}
}

class CheckOutStand {}
class Coffee {}

/**
 * 
 * @���������̵���Ʒ
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
class Product {
	
	/**
	 * ��ƷID
	 */
	private int id ;
	
	/**
	 * ��Ʒ����
	 */
	private String name;
	
	/**
	 * 
	 * Title:
	 * Description:
	 * @param id ��ƷID
	 * @param name ��Ʒ����
	 */
	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return "��ƷID:" + id + ",��Ʒ����:" + name;
	}



	/**
	 * ����һ��������
	 * @����:
	 * @return
	 * @�������� Generator<Product>
	 * @������ NICK
	 * @since
	 * @throws
	 */
	public  static Generator<Product> getIterator(){
		
		Random random = new Random();
		
		return new Generator<Product>(){
			public Product next(){
				return new Product(random.nextInt(100),String.valueOf( random.nextInt(100) ));
			}
		};
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
}

/**
 * 
 * @�������� ����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��28�� ����9:27:25
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
class Shelf extends ArrayList<Product>{
	public Shelf(int nProducts){
		Generators.fill(this, Product.getIterator(), nProducts);
	}
	
	
}

/**
 * 
 * @������������
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��28�� ����9:25:00
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
class Aisle extends ArrayList<Shelf>{
	public Aisle(int nShelves, int nProducts){
		for(int i = 0; i < nShelves; i++){
			add(new Shelf(nProducts));
		}
	}
}



