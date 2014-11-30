package bomberscene ;

import java.util.* ;

/**
 * <b>BmbElement</b> est la classe de base des diff�rents �l�ments du jeu. Elle
 * offre plusieurs m�thodes � sp�cialiser permettant d'afficher et de mettre �
 * jour.
 */
 
abstract public class BmbElement
{
  private static final String ELEMENT_INEXISTANT = "L'�l�ment sp�cifi� n'existe pas" ;

  private BmbElement parent ;  // parent de l'�l�ment
  private Vector enfants ;     // �l�ments fils de l'�l�ment actuel
  private String identifiant ; // identifiant de l'�l�ment cot� client
  
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
   * Rafraichit l'�l�ment. Met � jour les coordonn�es, etc...
   */
  public void rafraichir () throws Exception
  {  
    // rafraichit les �l�ments enfants.
    Vector copie = (Vector) this.enfants.clone () ;
    for(int i = 0; i < copie.size (); i ++)
    {
      ((BmbElement) copie.elementAt (i)).rafraichir () ;
    }
  } /* rafraichir () */

  /**
   * Ajoute un �l�ment enfant.
   *
   * @param element �l�ment � ajouter
   */
  public void ajouterElement (BmbElement element)
  {
    this.enfants.addElement (element) ;   
   } /* ajouterElement () */ 

  /**
   * Retire un �l�ment enfant.
   *
   * @param element �l�ment � retirer
   */
  public void retirerElement (BmbElement element)
  {
    this.enfants.removeElement (element) ;
  } /* retirerElement () */

  /**
   * Retire un �l�ment enfant.
   *
   * @param identifiant �l�ment de l'identifiant
   * @throws Exception si l'�l�ment n'existe pas
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
   * Retourne le nombre d'�l�ment enfant.
   * 
   * @return nombre d'�l�ments enfants
   */
  public int getNombreElement ()
  {
    return this.enfants.size () ;
  } /* getTaille () */

  /**
   * Retourne un �l�ment enfant.
   *
   * @param indice indice de l'�l�ment
   * @return �l�ment enfant
   */
  public BmbElement getElement (int indice)
  {
    return (BmbElement) enfants.elementAt (indice) ;
  } /* getElement () */ 

  /**
   * Retourne un �l�ment enfant selon son identifiant.
   *
   * @param identifiant �l�ment de l'identifiant
   * @return element visuel
   * @throws Exception si l'�l�ment n'existe pas
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
   * Retourne l'identifiant de l'�l�ment
   * 
   * @return identifiant de l'�l�ment
   */
  public String getIdentifiant ()
  {
    return this.identifiant ;
  } /* getIdentifiant () */

  /**
   * Retourne l'�l�ment parent.
   *
   * @return parent de l'�l�ment
   */
  public BmbElement getParent ()
  {
    return this.parent ;
  } /* getParent () */ 

  /**
   * Choisis le parent de l'�l�ment.
   *
   * @param parent parent de l'�l�ment
   */
  public void setParent (BmbElement parent)
  {
    this.parent = parent ;
  } /* setParent () */ 

  /**
   * Indique qu'un �l�ment vient d'�tre cr��.
   *
   * @param element �l�ment cr��
   */
  public void setCree (BmbElement element)
  {
    this.getParent ().setCree (element) ;
  } /* setCree () */ 

  /**
   * Indique que l'�l�ment vient d'�tre modifi�.
   *
   * @param element �l�ment modifi�
   */
  public void setModifie (BmbElement element)
  {
    this.getParent ().setModifie (element) ;
  } /* setModifie () */ 

  /**
   * Indique que l'�l�ment vient d'�tre supprim�.
   *
   * @param element �l�ment supprim�
   */
  public void setSupprime (BmbElement element)
  {
    this.getParent ().setSupprime (element) ;
  } /* setSupprime () */ 
} /* BmbElement */
