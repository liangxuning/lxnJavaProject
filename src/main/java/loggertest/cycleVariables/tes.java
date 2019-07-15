package loggertest.cycleVariables;

public class tes {
    public static void main(String[] args) {
        RootNode rootNode = new RootNode();
        Node node = new Node();
        rootNode.setNode(node);
//        node.setRootNode(rootNode);
        System.out.println("aaa");
    }
}
