package scut.melody.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scut.melody.dao.LocalAuthMapper;
import scut.melody.entity.LocalAuth;
import scut.melody.service.service.LocalUserService;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class LocalUserServiceImpl implements LocalUserService {
    @Autowired
    LocalAuthMapper lm;
    @Override
    public String querywithpage(int start, int end, int row, int page) {
        int i = lm.selectrangepageno(start,end);
        List<LocalAuth> list = lm.localselectbyidpage(start,end,row*(page-1),row);
        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(LocalAuth u:list){
                str.append("{\"localAuthId\":\"");
                str.append(u.getLocalAuthId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"username\":\"");
                str.append(u.getUsername());

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
        int i = lm.count();

        List<LocalAuth> list = new ArrayList<>();

        list = lm.queryallwithpage(start,end);

        int j = 0;
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(LocalAuth u:list){
                str.append("{\"localAuthId\":\"");
                str.append(u.getLocalAuthId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"username\":\"");
                str.append(u.getUsername());

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
    public int update(int user_id, String username, Date create_time, Date update_time, int local_auth_id) {
        int i =lm.update(user_id,username,create_time,update_time,local_auth_id);
        return i;
    }

    @Override
    public int delete(int id) {
        int i = lm.deleteByPrimaryKey(id);
        return i;
    }
}
