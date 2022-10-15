package com.example.android_summer_2022.ASM.Fragements;

import android.app.DatePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.Fragment;

import com.example.android_summer_2022.Adapter.CatAdapter;
import com.example.android_summer_2022.DAO.CatDAO;
import com.example.android_summer_2022.DAO.ProductDAO;
import com.example.android_summer_2022.DTO.Category;
import com.example.android_summer_2022.DTO.Product;
import com.example.android_summer_2022.R;
import com.example.android_summer_2022.Utils.Helper;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.sql.Date;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link ProductFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ProductFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    private ImageView imvProduct;
    private ImageButton btnOpenDateSP;
    private TextView txtNSXSP;
    private byte[] imageSP;
    private Category catSelected = null;
    public ProductFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ProductFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static ProductFragment newInstance(String param1, String param2) {
        ProductFragment fragment = new ProductFragment();
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
        View view = inflater.inflate(R.layout.fragment_product, container, false);

        Spinner catlList = view.findViewById(R.id.listCat);

        Button btnUploadImage = view.findViewById(R.id.btnUploadImage);
        Button btnThemSP = view.findViewById(R.id.btnThemSP);
        imvProduct = view.findViewById(R.id.imvProduct);
        RadioButton rdNew = view.findViewById(R.id.rdNewSP);
        rdNew.setChecked(true);
        btnOpenDateSP = view.findViewById(R.id.btnOpenDateSP);
        txtNSXSP = view.findViewById(R.id.txtNSXSP);
        EditText txtTenSP = view.findViewById(R.id.txtTenSP);
        EditText txtGiaSP = view.findViewById(R.id.txtGiaSP);
        EditText txtGiamGiaSp = view.findViewById(R.id.txtGiamGiaSP);
        EditText motTaSP = view.findViewById(R.id.txtMoTaSP);
        txtGiamGiaSp.setText("0");
        txtGiaSP.setText("1000");

        CatDAO catDao = new CatDAO(getActivity().getBaseContext());
        List<Category> categories = catDao.getList();
        CatAdapter catAdapter = new CatAdapter(getActivity().getBaseContext(), categories);
        catlList.setAdapter(catAdapter);
        Button btnOpenCat = view.findViewById(R.id.btnOpenCat);
        catlList.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                catSelected = categories.get(i);
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        btnOpenCat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
                LayoutInflater inflater = getActivity().getLayoutInflater();
                // nhúng layout vào dialog alert
                View v = inflater.inflate(R.layout.dialog_danhmuc, null);
                Button btnThemCat = v.findViewById(R.id.btnThemCat);
                EditText txtName = v.findViewById(R.id.txtTenCat);
                builder.setTitle("thêm danh mục");
                builder.setView(v);
                builder.setPositiveButton("OK", null);
                builder.setNegativeButton("Cancel", null);

                btnThemCat.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        Log.i("TEST", "TEST");
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
                dialog.getButton(AlertDialog.BUTTON_POSITIVE).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        String name = txtName.getText().toString();
                        Log.i("TEST", "Call here" + name);
                        if (name.trim().equals("")) {
                            txtName.setError("không để trống");
                        } else {
                            boolean isCreatedCat = catDao.create(new Category(name));
                            if (isCreatedCat) {
                                FancyToast.makeText(getActivity(),"Thêm thành công",
                                        FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                                dialog.dismiss();
                            }
                        }
                    }
                });
            }

        });

        btnUploadImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
                startActivityForResult(intent,3);
            }
        });

        openDatePicker();
        btnThemSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String tenSp = txtTenSP.getText().toString();
                String giaSp = txtGiaSP.getText().toString();
                String ngaySX = txtNSXSP.getText().toString();
                int newSP = 1;
                String giamGiaSP = txtGiamGiaSp.getText().toString();
                String motTaSp = motTaSP.getText().toString();
                ProductDAO productDAO = new ProductDAO(getActivity());
                Product product = new Product();
                product.setName(tenSp);
                product.setPrice(Float.parseFloat(giaSp));
                product.setDiscount(Float.parseFloat(giamGiaSP));
                Log.i("TEST", "THEM SP");
                SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy/MM/dd");
                Date date = null;
                try {
                    Log.i("TEST", ngaySX);
                     date = (Date) simpleDateFormat.parse(ngaySX);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                if(date != null) {
                    product.setExpiry_date(date);
                }
                product.setSale(rdNew.isChecked()?1:0);
                product.setImage(imageSP);
                Log.i("TEST",catSelected.toString());
                product.setCategory(catSelected);
                boolean isCreateProduct = productDAO.create(product);
                if(isCreateProduct) {
                    FancyToast.makeText(getActivity(),"Thêm SP thành công",
                            FancyToast.LENGTH_LONG,FancyToast.SUCCESS,true).show();
                }
            }
        });
        return view;

    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 3 && data != null) {
            Uri selectedImage = data.getData();
            Context applicationContext = getActivity().getApplicationContext();

            try {
                InputStream iStream  =  applicationContext.getContentResolver().openInputStream(selectedImage);
                byte[] bitArr = Helper.getBytes(iStream);
                imageSP = bitArr;
                Log.i("TEST","call here" + bitArr.toString());
                imvProduct.setImageURI(selectedImage);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }

        }
    }

    public void openDatePicker() {
        Calendar cal = Calendar.getInstance();

        DatePickerDialog.OnDateSetListener  dateEvent = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                int month = i1 +1;
                txtNSXSP.setText(i+"/"+month+"/"+i2);
                txtNSXSP.setError(null);
            }
        };

        btnOpenDateSP.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), dateEvent,
                        cal.get(Calendar.YEAR),
                        cal.get(Calendar.MONTH),
                        cal.get(Calendar.DAY_OF_MONTH));
                dialog.show();

            }
        });
    }

}
