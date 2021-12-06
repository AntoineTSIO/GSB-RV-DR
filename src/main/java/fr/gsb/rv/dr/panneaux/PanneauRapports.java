package fr.gsb.rv.dr.panneaux;

import fr.gsb.rv.dr.entites.Praticien;
import fr.gsb.rv.dr.modeles.ModeleGsbRv;
import fr.gsb.rv.dr.technique.ConnexionException;
import fr.gsb.rv.dr.technique.Mois;
import fr.gsb.rv.dr.entites.Visiteur;
import javafx.collections.FXCollections;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;

import java.time.Year;
import java.util.List;

public class PanneauRapports extends StackPane {

    private ComboBox<Visiteur> cbVisiteurs = new ComboBox<>(FXCollections.observableArrayList(ModeleGsbRv.getVisiteurs()));;
    private ComboBox<Mois> cbMois = new ComboBox<Mois>(FXCollections.observableArrayList(Mois.values()));
    private ComboBox<Integer> cbAnnee = new ComboBox<Integer>(FXCollections.observableArrayList(ModeleGsbRv.getAnnee()));


    public PanneauRapports() throws ConnexionException {
        super();
        VBox root = new VBox();


        root.setStyle("-fx-alignment: center; -fx-font-weight: bold");
        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().add(cbVisiteurs);
        root.getChildren().add(cbMois);
        root.getChildren().add(cbAnnee);
        this.getChildren().add(root);
    }

    public void raffraichir(){

    }

}
