package bomberscene;

import java.util.* ;
import bombergraphics.* ;
import bomberihm.* ;

/**
 * <b>BmbFlamme</b> est utilisé pour afficher une flamme.
 */
public class BmbFlamme extends BmbElementVisuel
{
  public static final int FLAMME_HAUT       = 4 ; // anmation de flamme vers le haut
  public static final int FLAMME_BAS        = 5 ; // anmation de flamme vers le bas
  public static final int FLAMME_GAUCHE     = 3 ; // anmation de flamme vers la gauche
  public static final int FLAMME_DROITE     = 2 ; // anmation de flamme vers la droite
  public static final int FLAMME_HORIZONTAL = 6 ; // anmation de flamme horizontale
  public static final int FLAMME_VERTICAL   = 7 ; // anmation de flamme verticale
  
  private int explosion ; // chronomètre la durée de l'explosion

  public BmbFlamme (int puissance, BmbElement parent, BmbSprite sprite, int x, int y, int imageX, int imageY)
  {
    super (parent, sprite, x, y, imageX, imageY, BmbBombe.DUREE_EXPLOSION / sprite.getNombreColonne ()) ;
    
    // teste les collisions avec la flamme
    Vector tabCollision = this.intersection (this.getForme ()) ;
    for (int j = 0 ; j < tabCollision.size(); j++)
    {
      if ((tabCollision.elementAt (j) instanceof BmbBlocDestructible))
      {
        ((BmbBlocDestructible) tabCollision.elementAt(j)).detruire ();
        this.setVisible (false) ;
      }
      else
      if (tabCollision.elementAt (j) instanceof BmbBloc )
      {
        this.setVisible (false) ;
      }
      else
      if (tabCollision.elementAt (j) instanceof BmbJoueur )
      {
        ((BmbJoueur) tabCollision.elementAt(j)).mourir();
      }
    }
    
    // s'il ne s'agit pas d'un embout de la flamme
    if (puissance != 0)
    {
      // utiliser une image de flamme horizontale ou verticale
      if ((imageY == FLAMME_DROITE) || (imageY == FLAMME_GAUCHE))
      {
        this.setImageY (FLAMME_HORIZONTAL) ;
      }
      else
      if ((imageY == FLAMME_HAUT) || (imageY == FLAMME_BAS))
      {
        this.setImageY (FLAMME_VERTICAL) ;
      }
      
      // si la flamme courante existe
      if (this.getVisible ())
      {
        // ajoute la flamme suivante
        try
        {
          if (imageY == FLAMME_HAUT)
          {
            BmbFlamme flamme = new BmbFlamme (puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX (), this.getY () - this.getSprite ().getHauteurCadre (), imageX, imageY) ;
            this.getParent ().ajouterElement (flamme) ;
          }
          else
          if (imageY == FLAMME_BAS)
          {
            BmbFlamme flamme = new BmbFlamme (puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX (), this.getY () + this.getSprite ().getHauteurCadre (), imageX, imageY) ;
            this.getParent ().ajouterElement (flamme) ;
          }
          else
          if (imageY == FLAMME_GAUCHE)
          {
            BmbFlamme flamme = new BmbFlamme (puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX () - this.getSprite ().getLargeurCadre (), this.getY (), imageX, imageY) ;
            this.getParent ().ajouterElement (flamme) ;
          }
          else
          if (imageY == FLAMME_DROITE)
          {
            BmbFlamme flamme = new BmbFlamme (puissance - 1, this.getParent (), BmbElementVisuel.surface.getSprite (BmbSceneServeur.ID_FLAMME), this.getX () + this.getSprite ().getLargeurCadre (), this.getY (), imageX, imageY) ;
            this.getParent ().ajouterElement (flamme) ;
          }
        }
        catch (Exception exception)
        {
          System.out.println (exception.toString ()) ;
          exception.printStackTrace () ;
        }
      }
    }
    
    this.explosion = 0 ;
  } /* BmbFlamme () */

  public void rafraichir () throws Exception
  {
    if (! this.getVisible ())
    {
      // supprimer la flamme de la scène
      this.setSupprime (this) ;
      
      this.getParent ().retirerElement (this) ;
      this.setParent (null) ;
    }
    else
    {
      if (this.explosion < BmbBombe.DUREE_EXPLOSION)
      {
        this.explosion = this.explosion + BmbPartieServeur.DELAI ;
        
        // dessine la flamme
        this.setModifie (this) ;
        super.rafraichir () ;
      }
      else
      {
        // supprimer la flamme de la scène
        this.setSupprime (this) ;

        this.getParent ().retirerElement (this) ;
        this.setParent (null) ;
      }
    }
  } /* rafraichir () */
} /* BmbFlamme */
