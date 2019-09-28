package com.agm91.resume.entity

import com.agm91.resume.entity.interfa.HasType

class Pdo(val value: String) : HasType {
    override val type: Int
        get() = Holder.PARENT.type
}
