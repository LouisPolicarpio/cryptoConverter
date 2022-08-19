package view.windows;

import javafx.beans.binding.Bindings;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import model.API.ModelFacade;
import model.balance.Balance;
import model.DB.Database;
import javafx.application.HostServices;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import model.cryptoInfo.InfoList;
import model.cryptoInfo.Info;
import view.components.BodyGrid;
import view.components.Header;
import javafx.scene.image.Image;
import main.MainApp;


import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

public class Home {
    private static int selectedCur;
    private static int compareCur;
    private static InfoList infoList;

    public void setInfo(InfoList info) {
        this.infoList = info;
    }

    public static Scene getScene(ModelFacade facade){

        Balance balance = new Balance( alertEnterBalance());


        //header
        HBox header = Header.create();

        Text scenetitle = new Text("Currency List");
        scenetitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
        scenetitle.setFill(Color.WHITE);





        Text selected = new Text(" Selected currency : ");
        selected.setFill(Color.WHITE);
        Text selectCur = new Text("             ");
        selectCur.setFill(Color.WHITE);
        Text convertTo = new Text("Convert To : ");
        convertTo.setFill(Color.WHITE);
        Text convertCur = new Text("             ");
        convertCur.setFill(Color.WHITE);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Details");
        Menu menuList = new Menu("Currency List");
        Button resetBtn = new Button("Reset Selection");


        MenuItem  convertBtn = new MenuItem ("Convert");
        MenuItem  aboutBtn = new MenuItem ("About");
        MenuItem  addCurBtn = new MenuItem ("Add Currency");
        MenuItem  clearBtn = new MenuItem ("Clear List");


        header.getChildren().add(scenetitle);



        header.getChildren().add(selected);
        header.getChildren().add(selectCur);
        header.getChildren().add(convertTo);
        header.getChildren().add(convertCur);
        header.getChildren().add(resetBtn);

        header.getChildren().add(menuBar);

        menuBar.getMenus().add(menuList);
        menuBar.getMenus().add(menu) ;

        menuList.getItems().add(addCurBtn) ;
        menuList.getItems().add(clearBtn);
        menuList.getItems().add(convertBtn);
        menu.getItems().add(aboutBtn);

        Text balanceText = new Text(" balance : " + balance.getValue());
        balanceText.setFont(Font.font("Tahoma", FontWeight.NORMAL, 15));
        balanceText.setFill(Color.WHITE);



        header.getChildren().add(balanceText);


        //body


        GridPane grid = BodyGrid.create();

        ScrollPane scrollPane = new ScrollPane();
        scrollPane.setContent(grid);
        scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);

        //footer
        HBox footer = new HBox();
        footer.setPadding(new Insets(15, 12, 15, 12));
        footer.setSpacing(10);
        Button clearCache = new Button("clear cache");
        footer.getChildren().add(clearCache);

        //events
        clearCache.setOnAction((event) -> {
            try {
                Database.delAllCache();
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Clear Cache Error");
                error.setHeaderText(e.getMessage());
                error.showAndWait();
                e.printStackTrace();
            }
        });

        //events
        addCurBtn.setOnAction((event) -> {
            AddCurWindow  addCurWindow= new AddCurWindow(infoList);
            addCurWindow.show(grid,  selectCur, convertCur, resetBtn,facade);
        });

        aboutBtn.setOnAction((event) -> {
            About.show();
        });


        convertBtn.setOnAction(event -> {
            if(selectedCur == 0 || compareCur == 0  ){
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Currency Selection incomplete ");
                error.setHeaderText("missing selected currencies, \n make sure two currencies have been selected ");
                error.showAndWait();
                return;
            }
            ConvertResultsWindow.show(    selectedCur, compareCur,facade, balance ,balanceText );
        });

        clearBtn.setOnAction((event) -> {
            try {
                clearCrypto(grid);
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("Clear Error");
                error.setHeaderText(e.getMessage());
                error.showAndWait();
                e.printStackTrace();
            }
        });


        //BorderPane
        BorderPane border = new BorderPane();
        border.setTop(header);
        border.setCenter(scrollPane);
        border.setBottom(footer);

        //scene
        Scene scene = new Scene(border,1500,900);
        return scene;



    }



    public static void clearCrypto(GridPane grid) throws Exception {
        infoList.delAllCurrency();
        grid.getChildren().clear();
    }

    public static void infoListDisp(GridPane grid, int offset, Text selectCur, Text convertCur , Button resetBtn) throws Exception {
        List<Info> infoList = Home.infoList.getAllCurrency();
        grid.getChildren().clear();

        int i = 0;
        for (Info info: infoList) {
            ImageView logo = new ImageView(new Image(info.getLogo())) ;
            logo.setFitWidth(20);
            logo.setFitHeight(20);
            logo.setPreserveRatio(true);

            Button selectBtn = new Button("select");
            Button rmvBtn = new Button("Remove");

            Text name = new Text("name : " + info.getName() );
            Text symbol = new Text("symbol :" + info.getSymbol() );
            Text description = new Text("description : " + info.getDescription() );
            Text launched = new Text("date launched : " + info.getDate_launched() );
            Hyperlink website = new Hyperlink("website : " + info.getUrls().getWebsite()[0]);

            name.setWrappingWidth(100);
            symbol.setWrappingWidth(100);
            launched.setWrappingWidth(200);
            description.setWrappingWidth(400);

            grid.add(selectBtn, 0,offset + i);
            grid.add(rmvBtn, 1,offset + i);
            grid.add(logo,2 , offset + i);
            grid.add(name,3 , offset + i);
            grid.add(symbol,4 , offset + i);
            grid.add(description,5 , offset + i);
            grid.add(launched,6 , offset + i);
            grid.add(website,7 , offset + i);

            rmvBtn.setOnAction(event -> {
                try {
                    Home.infoList.delCurrency( (int) Float.parseFloat(info.getId()));
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Remove Error");
                    error.setHeaderText(e.getMessage());
                    error.showAndWait();
                    e.printStackTrace();
                }
                grid.getChildren().clear();
                try {
                    infoListDisp(grid,1,  selectCur, convertCur, resetBtn);
                } catch (Exception e) {
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Remove Display Error");
                    error.setHeaderText(e.getMessage());
                    error.showAndWait();
                    e.printStackTrace();
                }
            });

            selectBtn.setOnAction(event -> {
                if(selectedCur != 0 && compareCur != 0  ){
                    Alert error = new Alert(Alert.AlertType.ERROR);
                    error.setTitle("Two Currencies Selected ");
                    error.setHeaderText("already selected two currencies \n click reset selection to change currencies ");
                    error.showAndWait();
                    return;
                }
                if(selectedCur == 0 ){
                    selectedCur = (int) Float.parseFloat(info.getId());
                    selectCur.setText(info.getName());
                }else{
                    compareCur = (int) Float.parseFloat(info.getId());
                    convertCur.setText(info.getName());
                }
            });

            resetBtn.setOnAction(event -> {
                selectedCur = 0;
                compareCur = 0;
                selectCur.setText("             ");
                convertCur.setText("             ");
            });


            website.setOnAction(e -> {
                HostServices services = MainApp.getMainHostServices();
                services.showDocument(info.getUrls().getWebsite()[0]);
            });

            i++;
        }

    }


    //https://docs.oracle.com/javase/8/javafx/api/javafx/scene/control/Dialog.html
    public static float alertEnterBalance(){
        AtomicReference<Float> val = new AtomicReference<>((float) 0);
        TextInputDialog  alert = new TextInputDialog();
        alert.setTitle("Enter  balance");

        alert.setHeaderText("Enter  balance");
        alert.getDialogPane().lookupButton(ButtonType.CANCEL).setVisible(false);


        final Button btnOk = (Button) alert.getDialogPane().lookupButton(ButtonType.OK);
        btnOk.addEventFilter(ActionEvent.ACTION, event -> {
            try{
                val.set(Float.parseFloat(alert.getEditor().getText()));
            } catch (Exception e) {
                Alert error = new Alert(Alert.AlertType.ERROR);
                error.setTitle("number entered is invalid");
                error.setHeaderText(e.getMessage());
                error.showAndWait();
                e.printStackTrace();
                event.consume();
            }
        });
        alert.showAndWait();
        return val.get();
    }

}
