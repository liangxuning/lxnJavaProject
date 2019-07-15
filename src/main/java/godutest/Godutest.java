package godutest;

import com.boco.godu.command.Constant;
import com.boco.godu.rs.client.ClientDataDispatcher;
import com.boco.godu.rs.pojo.QueryInfo;
import com.boco.godu.rs.pojo.UserInfo;

public class Godutest {
    public static void main(String[] args) {
        ClientDataDispatcher dis = new ClientDataDispatcher(
                "10.31.2.35:8899");
        QueryInfo info = dis.sendMessage("QUERYUSER"
                + " username=boco_test_js");
        dis.close();
        if (info.isSuccess()) {
            info.getObject();
            UserInfo.User user = (UserInfo.User) info.getObject();
            if(user.getIsadmin()!=1){
            }
        }
    }
}
