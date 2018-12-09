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

import duan1.catalogfood.model.FastFoodDAO;
import duan1.catalogfood.model.MainFoodDAO;

public class ChangeInfoMainFoodActivity extends AppCompatActivity {
    private EditText edtChangeTenMainFood,edtChangeDiaChiMainFood,edtChangeGiaMainFood,edtChangeDTMainFood;
    private Button btnHuyMF,btnChangeMF;
    private ImageView imgChangeMainFood;
    private MainFoodDAO mainFoodDAO;
    private Toolbar toolbar;
    public static final int PICK_IMAGE = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_change_info_main_food);
        innitviews();
        setSupportActionBar(toolbar);
        toolbar.setTitle("Change Infomation");
        toolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(ChangeInfoMainFoodActivity.this,MainFoodActivity.class);
                startActivity(intent);
            }
        });

        imgChangeMainFood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Intent.ACTION_PICK);
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent, PICK_IMAGE);
            }
        });
        btnHuyMF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                edtChangeDiaChiMainFood.setText("");
                edtChangeDTMainFood.setText("");
                edtChangeGiaMainFood.setText("");
                edtChangeTenMainFood.setText("");
            }
        });

        btnChangeMF.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                String name=edtChangeTenFastFood.toString().trim();
//                String dienthoai=edtChangeDTFastFood.toString().trim();
//                String gia=edtChangeGiaFastFood.toString().trim();
//                String diachi=edtChangeDiaChiFastFood.toString().trim();
                if(validate()>0){
                    if (mainFoodDAO.updateMainFood(edtChangeTenMainFood.getText().toString(),edtChangeDiaChiMainFood.getText().toString(),
                            edtChangeGiaMainFood.getText().toString(),edtChangeDTMainFood.getText().toString(),ImageViewChange(imgChangeMainFood))>0){
                        Toast.makeText(ChangeInfoMainFoodActivity.this, "Success", Toast.LENGTH_SHORT).show();
                        finish();
                    }


                }
            }
        });
    }

    private void innitviews() {
        edtChangeDiaChiMainFood=findViewById(R.id.edtChangeDiaChiMainFood);
        edtChangeDTMainFood=findViewById(R.id.edtChangeDTMainFood);
        edtChangeGiaMainFood=findViewById(R.id.edtChangeGiaMainFood);
        edtChangeTenMainFood=findViewById(R.id.edtChangeTenMainFood);
        btnChangeMF=findViewById(R.id.btnChangeMF);
        btnHuyMF=findViewById(R.id.btnHuyMF);
        imgChangeMainFood=findViewById(R.id.imgChangeMainFood);
        mainFoodDAO=new MainFoodDAO(getApplicationContext());
        toolbar=findViewById(R.id.toolbarMF);

    }
    private int validate() {
        int check = -1;
        if (edtChangeTenMainFood.getText().toString().equals("")) {
            edtChangeTenMainFood.setError(getString(R.string.empty));
            return check;
        } else if (edtChangeDiaChiMainFood.getText().toString().equals("")) {
            edtChangeDiaChiMainFood.setError(getString(R.string.empty));
            return check;
        } else if (edtChangeDTMainFood.getText().toString().length() != 10) {
            edtChangeDTMainFood.setError(getString(R.string.length));
            return check;
        } else if (Integer.parseInt(edtChangeGiaMainFood.getText().toString()) < 0) {
            edtChangeGiaMainFood.setError(getString(R.string.price));
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
                        imgChangeMainFood.setImageBitmap(selectedImage);
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
