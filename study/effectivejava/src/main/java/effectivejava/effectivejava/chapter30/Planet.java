package effectivejava.effectivejava.chapter30;

/**
 * 功能描述：《Effective Java 第二版本》 30条 用enum替代int常量</p>
 * 
 * 计算太阳系各行星质量和半径，计算的表面重力
 * @author Nick
 *
 */
public enum Planet {
	
		MERCUY(3d,3d),
		VENUS(4d,5d),
		EARHT(5d,7d),
		MARS(3d,8d),
		JUPITER(8d,10d),
		SATURN(9d,11d),
		UANUS(3d,10d),
		NEPTUNE(1d,9d);
		
		private final double mass;  //质量
		private final double radius; //半径
		private final double surfaceGravity; //表面重力
		
		private static final  double G = 6d;
		
		/**
		 * 结构化初始方法
		 * @param mass  质量
		 * @param radius 半径
		 */
		Planet(double mass, double radius){
			this.mass   = mass;
			this.radius = radius; 
			surfaceGravity = G * mass / (radius * radius);
		}
		
		public double mass() 	        { return this.mass; }
		public double radius() 	        { return this.radius; }
		public double surfaceGravity() 	{ return this.surfaceGravity; }
		
		public double surFaceWeight(double mass){
			return mass * surfaceGravity;
		}
}	
