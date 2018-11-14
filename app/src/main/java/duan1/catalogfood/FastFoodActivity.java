package duan1.catalogfood;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duan1.catalogfood.adapter.FastFoodAdapter;
import duan1.catalogfood.adapter.MainFoodAdapter;
import duan1.catalogfood.database.DatabaseHelper;
import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.FastFoodDAO;
import duan1.catalogfood.model.MainFood;
import duan1.catalogfood.model.MainFoodDAO;

public class FastFoodActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lv;
    private ImageView backFF;
    private FastFoodDAO fastFoodDAO;
    private FastFoodAdapter adapter;
    private List<FastFood> fastFoodList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_food);
        toolbar = findViewById(R.id.toolbarFF);
        lv = findViewById(R.id.lvFastFood);
        backFF=findViewById(R.id.imgbtnBackFF);
        setSupportActionBar(toolbar);

        fastFoodDAO = new FastFoodDAO(FastFoodActivity.this);
        try {
            fastFoodList = fastFoodDAO.getFastFood();
            adapter = new FastFoodAdapter(this, fastFoodList);
        } catch (Exception e) {
            e.printStackTrace();
        }
        lv.setAdapter(adapter);

        backFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(FastFoodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add:
                Intent intent = new Intent(FastFoodActivity.this, AddFastFoodActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
    }

    @Override
    protected void onResume() {
        super.onResume();
        fastFoodList.clear();
        fastFoodList = fastFoodDAO.getFastFood();
        adapter.changeDataset(fastFoodList);
    }

}
