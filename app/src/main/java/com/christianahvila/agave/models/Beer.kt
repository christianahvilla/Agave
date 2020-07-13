package com.christianahvila.agave.models

import com.christianahvila.agave.utils.Constants
import com.google.gson.annotations.SerializedName

/**
 * Class which provides a model for meta data
 * @constructor Sets all properties of the beer
 * @property id is the unique identifier
 * @property name is the name of the beer
 * @property tagLine is the brand
 * @property imageURL image url
 */

data class Beer (
    @SerializedName(Constants.ID)
    val id: Int,
    @SerializedName(Constants.NAME)
    val name: String,
    @SerializedName(Constants.TAG_LINE)
    val tagLine: String,
    @SerializedName(Constants.IMAGE_URL)
    val imageURL: String
)

/**
 * Class which provides a model for meta data
 * @constructor Sets all properties of the beer
 * @property id is the unique identifier
 * @property name is the name of the beer
 * @property tagLine is the brand
 * @property imageURL image url
 * @property description information about the beer
 * @property firstBrewed first brewed date
 * @property foodPairing food paring
 * @property abv information about the beer
 * @property ibu information about the beer
 * @property srm beer color
 */

data class BeerDetail (
    @SerializedName(Constants.ID)
    val id: Int,
    @SerializedName(Constants.NAME)
    val name: String,
    @SerializedName(Constants.TAG_LINE)
    val tagLine: String,
    @SerializedName(Constants.IMAGE_URL)
    val imageURL: String,
    @SerializedName(Constants.DESCRIPTION)
    val description: String,
    @SerializedName(Constants.FIRST_BREWED)
    val firstBrewed: String,
    @SerializedName(Constants.FOOD_PAIRING)
    val foodPairing: Array<String>,
    @SerializedName(Constants.ABU)
    val abv: Float,
    @SerializedName(Constants.IBU)
    val ibu: Float,
    @SerializedName(Constants.SRM)
    val srm: Float
)