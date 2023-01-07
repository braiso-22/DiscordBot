import net.dv8tion.jda.api.OnlineStatus
import net.dv8tion.jda.api.entities.Activity
import net.dv8tion.jda.api.exceptions.InvalidTokenException
import net.dv8tion.jda.api.sharding.DefaultShardManagerBuilder
import net.dv8tion.jda.api.sharding.ShardManager

object DiscordBot {
    private var shardManager: ShardManager? = null

    fun start(token: String) {
        shardManager?.let {
            println("Ya está encendido")
            return
        }
        println("Encendiendo")
        try {
            this.shardManager = DefaultShardManagerBuilder
                .createDefault(token)
                .setActivity(Activity.of(Activity.ActivityType.PLAYING, "Pensando"))
                .setStatus(OnlineStatus.ONLINE)
                .build()
        } catch (e: InvalidTokenException) {
            println("Error: $e")
        }
    }

    fun stop() {
        shardManager ?: run {
            println("Ya está apagado")
            return
        }
        println("Apagando")
        shardManager?.shutdown()
        shardManager = null
    }
}