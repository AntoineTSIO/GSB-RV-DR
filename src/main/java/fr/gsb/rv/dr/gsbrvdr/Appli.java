package fr.gsb.rv.dr.gsbrvdr;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.entites.Visiteur;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.panneaux.PanneauAccueil;
import fr.gsb.rv.dr.panneaux.PanneauPraticiens;
import fr.gsb.rv.dr.panneaux.PanneauRapports;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Session;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
import fr.gsb.rv.dr.vues.VueConnexion;
import fr.gsb.rv.dr.vues.VueErreur;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javafx.util.Pair;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class Appli extends Application {
    @Override
    public void start(Stage primaryStage) throws IOException, ConnexionException {
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

        PanneauAccueil vueAccueil = new PanneauAccueil();
        PanneauRapports vueRapports = new PanneauRapports();
        PanneauPraticiens vuePraticiens = new PanneauPraticiens();
        StackPane pile = new StackPane();
        pile.getChildren().addAll(vueAccueil, vueRapports, vuePraticiens);
        vueAccueil.toFront();

        BorderPane root = new BorderPane();
        root.setTop(barreMenus);
        root.setCenter(pile);
        Scene scene = new Scene( root , 600 , 500 );
        primaryStage.setTitle("GSB-RV-DR");
        primaryStage.setScene(scene);
        primaryStage.getIcons().add(new Image("https://i.imgur.com/oRcuNCA.png"));
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
                                        primaryStage.setTitle(Session.getSession().getLeVisiteur().getNom() + " " + Session.getSession().getLeVisiteur().getPrenom());
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
                        vueAccueil.toFront();
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
                            Session.fermer();
                            Platform.exit();
                        }
                    }
                }
        );
        itemConsulter.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //System.out.println("[Rapport] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                        /*try {
                            List<Visiteur> visiteurs = ModeleGsbRv.getVisiteurs();
                            for(Visiteur unVisiteur : visiteurs){
                                System.out.println(unVisiteur);
                            }
                        } catch (ConnexionException e) {
                            e.printStackTrace();
                        }*/
                        /*try {
                            List<RapportVisite> rapportsVisites = ModeleGsbRv.getRapportsVisite("c3", 12, 2021);
                            for (RapportVisite unRapportVisite : rapportsVisites) {
                                System.out.println(unRapportVisite);
                            }
                        }catch (ConnexionException e){
                            e.printStackTrace();
                        }*/
                        /*try {
                            ModeleGsbRv.setRapportVisiteLu("c3", 15);
                            List<RapportVisite> rapportsVisites = ModeleGsbRv.getRapportsVisite("c3", 12, 2021);
                            for (RapportVisite unRapportVisite : rapportsVisites) {
                                System.out.println(unRapportVisite);
                            }
                        }catch (ConnexionException e){
                            e.printStackTrace();
                        }*/
                        vueRapports.toFront();
                    }
                }
        );
        itemHesitants.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        //System.out.println("[Praticiens] " + Session.getSession().getLeVisiteur().getPrenom() + " " + Session.getSession().getLeVisiteur().getNom());
                        //try {
                            //List<Praticien> praticiens = ModeleGsbRv.getPraticiensHesitants();
                            //Collections.sort(praticiens, new ComparateurCoefConfiance());
                            //Collections.sort(praticiens, new ComparateurCoefNotoriete());
                            //Collections.sort(praticiens, new ComparateurDateVisite());
                            //for (Praticien unPraticien : praticiens){
                                //System.out.println(unPraticien);
                            //}
                        //} catch (ConnexionException e) {
                            //e.printStackTrace();
                        //}
                        vuePraticiens.toFront();
                    }
                }
        );
    }

    public static void main(String[] args) {
        launch();
    }
}