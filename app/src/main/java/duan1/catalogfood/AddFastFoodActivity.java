package duan1.catalogfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import duan1.catalogfood.model.FastFood;
import duan1.catalogfood.model.FastFoodDAO;

public class AddFastFoodActivity extends AppCompatActivity {
    private Button btnAddFastFood;
    private ImageView btnBackFast, imageFood;
    private EditText edtAddDTFastF, edtAddGiaFastF, edtAddDiaChiFastF, edtAddTenFastF;
    private FastFoodDAO fastFoodDAO;
    public static final int PICK_IMAGE = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_fast_food);
        initsView();
        btnBackFast.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(AddFastFoodActivity.this, FastFoodActivity.class);
                startActivity(intent);
            }
        });

        imageFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });

        btnAddFastFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String name = edtAddTenFastF.getText().toString();
                String address = edtAddDiaChiFastF.getText().toString();
                String sdt = edtAddDTFastF.getText().toString();
                String gia = edtAddGiaFastF.getText().toString();

                if(validate()>0){
                    FastFood fastFood = new FastFood(name,address,gia,sdt,ImageViewChange(imageFood));
                    if(fastFoodDAO.insertFastFood(fastFood)>0){
                        Toast.makeText(getApplicationContext(), "Add Successfull", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }
            }
        });


    }

    private void initsView() {
        fastFoodDAO = new FastFoodDAO(getApplicationContext());
        btnAddFastFood = findViewById(R.id.btnAddFastFood);
        btnBackFast = findViewById(R.id.btnBackFast);
        edtAddDiaChiFastF = findViewById(R.id.edtAddDiaChiFastF);
        edtAddDTFastF = findViewById(R.id.edtAddDTFastF);
        edtAddGiaFastF = findViewById(R.id.edtAddGiaFastF);
        edtAddTenFastF = findViewById(R.id.edtAddTenFastF);
        imageFood = findViewById(R.id.imgAddFastFood);
    }

    private int validate() {
        int check = -1;
        if (edtAddTenFastF.getText().toString().equals("")) {
            edtAddTenFastF.setError(getString(R.string.empty));
            return check;
        } else if (edtAddDiaChiFastF.getText().toString().equals("")) {
            edtAddDiaChiFastF.setError(getString(R.string.empty));
            return check;
        } else if (edtAddDTFastF.getText().toString().length() != 10) {
            edtAddDTFastF.setError(getString(R.string.length));
            return check;
        } else if (Integer.parseInt(edtAddGiaFastF.getText().toString()) < 0) {
            edtAddGiaFastF.setError(getString(R.string.price));
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
                        imageFood.setImageBitmap(selectedImage);
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
