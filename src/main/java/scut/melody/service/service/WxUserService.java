package scut.melody.service.service;

import java.util.Date;

public interface WxUserService {
    String querywithpage(int start, int end, int pstart, int psize);
    String queryallwithpage(int start, int end);
    int delete(int id);

}
