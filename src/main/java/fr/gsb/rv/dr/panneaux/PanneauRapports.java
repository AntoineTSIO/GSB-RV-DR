package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.entites.RapportVisite;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.entites.Visiteur;
import javafx.beans.property.SimpleStringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;

public class PanneauRapports extends StackPane {

    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<>(FXCollections.observableArrayList(ModeleGsbRv.getVisiteurs()));
    private ComboBox<Mois> cbMois = new ComboBox<Mois>(FXCollections.observableArrayList(Mois.values()));
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>(FXCollections.observableArrayList(ModeleGsbRv.getAnnee()));
    private Button btnValider = new Button("Valider");
    private TableView<RapportVisite> rapportVisiteTableView = new TableView<>();

    private List<Visiteur> visiteurList;
    private ObservableList<Visiteur> visiteurObservableList;

    public PanneauRapports() throws ConnexionException {
        super();
        VBox root = new VBox();
        GridPane boutons = new GridPane();
        boutons.add(cbVisiteurs, 0, 0);
        boutons.add(cbMois, 1, 0);
        boutons.add(cbAnnee, 2, 0);
        boutons.add(btnValider, 0, 1);
        btnValider.setOnAction(
                new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        rafraichir();
                    }
                }
        );
        try{
            visiteurList = ModeleGsbRv.getRapportsVisite();
            visiteurObservableList = FXCollections.observableArrayList(visiteurList);

            TableColumn<RapportVisite, Integer> colNumero = new TableColumn<>("Numéro");
            TableColumn<RapportVisite, String> colPraticien = new TableColumn<>("Praticien");
            TableColumn<Praticien, String> colNom = new TableColumn<>("Nom");
            TableColumn<Praticien, String> colVille = new TableColumn<>("Ville");
            TableColumn<RapportVisite, LocalDate> colVisite = new TableColumn<>("Visite");
            TableColumn<RapportVisite, String> colRedaction = new TableColumn<>("Rédaction");

            colNumero.setCellValueFactory(new PropertyValueFactory<>("numero"));
            rapportVisiteTableView.getColumns().add(colNumero);

            colNom.setCellValueFactory(
                    param -> {
                        String nom = param.getValue().getNom();
                        return new SimpleStringProperty(nom);
                    }
            );
            colVille.setCellValueFactory(
                    param -> {
                        String ville = param.getValue().getVille();
                        return new SimpleStringProperty(ville);
                    }
            );
            colPraticien.getColumns().addAll(colNom, colVille);
            rapportVisiteTableView.getColumns().add(colPraticien);

            colVisite.setCellValueFactory(
                    colonne -> {
                        return new TableCell<RapportVisite, LocalDate>(){
                            @Override
                            protected void updateItem( LocalDate item, boolean empty){
                                super.updateItem( item, empty );
                                if(empty){
                                    setText("");
                                }else{
                                    DateTimeFormatter formateur = DateTimeFormatter.ofPattern("dd/MM/uuuu");
                                    setText(item.format(formateur));
                                }
                            }
                        }
                    }
            );
            rapportVisiteTableView.getColumns().add(colVisite);

            rapportVisiteTableView.setColumnResizePolicy(TableView.CONSTRAINED_RESIZE_POLICY);

            rapportVisiteTableView.setItems(visiteurObservableList);
            this.rafraichir();

            rapportVisiteTableView.setRowFactory(
                    ligne -> {
                        return new TableRow<RapportVisite>(){
                            @Override
                            protected void updateItem( RapportVisite item, boolean empty){
                                super.updateItem(item, empty);
                                if(item != null ){
                                    if(item.isLu()){
                                        setStyle("-fx-background-color: gold");
                                    }else{
                                        setStyle("-fx-background-color: cyan");
                                    }
                                }
                            }
                        };
                    }
            );

            root.getChildren().add(rapportVisiteTableView);
        } catch (ConnexionException e) {
            e.printStackTrace();
        }

        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().add(boutons);
        root.setPadding(new Insets(20, 20, 20, 20));
        this.getChildren().add(root);
    }

    public void rafraichir() {
        try {
            visiteurList = ModeleGsbRv.getRapportsVisite(matricule, mois, mois);
            visiteurObservableList = FXCollections.observableArrayList(visiteurList);
        } catch (ConnexionException e) {
            e.printStackTrace();
        }

    }
}
