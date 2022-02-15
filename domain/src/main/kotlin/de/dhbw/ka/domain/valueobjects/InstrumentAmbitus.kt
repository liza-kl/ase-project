package de.dhbw.ka.domain.valueobjects

import kotlin.math.absoluteValue


fun generateTuneMap() : HashMap<Char, Int> {
    val tuneMap : HashMap<Char, Int> = HashMap<Char, Int> ();
    val keys = listOf<String>("C","D","E","F","G","A","H","c","d","e","f","g","a","h");
    for ((i, key) in keys.withIndex()) {
        tuneMap[key.first()] = i;
    }
    return tuneMap;
}

fun checkFormalities(firstTone: String, lastTone: String) : Boolean {
    val possibleTonePattern = "(^([a-hA-H])[1-4]?){1,2}".toRegex();

    if(!firstTone.matches(possibleTonePattern) || !lastTone.matches(possibleTonePattern)) {
        throw Exception("The tone you've provided does not exist (yet)")
    }
    if((generateTuneMap()[firstTone.first()]!!.absoluteValue > generateTuneMap()[lastTone.first()]!!.absoluteValue)) {
        throw Exception("The first tone can't be higher than the last")
    }
    if(firstTone.last() > lastTone.last()) {
        throw Exception("Please check your octaves")
    }
    return true;
}


data class InstrumentAmbitus(val firstTone: String, val lastTone: String) {
    val generatedAmbitus : String;
    init {
        require(checkFormalities(firstTone,lastTone)) {
            "The ambitus $firstTone-$lastTone is not valid because the first tone can't be higher than the last tone!"
        }
        generatedAmbitus  = "$firstTone - $lastTone";
    }
}
