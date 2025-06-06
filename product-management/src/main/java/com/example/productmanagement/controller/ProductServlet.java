package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDtoResponse;
import com.example.productmanagement.entity.Category;
import com.example.productmanagement.entity.Product;
import com.example.productmanagement.service.CategoryService;
import com.example.productmanagement.service.ProductService;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet(name = "ProductServlet", urlPatterns = "/products")
public class ProductServlet extends HttpServlet {
    private final ProductService productService = new ProductService();
    private final CategoryService categoryService = new CategoryService();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            action = "list";
        }
        switch (action) {
            case "create":
                showFromAdd(req, resp);
                break;
            case "edit":
                showEditForm(req, resp);
                break;
            case "delete":
                break;
            case "view":
                viewProduct(req, resp);
                break;
            case "search":
                searchProduct(req, resp);
                break;
            case "list":
            default:
                listProducts(req, resp);
                break;
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String searchName = req.getParameter("searchName");
        int idCategory= Integer.parseInt(req.getParameter("id_category"));
        req.setAttribute("searchName",searchName);
        req.setAttribute("id_category",idCategory);
        List<ProductDtoResponse> resultList = productService.searchByName(searchName, idCategory);
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.setAttribute("products", resultList);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);

    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDtoResponse> productDtoResponses = productService.findAll();
        List<Category> categories = categoryService.findAll();
        req.setAttribute("categories", categories);
        req.setAttribute("products", productDtoResponses);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);
//        int page = req.getParameter("page") == null ? 1 : Integer.parseInt(req.getParameter("page"));
//        int limit = 5;
//        int offset = (page - 1) * limit;
//
//        // Gọi service phân trang
//        List<ProductDtoResponse> productDtoResponses = productService.findPaginated(limit, offset);
//        int total = productService.countTotalProducts(); // Đếm tổng số sản phẩm
//        int totalPage = (int) Math.ceil((double) total / limit);
//
//        req.setAttribute("products", productDtoResponses);
//        req.setAttribute("totalPage", totalPage);
//        req.setAttribute("currentPage", page);
//
//        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
//        dispatcher.forward(req, resp);
    }


    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.trim().isEmpty()) {
            resp.sendRedirect("/products?mess=Thiếu ID sản phẩm để chỉnh sửa");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            Product product = productService.findById(id);
            if (product == null) {
                resp.sendRedirect("/products?mess=Sản phẩm không tồn tại");
            } else {
                req.setAttribute("product", product);
                req.setAttribute("categoryList", categoryService.findAll());
                req.getRequestDispatcher("product/edit.jsp").forward(req, resp);
            }
        } catch (NumberFormatException e) {
            resp.sendRedirect("/products?mess=ID sản phẩm không hợp lệ");
        }

    }

    private void showFromAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categories", categoryService.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/view.jsp");
        }
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
                createProduct(req, resp);
                break;
            case "update":
                updateProduct(req, resp);
                break;
            case "delete":
                deleteProduct(req, resp);
                break;
            default:
                listProducts(req, resp);
                break;

        }
    }

    private void createProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String publisher = req.getParameter("publisher");

        String idCategoryStr = req.getParameter("id_category");
        if (idCategoryStr == null || idCategoryStr.trim().isEmpty()) {
            req.setAttribute("message", "Vui lòng chọn thể loại.");
            req.setAttribute("categorys", categoryService.findAll());
            req.getRequestDispatcher("product/create.jsp").forward(req, resp);
            return;
        }

        int idCategory = Integer.parseInt(idCategoryStr);
        Product product = new Product(name, price, description, publisher, idCategory);
        boolean isAddSuccess = productService.add(product);
        String mess = isAddSuccess ? "created success" : "not created success";
        resp.sendRedirect("/products?mess=" + mess);
    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String publisher = req.getParameter("publisher");
        int idCategory = Integer.parseInt(req.getParameter("id_category"));
        Product product = new Product(id, name, price, description, publisher, idCategory);
        boolean isUpdateSuccess = productService.update(product);

        String mess = isUpdateSuccess ? "Update success" : "Not update success";
        resp.sendRedirect("/products?mess=" + mess);


    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        int id = Integer.parseInt(req.getParameter("deleteId"));
        boolean isDeleteSuccess = productService.deleteById(id);
        String mess = "Deleted success";
        if (!isDeleteSuccess) {
            mess = " Not deleted Success";
        }
        resp.sendRedirect("/products?mess=" + mess);
    }
}
