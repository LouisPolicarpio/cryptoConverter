package view.components;

public class Stage {
    public static javafx.stage.Stage
    create(String title){
        javafx.stage.Stage stage = new javafx.stage.Stage();
        stage.setWidth(1500);
        stage.setHeight(1000);
        stage.setTitle(title);
        return  stage;
    }
}
