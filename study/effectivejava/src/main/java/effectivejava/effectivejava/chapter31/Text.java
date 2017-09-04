
package effectivejava.effectivejava.chapter31;

import java.util.EnumSet;
import java.util.Set;

/**
 * 功能描述：《Effective Java 第二版本》 31条 用EnumSet代替位域</p>
 * @author Nick
 *
 */

public class Text {   
    public enum Style {   
        BOLD, ITALIC, UNDERLINE, STRIKETHROUGH   
    }   
  
    // Any Set could be passed in, but EnumSet is clearly best   
    public void applyStyles(Set<Style> styles) {   
        // Body goes here   
    	for (Style s : styles){
    		System.out.println( "Style Name:" + s.name());
    		System.out.println( "Style ordinal:" + s.ordinal());
    	}
    }   
  
    // Sample use   
    public static void main(String[] args) {   
        Text text = new Text();
        text.applyStyles(EnumSet.of(Style.BOLD, Style.ITALIC));
    }   
}  
