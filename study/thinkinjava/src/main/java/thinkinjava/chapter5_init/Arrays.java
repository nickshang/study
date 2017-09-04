package thinkinjava.chapter5_init;

public class Arrays {

	public static void main(String[] args) {
//		int[] a = { 1, 2, 3, 4 };
//		int[] d = new int[] { 1, 2, 3 };
//		System.out.println(java.util.Arrays.toString(d));
//		;
//
//		p(5, 2, 3, 4, 1);
		
	}

	public static void p(Object... a) {
//		System.out.println(java.util.Arrays.toString(a));
//		System.out.println(java.util.Arrays.binarySearch(a,5));
//		System.out.println("index:" + binarySearch0(a,0,a.length, 5));
		
//		Comparable midVal = (Comparable) a[1];
//		System.out.println(midVal.compareTo(0));
		
	}

	public static int binarySearch0(Object[] a, int fromIndex, int toIndex, Object key) {
		int low = fromIndex;
		int high = toIndex - 1;

		StringBuilder strBuil = null;
		while (low <= high) {
			int mid = (low + high) >>> 1;
			@SuppressWarnings("rawtypes")
			Comparable midVal = (Comparable) a[mid];
			@SuppressWarnings("unchecked")
			int cmp = midVal.compareTo(key);

			System.out.println("mid:" +  mid);
			if (cmp < 0)
				low = mid + 1;
			else if (cmp > 0)
				high = mid - 1;
			else
				return mid; // key found
		}
		return -(low + 1); // key not found.
	}
}
