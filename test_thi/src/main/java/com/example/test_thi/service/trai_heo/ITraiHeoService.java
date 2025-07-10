package com.example.test_thi.service.trai_heo;

import com.example.test_thi.dto.TraiHeoDtoResponse;
import com.example.test_thi.model.TraiHeo;

import java.util.List;

public interface ITraiHeoService {
    List<TraiHeoDtoResponse> findAll();
    boolean deleteById(int id);
    TraiHeo findById(int id);
    boolean update(TraiHeo traiHeo);
    List<TraiHeoDtoResponse> searchByName(String searchName);
    List<TraiHeoDtoResponse> searchByXuatXu(String searchXuatXu);

    List<TraiHeoDtoResponse> findTopHeoXuatChuongByTrongLuong(int limit);
}
