package com.almarpa.dragonballapp.data.remote

import co.touchlab.kermit.Logger
import com.almarpa.dragonballapp.data.remote.response.CharacterResponse
import com.almarpa.dragonballapp.data.remote.response.CharactersWrapperResponse
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter

class ApiService(private val client: HttpClient) {

    suspend fun getAllCharacters(): CharactersWrapperResponse {
        return try {
            client.get("/api/characters/") {
                parameter("limit", 60)
            }.body()
        }catch (e:Exception){
            Logger.e{ "Error API SERVICE -> ${e.message}"}
            return CharactersWrapperResponse(items = emptyList())
        }
    }

    suspend fun getDetailCharacter(id:Int):CharacterResponse?{
        return try {
            client.get("/api/characters/$id").body()
        }catch (e:Exception){
            Logger.e{ "Error API SERVICE -> ${e.message}"}
            return null
        }
    }

}