package com.agm91.resume.entity

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("bullets", "title")
class Other {

    @JsonProperty("bullets")
    @get:JsonProperty("bullets")
    @set:JsonProperty("bullets")
    lateinit var bullets: List<Bullet>
    @JsonProperty("title")
    @get:JsonProperty("title")
    @set:JsonProperty("title")
    lateinit var title: String
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    val otherAsNdo: Ndo
        get() {
            val stringBuilderText = StringBuilder()
            for (bullet in bullets) {
                stringBuilderText.append(bullet.text)
                stringBuilderText.append("\n")
            }

            return Ndo(title, stringBuilderText.toString())
        }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param title
     * @param bullets
     */
    constructor(bullets: List<Bullet>, title: String) : super() {
        this.bullets = bullets
        this.title = title
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
