package com.lxn.learn.ehcachermi.nodetest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class NodeThree {
    public static void main(String[] args) throws InterruptedException {
        CacheManager cacheManager = new CacheManager(NodeThree.class.getResource("/ehcache3.xml"));
        Cache cache = cacheManager.getCache("NE_INFO");
        Element element = new Element("client3", "client3");
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
