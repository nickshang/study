package effectivejava.effectivejava.chapter30;
/**
 * 功能描述：《Effective Java 第二版本》 30条 用enum替代int常量</p>
 * 
 * 显示各物体的信息
 * @author Nick
 *
 */
public class WeightTable {

	public static void main(String[] args) {
		double earthWeight = Double.parseDouble( "11" );
		double mass = earthWeight / Planet.EARHT.surfaceGravity();
		
		for (Planet p : Planet.values()) {
			System.out.printf("Weight on %s is %f%n", p, p.surFaceWeight(mass));
		}
	}
}
