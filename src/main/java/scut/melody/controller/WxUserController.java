package scut.melody.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import scut.melody.dao.WechatAuthMapper;
import scut.melody.entity.WechatAuth;
import scut.melody.service.service.WxUserService;

import java.util.Date;

@Controller
@RequestMapping("/wxuser")
public class WxUserController {
    @Autowired
    WxUserService ws;
    @Autowired
    private WechatAuthMapper wam;
    @RequestMapping("queryall")
    @ResponseBody
    public String queryall(@RequestParam(name = "page",required = false) int page,
                           @RequestParam(name = "rows",required = false)int rows,
                           @RequestParam(name = "start",required = false)Integer start,
                           @RequestParam(name = "end",required = false)Integer end){
        String str = null;
        if(start!=null||end!=null) {
            str = ws.querywithpage(start,end,rows,page);
            System.out.println(str);
        }else {
            str = ws.queryallwithpage(rows*(page-1),rows*page);
        }
        return str;
    }
    @RequestMapping("/update")
    @ResponseBody
    public String update(@RequestParam(name = "wechatAuthId",required = false) String wechatAuthId,
                         @RequestParam(name = "userId",required = false)String userId,
                         @RequestParam(name = "openid",required = false)String openid,
                         @RequestParam(name = "createTime",required = false) String createTime,
                         @RequestParam(name = "updateTime",required = false)String updateTime
    ){
        Integer wechatauthId = Integer.parseInt(wechatAuthId);
        Integer userid = Integer.parseInt(userId);
        Date create = new Date(createTime);
        Date updatetime = new Date(updateTime);
        WechatAuth wechatAuth = new WechatAuth();
        wechatAuth.setCreateTime(create);
        wechatAuth.setUpdateTime(updatetime);
        wechatAuth.setUserId(userId);
        wechatAuth.setOpenid(openid);
        wechatAuth.setWechatAuthId(wechatauthId);
        int i = wam.updateByPrimaryKeySelective(wechatAuth);
        return String.valueOf(i);
    }
    @RequestMapping("/delete")
    @ResponseBody
    public String delete(@RequestParam(name = "wechatAuthId")String wechatAuthId){
        int lai = Integer.parseInt(wechatAuthId);
        int i = ws.delete(lai);
        return String.valueOf(i);
    }

}
