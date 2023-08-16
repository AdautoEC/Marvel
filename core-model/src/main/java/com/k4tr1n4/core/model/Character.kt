package com.k4tr1n4.core.model

import android.os.Parcelable
import com.k4tr1n4.core.model.utils.formatDate
import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass
import kotlinx.parcelize.Parcelize

/*
*id (int, optional): The unique ID of the character resource.,
name (string, optional): The name of the character.,
description (string, optional): A short bio or description of the character.,
modified (Date, optional): The date the resource was most recently modified.,
resourceURI (string, optional): The canonical URL identifier for this resource.,
urls (Array[Url], optional): A set of public web site URLs for the resource.,
thumbnail (Image, optional): The representative image for this character.,
comics (ComicList, optional): A resource list containing comics which feature this character.,
stories (StoryList, optional): A resource list of stories in which this character appears.,
events (EventList, optional): A resource list of events in which this character appears.,
series (SeriesList, optional): A resource list of series in which this character appears.
*/
@Parcelize
@JsonClass(generateAdapter = true)
data class Character(
    var page: Int = 0,
    @field:Json(name = "id") val id: Int?,
    @field:Json(name = "name") val name: String,
    @field:Json(name = "description") val description: String?,
    @field:Json(name = "modified") val modified: String?,
    @field:Json(name = "resourceURI") val resourceURI: String?
) : Parcelable {
    fun getIdString(): String = String.format("#%03d", id)

    fun getFormatModified(): String? = modified?.formatDate()
}
