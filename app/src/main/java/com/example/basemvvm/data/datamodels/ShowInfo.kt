package com.example.basemvvm.data.datamodels

data class ShowInfo(
    val score: Double? = null,
    val show: Show? = null
)

data class Show(
    val id: Long? = null,
    val url: String? = null,
    val name: String? = "",
    val type: Type? = null,
    val language: Language? = null,
    val genres: List<String>? = null,
    val status: Status? = null,
    val runtime: Long? = null,
    val premiered: String? = null,
    val officialSite: String? = null,
    val schedule: Schedule? = null,
    val rating: Rating? = null,
    val weight: Long? = null,
    val network: Network? = null,
    val webChannel: Network? = null,
    val externals: Externals? = null,
    val image: Image? = null,
    val summary: String? = null,
    val updated: Long? = null,
    val links: Links? = null
)

data class Externals(
    val tvrage: Long? = null,
    val thetvdb: Long? = null,
    val imdb: String? = null
)

data class Image(
    val medium: String? = null,
    val original: String? = null
)

enum class Language {
    English
}

data class Links(
    val self: Nextepisode? = null,
    val previousepisode: Nextepisode? = null,
    val nextepisode: Nextepisode? = null
)

data class Nextepisode(
    val href: String? = null
)

data class Network(
    val id: Long? = null,
    val name: String? = null,
    val country: Country? = null
)

data class Country(
    val name: Name? = null,
    val code: Code? = null,
    val timezone: Timezone? = null
)

enum class Code {
    CA,
    GB,
    Us
}

enum class Name {
    Canada,
    UnitedKingdom,
    UnitedStates
}

enum class Timezone {
    AmericaHalifax,
    AmericaNewYork,
    EuropeLondon
}

data class Rating(
    val average: Double? = null
)

data class Schedule(
    val time: String? = null,
    val days: List<String>? = null
)

enum class Status {
    Ended,
    InDevelopment,
    Running
}

enum class Type {
    Animation,
    Scripted
}
