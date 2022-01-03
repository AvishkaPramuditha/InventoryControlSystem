package controller;


import bo.BOFactory;
import bo.custom.CategoryBO;
import com.jfoenix.controls.JFXButton;
import dtm.CategoryTM;
import dto.CategoryDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import validation.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class CategoriesFormController {
    private final CategoryBO categoryBO= (CategoryBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.CATEGORY);
    private final ObservableList<CategoryTM> categoryTMS = FXCollections.observableArrayList();
    @FXML
    public JFXButton btnAdd;
    @FXML
    public JFXButton btnUpdate;
    @FXML
    public JFXButton btnDelete;
    @FXML
    private AnchorPane context;
    @FXML
    private TextField txtCategoryCode;

    @FXML
    private TextField txtCategoryName;

    @FXML
    private TableView<CategoryTM> tblViewCategory;

    public void initialize(){
        setColumn();
        loadDataToTable();
        setDataToField();
    }

    @FXML
    public void clearButtonOnAction(MouseEvent mouseEvent) {
        txtCategoryName.clear();
        txtCategoryCode.clear();
        tblViewCategory.getSelectionModel().clearSelection();
        txtCategoryCode.setEditable(true);
        btnAdd.setDisable(false);
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);

    }
    @FXML
    public void addButtonOnAction(MouseEvent mouseEvent) {
        Alert alert = null;
       if (validate()){
           try {
               CategoryDTO categoryDTO = new CategoryDTO(txtCategoryCode.getText(), txtCategoryName.getText());
               boolean conformation = categoryBO.addCategory(categoryDTO);
               if (conformation){
                   alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added", ButtonType.CLOSE);
                   tblViewCategory.getItems().add(new CategoryTM(categoryDTO.getCategoryCode(), categoryDTO.getCategoryName()));
               }
           } catch (SQLException | ClassNotFoundException e) {
               alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
           }
           clearButtonOnAction(null);
       }else {
           alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
       }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    public void updateButtonOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert alert = null;
        if (validate()){
            try{
                CategoryDTO categoryDTO = new CategoryDTO(txtCategoryCode.getText(), txtCategoryName.getText());
                boolean conformation = categoryBO.updateCategory(categoryDTO);
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated", ButtonType.CLOSE);
                    tblViewCategory.getItems().get(tblViewCategory.getSelectionModel().getSelectedIndex()).setCategoryName(categoryDTO.getCategoryName());
                    tblViewCategory.refresh();
                }
            }catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearButtonOnAction(null);
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    public void deleteButtonOnAction(MouseEvent mouseEvent) throws SQLException, ClassNotFoundException {
        Alert alert = null;
        if (validate()){
            try {

                CategoryTM categoryTM = tblViewCategory.getSelectionModel().getSelectedItem();
                boolean conformation = categoryBO.deleteCategory(categoryTM.getCategoryCode());
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Deleted", ButtonType.CLOSE);
                    tblViewCategory.getItems().remove(categoryTM);
                }
            }catch (SQLException | ClassNotFoundException e){
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearButtonOnAction(null);
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }

        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    private void setColumn(){
        tblViewCategory.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("categoryCode"));
        tblViewCategory.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
    }

    private void loadDataToTable(){
        try {
            for (CategoryDTO categoryDTO :categoryBO.getAllCategory()
                 ) {
                categoryTMS.add(new CategoryTM(categoryDTO.getCategoryCode(), categoryDTO.getCategoryName()));
            }
            tblViewCategory.setItems(categoryTMS);
        } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }

    }
   private  void setDataToField(){
        tblViewCategory.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                txtCategoryName.setText(newValue.getCategoryName());
                txtCategoryCode.setText(newValue.getCategoryCode());
                txtCategoryCode.setEditable(false);
                btnAdd.setDisable(true);
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
            }

        });
   }

   private boolean validate(){
       LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
       map.put(txtCategoryCode,Pattern.compile("^[A-z0-9-/]{2,10}$"));
       map.put(txtCategoryName,Pattern.compile("^(?!\\s)[A-z0-9 ]{2,30}$"));
       return ValidationUtil.Validate(map);
   }
}
