import java.io.File
import java.time.LocalDate
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import kotlin.random.Random

fun exercise01() {
    println("Wybierz tryb gry: 1 = skróty, 2 = pełne nazwy")
    val tryb = readln().toInt()

    val choices = if (tryb == 2) listOf("kamień", "papier", "nożyce")
    else listOf("k", "p", "n")

    println("Dostępne wybory: ${choices.joinToString(", ")}")

    while (true) {
        print("Twój wybór: ")
        val playerChoice = readln().lowercase()

        val computerChoice = choices[Random.nextInt(3)]

        val result = when {
            playerChoice == computerChoice -> "Remis!"
            (playerChoice == choices[0] && computerChoice == choices[2]) ||
                    (playerChoice == choices[1] && computerChoice == choices[0]) ||
                    (playerChoice == choices[2] && computerChoice == choices[1]) -> "Wygrywasz!"
            else -> "Przegrywasz!"
        }

        println("Wybór komputera: $computerChoice")
        println(result)
        println("--------------")
    }
}

fun exercise02() {
    print("Podaj datę: ")
    val input = readln().trim()

    val formatterDateTime = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm:ss")
    val formatterDate = DateTimeFormatter.ofPattern("dd-MM-yyyy")

    var dateTime = when (input.length) {
        10 -> LocalDate.parse(input, formatterDate).atStartOfDay()
        19 -> LocalDateTime.parse(input, formatterDateTime)
        else -> error("Niepoprawny format daty")
    }

    print("Podaj przesunięcia (np. +2d -1m +3y +5h): ")
    val shifts = readln().split(" ")

    for (shift in shifts) {
        if (shift.isEmpty()) continue
        val value = shift.dropLast(1).toLong()
        when (shift.last()) {
            'd' -> dateTime = if (value >= 0) dateTime.plusDays(value) else dateTime.minusDays(-value)
            'm' -> dateTime = if (value >= 0) dateTime.plusMonths(value) else dateTime.minusMonths(-value)
            'y' -> dateTime = if (value >= 0) dateTime.plusYears(value) else dateTime.minusYears(-value)
            'h' -> dateTime = if (value >= 0) dateTime.plusHours(value) else dateTime.minusHours(-value)
        }
    }

    println("Wynikowa data: ${dateTime.format(formatterDateTime)}")
}

fun exercise03(inputFile: String, outputFile: String) {
    val inputLines = File(inputFile).readLines()
    val results = inputLines.map { line ->
        val trimmed = line.trim()
        when {
            trimmed.toIntOrNull() != null ->
                "$trimmed to zmienna typu Int"
            trimmed.toDoubleOrNull() != null ->
                "$trimmed to zmienna typu Double"
            trimmed.toBooleanStrictOrNull() != null ->
                "$trimmed to zmienna typu Boolean"
            trimmed.startsWith("[") && trimmed.endsWith("]") ->
                "$trimmed to zmienna typu Array"
            else ->
                "$trimmed to zmienna typu String"
        }
    }
    File(outputFile).writeText(results.joinToString("\n"))
}

fun exercise04() {
    println("Konwersje liczbowe (2-16)")
    println("1. Dec -> X")
    println("2. X -> Dec")
    print("Wybierz opcję: ")

    when (readln()) {
        "1" -> {
            print("Podaj podstawę systemu docelowego (2-16): ")
            val base = readln().toInt()
            print("Podaj liczbę dziesiętną: ")
            val dec = readln().toInt()
            println("Wynik: ${convertDecToBase(dec, base)}")
        }
        "2" -> {
            print("Podaj podstawę systemu źródłowego (2-16): ")
            val base = readln().toInt()
            print("Podaj liczbę: ")
            val num = readln()
            println("Wynik: ${convertBaseToDec(num, base)}")
        }
        else -> println("Nieprawidłowa opcja")
    }
}

fun convertDecToBase(value: Int, base: Int): String {
    if (base !in 2..16) return "Podstawa musi być w zakresie 2-16"
    if (value == 0) return "0"

    val digits = "0123456789ABCDEF"
    var num = value
    val result = StringBuilder()

    while (num > 0) {
        result.append(digits[num % base])
        num /= base
    }

    return result.reverse().toString()
}

fun convertBaseToDec(number: String, base: Int): Int {
    if (base !in 2..16) return -1

    val digits = "0123456789ABCDEF"
    var result = 0

    for (char in number.uppercase()) {
        val digitValue = digits.indexOf(char)
        if (digitValue == -1 || digitValue >= base) {
            println("Nieprawidłowa liczba dla podstawy $base")
            return -1
        }
        result = result * base + digitValue
    }

    return result
}

fun exercise05() {
    print("Podaj tekst do zaszyfrowania: ")
    val message = readLine() ?: ""
    print("Podaj klucz szyfrujący (np. 42310): ")
    val key = readLine() ?: ""

    val cols = key.length
    val order = key.map { it.toString().toInt() }
    val rows = (message.length + cols - 1) / cols
    val padded = message.padEnd(rows * cols, '_')

    val table = List(rows) { r -> padded.substring(r * cols, r * cols + cols) }
    val encrypted = buildString {
        for (c in order) {
            for (r in table) append(r[c])
        }
    }
    println("Zaszyfrowane: $encrypted")

    val decryptedTable = Array(rows) { CharArray(cols) }
    var idx = 0
    for (c in order) {
        for (r in 0 until rows) {
            decryptedTable[r][c] = encrypted[idx++]
        }
    }
    val decrypted = decryptedTable.joinToString("") { String(it) }.replace("_", "")
    println("Odszyfrowane: $decrypted")
}

fun exercise06() {
    print("Podaj prędkość wiatru w m/s: ")
    val speed = readln().toDouble()

    val result = when (speed) {
        in 0.0..0.2 -> "0 (cisza)"
        in 0.3..1.5 -> "1 (lekki powiew)"
        in 1.6..3.3 -> "2 (umiarkowany powiew)"
        in 3.4..5.4 -> "3 (lekki wiatr)"
        in 5.5..8.0 -> "4 (umiarkowany wiatr)"
        in 8.1..10.7 -> "5 (dość silny wiatr)"
        in 10.8..13.8 -> "6 (silny wiatr)"
        in 13.9..17.2 -> "7 (bardzo silny wiatr)"
        in 17.3..20.7 -> "8 (burza)"
        in 20.8..24.6 -> "9 (silna burza)"
        in 24.7..28.8 -> "10 (bardzo silna burza)"
        in 28.9..Double.POSITIVE_INFINITY -> "11+ (huragan)"
        else -> "Podałeś nieprawidłową liczbę"
    }

    println(result)
}

class Car(var make: String, var model: String, var yearOfProduction: Int, var mileage: Int, var maxSpeed: Int, var numberOfPassengers: Int) {
    var lastInspection = 0;

    fun drive(kilometers: Int) {
        mileage += kilometers
    }

    fun checkInspection() {
        if (mileage - lastInspection >= 10000) {
            println("Należy wykonać przegląd!")
        } else {
            println("Przegląd aktualny!")
        }
    }

    fun inspection() {
        if (mileage - lastInspection >= 10000) {
            lastInspection = mileage
            println("Przegląd wykonany!")
        } else {
            println("Przegląd aktualny!")
        }
    }

    override fun toString(): String {
        return "$make $model ($yearOfProduction)\nMileage: $mileage\nMax speed: $maxSpeed\nNumber of passengers: $numberOfPassengers"
    }
}

fun exercise07() {
    val car = Car("Audi", "R8", 2022, 30000, 320, 2)
    car.drive(200)
    car.checkInspection()
    car.inspection()
    car.drive(10000)
    car.checkInspection()
    println(car)
}

fun main() {
//    exercise01()
//    exercise02()
//    exercise03("dane.txt", "wynik.txt")
//    exercise04()
//    exercise06()
//    exercise07()
}