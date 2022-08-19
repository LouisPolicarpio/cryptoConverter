package view.components;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.HBox;

public class Header {
    public static HBox create() {
        HBox header = new HBox();
        header.setPadding(new Insets(15, 12, 15, 12));
        header.setSpacing(10);
        header.setStyle("-fx-background-color: #336699;");
        header.setAlignment(Pos.TOP_LEFT);
        return header;
    }
}


