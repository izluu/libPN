package izisluu.fpoly.librarymanagerment.Adapter;

import static java.security.AccessController.getContext;

import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.DAO.LoaiSachDAO;
import izisluu.fpoly.librarymanagerment.DAO.SachDAO;
import izisluu.fpoly.librarymanagerment.Model.LoaiSach;
import izisluu.fpoly.librarymanagerment.Model.Sach;
import izisluu.fpoly.librarymanagerment.R;

public class AdapterSach extends RecyclerView.Adapter<AdapterSach.SachViewHolder> {
    private Context context;
    private ArrayList<Sach> list;
    private SachDAO dao;
    public AdapterSach(Context context, ArrayList<Sach> list) {
        this.context = context;
        this.list = list;
        dao = new SachDAO(context);
    }

    @NonNull
    @Override
    public SachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.customrcv_sach,parent,false);
        return new SachViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SachViewHolder holder, int position) {
        Sach sach = list.get(position);
        holder.tvMS.setText(sach.getMaSach());
        holder.tvTen.setText(sach.getTenSach());
        holder.tvTacGia.setText(sach.getTacGia());
        holder.tvGiaTien.setText(sach.getGiaTien());
        holder.tvTenLoai.setText(sach.getTenLoaiSach());
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        holder.btnDelete.setOnClickListener(v -> {
            dao.delete(sach.getMaSach());
            list.remove(sach);
            notifyDataSetChanged();
        });
        holder.btnEdit.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_edit_sach,null);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
            TextInputLayout edtTenSach = view.findViewById(R.id.edtEditTenSach);
            TextInputLayout edtTacGia = view.findViewById(R.id.edtEditTacGia);
            TextInputLayout edtSoTien = view.findViewById(R.id.edtEditSoTien);
            Spinner spnTenLoai = view.findViewById(R.id.spnEditTenLoai);
            Button btnSaveSach = view.findViewById(R.id.btnSaveEdit);
            Button btnCancel = view.findViewById(R.id.btnCancelEdit);
            edtTenSach.getEditText().setText(sach.getTenSach());
            edtTacGia.getEditText().setText(sach.getTacGia());
            edtSoTien.getEditText().setText(sach.getGiaTien());
            LoaiSachDAO loaiSachDAO = new LoaiSachDAO(context);
            ArrayList<LoaiSach> listTenLoai = loaiSachDAO.getAll();
            ArrayAdapter<LoaiSach> adapterSpinner = new ArrayAdapter<>(context, android.R.layout.simple_spinner_dropdown_item, listTenLoai);
            spnTenLoai.setAdapter(adapterSpinner);
            for (int i = 0; i < listTenLoai.size(); i++) {
                if(sach.getTenLoaiSach().equalsIgnoreCase(listTenLoai.get(i).getTenLoaiSach())){
                    spnTenLoai.setSelection(i);
                    break;
                }
            }
            btnCancel.setOnClickListener(v1 -> dialog.dismiss());
            btnSaveSach.setOnClickListener(v1 -> {
//                String maSach = edtTenSach.getEditText().toString();
                String tenSach = edtTenSach.getEditText().getText().toString();
                String tacGia = edtTacGia.getEditText().getText().toString();
                String giaTien = edtSoTien.getEditText().getText().toString();
                if (tenSach.isEmpty() || tacGia.isEmpty() || giaTien.isEmpty()) {
                    Toast.makeText(context, "Vui lòng điền đầy đủ thông tin", Toast.LENGTH_SHORT).show();
                    return;
                }
                LoaiSach ls = (LoaiSach) spnTenLoai.getSelectedItem();
                String maLoaiSach = ls.getMaLoaiSach();
                String tenLoai = ls.getTenLoaiSach();
                sach.setTenSach(tenSach);
                sach.setTacGia(tacGia);
                sach.setGiaTien(giaTien);
                sach.setMaLoaiSach(maLoaiSach);
                sach.setTenLoaiSach(tenLoai);
                dao.update(sach);
                notifyDataSetChanged();
                dialog.dismiss();
            });




        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class SachViewHolder extends RecyclerView.ViewHolder{
        TextView tvMS, tvTen, tvGiaTien, tvTenLoai, tvTacGia;
        ImageView btnEdit, btnDelete;
        public SachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMS = itemView.findViewById(R.id.tvMS);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvTacGia = itemView.findViewById(R.id.tvTacGia);
            tvGiaTien = itemView.findViewById(R.id.tvGiaTien);
            tvTenLoai = itemView.findViewById(R.id.tvTenLoai);
            btnEdit = itemView.findViewById(R.id.btnEditSach);
            btnDelete = itemView.findViewById(R.id.btnDeleteSach);
        }
    }
}
