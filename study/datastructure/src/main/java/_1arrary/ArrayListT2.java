package _1arrary;

/**
 * 
 * @�������� ʵ�ֶ�һ�����������ӡ�ɾ���� �޸ġ� ʵ�֡�����
 * 
 * @�����ˣ�NICK
 * @mail sjshang@tsingsoft.com
 * @����ʱ�䣺2016��4��22�� ����11:06:45
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class ArrayListT2 {

	/**
	 * �������
	 */
	private int[] array = null;

	/**
	 * ��Ч�����С
	 */
	private int size = 0;

	/**
	 * Ĭ�ϳ�ʼ����С
	 */
	private final int DEFAULT_CAPACITY = 10;

	/**
	 * Title: ����Ĭ�Ϲ��캯����ʼ�������С
	 */
	public ArrayListT2() {
		array = new int[DEFAULT_CAPACITY];
	}

	/**
	 * Title: ���캯����ʼ�������С
	 * 
	 * @param size
	 *            ��ʼ����Сֵ
	 */
	public ArrayListT2(int initSize) {
		if (initSize > 0)
			array = new int[initSize];
		else if (initSize == 0)
			array = new int[DEFAULT_CAPACITY];
		else
			throw new IllegalArgumentException(" Illegal initSize:" + initSize);
	}

	/**
	 * 
	 * @����: ���������������
	 * @param value
	 *            ��ӵ�����
	 * @�������� void
	 * @������ NICK
	 * @����ʱ�� 2016��4��22�� ����11:16:51
	 *  @throws
	 */
	public void add(int value) {
		ensureCaptity(array.length);
		array[size++] = value;
	}

	/**
	 * 
	 * @����: ��ָ����λ�ã���������������� @param value ��ӵ����� @�������� void @������ NICK @����ʱ��
	 *      2016��4��22�� ����11:16:51 @throws
	 */
	public void add(int index, int value) {
		rangeCheckForAdd(index);

		System.arraycopy(array, index, array, index + 1, size);
		array[index] = value;
	}

	/**
	 * @����: ɾ������������λ�õ����� 
	 * @param index ����λ�� 
	 * @�������� int 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��22�� ����11:24:00 @throws
	 */
	public int remove(int index) {
		rangeCheck(index);

		int value = array[index];

		// ��ָ��λ��Ԫ����ǰ�ƶ�
		// for(int i = index; i < size-1; i++){
		// array[i] = array[i+1];
		// }

		// ����System.arrayCopy������������ĸ���
		System.arraycopy(array, index + 1, array, index, size);
		size--;
		return value;
	}

	/**
	 * @����:�޸�����������λ�õ�����
	 * @param index  ����λ�� 
	 * @param value ���� 
	 * @�������� void 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��22������11:24:00 
	 * @throws
	 */
	public int set(int index, int value) {
		rangeCheck(index);

		int old = array[index];
		array[index] = value;
		return old;
	}

	/**
	 * 
	 * @����: ���������������������� 
	 * @param index ����λ�� 
	 * @return ���ҵ������� 
	 * @�������� int 
	 * @������
	 *      NICK 
	 *  @����ʱ�� 2016��4��22�� ����11:33:19 
	 *  @since 
	 *  @throws
	 */
	public int get(int index) {
		rangeCheck(index);
		return array[index];
	}

	/**
	 * 
	 * @����: �����������������ڵ�λ�� 
	 * @param value ����λ�� 
	 * @return ���ҵ�����������λ�ã�δ�ҵ�����:-1 
	 * @�������� int 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��22�� ����11:33:19 
	 * @since 
	 * @throws
	 */
	public int getByValue(int value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @����: ��ȡ����Ĵ�С
	 * @return ����Ĵ�С
	 * @�������� int
	 * @������ NICK
	 * @����ʱ�� 2016��4��23�� ����10:52:24
	 * @since 
	 * @throws
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @����: �����������Ľ��м�鷶Χ
	 * @param index ����λ��
	 * @�������� void 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��23��     ����6:51:46 
	 * @since 
	 * @throws
	 */
	private void rangeCheckForAdd(int index) {
		if (index < 0 ||  index > size) {				// ��ָ��λ��������ݣ�֮�����������ƶ�
			throw new IndexOutOfBoundsException((" Illegal Argument��" + index));
		} 
	}

	/**
	 * 
	 * @����: �����������Ľ��м�鷶Χ 
	 * @param index ����λ��
	 * @�������� void 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��23��   ����6:51:46 @since @throws
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException((" Illegal Argument��" + index));
		}
	}

	/**
	 * 
	 * @����: ȷ�����鳤�� 
	 * @�������� void 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��23�� ����1:41:45 
	 * @since 
	 * @throws
	 */
	private void ensureCaptity(int minCapactiy) {
		if (size >= minCapactiy) {
			int old = size;
			int[] copy = new int[size + (old >> 1)];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	/**
	 * 
	 * @����:��ʾ�����е����� 
	 * @�������� void 
	 * @������ NICK 
	 * @����ʱ�� 2016��4��22��  ����11:30:38 
	 * @since 
	 * @throws
	 */
	public void display() {
		System.out.println("�����е����ݣ�");
		for (int i = 0; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
