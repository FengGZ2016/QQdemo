package com.example.administrator.qqdemo.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.administrator.qqdemo.R;
import com.example.administrator.qqdemo.model.ContactListItem;

import java.util.List;

import static android.view.View.GONE;
import static android.view.View.VISIBLE;

/**
 * Created by Administrator on 2017/5/26.
 */

public class ContactAdapter extends RecyclerView.Adapter<ContactAdapter.ViewHolder>{
    private Context mContext;
    private List<ContactListItem> mContactListItems;

    public ContactAdapter(Context mContext,List<ContactListItem> mContactListItems){
        this.mContext=mContext;
        this.mContactListItems=mContactListItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_list_item,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final ContactListItem contactListItem=mContactListItems.get(position);
        holder.mTextView_contact.setText(contactListItem.contact);
        if (contactListItem.showFirstLetter) {
            holder.mTextView_first_letter.setVisibility(VISIBLE);
            holder.mTextView_first_letter.setText(contactListItem.getFirstLetter());
        } else {
            holder.mTextView_first_letter.setVisibility(GONE);
        }
        holder.contactView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //跳转到聊天界面
          //      Intent intent = new Intent(mContext, ChatActivity.class);
                //传入联系人的名字
            //    intent.putExtra("contact", contactListItem.contact);
            //    mContext.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return mContactListItems.size();
    }


    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView mTextView_first_letter;
        TextView mTextView_contact;
        View contactView;

        public ViewHolder(View itemView) {
            super(itemView);
            contactView=itemView;
            mTextView_first_letter= (TextView) itemView.findViewById(R.id.first_letter);
            mTextView_contact= (TextView) itemView.findViewById(R.id.contact);
        }
    }
}
