import java.io.File

fun zad2() {
    println("Wybierz tryb:")
    println("1 - z dziesiętnego na inny system")
    println("2 - z innego systemu na dziesiętny")

    when (readln().trim()) {
        "1" -> {
            print("Podaj liczbę w systemie 10: ")
            val number = readln().trim().toLong()
            print("Podaj system docelowy (2–36): ")
            val base = readln().trim().toInt()
            if (base in 2..36)
                println("Wynik: ${fromDecimal(number, base)}")
            else
                println("Niepoprawna podstawa systemu.")
        }
        "2" -> {
            print("Podaj liczbę: ")
            val number = readln().trim().uppercase()
            print("Podaj system źródłowy (2–36): ")
            val base = readln().trim().toInt()
            if (base in 2..36)
                println("Wynik: ${toDecimal(number, base)}")
            else
                println("Niepoprawna podstawa systemu.")
        }
        else -> println("Niepoprawny wybór.")
    }
}

fun fromDecimal(number: Long, base: Int): String {
    if (number == 0L) return "0"
    var n = if (number < 0) -number else number
    var result = ""
    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    while (n > 0) {
        val remainder = (n % base).toInt()
        result = digits[remainder] + result
        n /= base
    }

    return if (number < 0) "-$result" else result
}

fun toDecimal(number: String, base: Int): Long {
    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var result = 0L
    var sign = 1
    var startIndex = 0

    if (number.startsWith("-")) {
        sign = -1
        startIndex = 1
    }

    for (i in startIndex until number.length) {
        val c = number[i]
        val value = digits.indexOf(c)
        if (value < 0 || value >= base) {
            println("Niepoprawny znak: $c")
            return 0
        }
        result = result * base + value
    }

    return result * sign
}

fun zad3() {
    print("Podaj nazwę pliku: ")
    val fileName = readln().trim()

    val file = File(fileName)
    if (!file.exists()) {
        println("Plik nie istnieje.")
        return
    }

    val lines = file.readLines()

    for (line in lines) {
        val trimmed = line.trim()
        if (trimmed.isEmpty()) continue

        val type = detectType(trimmed)
        println("$trimmed -> $type")
    }
}

fun detectType(value: String): String {
    val trimmed = value.trim()

    return when {
        trimmed.startsWith("[") && trimmed.endsWith("]") -> "Array"
        trimmed == "true" || trimmed == "false" -> "Boolean"
        trimmed.toIntOrNull() != null -> "Int"
        trimmed.endsWith("f") && trimmed.dropLast(1).toFloatOrNull() != null -> "Float"
        trimmed.toDoubleOrNull() != null -> "Double"
        trimmed.length == 1 -> "Char"
        else -> "String"
    }
}

fun zad4() {
    print("Podaj datę (YYYY-MM-DD HH:mm): ")
    val input = readln().trim()

    val year = input.substring(0, 4).toInt()
    val month = input.substring(5, 7).toInt()
    val day = input.substring(8, 10).toInt()
    val hour = input.substring(11, 13).toInt()
    val minute = input.substring(14, 16).toInt()

    var y = year
    var m = month
    var d = day
    var h = hour
    var min = minute

    print("Podaj przesunięcia (np. +2d -1m +3y +5h): ")
    val shifts = readln().split(" ")

    for (shift in shifts) {
        if (shift.isEmpty()) continue
        val sign = if (shift.startsWith("+")) 1 else -1
        val value = shift.drop(1).dropLast(1).toInt()
        val unit = shift.last()

        when (unit) {
            'y' -> y += sign * value
            'm' -> {
                m += sign * value
                while (m > 12) { m -= 12; y += 1 }
                while (m < 1) { m += 12; y -= 1 }
            }
            'd' -> {
                d += sign * value
                while (d > daysInMonth(y, m)) { d -= daysInMonth(y, m); m += 1; if (m > 12) { m = 1; y += 1 } }
                while (d < 1) { m -= 1; if (m < 1) { m = 12; y -= 1 }; d += daysInMonth(y, m) }
            }
            'h' -> {
                h += sign * value
                while (h >= 24) { h -= 24; d += 1; if (d > daysInMonth(y, m)) { d = 1; m += 1; if (m > 12) { m = 1; y += 1 } } }
                while (h < 0) { h += 24; d -= 1; if (d < 1) { m -= 1; if (m < 1) { m = 12; y -= 1 }; d = daysInMonth(y, m) } }
            }
            'i' -> {
                min += sign * value
                while (min >= 60) { min -= 60; h += 1; if (h >= 24) { h = 0; d += 1; if (d > daysInMonth(y, m)) { d = 1; m += 1; if (m > 12) y += 1 } } }
                while (min < 0) { min += 60; h -= 1; if (h < 0) { h = 23; d -= 1; if (d < 1) { m -= 1; if (m < 1) { m = 12; y -= 1 }; d = daysInMonth(y, m) } } }
            }
        }
    }

    println("Wynikowa data: ${"%04d".format(y)}-${"%02d".format(m)}-${"%02d".format(d)} ${"%02d".format(h)}:${"%02d".format(min)}")
}

fun daysInMonth(year: Int, month: Int): Int {
    return when(month) {
        1,3,5,7,8,10,12 -> 31
        4,6,9,11 -> 30
        2 -> if (isLeapYear(year)) 29 else 28
        else -> 30
    }
}

fun isLeapYear(year: Int): Boolean {
    return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0)
}

fun main() {
//    zad2()
    zad3()
}
