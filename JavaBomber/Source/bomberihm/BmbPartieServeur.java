package bomberihm ;

import java.awt.* ;
import java.awt.event.* ;
import java.io.*;
import javax.swing.* ;
import bombernetwork.* ;
import bombergraphics.* ;
import bomberscene.* ;

/**
 * <b>BmbPartieServeur</b> gère et affiche la partie côté serveur.
 */
public class BmbPartieServeur extends JFrame
{
  // constantes diverses
  private static final int FENETRE_HEIGHT = 760 ;                        // hauteur de la fenêtre
  private static final int FENETRE_WIDTH  = 872 ;                        // largeur de la fenêtre
  private static final String FENETRE_ICONE = "./Image/JavaBomber.png" ; // fichier contenant l'icone
  private static final String FENETRE_NOM = "JavaBomber Serveur" ;       // nom de la fenêtre
  /**
   * Délai avant rafraichissement.
   */
  public static final int DELAI = 80 ;

  // variables membres
  private BmbPanneau panneau ;        // serveur de l'application
  private BmbGraphics affichage ;     // panneau d'affichage
  private BmbClavier clavier ;        // évenement clavier
  private BmbTableauJoueurs joueurs ; // tableau de joueurs
  private BmbSceneServeur scene ;     // scene du jeu
  private Timer timer ;               // gère le temps

  /**
   * Constructor de <b>BmbPartieServeur</b>.
   * 
   * @param parent fenêtre parente
   * @param panneau interface permettant de démarrer la partie
   */
  public BmbPartieServeur (BmbPanneau panneau, BmbTableauJoueurs joueurs)
  {
    super () ;
    
    // initialisation des variables membres
    this.panneau   = panneau ;
    this.affichage = new BmbGraphics () ;
		this.clavier   = new BmbClavier () ; 
    // initialisation du tableau de joueurs
    this.joueurs   = joueurs ;
		for (int i = 0; i < this.joueurs.getNombreJoueur (); i ++)
		{
		  this.joueurs.getJoueur (i).setRafraichitOk (true) ;
		}
    
    this.getContentPane ().setLayout (new BorderLayout ()) ;
    this.getContentPane ().add (this.affichage, BorderLayout.CENTER) ;
    
    // initialisation de la fenêtre
    this.setResizable (false) ;
    this.setTitle (FENETRE_NOM) ;
    try
    {
      this.setIconImage (this.getToolkit ().getImage (new File (FENETRE_ICONE).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      this.panneau.gestionEtat (this.panneau.new BmbEvenement (BmbPanneau.EVT_SERVEUR_FINPARTIE)) ;
    }
    this.setSize (FENETRE_WIDTH, FENETRE_HEIGHT) ;
    
    // initialisation des gestionnaires
    this.addKeyListener (this.clavier) ;
    this.addWindowListener (new GestionnaireFenetre ()) ;
  } /* BmbPartieServeur () */

  /**
   * Gère les messages reçus en provenance du client.
   * 
   * @param message message envoyé
   */
  public void traiterMessage (BmbMessageServeur message)
  {
    switch (message.getAction ())
    {
      case BmbConstantes.MSG_TOUCHE :
      {
        try
        {
          // mettre à jour les touches préssées
          int touche = Integer.parseInt (message.getArgument(0)) ;
          boolean presse = message.getArgument (1).equals (Boolean.TRUE.toString ()) ;
          
          this.joueurs.getJoueur (message.getEmetteur ()).getVueClavier ().setPresse (touche, presse) ;
        }
        catch (Exception exception)
        {
          System.out.println (exception.toString ()) ;
          exception.printStackTrace () ;
        }
        break ;
      }
      case BmbConstantes.MSG_RAFRAICHITOK :
      {
        // met à jour les informations sur le joueur
        try
        {
          this.joueurs.getJoueur (message.getEmetteur ()).setRafraichitOk (true) ;
        }
        catch (Exception exception)
        {
          System.out.println (exception.toString ()) ;
          exception.printStackTrace () ;
        }
				
			  break ;
			}
    }
  } /* traiterMessage () */

  /**
   * Démarre une nouvelle partie.
   */
  public void demarrerPartie ()
  {
    // démarre tous les joueurs
    for (int i = 0; i < this.joueurs.getNombreJoueur (); i ++)
    {
      if (this.joueurs.getJoueur (i).getPret ())
      {
        this.joueurs.getJoueur (i).getConnexion ().envoyer (new BmbMessage (BmbConstantes.MSG_DEMARRER, "")) ;
      }
    }
    // affiche la fenêtre
    this.setVisible (true) ;
    
    // création de la scène
    try
    {
      this.scene = new BmbSceneServeur (this.affichage, this.joueurs) ;
    }
    catch (Exception exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      BmbPartieServeur.this.panneau.gestionEtat (BmbPartieServeur.this.panneau.new BmbEvenement (BmbPanneau.EVT_SERVEUR_FINPARTIE)) ;
    }
    // initialisation de la partie
    this.timer = new Timer (BmbPartieServeur.DELAI, new GestionnaireTemps ()) ;
		this.timer.start () ;

    this.envoyerScene () ;
  } /* demarrerPartie () */
	
  /**
   * Arrête la partie.
   */
  public void arreterPartie ()
  {
    // arrête tous les client
    for (int i = 0; i < this.joueurs.getNombreJoueur (); i ++)
    {
      if (this.joueurs.getJoueur (i).getPret ())
      {
        this.joueurs.getJoueur (i).setPret (false) ;
        this.joueurs.getJoueur (i).getConnexion ().envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
      }
    }
    this.setVisible (false) ;

    // arret du processus
		this.timer.stop () ;
  } /* arreterPartie () */

  /**
   * Envoie la scène au client
   */
  private void envoyerScene ()
  {
    // envoie les nouveaux éléments
    for (int i = 0 ; i < BmbPartieServeur.this.scene.getNombreNouveau () ; i++)
    {
      // initialise le message
      BmbElementVisuel element = (BmbElementVisuel) BmbPartieServeur.this.scene.getNouveau (i) ;
      String visible ;
      if (element.getVisible ())
      {
        visible = Boolean.TRUE.toString () ;
      }
      else
      {
        visible = Boolean.FALSE.toString () ;
      }
      String message = element.getIdentifiant ()
                     + BmbMessage.SENTINELLE + element.getSprite ().getIdentifiant()
                     + BmbMessage.SENTINELLE + Integer.toString (element.getX ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getY ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getImageX ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getImageY ())
                     + BmbMessage.SENTINELLE + visible ;
      
      // envoie le message aux clients
      for (int j = 0 ; j < BmbPartieServeur.this.joueurs.getNombreJoueur(); j++)
      {
        BmbPartieServeur.this.joueurs.getJoueur(j).getConnexion().envoyer (new BmbMessage (BmbConstantes.MSG_ELT_CREE, message)) ;
      }
    }

    // envoie les éléments modifiés
    for (int i = 0 ; i < BmbPartieServeur.this.scene.getNombreModifie () ; i++)
    {
      // initialise le message
      BmbElementVisuel element = (BmbElementVisuel) BmbPartieServeur.this.scene.getModifie (i) ;
      String visible ;
      if (element.getVisible ())
      {
        visible = Boolean.TRUE.toString () ;
      }
      else
      {
        visible = Boolean.FALSE.toString () ;
      }
      String message = element.getIdentifiant()
                     + BmbMessage.SENTINELLE + Integer.toString (element.getX ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getY ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getImageX ())
                     + BmbMessage.SENTINELLE + Integer.toString (element.getImageY ())
                     + BmbMessage.SENTINELLE + visible ;
      
      // envoie le message aux clients prêts
      for (int j = 0 ; j < BmbPartieServeur.this.joueurs.getNombreJoueur(); j++)
      {
        if (BmbPartieServeur.this.joueurs.getJoueur (j).getRafraichitOk ())
        {
          BmbPartieServeur.this.joueurs.getJoueur(j).getConnexion().envoyer (new BmbMessage (BmbConstantes.MSG_ELT_MODIFIE, message)) ;
        }
      }
    }

    // envoie les éléments supprimés
    for (int i = 0 ; i < BmbPartieServeur.this.scene.getNombreSupprime () ; i++)
    {
      // initialise le message
      BmbElementVisuel element = (BmbElementVisuel) BmbPartieServeur.this.scene.getSupprime (i) ;
      String message = element.getIdentifiant() ;
      
      // envoie le message aux clients
      for (int j = 0 ; j < BmbPartieServeur.this.joueurs.getNombreJoueur(); j++)
      {
        BmbPartieServeur.this.joueurs.getJoueur(j).getConnexion().envoyer (new BmbMessage (BmbConstantes.MSG_ELT_SUPPRIME, message)) ;
      }
    }
    
    // ordonne au client de se rafraichir
    for (int j = 0 ; j < BmbPartieServeur.this.joueurs.getNombreJoueur(); j++)
    {
      if (BmbPartieServeur.this.joueurs.getJoueur (j).getRafraichitOk ())
      {
        BmbPartieServeur.this.joueurs.getJoueur (j).setRafraichitOk (false) ;
        BmbPartieServeur.this.joueurs.getJoueur(j).getConnexion().envoyer (new BmbMessage (BmbConstantes.MSG_RAFRAICHIR, "")) ;
      }
    }
    
    // arrete le jeu s'il reste un seul joueur dans l'arène
    if (this.joueurs.getNombreVivant () < 2)
    {
      this.arreterPartie () ;
      this.panneau.gestionEtat (BmbPartieServeur.this.panneau.new BmbEvenement (BmbPanneau.EVT_SERVEUR_FINPARTIE)) ;
    }
  } /* envoyerScene () */
  
  /**
   * Gestionnaire de temps de <b>BmbPartieServeur</b>
   */
  private class GestionnaireTemps implements ActionListener
  {
    public void actionPerformed (ActionEvent evenement)
    {
      try
      {
        // met à jour la partie
        BmbPartieServeur.this.scene.rafraichir () ;
        BmbPartieServeur.this.affichage.repaint () ;
        
        BmbPartieServeur.this.envoyerScene () ;
      }
      catch (Exception exception)
      {
        System.out.println (exception.toString ()) ;
        exception.printStackTrace () ;
        BmbPartieServeur.this.panneau.gestionEtat (BmbPartieServeur.this.panneau.new BmbEvenement (BmbPanneau.EVT_SERVEUR_FINPARTIE)) ;
      }
    } /* actionPerformed () */
  } /* GestionnaireTemps */

  /**
   * Gestionnaire de fenêtre de <b>BmbPartieServeur</b>.
   */
  private class GestionnaireFenetre extends WindowAdapter
  {
    public void windowClosing (WindowEvent event)
    {
      BmbPartieServeur.this.panneau.gestionEtat (BmbPartieServeur.this.panneau.new BmbEvenement (BmbPanneau.EVT_SERVEUR_FINPARTIE)) ;
    } /* windowClosing () */
  } /* GestionnaireFenetre */
} /* BmbPartieServeur */
