package desginpatterns.proxy.reflect;

/**
 * 
 * @�������� ������ϢBean
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��3��12�� ����12:00:57
 * @�޸��ˣ�NICK
 * @�޸�ʱ�䣺2016��3��12�� ����12:00:57
 * @�޸ı�ע��
 * @version v1.0
 * @see [nothing]
 * @bug [nothing]
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
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
