package scut.melody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.dao.UserInfoMapper;
import scut.melody.dao.WechatAuthMapper;
import scut.melody.entity.UserInfo;
import scut.melody.service.service.UserInfoService;
import scut.melody.state.State;

import java.util.Date;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserInfoController {
    @Autowired
    private UserInfoService uis;
    @Autowired
    private UserInfoMapper uim;
    @Autowired
    private WechatAuthMapper wam;
    @RequestMapping("/queryall")
    @ResponseBody
    public String queryall(@RequestParam(name = "page",required = false) int page, @RequestParam(name = "rows",required = false)int rows){
        System.out.println(page);
        System.out.println(rows);
        System.out.println("haha"+uis.queryallwithpage(page,rows));
        return   uis.queryallwithpage(page,rows);
    }
    @RequestMapping("/queryuser")
    @ResponseBody
    public String queryuser(@RequestParam(name = "page",required = false) int page,
                            @RequestParam(name = "rows",required = false)int rows,
                            @RequestParam(name = "start",required = false)Integer start,
                            @RequestParam(name = "end",required = false)Integer end){
        System.out.println(page);
        System.out.println(rows);
        System.out.println(start);
        System.out.println(end);
        if(start==null||end==null){
            return uis.queryallwithpage(page,rows);
        }
        else {
            return uis.querywithidpage(start,end,page,rows);
        }
    }

    @RequestMapping("/update")
    @ResponseBody
    public String update(
                         @RequestParam(name = "state",required = true) String State,
                         @RequestParam(name = "role",required = true) String Role,
                         @RequestParam(name = "userInfoId",required = true) String userInfoId
    ){
        System.out.println(userInfoId);
        Byte state = null;
       if(State.equals("锁定")){
          state=new Byte("0");
       }else {
           state = new Byte("1");
       }
        Byte role = new Byte(Role);
        UserInfo ui = new UserInfo();
        ui.setState(state);
        ui.setRole(role);
        ui.setUserInfoId(Integer.parseInt(userInfoId));
        int i = uim.updateByPrimaryKeySelective(ui);
        return String.valueOf(i);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public  String deletebyuserid(@RequestParam(name = "userInfoId",required = false)String userInfoId){
        System.out.println(userInfoId);
        int i = uim.deleteByPrimaryKey(Integer.parseInt(userInfoId));
        return String.valueOf(i);
    }

    @RequestMapping("/userbuyinfo")
    public  String queryuserbyinfo(@RequestParam(name = "userid")String userid, Map<String,String> data){
        System.out.println("需要查询的userid为："+userid);
      //  String openid = wam.findopenidbyuserid(userid);
     //   System.out.println(openid);
        data.put("userid",userid);
        return "admin/userinfotoodermaster";
    }
}
