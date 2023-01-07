import io.github.cdimascio.dotenv.Dotenv
import java.util.Scanner

private lateinit var token: String
fun main() {
    token = Dotenv.configure().load().get("TOKEN")
    menuLoop()
}

fun menuLoop() {
    do {
        val option = menu()
        when (option) {
            Options.INVALIDO -> println("Opción no válida")
            Options.SALIR,
            Options.APAGAR,
            -> DiscordBot.stop()

            Options.ENCENDER -> DiscordBot.start(token)
        }
    } while (option != Options.SALIR)
}

fun menu(): Options {
    println("1. Encender Bot")
    println("2. Apagar Bot")
    println("0. Salir")
    val scanner = Scanner(System.`in`)
    return try {
        Options.values()[scanner.nextLine().toInt()]
    } catch (e: Exception) {
        Options.INVALIDO
    }
}

enum class Options {
    SALIR, ENCENDER, APAGAR, INVALIDO
}