package thinkinjava.chapter15_generator.c5;

import thinkinjava.net.mindview.util.Generator;

import java.util.ArrayList;
import java.util.Random;

/**
 * 
 * @类描述：利用泛型构建复杂的模型
 * 构建一个零售店，包含走廊、货架和商品。
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月28日 上午10:32:13
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
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
//					result.append( "走廊:" );
//					result.append(  a );
//					result.append( " 货架:" );
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
 * @类描述：商店商品
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class Product {
	
	/**
	 * 商品ID
	 */
	private int id ;
	
	/**
	 * 商品名称
	 */
	private String name;
	
	/**
	 * 
	 * Title:
	 * Description:
	 * @param id 商品ID
	 * @param name 商品名称
	 */
	public Product(int id, String name) {
		this.id = id;
		this.name = name;
	}
	
	
	
	@Override
	public String toString() {
		return "商品ID:" + id + ",商品名称:" + name;
	}



	/**
	 * 生成一个迭代器
	 * @描述:
	 * @return
	 * @返回类型 Generator<Product>
	 * @创建人 NICK
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
 * @类描述： 货架
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月28日 下午9:27:25
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class Shelf extends ArrayList<Product>{
	public Shelf(int nProducts){
		Generators.fill(this, Product.getIterator(), nProducts);
	}
	
	
}

/**
 * 
 * @类描述：走廊
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月28日 下午9:25:00
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
class Aisle extends ArrayList<Shelf>{
	public Aisle(int nShelves, int nProducts){
		for(int i = 0; i < nShelves; i++){
			add(new Shelf(nProducts));
		}
	}
}



