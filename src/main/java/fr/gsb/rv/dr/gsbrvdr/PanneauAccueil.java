package fr.gsb.rv.dr.gsbrvdr;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.TextAlignment;

public class PanneauAccueil extends StackPane {

    public PanneauAccueil() {
        super();
        VBox root = new VBox();
        Image image = new Image("https://i.imgur.com/k1xhSgD.png");
        ImageView imageView = new ImageView(image);

        root.setStyle("-fx-background-color: white ; -fx-alignment: center");
        root.getChildren().add(imageView);
        this.getChildren().add(root);
    }

}
