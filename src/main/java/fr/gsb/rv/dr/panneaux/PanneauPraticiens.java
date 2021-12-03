package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefConfiance;
import fr.gsb.rv.dr.utilitaires.ComparateurCoefNotoriete;
import fr.gsb.rv.dr.utilitaires.ComparateurDateVisite;
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
import javafx.scene.text.Text;

import java.util.List;

public class PanneauPraticiens extends StackPane {

    public static final int CRITERE_COEF_CONFIANCE = 1 ;
    public static final int CRITERE_COEF_NOTORIETE = 2 ;
    public static final int CRITERE_DATE_VISITE = 3 ;
    private int critereTri = CRITERE_COEF_CONFIANCE ;
    private RadioButton rbCoefConfiance = new RadioButton("Confiance");
    private RadioButton rbCoefNotoriete = new RadioButton("Notoriété");
    private RadioButton rbDateVisite = new RadioButton("Date Visite");
    private TableView<Praticien> tabPraticiens = new TableView<>();

    private List<Praticien> praticiens ;
    private ObservableList<Praticien> observableListPraticiens ;

    public PanneauPraticiens(){
        super();
        VBox root = new VBox();
        GridPane boutons = new GridPane();
        boutons.setAlignment(Pos.CENTER);
        boutons.setHgap(10);
        boutons.setVgap(10);
        Text label = new Text("Sélectionner un critère de tri");
        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
        boutons.setPadding(new Insets(25,25,25,25));

        root.getChildren().addAll(label , boutons);
        this.getChildren().add(root);

        boutons.add(rbCoefConfiance, 0, 0);
        boutons.add(rbCoefNotoriete, 1, 0);
        boutons.add(rbDateVisite, 2, 0);

        rbCoefConfiance.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_CONFIANCE);
            this.rafraichir();
        });
        rbCoefNotoriete.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_COEF_NOTORIETE);
            this.rafraichir();
        });
        rbDateVisite.setOnAction(ActionEvent -> {
            this.setCritereTri(CRITERE_DATE_VISITE);
            this.rafraichir();
        });


        ToggleGroup btnGroup = new ToggleGroup();
        rbCoefConfiance.setToggleGroup(btnGroup);
        rbCoefNotoriete.setToggleGroup(btnGroup);
        rbDateVisite.setToggleGroup(btnGroup);
        rbCoefConfiance.setSelected(true);

        try{
            praticiens = ModeleGsbRv.getPraticiensHesitants();
            observableListPraticiens = FXCollections.observableArrayList(praticiens);

            TableColumn<Praticien, Integer> colNumero = new TableColumn<>("Numéro");
            TableColumn<Praticien, String> colNom = new TableColumn<>("Nom");
            TableColumn<Praticien, String> colVille = new TableColumn<>("Ville");

            colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            tabPraticiens.getColumns().add(colNumero);

            colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
            tabPraticiens.getColumns().add(colNom);

            colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
            tabPraticiens.getColumns().add(colVille);

            tabPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            tabPraticiens.setItems(observableListPraticiens);
            this.rafraichir();

            root.getChildren().add(tabPraticiens);
        } catch (ConnexionException e) {
            e.printStackTrace();
        }
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
    }

    public void rafraichir() {
        try {
            praticiens = ModeleGsbRv.getPraticiensHesitants();
            observableListPraticiens = FXCollections.observableArrayList(praticiens);

            switch (this.getCritereTri()){
                case CRITERE_COEF_CONFIANCE:
                    observableListPraticiens.sort(new ComparateurCoefConfiance());
                    tabPraticiens.setItems(observableListPraticiens);
                    break ;
                case CRITERE_COEF_NOTORIETE:
                    observableListPraticiens.sort(new ComparateurCoefNotoriete());
                    tabPraticiens.setItems(observableListPraticiens);
                    break ;
                case CRITERE_DATE_VISITE:
                    observableListPraticiens.sort(new ComparateurDateVisite());
                    tabPraticiens.setItems(observableListPraticiens);
                    break ;
                default:
                    break ;
            }

        } catch (ConnexionException e) {
            e.printStackTrace();
        }

    }

    public int getCritereTri() {
        return critereTri;
    }

    public void setCritereTri(int critereTri) {
        this.critereTri = critereTri;
    }
}
