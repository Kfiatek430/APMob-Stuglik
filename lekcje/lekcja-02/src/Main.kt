import kotlin.math.PI
import kotlin.random.Random

fun exercise01() {
    print("Podaj tekst: ")
    val text = readln()
    val output = text.map{ it.code }.joinToString(" ")
    println(output)
}

fun exercise02() {
    print("Podaj liczbę: ")
    val n = readln().toInt()

    println(fibonacci(n))
}

fun fibonacci(n: Int): Int {
    if (n == 0) {
        return 0
    }

    if (n == 1) {
        return 1
    }

    return fibonacci(n - 1) + fibonacci(n - 2)
}

fun exercise03() {
    print("Podaj liczbę: ")
    val n = readln().toInt()

    println(1 shl n)
}

fun exercise04() {
    print("Podaj wysokość: ")
    val height = readln().toFloat()

    print("Podaj szerokość: ")
    val width = readln().toFloat()

    print("Podaj długość: ")
    val length = readln().toFloat()

    print("Podaj ilość farby na metr kwadratowy: ")
    val onSquare = readln().toFloat()

    val output: Float = 2 * (height * length + height * width) * onSquare
    println(output)
}

fun exercise05() {
    print("Podaj promień w cm: ")
    val radius = readln().toDouble()

    print("Podaj gęstość w g/cm3: ")
    val density = readln().toDouble()

    print("Podaj typ: ")
    val type = readln()

    if(type.lowercase() == "walec") {
        print("Podaj wysokość w cm: ")
        val height = readln().toDouble()
        val output: Double = PI * radius * radius * height * density
        println(output / 1000)
    } else if (type.lowercase() == "kula") {
        val output: Double = (4.0/3.0) * PI * radius * radius * radius * density
        println(output / 1000)
    } else {
        println("Wybrałeś zły typ")
    }
}

fun exercise06() {
    print("Dzień urodzenia: ")
    val day = readln().toInt()

    print("Miesiąc urodzenia: ")
    val month = readln().toInt()

    print("Podaj rok urodzenia: ")
    val year = readln().toInt()

    print("Podaj płeć (M / K): ")
    val gender = readln().uppercase()

    val correctedYear = year % 100
    val correctedMonth = if (year >= 2000) month + 20 else month

    val datePart = "%02d%02d%02d".format(correctedYear, correctedMonth, day)

    val random = arrayOfNulls<Int>(3)
    for (i in 0..2) {
        random[i] = Random.nextInt(0, 10)
    }
    val randomPart = random.joinToString("")

    var genderNumber: Int
    if (gender == "M") {
        do {
            genderNumber = Random.nextInt(0, 10)
        } while (genderNumber % 2 == 0)
    } else {
        do {
            genderNumber = Random.nextInt(0, 10)
        } while (genderNumber % 2 != 0)
    }

    val firstTen = datePart + randomPart + genderNumber

    val weights = arrayOf(1, 3, 7, 9, 1, 3, 7, 9, 1, 3)
    var sum = 0
    for (i in 0..<firstTen.length) {
        sum += (firstTen[i].toString().toInt() * weights[i])
    }
    val controlDigit = (10 - (sum % 10)) % 10

    val pesel = firstTen + controlDigit
    println("Wygenerowany PESEL: $pesel")
}


fun main() {
//    exercise01()
//    exercise02()
//    exercise03()
//    exercise04()
//    exercise05()
    exercise06()
}