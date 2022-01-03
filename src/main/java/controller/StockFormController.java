package controller;

import bo.BOFactory;
import bo.SuperBO;
import bo.custom.StockBO;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import dtm.ProductTM;
import dtm.StockTM;
import dto.ProductDTO;
import dto.StockDTO;
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
import javafx.scene.text.Text;
import validation.ValidationUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.regex.Pattern;

public class StockFormController {
   private final StockBO stockBO = (StockBO) BOFactory.getBoFactory().getBO(BOFactory.BOTypes.STOCK);
    private final ObservableList<StockTM> stockTMS = FXCollections.observableArrayList();
 public AnchorPane context;
    public Text lblUnitPrice;
    public JFXButton btnRemove;
    public JFXButton btnAdd;
    public JFXButton btnUpdate;
    @FXML
    private TableView<StockTM> tblViewStock;

    @FXML
    private JFXTextField txtSearchBar;

    @FXML
    private TextField txtQuantityOnHand;

    @FXML
    private Text lblProductID;

    @FXML
    private Text lblProductName;

    @FXML
    private Text lblProductCategory;

    @FXML
    private Text lblProductManufacturer;


    @FXML
    void addBtnFunction(MouseEvent event) {
        Alert alert = null;
        if (validate()&&!(lblProductID.getText().equals(""))){
            try {
                StockDTO stockDTO = new StockDTO(lblProductID.getText(),Integer.valueOf(txtQuantityOnHand.getText()));
                boolean conformation = stockBO.addProductToStock(stockDTO);
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Added", ButtonType.CLOSE);
                    stockTMS.add(new StockTM(lblProductID.getText(),lblProductName.getText(),lblProductCategory.getText(),lblProductManufacturer.getText(),Double.valueOf(lblUnitPrice.getText()),Integer.valueOf(txtQuantityOnHand.getText())));
                }else {  alert=new Alert(Alert.AlertType.ERROR,"Something went wrong", ButtonType.OK);}
            } catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearFields();
            txtSearchBar.clear();
        }else{
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    void deleteBtnFunction(MouseEvent event) {
        Alert alert = null;
        if (!(lblProductID.getText().equals(""))){
            try {
                boolean conformation = stockBO.removeProductFromStock(lblProductID.getText());
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Removed", ButtonType.CLOSE);
                    stockTMS.removeIf(stockTM -> stockTM.getProductID().equals(lblProductID.getText()));
                }else { alert=new Alert(Alert.AlertType.ERROR,"Something went wrong", ButtonType.OK);}
            } catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearFields();
            txtSearchBar.clear();
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Select the Product to remove", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }

    @FXML
    void searchBtnFunction(MouseEvent event) {
     Alert alert = null;
     if (!txtSearchBar.getText().isEmpty()){
         try {
             ProductDTO productDTO = stockBO.searchStockByIDName(txtSearchBar.getText());
             if (productDTO==null){
                 alert=new Alert(Alert.AlertType.ERROR,"Name Or ID is Wrong", ButtonType.OK);
                 clearFields();
                 alert.initOwner(context.getScene().getWindow());
                 alert.show();
             }else{
                 lblProductID.setText(productDTO.getProductID());
                 lblProductName.setText(productDTO.getProductName());
                 lblProductCategory.setText(productDTO.getCategoryName());
                 lblProductManufacturer.setText(productDTO.getManufacturer());
                 lblUnitPrice.setText(String.valueOf(productDTO.getUnitPrice()));
                 txtQuantityOnHand.setText(String.valueOf(productDTO.getQuantityOnHand()));
                 if (productDTO.getQuantityOnHand()==0){
                     btnAdd.setDisable(false);
                     btnUpdate.setDisable(true);
                     btnRemove.setDisable(true);
                 }else {
                     btnAdd.setDisable(true);
                     btnUpdate.setDisable(false);
                     btnRemove.setDisable(false);
                 }

             }
         } catch (SQLException | ClassNotFoundException e) {
             alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
             alert.initOwner(context.getScene().getWindow());
             alert.show();
         }
     }else {
         alert=new Alert(Alert.AlertType.ERROR,"Please Type Product ID Or Name ", ButtonType.OK);
         alert.initOwner(context.getScene().getWindow());
         alert.show();
     }

    }

    @FXML
    void updateBtnFunction(MouseEvent event) {
        Alert alert = null;
        if (validate()&&!(lblProductID.getText().equals(""))){
            try {
                StockDTO stockDTO = new StockDTO(lblProductID.getText(),Integer.valueOf(txtQuantityOnHand.getText()));
                boolean conformation = stockBO.UpdateProductInStock(stockDTO);
                if (conformation){
                    alert=new Alert(Alert.AlertType.CONFIRMATION,"Successfully Updated ", ButtonType.CLOSE);
                    stockTMS.removeIf(stockTM -> stockTM.getProductID().equals(lblProductID.getText()));
                    stockTMS.add(new StockTM(lblProductID.getText(),lblProductName.getText(),lblProductCategory.getText(),lblProductManufacturer.getText(),Double.valueOf(lblUnitPrice.getText()),Integer.valueOf(txtQuantityOnHand.getText())));
                }else {  alert=new Alert(Alert.AlertType.ERROR,"Something went wrong", ButtonType.OK);}
            } catch (SQLException | ClassNotFoundException e) {
                alert=new Alert(Alert.AlertType.ERROR,"Please Check Again\n("+e.getMessage()+")", ButtonType.OK);
            }
            clearFields();
            txtSearchBar.clear();
        }else {
            alert=new Alert(Alert.AlertType.ERROR,"Invalid Input Please Check Fields Again", ButtonType.OK);
        }
        alert.initOwner(context.getScene().getWindow());
        alert.show();

    }
    public void initialize(){
        loadDataToTable();
        setColumn();
        setDataFromTable();
    }

    private void setColumn(){
        tblViewStock.getColumns().get(0).setCellValueFactory(new PropertyValueFactory<>("productID"));
        tblViewStock.getColumns().get(1).setCellValueFactory(new PropertyValueFactory<>("productName"));
        tblViewStock.getColumns().get(2).setCellValueFactory(new PropertyValueFactory<>("categoryName"));
        tblViewStock.getColumns().get(3).setCellValueFactory(new PropertyValueFactory<>("manufacturer"));
        tblViewStock.getColumns().get(4).setCellValueFactory(new PropertyValueFactory<>("unitPrice"));
        tblViewStock.getColumns().get(5).setCellValueFactory(new PropertyValueFactory<>("quantityOnHand"));
    }
    private void loadDataToTable(){
        try {
            for (ProductDTO productDTO:stockBO.getAllStockDetail()
                 ) {
                stockTMS.add(new StockTM(productDTO.getProductID(),productDTO.getProductName(),productDTO.getCategoryName(),productDTO.getManufacturer(),productDTO.getUnitPrice(),productDTO.getQuantityOnHand()));
            }
            tblViewStock.setItems(stockTMS);
        } catch (SQLException | ClassNotFoundException e) { e.printStackTrace(); }
    }

    private  void setDataFromTable(){
        tblViewStock.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if(newValue!=null){
                lblProductID.setText(newValue.getProductID());
                lblProductName.setText(newValue.getProductName());
                lblProductCategory.setText(newValue.getCategoryName());
                lblProductManufacturer.setText(newValue.getManufacturer());
                lblUnitPrice.setText(String.valueOf(newValue.getUnitPrice()));
                txtQuantityOnHand.setText(String.valueOf(newValue.getQuantityOnHand()));
                btnAdd.setDisable(true);
                btnUpdate.setDisable(false);
                btnRemove.setDisable(false);
            }
        });
    }



    private void clearFields(){
        lblProductID.setText("");
        lblProductName.setText("");
        lblProductCategory.setText("");
        lblProductManufacturer.setText("");
        lblUnitPrice.setText("");
        txtQuantityOnHand.clear();
        tblViewStock.getSelectionModel().clearSelection();
    }

    private boolean validate(){
        LinkedHashMap<TextField, Pattern> map = new LinkedHashMap<>();
        map.put(txtQuantityOnHand,Pattern.compile("^[0-9]+$"));
        return ValidationUtil.Validate(map);
    }
}
