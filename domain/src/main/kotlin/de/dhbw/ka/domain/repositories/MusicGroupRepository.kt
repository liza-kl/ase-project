package de.dhbw.ka.domain.repositories

import de.dhbw.ka.domain.aggregates.MusicGroup
import de.dhbw.ka.domain.entities.Musician

interface MusicGroupRepository {

    suspend fun createMusicGroup(musicGroup: MusicGroup)

    suspend fun addMusicianToMusicGroup(musicGroup: MusicGroup, musician: Musician)
}
