package com.agm91.resume.entity

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("bullets")
class Language {

    @JsonProperty("bullets")
    @get:JsonProperty("bullets")
    @set:JsonProperty("bullets")
    lateinit var bullets: List<Bullet>
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    val languageAsNdo: List<Ndo>
        get() {
            val list = ArrayList<Ndo>()
            bullets.forEach {
                list.add(Ndo(it.text))
            }
            return list
        }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param bullets
     */
    constructor(bullets: List<Bullet>) : super() {
        this.bullets = bullets
    }

    @JsonAnyGetter
    fun getAdditionalProperties(): Map<String, Any> {
        return this.additionalProperties
    }

    @JsonAnySetter
    fun setAdditionalProperty(name: String, value: Any) {
        this.additionalProperties[name] = value
    }
}
