package view.windows;

import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import view.components.BodyGrid;
import view.components.Header;
import view.components.Stage;

public class About {

    public static void show(){
        javafx.stage.Stage stage = Stage.create("About");

        //header
        HBox header = Header.create();
        Text scenetitle = new Text("About");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE);
        header.getChildren().add(scenetitle);

        //body
        GridPane grid = BodyGrid.create();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);

        Text applicationName = new Text("Application Name:  Currency Converter" );
        grid.add(applicationName, 1, 1);

        Text creatorName = new Text("Creator Name:  Louis Policarpio" );
        grid.add(creatorName, 1, 2);

        Text references = new Text("references: ");
        grid.add(references, 1, 3);

        Text reference1 = new Text("        Currency api: https://coinmarketcap.com/api/ ");
        grid.add(reference1, 1, 4);

        Text reference2 = new Text("        email api: https://www.twilio.com/sendgrid/email-api ");
        grid.add(reference2, 1, 5);

        Text reference3 = new Text("        code references:");
        grid.add(reference3, 1, 6);

        Text reference3CodeRef0 = new Text("                https://coinmarketcap.com/api/documentation/v1/#section/Introduction");
        grid.add(reference3CodeRef0, 1, 7);

        Text reference3CodeRef1 = new Text("                https://www.baeldung.com/httpclient-post-http-request");
        grid.add(reference3CodeRef1, 1, 8);

        Text reference3CodeRef2 = new Text("                https://docs.oracle.com/javase/8/javafx/scene-graph-tutorial/scenegraph.htm#JFXSG107");
        grid.add(reference3CodeRef2, 1, 9);

        Text reference3CodeRef3 = new Text("                https://docs.oracle.com/javafx/2/layout/builtin_layouts.htm");
        grid.add(reference3CodeRef3, 1, 10);

        Text reference3CodeRef4 = new Text("                https://www.geeksforgeeks.org/javafx-alert-with-examples/");
        grid.add(reference3CodeRef4, 1, 11);

        Text reference3CodeRef5 = new Text("                https://jenkov.com/tutorials/javafx/menubar.html");
        grid.add(reference3CodeRef5, 1, 12);

        Text referenceEmailImage = new Text("Email header image:  https://www.marca.com/en/lifestyle/us-news/2022/05/13/627d9180268e3ed7558b45b1.html ");
        grid.add(referenceEmailImage, 1, 13);





        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setCenter(scrollPane);

        Scene scene = new Scene(border, 900, 900);
        stage.setScene(scene);

        stage.show();

    }
}
