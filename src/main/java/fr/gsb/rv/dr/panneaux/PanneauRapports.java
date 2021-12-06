package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.entites.Visiteur;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import java.util.Optional;

public class PanneauRapports extends StackPane {

    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<>(FXCollections.observableArrayList(ModeleGsbRv.getVisiteurs()));;
    private ComboBox<Mois> cbMois = new ComboBox<Mois>(FXCollections.observableArrayList(Mois.values()));
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>(FXCollections.observableArrayList(ModeleGsbRv.getAnnee()));
    private Button btnValider = new Button("Valider");

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
                            raffraichir();
                        }
                }
        );

        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().add(boutons);
        root.setPadding(new Insets(20,20,20,20));
        this.getChildren().add(root);
    }

    public void raffraichir(){
        System.out.println("raffraichir");
    }

}
