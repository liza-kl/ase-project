package de.dhbw.ka.domain.valueobjects

import kotlin.math.absoluteValue

data class InstrumentAmbitus(val firstTone: String, val lastTone: String) {
    val generatedAmbitus : String;

    private fun generateTuneMap() : HashMap<String, Int> {
        val tuneMap : HashMap<String, Int> = HashMap<String, Int> ();
        val keys = listOf<String>(
            "C","D","E","F","G","A","H",
            "c","d","e","f","g","a","h",
            "c1","d1","e1","f1","g1","a1","h1",
            "c2","d2","e2","f2","g2","a2","h2",
            "c3","d3","e3","f3","g3","a3","h3",
            "c4","d4","e4","f4","g4","a4","h4");
        for ((i, key) in keys.withIndex()) {
            tuneMap[key] = i;
        }
        return tuneMap;
    }

    private fun checkFormalities(firstTone: String, lastTone: String) : Boolean {
        if(!generateTuneMap().containsKey(firstTone) || !generateTuneMap().containsKey(lastTone) ) {
            throw Exception("The tone you've provided does not exist (yet) or isn't a valid tone")
        }
        if((generateTuneMap()[firstTone]!!.absoluteValue >= generateTuneMap()[lastTone]!!.absoluteValue)) {
            throw Exception("The first tone can't be higher or the same as the last one")
        }
        return true;
    }

    init {
        require(checkFormalities(firstTone,lastTone)) {
            "The ambitus $firstTone-$lastTone is not valid because the first tone can't be higher than the last tone!"
        }
        generatedAmbitus  = "$firstTone - $lastTone";
    }

    override fun toString(): String {
        return generatedAmbitus
    }
}

fun String.toInstrumentAmbitus() : InstrumentAmbitus {
    return InstrumentAmbitus(this, this);
}
