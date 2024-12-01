package izisluu.fpoly.librarymanagerment.Fragment;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.Adapter.AdapterPhieuMuon;
import izisluu.fpoly.librarymanagerment.DAO.PhieuMuonDAO;
import izisluu.fpoly.librarymanagerment.DAO.SachDAO;
import izisluu.fpoly.librarymanagerment.DAO.ThanhVienDAO;
import izisluu.fpoly.librarymanagerment.Model.PhieuMuon;
import izisluu.fpoly.librarymanagerment.Model.Sach;
import izisluu.fpoly.librarymanagerment.Model.ThanhVien;
import izisluu.fpoly.librarymanagerment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link QuanLyPhieuMuon#newInstance} factory method to
 * create an instance of this fragment.
 */
public class QuanLyPhieuMuon extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    FloatingActionButton fabPhieuMuon;
    private RecyclerView rcvPhieuMuon;
    private AdapterPhieuMuon adapter;
    private ArrayList<PhieuMuon> list;
    private PhieuMuonDAO dao;
    public QuanLyPhieuMuon() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment QuanLyPhieuMuon.
     */
    // TODO: Rename and change types and number of parameters
    public static QuanLyPhieuMuon newInstance(String param1, String param2) {
        QuanLyPhieuMuon fragment = new QuanLyPhieuMuon();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_quan_ly_phieu_muon, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        fabPhieuMuon = view.findViewById(R.id.btnAddPM);
        rcvPhieuMuon = view.findViewById(R.id.rcvPhieuMuon);
        dao = new PhieuMuonDAO(getContext());
        list = dao.getAll();
        adapter = new AdapterPhieuMuon(list, getContext());
        rcvPhieuMuon.setAdapter(adapter);
        rcvPhieuMuon.setLayoutManager(new LinearLayoutManager(getContext()));
        fabPhieuMuon.setOnClickListener(v -> {
            openDialogAdd();
        });
    }
    public void openDialogAdd(){
        MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(getContext());
        View view = LayoutInflater.from(getContext()).inflate(R.layout.dialog_addphieumuon, null);
        builder.setView(view);
        Dialog dialog = builder.create();
        dialog.setCancelable(false);
        Spinner spinerTenTv = view.findViewById(R.id.spinerTenTv);
        ThanhVienDAO thanhVienDAO = new ThanhVienDAO(getContext());
        ArrayList<ThanhVien> listTenTv = thanhVienDAO.getALL();
        ArrayAdapter<ThanhVien> adapterSpinnerThanhVien = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTenTv);
        spinerTenTv.setAdapter(adapterSpinnerThanhVien);
        Spinner spinerTenSach = view.findViewById(R.id.spinerTenSach);
        SachDAO sachDAO = new SachDAO(getContext());
        ArrayList<Sach> listTenSach = sachDAO.getAll();
        ArrayAdapter<Sach> adapterSpinnerSach = new ArrayAdapter<>(getContext(), android.R.layout.simple_spinner_dropdown_item, listTenSach);
        spinerTenSach.setAdapter(adapterSpinnerSach);
        EditText editNgayThue = view.findViewById(R.id.editNgayThue);
        EditText editTien = view.findViewById(R.id.editTien);
        CheckBox chkTraSach = view.findViewById(R.id.chkTraSach);
        EditText editMaPM = view.findViewById(R.id.edtAddMS);
        view.findViewById(R.id.btnCancelPM).setOnClickListener(v -> dialog.dismiss());
        view.findViewById(R.id.btnAddPM).setOnClickListener(v -> {
            String maPM = editMaPM.getText().toString().trim();
            ThanhVien thanhVien = (ThanhVien) spinerTenTv.getSelectedItem();
            Sach sach = (Sach) spinerTenSach.getSelectedItem();
            String ngayThue = editNgayThue.getText().toString().trim();
            String tien = editTien.getText().toString().trim();
//            String format = "\\d{4}-\\d{2}-\\d{2}";
            if (maPM.isEmpty() || thanhVien == null || sach == null || ngayThue.isEmpty() || tien.isEmpty()) {
                Toast.makeText(getContext(), "Vui lòng nhập đầy đủ thông tin", Toast.LENGTH_SHORT).show();
            }
            if(!isValidDate(ngayThue)){
                editNgayThue.setError("Ngày thuê không hợp lệ");
                return;
            }
            PhieuMuon phieuMuon = new PhieuMuon(maPM, thanhVien.getMaThanhVien(), thanhVien.getTenThanhVien(), ngayThue, 0, tien, sach.getMaSach(), sach.getTenSach());
            if (chkTraSach.isChecked()){
                phieuMuon.setTrangThai(1);
            }else {
                phieuMuon.setTrangThai(0);
            }
            if (dao.insert(phieuMuon)) {
                list.add(phieuMuon);
                adapter.notifyDataSetChanged();
            }
            dialog.dismiss();
        });
        dialog.show();
    }
    public boolean isValidDate(String date){
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        format.setLenient(false);
        try {
            format.parse(date);
            return true;
        }catch (Exception e){
            return false;
        }
    }
}