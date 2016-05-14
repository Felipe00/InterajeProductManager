package br.com.interaje.productmanager.dao;

import java.util.List;

import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.models.Category;
import br.com.interaje.productmanager.models.Product;

/**
 * Created by rayquaza on 06/05/16.
 */
public interface ProductDAO {

    void save(Product product, Database database);

    void update(Product product, Database database);

    List<Product> listAll(Database database);

    void delete(Long id, Database database);
}
