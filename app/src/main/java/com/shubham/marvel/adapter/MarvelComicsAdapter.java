package com.shubham.marvel.adapter;

import static com.shubham.marvel.utils.AppConstants.GET_COMICS_DATE;
import static com.shubham.marvel.utils.AppConstants.GET_SIMPLE_DATE;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubham.marvel.R;
import com.shubham.marvel.models.comics.ComicsModel;
import com.shubham.marvel.utils.AppCommonMethods;

import java.util.List;

public class MarvelComicsAdapter extends RecyclerView.Adapter<MarvelComicsAdapter.MyViewHolder> {
    private List<ComicsModel> comicsModelList;
    private Activity activity;

    /**
     * constructor is called
     * @param comicsModelList
     * @param activity
     */
    public MarvelComicsAdapter(List<ComicsModel> comicsModelList, Activity activity) {
        this.comicsModelList = comicsModelList;
        this.activity = activity;
    }

    /**
     * view holder created for recycler view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_date, tv_description;
        private ImageView iv_image;

        public MyViewHolder(View view) {
            super(view);
            tv_date = view.findViewById(R.id.tv_date);
            tv_description = view.findViewById(R.id.tv_description);
            iv_image = view.findViewById(R.id.iv_image);
        }
    }

    /**
     * MyViewHolder attached to xml
     * @param parent
     * @param viewType
     * @return
     */
    @NonNull
    @Override
    public MarvelComicsAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_comics_list, parent, false);

        return new MarvelComicsAdapter.MyViewHolder(itemView);
    }

    /**
     * data attached in onBindViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MarvelComicsAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final ComicsModel comicsModel = comicsModelList.get(position);
        if (comicsModel != null) {
            if (comicsModel.getThumbnail().getPath() != null) {
                String photo_url = comicsModel.getThumbnail().getPath() + "/portrait_uncanny.jpg";
                Log.e("photoURL", photo_url);
                if (activity != null && holder.iv_image != null) {
                    Glide.with(activity).load(photo_url)
                            .into(holder.iv_image);
                }
            }

            holder.tv_date.setText(AppCommonMethods.convertTimeFormat1(GET_COMICS_DATE, GET_SIMPLE_DATE,
                    comicsModel.getModified()));
            if (comicsModel.getDescription() != null) {
                holder.tv_description.setText(comicsModel.getDescription());
            }
            else {
                holder.tv_description.setText(R.string.no_description);
            }
        }
    }

    /**
     * list size get called
     * @return
     */
    @Override
    public int getItemCount() {
        return comicsModelList == null ? 0 : comicsModelList.size();
    }
}
