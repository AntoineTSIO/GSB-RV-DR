package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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

    public PanneauPraticiens(){
        super();
        VBox root = new VBox();
        Label label = new Label("Sélectionner un critère de tri");
        GridPane boutons = new GridPane();
        ToggleGroup btnGroup = new ToggleGroup();
        RadioButton btnConfiance = new RadioButton("Confiance");
        RadioButton btnNotoriete = new RadioButton("Notoriété");
        RadioButton btnDateVisite = new RadioButton("Date Visite");
        TableView<Praticien> tabPraticiens = new TableView<>();

        btnConfiance.setToggleGroup(btnGroup);
        btnNotoriete.setToggleGroup(btnGroup);
        btnDateVisite.setToggleGroup(btnGroup);
        btnConfiance.setSelected(true);
        boutons.add(btnConfiance, 0, 0);
        boutons.add(btnNotoriete, 1, 0);
        boutons.add(btnDateVisite, 2, 0);
        boutons.setAlignment(Pos.CENTER);
        boutons.setHgap(10);
        boutons.setVgap(10);

        TableColumn<Praticien, Integer> colNumero = new TableColumn<>("Numéro");
        TableColumn<Praticien, String> colNom = new TableColumn<>("Nom");
        TableColumn<Praticien, String> colVille = new TableColumn<>("Ville");

        try{
            List<Praticien> listePraticiens = ModeleGsbRv.getPraticiensHesitants();
            ObservableList<Praticien> praticiens = FXCollections.observableArrayList(listePraticiens);

            System.out.println(praticiens);
            System.out.println(praticiens.getClass());
            PropertyValueFactory<Praticien,Integer> valueNumero = new PropertyValueFactory<>("numero");
            colNumero.setCellValueFactory(valueNumero);
            tabPraticiens.getColumns().add(colNumero);
            PropertyValueFactory<Praticien,String> valueNom = new PropertyValueFactory<>("nom");
            colNom.setCellValueFactory(valueNom);
            tabPraticiens.getColumns().add(colNom);
            PropertyValueFactory<Praticien,String> valueVille = new PropertyValueFactory<>("ville");
            colVille.setCellValueFactory(valueVille);
            tabPraticiens.getColumns().add(colVille);
            tabPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            for(Praticien praticien : praticiens){
                tabPraticiens.getItems().add(praticien);
            }
        }catch (ConnexionException e){
            e.printStackTrace();
        }

        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        label.setPadding(new Insets(10,10,10,10));
        boutons.setPadding(new Insets(10,10,20,10));
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
