package controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;
import javafx.util.Duration;

import java.io.IOException;
import java.time.format.DateTimeFormatter;

public class HomeFormController {


    public AnchorPane loadingContext;
    public  Text lblUser;
    public static String userName;
    public void initialize(){
        lblUser.setText(userName);
    }
    public void moveToCategory(MouseEvent mouseEvent) throws IOException {
        loadingContext.getChildren().clear();
        loadingContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/categoriesForm.fxml")));
    }

    public void moveToProductManagement(MouseEvent mouseEvent) throws IOException {
        loadingContext.getChildren().clear();
        loadingContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/productManagementForm.fxml")));
    }

    public void returnToHome(MouseEvent mouseEvent) throws IOException {
        Stage stage =(Stage) loadingContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/homeForm.fxml"))));
    }

    public void logout_function(MouseEvent mouseEvent) throws IOException {
        Stage stage =(Stage) loadingContext.getScene().getWindow();
        stage.setScene(new Scene(FXMLLoader.load(getClass().getResource("../view/loginForm.fxml"))));
    }

    public void moveToStocks(MouseEvent mouseEvent) throws IOException {
        loadingContext.getChildren().clear();
        loadingContext.getChildren().add(FXMLLoader.load(getClass().getResource("../view/stockForm.fxml")));
    }
    public  void setUser(String user){
        userName=user;
        lblUser.setText(user);
    }
}
