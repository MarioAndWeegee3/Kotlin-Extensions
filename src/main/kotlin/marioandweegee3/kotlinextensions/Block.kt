package marioandweegee3.kotlinextensions

import net.minecraft.block.Block
import net.minecraft.item.BlockItem
import net.minecraft.item.Item
import net.minecraft.util.Identifier

fun Block.makeItem(settings:Item.Settings = Item.Settings()):BlockItem = BlockItem(this, settings)

fun Block.matches(tag:Identifier):Boolean = this.matches(tag.tag())

fun Block.matches(tag:String):Boolean = this.matches(tag.id)