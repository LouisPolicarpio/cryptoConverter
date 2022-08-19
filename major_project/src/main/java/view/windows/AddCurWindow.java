package view.windows;


import model.API.ModelFacade;
import model.DB.Database;
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
import model.cryptoInfo.InfoList;
import model.cryptoInfo.CryptoInfoResp;
import model.cryptoInfo.Info;
import model.cryptoMap.CryptoMap;
import model.cryptoMap.CryptoMapResp;
import view.components.BodyGrid;
import view.components.Header;

import java.util.Optional;

import static view.windows.Home.infoListDisp;

public class AddCurWindow {
    private static InfoList infoList;

   AddCurWindow(InfoList infoList) {
        this.infoList = infoList;
    }

    public static void show(GridPane homeGrid, Text selectCur, Text convertCur, Button resetBtn, ModelFacade facade){
        // Create a new stage and show it
        Stage stage = view.components.Stage.create("Add Currency");

        //header
        HBox header = Header.create();

        Text scenetitle = new Text("Add Currency");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE);
        header.getChildren().add(scenetitle);

        //body
        GridPane grid = BodyGrid.create();
        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);


        CryptoMapResp cryptoResp =  facade.getInput().getActiveCrypto();
            if(cryptoResp.getStatus().getError_code() == 0){
                int offset = 1;
                CryptoMap cryptoArr[] = cryptoResp.getData();
                for(int i = 0; i < cryptoArr.length; i ++){
                    CryptoMap crypto = cryptoArr[i];

                    Text cryptoName = new Text("Name: " +  crypto.getName());
                    grid.add(cryptoName, 0, i + offset);

                    Text cryptoSymbol = new Text("Symbol: " +  crypto.getSymbol());
                    grid.add(cryptoSymbol, 1, i + offset);

                    Button addCrypto = new Button("Add");
                    grid.add(addCrypto, 2, i + offset);

                    //events
                    addCrypto.setOnAction((event) -> {
                        try {
                            if(Database.doesCurExistInCache((int)Float.parseFloat(crypto.getId()))){
                                addAlertCache(crypto.getId(), homeGrid,  selectCur, convertCur, resetBtn,facade);
                            }else {
                                addAlert(crypto.getId(), homeGrid,  selectCur, convertCur, resetBtn, facade);
                            }
                        } catch (Exception e) {
                            Alert error = new Alert(Alert.AlertType.ERROR);
                            error.setTitle("add Error");
                            error.setHeaderText(e.getMessage());
                            error.showAndWait();
                            e.printStackTrace();
                        }
                    }
                    );
                }
            }

            //BorderPane
            BorderPane border = new BorderPane();
            border.setTop(header);
            border.setCenter(scrollPane);

            Scene scene = new Scene(border, 900, 900);
            stage.setScene(scene);


            stage.show();
    }

    public static void addAlert(String cryptoID, GridPane grid, Text selectCur, Text convertCur, Button resetBtn, ModelFacade facade) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add to list");
        alert.setHeaderText("do you want to add currency to list?");


        ButtonType yesBtn = new ButtonType("Yes");
        ButtonType cancleBtn = new ButtonType("No", ButtonBar.ButtonData.CANCEL_CLOSE);
        alert.getButtonTypes().setAll(yesBtn,cancleBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesBtn){
            CryptoInfoResp cryptoInfo = facade.getInput().getCryptoInfo(cryptoID, true);
            if(cryptoInfo.getStatus().getError_code() == 0){
                Info info = cryptoInfo.getData();

                infoList.addCurrency((int)Float.parseFloat(info.getId()), info.getLogo(), info.getName(), info.getSymbol(), info.getDescription(), info.getDate_launched(), info.getUrls().getWebsite()[0]);
                infoListDisp(grid,1,  selectCur,  convertCur, resetBtn);
            }
        }

        return;
    }

    public static void addAlertCache(String cryptoID, GridPane grid, Text selectCur, Text convertCur, Button resetBtn, ModelFacade facade) throws Exception {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Add to list");
        alert.setHeaderText("cache hit for this data \n get from cache or request fresh data from the API?");


        ButtonType yesBtn = new ButtonType("cache");
        ButtonType cancleBtn = new ButtonType("refresh");
        alert.getButtonTypes().setAll(yesBtn,cancleBtn);

        Optional<ButtonType> result = alert.showAndWait();
        if (result.get() == yesBtn){
            CryptoInfoResp cryptoInfo = facade.getInput().getCryptoInfo(cryptoID, true);
            if(cryptoInfo.getStatus().getError_code() == 0){
                Info info = cryptoInfo.getData();

                infoList.addCurrency((int)Float.parseFloat(info.getId()), info.getLogo(), info.getName(), info.getSymbol(), info.getDescription(), info.getDate_launched(), info.getUrls().getWebsite()[0]);
                infoListDisp(grid,1,  selectCur,  convertCur, resetBtn);
            }
        }if (result.get() == cancleBtn){
            CryptoInfoResp cryptoInfo = facade.getInput().getCryptoInfo(cryptoID, false);
            if(cryptoInfo.getStatus().getError_code() == 0){
                Info info = cryptoInfo.getData();
                infoList.addCurrency((int)Float.parseFloat(info.getId()), info.getLogo(), info.getName(), info.getSymbol(), info.getDescription(), info.getDate_launched(), info.getUrls().getWebsite()[0]);
                infoListDisp(grid,1,  selectCur,  convertCur, resetBtn);
            }
        }

        return;
    }
}
