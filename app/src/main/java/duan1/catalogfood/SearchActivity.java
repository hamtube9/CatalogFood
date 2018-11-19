package duan1.catalogfood;

import android.app.SearchManager;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import android.widget.ListView;

public class SearchActivity extends AppCompatActivity {
        private Toolbar toolbar;
    private ImageView imgSearchBack,btnfind;
    private EditText edtfind;
    private ListView lvfind;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setSupportActionBar(toolbar);
        innitView();

        imgSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });



    }

    private void innitView() {
        imgSearchBack=findViewById(R.id.imgSearchBack);
        toolbar = findViewById(R.id.toolbar);
        btnfind=findViewById(R.id.btnFind);
        edtfind=findViewById(R.id.edtFind);
        lvfind=findViewById(R.id.lvFind);
    }

}

