package izisluu.fpoly.librarymanagerment.Adapter;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.textfield.TextInputLayout;

import java.util.ArrayList;

import izisluu.fpoly.librarymanagerment.DAO.ThanhVienDAO;
import izisluu.fpoly.librarymanagerment.Model.ThanhVien;
import izisluu.fpoly.librarymanagerment.R;

public class AdapterThanhVien extends RecyclerView.Adapter<AdapterThanhVien.ThanhVienViewHolder> {
    private ArrayList<ThanhVien> list;
    private Context context;
    private ThanhVienDAO dao;
    public AdapterThanhVien(ArrayList<ThanhVien> list, Context context) {
        this.list = list;
        this.context = context;
        dao = new ThanhVienDAO(context);
    }

    @NonNull
    @Override
    public ThanhVienViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.customrcv_thanhvien,parent,false);
        return new ThanhVienViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ThanhVienViewHolder holder, int position) {
        ThanhVien thanhVien = list.get(position);
        holder.tvMTV.setText(thanhVien.getMaThanhVien());
        holder.tvTen.setText(thanhVien.getTenThanhVien());
        holder.tvDiaChi.setText(thanhVien.getNgaySinh());
        holder.tvTel.setText(thanhVien.getSoDienThoai());
//        holder.btnEdit.setOnClickListener(v -> {
//            dao.update(thanhVien);
//        });
        holder.btnDelete.setOnClickListener(v -> {
            AlertDialog.Builder builder = new AlertDialog.Builder(context);
            builder.setTitle("Xác nhận xóa");
            builder.setMessage("Bạn có chắc chắn muốn xóa?");
            builder.setPositiveButton("Có", (dialog, which) -> {
                dao.delete(thanhVien.getMaThanhVien());
                list.remove(thanhVien);
                notifyDataSetChanged();
            });
            builder.setNegativeButton("Không", (dialog, which) -> {

                    });
            builder.setCancelable(false);
            builder.show();

        });
        holder.btnEdit.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_edit_thanhvien,null);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
            TextInputLayout edtTen = view.findViewById(R.id.edtTen);
            TextInputLayout edtDiaChi = view.findViewById(R.id.edtDiaChi);
            TextInputLayout edtTel = view.findViewById(R.id.edtTel);
            Button btnCancel = view.findViewById(R.id.btnCancel);
            Button btnSave = view.findViewById(R.id.btnSave);
            edtTen.getEditText().setText(thanhVien.getTenThanhVien());
            edtDiaChi.getEditText().setText(thanhVien.getNgaySinh());
            edtTel.getEditText().setText(thanhVien.getSoDienThoai());
            btnCancel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
            btnSave.setOnClickListener(v1 -> {
                String tenTV = edtTen.getEditText().getText().toString();
                String diaChi = edtDiaChi.getEditText().getText().toString();
                String tel = edtTel.getEditText().getText().toString();
                thanhVien.setTenThanhVien(tenTV);
                thanhVien.setNgaySinh(diaChi);
                thanhVien.setSoDienThoai(tel);
                dao.update(thanhVien);
                notifyDataSetChanged();
                dialog.dismiss();
            });
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ThanhVienViewHolder extends RecyclerView.ViewHolder {
        TextView tvMTV,tvTen,tvDiaChi,tvTel;
        ImageView btnEdit,btnDelete;
        public ThanhVienViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMTV = itemView.findViewById(R.id.tvMTV);
            tvTen = itemView.findViewById(R.id.tvTen);
            tvDiaChi = itemView.findViewById(R.id.tvDiaChi);
            tvTel = itemView.findViewById(R.id.tvTel);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
