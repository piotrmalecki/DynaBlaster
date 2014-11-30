package bomberscene ;

import java.awt.*;
import java.util.*;
import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbJoueur</b> gère le comportement du joueur.
 */
public class BmbJoueur extends BmbElementVisuel
{
  private final static int DEPLACEMENT = 16 ; // longueur du déplacement
  private final static int IMG_BAS    = 1 ;   // indice de l'animation vers le bas
  private final static int IMG_DROITE = 2 ;   // indice de l'animation vers la droite
  private final static int IMG_GAUCHE = 3 ;   // indice de l'animation vers la gauche
  private final static int IMG_HAUT   = 4 ;   // indice de l'animation vers le haut
  private final static int IMG_MORT   = 5 ;   // indice de l'animation de destruction
  private final static int PUISSANCE   = 3 ;   // puissance de la bombe
  
  private BmbVueJoueur joueur ; // derniere bombe déposée par le joueur
  private BmbBombe bombe ;      // derniere bombe déposée par le joueur
  private int puissance ;       // puissance de la bombe déposée en nombre de cases
  private boolean detruit ;    // indique si le joueur est touché
  private BmbVueClavier vueClavier ; // données clavier associées au joueur
  
  /**
   * Constructeur de <b>BmbJoueur</b>.
   * 
   * @param puissance puissance de la bombe déposée
   * @param nbBombe 
   * @param parent élément parent
   * @param sprite sprite associé a l'élément
   * @param x coordonnée sous x
   * @param y coordonnée sous y
   */
  public BmbJoueur (BmbVueJoueur joueur, int nbBombe, BmbElement parent, BmbSprite sprite, int x, int y, BmbVueClavier vueClavier)
  {
    super (parent, sprite, x, y) ;
    
    // intialisation des variables.
    this.joueur = joueur ;
    this.puissance = PUISSANCE ;  
    this.bombe = null;
    this.vueClavier = vueClavier ;
    this.detruit = false ;
  } /* BmbJoueur () */

  /**
   * Rafraichit l'élément.
   */
  public void  rafraichir () throws Exception
  {
    boolean existe = false ; // indique si le joueur est sur sa propre bombe
    int vx = 0 ; // vecteur de déplacement sous x
    int vy = 0 ; // vecteur de déplacement sous x
    
    if (!this.detruit)
    {
      // initialise le vecteur de déplacement
      if (this.vueClavier.getPresse (BmbClavier.GAUCHE))
      {
        vx -= 1 ;
      }
      if (this.vueClavier.getPresse (BmbClavier.DROITE))
      {
        vx += 1 ;
      }
      if (this.vueClavier.getPresse (BmbClavier.HAUT))
      {
        vy -= 1 ;
      }
      if (this.vueClavier.getPresse (BmbClavier.BAS))
      {
        vy += 1 ;
      }
    
      // déplace le joueur dans les limites du terrain
      this.setX (this.getX () + vx * DEPLACEMENT) ;
      if ((this.getX () < 16) || (this.getX () > 784))
      {
        this.setX (this.getX () - vx * DEPLACEMENT) ;
        vx = 0 ;
      }
      
      this.setY (this.getY () + vy * DEPLACEMENT) ;
      if ((this.getY () < 16) || (this.getY () > 656))
      {
        this.setY (this.getY () - vy * DEPLACEMENT) ;
        vy = 0 ;
      }
      
      // teste si le joueur entre en collision sous x
      boolean collision = false ;
      Vector tabCollisionX = this.intersection (new Rectangle (this.getX (), this.getY () - vy * DEPLACEMENT, this.getSprite ().getLargeurCadre (), this.getSprite ().getHauteurCadre ())) ;
      for (int i = 0; i < tabCollisionX.size (); i ++)
      {
        // si le joueur entre en collision avec un bloc
        if ((tabCollisionX.elementAt(i) instanceof BmbBloc) || (tabCollisionX.elementAt(i) instanceof BmbBlocDestructible))
        {
          // si une collision avec un autre élément n'a pas déjà eu lieu
          if (! collision)
          {
            // revenir à la position initiale
            this.setX (this.getX () - vx * DEPLACEMENT) ;
            vx = 0 ;
            
            collision = true ;
          }
        }
        else
        if (tabCollisionX.elementAt(i) instanceof BmbBombe )
        {
          // s'il ne s'agit pas de la dernière bombe posée
          if (((BmbBombe) tabCollisionX.elementAt(i)) != this.bombe)
          {
            // si une collision avec un autre élément n'a pas déjà eu lieu
            if (! collision)
            {
              // revenir à la position initiale
              this.setX (this.getX () - vx * DEPLACEMENT) ;
              vx = 0 ;
              
              collision = true ;
            }
          }
          else
          {
            // indique que le joueur est toujours sur la dernière bombe posée
            existe = true ;
          }
        }
      }
      
      // teste si le joueur entre en collision sous y
      collision = false ;
      Vector tabCollisionY = this.intersection (new Rectangle (this.getX () - vx * DEPLACEMENT, this.getY (), this.getSprite ().getLargeurCadre(), this.getSprite ().getHauteurCadre ())) ;
      for (int i = 0; i < tabCollisionY.size (); i ++)
      {
        // si le joueur entre en collision avec un bloc
        if ((tabCollisionY.elementAt(i) instanceof BmbBloc) || (tabCollisionY.elementAt(i) instanceof BmbBlocDestructible))
        {
          // si une collision avec un autre élément n'a pas déjà eu lieu
          if (! collision)
          {
            // revenir à la position initiale
            this.setY (this.getY () - vy * DEPLACEMENT) ;
            vy = 0 ;
            
            collision = true ;
          }
        }
        else
        if (tabCollisionY.elementAt(i) instanceof BmbBombe )
        {
          // s'il ne s'agit pas de la dernière bombe posée
          if (((BmbBombe) tabCollisionY.elementAt(i)) != this.bombe)
          {
            // si une collision avec un autre élément n'a pas déjà eu lieu
            if (! collision)
            {
              // revenir à la position initiale
              this.setY (this.getY () - vy * DEPLACEMENT) ;
              vy = 0 ;
              
              collision = true ;
            }
          }
          else
          {
            // indique que le joueur est toujours sur la dernière bombe posée
            existe = true ;
          }
        }
      }
      
      // teste si le joueur est en collision sous x et y
      collision = false ;
      Vector tabCollision = this.intersection (this.getForme ()) ;
      for (int i = 0; i < tabCollision.size (); i ++)
      {
        // si le joueur entre en collision avec un bloc
        if ((tabCollision.elementAt(i) instanceof BmbBloc) || (tabCollision.elementAt(i) instanceof BmbBlocDestructible))
        {
          // si une collision avec un autre élément n'a pas déjà eu lieu
          if (! collision)
          {
            this.setX (this.getX () - vx * DEPLACEMENT) ;
            this.setY (this.getY () - vy * DEPLACEMENT) ;
            vx = 0 ;
            vy = 0 ;
            
            collision = true ;
          }
        }
        else
        if (tabCollision.elementAt(i) instanceof BmbBombe )
        {
          // s'il ne s'agit pas de la dernière bombe posée
          if (((BmbBombe) tabCollision.elementAt(i)) != this.bombe)
          {
            // si une collision avec un autre élément n'a pas déjà eu lieu
            if (! collision)
            {
              // revenir à la position initiale
              this.setX (this.getX () - vx * DEPLACEMENT) ;
              this.setY (this.getY () - vy * DEPLACEMENT) ;
              vx = 0 ;
              vy = 0 ;
              
              collision = true ;
            }
          }
          else
          {
            // indique que le joueur est toujours sur la dernière bombe posée
            existe = true ;
          }
        }
      }
      
      // met à jour l'animation
      if (vx == -1)
      {
        this.setImageY (IMG_GAUCHE) ;
      }
      else
      if (vx == 1)
      {
        this.setImageY (IMG_DROITE) ;
      }
  
      if (vy == -1)
      {
        this.setImageY (IMG_HAUT) ;
      }
      else
      if (vy == 1)
      {
        this.setImageY (IMG_BAS) ;
      }
      
      // si le joueur ne se déplace pas
      if ((vx == 0) && (vy == 0))
      {
        // réinitialise l'animation
        this.setImageX (1) ;
        this.setDelai (0) ;
      }
      else
      if (this.getImageX () < this.getSprite ().getNombreColonne ())
      {
        this.setDelai (BmbPartieServeur.DELAI) ;
      }
      
      // si le joueur n'est plus sur la dernière bombe qu'il a posé
      if ((! existe) && (this.bombe != null))
      {
        this.bombe = null ;
      }
      
      // place la bombe
      if (this.vueClavier.getPresse (BmbClavier.ENTREE))
      {
        // si le joueur n'est plus sur sa dernière bombe posée
        if (bombe == null)
        {
          // ajouter une bombe
          BmbBombe bombe = new BmbBombe (puissance, this.getParent (), surface.getSprite (BmbSceneServeur.ID_BOMBE), this.getX () - vx * DEPLACEMENT, this.getY () - vy * DEPLACEMENT) ;
          this.getParent ().ajouterElement (bombe) ;
          // indique que le joueur est sur la bombe qu'il vient de poser
          this.bombe = bombe ;
        }
      }
      this.setModifie (this) ;
      super.rafraichir();
    }
    else
    {
      if (this.getImageX () == this.getSprite ().getNombreColonne ())
      {
        this.setSupprime (this) ;
        
        this.getParent ().retirerElement (this) ;
        this.setParent (null) ;
        this.joueur.setMort (true) ;
      }
      else
      {
        this.setModifie (this) ;
        super.rafraichir();
      }
    }
  } /* rafraichir () */
  
  /**
   * Détruit le joueur
   * 
   */
  public void mourir ()
  {
    if (!this.detruit)
    {
      this.setImageX (1) ;
      this.setImageY (IMG_MORT);
      this.setDelai (BmbPartieServeur.DELAI) ;

      this.detruit = true ;
    }
  } /* mourir () */
} /* BmbJoueur */
