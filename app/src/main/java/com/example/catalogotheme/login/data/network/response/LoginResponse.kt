package com.example.catalogotheme.login.data.network.response

import com.google.gson.annotations.SerializedName

/**
 * todo: *JuanGarcia*
 *  como consejo siempre es recomendable poner el serializeName
     para que cuando ofusques el codigo no haya problemas
 */

data class LoginResponse(@SerializedName("success") val success: Boolean)