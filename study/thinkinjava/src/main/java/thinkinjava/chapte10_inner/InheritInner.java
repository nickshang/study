package thinkinjava.chapte10_inner;

/**
 * Created by Think on 2016/5/16.
 */
public class InheritInner extends WithInner.Inner{

    public InheritInner(WithInner wi){
        wi.super();
    }

    public static void main(String[] args) {
        WithInner wi = new WithInner();
        InheritInner in = new InheritInner(wi);
    }
}

class WithInner{
    class Inner {}
}
