package module_joueur;

import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.File;

public class ConnexionJoueur extends Application {

    Hyperlink hlRegister;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("Accueil : Connexion");
        primaryStage.setScene(creerConnexionJoueur());
        primaryStage.setResizable(false);
        File imageFile = new File("./img/pub/logoWithoutText.png");
        primaryStage.getIcons().add(new Image(imageFile.toURI().toString()));
        primaryStage.show();
    }

    public Scene creerConnexionJoueur(){
        BorderPane bp = new BorderPane();
        bp.setLeft(creerGauche());
        bp.setRight(creerDroite());
        bp.setBottom(creerBas());
        Scene scene = new Scene(bp, 500,290);
        return scene;
    }
    public VBox creerGauche(){
        VBox vBox = new VBox();
        File imageFile = new File("./img/pub/logo.png");
        Image image = new Image(imageFile.toURI().toString());
        ImageView logo = new ImageView();
        Label slogan = new Label("La plateforme de jeux videos innovante !");
        slogan.setTextAlignment(TextAlignment.CENTER);
        slogan.setWrapText(true);
        slogan.setFont(Font.font("Arial", 15));
        logo.setImage(image);
        logo.setFitWidth(100);
        logo.setFitHeight(100);
        vBox.getChildren().addAll(logo, slogan);
        vBox.setPrefWidth(250);
        vBox.setSpacing(20);
        vBox.setPadding(new Insets(25,0,0,25));
        vBox.setAlignment(Pos.TOP_CENTER);
        return vBox;
    }
    public VBox creerDroite(){
        VBox vBox = new VBox();
        hlRegister = new Hyperlink("Pas de compte ? S'inscrire maintenant");
        Hyperlink hlMotDePasseOubliee = new Hyperlink("Mot de passe oubliée ?");
        hlRegister.setOnAction(new ActionRegister(this));
        Label title = new Label("Connexion");
        Label lLogin = new Label("Votre nom d'utilisateur : ");
        Label lPassword = new Label("Votre mot de passe : ");
        TextField tfLogin = new TextField();
        PasswordField tfPassword = new PasswordField();
        Button btConnexion = new Button("Vers l'aventure ! -->>");
        title.setFont(Font.font("Arial", 19));
        vBox.setAlignment(Pos.TOP_CENTER);
        vBox.setPadding(new Insets(18,15,0,0));
        vBox.setPrefWidth(250);
        vBox.setSpacing(10);
        vBox.getChildren().addAll(title, lLogin, tfLogin, lPassword, tfPassword, hlRegister, hlMotDePasseOubliee, btConnexion);
        return vBox;
    }
    public HBox creerBas(){
        HBox hBas = new HBox();
        hBas.setPadding(new Insets(0,0,5,5));
        Label lCopyright = new Label("© Copyright : Duel sur la toile");
        lCopyright.setFont(Font.font("Arial", 10));
        hBas.getChildren().add(lCopyright);
        return hBas;
    }

    public Hyperlink getHlRegister() {
        return hlRegister;
    }

}
