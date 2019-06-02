package scut.melody.shiro;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.beans.factory.annotation.Autowired;
import scut.melody.entity.Adm;
import scut.melody.service.service.AdminService;

public class Realm2 extends AuthorizingRealm {
    @Autowired
    private AdminService as;
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        System.out.println("执行二授权逻辑");
        Subject subject = SecurityUtils.getSubject();
        Adm adm = (Adm)subject.getPrincipal();
        SimpleAuthorizationInfo info = new SimpleAuthorizationInfo();
        info.addStringPermission("user:ae");
        return info;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        System.out.println("执行2认证逻辑");
        //编写shiro判断逻辑
        //1.判断用户名
        UsernamePasswordToken token = (UsernamePasswordToken)authenticationToken;
        //查找数据库的用户名与密码,通过token来获取用户名与密码
        Adm adm = as.selectbyusername(token.getUsername());
        //如果用户名不存在
        if(adm==null){
            return null;
        }
   //
        //2.判断密码,传入password，该方法会自动验证
         return new SimpleAuthenticationInfo(adm,adm.getPassword(),"");
     //   return new SimpleAuthenticationInfo(adm,adm.getPassword(), ByteSource.Util.bytes(String.valueOf(adm.getId())),this.getName());
    }

}
