package com.agm91.resume.entity

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("bullets", "place", "rol")
class Experience {

    @JsonProperty("bullets")
    @get:JsonProperty("bullets")
    @set:JsonProperty("bullets")
    lateinit var bullets: List<Bullet>
    @JsonProperty("place")
    @get:JsonProperty("place")
    @set:JsonProperty("place")
    var place: String? = null
    @JsonProperty("rol")
    @get:JsonProperty("rol")
    @set:JsonProperty("rol")
    var rol: String? = null
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    val experienceAsNdo: Ndo
        get() {
            val stringBuilderTitle = StringBuilder()
            stringBuilderTitle.append(rol)
            stringBuilderTitle.append("\n")
            stringBuilderTitle.append(place)

            val stringBuilderText = StringBuilder()
            bullets.forEach {
                stringBuilderText.append(it.text)
                stringBuilderText.append("\n")
            }

            return Ndo(stringBuilderTitle.toString(), stringBuilderText.toString())
        }

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param rol
     * @param place
     * @param bullets
     */
    constructor(bullets: List<Bullet>, place: String, rol: String) : super() {
        this.bullets = bullets
        this.place = place
        this.rol = rol
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
