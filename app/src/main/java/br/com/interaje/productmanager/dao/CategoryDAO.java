package br.com.interaje.productmanager.dao;

import java.util.List;

import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.models.Category;

/**
 * Created by rayquaza on 11/05/16.
 */
public interface CategoryDAO {

    Long save(Category category, Database database);

    void update(Category category, Database database);

    List<Category> listAll(Database database);

    void delete(Long id, Database database);

    Category findById(Long id, Database database);
}
