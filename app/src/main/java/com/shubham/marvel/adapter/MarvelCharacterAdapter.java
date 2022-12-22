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

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;

public class MarvelCharacterAdapter extends RecyclerView.Adapter<MarvelCharacterAdapter.MyViewHolder> {
    private List<CharacterModel> characterModelList;
    private Activity activity;
    private CharacterRepository characterRepository;
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
        this.characterRepository = characterRepository;
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
            String photo_url = null;
            if (characterModel.getThumbnail().getPath() != null) {
                photo_url = characterModel.getThumbnail().getPath() + "/landscape_xlarge.jpg";
                String[] imageName = photo_url.split("/");
                if (activity != null && holder.iv_image != null) {
                    Glide.with(activity)
                                    .load(photo_url)
                                            .into(holder.iv_image);
                    Glide.with(activity)
                            .asBitmap()
                            .load(photo_url)
                            .into(new SimpleTarget<Bitmap>() {
                                @Override
                                public void onResourceReady(@NonNull Bitmap resource, @Nullable Transition<? super Bitmap> transition) {
                                    saveToInternalStorage(resource, imageName[10], characterModel);
                                }
                            });
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

    private String saveToInternalStorage(Bitmap bitmapImage, String imageName, CharacterModel characterModel){
        ContextWrapper cw = new ContextWrapper(activity);
        // path to /data/data/yourapp/app_data/imageDir
        File directory = cw.getDir("Marvel Photos", Context.MODE_PRIVATE);
        // Create imageDir
        File mypath=new File(directory,imageName + ".jpg");

        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(mypath);
            // Use the compress method on the BitMap object to write image to the OutputStream
            bitmapImage.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        addDataToRoomDataBase(characterModel, mypath.getAbsolutePath());
        return directory.getAbsolutePath();
    }

    private void addDataToRoomDataBase(CharacterModel characterModel, String imagePath) {
        characterModel.setPhotos(imagePath);
        characterRepository.insertUserData(characterModel);
        Log.e("character", "added");
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
