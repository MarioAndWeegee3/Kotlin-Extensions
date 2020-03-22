package marioandweegee3.kotlinextensions.api

import net.minecraft.nbt.Tag

interface NBTSerializable {
	fun toTag():Tag
	fun fromTag(tag:Tag):NBTSerializable
}