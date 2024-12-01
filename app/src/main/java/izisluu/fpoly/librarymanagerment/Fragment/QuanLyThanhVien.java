package izisluu.fpoly.librarymanagerment.Fragment;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Adapter.AdapterThanhVien;
import izisluu.fpoly.librarymanagerment.DAO.ThanhVienDAO;
import izisluu.fpoly.librarymanagerment.Model.ThanhVien;
import izisluu.fpoly.librarymanagerment.R;

public class QuanLyThanhVien extends Fragment {
    private RecyclerView RCVThanhVien;
    private AdapterThanhVien adapter;
    private ArrayList<ThanhVien> list;
    private ThanhVienDAO dao;
    private FloatingActionButton btnAddTV;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_quan_ly_thanh_vien, container, false);

        return view;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        RCVThanhVien = view.findViewById(R.id.RCVThanhVien);
        btnAddTV = view.findViewById(R.id.btnAddTV);
        dao = new ThanhVienDAO(getContext());
        list = dao.getALL();
        if (list == null || list.isEmpty()){
            list = new ArrayList<>();
            list.add(new ThanhVien("001","Hoàng Phong","08/06/2004","0868734935"));
        }
        adapter = new AdapterThanhVien(list, getContext());
        RCVThanhVien.setAdapter(adapter);
        RCVThanhVien.setLayoutManager(new LinearLayoutManager(getContext()));
        btnAddTV.setOnClickListener(v -> {
            openDialog();
        });
    }

    private void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_thanhvien,null);
        builder.setView(view);

        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        TextInputLayout edtMTV = view.findViewById(R.id.edtMTV);
        TextInputLayout edtTen = view.findViewById(R.id.edtTen);
        TextInputLayout edtDiaChi = view.findViewById(R.id.edtDiaChi);
        TextInputLayout edtTel = view.findViewById(R.id.edtTel);
        view.findViewById(R.id.btnCancel).setOnClickListener(v -> {
            dialog.dismiss();
        });
        view.findViewById(R.id.btnSave).setOnClickListener(v -> {
            String maTV = edtMTV.getEditText().getText().toString();
            String tenTV = edtTen.getEditText().getText().toString();
            String diaChi = edtDiaChi.getEditText().getText().toString();
            String tel = edtTel.getEditText().getText().toString();
            ThanhVien thanhVien = new ThanhVien(maTV,tenTV,diaChi,tel);
            if(!isValid(maTV,tenTV,diaChi,tel)){
                return;
            }
            list.add(thanhVien);
            adapter.notifyDataSetChanged();
            dao.insert(thanhVien);
            dialog.dismiss();

        });
        dialog.show();

    }
    public boolean isValid(String maTV, String tenTV, String diaChi, String tel){
        if(maTV.isEmpty() || tenTV.isEmpty() || diaChi.isEmpty() || tel.isEmpty()){
            Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin",Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }



}