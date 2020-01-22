package com.kangarootech.mediumapp1

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_title.view.itemTitleTextView

class TitleViewHolder(itemView: View) : SelectableViewHolder(itemView) {

    private val titleTextView = itemView.itemTitleTextView

    fun bind(titleListItem: TitleListItem) {
        titleTextView.text = titleListItem.title
    }

    override fun bind(isSelected: Boolean) {}

    companion object {
        fun create(parent: ViewGroup): TitleViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_title, parent, false)
            return TitleViewHolder(view)
        }
    }
}
