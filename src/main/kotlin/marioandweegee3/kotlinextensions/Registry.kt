package marioandweegee3.kotlinextensions

import net.minecraft.block.Block
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.EntityType
import net.minecraft.item.Item
import net.minecraft.util.Identifier
import net.minecraft.util.registry.Registry

operator fun <T> Registry<T>.get(string:String):T? = this[string.id]

operator fun <T> Registry<T>.set(id:Identifier, value:T):T = Registry.register(this, id, value)
operator fun <T> Registry<T>.set(id:String, value:T):T = this.set(id.id, value)

fun Identifier.item():Item = Registry.ITEM[this]
fun Identifier.block():Block = Registry.BLOCK[this]
fun Identifier.entityType():EntityType<*> = Registry.ENTITY_TYPE[this]
fun Identifier.blockEntityType():BlockEntityType<*> = Registry.BLOCK_ENTITY_TYPE[this]!!
fun Identifier.registry():Registry<*> = Registry.REGISTRIES[this]!!