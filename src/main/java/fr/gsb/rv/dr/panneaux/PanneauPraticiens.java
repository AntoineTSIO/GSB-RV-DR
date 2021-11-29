package fr.gsb.rv.dr.panneaux;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;

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

        btnConfiance.setToggleGroup(btnGroup);
        btnNotoriete.setToggleGroup(btnGroup);
        btnDateVisite.setToggleGroup(btnGroup);
        btnConfiance.setSelected(true);
        boutons.add(btnConfiance, 0, 0);
        boutons.add(btnNotoriete, 1, 0);
        boutons.add(btnDateVisite, 2, 0);
        boutons.setAlignment(Pos.CENTER);



        label.setStyle("-fx-font-weight: bold; -fx-font-size: 20");
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
        root.getChildren().addAll(label, boutons);
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
