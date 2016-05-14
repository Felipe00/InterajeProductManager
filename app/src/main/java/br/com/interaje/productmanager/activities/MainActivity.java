package br.com.interaje.productmanager.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import java.util.List;

import br.com.interaje.productmanager.R;
import br.com.interaje.productmanager.adapters.ProductAdapter;
import br.com.interaje.productmanager.dao.CategoryDAO;
import br.com.interaje.productmanager.dao.ProductDAO;
import br.com.interaje.productmanager.dao.impl.CategoryDaoImpl;
import br.com.interaje.productmanager.dao.impl.ProductDAOImpl;
import br.com.interaje.productmanager.databases.Database;
import br.com.interaje.productmanager.databases.ProductManagerDatabaseHelper;
import br.com.interaje.productmanager.models.Category;
import br.com.interaje.productmanager.models.Product;

public class MainActivity extends AppCompatActivity {

    private ListView productList;
    private List<Product> products;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        productList = (ListView) findViewById(R.id.productList);

        products = getProductList();

        ProductAdapter adapter = new ProductAdapter(this, products);

        productList.setAdapter(adapter);

        productList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Product item = products.get(position);

                // Aqui eu pego o [item.getId()] do sugarORM e coloco dentro
                // da nossa variável mId através do método [setmId(algumId)]
                // dessa forma, o getId do SugarOrm pode ser passado por meio do Intent.
                // item.setmId(item.getId());

                Intent intent = new Intent(MainActivity.this, InsertActivity.class);

                intent.putExtra("product", item);

                startActivity(intent);
                finish();

            }
        });

        // Testando o SharedPreferences
        SharedPreferences preferences =
                this.getSharedPreferences("product", MODE_PRIVATE);
        SharedPreferences.Editor editor = preferences.edit();
        editor.putString("thats_all_folks", "Isso é tudo pessoal");
        editor.commit();

        // Fim

    }

    public void newProduct(View v) {
        startActivity(new Intent(this, InsertActivity.class));
        finish();
    }

    private List<Product> getProductList() {
        // Recuperando dados do banco com SugarORM
        //List<Product> list = Product.listAll(Product.class);

        // Recuperando dados do banco com classes nativas do SQLite
        Database database = new Database(new ProductManagerDatabaseHelper(this));
        ProductDAO dao = new ProductDAOImpl();
        List<Product> list = dao.listAll(database);

        Database categoryDb = new Database(new ProductManagerDatabaseHelper(this));
        CategoryDAO categoryDAO = new CategoryDaoImpl();

        for (int i = 0; i < list.size(); i++) {
            Long idCategory = list.get(i).getCategory().getmId();
            Category category = categoryDAO.findById(idCategory, categoryDb);
            list.get(i).setCategory(category);
        }

        return list;
    }
}
