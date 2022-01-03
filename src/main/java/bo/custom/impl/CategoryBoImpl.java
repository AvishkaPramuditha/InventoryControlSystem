package bo.custom.impl;

import bo.custom.CategoryBO;
import dao.DAOFactory;
import dao.custom.CategoryDAO;
import dto.CategoryDTO;
import entity.Category;

import java.sql.SQLException;
import java.util.ArrayList;

public class CategoryBoImpl implements CategoryBO {
    CategoryDAO categoryDAO= (CategoryDAO) DAOFactory.getDaoFactory().getDAO(DAOFactory.DAOTypes.CATEGORY);

    @Override
    public boolean addCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
       return categoryDAO.add(new Category(categoryDTO.getCategoryCode(), categoryDTO.getCategoryName()));
    }

    @Override
    public boolean updateCategory(CategoryDTO categoryDTO) throws SQLException, ClassNotFoundException {
     return  categoryDAO.update(new Category(categoryDTO.getCategoryCode(), categoryDTO.getCategoryName()));
    }

    @Override
    public boolean deleteCategory(String code) throws SQLException, ClassNotFoundException {
       return categoryDAO.delete(code);
    }

    @Override
    public ArrayList<CategoryDTO> getAllCategory() throws SQLException, ClassNotFoundException {
        ArrayList<CategoryDTO> categoryDTOS = new ArrayList<>();
        for (Category category:categoryDAO.getAll()
             ) {
            categoryDTOS.add(new CategoryDTO(category.getCategoryCode(),category.getCategoryName()));
        }
        return categoryDTOS;
    }
}
