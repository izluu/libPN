package izisluu.fpoly.librarymanagerment.Adapter;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.DAO.PhieuMuonDAO;
import izisluu.fpoly.librarymanagerment.DAO.SachDAO;
import izisluu.fpoly.librarymanagerment.DAO.ThanhVienDAO;
import izisluu.fpoly.librarymanagerment.Model.PhieuMuon;
import izisluu.fpoly.librarymanagerment.Model.Sach;
import izisluu.fpoly.librarymanagerment.Model.ThanhVien;
import izisluu.fpoly.librarymanagerment.R;

public class AdapterPhieuMuon extends RecyclerView.Adapter<AdapterPhieuMuon.PhieuMuonViewHolder> {
    private ArrayList<PhieuMuon> list;
    private Context context;
    private PhieuMuonDAO phieuMuonDAO;
    private ThanhVienDAO thanhVienDAO;
    private SachDAO sachDAO;
    private ArrayList<ThanhVien> listTV;
    private ArrayList<Sach> listSach;

    public AdapterPhieuMuon(ArrayList<PhieuMuon> list, Context context) {
        this.list = list;
        this.context = context;
        phieuMuonDAO = new PhieuMuonDAO(context);
    }

    @NonNull
    @Override
    public PhieuMuonViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customrcv_phieumuon,parent,false);
        return new PhieuMuonViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhieuMuonViewHolder holder, int position) {
        thanhVienDAO = new ThanhVienDAO(context);
        listTV = thanhVienDAO.getALL();
        String tenTV = "";
        for (ThanhVien tv:listTV) {
            if (tv.getMaThanhVien().equals(list.get(position).getMaThanhVien())){
                tenTV = tv.getTenThanhVien();
                break;
            }
        }
        sachDAO = new SachDAO(context);
        listSach = sachDAO.getAll();
        String tenSach = "";
        for (Sach sach:listSach) {
            if (sach.getMaSach().equals(list.get(position).getMaSach())){
                tenSach = sach.getTenSach();
                break;}}
        PhieuMuon phieuMuon = list.get(position);
        holder.tvMaPM.setText(phieuMuon.getMaPhieuMuon());
        holder.tvThanhvienPM.setText(tenTV);
        holder.tvtenSachPM.setText(tenSach);
        holder.tvTienThue.setText(phieuMuon.getThanhTien());
        holder.tvNgaythue.setText(phieuMuon.getNgayMuon());
        if (phieuMuon.getTrangThai() == 1){
            holder.tvTraSach.setText("Đã trả sách");
        }else {
            holder.tvTraSach.setText("Chưa trả sách");
        }

        holder.imgDeletePM.setOnClickListener(v -> {
            phieuMuonDAO.delete(phieuMuon.getMaPhieuMuon());
            list.remove(position);
            notifyDataSetChanged();
        });
        holder.imgEditPM.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            View view = LayoutInflater.from(context).inflate(R.layout.dialog_editphieumuon, null);
            Dialog dialog = builder.setView(view).create();
            dialog.show();
            dialog.setCancelable(false);
            view.findViewById(R.id.btnCancelEditPM).setOnClickListener(v1 -> dialog.dismiss());

            thanhVienDAO = new ThanhVienDAO(context);
            listTV = thanhVienDAO.getALL();
            Spinner spinerTenTv = view.findViewById(R.id.spinerEditTenTv);
            ArrayAdapter<ThanhVien> adapterSpinnerThanhVien = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listTV);
            spinerTenTv.setAdapter(adapterSpinnerThanhVien);
            for(int i = 0; i<listTV.size();i++){
                if(listTV.get(i).getMaThanhVien().equals(phieuMuon.getMaThanhVien())){
                    spinerTenTv.setSelection(i);
                    break;
                }
            }
            SachDAO sachDAO = new SachDAO(context);
            ArrayList<Sach> listTenSach = sachDAO.getAll();
            Spinner spinerTenSach = view.findViewById(R.id.spinerEditTenSach);
            ArrayAdapter<Sach> adapterSpinnerSach = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listTenSach);
            spinerTenSach.setAdapter(adapterSpinnerSach);
            for(int i = 0; i<listTenSach.size();i++) {
                if (listTenSach.get(i).getMaSach().equals(phieuMuon.getMaSach())) {
                    spinerTenSach.setSelection(i);
                }
            }
            EditText editNgayThue = view.findViewById(R.id.edtEditNgayThue);
            EditText editTien = view.findViewById(R.id.edtEditTien);
            CheckBox chkTraSach = view.findViewById(R.id.chkEditTraSach);
            editNgayThue.setText(phieuMuon.getNgayMuon());
            editTien.setText(phieuMuon.getThanhTien());
            if (phieuMuon.getTrangThai() == 1){
                chkTraSach.setChecked(true);
            }else {
                chkTraSach.setChecked(false);
                }
            view.findViewById(R.id.btnAddEditPM).setOnClickListener(v1 -> {
                String maPM = phieuMuon.getMaPhieuMuon();
                ThanhVien thanhVien = (ThanhVien) spinerTenTv.getSelectedItem();
                String maTV = thanhVien.getMaThanhVien();
                String tenTV1 = thanhVien.getTenThanhVien();
                Sach sach = (Sach) spinerTenSach.getSelectedItem();
                String maSach = sach.getMaSach();
                String tenSach1 = sach.getTenSach();
                String ngayThue = editNgayThue.getText().toString();
                String format = "\\d{4}-\\d{2}-\\d{2}";
                if(!isValidDate(ngayThue)){
                    editNgayThue.setError("ngày thuê phải theo định dạng yyyy-mm-dd");
                    return;
                }
                if (maPM.isEmpty() || thanhVien == null || sach == null || ngayThue.isEmpty() || editTien.getText().toString().isEmpty()) {
                    return;
                }
                String thanhTien = editTien.getText().toString();
                int trangThai = 0;
                if (chkTraSach.isChecked()){
                    trangThai = 1;
                }
                phieuMuon.setMaPhieuMuon(maPM);
                phieuMuon.setMaThanhVien(maTV);
                phieuMuon.setTenTV(tenTV1);
                phieuMuon.setMaSach(maSach);
                phieuMuon.setTenSach(tenSach1);
                phieuMuon.setNgayMuon(ngayThue);
                phieuMuon.setThanhTien(thanhTien);
                phieuMuon.setTrangThai(trangThai);
                phieuMuonDAO.update(phieuMuon);
                notifyDataSetChanged();
                dialog.dismiss();
            });
        });
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

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class PhieuMuonViewHolder extends RecyclerView.ViewHolder {
        private TextView tvMaPM, tvThanhvienPM, tvtenSachPM, tvTienThue, tvTraSach, tvNgaythue;
        private ImageView imgEditPM, imgDeletePM;

        public PhieuMuonViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaPM = itemView.findViewById(R.id.tvMaPM);
            tvThanhvienPM = itemView.findViewById(R.id.tvThanhvienPM);
            tvtenSachPM = itemView.findViewById(R.id.tvtenSachPM);
            tvTienThue = itemView.findViewById(R.id.tvTienThue);
            tvTraSach = itemView.findViewById(R.id.tvTraSach);
            tvNgaythue = itemView.findViewById(R.id.tvNgaythue);
            imgEditPM = itemView.findViewById(R.id.imgEditPM);
            imgDeletePM = itemView.findViewById(R.id.imgDeletePM);
        }
    }
}
