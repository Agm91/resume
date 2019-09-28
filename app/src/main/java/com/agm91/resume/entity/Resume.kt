package com.agm91.resume.entity

import com.agm91.resume.MyAppl
import com.agm91.resume.R
import com.agm91.resume.entity.interfa.HasType
import com.agm91.resume.ui.adapter.NestedDataObjectWrapper
import com.fasterxml.jackson.annotation.JsonAnyGetter
import com.fasterxml.jackson.annotation.JsonAnySetter
import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonInclude
import com.fasterxml.jackson.annotation.JsonProperty
import com.fasterxml.jackson.annotation.JsonPropertyOrder
import java.util.*

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonPropertyOrder("certification", "education", "experience", "language", "mail", "name", "other", "telephone")
class Resume {

    @JsonProperty("certification")
    @get:JsonProperty("certification")
    @set:JsonProperty("certification")
    lateinit var certification: Certification
    @JsonProperty("education")
    @get:JsonProperty("education")
    @set:JsonProperty("education")
    lateinit var education: List<Education>
    @JsonProperty("experience")
    @get:JsonProperty("experience")
    @set:JsonProperty("experience")
    lateinit var experience: List<Experience>
    @JsonProperty("language")
    @get:JsonProperty("language")
    @set:JsonProperty("language")
    lateinit var language: Language
    @JsonProperty("mail")
    @get:JsonProperty("mail")
    @set:JsonProperty("mail")
    lateinit var mail: String
    @JsonProperty("name")
    @get:JsonProperty("name")
    @set:JsonProperty("name")
    lateinit var name: String
    @JsonProperty("other")
    @get:JsonProperty("other")
    @set:JsonProperty("other")
    lateinit var other: List<Other>
    @JsonProperty("telephone")
    @get:JsonProperty("telephone")
    @set:JsonProperty("telephone")
    lateinit var telephone: String
    @JsonIgnore
    private val additionalProperties = HashMap<String, Any>()

    private val educationAsNdo: List<Ndo>
        get() {
            val list = ArrayList<Ndo>()
            for (education in education) {
                list.add(Ndo(education.text))
            }
            return list
        }

    private val experienceAsNdo: List<Ndo>
        get() {
            val list = ArrayList<Ndo>()
            experience.forEach {
                list.add(it.experienceAsNdo)
            }
            return list
        }

    private val otherAsNdo: List<Ndo>
        get() {
            val list = ArrayList<Ndo>()
            other.forEach {
                list.add(it.otherAsNdo)
            }
            return list
        }

    val asList: List<HasType>
        get() = listOf(Pdo(name + "\n" + mail + "\n" + telephone),
                Pdo(MyAppl.instance.getString(R.string.education)),
                NestedDataObjectWrapper(educationAsNdo),
                Pdo(MyAppl.instance.getString(R.string.experience)),
                NestedDataObjectWrapper(experienceAsNdo),
                Pdo(MyAppl.instance.getString(R.string.certification)),
                NestedDataObjectWrapper(certification.certificationAsNdo),
                Pdo(MyAppl.instance.getString(R.string.other)),
                NestedDataObjectWrapper(otherAsNdo),
                Pdo(MyAppl.instance.getString(R.string.language)),
                NestedDataObjectWrapper(language.languageAsNdo))

    /**
     * No args constructor for use in serialization
     */
    constructor() {}

    /**
     * @param certification
     * @param other
     * @param experience
     * @param language
     * @param education
     */
    constructor(certification: Certification, education: List<Education>, experience: List<Experience>, language: Language, other: List<Other>) : super() {
        this.certification = certification
        this.education = education
        this.experience = experience
        this.language = language
        this.other = other
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
