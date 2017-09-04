package _1arrary;

/**
 * 
 * @类描述： 实现基于对象的对一个数组对象：添加、删除、 修改、 实现、查找
 * 
 * @创建人：NICK
 * @mail sjshang@tsingsoft.com
 * @创建时间：2016年4月22日 下午11:06:45
 * @Copyright 北京清软创新科技股份有限公司
 */
public class ArrayListT3 {

	/**
	 * 数组对象
	 */
	private Object[] array = null;
	

	/**
	 * 有效数组大小
	 */
	private int size = 0;

	/**
	 * 默认初始化大小
	 */
	private final int DEFAULT_CAPACITY = 10;

	/**
	 * Title: 利用默认构造函数初始化数组大小
	 */
	public ArrayListT3() {
		array = new Object[DEFAULT_CAPACITY];
	}

	/**
	 * Title: 构造函数初始化数组大小
	 * 
	 * @param size
	 *            初始化大小值
	 */
	public ArrayListT3(int initSize) {
		if (initSize > 0)
			array = new Object[initSize];
		else if (initSize == 0)
			array = new Object[DEFAULT_CAPACITY];
		else
			throw new IllegalArgumentException(" Illegal initSize:" + initSize);
	}

	/**
	 * 
	 * @描述: 向数据中添加数据
	 * @param value
	 *            添加的数据
	 * @返回类型 void
	 * @创建人 NICK
	 * @创建时间 2016年4月22日 下午11:16:51
	 *  @throws
	 */
	public void add(Object value) {
		ensureCaptity(array.length+1);
		array[size++] = value;
	}

	/**
	 * 
	 * @描述: 在指定的位置，向数组中添加数据 @param value 添加的数据 @返回类型 void @创建人 NICK @创建时间
	 *      2016年4月22日 下午11:16:51 @throws
	 */
	public void add(int index, Object value) {
		rangeCheckForAdd(index);
		ensureCaptity(size + 1);
		System.arraycopy(array, index, array, index + 1, size-index);
		array[index] = value;
	}

	/**
	 * @描述: 删除数组中索引位置的数据 
	 * @param index 索引位置 
	 * @返回类型 int 
	 * @创建人 NICK 
	 * @创建时间 2016年4月22日 下午11:24:00 @throws
	 */
	public Object remove(int index) {
		rangeCheck(index);

		Object value = array[index];

		// 将指定位置元素向前移动
		// for(int i = index; i < size-1; i++){
		// array[i] = array[i+1];
		// }

		// 利用System.arrayCopy函数进行数组的复制
		System.arraycopy(array, index + 1, array, index, size);
		size--;

		return value;
	}

	/**
	 * @描述:修改数组中索引位置的数据
	 * @param index  索引位置 
	 * @param value 数据 
	 * @返回类型 void 
	 * @创建人 NICK 
	 * @创建时间 2016年4月22日下午11:24:00 
	 * @throws
	 */
	public Object set(int index, int value) {
		rangeCheck(index);

		Object old = array[index];
		array[index] = value;
		return old;
	}

	/**
	 * 
	 * @描述: 根据索引从数组查查找数据 
	 * @param index 索引位置 
	 * @return 查找到的数据 
	 * @返回类型 int 
	 * @创建人
	 *      NICK 
	 *  @创建时间 2016年4月22日 下午11:33:19 
	 *  @since 
	 *  @throws
	 */
	public Object get(int index) {
		rangeCheck(index);
		return array[index];
	}

	/**
	 * 
	 * @描述: 从数组查查找数据所在的位置 
	 * @param value 索引位置 
	 * @return 查找到的数据索引位置，未找到返回:-1 
	 * @返回类型 int 
	 * @创建人 NICK 
	 * @创建时间 2016年4月22日 下午11:33:19 
	 * @since 
	 * @throws
	 */
	public Object getByValue(Object value) {
		for (int i = 0; i < array.length; i++) {
			if (array[i] == value) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * 
	 * @描述: 获取数组的大小
	 * @return 数组的大小
	 * @返回类型 int
	 * @创建人 NICK
	 * @创建时间 2016年4月23日 上午10:52:24
	 * @since 
	 * @throws
	 */
	public int getSize() {
		return size;
	}

	/**
	 * 
	 * @描述: 对数组索引的进行检查范围  （大于0  并且 小于或者等于有效元素个数）
	 * @param index 索引位置
	 * @返回类型 void 
	 * @创建人 NICK 
	 * @创建时间 2016年4月23日     下午6:51:46 
	 * @since 
	 * @throws
	 */
	private void rangeCheckForAdd(int index) {
		if (index < 0 ||  index > size) {				// 在指定位置添加数据，之后的数据向后移动
			throw new IndexOutOfBoundsException((" Illegal Argument：" + index));
		} 
	}

	/**
	 * 
	 * @描述: 对数组索引的进行检查范围  （大于0  并且 小于有效元素个数）
	 * @param index 索引位置
	 * @返回类型 void 
	 * @创建人 NICK 
	 * @创建时间 2016年4月23日   下午6:51:46 @since @throws
	 */
	private void rangeCheck(int index) {
		if (index < 0 || index >= size) {
			throw new IndexOutOfBoundsException((" Illegal Argument：" + index));
		}
	}

	/**
	 * 
	 * @描述: 确定数组长度 
	 * @返回类型 void 
	 * @创建人 NICK 
	 * @创建时间 2016年4月23日 下午1:41:45 
	 * @since 
	 * @throws
	 */
	private void ensureCaptity(int minCapactiy) {
		if (size >= minCapactiy) {
			int old = array.length;
			Object[] copy = new Object[size + (old >> 1)];
			System.arraycopy(array, 0, copy, 0, size);
			array = copy;
		}
	}

	/**
	 * 
	 * @描述:显示数组中的数据 
	 * @返回类型 void 
	 * @创建人 NICK 
	 * @创建时间 2016年4月22日  下午11:30:38 
	 * @since 
	 * @throws
	 */
	public void display() {
		System.out.println("数组中的数据：");
		for (int i = 0; i < size; i++) {
			System.out.print(array[i] + " ");
		}
		System.out.println();
	}
}
