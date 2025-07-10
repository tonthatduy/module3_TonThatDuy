package com.example.test_thi.repository.xuat_xu;

import com.example.test_thi.model.XuatXu;
import com.example.test_thi.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class XuatXuRepository implements IXuatXuRepository {
    private final String SELECT_ALL = "select * from xuat_xu";


    @Override
    public List<XuatXu> findAll() {
        List<XuatXu> xuatXuList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idXuatXu = resultSet.getInt("id_xuat_xu");
                String tenXuatXu = resultSet.getString("ten_xuat_xu");
                xuatXuList.add(new XuatXu(idXuatXu, tenXuatXu));
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return xuatXuList;
    }
}
