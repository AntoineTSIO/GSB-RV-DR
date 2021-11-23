package fr.gsb.rv.dr.gsbrvdr;

import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.vues.VueConnexion;
import fr.gsb.rv.dr.vues.VueErreur;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.stage.Stage;
import javafx.util.Pair;
import java.io.IOException;
import java.util.Optional;

public class Appli extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException {
        MenuBar barreMenus = new MenuBar();
        Menu menuFichier = new Menu("Fichier");
        Menu menuRapports = new Menu("Rapports");
        Menu menuPraticiens = new Menu("Praticiens");
        menuRapports.setDisable(true);
        menuPraticiens.setDisable(true);

        MenuItem itemSeConnecter = new MenuItem("Se Connecter");
        MenuItem itemSeDeconnecter = new MenuItem("Se Deconnecter");
        itemSeDeconnecter.setDisable(true);
        MenuItem itemQuitter = new MenuItem("Quitter");
        MenuItem itemConsulter = new MenuItem("Consulter");
        MenuItem itemHesitants = new MenuItem("Hésitants");

        menuFichier.getItems().addAll( itemSeConnecter , itemSeDeconnecter , itemQuitter );
        menuRapports.getItems().addAll( itemConsulter );
        menuPraticiens.getItems().addAll( itemHesitants );
        SeparatorMenuItem sep = new SeparatorMenuItem();
        menuFichier.getItems().add( 2 , sep );
        barreMenus.getMenus().addAll( menuFichier , menuRapports , menuPraticiens );

        Image img = new Image("gsblogo.png");
        ImageView imageView = new ImageView();
        imageView.setImage(img);
        HBox box = new HBox();
        box.getChildren().add(imageView);

        BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        root.getChildren().add(box);
        Scene scene = new Scene( root , 600 , 500 );
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.show();


        itemSeConnecter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        VueConnexion vue = new VueConnexion();
                        Optional<Pair<String, String>> response = vue.showAndWait();
                        if(response.isPresent()){
                                try {
                                    Visiteur delegue = ModeleGsbRv.seConnecter(response.get().getKey(), response.get().getValue());
                                    if(delegue != null){
                                        Session.ouvrir(delegue);
                                        menuRapports.setDisable(false);
                                        menuPraticiens.setDisable(false);
                                        itemSeDeconnecter.setDisable(false);
                                        itemSeConnecter.setDisable(true);
                                        primaryStage.setTitle("GSB-RV-DR | " + Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
                                    }else{
                                        VueErreur vueErreur = new VueErreur("Erreur de connexion","Impossible de se connecter, veuillez ré-essayer","Matricule ou Mot de passe incorrect");
                                        vueErreur.showAndWait();
                                    }
                                } catch (ConnexionException e) {
                                    e.printStackTrace();
                                }
                        }
                        // Session.ouvrir(new Visiteur("OB001", "BOUAICHI", "Oumayma"));
                    }
                }
        );
        itemSeDeconnecter.setOnAction(

                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        menuRapports.setDisable(true);
                        menuPraticiens.setDisable(true);
                        itemSeDeconnecter.setDisable(true);
                        itemSeConnecter.setDisable(false);
                        primaryStage.setTitle("GSB-RV-DR");
                        Session.fermer();
                    }
                }
        );
        itemQuitter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        Alert alertQuitter = new Alert(Alert.AlertType.CONFIRMATION);
                        alertQuitter.setTitle("Quitter");
                        alertQuitter.setHeaderText("Demande de confirmation");
                        alertQuitter.setContentText("Voulez-vous quitter l'application ?");
                        ButtonType btnOui = new ButtonType("Oui");
                        ButtonType btnNon = new ButtonType("Non");
                        alertQuitter.getButtonTypes().setAll( btnOui , btnNon );

                        Optional<ButtonType> response = alertQuitter.showAndWait();
                        if(response.get() == btnOui){
                            Platform.exit();
                        }

                        Session.fermer();
                    }
                }
        );
        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //System.out.println("[Rapport] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                    }
                }
        );
        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //System.out.println("[Praticiens] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}