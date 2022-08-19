package view.windows;

import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Stage;

import model.API.ModelFacade;
import model.balance.Balance;
import model.conversion.ConversionResp;
import org.apache.http.auth.AuthenticationException;
import view.components.BodyGrid;
import view.components.Header;

import java.io.IOException;

public class ConvertResultsWindow {
    public static void show(int selectedCur, int compareCur, ModelFacade facade, Balance balance, Text homeBalanceText){
        // Create a new stage and show it
        Stage stage = view.components.Stage.create("Convert Currency");

        //header
        HBox header = Header.create();


        Text scenetitle = new Text("Convert Currency");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE);
        header.getChildren().add(scenetitle);

        Text balanceText = new Text(" balance : " + balance.getValue());
        balanceText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        balanceText.setFill(Color.WHITE);
        header.getChildren().add(balanceText);

        //body
        GridPane grid = BodyGrid.create();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        Text amountText = new Text("Amount: ");
        TextField InputAmmount = new TextField("enter amount");
        Button submitBtn = new Button("submit");

        grid.add(amountText, 0,1);
        grid.add(InputAmmount, 1,1);
        grid.add(submitBtn, 2,1);

        //BorderPane
        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setCenter(scrollPane);

        Text rate = new Text("rate: ");
        Text total = new Text("total: ");
        grid.add(rate, 0,  2);
        grid.add(total, 0,  3);


        //event
        submitBtn.setOnAction(event -> {
            if(isPosNumber(InputAmmount.getText())) {
                try {
                    balance.subtractBalanceBy(Float.parseFloat(InputAmmount.getText()));
                    balanceText.setText(" balance : " + balance.getValue());
                    homeBalanceText.setText(" balance : " + balance.getValue());
                    ConversionResp conversionRate = facade.getInput().getConversion(1, Integer.toString(selectedCur), Integer.toString(compareCur));
                    if (conversionRate.getStatus().getError_code() == 0) {
                        conversionRate.getQuote().getPrice();
                        rate.setText("rate: " + Float.toString(conversionRate.getQuote().getPrice()));
                    } else {

                    }

                    ConversionResp conversionTotal = facade.getInput().getConversion(Float.parseFloat(InputAmmount.getText()), Integer.toString(selectedCur), Integer.toString(compareCur));
                    if (conversionTotal.getStatus().getError_code() == 0) {
                        conversionTotal.getQuote().getPrice();
                        total.setText("total: " + Float.toString(conversionTotal.getQuote().getPrice()));
                    } else {
                        Alert error = new Alert(Alert.AlertType.ERROR);
                        error.setTitle("conversion Error");
                        error.showAndWait();
                    }

                    Button emailBtn = new Button("Email Results");
                    grid.add(emailBtn, 5, 3);

                    TextField emailField = new TextField("enter email");
                    grid.add(emailField, 4, 3);

                    emailBtn.setOnAction(event1 -> {
                        try {
                            facade.getOutput().postEmail(emailField.getText(), selectedCur, compareCur, rate.getText(), total.getText());
                        } catch (IOException e) {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Email Error");
                            error.setHeaderText(e.getMessage());
                            error.showAndWait();
                            e.printStackTrace();
                        } catch (AuthenticationException e) {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("Authentication Error");
                            error.setHeaderText(e.getMessage());
                            error.showAndWait();
                            e.printStackTrace();
                        }
                    });
                }catch (IllegalArgumentException e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Out of money");
                    error.setHeaderText("Out of money, conversion amount exceeds balance ");
                    error.showAndWait();
                }




            }
        });


        Scene scene = new Scene(border, 900, 900);
        stage.setScene(scene);


        stage.show();
    }

    public static boolean isPosNumber(String string){
        if(string == null){
            return false;
        }

        try {
            double number = Double.parseDouble(string);
            if(number <= 0){
                return false;
            }
        }catch (NumberFormatException nfe){
            return false;
        }

        return true;
    }

}
