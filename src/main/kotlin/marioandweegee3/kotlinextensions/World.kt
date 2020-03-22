package marioandweegee3.kotlinextensions

import net.minecraft.world.World

fun World.runOnClient(function:() -> Unit) {
	if(isClient){
		function()
	}
}

fun World.runOnServer(function:() -> Unit) {
	if(!isClient){
		function()
	}
}