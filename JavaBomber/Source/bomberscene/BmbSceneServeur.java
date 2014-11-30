package bomberscene ;

import java.util.* ;
import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbScene</b> gère les différents éléments de la partie.
 */
public class BmbSceneServeur extends BmbElement
{
  // images du jeu
  public static final String IMG_FOND    = "./Image/BomberArene/Fond.png" ;     // image du fond
  public static final String IMG_MAISON  = "./Image/BomberArene/Maison.png" ;   // image de la maison
  public static final String IMG_BOMBE   = "./Image/BomberMan/Bombe.png" ;      // image de la bombe
  public static final String IMG_FLAMME  = "./Image/BomberMan/Flamme.png" ;     // image de la flamme
  public static final String IMG_JOUEUR1 = "./Image/BomberMan/Bomberman1.png" ; // image du joueur
  public static final String IMG_JOUEUR2 = "./Image/BomberMan/Bomberman2.png" ; // image du joueur2
  public static final String IMG_JOUEUR3 = "./Image/BomberMan/Bomberman3.png" ; // image du joueur3
  public static final String IMG_JOUEUR4 = "./Image/BomberMan/Bomberman4.png" ; // image du joueur4
  public static final String IMG_BLOC    = "./Image/BomberArene/Bloc.png" ;     // image du bloc
  
  public static final String ID_FOND    = "1" ; // identifiant du fond
  public static final String ID_MAISON  = "2" ; // identifiant de la maison
  public static final String ID_BOMBE   = "3" ; // identifiant de la bombe
  public static final String ID_FLAMME  = "4" ; // identifiant de la flamme
  public static final String ID_JOUEUR1 = "5" ; // identifiant du joueur
  public static final String ID_JOUEUR2 = "6" ; // identifiant du joueur2
  public static final String ID_JOUEUR3 = "7" ; // identifiant du joueur3
  public static final String ID_JOUEUR4 = "8" ; // identifiant du joueur4
  public static final String ID_BLOC    = "9" ; // identifiant du bloc
  
  public static final String ID_JOUEURS [] = {ID_JOUEUR1,ID_JOUEUR2,ID_JOUEUR3,ID_JOUEUR4} ;
  public static final int TAILLE_BORD = 16 ; // taille du bord en pixel
	
	private Vector elementsCree ;
	private Vector elementsMaj ;
  private Vector elementsSupp ;

  /**
   * Constructeur de <b>BmbSceneServeur</b>.
   * 
   * @param surface surface de dessin
   * @param clavier gestionnaire clavier
   * @param listeJoueurs tableau joueurs
   */
  public BmbSceneServeur (BmbGraphics surface, BmbTableauJoueurs listeJoueurs) throws Exception
  {
    super (null) ;
    
		// initialisation des variables membres
    BmbElementVisuel.surface = surface ;
    this.elementsCree = new Vector () ;
    this.elementsMaj = new Vector () ;
    this.elementsSupp = new Vector () ;

    // chargement des ressources
    try
    {
      surface.ajouter (new BmbSprite (ID_FOND, surface.getImage (IMG_FOND), surface.getContexte (), false)) ;
      surface.ajouter (new BmbSprite (ID_MAISON, surface.getImage (IMG_MAISON), surface.getContexte (), false)) ;
      surface.ajouter (new BmbSprite (ID_BOMBE, surface.getImage (IMG_BOMBE), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_FLAMME, surface.getImage (IMG_FLAMME), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_JOUEUR1, surface.getImage (IMG_JOUEUR1), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_JOUEUR2, surface.getImage (IMG_JOUEUR2), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_JOUEUR3, surface.getImage (IMG_JOUEUR3), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_JOUEUR4, surface.getImage (IMG_JOUEUR4), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (ID_BLOC, surface.getImage (IMG_BLOC), surface.getContexte (), 64, 64, false)) ;
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
    
    // chargement du niveau     
    try
    {
      // ajoute le fond
      this.ajouterElement (new BmbElementVisuel (this, surface.getSprite (ID_FOND), 0, 0)) ;
      
      // ajoute les maisons
      for (int i = 0 ; i < 6; i++)
      {
        for ( int j = 0 ; j < 5; j++)
        {
          this.ajouterElement (new BmbBloc (this, surface.getSprite (ID_MAISON), 80 + i * 128, 80 + j * 128)) ;
        }
      }
      
      // ajoute les blocs cassables
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 16 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 208, 16 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 16 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 464, 16 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 592, 16 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 16 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 80 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 80 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 16, 144 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 144 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 144 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 144 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 144 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 784, 144 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 208 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 400, 208 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 208 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 16, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 80, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 336, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 400, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 464, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 720, 272 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 784, 272 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 336 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 336 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 400, 336 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 336 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 16, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 336, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 400, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 464, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 400 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 784, 400 )) ;

      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 464 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 400, 464 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 784, 464 )) ;
        
       
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 16, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 80, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 144, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 656, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 720, 528 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 784, 528 )) ;
        
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 272, 592 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 592 )) ;
      
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 208, 656 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 464, 656 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 528, 656 )) ;
      this.ajouterElement (new BmbBlocDestructible (this, surface.getSprite (ID_BLOC), 592, 656 )) ;

      // ajoute les joueurs
      for (int i = 0; i < listeJoueurs.getNombreJoueur(); i++)
      {
        if ((i == 0) && (listeJoueurs.getJoueur (i).getPret ()))
        {
          BmbJoueur joueur = new BmbJoueur (listeJoueurs.getJoueur (0), 1, this, surface.getSprite(ID_JOUEURS [i]),  16,  16, listeJoueurs.getJoueur (i).getVueClavier ()) ;
          this.ajouterElement (joueur) ;
        }
        else
        if ((i == 1) && (listeJoueurs.getJoueur (i).getPret ()))
        {
          BmbJoueur joueur = new BmbJoueur (listeJoueurs.getJoueur (1), 1, this, surface.getSprite(ID_JOUEURS [i]),  784,  656, listeJoueurs.getJoueur (i).getVueClavier ()) ;
          this.ajouterElement (joueur) ;
        }
        else
        if ((i == 2) && (listeJoueurs.getJoueur (i).getPret ()))
        {
          BmbJoueur joueur = new BmbJoueur (listeJoueurs.getJoueur (2), 1, this, surface.getSprite(ID_JOUEURS [i]),  784,  16, listeJoueurs.getJoueur (i).getVueClavier ()) ;
          this.ajouterElement (joueur) ;
        }
        else
        if ((i == 3) && (listeJoueurs.getJoueur (i).getPret ()))
        {
          BmbJoueur joueur = new BmbJoueur (listeJoueurs.getJoueur (3), 1, this, surface.getSprite(ID_JOUEURS [i]),  16,  656, listeJoueurs.getJoueur (i).getVueClavier ()) ;
          this.ajouterElement (joueur) ;
        }
      }
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* BmbSceneServeur () */

  /**
   * Rafraichit la scène.
   */
  public void rafraichir () throws Exception
  {
    this.elementsCree.clear () ;
    this.elementsMaj.clear () ;
    this.elementsSupp.clear () ;

    super.rafraichir ();
  } /* rafraichir () */

  /**
   * @return nombre d'élément dans la liste des éléments nouvellement créés.
   */
  public int getNombreNouveau ()
  {
    return this.elementsCree.size () ;
  } /* getNombreNouveau () */
  
  /**
   * @return nombre d'élément dans la liste des éléments modifiés.
   */
  public int getNombreModifie ()
  {
    return this.elementsMaj.size () ;
  } /* getNombreModifie () */

  /**
   * @return nombre d'élément dans la liste des éléments Supprimés.
   */
  public int getNombreSupprime ()
  {
    return this.elementsSupp.size () ;
  } /* getNombreSupprime () */

  /**
   * Retourne un élément de la liste des nouveaux éléments.
   * 
   * @param indice indice du nouvel élément
   * @return élément qui vient d'être créé
   */
  public BmbElementVisuel getNouveau (int indice)
  {
    return (BmbElementVisuel) this.elementsCree.elementAt (indice) ;
  } /* getNouveau () */

  /**
   * Retourne un élément de la liste des éléments modifiés.
   * 
   * @param indice indice du nouvel élément
   * @return élément qui vient d'être créé
   */
  public BmbElementVisuel getModifie (int indice)
  {
    return (BmbElementVisuel) this.elementsMaj.elementAt (indice) ;
  } /* getModifie () */

  /**
   * Retourne un élément de la liste des éléments supprimés.
   * 
   * @param indice indice du nouvel élément
   * @return élément qui vient d'être créé
   */
  public BmbElementVisuel getSupprime (int indice)
  {
    return (BmbElementVisuel) this.elementsSupp.elementAt (indice) ;
  } /* getSupprime () */

  /**
   * Indique qu'un élément vient d'être créé.
   *
   * @param element élément créé
   */
  public void setCree (BmbElement element)
  {
    this.elementsCree.addElement (element) ;
  } /* setCree () */ 

  /**
   * Indique que l'élément vient d'être modifié.
   *
   * @param element élément modifié
   */
  public void setModifie (BmbElement element)
  {
    this.elementsMaj.addElement (element) ;
  } /* setModifie () */ 

  /**
   * Indique que l'élément vient d'être supprimé.
   *
   * @param element élément supprimé
   */
  public void setSupprime (BmbElement element)
  {
    this.elementsSupp.addElement (element) ;
  } /* setSupprime () */ 
} /* BmbSceneServeur */
