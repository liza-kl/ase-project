package de.dhbw.ka.storage.factories

import de.dhbw.ka.storage.h2.H2MemberStorage
import de.dhbw.ka.storage.interfaces.MemberStorage

interface MemberStorageFactory {
    fun createMemberStorageFromType(storageType: String) : MemberStorage
}

class StandardMemberStorageFactory : MemberStorageFactory {
    override fun createMemberStorageFromType(storageType: String): MemberStorage {
        return when (storageType) {
            "h2" -> H2MemberStorage()
            else -> throw Exception("I don't know how to create storage from type $storageType")
        }
    }
}
