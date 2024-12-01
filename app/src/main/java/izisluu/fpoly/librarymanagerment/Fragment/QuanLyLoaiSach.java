package izisluu.fpoly.librarymanagerment.Fragment;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Adapter.AdapterLoaiSach;
import izisluu.fpoly.librarymanagerment.DAO.LoaiSachDAO;
import izisluu.fpoly.librarymanagerment.Model.LoaiSach;
import izisluu.fpoly.librarymanagerment.R;

public class QuanLyLoaiSach extends Fragment {
    private RecyclerView rcvLoaiSach;
    private AdapterLoaiSach adapter;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO dao;
    private FloatingActionButton btnAddLS;

    public QuanLyLoaiSach() {
        // Required empty public constructor
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_quan_ly_loai_sach, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        rcvLoaiSach = view.findViewById(R.id.RCVLoaiSach);
        btnAddLS = view.findViewById(R.id.btnAddLS);
        dao = new LoaiSachDAO(getContext());
        list = dao.getAll();
        if(list == null || list.isEmpty()){
            list = new ArrayList<>();
            list.add(new LoaiSach("001","Tiểu thuyết","TS"));
        }
        adapter = new AdapterLoaiSach(getContext(),list);
        rcvLoaiSach.setAdapter(adapter);
        rcvLoaiSach.setLayoutManager(new LinearLayoutManager(getContext()));
        btnAddLS.setOnClickListener(v -> {
            openDialog();
        });

    }
    public void openDialog() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        LayoutInflater inflater = getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_add_loaisach, null);
        builder.setView(view);
        AlertDialog dialog = builder.create();
        dialog.setCancelable(false);
        TextInputLayout edtMLS = view.findViewById(R.id.edtMLS);
        TextInputLayout edtTenLS = view.findViewById(R.id.edtTenLS);
        TextInputLayout edtNickname = view.findViewById(R.id.edtNickname);
        view.findViewById(R.id.btnCancel).setOnClickListener(v -> {
            dialog.dismiss();
        });
        view.findViewById(R.id.btnSaveLS).setOnClickListener(v -> {
            String maLS = edtMLS.getEditText().getText().toString();
            String tenLS = edtTenLS.getEditText().getText().toString();
            String nickname = edtNickname.getEditText().getText().toString();
            LoaiSach loaiSach = new LoaiSach(maLS, tenLS, nickname);
            if (!isValid(maLS, tenLS, nickname)) {
                return;
            }
            list.add(loaiSach);
            adapter.notifyDataSetChanged();
            dao.insert(loaiSach);
            dialog.dismiss();
            });
        dialog.show();

    }
    public boolean isValid(String maLS, String tenLS, String nickname){
        if(maLS.isEmpty() || tenLS.isEmpty() || nickname.isEmpty()){
            Toast.makeText(getContext(),"Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            return false;
        }
        return true;
    }
}