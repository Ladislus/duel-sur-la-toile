package module_administrateur;

import javafx.scene.control.*;

/** Modèle du jeu */
public class Jeu {

  private int idJeu;
  private String nomJeu;
  private String regles;
  private String jarJeuPath;
  private char active;
  private int idType;
  private String imagePath;

  /** Constructeur du joueur */
  public Jeu(int idJeu, String nomJeu, String regles, String jarJeuPath, char active, int idType, String imagePath) {
    this.idJeu = idJeu;
    this.nomJeu = nomJeu;
    this.regles = regles;
    this.jarJeuPath = jarJeuPath;
    this.active = active;
    this.imagePath = imagePath;
  }

  public Jeu(){}

  public int getIdJeu() {
    return this.idJeu;
  }

  public String getNomJeu() {
    return this.nomJeu;
  }

  public String getRegles() {
    return this.regles;
  }

  public String getJarJeuPath(){
    return this.jarJeuPath;
  }

  public char getActive() {
    return this.active;
  }

  public int getIdType() {
    return this.idType;
  }

  public String getImagePath() {
    return this.imagePath;
  }

}
