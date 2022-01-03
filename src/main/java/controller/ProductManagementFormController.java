package controller;

import bo.BOFactory;
import bo.custom.ProductBO;
import com.jfoenix.controls.JFXButton;
import dtm.ProductTM;
import dto.CategoryDTO;
import dto.ProductDTO;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import validation.ValidationUtil;

import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class ProductManagementFormController {
    private final ProductBO productBO = (ProductBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.PRODUCT);
    private final ObservableList<ProductTM> productTMs = FXCollections.observableArrayList();
    @FXML
    private JFXButton btnSearch;
    @FXML
    private JFXButton btnDelete;
    @FXML
    private JFXButton btnAdd;
    @FXML
    private JFXButton btnUpdate;

    @FXML
    private AnchorPane context;

    @FXML
    private TextField txtProductID;

    @FXML
    private TextField txtProductName;

    @FXML
    private ComboBox<CategoryDTO> cmbCategory;

    @FXML
    private TextField txtUnitPrice;

    @FXML
    private TextField txtManufacturer;

    @FXML
    private TableView<ProductTM> tblViewProduct;

    @FXML
    void addProductFunction(MouseEvent event) {
        Alert alert = null;
        if (validate()&&!(cmbCategory.getSelectionModel().getSelectedItem()==null)){
            try {
                ProductDTO productDTO = new ProductDTO(txtProductID.getText(), txtProductName.getText(), Double.valueOf(txtUnitPrice.getText()), txtManufacturer.getText(), cmbCategory.getValue().getCategoryCode());
                boolean conformation = productBO.addProduct(productDTO);
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added", ButtonType.CLOSE);
                    productTMs.add(new ProductTM(productDTO.getProductID(),productDTO.getProductName(),productDTO.getUnitPrice(),productDTO.getManufacturer(),cmbCategory.getValue().getCategoryName()));
                }
            }catch (SQLException | ClassNotFoundException e){
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearTextFieldsFunction(null);
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input or selection Please Check Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    void clearTextFieldsFunction(MouseEvent event) {
        txtProductID.clear();
        txtProductName.clear();
        txtUnitPrice.clear();
        txtManufacturer.clear();
        cmbCategory.setValue(null);
        tblViewProduct.getSelectionModel().clearSelection();
        btnDelete.setDisable(true);
        btnUpdate.setDisable(true);
        btnAdd.setDisable(false);
    }

    @FXML
    void deleteProductFunction(MouseEvent event) {
        Alert alert = null;
        if (validate()&&!(cmbCategory.getSelectionModel().getSelectedItem()==null)){
            try {
                boolean conformation = productBO.deleteProduct(txtProductID.getText());
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Deleted", ButtonType.CLOSE);
                    productTMs.removeIf(productTM -> productTM.getProductID().equals(txtProductID.getText()));
                }
            }catch (SQLException | ClassNotFoundException e){
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearTextFieldsFunction(null);
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    void searchProductByIDNameFunction(MouseEvent event) {

        Alert alert = null;
        if (!txtProductID.getText().isEmpty()&&txtProductName.getText().isEmpty()){
            try {
                ProductDTO productDetail = productBO.SearchProduct(txtProductID.getText(), txtProductName.getText());
                if (productDetail==null){
                    alert=new Alert(Alert.AlertType.ERROR,"Name Or ID is Wrong", ButtonType.OK);
                    alert.initOwner(context.getScene().getWindow());
                    alert.show();
                }else{
                    txtProductID.setText(productDetail.getProductID());
                    txtProductName.setText(productDetail.getProductName());
                    txtUnitPrice.setText(String.valueOf(productDetail.getUnitPrice()));
                    txtManufacturer.setText(productDetail.getManufacturer());
                    cmbCategory.setValue(new CategoryDTO(productDetail.getCategoryCode(),productDetail.getProductName()));
                    tblViewProduct.getSelectionModel().clearSelection();
                    btnDelete.setDisable(false);
                    btnUpdate.setDisable(false);
                    btnAdd.setDisable(true);
                }
            }catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
                alert.initOwner(context.getScene().getWindow());
                alert.show();
            }
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"please Enter The ID OR Name", ButtonType.OK);
            alert.initOwner(context.getScene().getWindow());
            alert.show();
        }
    }

    @FXML
    void updateProductFunction(MouseEvent event) {
        Alert alert = null;
        if (validate()&&!(cmbCategory.getSelectionModel().getSelectedItem()==null)){
            try {
                ProductTM selectedItem = tblViewProduct.getSelectionModel().getSelectedItem();
                ProductDTO productDTO = new ProductDTO(txtProductID.getText(), txtProductName.getText(), Double.valueOf(txtUnitPrice.getText()), txtManufacturer.getText(), cmbCategory.getValue().getCategoryCode());
                boolean conformation = productBO.updateProduct(productDTO);
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated", ButtonType.CLOSE);
                    productTMs.removeIf(productTM -> productTM.getProductID().equals(txtProductID.getText()));
                    tblViewProduct.getItems().add(new ProductTM(productDTO.getProductID(),productDTO.getProductName(),productDTO.getUnitPrice(),productDTO.getManufacturer(),cmbCategory.getValue().getCategoryName()));
                }
            }catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearTextFieldsFunction(null);
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    public void initialize(){
        loadCategoryCombo();
        loadDateToTable();
        setColumn();
        setValuesToField();
    }

    private void loadCategoryCombo(){
        try {
            cmbCategory.setItems(FXCollections.observableArrayList(productBO.getAllCategory()));
        } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    private void loadDateToTable(){
        try {
            for (ProductDTO productDTO:productBO.getAllProduct()
            ) {
                productTMs.add(new ProductTM(productDTO.getProductID(),productDTO.getProductName(),productDTO.getUnitPrice(),productDTO.getManufacturer(),productBO.getCategoryName(productDTO.getCategoryCode())));
            }
            tblViewProduct.setItems(productTMs);
        }catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    private void setColumn(){
        tblViewProduct.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblViewProduct.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblViewProduct.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblViewProduct.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblViewProduct.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
    }
    private void setValuesToField(){
       tblViewProduct.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue!=null){
                txtProductID.setText(newValue.getProductID());
                txtProductName.setText(newValue.getProductName());
                txtUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtManufacturer.setText(newValue.getManufacturer());
                try {
                    cmbCategory.setValue(new CategoryDTO(productBO.getCategoryCode(newValue.getCategoryName()),newValue.getCategoryName()));
                } catch (SQLException | ClassNotFoundException e) {
                    e.printStackTrace();
                }
                btnDelete.setDisable(false);
                btnUpdate.setDisable(false);
                btnAdd.setDisable(true);
            }
        });
    }
    private boolean validate(){
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
        map.put(txtProductID,Pattern.compile("^[A-z0-9-/]{2,20}$"));
        map.put(txtProductName,Pattern.compile("^(?!\\s)[A-z0-9 ]{2,30}$"));
        map.put(txtUnitPrice,Pattern.compile("^[1-9][0-9]*[.]*[0-9]{0,2}$"));
        map.put(txtManufacturer,Pattern.compile("^(?!\\s)[A-z ]{2,30}$"));
        return ValidationUtil.Validate(map);
    }
}
