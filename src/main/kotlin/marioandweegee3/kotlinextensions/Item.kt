package marioandweegee3.kotlinextensions

import net.minecraft.item.Item
import net.minecraft.item.ItemStack
import net.minecraft.tag.Tag
import net.minecraft.util.Identifier

fun Item.stack(count:Int = 1) = ItemStack(this, count)

fun Item.matches(tag:Identifier):Boolean = this.matches(tag.tag())

fun Item.matches(tag:Tag<Item>):Boolean = tag.contains(this)

fun Item.matches(tag:String):Boolean = this.matches(tag.id)