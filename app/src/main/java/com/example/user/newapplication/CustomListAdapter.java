package com.example.user.newapplication;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class CustomListAdapter extends RecyclerView.Adapter<CustomListAdapter.ViewHolder>{

    Context context;
    ArrayList<LoanInfo> loans;

    public CustomListAdapter(Context context, ArrayList<LoanInfo> loans){
        this.context = context;
        this.loans = loans;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.list_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        Picasso.get().load(loans.get(position).photo).into(holder.logo);

        holder.percent.setText(loans.get(position).credit_interest_rate);
        holder.loan_sum.setText(loans.get(position).credit_max_sum);
        holder.loan_period.setText(loans.get(position).credit_max_period);
        holder.required_docs.setText(loans.get(position).credit_required_documents);
        holder.consideration.setText(loans.get(position).credit_consideration_time);
        holder.age.setText(loans.get(position).credit_age);
        holder.name_category.setText(loans.get(position).name_category);

        holder.loan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (loans.get(position).ads != null){
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(loans.get(position).ads));
                    context.startActivity(browserIntent);
                }else{
                    Toast.makeText(context, "Ссылка не существует", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    @Override
    public int getItemCount() {
        return loans.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        @BindView(R.id.logo)
        ImageView logo;
        @BindView(R.id.percent)
        TextView percent;
        @BindView(R.id.loan_sum)
        TextView loan_sum;
        @BindView(R.id.loan_period)
        TextView loan_period;
        @BindView(R.id.required_docs)
        TextView required_docs;
        @BindView(R.id.consideration)
        TextView consideration;
        @BindView(R.id.age)
        TextView age;
        @BindView(R.id.name_category)
        TextView name_category;
        @BindView(R.id.loan)
        Button loan;

        public ViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }
}
