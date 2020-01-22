package com.kangarootech.mediumapp1

import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.item_digit.view.itemDigitRootLayout
import kotlinx.android.synthetic.main.item_digit.view.itemDigitTextView

class DigitViewHolder(itemView: View) : SelectableViewHolder(itemView) {

    private val digitTextView = itemView.itemDigitTextView
    private val rootLayout = itemView.itemDigitRootLayout

    fun bind(digitListItem: DigitListItem) {
        with(digitListItem) {
            digitTextView.text = digit
            bind(isSelected)
        }
    }

    override fun bind(isSelected: Boolean) {
        rootLayout.setBackgroundColor(if (isSelected) Color.BLUE else Color.WHITE)
    }

    companion object {
        fun create(parent: ViewGroup): DigitViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.item_digit, parent, false)
            return DigitViewHolder(view)
        }
    }
}
