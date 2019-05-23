package com.example.basemvvm.ui

import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.example.basemvvm.R
import com.example.basemvvm.data.datamodels.ShowInfo
import com.example.basemvvm.extensions.inflate
import kotlinx.android.synthetic.main.show_list_item.view.showImage
import kotlinx.android.synthetic.main.show_list_item.view.showTitle

class ShowInfoListAdapter(
    private val showInfoList: List<ShowInfo>,
    val clickListener: (Int) -> Unit) : RecyclerView.Adapter<ShowInfoListAdapter.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = parent.inflate(R.layout.show_list_item)
        return ViewHolder(view)
    }

    override fun getItemCount(): Int = showInfoList.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val showInfo = showInfoList[position]
        val image = showInfo.show?.image?.medium
        holder.itemView.apply {
            setOnClickListener {
                clickListener(position)
            }
            if (image != null) {
                Glide
                    .with(holder.itemView)
                    .load(image)
                    .centerCrop()
                    .apply(RequestOptions().override(100, 100))
                    .placeholder(R.drawable.ic_broken_image)
                    .into(showImage)
            } else {
                showImage.setImageDrawable(context.getDrawable(R.drawable.ic_broken_image))
            }

            showTitle.text = showInfo.show?.name
        }
    }

    class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView)

}