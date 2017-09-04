package desginpatterns.proxy.reflect;

/**
 * 
 * @类描述： 人物信息Bean
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月12日 下午12:00:57
 * @修改人：NICK
 * @修改时间：2016年3月12日 下午12:00:57
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public interface PersonBean {
	
	String getName();
	
	String getGender();
	
	String getInterests();
	
	int getHotOrNotRating();
	
	void setName(String name);
	
	void setGender(String gender);
	
	void setInterests(String interests);
	
	void setHotOrNotRating(int hotOrNotRating);

}
