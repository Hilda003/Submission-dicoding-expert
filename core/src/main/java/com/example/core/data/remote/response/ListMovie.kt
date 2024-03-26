package com.example.core.data.remote.response

import com.google.gson.annotations.SerializedName


data class ListMovie (
    @field:SerializedName("results")
    var results: List<MovieResponse>
)

