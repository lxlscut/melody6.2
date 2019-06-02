package scut.melody.service.serviceimpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import scut.melody.dao.AdmMapper;
import scut.melody.entity.Adm;
import scut.melody.service.service.AdminService;

@Service
public class AdminImpl implements AdminService {
    @Autowired
    private AdmMapper am;
    @Override
    public Adm selectbyusername(String name) {
        Adm adm = am.selectbyusername(name);
        return adm;
    }

    @Override
    public int updatepassword(Adm adm) {
        int i =am.updateByPrimaryKey(adm);
        return 1;
    }
}
