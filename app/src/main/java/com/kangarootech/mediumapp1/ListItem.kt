package com.kangarootech.mediumapp1

sealed class ListItem {
    abstract fun getType(): Int

    enum class Type {
        TITLE,
        CHAR,
        DIGIT
    }
}

abstract class SelectableListItem : ListItem() {
    abstract var isSelected: Boolean
}

data class TitleListItem(val title: String) : ListItem() {
    override fun getType() = Type.TITLE.ordinal
}

data class CharListItem(val char: String, override var isSelected: Boolean = false) : SelectableListItem() {
    override fun getType() = Type.CHAR.ordinal
}

data class DigitListItem(val digit: String, override var isSelected: Boolean = false) : SelectableListItem() {
    override fun getType() = Type.DIGIT.ordinal
}
