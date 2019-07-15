import org.apache.commons.text.StringEscapeUtils;
import org.junit.jupiter.api.Test;

public class string2char {
    @Test
    public void string2onechar() {
        String s = "\\r";
        System.out.println("1");
        System.out.println(StringEscapeUtils.unescapeJava(s));
        System.out.println("2");
    }
}
