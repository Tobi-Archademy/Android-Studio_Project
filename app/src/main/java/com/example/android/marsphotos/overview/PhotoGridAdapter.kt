package com.example.android.marsphotos.overview

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.marsphotos.databinding.GridViewItemBinding
import com.example.android.marsphotos.network.MarsPhotos


class PhotoGridAdapter : ListAdapter<MarsPhotos,
        PhotoGridAdapter.MarsPhotosViewHolder>(DiffCallback) {
    class MarsPhotosViewHolder(
        private var binding:
        GridViewItemBinding,
    ) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(MarsPhotos: MarsPhotos) {
            binding.photo = MarsPhotos
            binding.executePendingBindings()
        }

    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int,
    ): PhotoGridAdapter.MarsPhotosViewHolder {
        return MarsPhotosViewHolder(GridViewItemBinding
            .inflate(LayoutInflater.from(parent.context)))
    }

    override fun onBindViewHolder(holder: PhotoGridAdapter.MarsPhotosViewHolder, position: Int) {
        val marsphoto = getItem(position)
        holder.bind(marsphoto)
    }

    companion object DiffCallback : DiffUtil.ItemCallback<MarsPhotos>() {
        override fun areItemsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: MarsPhotos, newItem: MarsPhotos): Boolean {
            return oldItem.imgSrcUrl == newItem.imgSrcUrl
        }
    }
}