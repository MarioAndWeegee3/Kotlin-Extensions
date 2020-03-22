package marioandweegee3.kotlinextensions

import net.fabricmc.api.ModInitializer
import org.apache.logging.log4j.LogManager
import org.apache.logging.log4j.Logger

@Suppress("unused")
object Extensions : ModInitializer {
	val logger:Logger = LogManager.getLogger()
	override fun onInitialize() {
		fun info(message:Any) = logger.info("[Kotlin Extensions]: $message")

		info("Kotlin Extensions is loaded!")
	}
}