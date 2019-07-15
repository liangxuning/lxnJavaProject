package ehcachermi.nodetest;

import main.java.ehcachermi.vo.NeInfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class NodeTwo {
    public static void main(String[] args) throws InterruptedException {
        CacheManager cacheManager = new CacheManager(NodeTwo.class.getResource("/ehcache2.xml"));
        Cache cache = cacheManager.getCache("NE_INFO");
        NeInfo neInfo = new NeInfo();
        neInfo.setNe_id("123");
        Element element = new Element("client2", "client2");
        cache.put(element);
        while (true) {
            Thread.sleep(3000);
            System.out.println("\n");
            for (Object key : cache.getKeys()) {
                System.out.println(key + ":" + cache.get(key));
            }
        }
    }
}
