public class EncryptPwd {
    byte pchDefKey[] = { '!', '#', '&', '<', '>', '_', '|', '~' };
    private int Hex2Byte(int h) {

        if (h < 65) {
            h -= 0x30;
        } else {
            h = h + 10 - 65;
        }
        return h;
    }

    private byte[] Decode(byte[] pchEncoded, byte[] pchKey) {
        int nLen = 0;
        int nKeyLen = 0;
        int i = 0, j = 0;
        int decoded_length = pchEncoded.length / 2;
        byte[] pchDecoded = new byte[decoded_length];
        if (pchEncoded == null || pchKey == null || pchKey.length <= 0) {
            return null;
        }
        nLen = pchEncoded.length;
        nKeyLen = pchKey.length;
        if (nLen % 2 != 0) {
            return null;
        }
        for (i = 0, j = 0; i < nLen; i += 2) {
            int ch = ((Hex2Byte(pchEncoded[i]) << 4) & 0xf0)
                    | Hex2Byte(pchEncoded[i + 1]);
            int chK = pchKey[j % nKeyLen];
            ch -= chK;
            pchDecoded[j] = (byte) ch;
            j++;
        }
        return pchDecoded;
    }
    /**
     * 解密算法
     *
     * @param - 输入: 7DDFBE5151E4417BBD1EFB
     * @return - 输出: abc
     */
    public byte[] DecodePwd(byte[] chEncoded) {
        if (chEncoded.length < 16) {
            return null;
        }
        byte chEM[] = new byte[16];
        byte chM[] = new byte[8];
        byte temp[] = new byte[chEncoded.length - 16];
        byte chDecoded[] = new byte[(chEncoded.length - 16) / 2];

        if (chEncoded == null)
            return null;

        System.arraycopy(chEncoded, 0, chEM, 0, 16);
        chM = Decode(chEM, pchDefKey);
        if (chM != null) {
            System.arraycopy(chEncoded, 16, temp, 0, chEncoded.length - 16);
            chDecoded = Decode(temp, chM);
        }
        return chDecoded;
    }

    public String decodePWD(String str) {
        byte[] by = DecodePwd(str.getBytes());
        if (null != by) {
            String str1 = new String(by);
            return str1;
        }
        return str;
    }

    /**
     * 测试用
     *
     * @param args
     */
    public static void main(String args[]) {
        EncryptPwd ep = new EncryptPwd();
        String t = "";
//        System.out.println(ep.);
        System.out.println(new String(ep.decodePWD(t)));
        String s = "1,2,3,";
        System.out.println(s.substring(0,s.length()-1));
    }
}
