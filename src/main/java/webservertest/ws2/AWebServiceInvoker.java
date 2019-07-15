package webservertest.ws2;

public abstract class AWebServiceInvoker {
//	public Logger log = LoggerFactory.getLogger(com.boco.godu.rs.webService.AWebServiceInvoker.class);
/**
 * 获取网元密码
 * @param xmlInfo
 * @return
 * @throws Exception
 */
	 public abstract Object invoke(String xmlInfo) throws Exception;
	 /**
	  * 获取资源名称
	  * @return
	  * @throws Exception
	  */
	 public abstract Object invokeServiceName(String xmlInfo) throws Exception;
	 /**
	  * 批量获取网元密码
	  * @param xmlInfo
	  * @return
	  * @throws Exception
	  */
	 public abstract Object invokeBatch(String xmlInfo) throws Exception;
}
