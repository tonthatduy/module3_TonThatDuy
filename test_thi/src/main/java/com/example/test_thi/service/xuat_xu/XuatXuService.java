package com.example.test_thi.service.xuat_xu;

import com.example.test_thi.model.XuatXu;
import com.example.test_thi.repository.xuat_xu.IXuatXuRepository;
import com.example.test_thi.repository.xuat_xu.XuatXuRepository;

import java.util.List;

public class XuatXuService implements IXuatXuService {
    private IXuatXuRepository xuatXuRepository = new XuatXuRepository();

    @Override
    public List<XuatXu> findAll() {
        return xuatXuRepository.findAll();
    }
}
