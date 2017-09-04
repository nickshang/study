package java7.classbytecode;

import java.lang.invoke.MethodHandle;
import java.lang.invoke.MethodHandles;
import java.lang.invoke.MethodType;

public class MethodHandleIntro {

    private static MethodHandleIntro inst = null;

    // Constructor
    private MethodHandleIntro() {
        super();
    }

    public String toString() {
        return "My toString()";
    }

    /*
     * This is where your actual code will go
     */
    private void run() throws Throwable {
        MethodHandle mh = getToStringMH();
        String s = (String) mh.invoke(this);
        System.out.println(s);
    }

    @SuppressWarnings(value = "unchecked")
    public MethodHandle getToStringMH() {
        MethodHandle mh;
        MethodType mt = MethodType.methodType(String.class);
        MethodHandles.Lookup lk = MethodHandles.lookup();

        try {
            mh = lk.findVirtual(getClass(), "toString", mt);
        } catch (NoSuchMethodException | IllegalAccessException mhx) {
            throw (AssertionError) new AssertionError().initCause(mhx);
        }

        return mh;
    }

    public MethodHandle getToMh2(){

        // 定义方法句柄
        MethodHandle mh = null ;

        // 定义方法签名类型
        MethodType mt = MethodType.methodType(String.class,String.class,String.class);

        //   A <em>lookup object</em> is a factory for creating method handles,
        MethodHandles.Lookup lk = MethodHandles.lookup();

        try {
            mh = lk.findVirtual(this.getClass(),"getHw",mt);
        } catch (NoSuchMethodException  | IllegalAccessException e) {
            e.printStackTrace();
        }

        return mh;
    }


    /**
     * 将两个字符串进行拼接
     * @param s1 字符串1
     * @param s2 字符串2
     * @return 拼接完成的字符串
     */
    public String getHw(String s1 ,String s2){
//        String s1 ="2",s2 ="1";
        return s1 + s2;
    }


    public void run2(){
        MethodHandle mh = getToMh2();
        String s = null;
        try {
            s = (String)mh.invokeExact(this,"nick ", "jocy");
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        System.out.println(s);
    }
    /**
     * @param args
     */
    public static void main(String[] args) throws Throwable {
        inst = new MethodHandleIntro();
//        inst.run();
        inst.run2();

        //"C:\Program Files\Java\jdk1.8.0_65\bin\java" -Didea.launcher.port=7533 "-Didea.launcher.bin.path=E:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.1.1\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_65\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_65\jre\lib\rt.jar;F:\workspace\shang1\WebRoot\WEB-INF\classes;F:\workspace\shang1\WebRoot\WEB-INF\commons-net-2.2.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\poi-ooxml-3.9-20121203.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-net-3.0.1.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\log4j-1.2.11.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-httpclient-3.1.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-fileupload-1.2.1.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-collections-3.1.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-codec-1.5.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-logging-1.1.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\httpclient-4.0.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-beanutils.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\poi-ooxml-schemas-3.9-20121203.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\ojdbc14.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-lang-2.3.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\ezmorph-1.0.6.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\junit-dep-4.9-SNAPSHOT-20100512-0041.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\javassist-3.15.0-GA.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\poi-excelant-3.9-20121203.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\poi-scratchpad-3.9-20121203.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\servlet-api.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-io-1.4.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\jxl-2.6.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-beanutils-core.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\httpmime-4.0.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\commons-beanutils-bean-collections.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\hamcrest-core-1.3.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\json-lib-2.3-jdk15.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\poi-3.9-20121203.jar;C:\Program Files\Java\jdk1.8.0_65\lib\tools.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\chardet.jar;F:\workspace\shang1\WebRoot\WEB-INF\lib\asm-4.0.jar;F:\workspace\shang1\test.jar;E:\Program Files (x86)\JetBrains\IntelliJ IDEA 2016.1.1\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain java7.classbytecode.MethodHandleIntro

    }

}
