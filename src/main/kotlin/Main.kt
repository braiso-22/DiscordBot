import net.dv8tion.jda.api.sharding.ShardManager
import java.util.Scanner
import javax.security.auth.login.LoginException
import kotlin.concurrent.thread

const val token: String = "MTA2MDk4Nzk4MTU5MjI3NzEyNA.GN-5yF.BpDVKVm6FK8ZwzR_hfM4d7N9l88rQ-xzwW9-1Y"
fun main() {
    menuLoop()
}

fun menuLoop() {
    do {
        val option = menu()
        when (option) {
            Options.INVALIDO -> println("Opción no válida")
            Options.SALIR, Options.APAGAR -> {
                DiscordBot.stop()
            }
            Options.ENCENDER -> {
                    try {
                        DiscordBot.start(token)
                    } catch (e: LoginException) {
                        println("Error: $e")
                    }
            }
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