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

import ma.entites.Compte;
import ma.metier.BanqueEJBImpl;
import ma.metier.BanqueLocal;

/**
 * Servlet implementation class CompteController
 */
@WebServlet("/CompteController")
public class CompteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@EJB
	private BanqueLocal service;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public CompteController() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getParameter("op") == null) {
			String solde = request.getParameter("solde");
			String dateCreate = request.getParameter("dateCreation").replace("-", "/");
			service.addCompte(new Compte(Double.parseDouble(solde), new Date(dateCreate)));
		}
		response.setContentType("application/json");
		List<Compte> comptes = service.listComptes();
		Gson json = new Gson();
		response.getWriter().write(json.toJson(comptes));

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
