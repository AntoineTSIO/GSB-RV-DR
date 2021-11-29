package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

import java.util.Collections;
import java.util.List;

public class PanneauPraticiens extends StackPane {

    protected static int CRITERE_COEF_CONFIANCE = 1 ;
    protected static int CRITERE_COEF_NOTORIETE = 2 ;
    protected static int CRITERE_DATE_VISITE = 3 ;
    private int critereTri = CRITERE_COEF_CONFIANCE ;
    private RadioButton rbCoefConfiance ;
    private RadioButton rbCoefNotoriete ;
    private RadioButton rbDateVisite ;

    public PanneauPraticiens() throws ConnexionException {
        super();
        VBox root = new VBox();
        Label label = new Label("Sélectionner un critère de tri");
        GridPane boutons = new GridPane();
        ToggleGroup btnGroup = new ToggleGroup();
        RadioButton btnConfiance = new RadioButton("Confiance");
        RadioButton btnNotoriete = new RadioButton("Notoriété");
        RadioButton btnDateVisite = new RadioButton("Date Visite");

        btnConfiance.setToggleGroup(btnGroup);
        btnNotoriete.setToggleGroup(btnGroup);
        btnDateVisite.setToggleGroup(btnGroup);
        btnConfiance.setSelected(true);
        boutons.add(btnConfiance, 0, 0);
        boutons.add(btnNotoriete, 1, 0);
        boutons.add(btnDateVisite, 2, 0);
        boutons.setAlignment(Pos.CENTER);

        TableView<Praticien> tabPraticiens = new TableView<Praticien>();
            TableColumn<Praticien, Integer> colNumero = new TableColumn<>("Numéro");
            TableColumn<Praticien, String> colIdentite = new TableColumn<>("Identité");
            TableColumn<Praticien, String> colNom = new TableColumn<>("Nom");
            TableColumn<Praticien, String> colPrenom = new TableColumn<>("Prenom");
            colIdentite.getColumns().addAll(colNom, colPrenom);
            TableColumn<Praticien, String> colVille = new TableColumn<>("Ville");
            colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            colPrenom.setCellValueFactory(new PropertyValueFactory<>("prenom"));
            colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));

        tabPraticiens.getColumns().addAll(colNumero, colIdentite, colVille);
        tabPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        label.setPadding(new Insets(10,10,10,10));
        boutons.setPadding(new Insets(10,10,10,10));
        tabPraticiens.setPadding(new Insets(10,30,10,30));
        root.setAlignment(Pos.CENTER);
        root.setStyle("-fx-alignment: center");
        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().addAll(label, boutons, tabPraticiens);
        this.getChildren().add(root);
    }

    public void rafraichir(){

    }

    public int getCritereTri() {
        return critereTri;
    }

    public void setCritereTri(int critereTri) {
        this.critereTri = critereTri;
    }
}
