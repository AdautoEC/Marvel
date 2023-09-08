package com.k4tr1n4.core.database.entity.mapper

import com.k4tr1n4.core.database.entity.CharacterEntity
import com.k4tr1n4.core.model.Character

object CharacterEntityMapper: EntityMapper<List<Character>, List<CharacterEntity>> {
    override fun asEntity(domain: List<Character>): List<CharacterEntity> {
        return domain.map { character ->
            CharacterEntity(
                page = character.page,
                id = character.id,
                name = character.name,
                description = character.description,
                modified = character.modified,
                resourceURI = character.resourceURI
            )
        }
    }

    override fun asDomain(entity: List<CharacterEntity>): List<Character> {
        return entity.map{ character ->
            Character(
                page = character.page,
                id = character.id,
                name = character.name,
                description = character.description,
                modified = character.modified,
                resourceURI = character.resourceURI
            )
        }
    }
}

fun List<Character>.asEntity(): List<CharacterEntity> {
    return CharacterEntityMapper.asEntity(this)
}

fun List<CharacterEntity>.asDomain(): List<Character> {
    return CharacterEntityMapper.asDomain(this)
}