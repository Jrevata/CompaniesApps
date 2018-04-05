package com.companies.jrevata.companiesapps.Adapters;

import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.companies.jrevata.companiesapps.DetailActivity;
import com.companies.jrevata.companiesapps.Models.Company;
import com.companies.jrevata.companiesapps.R;
import com.companies.jrevata.companiesapps.activities.MainActivity;
import com.companies.jrevata.companiesapps.activities.SearchResultActivity;

import java.util.List;

public class CompanyAdapter extends RecyclerView.Adapter<CompanyAdapter.ViewHolder>{

    private List<Company> companies;

    public void setCompanies(List<Company> companies) {
        this.companies = companies;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView pictureImage;
        TextView name;
        TextView address;
        TextView phone;

        public ViewHolder(View itemView) {
            super(itemView);
            pictureImage = itemView.findViewById(R.id.picture_image);
            name= itemView.findViewById(R.id.txtname);
            address = itemView.findViewById(R.id.txtaddress);
            phone = itemView.findViewById(R.id.txtphone);
        }
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.activity_items_companies, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }


    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {
        final Company company = companies.get(position);

        int resId = holder.itemView.getContext().getResources().getIdentifier(company.getLogo(), "drawable", holder.itemView.getContext().getPackageName());
        holder.pictureImage.setImageResource(resId);

        holder.name.setText(company.getName());
        holder.address.setText(company.getAddress());
        holder.phone.setText(company.getPhone());

        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                Intent intent = new Intent(holder.itemView.getContext(),DetailActivity.class);
                intent.putExtra("id", company.getId());
                holder.itemView.getContext().startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return companies.size();
    }

}
