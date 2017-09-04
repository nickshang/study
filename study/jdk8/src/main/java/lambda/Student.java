package lambda;


/**
 * Created by Think on 2016/6/29.
 */
public class Student implements Person, Named {

    @Override
    public String getName(){
        return Person.super.getName();
    }
}

/**
 * Ĭ�Ϸ���ʵ��
 * Think on 2016/6/29.
 */
 interface Named {

    default String getName() {
        return this.getClass().getName() + " " + hashCode();
    }
}


/**
 * Ĭ�Ϸ���ʵ��
 * Think on 2016/6/29.
 */
 interface Person {

    default   String getName(){
        return this.getClass().getName() + " " + hashCode(); }
}
