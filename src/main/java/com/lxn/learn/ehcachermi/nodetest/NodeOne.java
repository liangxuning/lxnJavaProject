package com.lxn.learn.ehcachermi.nodetest;

import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;

public class NodeOne {
    public static void main(String[] args) throws InterruptedException {
        CacheManager cacheManager = new CacheManager(NodeOne.class.getResource("/ehcache.xml"));
        Cache cache = cacheManager.getCache("NE_INFO");
        Element element = new Element("client1", "client1");
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
