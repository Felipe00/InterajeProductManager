package br.com.interaje.productmanager.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.productmanager.R;
import br.com.interaje.productmanager.dao.CategoryDAO;
import br.com.interaje.productmanager.dao.ProductDAO;
import br.com.interaje.productmanager.dao.impl.CategoryDaoImpl;
import br.com.interaje.productmanager.dao.impl.ProductDAOImpl;
import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.databases.ProductManagerDatabaseHelper;
import br.com.interaje.productmanager.models.Category;
import br.com.interaje.productmanager.models.Product;

public class InsertActivity extends AppCompatActivity {

    EditText productName, productPrice, categoryName;
    private Product item;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insert);

        productName = (EditText) findViewById(R.id.productName);
        productPrice = (EditText) findViewById(R.id.productPrice);
        categoryName = (EditText) findViewById(R.id.productCategory);

        setProductValues();


        // Testando o SharedPreferences

        SharedPreferences preferences = this.getSharedPreferences("product", MODE_PRIVATE);
        String endText = preferences.getString("thats_all_guys", "Valor padrão");

        // Descomente para aparecer um texto.
        // Toast.makeText(this, endText, Toast.LENGTH_LONG).show();

        // Fim

    }

    private void setProductValues() {
        if (getIntent().hasExtra("product")) {
            item = (Product) getIntent().getSerializableExtra("product");
            productName.setText(item.getName());
            productPrice.setText(String.valueOf(item.getPrice()));
            categoryName.setText(item.getCategory().getName());
        }
    }

    public void insertProduct(View v) {
        //TODO colocar caixa de texto perguntando se quer realmente cadastrar (?)

        Product product = new Product();
        if (item != null && item.getmId() != null) {
            product.setId(item.getmId());
        }
        product.setName(productName.getText().toString());
        Double price = Double.parseDouble(productPrice.getText().toString());
        product.setPrice(price);

        Category category = new Category();
        Long idCategory;
        if (item != null && item.getCategory() != null) {
            category.setId(item.getCategory().getmId());
        }

        category.setName(categoryName.getText().toString());

        idCategory = category.save();

        category.setmId(idCategory);
        product.setCategory(category);

        // Salvando o Produto no banco
        Long idProduct = product.save();
        product.setmId(idProduct);

        if (idCategory > 0 && idProduct > 0) {
            Toast.makeText(this, R.string.success_register, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, R.string.fail_register, Toast.LENGTH_SHORT).show();
        }
    }

    public void insertProductWithCursor(View v) {
        Product product = new Product();
        product.setName(productName.getText().toString());
        Double price = Double.parseDouble(productPrice.getText().toString());
        product.setPrice(price);

        Category category = new Category();
        category.setName(categoryName.getText().toString());

        Database categoryDatabase = new Database(new ProductManagerDatabaseHelper(this));
        CategoryDAO categoryDAO = new CategoryDaoImpl();
        Long idCategory = categoryDAO.save(category, categoryDatabase);

        category.setmId(idCategory);
        product.setCategory(category);

        Database productDatabase = new Database(new ProductManagerDatabaseHelper(this));
        ProductDAO productDAO = new ProductDAOImpl();
        productDAO.save(product, productDatabase);

        startActivity(new Intent(this, MainActivity.class));
        finish();
    }

}




