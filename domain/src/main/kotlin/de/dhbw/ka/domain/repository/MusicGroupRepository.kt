package de.dhbw.ka.domain.repository

import de.dhbw.ka.domain.aggregates.MusicGroup
import de.dhbw.ka.domain.entities.Member

interface MusicGroupRepository {

    suspend fun createMusicGroup(musicGroup: MusicGroup)

    suspend fun addMusicianToMusicGroup(musicGroup: MusicGroup, musician: Member)
}
