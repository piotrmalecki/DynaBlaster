package bomberihm;
import java.util.* ;
import bombernetwork.* ;

/**
 * Contient toutes les informations concernant les joueurs.
 */
public class BmbTableauJoueurs
{
  private static final String EXCEPTION_NONTROUVE = "Erreur : joueur non trouvé" ; // message envoyé si le joueur n'a pas été trouvé
  
  private Vector joueurs ; // joueurs connectés

  /**
   * Constructor de <b>BmbTableauJoueurs</b>.
   */
  public BmbTableauJoueurs()
  {
    super ();
    
    this.joueurs = new Vector () ;
  } /* BmbTableauJoueurs () */

  /**
   * Efface le contenu du tableau.
   */
  public void effacer ()
  {
    this.joueurs.clear () ;
  } /* effacer () */

  /**
   * Ajoute le joueur à la liste.
   */
  public void ajouter (BmbVueJoueur joueur)
  {
    this.joueurs.addElement (joueur) ;
  } /* ajouter () */

  /**
   * Retourne le nombre de joueur.
   * 
   * @return nombre de joueur
   */
  public int getNombreJoueur ()
  {
    return this.joueurs.size () ;
  } /* getNombreJoueur () */

  /**
   * Retourne le nombre de joueur vivant.
   * 
   * @return nombre de joueur vivant
   */
  public int getNombreVivant ()
  {
    int nbVivant = 0 ;
    for (int i = 0; i < this.joueurs.size (); i ++)
    {
      if (! ((BmbVueJoueur) this.joueurs.elementAt (i)).getMort ())
      {
        nbVivant ++ ;
      }
    }
    return nbVivant ;
  } /* getNombreVivant () */

  /**
   * Retourne le nombre de joueur prêt.
   * 
   * @return nombre de joueur prêt
   */
  public int getNombrePret ()
  {
    int nombrePret = 0 ;
    for (int i = 0; i < this.joueurs.size (); i ++)
    {
      if (((BmbVueJoueur) this.joueurs.elementAt (i)).getPret ())
      {
        nombrePret += 1 ;
      }
    }
    
    return nombrePret ;
  } /* getNombrePret () */

  /**
   * Retourne le joueur d'indice spécifié.
   * 
   * @param indice indice du joueur
   * @return joueur d'indice spécifié
   */
  public BmbVueJoueur getJoueur (int indice)
  {
    return (BmbVueJoueur) this.joueurs.elementAt (indice) ;
  } /* getJoueur () */

  /**
   * Retourne le joueur associé à la connexion spécifiée.
   * 
   * @param connexion connexion correspondant au joueur recherché
   * @return joueur associé à la connexion
   */
  public BmbVueJoueur getJoueur (BmbConnexion connexion) throws Exception
  {
    int i = 0 ;
    while ((i < this.joueurs.size ()))
    {
      if (((BmbVueJoueur) this.joueurs.elementAt (i)).getConnexion () == connexion)
      {
        return (BmbVueJoueur) this.joueurs.elementAt (i) ;
      }
      i ++ ;
    }
    throw new Exception (EXCEPTION_NONTROUVE) ;
  } /* getJoueur () */

  /**
   * Supprime le joueur associé à la connexion spécifiée.
   * 
   * @param connexion connexion correspondant au joueur recherché
   */
  public void supprimerJoueur (BmbConnexion connexion)
  {
    boolean trouve = false ;
    int i = 0 ;
    while ((i < this.joueurs.size ()) && (! trouve))
    {
      if (((BmbVueJoueur) this.joueurs.elementAt (i)).getConnexion () == connexion)
      {
        // supprimer le joueur correspondant
        this.joueurs.removeElementAt (i) ;
        trouve = true ;
      }
      i ++ ;
    }
  } /* supprimerJoueur () */
} /* BmbTableauJoueurs () */
