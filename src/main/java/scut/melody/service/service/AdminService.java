package scut.melody.service.service;


import scut.melody.entity.Adm;

public interface AdminService {
    Adm selectbyusername(String name);
    int updatepassword(Adm adm);
}
