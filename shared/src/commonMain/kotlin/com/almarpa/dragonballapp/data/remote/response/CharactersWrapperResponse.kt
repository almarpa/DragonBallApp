package com.almarpa.dragonballapp.data.remote.response

import kotlinx.serialization.Serializable

@Serializable
data class CharactersWrapperResponse(val items:List<CharacterResponse>)