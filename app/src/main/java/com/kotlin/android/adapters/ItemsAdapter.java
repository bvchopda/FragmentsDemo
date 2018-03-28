package com.kotlin.android.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

/**
 * Name : ReceivingVerifyInvoicesAdapter
 * <br/> Created by 1694 on 21/8/17
 * <br/> Modified by 1694 on 21/8/17
 * <br/> Purpose : To show all started invoices in ReceivingVerifyFragment
 */
public class ItemsAdapter extends RecyclerView.Adapter<ItemsAdapter.ViewHolder> {
    private List<String> mList;

    public ItemsAdapter(List<String> aList)
    {
        this.mList = aList;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType)
    {
        return new ViewHolder(LayoutInflater.from(parent.getContext()).inflate(android.R.layout.simple_list_item_1, parent, false));
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position)
    {
        holder.bind(mList.get(position));
    }

    @Override
    public int getItemCount()
    {
        return mList == null ? 0 : mList.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
/*        @BindView(R.id.tvVendorId)
        AppCompatTextView tvVendorId;

        @BindView(R.id.tvInvoiceNumber)
        AppCompatTextView tvInvoiceNumber;

        @BindView(R.id.tvInvoiceDate)
        AppCompatTextView tvInvoiceDate;

        @BindView(R.id.llItemView)
        LinearLayout llItemView;*/

        ViewHolder(View itemView)
        {
            super(itemView);
//            ButterKnife.bind(this, itemView);
        }

        public void bind(final String mItem)
        {
            try
            {
//                tvVendorId.setText(mItem);
                /*tvInvoiceNumber.setText(mItem.getInvoiceNumber());
                String invoiceDate = DateTimeUtils.getFormattedDate(mItem.getInvoiceDate());
                if (invoiceDate == null)
                {
                    tvInvoiceDate.setText(mItem.getInvoiceDate());
                } else
                {
                    tvInvoiceDate.setText(invoiceDate);
                }*/
            } catch (Exception e)
            {
                e.printStackTrace();
            }
        }
    }
}
