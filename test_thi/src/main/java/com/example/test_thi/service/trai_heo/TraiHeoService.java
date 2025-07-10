package com.example.test_thi.service.trai_heo;

import com.example.test_thi.dto.TraiHeoDtoResponse;
import com.example.test_thi.model.TraiHeo;
import com.example.test_thi.repository.trai_heo.ITraiHeoRepository;
import com.example.test_thi.repository.trai_heo.TraiHeoRepository;

import java.util.List;

public class TraiHeoService implements ITraiHeoService {
    private ITraiHeoRepository traiHeoRepository = new TraiHeoRepository();

    @Override
    public List<TraiHeoDtoResponse> findAll() {
        return traiHeoRepository.findAll();
    }

    @Override
    public boolean deleteById(int id) {
        return traiHeoRepository.deleteById(id);
    }

    @Override
    public TraiHeo findById(int id) {
        return traiHeoRepository.findById(id);
    }

    @Override
    public boolean update(TraiHeo traiHeo) {
        return traiHeoRepository.update(traiHeo);
    }

    @Override
    public List<TraiHeoDtoResponse> searchByName(String searchName) {
        return traiHeoRepository.searchByName(searchName);
    }

    @Override
    public List<TraiHeoDtoResponse> searchByXuatXu(String searchXuatXu) {
        return traiHeoRepository.searchByXuatXu(searchXuatXu);
    }

    @Override
    public List<TraiHeoDtoResponse> findTopHeoXuatChuongByTrongLuong(int limit) {
        return traiHeoRepository.findTopHeoXuatChuongByTrongLuong(limit);
    }
}
