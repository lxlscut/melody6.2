package scut.melody.service.serviceimpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scut.melody.dao.WechatAuthMapper;
import scut.melody.entity.WechatAuth;
import scut.melody.service.service.WxUserService;

import java.util.Date;
import java.util.List;

@Service
public class WxUserServiceImpl implements WxUserService {
    @Autowired
    private WechatAuthMapper wm;
    @Override
    public String querywithpage(int start, int end, int rows, int page) {
        int i = wm.selectrangepageno(start,end);
        List<WechatAuth> list = wm.quereywithpage(start,end,rows*(page-1),rows*page);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(WechatAuth u:list){
                str.append("{\"wechatAuthId\":\"");
                str.append(u.getWechatAuthId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"openid\":\"");
                str.append(u.getOpenid());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());
                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        return b;
    }

    @Override
    public String queryallwithpage(int start, int end) {
        int i = wm.count();
        List<WechatAuth> list = wm.queryallwithpage(start,end);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(WechatAuth u:list){
                str.append("{\"wechatAuthId\":\"");
                str.append(u.getWechatAuthId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"openid\":\"");
                str.append(u.getOpenid());

                str.append("\",\"createTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"updateTime\":\"");
                str.append(u.getUpdateTime());
                j++;
                if(j == list.size()){
                    str.append("\"}");
                }else {
                    str.append("\"},");
                }
            }
            str.append("]}");
            b = ""+str;
        }catch (Exception e){
            return null;
        }
        return b;
    }


    @Override
    public int delete(int id) {
        int i = wm.deleteByPrimaryKey(id);
        return i;
    }
}
