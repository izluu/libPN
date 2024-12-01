package izisluu.fpoly.librarymanagerment.Adapter;

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

import izisluu.fpoly.librarymanagerment.DAO.LoaiSachDAO;
import izisluu.fpoly.librarymanagerment.Model.LoaiSach;
import izisluu.fpoly.librarymanagerment.R;

public class AdapterLoaiSach extends RecyclerView.Adapter<AdapterLoaiSach.LoaiSachViewHolder> {
    private Context context;
    private ArrayList<LoaiSach> list;
    private LoaiSachDAO dao;
    public AdapterLoaiSach(Context context, ArrayList<LoaiSach> list) {
        this.context = context;
        this.list = list;
        dao = new LoaiSachDAO(context);
    }

    @NonNull
    @Override
    public LoaiSachViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.customrcv_loaisach,null);
        LoaiSachViewHolder holder = new LoaiSachViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull LoaiSachViewHolder holder, int position) {
        LoaiSach loaiSach = list.get(position);
        holder.tvMaLoaiSach.setText(loaiSach.getMaLoaiSach());
        holder.tvTenLoaiSach.setText(loaiSach.getTenLoaiSach());
        holder.tvNickname.setText(loaiSach.getNickname());
        holder.itemView.setLayoutParams(new RecyclerView.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT
        ));
        holder.btnDelete.setOnClickListener(v -> {
            dao.delete(loaiSach.getMaLoaiSach());
            list.remove(loaiSach);
            notifyDataSetChanged();
        });
        holder.btnEdit.setOnClickListener(v -> {
            MaterialAlertDialogBuilder builder = new MaterialAlertDialogBuilder(context);
            LayoutInflater inflater = LayoutInflater.from(context);
            View view = inflater.inflate(R.layout.dialog_edit_loaisach,null);
            builder.setView(view);
            Dialog dialog = builder.create();
            dialog.setCancelable(false);
            dialog.show();
            TextInputLayout edtTenLS = view.findViewById(R.id.edtTenLS);
            TextInputLayout edtNickname = view.findViewById(R.id.edtNickname);
            Button btnSaveLS = view.findViewById(R.id.btnSaveLS);
            Button btnCancel = view.findViewById(R.id.btnCancel);
            edtTenLS.getEditText().setText(loaiSach.getTenLoaiSach());
            edtNickname.getEditText().setText(loaiSach.getNickname());
            btnSaveLS.setOnClickListener(v1 -> {
                loaiSach.setTenLoaiSach(edtTenLS.getEditText().getText().toString());
                loaiSach.setNickname(edtNickname.getEditText().getText().toString());
                dao.update(loaiSach);
                notifyDataSetChanged();
                dialog.dismiss();
            });
            btnCancel.setOnClickListener(v1 -> {
                dialog.dismiss();
            });
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class LoaiSachViewHolder extends RecyclerView.ViewHolder{
        private TextView tvMaLoaiSach;
        private TextView tvTenLoaiSach;
        private TextView tvNickname;
        private ImageView btnEdit;
        private ImageView btnDelete;

        public LoaiSachViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMaLoaiSach = itemView.findViewById(R.id.tvMLS);
            tvTenLoaiSach = itemView.findViewById(R.id.tvTenLoaiSach);
            tvNickname = itemView.findViewById(R.id.tvNickname);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }

    }
}
