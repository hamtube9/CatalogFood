package duan1.catalogfood;

import android.app.SearchManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

import duan1.catalogfood.adapter.FastFoodAdapter;
import duan1.catalogfood.adapter.MainFoodAdapter;
import duan1.catalogfood.database.DatabaseHelper;
import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.FastFoodDAO;


public class FastFoodActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ListView lv;
    private ImageView backFF;
    private FastFoodDAO fastFoodDAO;
    private FastFoodAdapter adapter;
    private List<FastFood> fastFoodList;
    private SearchView searchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fast_food);
        toolbar = findViewById(R.id.toolbarFF);
        lv = findViewById(R.id.lvFastFood);
        backFF = findViewById(R.id.imgbtnBackFF);
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
                Intent intent = new Intent(FastFoodActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                AlertDialog.Builder builder = new AlertDialog.Builder(FastFoodActivity.this);
                builder.setTitle(" Bạn muốn sửa hay xóa");
                builder.setPositiveButton("Sửa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(FastFoodActivity.this, ChangeInfoFastFoodActivity.class);
                        startActivity(intent);


                    }
                });

                builder.setNegativeButton("Xóa", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                        fastFoodDAO.deleteFastFood(fastFoodList.get(position).getName());
                        fastFoodList.remove(position);
                        adapter.notifyDataSetChanged();


                    }
                });
                builder.show();
            }
        });
    }


        @Override
        public boolean onCreateOptionsMenu (Menu menu){
            getMenuInflater().inflate(R.menu.main, menu);

            SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
            searchView = (SearchView) menu.findItem(R.id.action_search)
                    .getActionView();
            searchView.setSearchableInfo(searchManager
                    .getSearchableInfo(getComponentName()));
            searchView.setMaxWidth(Integer.MAX_VALUE);

            searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
                @Override
                public boolean onQueryTextSubmit(String s) {
                    adapter.getFilter().filter(s);
                    return false;
                }

                @Override
                public boolean onQueryTextChange(String s) {
                    adapter.getFilter().filter(s);
                    return false;
                }
            });
            return true;
        }

    @Override
    public void onBackPressed() {
        if (!searchView.isIconified()) {
            searchView.setIconified(true);
            return;
        }
        super.onBackPressed();
    }


    @Override
        public boolean onOptionsItemSelected (MenuItem item){
            switch (item.getItemId()) {


                case R.id.action_add:
                    Intent intent = new Intent(FastFoodActivity.this, AddFastFoodActivity.class);
                    startActivity(intent);
            }
            return super.onOptionsItemSelected(item);
        }

        @Override
        protected void onActivityResult ( int requestCode, int resultCode, @Nullable Intent data){
            super.onActivityResult(requestCode, resultCode, data);
        }

        @Override
        protected void onResume () {
            super.onResume();
            fastFoodList.clear();
            fastFoodList = fastFoodDAO.getFastFood();
            adapter.changeDataset(fastFoodList);
        }



    }

