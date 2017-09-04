package thinkinjava.annotations;

/**
 * Created by Think on 2016/6/21.
 */
public class Title {

    private int titlePosition;

    @Property(editor = "TitlePostitionEditor")
    public void setTitlePosition(int p) { this.titlePosition = p; }

}
