package guavapack;

import com.google.common.base.CharMatcher;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import com.google.common.base.Splitter;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.ImmutableList;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.MapMaker;
import com.google.common.io.Files;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.TimeUnit;

public class GuavaTest {
    public static void main(String[] args) {
//        System.out.println(Splitter.on(',')
////                .trimResults()
//                .omitEmptyStrings()
//                .split("the ,quick, , brown         , fox,              jumps, over, the, lazy, little dog."));
        Optional<Integer> a = Optional.of(1);
        Optional<Integer> b = Optional.of(2);

//        System.out.println(sum(a,b).get());
        //2
//        ImmutableMap<String,String> map = ImmutableMap.of("key1", "value1", "key2", "value2");
//        for (Map.Entry entry:map.entrySet()) {
//            String s = (String) entry.getKey();
//            String ss = (String)entry.getValue();
//            System.out.println(s + "," + ss);
//        }
        //3
//        ImmutableList<String> list = ImmutableList.of("key1", "value1", "key2", "value2");
//        for (String s:list) {
//            System.out.println(s);
//        }
        //4
//        File file = new File(GuavaTest.class.getResource("/ehcache.xml").getFile());
//        List<String> lines = null;
//        try {
//            lines =  Files.readLines(file, Charset.defaultCharset());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        for (String s :lines) {
//            System.out.println(s);
//        }
        //5

        Cache<String, String> cache = CacheBuilder.newBuilder().expireAfterWrite(3, TimeUnit.SECONDS).build();
        cache.put("token", "");
        try {
            System.out.println(cache.get("aaa", new Callable<String>() {

                @Override
                public String call() throws Exception {
                    cache.put("aaa", "bbb");
                    return "123";
                }

            }));
        } catch (Exception e) {
            e.printStackTrace();
        }

        cache.put("a", "testa");
        cache.put("b", "testb");

        System.out.println(cache.asMap().get("a"));
        System.out.println(cache.asMap().get("b"));
        System.out.println(cache.asMap().get("aaa"));

        /**
         * 这里sleep4秒钟过后， 缓存都失效，再get就会根据绑定的function去获得value放在map中了。
         */
        try {
            Thread.sleep(4000);
        } catch (InterruptedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        /**
         * 看看这里的再输出，是不是就是新的值了！~
         */

        System.out.println(cache.asMap().get("a"));
        System.out.println(cache.asMap().get("b"));
        System.out.println(cache.asMap().get("c"));
    }

    public static Optional<Integer> sum(Optional<Integer> a,Optional<Integer> b){
        if(a.isPresent() && b.isPresent()){
            return Optional.of(a.get()+b.get());
        }
        return Optional.absent();
    }
}
