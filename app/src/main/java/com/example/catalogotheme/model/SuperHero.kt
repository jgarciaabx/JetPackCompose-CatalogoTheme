package com.example.catalogotheme.model

import androidx.annotation.DrawableRes

data class Superhero(var superheroname: String, var realname: String, var publisher: String, @DrawableRes var photo: Int)