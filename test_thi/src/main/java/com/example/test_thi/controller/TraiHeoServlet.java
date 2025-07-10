package com.example.test_thi.controller;

import com.example.test_thi.dto.TraiHeoDtoResponse;
import com.example.test_thi.model.TraiHeo;
import com.example.test_thi.model.XuatXu;
import com.example.test_thi.repository.xuat_xu.XuatXuRepository;
import com.example.test_thi.service.trai_heo.TraiHeoService;
import com.example.test_thi.service.xuat_xu.XuatXuService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "TraiHeoServlet", urlPatterns = "/traiheo")
public class TraiHeoServlet extends HttpServlet {

    private final static TraiHeoService traiHeoService = new TraiHeoService();
    private final static XuatXuService xuatXuService = new XuatXuService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                break;
            case "update":
                showEditForm(req, resp);
                break;
            case "delete":
                break;
            case "search":
                searchByNameAndXuatXu(req, resp);
            case "top":
                searchTopHeo(req,resp);
            case "list":
            default:
                listTraiHeo(req, resp);
                break;
        }
    }

    private void searchTopHeo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int limit = Integer.parseInt(req.getParameter("limit")); // lấy từ select
        List<TraiHeoDtoResponse> topHeo = traiHeoService.findTopHeoXuatChuongByTrongLuong(limit);
        req.setAttribute("traiHeoList", topHeo);
        req.setAttribute("xuatXuList", xuatXuService.findAll());
        req.getRequestDispatcher("trai_heo/list.jsp").forward(req, resp);
    }

    private void searchByNameAndXuatXu(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        String searchXuatXu = req.getParameter("searchXuatXu");
        List<TraiHeoDtoResponse> searchResult;
        if ((searchName == null || searchName.trim().isEmpty()) && (searchXuatXu == null || searchXuatXu.trim().isEmpty())) {
            searchResult = traiHeoService.findAll();
        } else if ((searchName != null && !searchName.trim().isEmpty()) && (searchXuatXu == null || searchXuatXu.trim().isEmpty())) {
            searchResult = traiHeoService.searchByName(searchName);
        } else {
            searchResult = traiHeoService.searchByXuatXu(searchXuatXu);
        }
//        if ((searchName == null || searchName.trim().isEmpty()) && (searchXuatXu != null && !searchXuatXu.trim().isEmpty())) {
        List<XuatXu> xuatXuList = xuatXuService.findAll();
        req.setAttribute("xuatXuList", xuatXuList);
        req.setAttribute("traiHeoList", searchResult);
        RequestDispatcher dispatcher = req.getRequestDispatcher("trai_heo/list.jsp");
        dispatcher.forward(req, resp);
//        } else {
//            searchResults = studentService.searchByNameAndClass(searchName, searchClass);
//        }
    }

    private void listTraiHeo(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<TraiHeoDtoResponse> traiHeoDtoResponseList = traiHeoService.findAll();
        List<XuatXu> xuatXuList = xuatXuService.findAll();
        req.setAttribute("traiHeoList", traiHeoDtoResponseList);
        req.setAttribute("xuatXuList", xuatXuList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("trai_heo/list.jsp");
        dispatcher.forward(req, resp);


    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "";
        }
        switch (action) {
            case "create":
                createHeo(req, resp);
                break;
            case "update":
                updateHeo(req, resp);
                break;
            case "delete":
                deleteHeo(req, resp);
                break;
            default:
                listTraiHeo(req, resp);
                break;
        }

    }

    private void createHeo(HttpServletRequest req, HttpServletResponse resp) {
    }

    private void updateHeo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String maSoHeo = req.getParameter("ma_so_heo");
        String ngayNhapChuong = req.getParameter("ngay_nhap_chuong");
        double trongLuongNhapChuong = Double.parseDouble(req.getParameter("trong_luong_nhap_chuong"));
        String ngayXuatChuong = req.getParameter("ngay_xuat_chuong");
        double trongLuongXuatChuonng = Double.parseDouble(req.getParameter("trong_luong_xuat_chuong"));
        int idXuatXu = Integer.parseInt(req.getParameter("id_xuat_xu"));
        TraiHeo traiHeo = new TraiHeo(id, maSoHeo, ngayNhapChuong, trongLuongNhapChuong, ngayXuatChuong, trongLuongXuatChuonng, idXuatXu);
        boolean isUpdateSuccess = traiHeoService.update(traiHeo);

        String mess = isUpdateSuccess ? "Update success" : "Not update success";
        resp.sendRedirect("/traiheo?mess=" + mess);

    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect("/traiheo?mess=Thiếu ID Heo để chỉnh sửa");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            TraiHeo traiHeo = traiHeoService.findById(id);
            if (traiHeo == null) {
                resp.sendRedirect("/traiheo?mess=Sản phẩm không tồn tại");
            } else {
                req.setAttribute("traiHeoList", traiHeo);
                req.setAttribute("xuatXuList", xuatXuService.findAll());
                req.getRequestDispatcher("trai_heo/edit.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/traiheo?mess=ID sản phẩm không hợp lệ");
        }


    }

    private void deleteHeo(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("deleteId"));
        boolean isDeleteSuccess = traiHeoService.deleteById(id);
        String mess = "Delete success";
        if (!isDeleteSuccess) {
            mess = "Not deleted success";
        }
        resp.sendRedirect("/traiheo?mess" + mess);

    }
}
