import kotlin.random.Random

fun exercise02() {
    val choices = listOf("k", "p", "n")
    println("k (kamień), p (papier), n (nożyce)")

    while (true) {
        print("Twój wybór: ")
        val playerChoice = readln()

        val computerChoice = choices[Random.nextInt(0, 2)]

        val result = when {
            playerChoice == computerChoice -> "Remis!"
            (playerChoice == "k" && computerChoice == "n") ||
            (playerChoice == "p" && computerChoice == "k") ||
            (playerChoice == "n" && computerChoice == "p") -> "Wygrywasz!"
            else -> "Przegrywasz!"
        }

        println("Wybór komputera: $computerChoice")
        println(result)
        println("--------------")
    }
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
//    exercise02()
//    exercise06()
//    exercise07()
}