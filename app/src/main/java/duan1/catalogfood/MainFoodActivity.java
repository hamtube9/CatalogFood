package duan1.catalogfood;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duan1.catalogfood.adapter.MainFoodAdapter;
import duan1.catalogfood.database.DatabaseHelper;
import duan1.catalogfood.model.MainFood;
import duan1.catalogfood.model.MainFoodDAO;

public class MainFoodActivity extends AppCompatActivity {
    private Toolbar toolbarMF;
    private ListView lv;
    private MainFoodAdapter mainFoodAdapter;
    private List<MainFood> mainFoodList;
    private MainFoodDAO mainFoodDAO;
    private ImageView backMF;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_food);
       toolbarMF = findViewById(R.id.toolbarMainFood);
        setSupportActionBar(toolbarMF);
        backMF=findViewById(R.id.imgbtnBackMF);
        lv=findViewById(R.id.lvMainFood);

        mainFoodDAO=new MainFoodDAO(MainFoodActivity.this);

        try{
            mainFoodList=mainFoodDAO.getMainFood();
            mainFoodAdapter=new MainFoodAdapter(this,mainFoodList);
        }catch (Exception e){
            e.printStackTrace();
        }
        lv.setAdapter(mainFoodAdapter);

        backMF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainFoodActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(MainFoodActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(MainFoodActivity.this, ChangeInfoMainFoodActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        mainFoodDAO.deleteMainFood(mainFoodList.get(position).getName());
                        mainFoodList.remove(position);
                        mainFoodAdapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });
    }





    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.action_add:
                Intent intent=new Intent(MainFoodActivity.this,AddMainFoodActivity.class);
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
        mainFoodList.clear();
        mainFoodList=mainFoodDAO.getMainFood();
        mainFoodAdapter.changeDataset(mainFoodList);
    }
}
