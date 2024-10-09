package com.almarpa.dragonballapp.domain

import com.almarpa.dragonballapp.domain.model.CharacterDetailModel
import com.almarpa.dragonballapp.domain.model.CharacterModel

interface Repository {
    suspend fun getCharacters():List<CharacterModel>
    suspend fun getSingleCharacter(id:Int): CharacterDetailModel?
}