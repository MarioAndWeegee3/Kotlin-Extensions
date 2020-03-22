package marioandweegee3.kotlinextensions

import marioandweegee3.kotlinextensions.api.NBTSerializable
import net.minecraft.block.entity.BlockEntity
import net.minecraft.entity.Entity
import net.minecraft.nbt.*
import net.minecraft.util.math.BlockPos

operator fun CompoundTag.set(key:String, value:Tag) = this.put(key, value)

fun compoundTag(vararg elements:Pair<String, Tag>):CompoundTag {
	val tag = CompoundTag()
	for((key, value) in elements){
		tag[key] = value
	}
	return tag
}

fun Map<String, Tag>.toCompoundTag():CompoundTag = compoundTag(*this.map {it.key to it.value}.toTypedArray())

fun Any?.toTag():Tag {
	return when(this){
		is Tag -> this
		is BlockEntity -> this.toTag(CompoundTag())
		is Entity -> this.toTag(CompoundTag())
		is NBTSerializable -> this.toTag()
		is String -> StringTag.of(this)
		is Byte -> ByteTag.of(this)
		is Boolean -> ByteTag.of(this)
		is Int -> IntTag.of(this)
		is Collection<*> -> {
			val tag = ListTag()
			tag.addAll(this.map {it.toTag()})
			tag
		}
		is Long -> LongTag.of(this)
		is Float -> FloatTag.of(this)
		is Double -> DoubleTag.of(this)
		is Short -> ShortTag.of(this)
		is BlockPos -> compoundTag("x" to this.x.toTag(), "y" to this.y.toTag(), "z" to this.z.toTag())
		is Map<*, *> -> this.map {it.key.toString() to it.value.toTag()}.toMap().toCompoundTag()
		null -> ByteTag.ZERO
		else -> throw IllegalArgumentException("Cannot convert $this to tag")
	}
}