package scut.melody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.dao.LocalAuthMapper;
import scut.melody.entity.LocalAuth;
import scut.melody.service.service.LocalUserService;

import java.util.Date;

@Controller
@RequestMapping("/localuser")
public class LocalUserController {
    @Autowired
    LocalUserService lus;
    @Autowired
    LocalAuthMapper lam;
    @RequestMapping("/alluser")
    @ResponseBody
    public String querylocaluser(@RequestParam(name = "page",required = false) int page,
                                 @RequestParam(name = "rows",required = false)int rows,
                                 @RequestParam(name = "start",required = false)Integer start,
                                 @RequestParam(name = "end",required = false)Integer end){
        String str = null;
        if(start!=null||end!=null) {
            str = lus.querywithpage(start,end,rows,page);
            System.out.println(str);
        }else {
            str = lus.queryallwithpage(rows*(page-1),rows*page);
        }
        return str;
    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(name = "localAuthId",required = false) String local_auth_id,
                         @RequestParam(name = "userId",required = false)String user_id,
                         @RequestParam(name = "username",required = false)String username,
                         @RequestParam(name = "createTime",required = false) String create_time,
                         @RequestParam(name = "updateTime",required = false)String update_time
                         ){
        Integer localauthid = Integer.parseInt(local_auth_id);
        Date create = new Date(create_time);
        Date updatetime = new Date(update_time);
        LocalAuth localAuth = new LocalAuth();
        localAuth.setLocalAuthId(localauthid);
        localAuth.setCreateTime(create);
        localAuth.setUpdateTime(updatetime);
        localAuth.setUserId(user_id);
        localAuth.setUsername(username);
        int i = lam.updateByPrimaryKeySelective(localAuth);
        return String.valueOf(i);
    }

    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(name = "localAuthId")String local_auth_id){
        int lai = Integer.parseInt(local_auth_id);
        int i = lus.delete(lai);
        return String.valueOf(i);
    }
}
