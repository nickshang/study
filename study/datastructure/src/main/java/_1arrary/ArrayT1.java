package _1arrary;


/**
 * 
 * @��������ѧϰ���ݽṹ���㷨
 * 1.��������
 * 2.ɾ������
 * 3.�޸�����
 * 4.��������
 * 5.��ʾ���� 
 * @�����ˣ�NICK
 * @����ʱ�䣺2016��4��17�� ����2:13:32
 * @version v1.0
 * @mail sjshang@tsingsoft.com
 * @Copyright ���������¿Ƽ��ɷ����޹�˾
 */
public class ArrayT1 {
	 
	public static void main(String[] args) {
		
		// ��������
		final int SIZE = 10;										// �����ʼ����С
		
		byte[] 	a1 	= new byte[SIZE];
		char[] 	a2 	= new char[SIZE];
		short[] a3 	= new short[SIZE];
		int[] 	a4 	= new int[SIZE];
		long[] 	a5 	= new long[SIZE];
		float[] a6 	= new float[SIZE];
		double[] a7 = new double[SIZE];
		
		// �鿴��ʼ������ 
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a1", a1[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a2", a2[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a3", a3[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a4", a4[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a5", a5[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a6", a6[0] ));
		System.out.println(String.format("�������ͣ�%s,��ʼ��ֵ:%s", "a7", a7[0] ));
		
		// ����Ķ��巽ʽ
		byte[] 	a1_ = new byte[SIZE];		// ֱ�Ӷ��� ���߿�ʼ����Ϊ�� ���ʼ��
		int[]	a2_ = {1,2,3};				// ֱ�ӳ�ʼ��
		
		// �������� 
		for(byte b = 0; b < SIZE; b++ ){
			a1_[b] = b;
		}
		
		// �������
		for(byte b = 0; b < SIZE; b++ ){
			System.out.println(String.format("�������%s��λ�ã�%d��ֵ:%s", "a1_", b, a1_[b] ));
		}
		
		
		for(byte b = 0; b < a2_.length; b++ ){
			System.out.println(String.format("�������%s��λ�ã�%d����ֵ:%s", "a2_", b, a2_[b] ));
		}
		
		// ɾ������
		// ɾ������a1_���Ԫ��
		for(byte b = 0; b < a1.length; b++){
			a1[b] = b;
		}
		
		byte del = 5;  									// ȷ��ɾ����λ��
		for(byte b =  del--; b < a1.length - 1; b++ ){
			a1[b] = a1[b+1]; 							// ��Ҫɾ����������λ
		}
		
		for(byte b =0; b < a1.length; b++ ){
			System.out.println( String.format("λ��:%s,����:%d", b, a1[b]) );
		}
		
		// ��������
		int searchValue = 3;
		int searchIndex = -1;
		for( int i = 0;  i < a1.length; i ++){
			if( a1[i] == searchValue ){
				searchIndex = i; 
			}
		}
		System.out.println( String.format( "��Ҫ���ҵ���ֵ:%s,��Ҫ���ҵ�λ��:%s", searchValue, searchIndex));
		
		// �޸�����
		int oldValue = 3;
		int newValue = 5;
		for( int i = 0;  i < a1.length; i ++){
			if( a1[i] == oldValue ){
				a1[i]  = (byte)newValue ; 
			}
		}
		
		
		// ��ʾ����
		System.out.print( "a1��������: " );
		for( int i = 0;  i < a1.length; i ++){
			System.out.print( a1[i]  + " ");
		}
		
		
	}
	
	
	/**
	 * 
	 * @����:a+b
	 * @param a  ����1
	 * @param b  ����2
	 * @since
	 * @throws
	 */
	public static void S(int a , int b){
		
	}

}
