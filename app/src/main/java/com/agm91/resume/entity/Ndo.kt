package com.agm91.resume.entity

import com.agm91.resume.entity.interfa.HasType

class Ndo : HasType {
    var title: String
    var text: String? = null

    override val type: Int
        get() = Holder.NESTED.type

    constructor(title: String) {
        this.title = title
    }

    constructor(title: String, text: String) {
        this.title = title
        this.text = text
    }
}