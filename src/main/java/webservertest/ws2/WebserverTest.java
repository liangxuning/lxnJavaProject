package webservertest.ws2;

public class WebserverTest {
    public static void main(String[] args) {
        DynamicInvoker invoker = new DynamicInvoker();
        String xml = "<?xml version=\"1.0\" encoding=\"UTF-8\"?>" +
                "<resource><resid>GODU</resid><res><ip>10.102.53.53</ip></res></resource>";
        try {
            String xmlinfo = String.valueOf(invoker.invokeServiceName(xml));
            System.out.println(xmlinfo);
        } catch (Exception e) {
            System.out.println("error");
            e.printStackTrace();
        }
    }
}
