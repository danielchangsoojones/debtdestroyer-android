package com.debtdestroyer.android.utils.media

import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.transform.RoundedCornersTransformation
import com.debtdestroyer.android.R
//import com.bumptech.glide.RequestManager
import com.debtdestroyer.android.databinding.ItemFeedBinding
import com.debtdestroyer.android.model.MediaObject
import com.debtdestroyer.android.model.WinnersParse

class PlayerViewHolder(private val binding: ItemFeedBinding, val parent: ViewGroup) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        mediaObject: WinnersParse,
        index: Int,
        onItemClick: (WinnersParse) -> Unit,
        //requestManager: RequestManager
    ) {
        binding.apply {
            item = mediaObject
            parent.tag = this
            //requestManager.load(mediaObject.coverUrl).into(binding.ivMediaCoverImage)
            /*ivMediaCoverImage.load(mediaObject) {
                crossfade(true)
                placeholder(R.drawable.logoicon)
                transformations(RoundedCornersTransformation())
            }*/
            //root.setOnClickListener { onItemClick(mediaObject) }
        }
    }
}