package scut.melody.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scut.melody.dao.UserInfoMapper;
import scut.melody.entity.UserInfo;
import scut.melody.service.service.UserInfoService;
import scut.melody.state.State;
import scut.melody.state.UserState;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserInfoServiceImpl implements UserInfoService {
    @Autowired
    private UserInfoMapper uim;
    State state = new State();

    @Override
    public String queryallwithpage(int page,int rows) {
        int i = uim.count();
        int j = 0;
        List<UserInfo> list = new ArrayList<>();
        list = uim.selectallwithpage((page-1)*rows,rows);
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(UserInfo u:list){
                str.append("{\"userInfoId\":\"");
                str.append(u.getUserInfoId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"CreateTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"nickname\":\"");
                str.append(u.getNickname());

                str.append("\",\"icon\":\"");
                str.append(u.getIcon());

                str.append("\",\"gender\":\"");
                str.append(u.getGender());

                str.append("\",\"province\":\"");
                str.append(u.getProvince());

                str.append("\",\"city\":\"");
                str.append(u.getCity());

                str.append("\",\"country\":\"");
                str.append(u.getCountry());

                str.append("\",\"birthday\":\"");
                str.append(u.getBirthday());

                str.append("\",\"email\":\"");
                str.append(u.getEmail());

                str.append("\",\"telephone\":\"");
                str.append(u.getTelephone());

                str.append("\",\"introduce\":\"");
                str.append(u.getIntroduce());

                str.append("\",\"activeCode\":\"");
                str.append(u.getActiveCode());

                str.append("\",\"state\":\"");
                str.append(state.map.get(String.valueOf(u.getState())));

                str.append("\",\"role\":\"");
                str.append(u.getRole());

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
            return  null;
        }
        return b;
    }

    @Override
    public String querywithidpage(int idstart, int idend, int page, int rows) {
        int i = uim.selectidno(idstart,idend);
        int j = 0;
        List<UserInfo> list = new ArrayList<>();
        list = uim.querywithidpage(idstart,idend,(page-1)*rows,rows);
        String b = null;
        StringBuilder str = new StringBuilder();
        try{
            str.append("{\"total\":");
            str.append(i);
            str.append(",\"rows\":[");
            for(UserInfo u:list){
                str.append("{\"userInfoId\":\"");
                str.append(u.getUserInfoId());

                str.append("\",\"userId\":\"");
                str.append(u.getUserId());

                str.append("\",\"CreateTime\":\"");
                str.append(u.getCreateTime());

                str.append("\",\"nickname\":\"");
                str.append(u.getNickname());

                str.append("\",\"icon\":\"");
                str.append(u.getIcon());

                str.append("\",\"gender\":\"");
                str.append(u.getGender());

                str.append("\",\"province\":\"");
                str.append(u.getProvince());

                str.append("\",\"city\":\"");
                str.append(u.getCity());

                str.append("\",\"country\":\"");
                str.append(u.getCountry());

                str.append("\",\"birthday\":\"");
                str.append(u.getBirthday());

                str.append("\",\"email\":\"");
                str.append(u.getEmail());

                str.append("\",\"telephone\":\"");
                str.append(u.getTelephone());

                str.append("\",\"introduce\":\"");
                str.append(u.getIntroduce());

                str.append("\",\"activeCode\":\"");
                str.append(u.getActiveCode());

                str.append("\",\"state\":\"");
                str.append(state.map.get(String.valueOf(u.getState())));

                str.append("\",\"role\":\"");
                str.append(u.getRole());

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
            return  null;
        }
        return b;
    }
}
