package sometest;

import java.util.HashMap;
import java.util.Map;

public class MapKeyNull {
    public static void main(String[] args) {
        Map<String, String> map = new HashMap<>();
        map.put(null,"aaa");
        System.out.println(map.get(null));
    }
}
