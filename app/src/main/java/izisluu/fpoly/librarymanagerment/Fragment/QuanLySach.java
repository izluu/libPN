package izisluu.fpoly.librarymanagerment.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Adapter.AdapterSach;
import izisluu.fpoly.librarymanagerment.DAO.LoaiSachDAO;
import izisluu.fpoly.librarymanagerment.DAO.SachDAO;
import izisluu.fpoly.librarymanagerment.Model.LoaiSach;
import izisluu.fpoly.librarymanagerment.Model.Sach;
import izisluu.fpoly.librarymanagerment.R;

public class QuanLySach extends Fragment {

    private RecyclerView rcvSach;
    private AdapterSach adapter;
    private FloatingActionButton btnAddSach;
    private ArrayList<Sach> list;
    private SachDAO dao;

    public QuanLySach() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        dao = new SachDAO(getContext());
        list = dao.getAll();

        rcvSach = view.findViewById(R.id.RCVSach);
        btnAddSach = view.findViewById(R.id.btnAddSach);
        adapter = new AdapterSach(getContext(), list);
        rcvSach.setAdapter(adapter);
        rcvSach.setLayoutManager(new LinearLayoutManager(getContext()));
        btnAddSach.setOnClickListener(v -> openDialog());
    }

    public void openDialog() {
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_add_sach, null);
        builder.setView(view);

        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        dialog.show();

        TextInputLayout edtAddMS = view.findViewById(R.id.edtAddMS);
        TextInputLayout edtAddTenSach = view.findViewById(R.id.edtAddTenSach);
        TextInputLayout edtAddTacGia = view.findViewById(R.id.edtAddTacGia);
        TextInputLayout edtAddSoTien = view.findViewById(R.id.edtAddSoTien);
        Spinner spnTenLoai = view.findViewById(R.id.spnTenLoai);
        Button btnCancel = view.findViewById(R.id.btnCancel);
        Button btnSave = view.findViewById(R.id.btnSave);

        LoaiSachDAO loaiSachDAO = new LoaiSachDAO(getContext());
        ArrayList<LoaiSach> listLS = loaiSachDAO.getAll();
        ArrayAdapter<LoaiSach> adapterSpinner = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listLS);
        spnTenLoai.setAdapter(adapterSpinner);

        btnCancel.setOnClickListener(v -> dialog.dismiss());
        btnSave.setOnClickListener(v -> {
            String maSach = edtAddMS.getEditText().getText().toString().trim();
            String tenSach = edtAddTenSach.getEditText().getText().toString().trim();
            String tacGia = edtAddTacGia.getEditText().getText().toString().trim();
            String giaTien = edtAddSoTien.getEditText().getText().toString().trim();

            if (maSach.isEmpty() || tenSach.isEmpty() || tacGia.isEmpty() || giaTien.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                return;
            }

            LoaiSach ls = (LoaiSach) spnTenLoai.getSelectedItem();
            if (ls == null) {
                Toast.makeText(getContext(), "Vui lòng chọn loại sách", Toast.LENGTH_SHORT).show();
                return;
            }
            String maLoaiSach = ls.getMaLoaiSach();
            String tenLoai = ls.getTenLoaiSach();
//            Log.d("LoaiSach", "MaLoaiSach: " + maLoaiSach);

            Sach sach = new Sach(maSach, maLoaiSach, tenSach, tacGia, giaTien);
            sach.setTenLoaiSach(tenLoai);
            boolean result = dao.insert(sach);

            if (result) {
//                list.add(sach);
                list.clear();
                list.addAll(dao.getAll());
                adapter.notifyDataSetChanged();
                dialog.dismiss();
            } else {
                Toast.makeText(getContext(), "Thêm sách thất bại", Toast.LENGTH_SHORT).show();
            }
        });

        dialog.show();
    }

}
