package ehcachermi;

import main.java.ehcachermi.vo.NeInfo;
import net.sf.ehcache.Cache;
import net.sf.ehcache.CacheManager;
import net.sf.ehcache.Element;
import net.sf.ehcache.search.*;
import net.sf.ehcache.search.expression.Criteria;

import java.util.*;
import java.util.Map.Entry;

/**
 * @createTime 2013-6-19 下午07:52:45
 * @author zhangshupei
 * @version V3.1
 */
public class EhcacheFactory {
	private CacheManager manager = null;
	private static EhcacheFactory ehcacheFactory = null;
	public static EhcacheFactory getInstance() {
		if (null == ehcacheFactory) {
			ehcacheFactory = new EhcacheFactory();
		}
		return ehcacheFactory;
	}

	private EhcacheFactory() {

	}

	private CacheManager getCacheManager() {
		if (null == manager) {
			manager = CacheManager.newInstance(getClass().getResource(
					"/ehcache.xml"));
		}
		return manager;
	}

	/**
	 * 查询cache
	 * 
	 * @param cacheName
	 * @return
	 */
	public Cache getCache(String cacheName) {
		//根据主cache的到当前Cache
		getCacheManager();
		return manager.getCache(cacheName);
	}
	/**
	 * 插入
	 * @param cacheName
	 * @param key
	 * @param value
	 * @return
	 */
	public void insertCache(String cacheName, Object key, Object value) {
		Cache cache = getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
	}

	/**
	 * 插入并判断是否成功
	 * 
	 * @param cacheName
	 * @param key
	 * @param value
	 */
	public boolean updateCache(String cacheName, Object key,
                               Object value) {
		Cache cache = getCache(cacheName);
		Element element = new Element(key, value);
		cache.put(element);
		//插入一条记录后，查询一下是否插入成功
		Object obj=cache.get(key);
		if(obj==null){
			return false;
		}else{
			return true;
		}
	}
	/**
	 * 根据KEY值删除某一条记录
	 * @param cacheName
	 * @param key
	 * @return
	 */
   public boolean deleteCache(String cacheName, Object key){
	Cache cache = getCache(cacheName);
	return cache.remove(key);
}
   /**
    * 删除cacheName的所有记录
    * @param cacheName
    */
   public void deleteCache(String cacheName){
	   Cache cache=getCache(cacheName);
	   cache.removeAll();
   }
	/**
	 * 查询
	 * 
	 * @param cacheName
	 * @param key
	 * @return
	 */
	public Object queryCache(String cacheName, String key) {
//		cacheName=CacheStatus.getNowUsedCacheName(cacheName);
		Cache cache = getCache(cacheName);
		Element el = cache.get(key);
		if (null != el) {
			return  el.getObjectValue();
		}
		return null;
	}
    
	public List<String> queryCacheAllKey(String cacheName){
		Cache cache = getCache(cacheName);
		return (List<String>)cache.getKeys();
	}
//	public String queryCahceString(String cacheName, String key) {
//		Cache cache = getCache(cacheName);
//		Element el = cache.get(key);
//		if (null != el&&(el.getObjectValue() instanceof String)) {
//			return (String) el.getObjectValue();
//		}
//		return null;
//	}
	public List<Object> queryCacheInOrderByAsc(String cacheName, Map<String,List<Object>> queryCondition, List<String> orderCondition){
		List<Object> resultList=new ArrayList<Object>();
		Cache cache=getCache(cacheName);
		Query query=cache.createQuery();
		for(Entry<String,List<Object>> condition:queryCondition.entrySet()){
			String key=condition.getKey();
			List<Object> values=condition.getValue();
			Attribute<Object> attribute=cache.getSearchAttribute(key);
			query.includeAttribute(attribute);
			query.addCriteria(attribute.in(values));
		}
		query.includeValues();
		for(String condition:orderCondition){
			query.addOrderBy(new Attribute<String>(condition), Direction.ASCENDING);
		}
		Results results=query.execute();
		List<Result> allList=results.all();
		int size=allList.size();
		for(int i=0;i<size;i++){
			resultList.add(allList.get(i).getValue());
		}
		return resultList;
	}
	/**
	 * 根据查询条件queryCondition查询相应对象
	 * @param cacheName
	 * @param queryCondition
	 * @return
	 */
	public List<Object> queryCache(String cacheName, Map<String,Object> queryCondition){
//		cacheName=CacheStatus.getNowUsedCacheName(cacheName);
		List<Object> resultList=new ArrayList<Object>();
		Cache cache=getCache(cacheName);
		Query query=cache.createQuery();
		for(Entry<String,Object> condition:queryCondition.entrySet()){
			String key=condition.getKey();
			Object value=condition.getValue();
			//使用自己实现的Criteria
			Criteria owneq=new ehcachermi.OwnEqualTo(key,value);
			Attribute<Object> attribute=cache.getSearchAttribute(key);
			query.includeAttribute(attribute);
			query.addCriteria(owneq);
		}
		query.includeValues();
		Results results=query.execute();
		List<Result> allList=results.all();
		int size=allList.size();
		for(int i=0;i<size;i++){
			resultList.add(allList.get(i).getValue());
		}
		return resultList;
	}
	/**
	 * 根据查询条件queryCondition查询相应对象
	 * @param cacheName
	 * @param queryCondition
	 * @return
	 */
	public List<Object> queryCacheOrderByDes(String cacheName, Map<String,Object> queryCondition, List<String> orderCondition){
		List<Object> resultList=new ArrayList<Object>();
		Cache cache=getCache(cacheName);
		Query query=cache.createQuery();
		for(Entry<String,Object> condition:queryCondition.entrySet()){
			String key=condition.getKey();
			Object value=condition.getValue();
			//使用自己实现的Criteria
			Criteria owneq=new ehcachermi.OwnEqualTo(key,value);
			Attribute<Object> attribute=cache.getSearchAttribute(key);
			query.includeAttribute(attribute);
			query.addCriteria(owneq);
			//query.addCriteria(attribute.eq(value));
		}
		query.includeValues();
		for(String condition:orderCondition){
			query.addOrderBy(new Attribute<String>(condition), Direction.DESCENDING);
		}
		Results results=query.execute();
		List<Result> allList=results.all();
		int size=allList.size();
		for(int i=0;i<size;i++){
			resultList.add(allList.get(i).getValue());
		}
		return resultList;
	}
	public List<Object> queryCacheOrderByAsc(String cacheName, Map<String,Object> queryCondition, List<String> orderCondition){
		List<Object> resultList=new ArrayList<Object>();
		Cache cache=getCache(cacheName);
		Query query=cache.createQuery();
		for(Entry<String,Object> condition:queryCondition.entrySet()){
			String key=condition.getKey();
			Object value=condition.getValue();
			//使用自己实现的Criteria
			Criteria owneq=new ehcachermi.OwnEqualTo(key,value);
			Attribute<Object> attribute=cache.getSearchAttribute(key);
			query.includeAttribute(attribute);
			query.addCriteria(owneq);
			//query.addCriteria(attribute.eq(value));
		}
		query.includeValues();
		for(String condition:orderCondition){
			query.addOrderBy(new Attribute<String>(condition), Direction.ASCENDING);
		}
		Results results=query.execute();
		List<Result> allList=results.all();
		int size=allList.size();
		for(int i=0;i<size;i++){
			resultList.add(allList.get(i).getValue());
		}
		return resultList;
	}
	public static void main(String[] args) {
		System.out.println(EhcacheFactory.class.getResource("/ehcache2.xml"));

		EhcacheFactory ehcache=EhcacheFactory.getInstance();
		NeInfo neInfo = new NeInfo();
		neInfo.setNe_id("111");
		neInfo.setAcc_source(1);
		ehcache.insertCache("NE_INFO", "111", neInfo);
		Map<String, Object> searchMap = new HashMap<String, Object>();
        searchMap.put("acc_source", "1");
		List<Object> ss = ehcache.queryCache("NE_INFO", searchMap);
        System.out.println((NeInfo)ss.get(0));
		NeInfo neinfo = new NeInfo();
		neinfo.setNe_id("123");
		ehcache.insertCache("NEINFO", "NE_INFO", neinfo);
		searchMap.put("ne_id", "123");
		List<Object> re=(List<Object>)ehcache.queryCache("NEINFO", searchMap);
		System.out.println("======="+re.size());
	}
}
