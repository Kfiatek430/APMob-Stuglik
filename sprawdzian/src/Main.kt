import kotlin.math.pow
import kotlin.math.sqrt

fun main() {
//    zad1a()
//    zad1b()
//    zad2()
    zad4()
}

fun zad1a() {
    println("Podaj gęstość: ")
    val g = readln().toDouble()

    println("Podaj a: ")
    val a = readln().toDouble()

    val v = (5.0*(3.0 + sqrt(5.0))*a*a*a)/12.0;
    val m = v * g;
    println("Masa $m")
}

fun zad1b() {
    println("Podaj wagę: ")
    val m = readln().toDouble()

    println("Podaj gęstość: ")
    val g = readln().toDouble()

    val v = m / g

    val a = (v / ((5.0 * (3.0 + sqrt(5.0)) / 12.0))).pow(1.0 / 3.0)
    println(a)
}

fun zad2() {
    println("Wybierz tryb:")
    println("1 (z dziesiętnego na inny)")
    println("2 (z innego systemu na dziesiętny)")

    when (readln().trim()) {
        "1" -> {
            print("Podaj liczbę w systemie 10: ")
            val number = readln().toInt()
            print("Podaj system docelowy (2–36): ")
            val base = readln().toInt()
            if (base in 2..36)
                println("Wynik: ${fromDecimal(number, base)}")
            else
                println("Niepoprawny system")
        }
        "2" -> {
            print("Podaj liczbę: ")
            val number = readln().uppercase()
            print("Podaj system źródłowy: ")
            val base = readln().toInt()
            if (base in 2..36)
                println("Wynik: ${toDecimal(number, base)}")
            else
                println("Niepoprawny system")
        }
        else -> println("Niepoprawny wybór")
    }
}

fun fromDecimal(number: Int, base: Int): String {
    if (number == 0) return "0"
    var n = number

    var result = ""
    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"

    while (n > 0) {
        val remainder = (n % base).toInt()
        result = digits[remainder] + result
        n /= base
    }

    return result
}

fun toDecimal(number: String, base: Int): Int {
    val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
    var result = 0

    for (i in 0 until number.length) {
        val c = number[i]
        val value = digits.indexOf(c)
        if (value < 0 || value >= base) {
            println("Niepoprawny znak: $c")
            return 0
        }
        result = result * base + value
    }

    return result
}

class NumberSystem(val value: String, val base: Int) {
    fun toDec(): Int {
        val digits = "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZ"
        var result = 0

        for (i in 0 until this.value.length) {
            val c = value[i]
            val value2 = digits.indexOf(c)
            if (value2 < 0 || value2 >= base) {
                println("Niepoprawny znak: $c")
                return 0
            }
            result = result * base + value2
        }

        return result
    }

    operator fun plus(other: NumberSystem): String {
        val val1 = other.toDec();
        val val2 = toDec();
        return "Wartość sumy w systemie 10: ${val1 + val2}"
    }

    operator fun minus(other: NumberSystem): String {
        val val1 = other.toDec();
        val val2 = toDec();
        return "Wartość odejmowania w systemie 10: ${val1 - val2}"
    }
}

fun zad4() {
    val number1 = NumberSystem("AZ", 36)
    val number2 = NumberSystem("70", 8)

    println(number1.toDec())
    println(number2.toDec())

    println(number1 + number2)
    println(number1 - number2)
}
