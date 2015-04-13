package fr.noixcoop.nuceus.presentation;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import fr.noixcoop.nuceus.logiqueMetier.MetierVarietes;
import fr.noixcoop.nuceus.metier.Variete;

/**
 * Servlet implementation class ControleurNuceus
 */
public class ControleurNuceus extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private MetierVarietes metierVarietes ;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ControleurNuceus() {
		super();
		// TODO Auto-generated constructor stub
	}

	public void init() throws ServletException {
		metierVarietes = new MetierVarietes();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String action = request.getParameter("action");
		if(action == null)
			action ="visualiser";

		if(action.equals("visualiser") || action.equals("annulerAjout")) {
			request.setAttribute("varietes",metierVarietes.consulter());
			getServletContext().getRequestDispatcher("/WEB-INF/vues/vueListe.jsp").forward(request,response);
		}
		else if(action.equals("renseignerAjout")) 
			getServletContext().getRequestDispatcher("/WEB-INF/vues/vueFormulaireAjout.jsp").forward(request,response);

		else if (action.equals("ajouter")) {
			String libelle = request.getParameter("libelle");
			String aoc = request.getParameter("aoc");
			boolean aocObtenu = false;
			if(aoc == null ){
				aoc = "non";
				aocObtenu = false;
			}
			else {
				aoc="oui";
				aocObtenu =true;
			}
			boolean ajouterOk = metierVarietes.ajouter(new Variete(libelle,aocObtenu));
			System.out.println(ajouterOk);
			if(ajouterOk) {
				request.setAttribute("libelle",libelle);
				request.setAttribute("aoc",aoc);
				System.out.println("ADA"+metierVarietes.consulter().toString());
				getServletContext().getRequestDispatcher("/WEB-INF/vues/vueResultatAjout.jsp").forward(request,response);
			}
			else 
				getServletContext().getRequestDispatcher("/WEB-INF/vues/vueResultatAjout.jsp").forward(request,response);

		}
		else if(action.equals("Supprimer")) {
			String libelle = request.getParameter("variete");
			boolean sup = metierVarietes.supprimer(libelle);
			if(sup){
				request.setAttribute("varietes",metierVarietes.consulter());
				getServletContext().getRequestDispatcher("/WEB-INF/vues/vueListe.jsp").forward(request,response);
			}
		}
		else if(action.equals("formulaireModifier")) {
			String libelle = request.getParameter("variete");
			Variete variete = metierVarietes.consulter(libelle);
			request.setAttribute("libelle",libelle);
			request.setAttribute("aoc",variete.isAoc());
			getServletContext().getRequestDispatcher("/WEB-INF/vues/vueModifier.jsp").forward(request,response);
		}
		else if(action.equals("modifier")) {
			String libelle = request.getParameter("libelle");
			Variete variete = metierVarietes.consulter(libelle);
			if(request.getParameter("isAoc") != null){
				variete.setAoc(true);
			}
			else {
				variete.setAoc(false);
			}
			boolean modifier = metierVarietes.modifier(variete);
			if(modifier){
				request.setAttribute("varietes",metierVarietes.consulter());
				getServletContext().getRequestDispatcher("/WEB-INF/vues/vueListe.jsp").forward(request,response);
			}
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
