abstract class Shape(val name: String) {
    abstract fun area(): Double

    abstract fun perimeter(): Double

    abstract override fun toString(): String
}

class Rectangle(name: String, val w: Double, val h: Double): Shape(name) {
    init {
        if(w <= 0) throw IllegalArgumentException("Szerokość musi być dodatnia");
        if(h <= 0) throw IllegalArgumentException("Wysokość musi być dodatnia");
    }

    override fun area(): Double {
        return w * h;
    }

    override fun perimeter(): Double {
        return 2 * (w + h);
    }

    override fun toString(): String {
        return "$name: pole - ${area()}; obwód - ${perimeter()}";
    }
}

class Circle(name: String, val r: Double): Shape(name) {
    init {
        if(r <= 0) throw IllegalArgumentException("Promień musi być dodatni");
    }

    override fun area(): Double {
        return Math.PI * r * r;
    }

    override fun perimeter(): Double {
        return 2 * Math.PI * r;
    }

    override fun toString(): String {
        return "$name: pole - ${area()}; obwód - ${perimeter()}";
    }
}

class Trapez(
    name: String,
    var a: Double, val b: Double, val c: Double, val d: Double, val h: Double
) : Shape(name) {
    init {
        if(a <= 0) throw IllegalArgumentException("Podstawa górna musi być dodatnia")
        if(b <= 0) throw IllegalArgumentException("Podstawa dolna musi być dodatnia")
        if(c <= 0) throw IllegalArgumentException("Bok boczny c musi być dodatni")
        if(d <= 0) throw IllegalArgumentException("Bok boczny d musi być dodatni")
        if(h <= 0) throw IllegalArgumentException("Wysokość musi być dodatnia")
    }

    override fun area(): Double {
        return (a + b) * h / 2;
    }

    override fun perimeter(): Double {
        return a + b + c + d;
    }

    override fun toString(): String {
        return "$name: pole - ${area()}; obwód - ${perimeter()}";
    }
}

open class Vehicle(
    val brand: String,
    val model: String,
    val yearOfProduction: Int,
    var mileage: Int,
    private val baseMaxSpeed: Int,
    val numberOfPassengers: Int
) {
    private val inspections = mutableListOf<Int>()
    private var lastInspectionMileage: Int = 0
    var maxSpeed: Int = baseMaxSpeed

    init {
        if (yearOfProduction < 0) throw IllegalArgumentException("Rok produkcji musi być nieujemny")
        if (mileage < 0) throw IllegalArgumentException("Przebieg musi być nieujemny")
        if (baseMaxSpeed < 0) throw IllegalArgumentException("Maksymalna prędkość musi być nieujemna")
        if (numberOfPassengers < 0) throw IllegalArgumentException("Liczba pasażerów musi być nieujemna")
    }

    fun drive(kilometers: Int) {
        if (kilometers < 0) throw IllegalArgumentException("Kilometry muszą być nieujemne")
        mileage += kilometers

        if (checkInspection()) {
            maxSpeed = baseMaxSpeed / 2
        }
    }

    fun checkInspection(): Boolean {
        return (mileage - lastInspectionMileage) >= 10000
    }

    fun doInspection() {
        inspections.add(mileage)
        lastInspectionMileage = mileage
        maxSpeed = baseMaxSpeed
    }

    operator fun plus(other: Vehicle): Int {
        return this.numberOfPassengers + other.numberOfPassengers
    }

    override fun toString(): String {
        return "Pojazd: $brand $model, rok: $yearOfProduction, przebieg: $mileage km, " +
                "max prędkość: $maxSpeed km/h, pasażerów: $numberOfPassengers"
    }
}

class Car(
    brand: String,
    model: String,
    yearOfProduction: Int,
    mileage: Int,
    maxSpeed: Int,
    numberOfPassengers: Int,
    val trunkCapacity: Int
) : Vehicle(brand, model, yearOfProduction, mileage, maxSpeed, numberOfPassengers) {
    init {
        if (trunkCapacity < 0) throw IllegalArgumentException("Pojemność bagażnika musi być nieujemna")
    }

    override fun toString(): String {
        return super.toString() + ", pojemność bagażnika: $trunkCapacity L"
    }
}
class Bike(
    brand: String,
    model: String,
    yearOfProduction: Int,
    mileage: Int,
    maxSpeed: Int,
    numberOfPassengers: Int,
    val hasBell: Boolean
) : Vehicle(brand, model, yearOfProduction, mileage, maxSpeed, numberOfPassengers) {

    override fun toString(): String {
        val bellStatus = if (hasBell) "tak" else "nie"
        return super.toString() + ", dzwonek: $bellStatus"
    }
}
class Motorbike(
    brand: String,
    model: String,
    yearOfProduction: Int,
    mileage: Int,
    maxSpeed: Int,
    numberOfPassengers: Int,
    val hasSidecar: Boolean
) : Vehicle(brand, model, yearOfProduction, mileage, maxSpeed, numberOfPassengers) {
    override fun toString(): String {
        return super.toString() + if (hasSidecar) ", z wózkiem bocznym" else ", bez wózka bocznego"
    }
}


fun main() {
    val prostokat = Rectangle("Prostokąt", 2.0, 5.0)
    println(prostokat.toString())
}