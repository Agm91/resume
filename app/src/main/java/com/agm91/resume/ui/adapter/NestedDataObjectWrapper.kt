package com.agm91.resume.ui.adapter

import com.agm91.resume.entity.interfa.HasType
import com.agm91.resume.entity.Holder
import com.agm91.resume.entity.Ndo

class NestedDataObjectWrapper(internal var nestedDataObjectList: List<Ndo>) : HasType {

    override val type: Int
        get() = Holder.NESTED.type
}
