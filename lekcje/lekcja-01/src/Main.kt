fun main() {
    val name = "Kotlin"

    println("Hello, $name!")

    for (i in 1..5) {
        println("i = $i")
    }

    for (j in 5 downTo 1) {
        println("j = $j")
    }

    val array = arrayOf("String", 20);

    for(item in array) {
        println(item);
    }
}