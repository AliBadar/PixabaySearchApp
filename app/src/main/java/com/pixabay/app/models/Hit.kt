package com.pixabay.app.models


import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Hit {
    @SerializedName("largeImageURL")
    @Expose
    var largeImageURL: String? = null
    @SerializedName("likes")
    @Expose
    var likes: Int? = null
    @SerializedName("views")
    @Expose
    var views: Int? = null
    @SerializedName("tags")
    @Expose
    var tags: String? = null
    @SerializedName("downloads")
    @Expose
    var downloads: Int? = null
    @SerializedName("user")
    @Expose
    var user: String? = null
    @SerializedName("previewURL")
    @Expose
    var previewURL: String? = null
}
