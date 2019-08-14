package com.example.neighbouradmin;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.neighbouradmin.Controller.AdminController;
import com.example.neighbouradmin.model.Admin;

public class LoginActivity extends AppCompatActivity {

    private EditText loginEmail;
    private EditText logiPassword;
    private Button btnLogin;
    private AdminController adminController;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loginEmail = findViewById(R.id.loginEmail);
        logiPassword = findViewById(R.id.loginPassword);
        btnLogin = findViewById(R.id.btnLogin);
    }

    public void btnLogin(View view){
        String email = loginEmail.getText().toString();
        String password = logiPassword.getText().toString();

        if(loginEmail.getText().toString().equals("")|| logiPassword.getText().toString() .equals("")){
            AlertDialog alertDialog = new AlertDialog.Builder(this).create();
            alertDialog.setTitle("");
            alertDialog.setMessage("Please fill in all the blanks");
            alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                    new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss();
                        }
                    });
            alertDialog.show();
        }
        else{}

        AdminController adminController = new AdminController(getApplicationContext());
        adminController.getAdmin(loginEmail.getText().toString(), this);
    }

    public void setAdmin(Admin admin) {
        Intent intent = new Intent();
        if (admin!=null){
            if (admin.getPassword().equals(logiPassword.getText().toString())) {
                intent.putExtra("email", admin.getEmail());
                intent.putExtra("username", admin.getUsername());
                setResult(RESULT_OK, intent);
                Toast.makeText(this,"Succesfull Login", Toast.LENGTH_SHORT).show();
                finish();
            }
        }
        else
            Toast.makeText(this, "Wrong password", Toast.LENGTH_SHORT).show();
    }

}
