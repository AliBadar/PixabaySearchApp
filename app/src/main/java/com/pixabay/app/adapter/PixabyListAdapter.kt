package com.pixabay.app.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import butterknife.BindView
import butterknife.ButterKnife
import com.bumptech.glide.Glide
import com.pixabay.app.R
import com.pixabay.app.models.Hit

class PixabyListAdapter(private val context: Context, private var datalist: List<Hit>?, private var itemClick: ItemClick) :
RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val viewHolder: RecyclerView.ViewHolder
        val inflater = LayoutInflater.from(parent.context)
        val view = inflater.inflate(R.layout.hit_list_item, parent, false)
        viewHolder = ItemRow(view)
        return viewHolder
    }

    //Setting the values to the Item Views
    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        val itemRow = holder as ItemRow

        var obj = datalist?.get(position)

        if (obj != null) {
            Glide.with(context)
                .load(obj.previewURL)
                .into(itemRow.ivHit)

            itemRow.tvTitle.text = obj.user
            itemRow.tvViews.text = context.getString(R.string.views) + " " + obj.views
            itemRow.tvDownloads.text = obj.downloads.toString()
            itemRow.tvLike.text = obj.likes.toString()

            itemRow.itemView.setOnClickListener { itemClick.onAdapterItemClick(datalist!!.get(position)) }
        }
    }

    override fun getItemCount(): Int {
        return datalist!!.size
    }

    inner class ItemRow(itemView: View) : RecyclerView.ViewHolder(itemView) {
        @BindView(R.id.ivHit)
        lateinit var ivHit: ImageView

        @BindView(R.id.tvTitle)
        lateinit var tvTitle: TextView;

        @BindView(R.id.tvViews)
        lateinit var tvViews: TextView;

        @BindView(R.id.tvLike)
        lateinit var tvLike: TextView;

        @BindView(R.id.tvDownloads)
        lateinit var tvDownloads: TextView;
        init {
            ButterKnife.bind(this, itemView)
            itemView.tag = this
        }
    }

    fun getItem(pos: Int): Hit {
        return datalist!![pos]
    }

    fun setItemsData(datalist: List<Hit>) {
        this.datalist = datalist
        notifyDataSetChanged()
    }

    interface ItemClick{
        fun onAdapterItemClick(hit: Hit);
    }
}