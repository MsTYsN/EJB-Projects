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

import ma.entites.Createur;
import ma.metier.CreateurEJBImpl;
import ma.metier.CreateurLocal;

/**
 * Servlet implementation class CreateurController
 */
@WebServlet("/CreateurController")
public class CreateurController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private CreateurLocal service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CreateurController() {
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
			String prenom = request.getParameter("prenom");
			String description = request.getParameter("description");
			String dateDebut = request.getParameter("debut").replace("-", "/");
			String dateFin = request.getParameter("fin").replace("-", "/");
			service.addCreateur(new Createur(nom, prenom, description, new Date(dateDebut), new Date(dateFin)));
		}
		if(request.getParameter("op").equals("update")) {
			int id = Integer.parseInt(request.getParameter("id"));
			String nom = request.getParameter("nom");
			String prenom = request.getParameter("prenom");
			String description = request.getParameter("description");
			String dateDebut = request.getParameter("debut").replace("-", "/");
			String dateFin = request.getParameter("fin").replace("-", "/");
			service.updateCreateur(id, new Createur(nom, prenom, description, new Date(dateDebut), new Date(dateFin)));
		}
		if(request.getParameter("op").equals("delete")) {
			int id = Integer.parseInt(request.getParameter("id"));
			service.deleteCreateur(id);
		}
		if(request.getParameter("op").equals("load")) {
			response.setContentType("application/json");
			List<Createur> createurs= service.listCreateurs();
			Gson json = new Gson();
			response.getWriter().write(json.toJson(createurs));
		}
		if(request.getParameter("op").equals("search")) {
			response.setContentType("application/json");
			String nom = request.getParameter("nom");
			List<Createur> createurs= service.listCreateursParNom(nom);
			Gson json = new Gson();
			response.getWriter().write(json.toJson(createurs));
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
