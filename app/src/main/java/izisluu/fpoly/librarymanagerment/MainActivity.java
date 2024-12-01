package izisluu.fpoly.librarymanagerment;

import android.os.Bundle;
import android.view.MenuItem;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.navigation.NavigationView;

import izisluu.fpoly.librarymanagerment.Fragment.AddThuThu;
import izisluu.fpoly.librarymanagerment.Fragment.DoanhThu;
import izisluu.fpoly.librarymanagerment.Fragment.DoiMatKhau;
import izisluu.fpoly.librarymanagerment.Fragment.QuanLyLoaiSach;
import izisluu.fpoly.librarymanagerment.Fragment.QuanLyPhieuMuon;
import izisluu.fpoly.librarymanagerment.Fragment.QuanLySach;
import izisluu.fpoly.librarymanagerment.Fragment.QuanLyThanhVien;
import izisluu.fpoly.librarymanagerment.Fragment.ThongKeTop10;

public class MainActivity extends AppCompatActivity {
    private Toolbar toolbar;
    private DrawerLayout drawerLayout;
    private NavigationView navigationView;
    private FloatingActionButton fab;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        toolbar = findViewById(R.id.toolbar);
        drawerLayout = findViewById(R.id.main);
        navigationView = findViewById(R.id.navView);
//        fab = findViewById(R.id.btnAdd);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle toggle =new ActionBarDrawerToggle(this,drawerLayout,toolbar,R.string.open,R.string.close);
        drawerLayout.addDrawerListener(toggle);
        toggle.syncState();
        if(savedInstanceState == null){
            getSupportFragmentManager().beginTransaction().replace(R.id.container,new QuanLyPhieuMuon()).commit();
            navigationView.setCheckedItem(R.id.QLPM);
        }
        navigationView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                int id = item.getItemId();
                if (id == R.id.QLPM) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new QuanLyPhieuMuon()).commit();
                } else if (id == R.id.QLSach) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new QuanLySach()).commit();
                } else if (id == R.id.QLTV) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new QuanLyThanhVien()).commit();
                } else if (id == izisluu.fpoly.librarymanagerment.R.id.QLLoaiSach) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new QuanLyLoaiSach()).commit();
                } else if (id == R.id.AddThuThu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new AddThuThu()).commit();
                } else if (id == R.id.Logout) {

                } else if (id == R.id.doiMatKhau) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new DoiMatKhau()).commit();
                } else if (id == R.id.doanhThu) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new DoanhThu()).commit();

                } else if (id == R.id.top10) {
                    getSupportFragmentManager().beginTransaction().replace(R.id.container, new ThongKeTop10()).commit();
                }
                drawerLayout.closeDrawers();
                return true;
            }
        });

    }
}