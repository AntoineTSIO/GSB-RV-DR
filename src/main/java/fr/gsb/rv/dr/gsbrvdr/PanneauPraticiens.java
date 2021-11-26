package fr.gsb.rv.dr.gsbrvdr;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;
import javafx.scene.text.TextAlignment;

public class PanneauPraticiens extends StackPane {

    public PanneauPraticiens(){
        super();
        VBox root = new VBox();
        Label label = new Label("Praticiens");

        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        root.setStyle("-fx-alignment: center");
        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().add(label);
        this.getChildren().add(root);
    }

}
