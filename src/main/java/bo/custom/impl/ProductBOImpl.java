package bo.custom.impl;

import bo.custom.ProductBO;
import dao.DAOFactory;
import dao.custom.CategoryDAO;
import dao.custom.ProductDAO;
import dao.custom.QueryDAO;
import dto.CategoryDTO;
import dto.ProductDTO;
import entity.Category;
import entity.Product;

import java.sql.SQLException;
import java.util.ArrayList;

public class ProductBOImpl implements ProductBO {
    ProductDAO productDAO = (ProductDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.PRODUCT);
    CategoryDAO categoryDAO= (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CATEGORY);
    QueryDAO queryDAO = (QueryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.Query);

    @Override
    public boolean addProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
      return productDAO.add(new Product(productDTO.getProductID(),productDTO.getProductName(),productDTO.getUnitPrice(),productDTO.getManufacturer(),productDTO.getCategoryCode()));
    }

    @Override
    public boolean updateProduct(ProductDTO productDTO) throws SQLException, ClassNotFoundException {
        return productDAO.update(new Product(productDTO.getProductID(),productDTO.getProductName(),productDTO.getUnitPrice(),productDTO.getManufacturer(),productDTO.getCategoryCode()));
    }

    @Override
    public boolean deleteProduct(String ID) throws SQLException, ClassNotFoundException {
        return productDAO.delete(ID);
    }

    @Override
    public ArrayList<ProductDTO> getAllProduct() throws SQLException, ClassNotFoundException {
       ArrayList<ProductDTO> productDTOS=new ArrayList<>();
        for (Product product:productDAO.getAll()
             ) {
                productDTOS.add(new ProductDTO(product.getProductID(),product.getProductName(),product.getUnitPrice(),product.getManufacturer(),product.getCategoryCode()));
        }
        return productDTOS;
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException {
        ArrayList<CategoryDTO> categoryDTOS=new ArrayList<>();
        for (Category category:categoryDAO.getAll()
        ) {
           categoryDTOS.add(new CategoryDTO(category.getCategoryCode(),category.getCategoryName()));
        }
        return categoryDTOS;
    }

    @Override
    public ProductDTO SearchProduct(String ID, String name) throws SQLException, ClassNotFoundException {
      return queryDAO.searchProductDetail(ID,name);

    }

    @Override
    public String getCategoryName(String code) throws SQLException, ClassNotFoundException {
        return categoryDAO.searchByCode(code).getCategoryName();

    }

    @Override
    public String getCategoryCode(String name) throws SQLException, ClassNotFoundException {
        return categoryDAO.searchByName(name).getCategoryCode();
    }
}
