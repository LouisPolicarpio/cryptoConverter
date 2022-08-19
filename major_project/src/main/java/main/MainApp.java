package main;

import model.API.ModelFacade;
import model.DB.Database;
import javafx.application.Application;
import javafx.stage.Stage;
import model.cryptoInfo.InfoList;
import view.windows.Home;
import javafx.application.HostServices;


public class MainApp extends Application {
    private static HostServices hostServices ;



    public static HostServices getMainHostServices() {
        return hostServices ;
    }


    public static void main(String[] args){
        launch(args);
    }

    @Override
    public void start(Stage primaryStage){
        hostServices = getHostServices();

        //set up database
        Database.createCacheDB();
        Database.setupDB();

        InfoList infoDAO = new InfoList();


        //get run mode (gradle run --args="online online")
       // ControllerFacade facade = new ControllerFacade(getParameters().getRaw().get(0), getParameters().getRaw().get(1));
        ModelFacade facade = new ModelFacade(getParameters().getRaw().get(0), getParameters().getRaw().get(1));


        //stage
        primaryStage.setTitle("Homepage");
        Home home = new Home(); 
        home.setInfo(infoDAO);
        primaryStage.setScene(Home.getScene(facade));
        primaryStage.show();

    }


}
