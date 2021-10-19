package net.ukisoft.ukistreams.model.core

import javax.persistence.criteria.JoinType

/**
 * Started in IntelliJ IDEA
 * User: Vyzhanov Andrey
 * Created: 02.12.2020 18:18
 */
data class FetchField constructor(val name: String, val joinType: JoinType) {
    companion object {
        fun inner(name: String): FetchField {
            return FetchField(name, JoinType.INNER)
        }

        fun left(name: String): FetchField {
            return FetchField(name, JoinType.LEFT)
        }

        fun right(name: String): FetchField {
            return FetchField(name, JoinType.RIGHT)
        }
    }
}