package fr.noixcoop.nuceus.logiqueMetier;

import java.util.List;

import fr.noixcoop.nuceus.dao.DaoVarietes;
import fr.noixcoop.nuceus.metier.Variete;

public class MetierVarietes implements IMetierVarietes{
	private DaoVarietes daoVariete = new DaoVarietes();

	/** Consulter la liste des variétés
	 * 
	 * @return Liste des variétés
	 */
	public List<Variete> consulter(){
		return this.daoVariete.consulter() ;
	}

	/** Consulter une variété
	 * 
	 * @param libelle Nom de la variété 
	 * @return Variété
	 */
	public Variete consulter(String libelle){
		return this.daoVariete.consulter(libelle);
	}

	/** Ajouter une variété
	 * 
	 * @param variete Variété à ajouter
	 * @return true si l'ajout s'est déroulé avec succès
	 */
	public boolean ajouter(Variete variete){
		return this.daoVariete.ajouter(variete);
	}

	/** Modifier les caractéristiques d'une variété
	 * 
	 * @param variete Variété à modifier
	 * @return true si la modification s'est déroulée avec succès
	 */
	public boolean modifier(Variete variete){
		return this.daoVariete.modifier(variete);
	}

	/** Supprimer une variété de la liste
	 * 
	 * @param libelle Nom de la variété
	 * @return true si la suppression s'est déroulée avec succès
	 */
	public boolean supprimer(String libelle){
		return this.daoVariete.supprimer(libelle);
	}

	/** Supprimer une variété de la liste
	 * 
	 * @param variete Variété à supprimer
	 * @return true si la suppression s'est déroulée avec succès
	 */
	public boolean supprimer(Variete variete){
		return supprimer(variete.getLibelle()) ;
	}

}
