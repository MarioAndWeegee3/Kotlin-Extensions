package marioandweegee3.kotlinextensions.test

import marioandweegee3.kotlinextensions.*
import marioandweegee3.kotlinextensions.api.NBTSerializable
import net.fabricmc.api.ModInitializer
import net.fabricmc.fabric.api.event.server.ServerStartCallback
import net.fabricmc.loader.api.FabricLoader
import net.minecraft.block.Block
import net.minecraft.block.Blocks
import net.minecraft.block.entity.BlockEntityType
import net.minecraft.entity.EntityType
import net.minecraft.entity.mob.CreeperEntity
import net.minecraft.item.Item
import net.minecraft.item.Items
import net.minecraft.nbt.CompoundTag
import net.minecraft.nbt.IntTag
import net.minecraft.nbt.StringTag
import net.minecraft.nbt.Tag
import net.minecraft.tag.BlockTags
import net.minecraft.tag.ItemTags
import net.minecraft.util.math.BlockPos
import net.minecraft.util.registry.Registry

@Suppress("unused")
object Tests:ModInitializer {
	override fun onInitialize() {
		fun info(message:Any) = Extensions.logger.info("[Kotlin Extensions Tests]: $message")

		if(FabricLoader.getInstance().isDevelopmentEnvironment){
			info("Running debug tests...")

			kotlin.run {
				info("Running identifier extension tests...")

				val test = "test:id"

				info("Initial string: $test")

				info(if(test.validId()) "$test is valid" else "$test is invalid")

				if(test.validId()){
					val id = test.id

					info("$test as an identifier: $id")

					val (namespace, path) = id

					info("$test's namespace is $namespace. Its path is $path")
				}

				val testPair = "test" to "id2"

				info("Initial pair: $testPair")

				info(if(testPair.validId()) "$testPair is valid" else "$testPair is invalid")

				if(testPair.validId()){
					val id = testPair.id

					info("$testPair as an identifier: $id")
				}
			}

			kotlin.run {
				info("Running Item and Registry extension tests...")

				val testItem = Item(Item.Settings())

				val testId = "kotlinextensions:test_item".id

				Registry.ITEM[testId] = testItem

				info("Registered first test item")

				val testItem2 = Item(Item.Settings())

				val testId2 = "kotlinextensions:test_item_2"

				Registry.ITEM[testId2] = testItem2

				info("Registered second test item")

				info("Test stack with count 45: ${testItem.stack(45)}")

				assert("oak_planks".id.item() == Items.OAK_PLANKS)
				assert("oak_log".id.block() == Blocks.OAK_LOG)
				assert("creeper".id.entityType() == EntityType.CREEPER)
				assert("furnace".id.blockEntityType() == BlockEntityType.FURNACE)
				assert("item".id.registry() == Registry.ITEM)

				info("Tested Identifier to registry calls")
			}

			kotlin.run {
				info("Running Block tests...")

				val block = Blocks.OAK_LOG

				block.makeItem()

				info("Tested item creation")
			}

			kotlin.run {
				info("Running NBT extension tests...")
				info(69.toTag())
				info(1.2.toTag())
				info("foo".toTag())
				info(mapOf("bar" to 45).toTag())
				info(false.toTag())
				info(BlockPos(4,4,4).toTag())
				info((1..6).toList().toTag())
				data class Test(val count:Int, val value:String):NBTSerializable {
					override fun toTag():Tag = compoundTag("count" to count.toTag(), "value" to value.toTag())

					override fun fromTag(tag:Tag):NBTSerializable {
						if(tag is CompoundTag){
							return Test((tag["count"] as IntTag).int, (tag["value"] as StringTag).asString())
						} else throw IllegalArgumentException("Tag must be compound")
					}
				}

				val test = Test(69, "nice")
				assert(test == test.fromTag(test.toTag()))

				info("NBT tests complete")
			}

			info("Initialization tests complete")

			ServerStartCallback.EVENT.register(ServerStartCallback {
				info("Running Tag tests...")
				assert("planks".tag<Item>().isEqual(ItemTags.PLANKS))
				assert("oak_logs".tag<Block>().isEqual(BlockTags.OAK_LOGS))
				assert(Blocks.OAK_LOG.matches("oak_logs"))
				assert(Items.OAK_PLANKS.matches("planks"))
				info("Tag tests complete!")
			})
		}
	}
}