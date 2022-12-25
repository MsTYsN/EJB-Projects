package controllers;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import javax.ejb.EJB;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import ma.entites.Ville;
import ma.metier.VilleEJBImpl;
import ma.metier.VilleLocal;

/**
 * Servlet implementation class VilleController
 */
@WebServlet("/VilleController")
public class VilleController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private VilleLocal service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public VilleController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		System.out.println(request.getParameter("op"));
		if (request.getParameter("op").equals("add")) {
			String nom = request.getParameter("nom");
			service.addVille(new Ville(nom));
		}
		if(request.getParameter("op").equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			service.updateVille(id, new Ville(nom));
		}
		if(request.getParameter("op").equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			service.deleteVille(id);
		}
		if(request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Ville> villes= service.listVilles();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(villes));
		}
		if(request.getParameter("op").equals("search")) {
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
			List<Ville> villes= service.listVillesParNom(nom);
			Gson json = new Gson();
			response.getWriter().write(json.toJson(villes));
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
