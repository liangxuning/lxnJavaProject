package packetinfotest.test2;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import packetinfotest.test.EleClassA;

public class Test2 {

    public static void main(String[] args) {
        RootClass rc = new RootClass();
        EleClassA a = new EleClassA();
        EleClassB b = new EleClassB();

        a.setAttrC("attrc");
        a.setEleA("eleA");
        a.setEleB("eleB");

        b.setAttrPassword("attrPassword");
        b.setAttrUserName("attrUsrName");
        b.setEleCode("eleCode");

        rc.setA(a);
        rc.setB(b);
        rc.setRoot("root");
        rc.setRootA("rootA");


        JAXBContext context;
        try {
            context = JAXBContext.newInstance(RootClass.class);
            Marshaller mar = context.createMarshaller();
            mar.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            mar.setProperty(Marshaller.JAXB_ENCODING, "UTF-8");

            StringWriter writer = new StringWriter();

            mar.marshal(rc, writer);

            System.out.println(writer.toString());
        } catch (JAXBException e) {
            e.printStackTrace();
        }

    }
}








