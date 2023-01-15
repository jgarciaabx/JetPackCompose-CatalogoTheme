package com.example.catalogotheme.login.domain

import com.example.catalogotheme.login.data.LoginRepository

class LoginUseCase {
    val repository = LoginRepository()

    suspend operator fun invoke(user:String, password:String):Boolean{
        return repository.doLogin(user,password)
    }

}