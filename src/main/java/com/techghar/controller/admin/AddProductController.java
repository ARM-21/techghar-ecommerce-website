package com.techghar.controller.admin;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.techghar.DAO.ProductDAO;
import com.techghar.model.Product;
import com.techghar.utility.ErrorHandlerUtilty;
import com.techghar.model.Category;
import com.techghar.model.Brand;

/**
 * Servlet implementation class AddProductController
 */


@WebServlet(asyncSupported = true, urlPatterns = { "/add-product", "/save-product" })
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)

public class AddProductController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public AddProductController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("addNewProduct", true);

		List<Brand> brands;
		try {
			ProductDAO dao = new ProductDAO();
			ArrayList<Category> categories = dao.getAllCategories();
			brands = dao.getAllBrands();
			request.setAttribute("categories", categories);
			request.setAttribute("brands", brands);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./products.jsp");
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);
		} catch (SQLException | ClassNotFoundException e) {
			// TODO Auto-generated catch block
			ErrorHandlerUtilty.handleErrorAdmin(request, response,
					"Oops! Error Occured While Fetching Product related data");
			e.printStackTrace();
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		try {
//			ProductDAO dao = new ProductDAO();
		
			System.out.println(request.getParameter("brandId"));
			ProductDAO dao = new ProductDAO();
			Product product = new Product();
			Part image = request.getPart("imageFile");
			if(image == null) {
				response.sendError(401, "No Image Uploaded");
				
			}
			
			product.setName(request.getParameter("name"));
			product.setPrice(Double.parseDouble(request.getParameter("price")));
			product.setBrand(Integer.parseInt(request.getParameter("brandId")));
			product.setCategory(Integer.parseInt(request.getParameter("categoryId")));
			product.setDescription(request.getParameter("description"));
			product.setImageURL(request.getParameter("imageUrl"));
			product.setStock(Integer.parseInt(request.getParameter("stock")));
			product.setRating(Integer.parseInt(request.getParameter("rating")));

			// for image uploading

			String pathFile = fileWriter(request, response, image);
			System.out.println(pathFile);

			if (pathFile == null) {
				request.setAttribute("message", "❌ Failed to Upload the product image.");
				request.setAttribute("messageType", "error");
				return;

			}
			product.setImageURL(pathFile);		
			Boolean productAddedSuccessfully = dao.addProduct(product);
			
			System.out.println("product Added" + productAddedSuccessfully);
			if (productAddedSuccessfully) {
				request.setAttribute("message", "✅ Product added successfully!");
				request.setAttribute("messageType", "success");
				
			} else {
				request.setAttribute("message", "❌ Failed to add product.");
				request.setAttribute("messageType", "error");
				return;
			}
			
			List<Brand> brands;
		
			ArrayList<Category> categories = dao.getAllCategories();
			brands = dao.getAllBrands();
			request.setAttribute("categories", categories);
			request.setAttribute("brands", brands);
			request.setAttribute("activePage", "admin-products");
			request.setAttribute("pageContent", "./products.jsp");
			request.setAttribute("addNewProduct", true);
//			request.getRequestDispatcher("add-product.jsp").forward(request, response);
			request.getRequestDispatcher("WEB-INF/pages/admin/dashboard.jsp").forward(request, response);

		} catch (SQLException | ClassNotFoundException e) {
			ErrorHandlerUtilty.handleErrorAdmin(request, response, "Error Occured While Updating the Products");
			e.printStackTrace();
		}

	}

	protected String fileWriter(HttpServletRequest request, HttpServletResponse response, Part image) {

		try {
			
			System.out.println(image);
			String fileName = image.getSubmittedFileName();
			System.out.println(fileName);

			String storePath = request.getServletContext().getRealPath("");
			String filePath = "assets" + File.separator + "images" + File.separator + fileName;

			System.out.println("storedpath" +storePath);
			System.out.println("filepath"+filePath);
			image.write(storePath + File.separator + filePath);

			System.out.println("File uploaded");
			return  filePath;
			// TODO: Write respective DAO process to store all attributes and filePath to
			// database

		} catch (Exception e) {
			System.out.println("File not uploaded");
			e.printStackTrace();
			return null;
		}
	}

}
