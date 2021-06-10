package com.srvraj311.smart_health_management.HospitalScreen;

import android.content.Context;
import android.graphics.Color;
import android.os.Build;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.recyclerview.widget.RecyclerView;

import com.srvraj311.smart_health_management.R;

import org.jetbrains.annotations.NotNull;

import java.util.List;
import java.util.zip.Inflater;

public class HospitalsAdapter extends RecyclerView.Adapter<HospitalsAdapter.ViewHolder> {

    List<Hospital> hospitals;


    public HospitalsAdapter(List<Hospital> hospitals){
        this.hospitals = hospitals;
    }

    public void setData(List<Hospital> hospitals){
        this.hospitals = hospitals;
        notifyDataSetChanged();
    }


    @NonNull
    @NotNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull @NotNull ViewGroup parent, int viewType) {
        // Define ClickListener for Each Items here. Add Hospital Launch screen Here
        Context context = parent.getContext();
        LayoutInflater inflater = LayoutInflater.from(context);

        // Inflate the custom layout
        View HospitalView = inflater.inflate(R.layout.layout_hospital_item, parent, false);

        // Assigning data To Fields


        // Return a new holder instance
        return new ViewHolder(HospitalView);
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public void onBindViewHolder(@NonNull @NotNull ViewHolder holder, int position) {
        Hospital hospital = hospitals.get(position);
        holder.title.setText(hospital.getName());
        holder.hospital_type.setText(hospital.getType());
        holder.address.setText(hospital.getAddress());

        // Hospital Timing
        String time = "";
        if(hospital.getIs_24_hr_service()){
            time = "24 x 7";
        }else {
            time = String.format("%s-%s", hospital.getOpening_time(), hospital.getClosing_time());
        }
        holder.timing.setText(time);

        // Hospital Grade
        holder.grade.setText(hospital.getGrade());
        if(hospital.getGrade().equals("A")){
            holder.grade.setTextColor(Color.parseColor("#247700"));
        }else if(hospital.getGrade().equals("B")){
            holder.grade.setTextColor(Color.parseColor("#FF9800"));
        }else {
            holder.grade.setTextColor(Color.parseColor("#FF2222"));
        }


        // Hospital Bed
        int total = Integer.parseInt(hospital.getNo_of_bed());
        int vacant = Integer.parseInt(hospital.getVacant_bed());
        float percent = total / vacant;
        holder.vacant_bed.setText(String.valueOf(vacant));

        if(percent > 0.8){
            holder.vacant_bed.setTextColor(Color.parseColor("#247700"));
        }else if(percent > 0.6){
            holder.vacant_bed.setTextColor(Color.parseColor("#FF9800"));
        }else{
            holder.vacant_bed.setTextColor(Color.parseColor("#FF2222"));
        }

        // Geolocation
        // TODO : Set Direction using API

    }

    @Override
    public int getItemCount() {
        return this.hospitals.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        // Define View Elements Here
        public TextView title;
        public TextView hospital_type;
        public TextView address;
        public TextView timing;
        public TextView grade;
        public TextView vacant_bed;
        public TextView eta;



        public ViewHolder(@NonNull @NotNull View itemView) {
            super(itemView);
            title = itemView.findViewById(R.id.hospital_name);
            hospital_type = itemView.findViewById(R.id.hospital_type);
            address = itemView.findViewById(R.id.hospital_address);
            timing = itemView.findViewById(R.id.hospital_timing);
            grade = itemView.findViewById(R.id.hospital_grade);
            vacant_bed = itemView.findViewById(R.id.hospital_bed_vacant);
            eta = itemView.findViewById(R.id.hospital_eta);
        }
    }
}
