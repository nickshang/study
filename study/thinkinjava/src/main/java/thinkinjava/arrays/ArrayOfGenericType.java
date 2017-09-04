package thinkinjava.arrays;//: arrays/ArrayOfGenericType.java
// Arrays of generic types won't compile.

public class ArrayOfGenericType<T> {
    T[] array; // OK

    @SuppressWarnings("unchecked")
    public ArrayOfGenericType(int size) {
        //! array = new T[size]; // Illegal
        array = (T[]) new Object[size]; // "unchecked" Warning

        int i = 1;
        if (i==1) {

        }

    }
    // Illegal:
    //! public <U> U[] makeArray() { return new U[10]; }
} ///:~
