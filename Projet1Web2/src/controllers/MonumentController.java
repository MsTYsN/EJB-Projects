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

import ma.entites.Monument;
import ma.metier.CreateurLocal;
import ma.metier.MonumentEJBImpl;
import ma.metier.MonumentLocal;
import ma.metier.VilleLocal;

/**
 * Servlet implementation class MonumentController
 */
@WebServlet("/MonumentController")
public class MonumentController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private MonumentLocal service;
	@EJB
	private VilleLocal serviceVille;
	@EJB
	private CreateurLocal serviceCreateur;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public MonumentController() {
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
			String adresse = request.getParameter("adresse");
			String description = request.getParameter("description");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			int ville = Integer.parseInt(request.getParameter("ville"));
			int createur = Integer.parseInt(request.getParameter("createur"));
			if (ville == 0 && createur == 0) {
				service.addMonument(new Monument(nom, adresse, description, latitude, longitude));
			} else if (ville != 0 && createur == 0) {
				service.addMonument(
						new Monument(nom, adresse, description, latitude, longitude, serviceVille.getVille(ville)));
			} else if (ville == 0 && createur != 0) {
				service.addMonument(new Monument(nom, adresse, description, latitude, longitude,
						serviceCreateur.getCreateur(createur)));
			} else if (ville != 0 && createur != 0) {
				service.addMonument(new Monument(nom, adresse, description, latitude, longitude,
						serviceVille.getVille(ville), serviceCreateur.getCreateur(createur)));
			}
		}
		if (request.getParameter("op").equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String adresse = request.getParameter("adresse");
			String description = request.getParameter("description");
			double latitude = Double.parseDouble(request.getParameter("latitude"));
			double longitude = Double.parseDouble(request.getParameter("longitude"));
			int ville = Integer.parseInt(request.getParameter("ville"));
			int createur = Integer.parseInt(request.getParameter("createur"));
			if (ville == 0 && createur == 0) {
				service.updateMonument(id, new Monument(nom, adresse, description, latitude, longitude));
			} else if (ville != 0 && createur == 0) {
				service.updateMonument(id,
						new Monument(nom, adresse, description, latitude, longitude, serviceVille.getVille(ville)));
			} else if (ville == 0 && createur != 0) {
				service.updateMonument(id, new Monument(nom, adresse, description, latitude, longitude,
						serviceCreateur.getCreateur(createur)));
			} else if (ville != 0 && createur != 0) {
				service.updateMonument(id, new Monument(nom, adresse, description, latitude, longitude,
						serviceVille.getVille(ville), serviceCreateur.getCreateur(createur)));
			}
		}
		if (request.getParameter("op").equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			service.deleteMonument(id);
		}
		if (request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Monument> monuments = service.listMonuments();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(monuments));
		}
		if (request.getParameter("op").equals("search")) {
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
			List<Monument> monuments = service.listMonumentsParNom(nom);
			Gson json = new Gson();
			response.getWriter().write(json.toJson(monuments));
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
