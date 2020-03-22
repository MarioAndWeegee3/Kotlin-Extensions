# Kotlin Extensions for Minecraft [Fabric]
This mod is an API that provides useful extensions to existing vanilla classes. 
They are grouped based on the class that is primarily dealt with or returned by the function.

## Block Extensions
* `Block.makeItem()` - creates a new `BlockItem` based on the block. Optionally takes an `Item.Settings`.
* `Block.matches(Identifier)` - checks whether the block is contained in the tag with the given id
* `Block.matches(String)` - converts the String to an `Identifier` then calls `matches(Identifier)`
## Identifier Extensions
* `String.id` - converts the String to an `Identifier`
* `Pair<String,String>.id` - converts the Pair to an `Identifier`
* `Identifier.component1() and .component2()` - allows for destructuring of `Identifier`s
* `String.validId()` - checks if the String is a valid `Identifier`
* `Pair<String,String>.validId()` checks if the Pair is a valid `Identifier`
## Item Extensions
* `Item.stack()` - creates a new ItemStack with the Item. Optionally takes an Int for the count.
* `Item.matches(Tag<Item> or Identifier or String)` - checks if the Item is in the Tag. Same idea as `Block::matches`
## NBT Tag Extensions
*  `compooundTag(vararg Pair<String, Tag>)` - creates a new `CompoundTag` with the given elements
* `CompoundTag.set(String, Tag)` - operator for `CompoundTag::put`
* `Map<String,Tag>.toCompoundTag()` - creates a new `CompoundTag` from the map's elements
* `Any?.toTag()` - Creates a new `Tag` from the object. _Note: if the object cannot be converted into a tag, this will crash the game._\
* `interface NBTSerializable` - Allows you to create the NBT representation for your classes.
Not needed for `Entity` and `BlockEntity` subclasses.
## Registry Extensions
* `Registry<T>.get(String)` - converts to an `Identifier` and gets the value in the Registry
* `Registry<T>.set(Identifier or String, T)` - Registers the value in the Registry using `Registry.register`
* `Identifier.item() and .block() and .entityType() and .blockEntityType() and .registry()` - gets the value
at the `Identifier` in the appropriate Registry
## Tag Extensions
* `Identifier.tag<T>()` - gets a `Tag<T>` at the `Identifier`. Only valid for Item, Block, EntityType, and Fluid.
_Any other type will crash the game._
* `String.tag<T>()` - converts to an `Identifier` and gets the `Tag<T>` at it
* `Tag<T>.isEqual(Tag<T>)` - `Tag<T>.equals` is not properly overridden. Use this to compare equality with tags.
## World Extensions
* `World.runOnClient(()->Unit)` - Runs the passed function only on the client
* `World.runOnServer(()->Unit)` - Runs the passed function only on the server