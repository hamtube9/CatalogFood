package duan1.catalogfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.FastFoodDAO;

public class ChangeInfoFastFoodActivity extends AppCompatActivity {
    private EditText edtChangeTenFastFood,edtChangeDiaChiFastFood,edtChangeGiaFastFood,edtChangeDTFastFood;
    private Button btnHuyFF,btnChangeFF;
    private ImageView imgChangeFastFood;
    private FastFoodDAO fastFoodDAO;
    private Toolbar toolbar;
    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info_fast_food);
        innitviews();
        setSupportActionBar(toolbar);
        toolbar.setTitle("Change Infomation");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangeInfoFastFoodActivity.this,FastFoodActivity.class);
                startActivity(intent);
            }
        });

        imgChangeFastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        btnHuyFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtChangeDiaChiFastFood.setText("");
                edtChangeDTFastFood.setText("");
                edtChangeGiaFastFood.setText("");
                edtChangeTenFastFood.setText("");
            }
        });

        btnChangeFF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name=edtChangeTenFastFood.toString().trim();
//                String dienthoai=edtChangeDTFastFood.toString().trim();
//                String gia=edtChangeGiaFastFood.toString().trim();
//                String diachi=edtChangeDiaChiFastFood.toString().trim();
                if(validate()>0){
                    if (fastFoodDAO.updateFastFood(edtChangeTenFastFood.getText().toString(),edtChangeDiaChiFastFood.getText().toString(),
                            edtChangeGiaFastFood.getText().toString(),edtChangeDTFastFood.getText().toString(),ImageViewChange(imgChangeFastFood))>0){
                        Toast.makeText(ChangeInfoFastFoodActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
            }
        });
    }

    private void innitviews() {
        edtChangeDiaChiFastFood=findViewById(R.id.edtChangeDiaChiFastFood);
        edtChangeDTFastFood=findViewById(R.id.edtChangeDTFastFood);
        edtChangeGiaFastFood=findViewById(R.id.edtChangeGiaFastFood);
        edtChangeTenFastFood=findViewById(R.id.edtChangeTenFastFood);
        btnChangeFF=findViewById(R.id.btnChangeFF);
        btnHuyFF=findViewById(R.id.btnHuyFF);
        imgChangeFastFood=findViewById(R.id.imgChangeFastFood);
        fastFoodDAO=new FastFoodDAO(getApplicationContext());
        toolbar=findViewById(R.id.toolbarFF);

    }
    private int validate() {
        int check = -1;
        if (edtChangeTenFastFood.getText().toString().equals("")) {
            edtChangeTenFastFood.setError(getString(R.string.empty));
            return check;
        } else if (edtChangeDiaChiFastFood.getText().toString().equals("")) {
            edtChangeDiaChiFastFood.setError(getString(R.string.empty));
            return check;
        } else if (edtChangeDTFastFood.getText().toString().length() != 10) {
            edtChangeDTFastFood.setError(getString(R.string.length));
            return check;
        } else if (Integer.parseInt(edtChangeGiaFastFood.getText().toString()) < 0) {
            edtChangeGiaFastFood.setError(getString(R.string.price));
            return check;
        }
        return 1;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        switch(requestCode) {
            case PICK_IMAGE:
                if(resultCode == RESULT_OK){
                    try {
                        final Uri imageUri = data.getData();
                        final InputStream imageStream = getContentResolver().openInputStream(imageUri);
                        final Bitmap selectedImage = BitmapFactory.decodeStream(imageStream);
                        imgChangeFastFood.setImageBitmap(selectedImage);
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    }

                }
        }
    }

    private byte[] ImageViewChange(ImageView imageView) {
        BitmapDrawable drawable = (BitmapDrawable) imageView.getDrawable();
        Bitmap bitmap = drawable.getBitmap();
        ByteArrayOutputStream stream = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.PNG, 100, stream);
        return stream.toByteArray();
    }
}
