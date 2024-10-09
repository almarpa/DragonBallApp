package com.almarpa.dragonballapp.data

import com.almarpa.dragonballapp.data.remote.ApiService
import com.almarpa.dragonballapp.domain.Repository
import com.almarpa.dragonballapp.domain.model.CharacterDetailModel
import com.almarpa.dragonballapp.domain.model.CharacterModel

class RepositoryImpl(private val api: ApiService) : Repository {

    override suspend fun getCharacters(): List<CharacterModel> {
        return api.getAllCharacters().items.map { characterResponse -> characterResponse.toDomain() }
    }

    override suspend fun getSingleCharacter(id:Int): CharacterDetailModel? {
        return api.getDetailCharacter(id)?.toDetailDomain()
    }
}