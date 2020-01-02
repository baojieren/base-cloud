package ink.baojie.cloud.util;

import java.util.Random;

public class RandomUtil {

    /**
     * a-z A-Z 0-9随机数
     */
    public static String genStr(int len) {
        String str = "0123456789qwertyuiopasdfghjklzxcvbnmQWERTYUIOPASDFGHJKLZXCVBNM";
        Random r = new Random();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < len; i++) {
            sb.append(str.charAt(r.nextInt(62)));
        }
        return sb.toString();
    }
}
