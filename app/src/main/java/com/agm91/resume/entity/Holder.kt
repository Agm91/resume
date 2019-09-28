package com.agm91.resume.entity

enum class Holder private constructor(var type: Int) {
    PARENT(1),
    NESTED(2)
}