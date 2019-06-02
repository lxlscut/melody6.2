package scut.melody.shiro;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.crypto.RandomNumberGenerator;
import org.apache.shiro.crypto.SecureRandomNumberGenerator;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;

import java.util.Map;

public class VerityUtil {

    public static String SALT_STR = "salt";
    public static String PASSWORD_STR = "password";
    public static String LOGIN_NAME_STR = "loginName";
    //加密方式
    public static String ALGORITHMNAME = "md5";
    //加密次数
    public static int HASHITERATIONS = 2;

    private static RandomNumberGenerator randomNumberGenerator = new SecureRandomNumberGenerator();

    /**
     * shiro 盐值加密
     *
     */
    public static void encryptPassword(Map userFormMap) {
        String salt = MapUtils.getString(userFormMap, SALT_STR);
        if (StringUtils.isBlank(salt)) {
            salt = randomNumberGenerator.nextBytes().toHex();
            userFormMap.put(SALT_STR, salt);
        }
        String newPassword = new SimpleHash(
                ALGORITHMNAME,
                userFormMap.get(PASSWORD_STR),
                ByteSource.Util.bytes(userFormMap.get(LOGIN_NAME_STR) + salt),
                HASHITERATIONS
        ).toHex();
        userFormMap.put(PASSWORD_STR, newPassword);
    }

}