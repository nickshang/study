package com.shang.util;

import java.math.BigDecimal;

/**

* 功能描述：与小数位精度(四舍五入等)相关的一些常用工具方法.

* float/double的精度取值方式分为以下几种:

* java.math.BigDecimal.ROUND_UP     要第2位后面存在大于0的小数，则第2位就+1

* java.math.BigDecimal.ROUND_DOWN   直接舍弃第2位后面的所有小数

* java.math.BigDecimal.ROUND_CEILING

* java.math.BigDecimal.ROUND_FLOOR

* java.math.BigDecimal.ROUND_HALF_UP  

* java.math.BigDecimal.ROUND_HALF_DOWN 

* java.math.BigDecimal.ROUND_HALF_EVEN  四舍五入

*/
public class RoundUtil {

	
	/**
	 * 功能描述：将float、double类型的小数位精度进行整改
	 * @param value 变量
	 * @param scale 小数位
	 * @param roundingMode  直接舍弃第2位后面的所有小数(BigDecimal.ROUND_DOWN)
	 * @return
	 */
	public static double round(double value, int scale, int roundingMode) {
		BigDecimal bd = new BigDecimal(value);
		bd = bd.setScale(scale, roundingMode);
		double d = bd.doubleValue();
		bd = null;
		return d;
	}

}
