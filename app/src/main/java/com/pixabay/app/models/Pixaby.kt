package com.pixabay.app.models

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class Pixaby {

    @SerializedName("totalHits")
    @Expose
    var totalHits: Int? = null
    @SerializedName("hits")
    @Expose
    var hits: List<Hit>? = null
    @SerializedName("total")
    @Expose
    var total: Int? = null
}
