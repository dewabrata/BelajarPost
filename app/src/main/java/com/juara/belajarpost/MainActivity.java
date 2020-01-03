package com.juara.belajarpost;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.juara.belajarpost.model.result.ResultModel;
import com.juara.belajarpost.service.APIClient;
import com.juara.belajarpost.service.APIInterfacesRest;

import org.json.JSONObject;

import java.text.DecimalFormat;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    EditText txtName, txtPhone, txtAlamat,txtPhoto;
    Button btnSend;
    RecyclerView lstUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txtName = findViewById(R.id.txtNama);
        txtAlamat = findViewById(R.id.txtAlamat);
        txtPhone = findViewById(R.id.txtPhone);
        txtPhoto = findViewById(R.id.txtFoto);

        btnSend = findViewById(R.id.btnSend);
        lstUser = findViewById(R.id.lstUser);



        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                postUserData(txtName.getText().toString(),txtAlamat.getText().toString(),txtPhone.getText().toString()
                ,txtPhoto.getText().toString());
            }
        });


    }

    APIInterfacesRest apiInterface;
    ProgressDialog progressDialog;
    public void postUserData(String nama, String alamat, String telp, String foto){

        apiInterface = APIClient.getClient().create(APIInterfacesRest.class);
        progressDialog = new ProgressDialog(MainActivity.this);
        progressDialog.setTitle("Loading");
        progressDialog.show();
        Call<ResultModel> call3 = apiInterface.sendUser(nama,alamat,telp,foto);
        call3.enqueue(new Callback<ResultModel>() {
            @Override
            public void onResponse(Call<ResultModel> call, Response<ResultModel> response) {
                progressDialog.dismiss();
                ResultModel dataWeather = response.body();
                //Toast.makeText(LoginActivity.this,userList.getToken().toString(),Toast.LENGTH_LONG).show();
                if (dataWeather !=null) {


                    Toast.makeText(MainActivity.this,"Data berhasil disimpan .",Toast.LENGTH_LONG).show();





                }else{

                    try {
                        JSONObject jObjError = new JSONObject(response.errorBody().string());
                        Toast.makeText(MainActivity.this, jObjError.getString("message"), Toast.LENGTH_LONG).show();
                    } catch (Exception e) {
                        Toast.makeText(MainActivity.this, e.getMessage(), Toast.LENGTH_LONG).show();
                    }
                }

            }

            @Override
            public void onFailure(Call<ResultModel> call, Throwable t) {
                progressDialog.dismiss();
                Toast.makeText(getApplicationContext(),"Maaf koneksi bermasalah",Toast.LENGTH_LONG).show();
                call.cancel();
            }
        });




    }

}
