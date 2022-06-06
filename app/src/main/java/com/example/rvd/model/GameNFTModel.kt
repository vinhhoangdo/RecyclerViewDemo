package com.example.rvd.model

data class GameNFT(
    val assets: List<GameModel>
)


data class GameModel(
    val collection: CollectionModel,
    val creator: Creator
)

data class CollectionModel(
    val name: String,
    val banner_image_url: String,
    val description: String
)

data class Creator (
    val profile_img_url: String,
    val user: User
)
data class User(
    val username: String
)