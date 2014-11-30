package bomberscene ;

import java.util.* ;

/**
 * <b>BmbElement</b> est la classe de base des différents éléments du jeu. Elle
 * offre plusieurs méthodes à spécialiser permettant d'afficher et de mettre à
 * jour.
 */
 
abstract public class BmbElement
{
  private static final String ELEMENT_INEXISTANT = "L'élément spécifié n'existe pas" ;

  private BmbElement parent ;  // parent de l'élément
  private Vector enfants ;     // éléments fils de l'élément actuel
  private String identifiant ; // identifiant de l'élément coté client
  
  /**
   * Constructeur de <b>BmbElement</b>.
   */
  public BmbElement (BmbElement parent)
  {
    // initialisation des variables membres
    this.parent  = parent ;
    this.enfants = new Vector () ;
    this.identifiant = Integer.toString (this.hashCode()) ;
  } /* BmbElement () */

  /**
   * Constructeur de <b>BmbElement</b>.
   */
  public BmbElement (BmbElement parent, String identifiant)
  {
    // initialisation des variables membres
    this.parent  = parent ;
    this.enfants = new Vector () ;
    this.identifiant = identifiant ;
  } /* BmbElement () */

  /**
   * Rafraichit l'élément. Met à jour les coordonnées, etc...
   */
  public void rafraichir () throws Exception
  {  
    // rafraichit les éléments enfants.
    Vector copie = (Vector) this.enfants.clone () ;
    for(int i = 0; i < copie.size (); i ++)
    {
      ((BmbElement) copie.elementAt (i)).rafraichir () ;
    }
  } /* rafraichir () */

  /**
   * Ajoute un élément enfant.
   *
   * @param element élément à ajouter
   */
  public void ajouterElement (BmbElement element)
  {
    this.enfants.addElement (element) ;   
   } /* ajouterElement () */ 

  /**
   * Retire un élément enfant.
   *
   * @param element élément à retirer
   */
  public void retirerElement (BmbElement element)
  {
    this.enfants.removeElement (element) ;
  } /* retirerElement () */

  /**
   * Retire un élément enfant.
   *
   * @param identifiant élément de l'identifiant
   * @throws Exception si l'élément n'existe pas
   */
  public void retirerElement (String identifiant) throws Exception
  {
    for( int i = 0 ; i < this.getNombreElement () ; i++ )
    {
      if (this.getElement(i).getIdentifiant ().equals (identifiant))
      {
        this.retirerElement (this.getElement (i)) ;
        return ;
      }
    }
    throw new Exception (ELEMENT_INEXISTANT) ;
  } /* retirerElement () */

  /**
   * Retourne le nombre d'élément enfant.
   * 
   * @return nombre d'éléments enfants
   */
  public int getNombreElement ()
  {
    return this.enfants.size () ;
  } /* getTaille () */

  /**
   * Retourne un élément enfant.
   *
   * @param indice indice de l'élément
   * @return élément enfant
   */
  public BmbElement getElement (int indice)
  {
    return (BmbElement) enfants.elementAt (indice) ;
  } /* getElement () */ 

  /**
   * Retourne un élément enfant selon son identifiant.
   *
   * @param identifiant élément de l'identifiant
   * @return element visuel
   * @throws Exception si l'élément n'existe pas
   */
  public BmbElement getElement (String identifiant) throws Exception
  {
    for( int i = 0 ; i < this.getNombreElement () ; i++ )
    {
      if (this.getElement(i).getIdentifiant ().equals (identifiant))
      {
        return this.getElement (i) ;
      }
    }
    throw new Exception (ELEMENT_INEXISTANT) ;
  } /* getElement () */

  /**
   * Retourne l'identifiant de l'élément
   * 
   * @return identifiant de l'élément
   */
  public String getIdentifiant ()
  {
    return this.identifiant ;
  } /* getIdentifiant () */

  /**
   * Retourne l'élément parent.
   *
   * @return parent de l'élément
   */
  public BmbElement getParent ()
  {
    return this.parent ;
  } /* getParent () */ 

  /**
   * Choisis le parent de l'élément.
   *
   * @param parent parent de l'élément
   */
  public void setParent (BmbElement parent)
  {
    this.parent = parent ;
  } /* setParent () */ 

  /**
   * Indique qu'un élément vient d'être créé.
   *
   * @param element élément créé
   */
  public void setCree (BmbElement element)
  {
    this.getParent ().setCree (element) ;
  } /* setCree () */ 

  /**
   * Indique que l'élément vient d'être modifié.
   *
   * @param element élément modifié
   */
  public void setModifie (BmbElement element)
  {
    this.getParent ().setModifie (element) ;
  } /* setModifie () */ 

  /**
   * Indique que l'élément vient d'être supprimé.
   *
   * @param element élément supprimé
   */
  public void setSupprime (BmbElement element)
  {
    this.getParent ().setSupprime (element) ;
  } /* setSupprime () */ 
} /* BmbElement */
