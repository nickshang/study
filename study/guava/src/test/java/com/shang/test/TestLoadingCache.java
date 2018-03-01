package com.shang.test;

import com.google.common.cache.*;
import org.junit.Test;

import java.util.concurrent.Callable;
import java.util.concurrent.TimeUnit;

/**
 * Guava cache模块学习
 * callable callback的实现
 *
 * 1. cacheLoader
 * 2. callable callback
 * 通过这两种方法创建的cache，和通常用map来缓存的做法比，不同在于，这两种方法都实现了一种逻辑——从缓存中取key X的值，如果该值已经缓存过了，则返回缓存中的值，如果没有缓存过，可以通过某个方法来获取这个值。
 * 但不同的在于cacheloader的定义比较宽泛，是针对整个cache定义的，可以认为是统一的根据key值load value的方法。而callable的方式较为灵活，允许你在get的时候指定。
 *
 *  回收的参数：
 *　　1. 大小的设置：CacheBuilder.maximumSize(long)  CacheBuilder.weigher(Weigher)  CacheBuilder.maxumumWeigher(long)
 *　　2. 时间：expireAfterAccess(long, TimeUnit) expireAfterWrite(long, TimeUnit)
 *　　3. 引用：CacheBuilder.weakKeys() CacheBuilder.weakValues()  CacheBuilder.softValues()
 *　　4. 明确的删除：invalidate(key)  invalidateAll(keys)  invalidateAll()
 *　　5. 删除监听器：CacheBuilder.removalListener(RemovalListener)
 *　refresh机制：
 *　　1. LoadingCache.refresh(K)  在生成新的value的时候，旧的value依然会被使用。
 *　　2. CacheLoader.reload(K, V) 生成新的value过程中允许使用旧的value
 *　　3. CacheBuilder.refreshAfterWrite(long, TimeUnit) 自动刷新cache
 *
 * 参考：http://www.cnblogs.com/peida/p/Guava_Cache.html
 *
 * @author NICK
 * @create 2018-02-28
 **/


public class TestLoadingCache {

    /**
     *  cacheLoader方式实现实例
     */
    @Test
    public void TestLoadingCache() throws Exception{
        LoadingCache<String,String> cahceBuilder= CacheBuilder
                .newBuilder()
                .maximumSize(1)
                .expireAfterAccess(10, TimeUnit.SECONDS)
                .build(new CacheLoader<String, String>(){
                    @Override
                    public String load(String key) throws Exception {
                        String strProValue="hello "+key+"!";
                        return strProValue;
                    }

                });

        System.out.println("jerry value:"+cahceBuilder.apply("jerry"));
        System.out.println("jerry value:"+cahceBuilder.get("jerry"));
        System.out.println("peida value:"+cahceBuilder.get("peida"));
        System.out.println("peida value:"+cahceBuilder.apply("peida"));
        System.out.println("lisa value:"+cahceBuilder.apply("lisa"));
        cahceBuilder.put("harry", "ssdded");
        System.out.println("harry value:"+cahceBuilder.get("harry"));

        // 获取值，如果没有缓存，则返回为NULL
        System.out.println("harry value:"+cahceBuilder.getIfPresent("jerry"));

    }

    /**
     * callable callback的实现
      * @throws Exception
     */
    @Test
    public void testcallableCache()throws Exception{
        Cache<String, String> cache = CacheBuilder.newBuilder().maximumSize(1000).build();
        String resultVal = cache.get("jerry", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"jerry"+"!";
                return strProValue;
            }
        });
        System.out.println("jerry value : " + resultVal);

        resultVal = cache.get("peida", new Callable<String>() {
            public String call() {
                String strProValue="hello "+"peida"+"!";
                return strProValue;
            }
        });
        System.out.println("peida value : " + resultVal);
    }

    /**
     * 不需要延迟处理(泛型的方式封装)
     * @return
     */
    public  <K , V> LoadingCache<K , V> cached(CacheLoader<K , V> cacheLoader) {
        LoadingCache<K , V> cache = CacheBuilder
                .newBuilder()
                .maximumSize(2)
                .weakKeys()
                .softValues()
                .refreshAfterWrite(120, TimeUnit.SECONDS)
                .expireAfterWrite(10, TimeUnit.MINUTES)
                .removalListener(new RemovalListener<K, V>(){
                    @Override
                    public void onRemoval(RemovalNotification<K, V> rn) {
                        System.out.println(rn.getKey()+"被移除");

                    }})
                .build(cacheLoader);
        return cache;
    }

    /**
     * 通过key获取value
     * 调用方式 commonCache.get(key) ; return String
     * @param key
     * @return
     * @throws Exception
     */
    public  LoadingCache<String , String> commonCache(final String key) throws Exception{
        LoadingCache<String , String> commonCache= cached(new CacheLoader<String , String>(){
            @Override
            public String load(String key) throws Exception {
                return "hello "+key+"!";
            }
        });
        return commonCache;
    }

    @Test
    public void testCache() throws Exception{
        LoadingCache<String , String> commonCache=commonCache("peida");
        System.out.println("peida:"+commonCache.get("peida"));
        commonCache.apply("harry");
        System.out.println("harry:"+commonCache.get("harry"));
        commonCache.apply("lisa");
        System.out.println("lisa:"+commonCache.get("lisa"));
    }
}
