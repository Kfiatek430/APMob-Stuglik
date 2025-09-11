import kotlin.math.PI
import kotlin.random.Random.Default.nextInt
import kotlin.random.nextInt

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
    val gender = readln()

    val correctedYear = year % 100
    val correctedMonth = if (year >= 2000) month + 20 else month

    val datePart = "%02d%02d%02d".format(correctedYear, correctedMonth, day)

    val random: Array<Int?> = arrayOfNulls(3);
    for (i in 0..2)
        random[i] = nextInt(0, 9)

    val randomPart = random.joinToString("")
    println(randomPart)

    var genderNumber: Int;
    if(gender == "M") {
        do {
            genderNumber = nextInt(1, 9)
        } while (genderNumber % 2 !== 0)
    } else {

    }
}

fun main() {
//    exercise01()
//    exercise02()
//    exercise03()
//    exercise04()
//    exercise05()
    exercise06()
}