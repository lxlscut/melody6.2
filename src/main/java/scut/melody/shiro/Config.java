package scut.melody.shiro;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.authc.pam.AtLeastOneSuccessfulStrategy;
import org.apache.shiro.authc.pam.ModularRealmAuthenticator;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

@Configuration
public class Config {
    /**
     * 创建shirofliterbean配置类
     */
    @Bean
    public ShiroFilterFactoryBean getshirofactorybean(@Qualifier("mysecurity")DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        /**
         * 添加shiro的内置过滤器，实现相关页面的拦截
         * anon;无需登录即可访问
         * authc:必须认证才能访问
         * user:如果使用remember可以直接访问
         * perms:该资源必须得到资源权限
         * role:该资源必须得到角色权限
         */
        Map<String,String> flitermap = new LinkedHashMap<>();
        flitermap.put("/jump/admin/index","authc");

        flitermap.put("/img/**", "anon");//img
        flitermap.put("/js/**", "anon");//js
        flitermap.put("/css/**", "anon");//css

     //   flitermap.put("/jump/admin/index","anon");
     //   不配置会禁止访问静态文件



     //   flitermap.put("/jump/admin/alluser","perms[user:add]");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(flitermap);
        //设置跳转的登录页面
        shiroFilterFactoryBean.setLoginUrl("http://localhost:8080/login");
        shiroFilterFactoryBean.setUnauthorizedUrl("http://localhost:8080/jump/admin/Auth");
        return shiroFilterFactoryBean;
    }
    /**
     * 创建defaultwebsecurityManager
     */
       @Bean("mysecurity")
    public DefaultWebSecurityManager getWebSecurityManager(@Qualifier("Authenticator")ModularRealmAuthenticator authenticator){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        //使用Realm里的方式进行授权验证
        securityManager.setAuthenticator(authenticator);
        return securityManager;
    }


    /**
     * 创建realm
     * @param matcher
     * @return
     */
    @Bean("myrealm")
    public Realm1 getrealm(@Qualifier("CredentialsMatcher")HashedCredentialsMatcher matcher){
        Realm1 myrealm =  new Realm1();
        myrealm.setCredentialsMatcher(matcher);
        return myrealm;
    }

    /**
     * 针对明文的初始密码
     * @return
     */
    @Bean("myrealm2")
    public Realm2 getrealm2(){
        return new Realm2();
    }
    /**
     * 设置加密方式
     * @return
     */
    @Bean("CredentialsMatcher")
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
        // 使用md5 算法进行加密
        matcher.setHashAlgorithmName("MD5");
        // 设置散列次数： 意为加密几次
        matcher.setHashIterations(10);
        return matcher;
    }


    @Bean("Authenticator")
    public ModularRealmAuthenticator modularRealmAuthenticator(@Qualifier("myrealm")Realm1 realm1, @Qualifier("myrealm2")Realm2 realm2){
        //自己重写的ModularRealmAuthenticator
        ModularRealmAuthenticator modularRealmAuthenticator = new ModularRealmAuthenticator();
        modularRealmAuthenticator.setAuthenticationStrategy(new AtLeastOneSuccessfulStrategy());
        List<Realm> realms = new ArrayList<>();
        realms.add(realm1);
        realms.add(realm2);
        modularRealmAuthenticator.setRealms(realms);
        return modularRealmAuthenticator;
    }



}
