package com.kangarootech.mediumapp1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_char.view.itemCharRootLayout
import kotlinx.android.synthetic.main.item_char.view.itemCharTextView

class CharViewHolder(itemView: View) : SelectableViewHolder(itemView) {

    private val charTextView = itemView.itemCharTextView
    private val rootLayout = itemView.itemCharRootLayout

    fun bind(charListItem: CharListItem) {
        with(charListItem) {
            charTextView.text = char
            bind(isSelected)
        }
    }

    override fun bind(isSelected: Boolean) {
        rootLayout.setBackgroundColor(if (isSelected) Color.BLUE else Color.WHITE)
    }

    companion object {
        fun create(parent: ViewGroup): CharViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_char, parent, false)
            return CharViewHolder(view)
        }
    }
}
