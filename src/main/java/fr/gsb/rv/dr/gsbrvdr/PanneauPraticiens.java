package fr.gsb.rv.dr.gsbrvdr;

import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PanneauPraticiens extends StackPane {

    public PanneauPraticiens(){
        super();
        VBox root = new VBox();
        Label label = new Label("Praticiens");

        label.setAlignment(Pos.CENTER);
        label.setTextAlignment(TextAlignment.CENTER);
        root.setStyle("-fx-background-color: white ; -fx-alignment: center");
        root.getChildren().add(label);
        this.getChildren().add(root);
    }

}
