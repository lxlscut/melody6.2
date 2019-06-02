package scut.melody.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Configuration
@RequestMapping("/visit")
public class login {
    @RequestMapping("/login")
    @ResponseBody
    public String login(@RequestParam(name = "username") String name, @RequestParam(name = "password") String password){
        System.out.println("name:  "+name+"password:  "+password);
        /**
         * 使用shiro来编写认证操作
         */
        //1.获取subject
         Subject subject = SecurityUtils.getSubject();
        //2.封装用户数据
        UsernamePasswordToken token = new UsernamePasswordToken(name,password);
        //执行登录方法
        try {
            subject.login(token);
            //没有任何异常即代表登陆成功
            System.out.println("登陆成功");
            return "1";
        }catch (Exception e){
            //登陆失败
            System.out.println("登录失败");
            return "0";
        }
    }
    @RequestMapping("/logout")
    @ResponseBody
    public String logout(){
        Subject subject = SecurityUtils.getSubject();
        if(subject.isAuthenticated())
        {
            subject.logout();
            System.out.println(subject);
            return "1";
        }
        else
            return "0";
    }

    @RequestMapping("/updatepassword")
    @ResponseBody
    public String updatepassword(String oldpassword,String newpassword){


        //        String hashAlgorithmName = "MD5";//加密方式
//        Object crdentials = "333";//密码原值
//        Object salt = "1";//盐值
//        int hashIterations = 10;//加密1024次
//        Object result = new SimpleHash(hashAlgorithmName,crdentials,salt,hashIterations);
//        System.out.println(result);
         return null;
    }



}
