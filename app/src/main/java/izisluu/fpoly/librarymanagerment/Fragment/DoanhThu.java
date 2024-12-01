package izisluu.fpoly.librarymanagerment.Fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.fragment.app.Fragment;

import izisluu.fpoly.librarymanagerment.DAO.PhieuMuonDAO;
import izisluu.fpoly.librarymanagerment.R;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link DoanhThu#newInstance} factory method to
 * create an instance of this fragment.
 */
public class DoanhThu extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private EditText edtTuNgay;
    private EditText edtDenNgay;
    private Button btnTimKiem;
    private TextView tvDoanThu;
    private PhieuMuonDAO dao;

    public DoanhThu() {
        // Required empty public constructor
    }


    // TODO: Rename and change types and number of parameters
    public static DoanhThu newInstance(String param1, String param2) {
        DoanhThu fragment = new DoanhThu();
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
        return inflater.inflate(R.layout.fragment_doanhthu, container, false);
    }
    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtTuNgay = view.findViewById(R.id.edt_tuNgay);
        edtDenNgay = view.findViewById(R.id.edt_denNgay);
        btnTimKiem = view.findViewById(R.id.btnTimKiem);
        tvDoanThu = view.findViewById(R.id.tvDoanThu);
        dao = new PhieuMuonDAO(getContext());

        btnTimKiem.setOnClickListener(v -> {
            Double doanhThu = dao.getDoanhThu(edtTuNgay.getText().toString(), edtDenNgay.getText().toString());
            tvDoanThu.setText(doanhThu + "VND");
        });

    }
}