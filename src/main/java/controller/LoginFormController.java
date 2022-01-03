package controller;

import bo.BOFactory;
import bo.SuperBO;
import bo.custom.LoginBO;
import com.jfoenix.controls.JFXPasswordField;

import com.jfoenix.controls.JFXTextField;

import javafx.fxml.FXMLLoader;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

import java.io.IOException;
import java.sql.SQLException;

public class LoginFormController {
    public AnchorPane loginContext;
    public JFXTextField txtUserName;
    public JFXPasswordField txtPassword;
    private final LoginBO loginBO = (LoginBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.LOGIN);

    public void loginBtnFunction(MouseEvent mouseEvent) throws IOException {
        Alert alert;
        try {
            String username = txtUserName.getText();
            String password = txtPassword.getText();
            if (loginBO.isItDefaultLogin(username,password)){
                alert=new Alert(Alert.AlertType.CONFIRMATION,"You Have logged Successfully With The Default Credentials", ButtonType.OK);
                alert.initOwner(loginContext.getScene().getWindow());
                alert.showAndWait();
                moveToHome(username);
            }else if (loginBO.checkLoginCredentials(username,password)){
                alert=new Alert(Alert.AlertType.CONFIRMATION,"You Have logged Successfully", ButtonType.OK);
                alert.initOwner(loginContext.getScene().getWindow());
                alert.showAndWait();
                moveToHome(username);
            }else {
                alert=new Alert(Alert.AlertType.CONFIRMATION,"InValid UserName Or Password Please Check Again", ButtonType.OK);
                alert.initOwner(loginContext.getScene().getWindow());
                alert.showAndWait();
            }
        }catch (SQLException | ClassNotFoundException e) {
            alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            alert.initOwner(loginContext.getScene().getWindow());
            alert.showAndWait();
        }

    }
    private void  moveToHome(String userName) throws IOException {
        loginContext.getChildren().clear();
        loginContext.getChildren().add(loginBO.setUser(userName));
    }

}
