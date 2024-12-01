package izisluu.fpoly.librarymanagerment;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import izisluu.fpoly.librarymanagerment.DAO.ThuThuDAO;
import izisluu.fpoly.librarymanagerment.Model.ThuThu;

public class Login extends AppCompatActivity {
    private EditText edtUsername,edtPassword;
    private Button btnLogin;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        edtUsername = findViewById(R.id.edtUsername);
        edtPassword = findViewById(R.id.edtPassword);

        btnLogin = findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(v -> {
            String username = edtUsername.getText().toString();
            String password = edtPassword.getText().toString();
            ThuThuDAO thuThuDAO = new ThuThuDAO(Login.this);
            ThuThu thuThu = new ThuThu("1","1","1","1");
            thuThuDAO.insert(thuThu);

            if(thuThuDAO.checkLogin(username,password)){
                Intent intent = new Intent(Login.this, MainActivity.class);
                startActivity(intent);
                finish();
            }else {
                Toast.makeText(Login.this,"Sai tài khoản hoặc mật khẩu",Toast.LENGTH_SHORT).show();
            }
        });
    }
}