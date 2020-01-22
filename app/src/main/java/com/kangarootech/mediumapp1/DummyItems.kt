package com.kangarootech.mediumapp1

object DummyItems {
    fun getItems() = mutableListOf<ListItem>().apply {
        add(TitleListItem("Char Items Title"))
        repeat(8) {
            add(CharListItem(('a'.toInt() + it).toChar().toString()))
        }
        add(TitleListItem("Digit Items Title"))
        repeat(6) {
            add(DigitListItem(it.toString()))
        }
    }
}
