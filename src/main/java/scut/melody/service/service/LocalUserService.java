package scut.melody.service.service;

import java.util.Date;

public interface LocalUserService {
  String querywithpage(int start, int end, int pstart, int psize);
  String queryallwithpage(int start, int end);
  int update(int user_id, String username, Date create_time, Date update_time, int local_auth_id);
  int delete(int id);
}
