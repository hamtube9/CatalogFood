package duan1.catalogfood;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ImageView;

public class SearchActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private ImageView imgSearchBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        setContentView(R.layout.activity_search);
        imgSearchBack=findViewById(R.id.imgSearchBack);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);


        imgSearchBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(SearchActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });

    }
}
