package ink.baojie.cloud.appauth8102.util;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTDecodeException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;

import java.util.Calendar;
import java.util.Date;

public class JwtUtils {

    /**
     * 生成token,expireTime后过期
     *
     * @param userId 用户名
     * @param salt   盐
     * @return 加密的token
     */
    public static String sign(String userId, String[] roles, String salt) {
        // Date date = new Date(System.currentTimeMillis() + time * 1000);
        Algorithm algorithm = Algorithm.HMAC256(salt);
        // 附带userId信息
        return JWT.create()
                .withClaim("userId", userId)
                .withArrayClaim("roles", roles)
                // .withExpiresAt(date)
                .withIssuedAt(new Date())
                .sign(algorithm);
    }

    /**
     * 获得token的签发时间,无需secret解密也能获得
     *
     * @return token中包含的签发时间
     */
    public static Date getIssuedAt(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getIssuedAt();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的用户id, 无需secret解密也能获得
     *
     * @return token中包含的用户id
     */
    public static String getUserId(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("userId").asString();
        } catch (JWTDecodeException e) {
            return null;
        }
    }

    /**
     * 获得token中的角色数组, 无需secret解密也能获得
     *
     * @return token中包含的角色数组
     */
    public static String[] getRoles(String token) {
        try {
            DecodedJWT jwt = JWT.decode(token);
            return jwt.getClaim("roles").asArray(String.class);
        } catch (JWTDecodeException e) {
            return null;
        }
    }


    /**
     * token是否过期
     *
     * @return true：过期
     */
    public static boolean isTokenExpired(String token) {
        Date now = Calendar.getInstance().getTime();
        DecodedJWT jwt = JWT.decode(token);
        return jwt.getExpiresAt().before(now);
    }

    /**
     * 生成随机盐,长度32位
     *
     * @return
     */
    public static String generateSalt() {
        SecureRandomNumberGenerator secureRandom = new SecureRandomNumberGenerator();
        String hex = secureRandom.nextBytes(16).toHex();
        return hex;
    }

}
