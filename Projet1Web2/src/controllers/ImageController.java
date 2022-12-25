package controllers;

import java.io.File;
import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;

import ma.entites.Image;
import ma.metier.ImageLocal;
import ma.metier.ImageEJBImpl;
import ma.metier.MonumentLocal;

/**
 * Servlet implementation class ImageController
 */
@WebServlet("/ImageController")
public class ImageController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private ImageLocal service;
	@EJB
	private MonumentLocal serviceMonument;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op").equals("add")) {
			String url = request.getParameter("url");
			int monument = Integer.parseInt(request.getParameter("monument"));
			if (monument == 0) {
				service.addImage(new Image(url));
			} else {
				service.addImage(new Image(url, serviceMonument.getMonument(monument)));
			}
		}
		if (request.getParameter("op").equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String url = request.getParameter("url");
			int monument = Integer.parseInt(request.getParameter("monument"));
			if (monument == 0) {
				service.updateImage(id, new Image(url));
			} else {
				service.updateImage(id, new Image(url, serviceMonument.getMonument(monument)));
			}
		}
		if (request.getParameter("op").equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			service.deleteImage(id);
		}
		if (request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Image> images = service.listImages();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(images));
		}
		if (request.getParameter("op").equals("search")) {
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
			List<Image> images = service.listImagesParMonument(nom);
			Gson json = new Gson();
			response.getWriter().write(json.toJson(images));
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
