package duan1.catalogfood;

import android.app.SearchManager;
import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ListView;

import duan1.catalogfood.adapter.FastFoodAdapter;
import duan1.catalogfood.database.DatabaseHelper;
import duan1.catalogfood.model.FastFoodDAO;

public class SearchActivity extends AppCompatActivity {
       private Toolbar toolBarSearch;
      private FastFoodDAO fastFoodDAO;
       private FastFoodAdapter fastFoodAdapter;

    private SearchView searchView;
    private ListView lvfind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        innitView();
        setSupportActionBar(toolBarSearch);
        toolBarSearch.setTitle("Search");
        toolBarSearch.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolBarSearch.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });






    }

    private void innitView() {

        toolBarSearch=findViewById(R.id.toolBarSearch);
        searchView=findViewById(R.id.searchView);
        lvfind=findViewById(R.id.lvFind);
        fastFoodDAO=new FastFoodDAO(SearchActivity.this);

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.search, menu);

        SearchManager searchManager = (SearchManager) getSystemService(Context.SEARCH_SERVICE);
        searchView = (SearchView) menu.findItem(R.id.action_search)
                .getActionView();
        searchView.setSearchableInfo(searchManager
                .getSearchableInfo(getComponentName()));
        searchView.setMaxWidth(Integer.MAX_VALUE);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String s) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String s) {
                return false;
            }
        });
        return true;
    }
}

