package webservertest.ws2;

// Web Service Dynamic Invoker
public class DynamicInvoker extends AWebServiceInvoker {
	/**
	 * 新4A的webService接口
	 */
	
	@Override
	public Object invoke(String xmlInfo) throws Exception {
		QuerySysRes querySysRes=new QuerySysRes();
		QuerySysResPortType querySysResPortType= querySysRes.getQuerySysResHttpPort();
		return querySysResPortType.getPwd(xmlInfo);
	}

	@Override
	public Object invokeBatch(String xmlInfo) throws Exception {
		QuerySysRes querySysRes=new QuerySysRes();
		QuerySysResPortType querySysResPortType= querySysRes.getQuerySysResHttpPort();
		return querySysResPortType.getMultPwd(xmlInfo);
	}

	@Override
	public Object invokeServiceName(String xmlInfo) throws Exception {
		QuerySysRes querySysRes=new QuerySysRes();
		QuerySysResPortType querySysResPortType= querySysRes.getQuerySysResHttpPort();
		return querySysResPortType.searchRes(xmlInfo);
	}

	/**
	 * 旧4A的webService接口
	 */
	
	/*@Override
	public Object invoke(String xmlInfo) throws Exception {
		AccService accS=new AccService();
		AccServicePortType type=accS.getAccServiceHttpPort();
		String pwd=type.getPwd(xmlInfo);
		return pwd;
	}
	@Override
	public Object invokeServiceName(String xmlInfo) throws Exception {
		AccService accS=new AccService();
		AccServicePortType type=accS.getAccServiceHttpPort();
		String serviceName=type.searchRes(xmlInfo);
		return serviceName;
	}
	@Override
	public Object invokeBatch(String xmlInfo) throws Exception {
		AccService accS=new AccService();
		AccServicePortType type=accS.getAccServiceHttpPort();
		String serviceNameBatch=type.getMultPwd(xmlInfo);
		return serviceNameBatch;
	}*/
}