package com.example.test_thi.repository.trai_heo;

import com.example.test_thi.dto.TraiHeoDtoResponse;
import com.example.test_thi.model.TraiHeo;
import com.example.test_thi.repository.BaseRepository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TraiHeoRepository implements ITraiHeoRepository {
    private final String SELECT_ALL_JOIN = "select th.*,xx.ten_xuat_xu from trai_heo th\n" +
            "join xuat_xu xx on xx.id_xuat_xu = th.id_xuat_xu";

    private final String DELETE_BY_ID = "delete from trai_heo where id =?";
    private final String FIND_BY_ID = "select * from trai_heo where id=?";
    private final String UPDATE_TRAI_HEO = "update trai_heo set ma_so_heo =? ,ngay_nhap_chuong=?," +
            "trong_luong_nhap_chuong=?,ngay_xuat_chuong=?,trong_luong_xuat_chuong=?,id_xuat_xu=? where id =?";
    private final String SEARCH_BY_NAME = "select th.*,xx.ten_xuat_xu from trai_heo th\n" +
            "join xuat_xu xx on xx.id_xuat_xu = th.id_xuat_xu\n" +
            "where th.ma_so_heo like ?";
    private final String SEAR_BY_XUAT_XU = "select th.*,xx.ten_xuat_xu from trai_heo th\n" +
            "join xuat_xu xx on xx.id_xuat_xu = th.id_xuat_xu\n" +
            "where xx.id_xuat_xu = ?";
    private final String SEARCH_BY_TOP="select th.*,xx.ten_xuat_xu from trai_heo th\n" +
            "join xuat_xu xx on xx.id_xuat_xu = th.id_xuat_xu\n" +
            "order by  th.trong_luong_xuat_chuong desc\n" +
            "limit ?";

    @Override
    public List<TraiHeoDtoResponse> findAll() {
        List<TraiHeoDtoResponse> traiHeoDtoResponseList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_JOIN)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idHeo = resultSet.getInt("id");
                String maSoHeo = resultSet.getString("ma_so_heo");
                String ngayNhapChuong = resultSet.getString("ngay_nhap_chuong");
                double trongLuongNhapChuong = resultSet.getDouble("trong_luong_nhap_chuong");
                String ngayXuatChuong = resultSet.getString("ngay_xuat_chuong");
                double trongLuongXuatChuong = resultSet.getDouble("trong_luong_xuat_chuong");
                String tenXuatXu = resultSet.getString("ten_xuat_xu");
                traiHeoDtoResponseList.add(new TraiHeoDtoResponse(idHeo, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuong, tenXuatXu));
            }
        } catch (SQLException e) {
            System.out.println("Không hiển thị được");
            throw new RuntimeException(e);
        }
        return traiHeoDtoResponseList;
    }

    @Override
    public boolean deleteById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(DELETE_BY_ID)) {
            preparedStatement.setInt(1, id);
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            System.out.println("Lỗi kết nối DB");
        }
        return false;
    }

    @Override
    public TraiHeo findById(int id) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(FIND_BY_ID);) {
            preparedStatement.setInt(1, id);
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                String maSoHeo = resultSet.getString("ma_so_heo");
                String ngayNhapChuong = resultSet.getString("ngay_nhap_chuong");
                double trongLuongNhapChuong = resultSet.getDouble("trong_luong_nhap_chuong");
                String ngayXuatChuong = resultSet.getString("ngay_xuat_chuong");
                double trongLuongXuatChuong = resultSet.getDouble("trong_luong_xuat_chuong");
                int idXuatXu = resultSet.getInt("id_xuat_xu");
                return new TraiHeo(id, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuong, idXuatXu);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    @Override
    public boolean update(TraiHeo traiHeo) {
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_TRAI_HEO);) {
            preparedStatement.setString(1, traiHeo.getMaSoHeo());
            preparedStatement.setString(2, traiHeo.getNgayNhapChuong());
            preparedStatement.setDouble(3, traiHeo.getTrongLuongNhapChuong());
            preparedStatement.setString(4, traiHeo.getNgayXuatChuong());
            preparedStatement.setDouble(5, traiHeo.getTrongLuongXuatChuong());
            preparedStatement.setInt(6, traiHeo.getIdXuatXu());
            preparedStatement.setInt(7, traiHeo.getId());
            int effectRow = preparedStatement.executeUpdate();
            return effectRow == 1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public List<TraiHeoDtoResponse> searchByName(String searchName) {
        List<TraiHeoDtoResponse> resultList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_NAME)) {
            preparedStatement.setString(1, "%" + searchName + "%");
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idHeo = resultSet.getInt("id");
                String maSoHeo = resultSet.getString("ma_so_heo");
                String ngayNhapChuong = resultSet.getString("ngay_nhap_chuong");
                double trongLuongNhapChuong = resultSet.getDouble("trong_luong_nhap_chuong");
                String ngayXuatChuong = resultSet.getString("ngay_xuat_chuong");
                double trongLuongXuatChuong = resultSet.getDouble("trong_luong_xuat_chuong");
                String tenXuatXu = resultSet.getString("ten_xuat_xu");
                resultList.add(new TraiHeoDtoResponse(idHeo, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuong, tenXuatXu));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi searchByName");
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<TraiHeoDtoResponse> searchByXuatXu(String searchXuatXu) {
        List<TraiHeoDtoResponse> resultList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEAR_BY_XUAT_XU)) {
            preparedStatement.setString(1, searchXuatXu);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idHeo = resultSet.getInt("id");
                String maSoHeo = resultSet.getString("ma_so_heo");
                String ngayNhapChuong = resultSet.getString("ngay_nhap_chuong");
                double trongLuongNhapChuong = resultSet.getDouble("trong_luong_nhap_chuong");
                String ngayXuatChuong = resultSet.getString("ngay_xuat_chuong");
                double trongLuongXuatChuong = resultSet.getDouble("trong_luong_xuat_chuong");
                String tenXuatXu = resultSet.getString("ten_xuat_xu");
                resultList.add(new TraiHeoDtoResponse(idHeo, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuong, tenXuatXu));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi searchByName");
            throw new RuntimeException(e);
        }
        return resultList;
    }

    @Override
    public List<TraiHeoDtoResponse> findTopHeoXuatChuongByTrongLuong(int limit) {
        List<TraiHeoDtoResponse> resultList = new ArrayList<>();
        try (Connection connection = BaseRepository.getConnectDB();
             PreparedStatement preparedStatement = connection.prepareStatement(SEARCH_BY_TOP)) {
            preparedStatement.setInt(1, limit);
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                int idHeo = resultSet.getInt("id");
                String maSoHeo = resultSet.getString("ma_so_heo");
                String ngayNhapChuong = resultSet.getString("ngay_nhap_chuong");
                double trongLuongNhapChuong = resultSet.getDouble("trong_luong_nhap_chuong");
                String ngayXuatChuong = resultSet.getString("ngay_xuat_chuong");
                double trongLuongXuatChuong = resultSet.getDouble("trong_luong_xuat_chuong");
                String tenXuatXu = resultSet.getString("ten_xuat_xu");
                resultList.add(new TraiHeoDtoResponse(idHeo, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuong, tenXuatXu));
            }
        } catch (SQLException e) {
            System.out.println("Lỗi khi gọi searchByName");
            throw new RuntimeException(e);
        }
        return resultList;
    }


}
