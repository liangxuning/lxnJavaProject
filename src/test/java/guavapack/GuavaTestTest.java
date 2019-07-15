package guavapack;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;
import com.google.common.collect.Lists;
import com.google.common.io.Files;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;
import java.util.List;

import static com.google.common.truth.Truth.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class GuavaTestTest {

    @org.junit.jupiter.api.Test
    void sum() {
    }
    @Test
    public void fileReadlines() {
        File file = new File(GuavaTest.class.getResource("/ehcache.xml").getFile());
        List<String> lines = null;
        try {
            lines =  Files.readLines(file, Charset.defaultCharset());
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String s :lines) {
            System.out.println(s);
        }
    }
    @Test
    public void testJoiner() {
        Joiner joiner = Joiner.on(";").skipNulls();
        String result1 = joiner.join("Harry", null, "Ron", "Hermione");
        assertThat(result1).isEqualTo("Harry;Ron;Hermione");
        String result2 = Joiner.on(",").join(Arrays.asList(1, 5, 7));
        assertThat(result2).isEqualTo("1,5,7");
    }
    @Test
    public void testSplitter() {
        List<String> list1 = Splitter.on(';')
                .trimResults()//移除结果字符串的前导空白和尾部空白
                .omitEmptyStrings()//从结果中自动忽略空字符串
                .splitToList("foo;bar;; ; qux;");
        assertThat(list1).isEqualTo(Lists.newArrayList("foo", "bar", "qux"));
        List<String> list2 = Splitter.fixedLength(3)//限制拆分出的字符串数量
                .splitToList("这使得splitter实例都是线程安全的");
        assertThat(list2).isEqualTo(Lists.newArrayList("这使得", "spl", "itt", "er实", "例都是", "线程安", "全的"));
    }
}