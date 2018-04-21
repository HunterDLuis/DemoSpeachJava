package com.demospeachjava.adapter;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.AppCompatButton;
import android.support.v7.widget.RecyclerView;
import android.view.*;
import android.widget.ImageButton;
import android.widget.TextView;

import com.demospeachjava.R;
import com.demospeachjava.activity.contact.EditContact;
import com.demospeachjava.activity.contact.ViewContact;
import com.demospeachjava.helper.ContactHelper;
import com.demospeachjava.model.People;
import com.squareup.picasso.Picasso;
import de.hdodenhof.circleimageview.CircleImageView;

import java.util.List;

public class AdapterPeopleLeft extends RecyclerView.Adapter<AdapterPeopleLeft.ViewHolder> {

    private List<People> mPeopleList;
    private Context mContext;
    private RecyclerView mRecyclerV;
    private final static int PHOTO_SELECTED = 1;

    public class ViewHolder extends RecyclerView.ViewHolder {
        // each data item is just a string in this case
        public CircleImageView image;
        public TextView item_left_name;
        public TextView item_left_cell;
        public TextView item_left_type;
        public View lyt_parent;

        public ViewHolder(View v) {
            super(v);
            image = (CircleImageView) v.findViewById(R.id.image);
            item_left_name = (TextView) v.findViewById(R.id.item_left_name);
            item_left_cell = (TextView) v.findViewById(R.id.item_left_cell);
            item_left_type = (TextView) v.findViewById(R.id.item_left_tyoe);
            lyt_parent = (View) v.findViewById(R.id.lyt_parent);
        }
    }

    public void add(int position, People person) {
        mPeopleList.add(position, person);
        notifyItemInserted(position);
    }

    public void remove(int position) {
        mPeopleList.remove(position);
        notifyItemRemoved(position);
    }

    public AdapterPeopleLeft(List<People> mDataset,Context context, RecyclerView recyclerView) {
        mPeopleList = mDataset;
        mContext = context;
        mRecyclerV = recyclerView;
    }

    @Override
    public AdapterPeopleLeft.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View v = inflater.inflate(R.layout.item_people_left, parent, false);
        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    // Replace the contents of a view (invoked by the layout manager)
    @Override
    public void onBindViewHolder(ViewHolder holder, final int position) {
        final People people = mPeopleList.get(position);
        holder.item_left_name.setText(people.getFirsname());
        holder.item_left_cell.setText(people.getCell_phone());
        holder.item_left_type.setText(people.getType_register());
        //Picasso.with(mContext).load(people.getAvatar()).placeholder(R.drawable.ic_face).into(holder.image);

        holder.lyt_parent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final Dialog dialog = new Dialog(mContext);
                dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); // before
                dialog.setContentView(R.layout.dialog_dark);
                dialog.setCancelable(true);

                WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
                lp.copyFrom(dialog.getWindow().getAttributes());
                lp.width = WindowManager.LayoutParams.WRAP_CONTENT;
                lp.height = WindowManager.LayoutParams.WRAP_CONTENT;

                ((TextView) dialog.findViewById(R.id.title_firsname)).setText(people.getFirsname());
                //((CircleImageView) dialog.findViewById(R.id.image)).setT(people.getAvatar());

                ((ImageButton) dialog.findViewById(R.id.bt_close)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });

                ((AppCompatButton) dialog.findViewById(R.id.bt_view_content)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToViewActivity(people.getId());                        }
                });

                ((AppCompatButton) dialog.findViewById(R.id.bt_edit_content)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        goToUpdateActivity(people.getId());                       }
                });

                ((AppCompatButton) dialog.findViewById(R.id.bt_delete_content)).setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        ContactHelper dbHelper = new ContactHelper(mContext);
                        dbHelper.deletePeople(people.getId(), mContext);

                        mPeopleList.remove(position);
                        mRecyclerV.removeViewAt(position);
                        notifyItemRemoved(position);
                        notifyItemRangeChanged(position, mPeopleList.size());
                        notifyDataSetChanged();
                        dialog.dismiss();
                    }
                });

                dialog.show();
                dialog.getWindow().setAttributes(lp);
            }
        });

    }

    private void goToViewActivity(long personId){
        Intent goToView = new Intent(mContext, ViewContact.class);
        goToView.putExtra("CONTACT_ID", personId);
        mContext.startActivity(goToView);
    }
    private void goToUpdateActivity(long personId){
        Intent goToUpdate = new Intent(mContext, EditContact.class);
        goToUpdate.putExtra("CONTACT_ID", personId);
        mContext.startActivity(goToUpdate);
    }

    @Override
    public int getItemCount() {
        return mPeopleList.size();
    }

}