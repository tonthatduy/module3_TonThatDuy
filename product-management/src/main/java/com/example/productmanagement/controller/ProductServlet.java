package com.example.productmanagement.controller;

import com.example.productmanagement.dto.ProductDtoResponse;
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
                showDeleteForm(req, resp);
                break;
            case "view":
                viewProduct(req, resp);
                break;
            case "search":
                searchProduct(req, resp);
            case "list":
            default:
                listProducts(req, resp);
                break;
        }
    }

    private void searchProduct(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String query = req.getParameter("query");
        List<Product> products = productService.searchByName(query);
        req.setAttribute("products", products);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);

    }

    private void listProducts(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        List<ProductDtoResponse> productDtoResponses = productService.findAll();
        req.setAttribute("products", productDtoResponses);
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/list.jsp");
        dispatcher.forward(req, resp);
    }

    private void showDeleteForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/delete.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void showEditForm(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void showFromAdd(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("categorys", categoryService.findAll());
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        dispatcher.forward(req, resp);
    }

    private void viewProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            req.setAttribute("product", product);
            dispatcher = req.getRequestDispatcher("product/view.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
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
            case "edit":
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
        int idCategory =Integer.parseInt(req.getParameter("idCategory"));
        Product product = new Product( name, price, description, publisher, idCategory);
        boolean isAddSuccess  = productService.add(product);
        String mess = "created success";
        if (!isAddSuccess){
            mess = "not created success";
        }
        RequestDispatcher dispatcher = req.getRequestDispatcher("product/create.jsp");
        req.setAttribute("message", "New product was created");
        dispatcher.forward(req, resp);

    }

    private void updateProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        String name = req.getParameter("name");
        double price = Double.parseDouble(req.getParameter("price"));
        String description = req.getParameter("description");
        String publisher = req.getParameter("publisher");
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            product.setName(name);
            product.setPrice(price);
            product.setDescription(description);
            product.setPublisher(publisher);
            productService.update(id, product);
            req.setAttribute("product", product);
            req.setAttribute("message", "Product information was updated");
            dispatcher = req.getRequestDispatcher("product/edit.jsp");
        }
        try {
            dispatcher.forward(req, resp);
        } catch (ServletException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void deleteProduct(HttpServletRequest req, HttpServletResponse resp) {
        int id = Integer.parseInt(req.getParameter("id"));
        Product product = productService.findById(id);
        RequestDispatcher dispatcher;
        if (product == null) {
            dispatcher = req.getRequestDispatcher("error-404.jsp");
        } else {
            productService.remove(id);
            try {
                resp.sendRedirect("/products");
            } catch (IOException e) {
                e.printStackTrace();
            }
        }


    }
}
