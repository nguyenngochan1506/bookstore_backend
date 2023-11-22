package vn.titv.webbansach_backend.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import vn.titv.webbansach_backend.dao.TheLoaiRepository;
@Service
public class TheLoaiServiceImpl implements  TheLoaiService{
    private TheLoaiRepository theLoaiRepository;
    @Autowired
    public TheLoaiServiceImpl(TheLoaiRepository theLoaiRepository){
        this.theLoaiRepository = theLoaiRepository;
    }
    @Override
    public int getMaTheLoaiByName(String tenTheLoai) {
        Integer maTheLoai = theLoaiRepository.getMaTheLoaiByName(tenTheLoai);
        if(maTheLoai == null){
            return -1;
        }
        return maTheLoai;
    }
}
