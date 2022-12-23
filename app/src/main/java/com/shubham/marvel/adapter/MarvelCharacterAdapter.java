package com.shubham.marvel.adapter;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.media.MediaScannerConnection;
import android.os.Environment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.transition.Transition;
import com.shubham.marvel.R;
import com.shubham.marvel.database.repositoryDB.CharacterRepository;
import com.shubham.marvel.models.character.CharacterModel;
import com.shubham.marvel.utils.AppCommonMethods;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
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
                               MarvelCharacterAdapter.OnClickInterface onClickInterface, CharacterRepository characterRepository) {
        this.characterModelList = characterModelList;
        this.activity = activity;
        this.onClickInterface = onClickInterface;
    }

    /**
     * view holder created for recycler view
     */
    public class MyViewHolder extends RecyclerView.ViewHolder {
        private TextView tv_name;
        private ImageView iv_image, iv_bookmark;
        private LinearLayout ll_main;

        public MyViewHolder(View view) {
            super(view);
            tv_name = view.findViewById(R.id.tv_name);
            iv_image = view.findViewById(R.id.iv_image);
            iv_bookmark = view.findViewById(R.id.iv_bookmark);
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
            if (AppCommonMethods.isNetworkAvailable(activity)) {
                if (characterModel.getThumbnail().getPath() != null) {
                    String photo_url = characterModel.getThumbnail().getPath() + "/landscape_xlarge.jpg";
                    if (activity != null && holder.iv_image != null) {
                        Glide.with(activity)
                                .load(photo_url)
                                .into(holder.iv_image);
                    }
                }
            }
            else {
                if (characterModel.getPhotos() != null) {
                    String photo_url = characterModel.getPhotos();
                    if (activity != null && holder.iv_image != null) {
                        Glide.with(activity)
                                .load(photo_url)
                                .into(holder.iv_image);
                    }
                }

                if (characterModel.getIsSelected().equalsIgnoreCase("yes")) {
                    holder.iv_bookmark.setVisibility(View.VISIBLE);
                }
                else {
                    holder.iv_bookmark.setVisibility(View.INVISIBLE);
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

        holder.ll_main.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View view) {
                if (holder.iv_bookmark.getVisibility() == View.VISIBLE) {
                    holder.iv_bookmark.setVisibility(View.INVISIBLE);
                    onClickInterface.longClickListener(view, position, characterModel, "no");
                }
                else {
                    holder.iv_bookmark.setVisibility(View.VISIBLE);
                    onClickInterface.longClickListener(view, position, characterModel, "yes");
                }
                return true;
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
        void longClickListener(View v, int position, CharacterModel characterModel, String booked);
    }
}
