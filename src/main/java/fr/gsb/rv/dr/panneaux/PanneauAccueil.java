package fr.gsb.rv.dr.panneaux;

import javafx.geometry.Insets;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.paint.CycleMethod;
import javafx.scene.paint.LinearGradient;
import javafx.scene.paint.Stop;

public class PanneauAccueil extends StackPane {

    public PanneauAccueil() {
        super();
        VBox root = new VBox();
        Image image = new Image("https://i.imgur.com/k1xhSgD.png");
        ImageView imageView = new ImageView(image);

        root.setStyle("-fx-alignment: center");
        root.setBackground(new Background(
                new BackgroundFill(
                        new LinearGradient(0, 0, 0, 1, true,
                                CycleMethod.NO_CYCLE,
                                new Stop(0, Color.web("#4568DC")),
                                new Stop(1, Color.web("#B06AB3"))
                        ), CornerRadii.EMPTY, Insets.EMPTY
                )));
        root.getChildren().add(imageView);
        this.getChildren().add(root);
    }

}
