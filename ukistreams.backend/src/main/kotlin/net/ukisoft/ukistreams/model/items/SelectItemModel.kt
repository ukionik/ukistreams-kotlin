package net.ukisoft.ukistreams.model.items

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 17.09.2020 21:46
 */
data class SelectItemModel<TKey>(val value: TKey, val text: String)
        where TKey : Any