package com.shubham.marvel.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.shubham.marvel.R;
import com.shubham.marvel.models.character.CharacterModel;

import java.util.List;

public class MarvelCharacterAdapter extends RecyclerView.Adapter<MarvelCharacterAdapter.MyViewHolder> {
    private List<CharacterModel> characterModelList;
    private Activity activity;
    private MarvelCharacterAdapter.OnClickInterface onClickInterface;

    /**
     * constructor is called
     * @param characterModelList
     * @param activity
     * @param onClickInterface
     */
    public MarvelCharacterAdapter(List<CharacterModel> characterModelList, Activity activity,
                               MarvelCharacterAdapter.OnClickInterface onClickInterface) {
        this.characterModelList = characterModelList;
        this.activity = activity;
        this.onClickInterface = onClickInterface;
    }

    /**
     * view holder created for recycler view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView iv_image;
        private LinearLayout ll_main;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            iv_image = view.findViewById(R.id.iv_image);
            ll_main = view.findViewById(R.id.ll_main);
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
    public MarvelCharacterAdapter.MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rcv_characters_list, parent, false);

        return new MarvelCharacterAdapter.MyViewHolder(itemView);
    }

    /**
     * data attached in onBindViewHolder
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(@NonNull MarvelCharacterAdapter.MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        final CharacterModel characterModel = characterModelList.get(position);
        if (characterModel != null) {
            if (characterModel.getThumbnail().getPath() != null) {
                String photo_url = characterModel.getThumbnail().getPath() + "/landscape_xlarge.jpg";
                if (activity != null && holder.iv_image != null) {
                    Glide.with(activity).load(photo_url)
                            .into(holder.iv_image);
                }
            }
            holder.tv_name.setText(characterModel.getName());
        }

        holder.ll_main.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickInterface.clickedOnCharacter(v, position);
            }
        });
    }

    /**
     * list size get called
     * @return
     */
    @Override
    public int getItemCount() {
        return characterModelList == null ? 0 : characterModelList.size();
    }

    /**
     * interface created for onClick
     */
    public interface OnClickInterface {
        void clickedOnCharacter(View v, int position);
    }
}
