package scut.melody.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import scut.melody.entity.Adm;
import scut.melody.service.service.AdminService;

/**
 * 授权认证的逻辑
 */
@Component
public class Realm1 extends AuthorizingRealm {
    @Autowired
    private AdminService as;
    /**
     * 执行授权逻辑
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        Adm adm = (Adm)subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:ad");
        return info;
    }

    /**
     * 执行认证逻辑
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行认证逻辑");
        //编写shiro判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //查找数据库的用户名与密码,通过token来获取用户名与密码
        Adm adm = as.selectbyusername(token.getUsername());
        //如果用户名不存在
       if(adm==null){
           return null;
       }
        //2.判断密码,传入password，该方法会自动验证
       // return new SimpleAuthenticationInfo(adm,adm.getPassword(),"");
       return new SimpleAuthenticationInfo(adm,adm.getPassword(), ByteSource.Util.bytes(String.valueOf(adm.getId())),this.getName());
//        return null;
    }

//    public static void main(String[] args) {
//        String hashAlgorithmName = "MD5";//加密方式
//        Object crdentials = "333";//密码原值
//        Object salt = "1";//盐值
//        int hashIterations = 10;//加密1024次
//        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
//        System.out.println(result);
//    }
}
