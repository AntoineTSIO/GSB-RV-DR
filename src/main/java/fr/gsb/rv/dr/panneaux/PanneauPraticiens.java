package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
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
import java.util.List;

public class PanneauPraticiens extends StackPane {

    protected static int CRITERE_COEF_CONFIANCE = 1 ;
    protected static int CRITERE_COEF_NOTORIETE = 2 ;
    protected static int CRITERE_DATE_VISITE = 3 ;
    private int critereTri = CRITERE_COEF_CONFIANCE ;
    private RadioButton rbCoefConfiance = new RadioButton("Confiance");
    private RadioButton rbCoefNotoriete = new RadioButton("Notoriété");
    private RadioButton rbDateVisite = new RadioButton("Date Visite");

    private VBox root = new VBox();
    private Label label = new Label("Sélectionner un critère de tri");
    private GridPane boutons = new GridPane();
    private ToggleGroup btnGroup = new ToggleGroup();
    private TableView<Praticien> tabPraticiens = new TableView<>(this.rafraichir());
    private TableColumn<Praticien, Integer> colNumero = new TableColumn<>("Numéro");
    private TableColumn<Praticien, String> colNom = new TableColumn<>("Nom");
    private TableColumn<Praticien, String> colVille = new TableColumn<>("Ville");
    private List<Praticien> praticiens ;
    private ObservableList<Praticien> observableListPraticiens ;

    public PanneauPraticiens(){
        super();
        System.out.println(praticiens);
        rbCoefConfiance.setToggleGroup(btnGroup);
        rbCoefNotoriete.setToggleGroup(btnGroup);
        rbDateVisite.setToggleGroup(btnGroup);
        rbCoefConfiance.setSelected(true);
        boutons.add(rbCoefConfiance, 0, 0);
        boutons.add(rbCoefNotoriete, 1, 0);
        boutons.add(rbDateVisite, 2, 0);
        boutons.setAlignment(Pos.CENTER);
        boutons.setHgap(10);
        boutons.setVgap(10);
        colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
        tabPraticiens.getColumns().add(colNumero);
        colNom.setCellValueFactory(new PropertyValueFactory<>("nom"));
        tabPraticiens.getColumns().add(colNom);
        colVille.setCellValueFactory(new PropertyValueFactory<>("ville"));
        tabPraticiens.getColumns().add(colVille);
        tabPraticiens.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

        observableListPraticiens = FXCollections.observableArrayList(this.praticiens);
        System.out.println(observableListPraticiens);
        tabPraticiens.setItems(observableListPraticiens);

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

    public ObservableList rafraichir() {
        try {
            this.praticiens = ModeleGsbRv.getPraticiensHesitants();
        } catch (ConnexionException e) {
            e.printStackTrace();
        }
        observableListPraticiens = FXCollections.observableArrayList(this.praticiens);
        return observableListPraticiens;

    }

    public int getCritereTri() {
        return critereTri;
    }

    public void setCritereTri(int critereTri) {
        this.critereTri = critereTri;
    }
}
