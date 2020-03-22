package marioandweegee3.kotlinextensions

import net.fabricmc.api.EnvType
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.util.Identifier
import net.minecraft.util.InvalidIdentifierException

val String.id
	get() = Identifier(this)

val Pair<String, String>.id
	get() = Identifier(this.first, this.second)

operator fun Identifier.component1():String = this.namespace
operator fun Identifier.component2():String = this.path

fun String.validId():Boolean {
	return if(FabricLoader.getInstance().environmentType == EnvType.CLIENT){
		Identifier.isValid(this)
	} else {
		try{
			Identifier(this)
			true
		} catch(e:InvalidIdentifierException) {
			false
		}
	}
}

fun Pair<String, String>.validId():Boolean {
	return try{
		this.id
		true
	} catch(e:InvalidIdentifierException){
		false
	}
}