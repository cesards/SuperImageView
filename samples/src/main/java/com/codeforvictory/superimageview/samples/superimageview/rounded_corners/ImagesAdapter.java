package com.codeforvictory.superimageview.samples.superimageview.rounded_corners;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.codeforvictory.android.superimageview.SuperImageView;
import com.codeforvictory.android.superimageview.crop.CroppedImage;
import com.codeforvictory.superimageview.samples.superimageview.R;

import java.util.ArrayList;
import java.util.List;

import androidx.annotation.NonNull;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

final class ImagesAdapter extends RecyclerView.Adapter<ImagesAdapter.ItemImageViewHolder> {

    private final List<Image> images = new ArrayList<>();

    void removeAll() {
        if (images.isEmpty()) {
            return;
        }
        int imagesLength = images.size();
        images.clear();
        notifyItemRangeRemoved(0, imagesLength);
    }

    void add(Image image) {
        images.add(image);
        notifyItemInserted(this.images.size() - 1);
    }

    void add(List<Image> images) {
        this.images.addAll(images);
        notifyItemRangeInserted(this.images.size() - 1, images.size());
    }

    @NonNull
    @Override
    public ItemImageViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemImageViewHolder(LayoutInflater.from(
                parent.getContext()).inflate(R.layout.item_image, parent, false)
        );
    }

    @Override
    public void onBindViewHolder(@NonNull ItemImageViewHolder itemImageViewHolder, int position) {
        itemImageViewHolder.bind(images.get(position));
    }

    @Override
    public int getItemCount() {
        return images.size();
    }

    static class ItemImageViewHolder extends RecyclerView.ViewHolder {

        private final SuperImageView superImageView;

        ItemImageViewHolder(@NonNull View itemView) {
            super(itemView);
            this.superImageView = (SuperImageView) itemView;
        }

        void bind(Image image) {
            superImageView.getFeature(CroppedImage.class).setCropType(image.cropType());
            superImageView.setImageDrawable(ContextCompat.getDrawable(itemView.getContext(), image.drawableResource()));
        }
    }
}
