package com.srvraj311.smart_health_management.HospitalScreen;

import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListAdapter;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.textfield.TextInputEditText;
import com.srvraj311.smart_health_management.DataSets.DataSetsHospital;
import com.srvraj311.smart_health_management.R;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.zip.Inflater;


public class DistrictSelectorDialog extends DialogFragment {

    HospitalScreen hospitalScreen;
    Context context;

    public DistrictSelectorDialog(HospitalScreen hospitalScreen, Context context) {
        this.hospitalScreen = hospitalScreen;
        this.context = context;
    }

    RecyclerView rv;
    Button cancel;
    TextInputEditText searchText;
    DistrictAdapter adapter;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Nullable
    @org.jetbrains.annotations.Nullable
    @Override
    public View onCreateView(@NonNull @NotNull LayoutInflater inflater,
                             @Nullable @org.jetbrains.annotations.Nullable ViewGroup container,
                             @Nullable @org.jetbrains.annotations.Nullable Bundle savedInstanceState) {


        getDialog().requestWindowFeature(Window.FEATURE_NO_TITLE);
        getDialog().getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        setStyle(DialogFragment.STYLE_NO_FRAME, android.R.style.Theme);
        View rootView = inflater.inflate(R.layout.district_selector, container);
        rv = rootView.findViewById(R.id.recycler_district);
        adapter = new DistrictAdapter(DataSetsHospital.getCity_names(), DistrictSelectorDialog.this, hospitalScreen);
        rv.setLayoutManager(new LinearLayoutManager(context));
        rv.setAdapter(adapter);


        cancel = rootView.findViewById(R.id.district_cancel);
        cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Objects.requireNonNull(getDialog()).cancel();
            }
        });

        searchText = rootView.findViewById(R.id.search_district);

        searchText.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String[] dists = DataSetsHospital.getCity_names();
                ArrayList<String> arr = new ArrayList<>();
                for (String city : dists) {
                    if (city.toLowerCase().contains(s)) {
                        arr.add(city);
                    }
                }
                adapter.setData(arr.toArray(new String[0]));
            }

            @Override
            public void afterTextChanged(Editable s) {
            }
        });


        return rootView;
    }
}
