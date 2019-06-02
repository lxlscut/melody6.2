package scut.melody.service.service;

import scut.melody.entity.UserInfo;

import java.util.List;

public interface UserInfoService {
    /**
     * 分页查询所有，按时间排序
     * @param page
     * @param rows
     * @return
     */
    String queryallwithpage(int page,int rows);

    /**
     * 分页根据id查询
     * @param idstart
     * @param idend
     * @param page
     * @param rows
     * @return
     */
    String querywithidpage(int idstart,int idend,int page,int rows);


}
