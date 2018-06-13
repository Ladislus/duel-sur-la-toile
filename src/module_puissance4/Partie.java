package module_puissance4;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import javafx.scene.shape.Circle;
import javafx.stage.Stage;

import java.io.File;
import java.util.ArrayList;
import java.util.List;


public class Partie {
    // Vue de la plateforme de jeu Puissance 4

    public static String chem = "./img/module_puissance4/";

    private Plateau p; // Modèle du jeu

    private List<List<Circle>> tableau; // Le tableau de pion
    private List<Button> listeBoutons; // Liste des boutons pour sélectionner une colonne

    public Partie(){
        p = new Plateau("Bernard","xX-Dark-Xx");
    }

    public Scene getScene() {
        BorderPane res = new BorderPane();

        res.setLeft(this.menu());
        res.setBottom(this.bas());
        res.setTop(this.haut());
        res.setRight(this.indic());
        res.setCenter(this.plateau());

        return new Scene(res, 850, 650);
    }

    public VBox menu() {
        VBox res = new VBox();
        res.setAlignment(Pos.BOTTOM_LEFT);
        res.setPadding(new Insets(5,5,30,5));
        res.setSpacing(20.);
        res.setPrefWidth(160.);

        Button save = new Button("Save Game");

        String saveNormal = "-fx-background-color: linear-gradient(#F0E000,#FFA500);"+
                "-fx-background-radius: 5;"+
                "-fx-font-size: 15;";
        String saveHover = "-fx-background-color: linear-gradient(#ffFF80, #ffFF80);"+
                "-fx-background-radius: 5;"+
                "-fx-font-size: 15;";

        save.setStyle(saveNormal);
        save.setOnMouseEntered(e -> save.setStyle(saveHover));
        save.setOnMouseExited(e -> save.setStyle(saveNormal));
        save.setPrefWidth(115.);

        Button forfait = new Button("Forfait");

        String forfaitNormal = "-fx-background-color: linear-gradient(#FF4d40,#f02020);"+
                "-fx-background-radius: 5;"+
                "-fx-font-size: 15;";
        String forfaitHover = "-fx-background-color: linear-gradient(#ff8d80, #ff8d80);"+
                "-fx-background-radius: 5;"+
                "-fx-font-size: 15;";

        forfait.setStyle(forfaitNormal);
        forfait.setOnMouseEntered(e -> forfait.setStyle(forfaitHover));
        forfait.setOnMouseExited(e -> forfait.setStyle(forfaitNormal));
        forfait.setPrefWidth(115.);


        res.getChildren().addAll(save,forfait);
        return res;
    }

    public VBox indic() {
        VBox res = new VBox();
        res.setPrefWidth(160.);

        return res;
    }

    private HBox haut() {
        HBox res = new HBox();
        res.setPrefHeight(100.);
        res.setAlignment(Pos.CENTER_LEFT);

        File file = new File(chem+"connect4logo.png");
        Image im = new Image(file.toURI().toString());
        ImageView iv = new ImageView(im);
        iv.setPreserveRatio(true);
        iv.setFitHeight(60);
        res.getChildren().addAll(iv);

        return res;
    }

    public HBox bas() {
        HBox res = new HBox();
        res.setAlignment(Pos.CENTER);
        res.setSpacing(20.);
        res.setPadding(new Insets(30,0,30,0));
        res.setPrefHeight(100.);

        HBox j1 = new HBox();
        j1.setPrefWidth(300.);
        Circle cercle = new Circle(20.,Color.RED);
        cercle.setStroke(Paint.valueOf("black"));
        j1.getChildren().addAll(p.getJ(1), cercle);
        j1.setSpacing(10.);
        j1.setAlignment(Pos.CENTER_RIGHT);

        HBox j2 = new HBox();
        j2.setPrefWidth(300.);
        Circle cercle2 = new Circle(20.,Color.valueOf("FFE000"));
        cercle2.setStroke(Paint.valueOf("black"));
        j2.getChildren().addAll(cercle2,p.getJ(2));
        j2.setSpacing(10.);
        j2.setAlignment(Pos.CENTER_LEFT);

        res.getChildren().addAll(j1,j2);

        return res;
    }

    public VBox plateau() {
        VBox res = new VBox();

        File file1 = new File(chem+"boutColDesactive.png");
        File file2 = new File(chem+"boutColRed.png");
        File file3 = new File(chem+"boutColYellow.png");

        // LIGNE DE BOUTONS POUR SÉLECTIONNER UNE COLONNE
        this.listeBoutons = new ArrayList<>();
        HBox ligneBoutons = new HBox();
        ligneBoutons.setSpacing(23.);
        ligneBoutons.setAlignment(Pos.CENTER);
        for (int c=0;c<7;c++){
            ImageView imDesactive = new ImageView(new Image(file1.toURI().toString()));
            imDesactive.setPreserveRatio(true);
            imDesactive.setFitWidth(25.);

            ImageView imRed = new ImageView(new Image(file2.toURI().toString()));
            imRed.setPreserveRatio(true);
            imRed.setFitWidth(25.);

            ImageView imYel = new ImageView(new Image(file3.toURI().toString()));
            imYel.setPreserveRatio(true);
            imYel.setFitWidth(25.);

            Button b = new Button("",imDesactive);
            b.setUserData(c);
            b.setOnMouseEntered(e -> {
                if (this.p.getJCour()==1)
                    b.setGraphic(imRed); // si c'est le joueur 1 qui joue, les boutons deviendront rouges au passage de souris
                else
                    b.setGraphic(imYel); // sinon, ils deviendront jaunes
            });
            b.setOnMouseExited(e -> b.setGraphic(imDesactive));
            b.setStyle("-fx-background-color: transparent;");
            b.setOnAction(new ActionJouerCol(this));

            this.listeBoutons.add(b);
            ligneBoutons.getChildren().add(b);
        }

        // TABLEAU DU PLATEAU
        tableau = new ArrayList<>();
        GridPane tab = new GridPane();
        tab.setStyle("-fx-background-color: #4F7EFF;" +
                     "-fx-border-width: 2px;" +
                     "-fx-border-color: black;" +
                     "-fx-background-radius: 30px 30px 0 0;" +
                     "-fx-border-radius: 30px 30px 0 0;");
        tab.setAlignment(Pos.CENTER);
        tab.setHgap(15.);
        tab.setVgap(15.);
        tab.setPadding(new Insets(5,0,10,0));

        for (int c=0;c<7;c++){
            tableau.add(new ArrayList<>());
            for (int l=0;l<6;l++){
                Circle cercle = new Circle(25.,Color.LIGHTGREY);
                cercle.setStroke(Paint.valueOf("black"));
                tab.add(cercle,c,5-l);
                tableau.get(c).add(cercle);
            }
        }

        res.getChildren().addAll(ligneBoutons,tab);
        return res;
    }

    public void majAffichage(int l, int c){
        if (this.p.getPion(l,c)==1)
            this.tableau.get(c).get(l).setFill(Color.RED);
        else if (this.p.getPion(l,c)==2)
            this.tableau.get(c).get(l).setFill(Color.YELLOW);
        if (this.p.getCol(c).isFull())
            this.listeBoutons.get(c).setDisable(true); // on désactive le bouton si sa colonne est pleine
        if (this.p.aUnPuissance4(l,c)){
            // Il y a un puissance 4
            int numJGagne = this.p.getJCour();
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Connect 4 - Victory");
            alert.setHeaderText("And the winner is..."+this.p.getJ(numJGagne).getNom()+" !");
            alert.showAndWait();
        }
        if (this.p.aUnPuissance4(l,c) || this.p.isFull()){
            for (Button b : listeBoutons)
                b.setDisable(true);
        }
    }

    public Plateau getPlateau() {
        return p;
    }
}