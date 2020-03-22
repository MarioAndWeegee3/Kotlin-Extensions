package marioandweegee3.kotlinextensions

import net.fabricmc.fabric.api.tag.TagRegistry
import net.minecraft.block.Block
import net.minecraft.entity.EntityType
import net.minecraft.fluid.Fluid
import net.minecraft.item.Item
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

@Suppress("UNCHECKED_CAST")
inline fun <reified T> Identifier.tag():Tag<T> = when(T::class){
	Item::class -> TagRegistry.item(this)
	Block::class -> TagRegistry.block(this)
	EntityType::class -> TagRegistry.entityType(this)
	Fluid::class -> TagRegistry.fluid(this)
	else -> null
} as Tag<T>

inline fun <reified T> String.tag():Tag<T> = this.id.tag()

fun <T> Tag<T>.isEqual(other:Tag<T>):Boolean {
	return if(this == other) true
	else this.entries() == other.entries()
}