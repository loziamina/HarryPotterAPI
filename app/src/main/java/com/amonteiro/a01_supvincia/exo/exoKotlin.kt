package com.amonteiro.a01_supvincia.exo

data class PersonBean(var name:String, var note:Int)


fun main() {


    val toto : (Int)-> String = { it ->
        print(it)
        it.toString()
    }

    var res = toto(5)

    val list = arrayListOf(PersonBean ("toto", 16),
        PersonBean ("Tata", 20),
        PersonBean ("Toto", 8),
        PersonBean ("Charles", 14))

    val isToto : (PersonBean) -> Boolean = {
        it.name.equals("toto" , true)
    }

    println("\n\nAfficher combien de Toto ont plus que la moyenne de la classe")
    val moyenne = list.map { it.note }.average()
    println(list.count { isToto(it) && it.note >= moyenne })

}

fun scanText(question: String): String {
    print(question)
    return readlnOrNull() ?: "-"
}

fun scanNumber(question: String): Int {
    return scanText(question).toIntOrNull() ?: 0
}

fun boulangerie(nbC: Int = 0, nbS: Int = 0, nbBag: Int = 0) =
    nbC * PRICE_CROI + nbS * PRICE_SAND + nbBag * PRICE_BAG

fun myPrint(text: String) = println("#$text#")

fun pair(c: Int) = c % 2 == 0

fun min(a: Int, b: Int, c: Int) = if (a < b && a < c) a else if (b < a && b < c) b else c


































