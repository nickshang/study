package desginpatterns.proxy.reflect;

/**
 * 
 * @类描述： 人物信息实现类
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年3月12日 下午12:27:14
 * @修改人：NICK
 * @修改时间：2016年3月12日 下午12:27:14
 * @修改备注：
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright 北京清软创新科技股份有限公司
 */
public class PersonBeanImpl implements PersonBean {

	private String name;
	
	private  String gender;
	
	private String interests;
	
	private int hotOrNotRating;
	
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public String getGender() {
		return this.gender;
	}

	@Override
	public String getInterests() {
		return this.interests;
	}

	@Override
	public int getHotOrNotRating() {
		return this.hotOrNotRating;
	}

	@Override
	public void setName(String name) {
		this.name = name;
	}

	@Override
	public void setGender(String gender) {
		this.gender = gender;
	}

	@Override
	public void setInterests(String interests) {
		this.interests = interests;
	}

	@Override
	public void setHotOrNotRating(int hotOrNotRating) {
		this.hotOrNotRating = hotOrNotRating;
	}

}
