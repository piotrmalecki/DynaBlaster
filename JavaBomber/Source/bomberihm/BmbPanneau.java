package bomberihm ;

import java.awt.* ;
import java.awt.event.* ;
import java.io.* ;
import java.net.* ;
import java.util.* ;
import javax.swing.* ;
import bombernetwork.* ;

/**
 * Panneau principal de l'application <b>JavaBomber</b>. Permet de gérer les
 * connexions, les options, et lancer le jeu.
 * @see bombernetwork.BmbServeur
 * @see bombernetwork.BmbClient
 */
public class BmbPanneau extends JPanel
{
  // constantes utilisées dans les contrôles graphiques
  // barre d'outils
  private static final String BTNMODE_TEXT_CLIENT      = "Client" ;
  private static final String BTNMODE_FILE_CLIENT      = "./Image/Client.png" ;
  private static final String BTNMODE_HINT_CLIENT      = "Mode client" ;
  private static final String BTNMODE_TEXT_SERVEUR     = "Serveur" ;
  private static final String BTNMODE_FILE_SERVEUR     = "./Image/Serveur.png" ;
  private static final String BTNMODE_HINT_SERVEUR     = "Mode serveur" ;
  private static final String BTNMODE_FILE_CONNEXION   = "./Image/Connexion.png" ;
  private static final String BTNMODE_HINT_CONNEXION   = "Se connecter" ;
  private static final String BTNMODE_FILE_DECONNEXION = "./Image/Deconnexion.png" ;
  private static final String BTNMODE_HINT_DECONNEXION = "Se deconnecter" ;
  private static final String BTNMODE_FILE_JOUER       = "./Image/Jouer.png" ;
  private static final String BTNMODE_HINT_JOUER       = "Demarrer la partie" ;
  private static final String BTNMODE_FILE_QUITTER     = "./Image/Quitter.png" ;
  private static final String BTNMODE_HINT_QUITTER     = "Quitter l'application" ;
  private static final String BTNSEPARATEUR_TEXT       = "  " ;
  // liste de connexions
  private static final String CONNEXION_FILE_PRET    = "./Image/Pret.png" ;
  private static final String CONNEXION_FILE_NONPRET = "./Image/NonPret.png" ;
  // panneau de controle
  private static final String PNLRESEAU_TEXT = " Reseau " ;
  private static final String PNLRESEAU_HINT = "Controle des options de connexions" ;
  private static final String PNLOPTION_TEXT = " Options " ;
  private static final String PNLOPTION_HINT = "Options de la partie" ;
  // panneau de controle de la connexion
  private static final String PNLCONNEXION_TEXT = " Connexion " ;
  private static final String PNLCONNEXION_HINT = "Options de connexions" ;
  private static final String PNLADRESSE_TEXT   = "Adresse du serveur :" ;
  private static final String PNLADRESSE_HINT   = "Adresse IP ou textuelle du serveur" ;
  private static final String TFLADRESSE_TEXT   = "127.0.0.1" ;
  private static final String PNLPORT_TEXT      = "Port :" ;
  private static final String PNLPORT_HINT      = "Numero de port à utiliser" ;
  private static final String TFLPORT_TEXT      = "1050" ;
  // panneau d'information réseau
  private static final String PNLINFORMATIONS_TEXT   = " Informations " ;
  private static final String PNLINFORMATIONS_HINT   = "Informations concernant la machine actuelle" ;
  private static final String PNLMACHINENOM_TEXT     = "Nom de la machine :" ;
  private static final String PNLMACHINENOM_HINT     = "Nom de la machine actuelle" ;
  private static final String TFLMACHINENOM_VIDE     = "????????" ;
  private static final String PNLMACHINEADRESSE_TEXT = "Adresse de la machine :" ;
  private static final String PNLMACHINEADRESSE_HINT = "Adresse de la machine actuelle" ;
  private static final String TFLMACHINEADRESSE_VIDE = "???.???.???.???" ;
  // panneau d'opntion du joueur
  private static final String PNLJOUEUR_TEXT    = "Information du joueur" ;
  private static final String PNLJOUEUR_HINT    = "Options concernant le joueur" ;
  private static final String PNLNOMJOUEUR_TEXT = "Nom du joueur :" ;
  private static final String PNLNOMJOUEUR_HINT = "Nom du joueur lors de la partie" ;
  private static final String TFLNOMJOUEUR_VIDE = "Joueur" ;
  // panneau d'options de la partie 
  private static final String PNLPARTIE_TEXT     = " Partie " ;
  private static final String PNLPARTIE_HINT     = "Options de la partie" ;
  private static final String PNLNBROUNDS_TEXT   = "Nombre de rounds :" ;
  private static final String PNLNBROUNDS_HINT   = "Nombre de rounds necessaire pour gagner" ;
  private static final String TFLNBROUNDS_VIDE   = "3" ;
  private static final String PNLDUREEROUND_TEXT = "Duree d'un round :" ;
  private static final String PNLDUREEROUND_HINT = "Duree d'un round en minute" ;
  private static final String TFLDUREEROUND_VIDE = "3" ;
  // panneau de choix de niveau
  private static final String PNLNIVEAU_TEXT = " Niveau " ;
  private static final String PNLNIVEAU_HINT = "Choix du niveau" ;
  private static final String PNLARENE_TEXT  = "Arene :" ;
  private static final String PNLARENE_HINT  = "Choix de l'arene" ;
  private static final String TFLARENE_VIDE  = "BomberArene" ;
  // boîte message
  private static final int TAILLE_BORDURE                  = 2 ;
  private static final Color BORDURE_INT                   = new Color (121, 124, 136) ;
  private static final Color BORDURE_EXT                   = new Color (248, 254, 255) ;
  private static final String PNLMESSAGE_TEXT              = " Messages " ;
  private static final String PNLMESSAGE_HINT              = "Messages indiquant l'etat de l'application" ;
  private static final String MSG_MODE_CLIENT              = "Mode client\n" ;
  private static final String MSG_MODE_SERVEUR             = "Mode serveur\n" ;
  private static final String MSG_DEMARRAGE_SERVEUR        = "Demarrage du serveur\n" ;
  private static final String MSG_ARRET_SERVEUR            = "Arret du serveur\n" ;
  private static final String MSG_DEMARRAGE_CLIENT         = "Demarrage du client\n" ;
  private static final String MSG_ARRET_CLIENT             = "Arret du client\n" ;
  private static final String MSG_DEMARRAGEPARTIE          = "Demarrage de la partie\n" ;
  private static final String MSG_FINPARTIE                = "Fin de la partie\n" ;
  private static final String MSG_ERREUR_DEMARRAGE_SERVEUR = "Erreur lors du demarrage du serveur\n" ;
  private static final String MSG_ERREUR_DEMARRAGE_CLIENT  = "Erreur lors du demarrage du client\n" ;
  // constantes images
  private static ImageIcon imgClient ;
  private static ImageIcon imgServeur ;
  private static ImageIcon imgConnexion ;
  private static ImageIcon imgDeconnexion ;
  private static ImageIcon imgPret ;
  private static ImageIcon imgNonPret ;
  
  // composant graphiques
  private JScrollPane spnConnexion ;
  private JList lstConnexion ;
  // barre d'outils
  private JToolBar tbrBarreOutil ;
  private JButton btnMode ;
  private JButton btnSeparateur1 ;
  private JButton btnConnexion ;
  private JButton btnJouer ;
  private JButton btnSeparateur2 ;
  private JButton btnQuitter ;
  // boîte de contrôle
  private JTabbedPane tpnControle ;
  private JScrollPane spnReseau ;
  private JPanel pnlReseau ;
  private JPanel pnlReseauNord ;
  private JScrollPane spnOption ;
  private JPanel pnlOption ;
  private JPanel pnlOptionNord ;
  // panneau d'information
  private JPanel pnlInformations ;
  private JLabel lblMachineNom ;
  private JTextField tflMachineNom ;
  private JLabel lblMachineAdresse ;
  private JTextField tflMachineAdresse ;
  // panneau de connexions
  private JPanel pnlConnexion ;
  private JLabel lblPort ;
  private JTextField tflPort ;
  private JLabel lblAdresse ;
  private JTextField tflAdresse ;
  // panneau d'options
  private JPanel pnlJoueur ;
  private JLabel lblNomJoueur ;
  private JTextField tflNomJoueur ;
  // panneau d'options
  private JPanel pnlPartie ;
  private JLabel lblNbRounds ;
  private JTextField tflNbRounds ;
  private JLabel lblDureeRound ;
  private JTextField tflDureeRound ;
  // panneau de choix de niveau
  private JPanel pnlNiveau ;
  private JPanel pnlArene ;
  private JLabel lblArene ;
  private JComboBox cbxArene ;
  private JPanel pnlAreneImage ;
  private JButton btnAreneImage ;
  // boîte de messages
  private JPanel pnlMessage ;
  private JScrollPane spnMessage ;
  private JTextArea tarMessage ;
  // gestionnaire d'événement
  private GestionnaireServeur gestionnaireServeur ;
  private GestionnaireClient gestionnaireClient ;

  // constantes représentant un événement
  private static final int EVT_INITIALISATION       = 1  ; // initialisation de la fenêtre
  private static final int EVT_MODE                 = 2  ; // changement de mode
  private static final int EVT_DEMARRER             = 3  ; // démarrer le client ou le serveur
  private static final int EVT_CLIENT_FININITIALISE = 4  ; // le client a fini de s'initialiser
  private static final int EVT_CLIENT_ACCEPTE       = 5  ; // une nouvelle connexion au serveur est accepté
  private static final int EVT_CLIENT_ACCEPTEOK     = 6  ; // une nouvelle connexion au serveur est accepté
  private static final int EVT_CLIENT_PRET          = 7  ; // le client est prêt à jouer
  private static final int EVT_JOUER                = 8  ; // le serveur ou le client veule jouer
  private static final int EVT_LANCE                = 9  ; // le serveur décide de démarrer la partie
  private static final int EVT_PRETOK               = 10 ; // le serveur confirme que le joueur est prêt
  private static final int EVT_CLIENT_FERME         = 11 ; // la connexion est fermé
  private static final int EVT_CONNEXIONS           = 12 ; // le serveur envoie la liste des connexions au clients
  private static final int EVT_NOMJOUEUR            = 13 ; // changement du nom du joueur
  private static final int EVT_MESSAGE              = 14 ; // un message non géré par le panneau est reçu
  /**
   * Evénement indiquant de fermer le panneau
   */
  public static final int EVT_FERMER = 15 ; // fermeture de l'application
  /**
   * Evénement indiquant la fin de la partie cliente
   */
  public static final int EVT_CLIENT_FINPARTIE     = 16 ; // le client arrête la partie
  /**
   * Evénement indiquant la fin de la partie serveur
   */
  public static final int EVT_SERVEUR_FINPARTIE    = 17 ; // le serveur arrête la pârtie
  
  // constantes représentant un état
  private static final int ETAT_SERVEUR_ARRETE        = 1 ; // le serveur n'est pas démarré
  private static final int ETAT_SERVEUR_DEMARRE       = 2 ; // le serveur est à l'écoute
  private static final int ETAT_SERVEUR_JOUER         = 4 ; // la partie serveur est en cours
  private static final int ETAT_CLIENT_ARRETE         = 5 ; // le client n'est pas démarré
  private static final int ETAT_CLIENT_INITIALISATION = 6 ; // le client est en cours d'initialsation
  private static final int ETAT_CLIENT_DEMARRE        = 7 ; // le client est connecté au serveur
  private static final int ETAT_CLIENT_ATTENTE        = 8 ; // le client est en l'attente du démarrage de la partie
  private static final int ETAT_CLIENT_JOUER          = 9 ; // la partie cliente est en cours
  
  // constantes diverses
  private static final int MAXCONNEXIONS = 4 ; // nombre maximum de connexions
  
  // variables membres  
  private int etat ;                       // état actuel de la fenêtre
  private BmbServeur serveur ;             // serveur de l'application
  private BmbClient client ;               // serveur de l'application
  private BmbPartieServeur partieServeur ; // partie serveur de l'application
  private BmbPartieClient partieClient ;   // client de l'application
  private BmbTableauJoueurs joueurs ;      // joueurs connectés

  /**
   * Constructeur de <b>JavaBomberPanneau</b>.
   */
  public BmbPanneau ()
  {
  	super () ;
  	
    // initialisation des gestionnaires
    GestionnaireAction gestionnaireAction = new GestionnaireAction () ;
    this.gestionnaireServeur = new GestionnaireServeur () ;
    this.gestionnaireClient = new GestionnaireClient () ;
    
    // initialisation du panneau d'options de niveau
    this.lblArene = new JLabel () ;
    this.lblArene.setEnabled (false) ;
    this.lblArene.setText (PNLARENE_TEXT) ;
    this.lblArene.setToolTipText (PNLARENE_HINT) ;
    
    this.pnlArene = new JPanel () ;
    this.pnlArene.setLayout (new GridLayout (1,1)) ;
    this.pnlArene.add (lblArene) ;

    this.cbxArene = new JComboBox () ;
    this.cbxArene.addItem (TFLARENE_VIDE) ;
    this.cbxArene.setEnabled (false) ;

    this.btnAreneImage = new JButton () ;
    this.btnAreneImage.setBackground (Color.black) ;
    this.btnAreneImage.setBorder (null) ;
    this.btnAreneImage.setEnabled (false) ;

    this.pnlAreneImage = new JPanel () ;
    this.pnlAreneImage.setLayout (new GridLayout (1,1)) ;
    this.pnlAreneImage.add (btnAreneImage) ;
    
    this.pnlNiveau = new JPanel () ;
    this.pnlNiveau.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLNIVEAU_TEXT)) ;
    this.pnlNiveau.setLayout (new BoxLayout (this.pnlNiveau, BoxLayout.Y_AXIS)) ;
    this.pnlNiveau.setToolTipText (PNLNIVEAU_HINT) ;
    this.pnlNiveau.add (this.pnlArene) ;
    this.pnlNiveau.add (this.cbxArene) ;
    this.pnlNiveau.add (this.pnlAreneImage) ;

    // initialisation du panneau d'options de la partie
    this.lblDureeRound = new JLabel () ;
    this.lblDureeRound.setEnabled (false) ;
    this.lblDureeRound.setText (PNLDUREEROUND_TEXT) ;
    this.lblDureeRound.setToolTipText (PNLDUREEROUND_HINT) ;
    
    this.tflDureeRound = new JTextField () ;
    this.tflDureeRound.setEnabled (false) ;
    this.tflDureeRound.setText (TFLDUREEROUND_VIDE) ;

    this.lblNbRounds = new JLabel () ;
    this.lblNbRounds.setEnabled (false) ;
    this.lblNbRounds.setText (PNLNBROUNDS_TEXT) ;
    this.lblNbRounds.setToolTipText (PNLNBROUNDS_HINT) ;
    
    this.tflNbRounds = new JTextField () ;
    this.tflNbRounds.setEnabled (false) ;
    this.tflNbRounds.setText (TFLNBROUNDS_VIDE) ;
    
    this.pnlPartie = new JPanel () ;
    this.pnlPartie.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLPARTIE_TEXT)) ;
    this.pnlPartie.setLayout (new BoxLayout (this.pnlPartie, BoxLayout.Y_AXIS)) ;
    this.pnlPartie.setToolTipText (PNLPARTIE_HINT) ;
    this.pnlPartie.add (this.lblDureeRound) ;
    this.pnlPartie.add (this.tflDureeRound) ;
    this.pnlPartie.add (this.lblNbRounds) ;
    this.pnlPartie.add (this.tflNbRounds) ;

    // initialisation du panneau d'options du joueur
    this.lblNomJoueur = new JLabel () ;
    this.lblNomJoueur.setText (PNLNOMJOUEUR_TEXT) ;
    this.lblNomJoueur.setToolTipText (PNLNOMJOUEUR_HINT) ;
    
    this.tflNomJoueur = new JTextField () ;
    this.tflNomJoueur.setText (TFLNOMJOUEUR_VIDE) ;
    this.tflNomJoueur.addActionListener (gestionnaireAction) ;
    this.tflNomJoueur.addFocusListener (new GestionnaireFocus ()) ;
    
    this.pnlJoueur = new JPanel () ;
    this.pnlJoueur.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLJOUEUR_TEXT)) ;
    this.pnlJoueur.setLayout (new BoxLayout (this.pnlJoueur, BoxLayout.Y_AXIS)) ;
    this.pnlJoueur.setToolTipText (PNLJOUEUR_HINT) ;
    this.pnlJoueur.add (this.lblNomJoueur) ;
    this.pnlJoueur.add (this.tflNomJoueur) ;
    
    // initialisation du panneau connexion
    this.lblPort = new JLabel () ;
    this.lblPort.setText (PNLPORT_TEXT) ;
    this.lblPort.setToolTipText (PNLPORT_HINT) ;
    
    this.tflPort = new JTextField () ;
    this.tflPort.setText (TFLPORT_TEXT) ;

    this.lblAdresse = new JLabel () ;
    this.lblAdresse.setText (PNLADRESSE_TEXT) ;
    this.lblAdresse.setToolTipText (PNLADRESSE_HINT) ;
    
    this.tflAdresse = new JTextField () ;
    this.tflAdresse.setText (TFLADRESSE_TEXT) ;
    
    this.pnlConnexion = new JPanel () ;
    this.pnlConnexion.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLCONNEXION_TEXT)) ;
    this.pnlConnexion.setLayout (new BoxLayout (this.pnlConnexion, BoxLayout.Y_AXIS)) ;
    this.pnlConnexion.setToolTipText (PNLCONNEXION_HINT) ;
    this.pnlConnexion.add (this.lblAdresse) ;
    this.pnlConnexion.add (this.tflAdresse) ;
    this.pnlConnexion.add (this.lblPort) ;
    this.pnlConnexion.add (this.tflPort) ;

    // initialisation du panneau informations machine
    this.lblMachineAdresse = new JLabel () ;
    this.lblMachineAdresse.setText (PNLMACHINEADRESSE_TEXT) ;
    this.lblMachineAdresse.setToolTipText (PNLMACHINEADRESSE_HINT) ;
    
    this.tflMachineAdresse = new JTextField () ;
    this.tflMachineAdresse.setEnabled (false) ;

    this.lblMachineNom = new JLabel () ;
    this.lblMachineNom.setText (PNLMACHINENOM_TEXT) ;
    this.lblMachineNom.setToolTipText (PNLMACHINENOM_HINT) ;
    
    this.tflMachineNom = new JTextField () ;
    this.tflMachineNom.setEnabled (false) ;

    this.pnlInformations = new JPanel () ;
    this.pnlInformations.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLINFORMATIONS_TEXT)) ;
    this.pnlInformations.setLayout (new BoxLayout (this.pnlInformations, BoxLayout.Y_AXIS)) ;
    this.pnlInformations.setToolTipText (PNLINFORMATIONS_HINT) ;
    this.pnlInformations.add (this.lblMachineNom) ;
    this.pnlInformations.add (this.tflMachineNom) ;
    this.pnlInformations.add (this.lblMachineAdresse) ;
    this.pnlInformations.add (this.tflMachineAdresse) ;

    // initialisation de la boîte de controle
    this.pnlOptionNord = new JPanel () ;
    this.pnlOptionNord.setLayout (new BoxLayout (this.pnlOptionNord, BoxLayout.Y_AXIS)) ;
    this.pnlOptionNord.setToolTipText (PNLOPTION_HINT) ;
    this.pnlOptionNord.add (this.pnlJoueur) ;
    this.pnlOptionNord.add (this.pnlPartie) ;
    this.pnlOptionNord.add (this.pnlNiveau) ;
    
    this.pnlOption = new JPanel () ;
    this.pnlOption.setBorder (BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)) ;
    this.pnlOption.setLayout (new BorderLayout ()) ;
    this.pnlOption.setToolTipText (PNLOPTION_HINT) ;
    this.pnlOption.add (this.pnlOptionNord, BorderLayout.NORTH) ;
    
    this.spnOption = new JScrollPane () ;
    this.spnOption.setName (PNLOPTION_TEXT) ;
    this.spnOption.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS) ;
    this.spnOption.setToolTipText (PNLRESEAU_HINT) ;
    this.spnOption.getViewport ().add (this.pnlOption) ;

    this.pnlReseauNord = new JPanel () ;
    this.pnlReseauNord.setLayout (new BoxLayout (this.pnlReseauNord, BoxLayout.Y_AXIS)) ;
    this.pnlReseauNord.setToolTipText (PNLRESEAU_HINT) ;
    this.pnlReseauNord.add (this.pnlInformations) ;
    this.pnlReseauNord.add (this.pnlConnexion) ;
    
    this.pnlReseau = new JPanel () ;
    this.pnlReseau.setBorder (BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)) ;
    this.pnlReseau.setLayout (new BorderLayout ()) ;
    this.pnlReseau.setToolTipText (PNLRESEAU_HINT) ;
    this.pnlReseau.add (this.pnlReseauNord, BorderLayout.NORTH) ;
    
    this.spnReseau = new JScrollPane () ;
    this.spnReseau.setName (PNLRESEAU_TEXT) ;
    this.spnReseau.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS) ;
    this.spnReseau.setToolTipText (PNLRESEAU_HINT) ;
    this.spnReseau.getViewport ().add (this.pnlReseau) ;

    this.tpnControle = new JTabbedPane () ;
    this.tpnControle.add (this.spnReseau) ;
    this.tpnControle.add (this.spnOption) ;

    // initialisation de la boîte message
    this.tarMessage = new JTextArea () ;
    this.tarMessage.setEnabled (false) ;
    this.tarMessage.setRows (6) ;

    this.spnMessage = new JScrollPane () ;
    this.spnMessage.setVerticalScrollBarPolicy (JScrollPane.VERTICAL_SCROLLBAR_ALWAYS) ;
    this.spnMessage.setToolTipText (PNLMESSAGE_HINT) ;
    this.spnMessage.getViewport ().add (this.tarMessage) ;

    this.pnlMessage = new JPanel () ;
    this.pnlMessage.setBorder (BorderFactory.createTitledBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE)), PNLMESSAGE_TEXT)) ;
    this.pnlMessage.setLayout (new BorderLayout ()) ;
    this.pnlMessage.setToolTipText (PNLMESSAGE_HINT) ;
    this.pnlMessage.add (this.spnMessage, BorderLayout.CENTER) ;
    
    // initialisation de la barre d'outil
    try
    {
      BmbPanneau.imgClient = new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_CLIENT).getCanonicalPath ())) ;
      BmbPanneau.imgServeur = new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_SERVEUR).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    
    this.btnMode = new JButton () ;
    this.btnMode.addActionListener (gestionnaireAction) ;
    this.btnMode.setIcon (BmbPanneau.imgClient) ;
    this.btnMode.setName (BTNMODE_TEXT_CLIENT) ;
    this.btnMode.setToolTipText (BTNMODE_HINT_CLIENT) ;
    
    this.btnSeparateur1 = new JButton () ;
    this.btnSeparateur1.setBorder (null) ;
    this.btnSeparateur1.setEnabled (false) ;
    this.btnSeparateur1.setText (BTNSEPARATEUR_TEXT) ;
    
    try
    {
      BmbPanneau.imgConnexion = new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_CONNEXION).getCanonicalPath ())) ;
      BmbPanneau.imgDeconnexion = new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_DECONNEXION).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    
    this.btnConnexion = new JButton () ;
    this.btnConnexion.addActionListener (gestionnaireAction) ;
    this.btnConnexion.setIcon (imgConnexion) ;
    this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
    
    this.btnJouer = new JButton () ;
    this.btnJouer.addActionListener (gestionnaireAction) ;
    this.btnJouer.setEnabled (false) ;
    try
    {
      this.btnJouer.setIcon (new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_JOUER).getCanonicalPath ()))) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    this.btnJouer.setToolTipText (BTNMODE_HINT_JOUER) ;
    
    this.btnSeparateur2 = new JButton () ;
    this.btnSeparateur2.setBorder (null) ;
    this.btnSeparateur2.setEnabled (false) ;
    this.btnSeparateur2.setText (BTNSEPARATEUR_TEXT) ;

    this.btnQuitter = new JButton () ;
    try
    {
      this.btnQuitter.setIcon (new ImageIcon (this.getToolkit ().getImage (new File (BTNMODE_FILE_QUITTER).getCanonicalPath ()))) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    this.btnQuitter.setToolTipText (BTNMODE_HINT_QUITTER) ;
    this.btnQuitter.addActionListener (gestionnaireAction) ;

    this.tbrBarreOutil = new JToolBar () ;
    this.tbrBarreOutil.add (this.btnMode) ;
    this.tbrBarreOutil.add (this.btnSeparateur1) ;
    this.tbrBarreOutil.add (this.btnConnexion) ;
    this.tbrBarreOutil.add (this.btnJouer) ;
    this.tbrBarreOutil.add (this.btnSeparateur2) ;
    this.tbrBarreOutil.add (this.btnQuitter) ;

    // initialisation de la liste de connexions
    try
    {
      BmbPanneau.imgPret = new ImageIcon (this.getToolkit ().getImage (new File (CONNEXION_FILE_PRET).getCanonicalPath ())) ;
      BmbPanneau.imgNonPret = new ImageIcon (this.getToolkit ().getImage (new File (CONNEXION_FILE_NONPRET).getCanonicalPath ())) ;
    }
    catch (IOException exception)
    {
      System.out.println (exception.toString ()) ;
      exception.printStackTrace () ;
      System.exit (1) ;
    }
    
    this.lstConnexion = new JList () ;
    this.lstConnexion.setCellRenderer (new ConnexionListCellRenderer ()) ;
    this.lstConnexion.setListData (new Vector ()) ;
    
    this.spnConnexion = new JScrollPane () ;
    this.spnConnexion.getViewport ().add (this.lstConnexion) ;
    this.spnConnexion.setBorder (BorderFactory.createCompoundBorder (BorderFactory.createEtchedBorder (BORDURE_INT, BORDURE_EXT), BorderFactory.createEmptyBorder (TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE,TAILLE_BORDURE))) ;

    // initialisation du panneau principal
    this.setLayout (new BorderLayout ()) ;
    this.add (this.tbrBarreOutil, BorderLayout.NORTH) ;
    this.add (this.tpnControle, BorderLayout.EAST) ;
    this.add (this.pnlMessage, BorderLayout.SOUTH) ;
    this.add (this.spnConnexion, BorderLayout.CENTER) ;

  	// initialisation de l'état
    this.gestionEtat (new BmbEvenement (EVT_INITIALISATION)) ;
  } /* JavaBomberPanneau () */

  /**
   * Gère l'état de la fenêtre en fonction des évenements.
   * 
   * @param evenement évenement provoquant un éventuel changement d'état
   */
  public void gestionEtat (BmbEvenement evenement)
  {
    switch (evenement.getIdentifiant ())
    {
      case EVT_INITIALISATION :
      {
        // charge l'image de l'arène par défaut
        try
        {
          ImageIcon imgArene = new ImageIcon (this.getToolkit ().getImage (new File ("./Image/BomberArene/BomberArene.png").getCanonicalPath ())) ;
          this.btnAreneImage.setIcon (imgArene) ;
          this.btnAreneImage.setDisabledIcon (imgArene) ;
        }
        catch (IOException exception)
        {
          System.out.println (exception.toString ()) ;
          exception.printStackTrace () ;
          System.exit (1) ;
        }
        
        // recherche du nom et de l'adresse de la machine
        String nomMachine ;
        try
        {
          nomMachine = InetAddress.getLocalHost ().getHostName () ;
        }
        catch (Exception exception)
        {
          nomMachine = TFLMACHINENOM_VIDE ;
        }
        
        String adresseMachine ;
        try
        {
          adresseMachine = InetAddress.getLocalHost ().getHostAddress () ;
        }
        catch (Exception exception)
        {
          adresseMachine = TFLMACHINEADRESSE_VIDE ;
        }
        this.tflMachineAdresse.setText (adresseMachine) ;
        this.tflMachineNom.setText (nomMachine) ;
        
        // mise à jour de l'état
        this.etat = ETAT_CLIENT_ARRETE ;
        
        break ;
      }
      
      case EVT_MODE :
      {
        switch (this.etat)
        {
          case ETAT_CLIENT_ARRETE :
          {
            // mise à jour de l'état
            this.etat = ETAT_SERVEUR_ARRETE ;
            
            this.btnMode.setIcon (BmbPanneau.imgServeur) ;
            this.btnMode.setName (BTNMODE_TEXT_SERVEUR) ;
            this.btnMode.setToolTipText (BTNMODE_HINT_SERVEUR) ;
            this.tarMessage.append (MSG_MODE_SERVEUR) ;
            this.lblAdresse.setVisible (false) ;
            this.tflAdresse.setVisible (false) ;
            this.lblArene.setEnabled (true) ;
            this.pnlArene.setEnabled (true) ;
            this.cbxArene.setEnabled (true) ;
            this.pnlAreneImage.setEnabled (true) ;
            this.pnlNiveau.setEnabled (true) ;
            this.lblDureeRound.setEnabled (true) ;
            this.tflDureeRound.setEnabled (true) ;
            this.lblNbRounds.setEnabled (true) ;
            this.tflNbRounds.setEnabled (true) ;
            this.pnlPartie.setEnabled (true) ;
            this.lblNomJoueur.setEnabled (false) ;
            this.tflNomJoueur.setEnabled (false) ;
            this.pnlJoueur.setEnabled (false) ;
            
            break ;
          }
          case ETAT_SERVEUR_ARRETE :
          {
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.btnMode.setIcon (BmbPanneau.imgClient) ;
            this.btnMode.setName (BTNMODE_TEXT_CLIENT) ;
            this.btnMode.setToolTipText (BTNMODE_HINT_CLIENT) ;
            this.tarMessage.append (MSG_MODE_CLIENT) ;
            this.lblAdresse.setVisible (true) ;
            this.tflAdresse.setVisible (true) ;
            this.lblArene.setEnabled (false) ;
            this.pnlArene.setEnabled (false) ;
            this.cbxArene.setEnabled (false) ;
            this.pnlAreneImage.setEnabled (false) ;
            this.pnlNiveau.setEnabled (false) ;
            this.lblDureeRound.setEnabled (false) ;
            this.tflDureeRound.setEnabled (false) ;
            this.lblNbRounds.setEnabled (false) ;
            this.tflNbRounds.setEnabled (false) ;
            this.pnlPartie.setEnabled (false) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_FERMER :
      {
        // mise à jour de l'état
        switch (etat)
        {
          case ETAT_SERVEUR_ARRETE :
          {
            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_SERVEUR_DEMARRE :
          {
            // arrêt du serveur
            this.serveur.stop () ;

            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_SERVEUR_JOUER :
          {
            // arrêt du serveur
            this.partieServeur.arreterPartie () ;
            this.serveur.stop () ;
            this.joueurs.effacer () ;

            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_CLIENT_ARRETE :
          {
            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // arrêt du client
            this.client.stop () ;
            
            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_CLIENT_ATTENTE :
          {
            // arrêt du client
            this.client.stop () ;
            
            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
          case ETAT_CLIENT_JOUER :
          {
            // arrêt du client
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
            this.partieClient.arreterPartie () ;
            this.client.stop () ;
            
            // fermeture de l'application
            System.exit (0) ;
            
            break ;
          }
        }
      }
      
      case EVT_DEMARRER :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_ARRETE :
          {
            try
            {
              // initialise la liste des joueurs
              this.joueurs = new BmbTableauJoueurs () ;
              // initialise le serveur
              this.serveur = new BmbServeur (this.gestionnaireServeur, Integer.parseInt (this.tflPort.getText ()), MAXCONNEXIONS) ;
              
              // mise à jour de l'état
              this.etat = ETAT_SERVEUR_DEMARRE ;
              
              this.btnMode.setEnabled (false) ;
              this.btnConnexion.setIcon (imgDeconnexion) ;
              this.btnConnexion.setToolTipText (BTNMODE_HINT_DECONNEXION) ;
              this.tflPort.setEnabled (false) ;
              this.tarMessage.append (MSG_DEMARRAGE_SERVEUR) ;
            }
            catch (Exception exception)
            {
              System.out.println (exception.toString ()) ;
              exception.printStackTrace () ;
              this.tarMessage.append (MSG_ERREUR_DEMARRAGE_SERVEUR) ;
              this.etat = ETAT_SERVEUR_ARRETE ;
            }
            break ;
          }
          case ETAT_SERVEUR_DEMARRE :
          {
            // arrêt du serveur
            this.serveur.stop () ;
            this.serveur = null ;
            // finalise la liste des joueurs
            this.joueurs.effacer () ;
            this.joueurs = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_SERVEUR_ARRETE ;

            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.btnJouer.setEnabled (false) ;
            this.tflPort.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_SERVEUR) ;
            
            break ;
          }
          case ETAT_SERVEUR_JOUER :
          {
            // arrêt du serveur
            this.partieServeur.arreterPartie () ;
            this.partieServeur = null ;
            this.serveur.stop () ;
            this.serveur = null ;
            // finalise la liste des joueurs
            this.joueurs.effacer () ;
            this.joueurs = null ;
            
            
            // mise à jour de l'état
            this.etat = ETAT_SERVEUR_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.btnJouer.setEnabled (false) ;
            this.tflPort.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_SERVEUR) ;
            
            break ;
          }
          case ETAT_CLIENT_ARRETE :
          {
            try
            {
              // mise à jour de l'état 
              this.etat = ETAT_CLIENT_INITIALISATION ;
              
              // initialisation du client
              this.client = new BmbClient (this.gestionnaireClient, Integer.parseInt(this.tflPort.getText()), this.tflAdresse.getText()) ;
              
              // mise à jour de l'état de la fenêtre
              this.btnMode.setEnabled (false) ;
              this.btnConnexion.setIcon (imgDeconnexion) ;
              this.btnConnexion.setToolTipText (BTNMODE_HINT_DECONNEXION) ;
              this.btnJouer.setEnabled (true) ;
              this.tflPort.setEnabled (false) ;
              this.tflAdresse.setEnabled (false) ;
              this.tarMessage.append (MSG_DEMARRAGE_CLIENT) ;
              
              this.gestionEtat (new BmbEvenement (EVT_CLIENT_FININITIALISE)) ;
            }
            catch (Exception exception)
            {
              System.out.println (exception.toString ()) ;
              exception.printStackTrace () ;
              this.tarMessage.append (MSG_ERREUR_DEMARRAGE_CLIENT) ;
              this.etat = ETAT_CLIENT_ARRETE ;
            }
            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // arrêt du client
            this.client.stop () ;
            this.client = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.btnJouer.setEnabled (false) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            
            break ;
          }
          case ETAT_CLIENT_ATTENTE :
          {
            // arrêt du client
            this.client.stop () ;
            this.client = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            
            break ;
          }
          case ETAT_CLIENT_JOUER :
          {
            // arrêt du client
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_ARRETER, "")) ;
            this.partieClient.arreterPartie () ;
            this.partieClient = null ;
            this.client.stop () ;
            this.client = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            this.tarMessage.append (MSG_FINPARTIE) ;

            break ;
          }
        }
        break ;
      }
      
      case EVT_CLIENT_FININITIALISE :
      {
          switch (this.etat)
          {
            case ETAT_CLIENT_INITIALISATION :
            {
              // si la connexion a échoué
              if (this.client.estArrete ())
              {
                // mise à jour de l'état
                this.etat = ETAT_CLIENT_ARRETE ;
                
                this.lstConnexion.setListData (new Vector ()) ;
                this.btnMode.setEnabled (true) ;
                this.btnConnexion.setIcon (imgConnexion) ;
                this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
                this.tflPort.setEnabled (true) ;
                this.tflAdresse.setEnabled (true) ;
                this.tarMessage.append (MSG_ARRET_CLIENT) ;
              }
              else
              {
                // mise à jour de l'état
                this.etat = ETAT_CLIENT_DEMARRE ;
              }
            }
            break ;
          }
        break ;
      }
      
      case EVT_CLIENT_ACCEPTE :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_DEMARRE :
          {
            // ajoute la connexion à la liste
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              BmbConnexion connexion = (BmbConnexion) evenement.getParametre () ;
              this.joueurs.ajouter (new BmbVueJoueur (connexion, "", false)) ;
              connexion.envoyer (new BmbMessage (BmbConstantes.MSG_ACCEPTEROK, "")) ;
              // rafraichir la liste des connexions
              this.rafraichirConnexions () ;
            }
            
            break ;
          }
          case ETAT_SERVEUR_JOUER :
          {
            // ajoute la connexion à la liste
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              this.joueurs.ajouter (new BmbVueJoueur ((BmbConnexion) evenement.getParametre (), "", false)) ;
              this.serveur.getConnexion (this.joueurs.getNombreJoueur () - 1).envoyer (new BmbMessage (BmbConstantes.MSG_ACCEPTEROK, "")) ;
              
              // rafraichir la liste des connexions
              this.rafraichirConnexions () ;
            }
            
            break ;
          }
        }
        break ;
      }

      case EVT_CLIENT_ACCEPTEOK :
      {
        switch (this.etat)
        {
          case ETAT_CLIENT_INITIALISATION :
          {
            // changement du nom du joueur
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_NOMJOUEUR, this.tflNomJoueur.getText ())) ;
            
            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // changement du nom du joueur
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_NOMJOUEUR, this.tflNomJoueur.getText ())) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_CONNEXIONS :
      {
        switch (etat)
        {
          case ETAT_CLIENT_INITIALISATION :
          case ETAT_CLIENT_DEMARRE :
          case ETAT_CLIENT_ATTENTE :
          case ETAT_CLIENT_JOUER :
          {
            // mise à jour des connexions
            if (evenement.getParametre () instanceof BmbMessage)
            {
              BmbMessage message = (BmbMessage) evenement.getParametre ();
              
              Vector connexions = new Vector () ;
              for (int i = 0; i < message.getNombreArguments (); i = i + 2)
              {
                connexions.add (new ConnexionListCellData (message.getArgument (i).equals (Boolean.TRUE.toString ()), message.getArgument (i + 1))) ;
              }
              BmbPanneau.this.lstConnexion.setListData (connexions) ;
            }
            
            break ;
          }
        }
        break ;
      }

      case EVT_JOUER :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_DEMARRE :
          {
            // mise à jour de l'état
            this.etat = ETAT_SERVEUR_JOUER ;
            
            this.btnJouer.setEnabled (false) ;
            this.tarMessage.append (MSG_DEMARRAGEPARTIE) ;

            // démarrage de la partie
            this.partieServeur = new BmbPartieServeur (this, this.joueurs) ;
            this.partieServeur.demarrerPartie () ;
            
            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // démarrage de la partie
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_PRET, "")) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_CLIENT_PRET :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_DEMARRE :
          {
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              BmbConnexion connexion = (BmbConnexion) evenement.getParametre () ;
              
              // indique le joueur comme prêt
              try
              {
                this.joueurs.getJoueur (connexion).setPret (true) ;
                this.joueurs.getJoueur (connexion).setMort (false) ;
                connexion.envoyer (new BmbMessage (BmbConstantes.MSG_PRETOK, "")) ;
              }
              catch (Exception exception)
              {
                System.out.println (exception.toString ()) ;
                exception.printStackTrace () ;
              }

              // mise à jour de l'état de la fenêtre
              this.rafraichirConnexions () ;
              
              // dégriser le bouton jouer s'il y a plus de deux joueurs
              if (this.joueurs.getNombrePret () >= 2)
              {
                this.btnJouer.setEnabled (true) ;
              }
            }
            
            break ;
          }
        }
        break ;
      }

      case EVT_PRETOK :
      {
        switch (etat)
        {
          case ETAT_CLIENT_DEMARRE :
          {
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ATTENTE ;
            
            this.btnJouer.setEnabled (false) ;
            this.lblNomJoueur.setEnabled (false) ;
            this.tflNomJoueur.setEnabled (false) ;
            this.pnlJoueur.setEnabled (false) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_LANCE :
      {
        switch (etat)
        {
          case ETAT_CLIENT_ATTENTE :
          {
            // démarrage de la partie
            this.partieClient = new BmbPartieClient (this, this.client) ;
            this.partieClient.demarrerPartie () ;
            
            // mise à jour de l'état
            this.etat = BmbPanneau.ETAT_CLIENT_JOUER ;
            
            this.tarMessage.append (MSG_DEMARRAGEPARTIE) ;
          }
        }
        break ;
      }
      
      case EVT_CLIENT_FINPARTIE :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_JOUER :
          {
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              // indique le joueur comme non prêt
              try
              {
                BmbPanneau.this.joueurs.getJoueur ((BmbConnexion) evenement.getParametre ()).setPret (false) ;
                BmbPanneau.this.joueurs.getJoueur ((BmbConnexion) evenement.getParametre ()).setMort (true) ;
              }
              catch (Exception exception)
              {
                System.out.println (exception.toString ()) ;
                exception.printStackTrace () ;
              }
              
              // arreter la partie s'il ne reste qu'un seul joueur
              if (this.joueurs.getNombrePret () < 2)
              {
                this.partieServeur.arreterPartie () ;
                this.partieServeur = null ;
                
                // mise à jour de l'état
                this.etat = ETAT_SERVEUR_DEMARRE ;
                
                this.tarMessage.append (MSG_FINPARTIE) ;
              }
              
              // rafraichir la liset des connexions
              this.rafraichirConnexions () ;
            }
            
            break ;
          }
          case ETAT_CLIENT_JOUER :
          {
            this.partieClient.arreterPartie () ;
            this.partieClient = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_DEMARRE ;
            
            this.btnJouer.setEnabled (true) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            this.tarMessage.append (MSG_FINPARTIE) ;
            
            break ;
          }
        }
        break ;
      }
      
      case EVT_SERVEUR_FINPARTIE :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_JOUER :
          {
            // arreter la partie
            this.partieServeur.arreterPartie () ;
            this.partieServeur = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_SERVEUR_DEMARRE ;
            
            this.rafraichirConnexions () ;
            this.tarMessage.append (MSG_FINPARTIE) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_CLIENT_FERME :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_DEMARRE :
          {
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              // supprimer le joueur correspondant
              this.joueurs.supprimerJoueur ((BmbConnexion) evenement.getParametre ()) ;
              
              // s'il y'a moins de deux joueurs, grisé le bouton jouer
              if (this.joueurs.getNombrePret () < 2)
              {
                this.btnJouer.setEnabled (false) ;
              }
              // rafraichir les connexions
              this.rafraichirConnexions () ;
            }
            
            break ;
          }
          case ETAT_SERVEUR_JOUER :
          {
            if (evenement.getParametre () instanceof BmbConnexion)
            {
              // rafraichir la liset des connexions
              this.rafraichirConnexions () ;
              // supprimer le joueur correspondant
              this.joueurs.supprimerJoueur ((BmbConnexion) evenement.getParametre ()) ;
              
              // arreter la partie s'il reste moins de deux joueurs
              if (this.joueurs.getNombrePret () < 2)
              {
                // arrête la partie
                this.partieServeur.arreterPartie () ;
                this.partieServeur = null ;

                this.etat = ETAT_SERVEUR_DEMARRE ;

                this.tarMessage.append (MSG_FINPARTIE) ;
              }
              
              // rafraichir les connexions
              this.rafraichirConnexions () ;
            }

            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // suppression du client
            this.client.stop () ;
            this.client = null ;
            
          	// mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.btnJouer.setEnabled (false) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            
            break ;
          }
          case ETAT_CLIENT_ATTENTE :
          {
            // suppression du client
            this.client.stop () ;
            this.client = null ;
            
            // mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            
            break ;
          }
          case ETAT_CLIENT_JOUER :
          {
            // suppression du client
            this.partieClient.arreterPartie () ;
            this.partieClient = null ;
            this.client.stop () ;
            this.client = null ;
            
          	// mise à jour de l'état
            this.etat = ETAT_CLIENT_ARRETE ;
            
            this.lstConnexion.setListData (new Vector ()) ;
            this.btnMode.setEnabled (true) ;
            this.btnConnexion.setIcon (imgConnexion) ;
            this.btnConnexion.setToolTipText (BTNMODE_HINT_CONNEXION) ;
            this.lblNomJoueur.setEnabled (true) ;
            this.tflNomJoueur.setEnabled (true) ;
            this.pnlJoueur.setEnabled (true) ;
            this.tflPort.setEnabled (true) ;
            this.tflAdresse.setEnabled (true) ;
            this.tarMessage.append (MSG_ARRET_CLIENT) ;
            
            break ;
          }
        }
        break ;
      }

      case EVT_NOMJOUEUR :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_DEMARRE :
          {
            if (evenement.getParametre () instanceof BmbMessageServeur)
            {
              BmbMessageServeur message = (BmbMessageServeur) evenement.getParametre () ;
              
              // change le nom du joueur
              try
              {
                this.joueurs.getJoueur (message.getEmetteur ()).setNom (message.getArgument (0)) ;
              }
              catch (Exception exception)
              {
                System.out.println (exception.toString ()) ;
                exception.printStackTrace () ;
              }
              
              // rafraichir les connexions
              this.rafraichirConnexions () ;
            }
            
            break ;
          }
          case ETAT_SERVEUR_JOUER :
          {
            if (evenement.getParametre () instanceof BmbMessageServeur)
            {
              BmbMessageServeur message = (BmbMessageServeur) evenement.getParametre () ;
              
              // si le joueur ne joue pas
              try
              {
                if (! this.joueurs.getJoueur (message.getEmetteur ()).getPret ())
                {
                  // change le nom du joueur
                  this.joueurs.getJoueur (message.getEmetteur ()).setNom (message.getArgument (0)) ;
                  
                  // rafraichir les connexions
                  this.rafraichirConnexions () ;
                }
              }
              catch (Exception exception)
              {
                System.out.println (exception.toString ()) ;
                exception.printStackTrace () ;
              }
            }
            
            break ;
          }
          case ETAT_CLIENT_DEMARRE :
          {
            // changement du nom du joueur
            this.client.envoyer (new BmbMessage (BmbConstantes.MSG_NOMJOUEUR, this.tflNomJoueur.getText ())) ;
            
            break ;
          }
        }
        break ;
      }
      
      case EVT_MESSAGE :
      {
        switch (etat)
        {
          case ETAT_SERVEUR_JOUER :
          {
            if (evenement.getParametre () instanceof BmbMessageServeur)
            {
              // transmettre le message à la partie
              this.partieServeur.traiterMessage ((BmbMessageServeur) evenement.getParametre ()) ;
            }

            break ;
          }
          case ETAT_CLIENT_JOUER :
          {
            if (evenement.getParametre () instanceof BmbMessage)
            {
              // transmettre le message à la partie
              this.partieClient.traiterMessage ((BmbMessage) evenement.getParametre ()) ;
            }
            
            break ;
          }
        }
        break ;
      }
    }
  }

  /**
   * Rafraichie la liste des connexions du serveur.
   */
  public void rafraichirConnexions ()
  {
    // mise à jour de la liste des connexions
    Vector connexions = new Vector () ;
    
    if (serveur != null)
    {
      String msgConnexions = new String () ; // contient le message à envoyer aux clients
      
      for (int i = 0; i < this.serveur.getNombreConnexions (); i ++)
      {
        // ajoute la connexion à la liste
        connexions.add (new ConnexionListCellData (this.joueurs.getJoueur (i).getPret (), this.joueurs.getJoueur (i).getNom ())) ;
        
        // indique si le joueur est prêt ou non
        if (this.joueurs.getJoueur (i).getPret ())
        {
          msgConnexions = msgConnexions + Boolean.TRUE.toString () + BmbMessage.SENTINELLE ;
        }
        else
        {
          msgConnexions = msgConnexions + Boolean.FALSE.toString () + BmbMessage.SENTINELLE ;
        }
        
        // ajoute le nom du joueur
        msgConnexions = msgConnexions + this.joueurs.getJoueur (i).getNom () ;
        
        // s'il ne s'agit pas de la dernière connexion, on rajoute la sentinelle
        if (i != this.serveur.getNombreConnexions () - 1)
        {
          msgConnexions = msgConnexions + BmbMessage.SENTINELLE ;
        }
      }
      
      // envoie de la liste des connexions aux clients
      for (int i = 0; i < this.serveur.getNombreConnexions (); i ++)
      {
        this.serveur.getConnexion (i).envoyer (new BmbMessage (BmbConstantes.MSG_CONNEXIONS, msgConnexions)) ;
      }
    }
    
    // retourne la liste des connexions
    this.lstConnexion.setListData (connexions) ;
  }

  /**
   * Gère les messages reçus en provenance du client.
   */
  private class GestionnaireServeur implements Observer
  {
    /**
     * Gère les messages reçus en provenance du client.
     * 
     * @param observable objet observé
     * @param message message envoyé
     * @see java.util.Observer#update(Observable, Object)
     */
    public void update (Observable observe, Object message)
    {
      // affiche le message
      if (message instanceof BmbMessageServeur)
      {
        BmbMessageServeur messageRecu = (BmbMessageServeur) message ;
        switch (messageRecu.getAction ())
        {
          case BmbMessage.MSG_ACCEPTER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_ACCEPTE, messageRecu.getEmetteur ())) ;
            
            break ;
          }
          case BmbMessage.MSG_FERMER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_FERME, messageRecu.getEmetteur ())) ;

            break ;
          }
        
          case BmbConstantes.MSG_PRET :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_PRET, messageRecu.getEmetteur ())) ;
            
            break ;
          }
        
          case BmbConstantes.MSG_ARRETER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE, messageRecu.getEmetteur ())) ;
            
            break ;
          }
          case BmbConstantes.MSG_NOMJOUEUR :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_NOMJOUEUR, messageRecu)) ;
            
            break ;
          }
          default :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_MESSAGE, messageRecu)) ;
            
            break ;
          }
        }
      }
    } /* update () */
  } /* ServerListener */
  
  /**
   * Gère les messages reçus en provenance du serveur.
   */
  private class GestionnaireClient implements Observer
  {
    /**
     * Gère les messages reçus en provenance du serveur.
     * 
     * @param observable objet observé
     * @param message message envoyé
     * @see java.util.Observer#update(Observable, Object)
     */
    public void update (Observable observe, Object message)
    {
      // affiche le message
      if (message instanceof BmbMessage)
      {
        BmbMessage messageRecu = (BmbMessage) message ;
        switch (messageRecu.getAction ())
        {
          case BmbConstantes.MSG_ACCEPTEROK :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_ACCEPTEOK)) ;
            
            break ;
          }
        
          case BmbMessage.MSG_FERMER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_FERME)) ;
            
            break ;
          }
        
          case BmbConstantes.MSG_CONNEXIONS :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CONNEXIONS, messageRecu)) ;
            
            break ;
          }
          
          case BmbConstantes.MSG_PRETOK :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_PRETOK)) ;
            
            break ;
          }
          
          case BmbConstantes.MSG_DEMARRER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_LANCE)) ;
            
            break ;
          }
        
          case BmbConstantes.MSG_ARRETER :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_CLIENT_FINPARTIE)) ;

            break ;
          }
          default :
          {
            BmbPanneau.this.gestionEtat (new BmbEvenement (BmbPanneau.EVT_MESSAGE, messageRecu)) ;
            
            break ;
          }
        }
      }
    } /* update () */
  } /* ClientListener */

  /**
   * gestionnaire d'action de JavaBomberPanneau.
   */
  private class GestionnaireAction implements ActionListener
  {
    public void actionPerformed (ActionEvent event)
    {
      if (event.getSource () instanceof JButton)
      {
        JButton presse = (JButton) event.getSource () ;
        if (presse == BmbPanneau.this.btnMode)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_MODE)) ;
        }
        else
        if (presse == BmbPanneau.this.btnQuitter)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_FERMER)) ;
        }
        else
        if (presse == BmbPanneau.this.btnConnexion)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_DEMARRER)) ;
        }
        else
        if (presse == BmbPanneau.this.btnJouer)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_JOUER)) ;
        }
      }
      else
      if (event.getSource () instanceof JTextField)
      {
        JTextField entre = (JTextField) event.getSource () ;
        if (entre == BmbPanneau.this.tflNomJoueur)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_NOMJOUEUR)) ;
        }
      }
    } /* actionPerformed () */
  } /* GestionnaireServeur () */

  /**
   * gestionnaire de focus de JavaBomberPanneau.
   */
  private class GestionnaireFocus extends FocusAdapter
  {
    /**
     * Gère une perte de focus.
     */
    public void focusLost(FocusEvent event)
    {
      if (event.getSource () instanceof JTextField)
      {
        JTextField entre = (JTextField) event.getSource () ;
        if (entre == BmbPanneau.this.tflNomJoueur)
        {
          BmbPanneau.this.gestionEtat (new BmbEvenement (EVT_NOMJOUEUR)) ;
        }
      }
    }
  }
  
  /**
   * Classe utilisée pour rendre une ligne de la liste de connexions.
   */
  private class ConnexionListCellRenderer extends DefaultListCellRenderer
  {
    /**
     * Retourne une ligne de la liste.
     */
    public Component getListCellRendererComponent (JList list, Object value, int index, boolean isSelected, boolean cellHasFocus)
    {
      if (value instanceof ConnexionListCellData)
      {
        // choisi l'icone selon que le joueur soit prêt ou non
        ConnexionListCellData valeur = (ConnexionListCellData) value ;
        if (valeur.getEtat ())
        {
          this.setIcon (BmbPanneau.imgPret) ;
        }
        else
        {
          this.setIcon (BmbPanneau.imgNonPret) ;
        }
        // affiche le nom du joueur
        this.setText (valeur.getNom ()) ;
        
        // affiche en surbrillance si la ligne est selectionnée
        if (isSelected)
        {
          this.setBackground (BmbPanneau.this.lstConnexion.getSelectionBackground ()) ;
          this.setForeground (BmbPanneau.this.lstConnexion.getSelectionForeground ()) ;
        }
        else
        {
          this.setBackground (BmbPanneau.this.lstConnexion.getBackground ()) ;
          this.setForeground (BmbPanneau.this.lstConnexion.getForeground ()) ;
        }
      }
      return this ;
    } /* getListCellRendererComponent () */
  } /* ConnexionListCellRenderer */

  /**
   * Contient les données d'une ligne de la liste de connexions.
   */
  private class ConnexionListCellData extends DefaultListCellRenderer
  {
    private boolean etat ; // état du joueur
    private String nom ; // nom du joueur
      
    /**
     * Constructeur de <b>ConnexionListCellData</b>.
     * 
     * @param etat etat du joueur (prêt ou non prêt)
     * @param nom nom du joueur
     */
    public ConnexionListCellData (boolean etat, String nom)
    {
      this.etat = etat ;
      this.nom = nom ;
    } /* ConnexionListCellData () */
    
    /**
     * Retourne l'état du joueur (prêt ou non prêt).
     * 
     * @return etat du joueur
     */
    public boolean getEtat ()
    {
      return this.etat ;
    } /* getEtat () */
    
    /**
     * Retourne le nom du joueur.
     * 
     * @return nom du joueur
     */
    public String getNom ()
    {
      return this.nom ;
    } /* getEtat () */
  } /* ConnexionListCellData */

  /**
   * Encapsule un événement
   */
  public class BmbEvenement
  {
    private int indentifiant ;
    private Object parametre ;
    
    /**
     * Constructeur de <b>BmbEvenement</b>.
     * 
     * @param identificateur indentifiant de l'événement
     * @param parametre parametre de l'événement
     */
    public BmbEvenement (int indentifiant)
    {
      this.indentifiant = indentifiant ;
      this.parametre = null ;
    } /* BmbEvenement () */

    /**
     * Constructeur de <b>BmbEvenement</b>.
     * 
     * @param identificateur indentifiant de l'événement
     * @param parametre parametre de l'événement
     */
    public BmbEvenement (int indentifiant, Object parametre)
    {
      this.indentifiant = indentifiant ;
      this.parametre = parametre ;
    } /* BmbEvenement () */

    /**
     * Retourne l'identifiant de l'événement.
     * 
     * @return identifiant de l'événement
     */
    public int getIdentifiant ()
    {
      return this.indentifiant ;
    } /* getIdentifiant () */

    /**
     * Retourne le parametre de l'événement.
     * 
     * @param parametre paramètre de l'événement
     */
    public Object getParametre ()
    {
      return this.parametre ;
    } /* getParametre () */
  } /* BmbEvenement */
} /* JavaBomberPanneau */
