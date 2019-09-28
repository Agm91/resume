package com.agm91.resume.entity

import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder

import java.util.HashMap

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("text")
class Education {

    @JsonProperty("text")
    @get:JsonProperty("text")
    @set:JsonProperty("text")
    lateinit var text: String
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param text
     */
    constructor(text: String) : super() {
        this.text = text
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
