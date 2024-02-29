package com.example.pokemon.pokemon.data.model

import com.example.pokemon.pokemon.data.util.Constants.BASE_URL_IMAGE
import com.example.pokemon.pokemon.data.util.Constants.IMAGE_EXT
import com.example.pokemon.pokemon.data.util.Constants.LAST_CHARACTER
import com.example.pokemon.pokemon.data.util.Constants.PARAM_NAME
import com.example.pokemon.pokemon.data.util.Constants.PARAM_URL
import com.example.pokemon.pokemon.data.util.Constants.SLASH
import com.google.gson.annotations.SerializedName

data class PokemonModel(
    @SerializedName(PARAM_NAME)
    val name: String,
    @SerializedName(PARAM_URL)
    val url: String
) {
    fun getImageUrl(): String {
        val index = url.split(SLASH.toRegex()).dropLast(LAST_CHARACTER).last()
        return BASE_URL_IMAGE + index + IMAGE_EXT
    }
    fun getId(): Int = url.split(SLASH.toRegex()).dropLast(LAST_CHARACTER).last().toInt()
}