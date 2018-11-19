package duan1.catalogfood;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.net.Uri;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;

import duan1.catalogfood.model.MainFood;
import duan1.catalogfood.model.MainFoodDAO;

public class AddMainFoodActivity extends AppCompatActivity {
    private Button btnAddMainFood;
    private ImageView btnBackMain,imageFood;
    private EditText edtAddDTMainF,edtAddGiaMainF,edtAddDiaChiMainF,edtAddTenMainF;
    private MainFoodDAO mainFoodDAO;
    public static final int PICK_IMAGE=1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_main_food);
        initsView();
        btnBackMain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(AddMainFoodActivity.this,MainFoodActivity.class);
                startActivity(intent);
            }
        });

        btnAddMainFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name=edtAddTenMainF.getText().toString().trim();
                String gia=edtAddDiaChiMainF.getText().toString().trim();

                String diachi=edtAddDiaChiMainF.getText().toString().trim();

                String dienthoai=edtAddDTMainF.getText().toString().trim();
                if (validate()>0){
                    MainFood mainFood=new MainFood(name,diachi,gia,dienthoai,ImageViewChange(imageFood));
                    if (mainFoodDAO.insertMainFood(mainFood)>0){
                        Toast.makeText(AddMainFoodActivity.this, "Add sucess", Toast.LENGTH_SHORT).show();
                        finish();
                    }

                }

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
    }

    private void initsView() {
        mainFoodDAO=new MainFoodDAO(AddMainFoodActivity.this);
        btnAddMainFood=findViewById(R.id.btnAddMainFood);
        btnBackMain=findViewById(R.id.btnBackMain);
        edtAddDiaChiMainF=findViewById(R.id.edtAddDiaChiMainF);
        edtAddDTMainF=findViewById(R.id.edtAddDTMainF);
        edtAddGiaMainF=findViewById(R.id.edtAddGiaMainF);
        edtAddTenMainF=findViewById(R.id.edtAddTenMainF);
        imageFood=findViewById(R.id.imgAddMainFood);

    }

    private int validate() {
        int check = -1;
        if (edtAddTenMainF.getText().toString().equals("")) {
            edtAddTenMainF.setError(getString(R.string.empty));
            return check;
        } else if (edtAddDiaChiMainF.getText().toString().equals("")) {
            edtAddDiaChiMainF.setError(getString(R.string.empty));
            return check;
        } else if (edtAddDTMainF.getText().toString().length() != 10) {
            edtAddDTMainF.setError(getString(R.string.length));
            return check;
        } else if (Integer.parseInt(edtAddGiaMainF.getText().toString()) < 0) {
            edtAddGiaMainF.setError(getString(R.string.price));
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
