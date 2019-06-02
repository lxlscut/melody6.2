package scut.melody.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/jump")
public class Jump {

    /**
     * 管理员主页
     * @return
     */
    @RequestMapping("admin/index")
    public String toindex(){
        return "admin/index";
    }

    /**
     * 所有用户界面
     * @return
     */
    @RequestMapping("admin/alluser")
    public String toalluser(){ return "admin/userinfo";
    }

    /**
     * 按条件查询用户
     * @return
     */
    @RequestMapping("admin/queryuser")
    public String queryuser(){
        return "admin/userinfoquery";
    }

    /**
     * 本地用户
     * @return
     */
    @RequestMapping("admin/localuser")
    public String localuser(){
        return "admin/localuser";
    }

    /**
     * 微信授权用户信息
     * @return
     */
    @RequestMapping("admin/wxuser")
    public String wxuser(){
        return "admin/wxuser";
    }

    /**
     * 账单信息
     * @return
     */
    @RequestMapping("admin/paybill")
    public String paybill(){
        return "admin/paybill";
    }

    /**
     * 订单信息
     * @return
     */
    @RequestMapping("admin/ordermaster")
    public String ordermaster(){
        return "admin/odermaster";
    }

//    /**
//     * 登录界面
//     */
//    @RequestMapping("admin/login")
//    public String login(){
//        return "admin/login";
//    }

    /**
     * 未授权页面
     * @return
     */
    @RequestMapping("admin/auth")
    public String auth(){
        return "admin/Auth";
    }

}
