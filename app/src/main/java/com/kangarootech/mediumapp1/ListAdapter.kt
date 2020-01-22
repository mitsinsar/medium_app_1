package com.kangarootech.mediumapp1

import android.view.ViewGroup
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.kangarootech.mediumapp1.ListItem.Type.CHAR
import com.kangarootech.mediumapp1.ListItem.Type.DIGIT
import com.kangarootech.mediumapp1.ListItem.Type.TITLE
import kotlin.properties.Delegates

class ListAdapter : RecyclerView.Adapter<SelectableViewHolder>() {

    private val items = mutableListOf<ListItem>()
    private var selectedCharPosition by Delegates.observable<Int>(RecyclerView.NO_POSITION) { _, oldValue, newValue ->
        handleItemSelection<CharListItem>(oldValue, newValue)
    }
    private var selectedDigitPosition by Delegates.observable<Int>(RecyclerView.NO_POSITION) { _, oldValue, newValue ->
        handleItemSelection<DigitListItem>(oldValue, newValue)
    }

    override fun onAttachedToRecyclerView(recyclerView: RecyclerView) {
        super.onAttachedToRecyclerView(recyclerView)
        (recyclerView.layoutManager as GridLayoutManager).spanSizeLookup = getSpanSizeLookup()
    }

    override fun getItemViewType(position: Int) = items[position].getType()

    override fun getItemCount() = items.size

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SelectableViewHolder {
        return when (viewType) {
            TITLE.ordinal -> TitleViewHolder.create(parent)
            CHAR.ordinal -> CharViewHolder.create(parent).apply {
                itemView.setOnClickListener { selectedCharPosition = adapterPosition }
            }
            DIGIT.ordinal -> DigitViewHolder.create(parent).apply {
                itemView.setOnClickListener { selectedDigitPosition = adapterPosition }
            }
            else -> throw Exception(" $LOG_TAG Unknown view type exception")
        }
    }

    override fun onBindViewHolder(holder: SelectableViewHolder, position: Int) {
        when (holder) {
            is DigitViewHolder -> holder.bind(items[position] as DigitListItem)
            is CharViewHolder -> holder.bind(items[position] as CharListItem)
            is TitleViewHolder -> holder.bind(items[position] as TitleListItem)
            else -> throw Exception(" $LOG_TAG Unknown view type exception")
        }
    }

    override fun onBindViewHolder(holder: SelectableViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            payloads.forEach {
                when (it) {
                    ITEM_SELECTION_PAYLOAD -> holder.bind((items[position] as SelectableListItem).isSelected)
                }
            }
        }
    }

    fun setItems(newItem: List<ListItem>) {
        items.apply {
            clear()
            addAll(newItem)
        }
        notifyDataSetChanged()
    }

    private inline fun <reified T> handleItemSelection(oldValue: Int, newValue: Int) {
        if (oldValue != RecyclerView.NO_POSITION) {
            setSelectedItem<T>(oldValue, isSelected = false)
        }
        setSelectedItem<T>(newValue, isSelected = true)
    }

    private inline fun <reified T> setSelectedItem(position: Int, isSelected: Boolean) {
        when (T::class) {
            DigitListItem::class, CharListItem::class -> (items[position] as SelectableListItem).isSelected = isSelected
            else -> throw Exception("$LOG_TAG Unknown class type exception")
        }
        notifyItemChanged(position, ITEM_SELECTION_PAYLOAD)
    }

    private fun getSpanSizeLookup(): GridLayoutManager.SpanSizeLookup {
        return object : GridLayoutManager.SpanSizeLookup() {
            override fun getSpanSize(position: Int): Int {
                return if (items[position] is TitleListItem) {
                    SPAN_SIZE
                } else {
                    DEFAULT_SPAN_SIZE
                }
            }
        }
    }

    companion object {
        const val SPAN_SIZE = 3
        private const val DEFAULT_SPAN_SIZE = 1
        private val LOG_TAG = ListAdapter::class.java.simpleName
        private const val ITEM_SELECTION_PAYLOAD = "item_selection_payload"
    }
}
