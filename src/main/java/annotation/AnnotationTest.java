package annotation;

import javax.xml.ws.WebServiceClient;
import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Proxy;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Map;

@WebServiceClient(name = "QuerySysRes", targetNamespace = "http://sysres.scgk.webservice.server.iam.ultrapower.com", wsdlLocation = "http://10.102.102.101/ams/account_service/services/QuerySysRes?wsdl")
public class AnnotationTest {
    private static String s;
    private static URL url;
    private String ss;
    static {
        s = "aaa";
    }
    public AnnotationTest() {
        ss = s;
    }
    public static void main(String[] args) throws NoSuchFieldException, IllegalAccessException, MalformedURLException {
        AnnotationTest annotationTest = new AnnotationTest();
        Annotation annotation = annotationTest.getClass().getAnnotation(WebServiceClient.class);
        System.out.println(annotation.toString());
        InvocationHandler invocationHandler = Proxy.getInvocationHandler(annotation);
        Field field = invocationHandler.getClass().getDeclaredField("memberValues");
        field.setAccessible(true);
        Map memberValues = (Map) field.get(invocationHandler);
        memberValues.put("wsdlLocation", "lalala");
        System.out.println(annotation.toString());

        Field field1 = annotationTest.getClass().getDeclaredField("s");
        field1.setAccessible(true);
        url = new URL("http://10.102.102.101/ams/account_service/services/QuerySysRes?wsdl");
        System.out.println(url.toString());
    }
}
