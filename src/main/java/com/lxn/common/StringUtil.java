package com.lxn.common;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

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
    public static void main(String[] args) throws Exception {
//        System.out.println(getSubString("liang旭宁", 2));
//        System.out.println(getByteLength("{\"messHead\":1,\"messBody\":{\"neId\":\"test02\",\"username\":\"godu\",\"operate\":\"发送指令\",\"statu\":1,\"messages\":\"ADD CLDPREANA1:CSCNAME\\u003d\\\"ALL\\\",CS\\u003dALL,PFX\\u003dK\\u0027055295056,MINCLDLEN\\u003d9,CDADDR\\u003dALL,CRP\\u003dALL,CLDNCN\\u003d\\\"DEFAULT\\\",NP\\u003d255,PT\\u003dFAILPROC,FCC\\u003dCV45,CLISRVCAT\\u003dHOT_BILL-0\\u0026IN-0\\u0026SMMO_FILTER1-0\\u0026SMMO_FILTER2-0\\u0026SMMO_BKLST1-0\\u0026SMMO_BKLST2-0\\u0026SMMT_BKLST1-0\\u0026SMMT_BKLST2-0\\u0026MO_BKLST-0\\u0026MT_BKLST-0\\u0026SMCS_ALLOW1-0\\u0026SMCS_ALLOW2-0\\u0026ODB1_ALLOW-0\\u0026SERVICE12-0\\u0026SERVICE13-0\\u0026SMMO_ODBBAOC_B_ALLOW_LIST-0\\u0026OSS1-0\\u0026OSS3-0\\u0026VIDEOPHONE-0\\u0026RESTR_CRBT-0\\u0026CFPHSERVICE-0\\u0026SERVICE20-0\\u0026COLLECTCALL-0\\u0026SERVICE22-0\\u0026OCSIPROTECT-0\\u0026TCSIPROTECT-0\\u0026SERVICE25-0\\u0026SMMO_SMC-0\\u0026OTHERPLMNSUB-0\\u0026UNLOCAL_USER-0\\u0026SERVICE29-0\\u0026SERVICE30-0\\u0026SUPPRESS_ANNOUNCEMENT-0\\u0026WPS-0\\u0026SERVICE33-0\\u0026SERVICE34-0\\u0026SRI_VER-0\\u0026RESTR_HOLD-0\\u0026RESTR_MPTY-0\\u0026RESTR_ECT-0\\u0026PFPHSERVICE-0\\u0026SERVICE40-0\\u0026CANCEL_CLDANA_TIMES-0\\u0026SERVICE42-0\\u0026SERVICE43-0\\u0026SERVICE44-0\\u0026SERVICE45-0\\u0026SERVICE46-0\\u0026SERVICE47-0\\u0026SERVICE48-0\\u0026SERVICE49-0\\u0026SERVICE50-0\\u0026SERVICE51-0\\u0026SERVICE52-0\\u0026SERVICE53-0\\u0026SERVICE54-0\\u0026SERVICE55-0\\u0026SERVICE56-0\\u0026SERVICE57-0\\u0026SERVICE58-0\\u0026SERVICE59-0\\u0026SERVICE60-0\\u0026SERVICE61-0\\u0026SERVICE62-0\\u0026SERVICE63-0\\u0026SERVICE64-0\\u0026SERVICE65-0\\u0026SERVICE66-0\\u0026SERVICE67-0\\u0026SERVICE68-0\\u0026SERVICE69-0\\u0026SERVICE70-0\\u0026SERVICE71-0\\u0026SERVICE72-0\\u0026SERVICE73-0\\u0026SERVICE74-0\\u0026SERVICE75-0\\u0026SERVICE76-0\\u0026SERVICE77-0\\u0026RCF-0\\u0026SUPPRESS_TCSI-0\\u0026SERVICE80-0\\u0026SERVICE81-0\\u0026SERVICE82-0\\u0026SERVICE83-0\\u0026SERVICE84-0\\u0026SERVICE85-0\\u0026SERVICE86-0\\u0026SERVICE87-0\\u0026SERVICE88-0\\u0026SERVICE89-0\\u0026SERVICE90-0\\u0026SERVICE91-0\\u0026SERVICE92-0\\u0026SERVICE93-0\\u0026SERVICE94-0\\u0026SERVICE95-0\\u0026SERVICE96-0\\u0026SERVICE97-0\\u0026SERVICE98-"));
        System.out.println(match("Y", "/N]"));
        System.out.println(match("*abc asd", "/*abc"));
    }

    public static Boolean match(String string, String expected) {
        Pattern p = Pattern.compile(expected, Pattern.MULTILINE);
        Matcher m = p.matcher(string);
        Boolean b = m.find();
        return b;
    }
}
