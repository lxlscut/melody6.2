package scut.melody.controller;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.crypto.hash.SimpleHash;
import org.apache.shiro.util.ByteSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.entity.Adm;
import scut.melody.service.service.AdminService;

@Controller
public class test {
    @Autowired
    private AdminService as;
    @RequestMapping("/login")
    public String test(){
        return "admin/loginme";
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(name = "old")String oldp,@RequestParam(name = "new")String newp,@RequestParam(name = "new1")String new2){
        int i = 0;
        System.out.println(oldp);
        System.out.println(newp);
        System.out.println(new2);
        Subject subject = SecurityUtils.getSubject();
        Adm adm = (Adm) subject.getPrincipal();
        Object old2 = new SimpleHash("MD5",oldp, ByteSource.Util.bytes(String.valueOf(adm.getId())),10);
        if(adm.getPassword().equals(oldp)||adm.getPassword().equals(old2.toString())){
            String hashAlgorithmName = "MD5";//加密方式
            Object crdentials = newp;//密码原值
            Object salt = String.valueOf(adm.getId());//盐值
            int hashIterations = 10;//加密10次
            Object result = new SimpleHash(hashAlgorithmName,crdentials, ByteSource.Util.bytes(salt),hashIterations);
            System.out.println(result.toString());

            adm.setPassword(String.valueOf(result));
            i = as.updatepassword(adm);
        }else {
            i = 0;
        }
        System.out.println(i);
        return String.valueOf(i);
    }

}
