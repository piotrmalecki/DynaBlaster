package bomberscene ;

import bombergraphics.* ;

/**
 * <b>BmbScene</b> gère les différents éléments de la partie.
 */
public class BmbSceneClient extends BmbElement
{
  /**
   * Constructeur de <b>BmbSceneClient</b>.
   * 
   * @param surface surface de dessin
   * @param clavier gestionnaire clavier
   */
  public BmbSceneClient (BmbGraphics surface) throws Exception
  {
    super (null) ;
    
    BmbElementVisuel.surface = surface ;

    // chargement des ressources
    try
    {
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_FOND, surface.getImage (BmbSceneServeur.IMG_FOND), surface.getContexte (), false)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_MAISON, surface.getImage (BmbSceneServeur.IMG_MAISON), surface.getContexte (), false)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_BOMBE, surface.getImage (BmbSceneServeur.IMG_BOMBE), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_FLAMME, surface.getImage (BmbSceneServeur.IMG_FLAMME), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_JOUEUR1, surface.getImage (BmbSceneServeur.IMG_JOUEUR1), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_JOUEUR2, surface.getImage (BmbSceneServeur.IMG_JOUEUR2), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_JOUEUR3, surface.getImage (BmbSceneServeur.IMG_JOUEUR3), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_JOUEUR4, surface.getImage (BmbSceneServeur.IMG_JOUEUR4), surface.getContexte (), 64, 64, true)) ;
      surface.ajouter (new BmbSprite (BmbSceneServeur.ID_BLOC, surface.getImage (BmbSceneServeur.IMG_BLOC), surface.getContexte (), 64, 64, false)) ;
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
    }
  } /* BmbSceneClient () */
} /* BmbSceneClient */
