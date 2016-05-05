package br.com.interaje.productmanager.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import br.com.interaje.productmanager.R;
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
        if (item.getId() > 0) {
            product.setId(item.getId());
        }
        product.setName(productName.getText().toString());
        Double price = Double.parseDouble(productPrice.getText().toString());
        product.setPrice(price);

        Category category = new Category();
        category.setName(item.getCategory().getName());

        Long idCategory;

        if (item.getCategory() != null) {
            category.setId(item.getCategory().getId());
        }

        idCategory = category.save();

        category.setId(idCategory);
        product.setCategory(category);

        // Salvando o Produto no banco
        Long idProduct = product.save();

        if (idCategory > 0 && idProduct > 0) {
            Toast.makeText(this, R.string.success_register, Toast.LENGTH_SHORT).show();
            startActivity(new Intent(this, MainActivity.class));
            finish();
        } else {
            Toast.makeText(this, R.string.fail_register, Toast.LENGTH_SHORT).show();
        }
    }

}




