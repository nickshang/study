package desginpatterns.proxy.reflect;

/**
 * 
 * @�������� ������Ϣʵ����
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��3��12�� ����12:27:14
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��12�� ����12:27:14
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
