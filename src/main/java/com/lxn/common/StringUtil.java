package com.lxn.common;

import com.boco.godu.rs.resource.Constant;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;

public class StringUtil {
    public static String getSubString(String targetString, int byteIndex)
            throws Exception {
//        if (targetString.getBytes("UTF-8").length < byteIndex) {
//            throw new Exception("超过长度");
//        }
        String temp = targetString;
        for (int i = 0; i < targetString.length(); i++) {
            if (temp.getBytes("UTF-8").length <= byteIndex) {
                break;
            }
            temp = temp.substring(0, temp.length() - 1);
        }
        return temp;
    }
    public static int getByteLength(String s ) {
        byte[] bytes = s.getBytes();
        return bytes.length;
    }
    public static void main111(String[] args) throws Exception {
//        System.out.println(getSubString("liang旭宁", 2));
//        System.out.println(getByteLength("{\"messHead\":1,\"messBody\":{\"neId\":\"test02\",\"username\":\"godu\",\"operate\":\"发送指令\",\"statu\":1,\"messages\":\"ADD CLDPREANA1:CSCNAME\\u003d\\\"ALL\\\",CS\\u003dALL,PFX\\u003dK\\u0027055295056,MINCLDLEN\\u003d9,CDADDR\\u003dALL,CRP\\u003dALL,CLDNCN\\u003d\\\"DEFAULT\\\",NP\\u003d255,PT\\u003dFAILPROC,FCC\\u003dCV45,CLISRVCAT\\u003dHOT_BILL-0\\u0026IN-0\\u0026SMMO_FILTER1-0\\u0026SMMO_FILTER2-0\\u0026SMMO_BKLST1-0\\u0026SMMO_BKLST2-0\\u0026SMMT_BKLST1-0\\u0026SMMT_BKLST2-0\\u0026MO_BKLST-0\\u0026MT_BKLST-0\\u0026SMCS_ALLOW1-0\\u0026SMCS_ALLOW2-0\\u0026ODB1_ALLOW-0\\u0026SERVICE12-0\\u0026SERVICE13-0\\u0026SMMO_ODBBAOC_B_ALLOW_LIST-0\\u0026OSS1-0\\u0026OSS3-0\\u0026VIDEOPHONE-0\\u0026RESTR_CRBT-0\\u0026CFPHSERVICE-0\\u0026SERVICE20-0\\u0026COLLECTCALL-0\\u0026SERVICE22-0\\u0026OCSIPROTECT-0\\u0026TCSIPROTECT-0\\u0026SERVICE25-0\\u0026SMMO_SMC-0\\u0026OTHERPLMNSUB-0\\u0026UNLOCAL_USER-0\\u0026SERVICE29-0\\u0026SERVICE30-0\\u0026SUPPRESS_ANNOUNCEMENT-0\\u0026WPS-0\\u0026SERVICE33-0\\u0026SERVICE34-0\\u0026SRI_VER-0\\u0026RESTR_HOLD-0\\u0026RESTR_MPTY-0\\u0026RESTR_ECT-0\\u0026PFPHSERVICE-0\\u0026SERVICE40-0\\u0026CANCEL_CLDANA_TIMES-0\\u0026SERVICE42-0\\u0026SERVICE43-0\\u0026SERVICE44-0\\u0026SERVICE45-0\\u0026SERVICE46-0\\u0026SERVICE47-0\\u0026SERVICE48-0\\u0026SERVICE49-0\\u0026SERVICE50-0\\u0026SERVICE51-0\\u0026SERVICE52-0\\u0026SERVICE53-0\\u0026SERVICE54-0\\u0026SERVICE55-0\\u0026SERVICE56-0\\u0026SERVICE57-0\\u0026SERVICE58-0\\u0026SERVICE59-0\\u0026SERVICE60-0\\u0026SERVICE61-0\\u0026SERVICE62-0\\u0026SERVICE63-0\\u0026SERVICE64-0\\u0026SERVICE65-0\\u0026SERVICE66-0\\u0026SERVICE67-0\\u0026SERVICE68-0\\u0026SERVICE69-0\\u0026SERVICE70-0\\u0026SERVICE71-0\\u0026SERVICE72-0\\u0026SERVICE73-0\\u0026SERVICE74-0\\u0026SERVICE75-0\\u0026SERVICE76-0\\u0026SERVICE77-0\\u0026RCF-0\\u0026SUPPRESS_TCSI-0\\u0026SERVICE80-0\\u0026SERVICE81-0\\u0026SERVICE82-0\\u0026SERVICE83-0\\u0026SERVICE84-0\\u0026SERVICE85-0\\u0026SERVICE86-0\\u0026SERVICE87-0\\u0026SERVICE88-0\\u0026SERVICE89-0\\u0026SERVICE90-0\\u0026SERVICE91-0\\u0026SERVICE92-0\\u0026SERVICE93-0\\u0026SERVICE94-0\\u0026SERVICE95-0\\u0026SERVICE96-0\\u0026SERVICE97-0\\u0026SERVICE98-"));
        System.out.println(match("abaa", "a"));
//        System.out.println(match("Y", ">N]"));
//        System.out.println(match("*abc asd", "/*abc"));
    }

    public static void main(String[] args) {

        double i = 4.4;
        int sum = 0;
        while (i<8) {
            i = i + i * 0.1;
            sum++;
            System.out.println(i);
            System.out.println(sum+ "\n");
        }

        String s = "1,2,3,";
        System.out.println(s.substring(0,s.length()-1));
        String ss = "123,";
        System.out.println(ss.endsWith("."));
//        System.out.println(i);
//        System.out.println(sum);


    }
public static void main1111(String[] args) {
    String [] a = {"a"};

    System.out.println(a[2]);
}

    public static void main12(String[] args) {
//        String s  = "文件系统                 容量  已用  可用 已用% 挂载点\n" +
//                "/dev/dm-25                10G  243M  9.8G   3% /home/data/var/lib/docker/devicemapper/mnt/00d8c4d7495a8dc6a1430ed6cae75c39d49c39b0560bc5b9cae946c6160bd2b1\n" +
//                "tmpfs                     16G     0   16G    0% /dev/shm\n" +
//                "tmpfs                     16G   49M   16G    1% /run\n" +
//                "tmpfs                     16G     0   16G    0% /sys/fs/cgroup\n" +
//                "/dev/mapper/centos-root   50G   50G  188M  100% /\n" +
//                "/dev/mapper/centos-home  434G  9.7G  424G    3% /home\n" +
//                "/dev/vda1                497M  162M  336M   33% /boot\n" +
//                "tmpfs                    3.2G     0  3.2G    0% /run/user/0";
        String s = "/dev/dm-25                10G  0.243M  98G   3% /home/data/var/lib/docker/device\n";
        String ss = "-rw-r--r--   1 root root   2195560 8月  31 2020 6.0-rc2.tar.gz";
//        String ss = "月亮";

        String[] split = s.split("\\n");
//        boolean b = checkSpecialChar(split[1]);
//        System.out.println(b);
//        String expected = "(.*)\\s+([0-9]\\d*\\.?\\d*)\\w\\s+([0-9]\\d*\\.?\\d*)\\w?\\s+([0-9]\\d*\\.?\\d*)\\w?\\s+([0-9]\\d*\\.?\\d*)%\\s+(.*)";
//        String expected = ".*(\\d+)\\s+\\d+月\\s+\\d+\\s+\\d+:?\\d+\\s+(.*)";
        String expected = ".*\\d+\\s+\\w+\\s+\\w+\\s+(\\d+)\\s+(\\d+月)\\s+\\d+\\s+\\d+\\s+(.*)";
//        String expected = "([^x00-xff])";
//        String expected = "(.*)\\s+(\\d+)\\w\\s+([0-9]\\d*\\.?\\d*)\\w\\s+(\\d+)\\w?\\s+(\\d+)%\\s+(.*)";
        Pattern p = Pattern.compile(expected, Pattern.MULTILINE);
        Matcher m = p.matcher(ss);
//        Matcher m = p.matcher(split[0]);
        String str = null;
        if (m.find()) {
            for (int i = 1;i< 4;i++) {
                System.out.println(m.group(i));
            }
//            str = m.group(0);
        } else {
            System.out.println("error");
        }
//        System.out.println(str);

    }

    public static boolean checkSpecialChar(String str) throws PatternSyntaxException {
        // 清除掉所有特殊字符
        String regEx =  ".*[`~!@#$%^&*()+=|{}':;',\\[\\].<>/?~！@#￥%……&*（）——+|{}【】‘；：”“’。，、？\\\\]+.*";
        Pattern p = Pattern.compile(regEx);
        Matcher m = p.matcher(str);
        m.find();
        System.out.println(m.group());
        return m.matches();
    }

    public static Boolean match(String string, String expected) {
        Pattern p = Pattern.compile(expected, Pattern.MULTILINE);
        Matcher m = p.matcher(string);
//        System.out.println(m.groupCount()); 
        int i = 0;
        while (m.find()) {
            i++;
        }
        System.out.println(i);
        Boolean b = m.find();
        Boolean c = m.find();
        Boolean d = m.find();
        System.out.println(b);
        System.out.println(c);
        System.out.println(d);
        return b;
    }

    public static void main2(String[] args) {
        String s = "REG VNFC:NAME=\"vnrs\";\n" +
                "+++     NMS SERVER        2020-03-17 17:00:39\n" +
                "O&M     #2304\n" +
                "%%REG VNFC:NAME=\"vnrs\";%%\n" +
                "RETCODE = 0  Success\n" +
                "\n" +
                "Success\n" +
                "\n" +
                "---    END\n" +
                "DSP DATETIME:;\n" +
                "+++     NMS SERVER        2020-03-17 17:00:40\n" +
                "O&M     #2304\n" +
                "%%DSP DATETIME:;%%\n" +
                "RETCODE = 1  没有权限。\n" +
                "\n" +
                "没有权限。\n" +
                "\n" +
                "---    END\n" +
                "UNREG VNFC:NAME=\"vnrs\";\n" +
                "+++     NMS SERVER        2020-03-17 17:00:40\n" +
                "O&M     #2304\n" +
                "%%UNREG VNFC:NAME=\"vnrs\";%%\n" +
                "RETCODE = 0  Success\n" +
                "\n" +
                "Success\n" +
                "\n" +
                "---    END";

        String[] ss = s.split("DSP DATETIME:;\n");
        String[] sss = ss[1].split("UNREG VNFC:NAME=\"vnrs\";\n");
        System.out.println(ss[0]);
        System.out.println("*********************************");
        System.out.println(sss[0]);
        System.out.println("*********************************");
        System.out.println(sss[1]);
    }

    //数组对象能不能指
    public static void main3(String[] args) {
        String[] ss = new String[4];
        String[] sss = new String[7];
        ss = sss;
        System.out.println(ss.length);
    }

    public static void main11(String[] args) {
        String TRAN_PASSWORD = ".*\\{transfer:\\<[^{}<>]*\\>:password\\}.*";
        String TRAN_PASSWORDs = "\\{transfer:\\<[^{}<>]*\\>:password\\}";
        String s = "{transfer:<a>:password}";
        boolean matches = s.matches(TRAN_PASSWORD);
        System.out.println(matches);
        String variableStr = getVariableStr(TRAN_PASSWORDs, s);
        System.out.println(variableStr);
        String aConst = getConst(variableStr);
        System.out.println(aConst);
        String ss = s.replaceFirst(Constant.TRAN_PASSWORD, "(1\\$JS!#Lt)");
//        String ss = s.replaceFirst(Constant.TRAN_PASSWORD, "(1$JS!#It)");
        System.out.println(ss);
    }
    private static String getVariableStr(String regex, String mess) {
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(mess);
        String str = "";
        if (matcher.find()) {
            str = matcher.group(0);
        }
        return str;
    }
    public static String getConst(String str) {
        return str.substring(str.indexOf("<") + 1, str.indexOf(">"));
    }
}
