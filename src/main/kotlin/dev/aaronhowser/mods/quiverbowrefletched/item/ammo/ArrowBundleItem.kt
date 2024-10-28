package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModItems
import net.minecraft.network.chat.Component
import net.minecraft.tags.ItemTags
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag

/**
 * Holds 8 `#arrows`, possibly spread over multiple ItemStacks.
 */
class ArrowBundleItem(
    properties: Properties = Properties()
        .stacksTo(64)
        .component(
            ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
            ItemStackListComponent(
                8,
                listOf(ItemStack(Items.ARROW, 8))
            )
        )
) : Item(properties) {

    companion object {
        fun getStackWithArrows(list: List<ItemStack>): ItemStack {
            val amountOfArrows = list.sumOf { it.count }
            require(amountOfArrows == 8) { "Arrow bundle must contain exactly 8 arrows, it has $amountOfArrows instead" }
            require(list.all { it.`is`(ItemTags.ARROWS) }) { "Arrow bundle must contain only arrows" }

            val stack = ModItems.ARROW_BUNDLE.toStack()
            stack.set(
                ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
                ItemStackListComponent(
                    8,
                    list
                )
            )

            return stack
        }
    }

    override fun appendHoverText(
        stack: ItemStack,
        context: TooltipContext,
        tooltipComponents: MutableList<Component>,
        tooltipFlag: TooltipFlag
    ) {
        super.appendHoverText(stack, context, tooltipComponents, tooltipFlag)

        if (!tooltipFlag.hasShiftDown()) {
            tooltipComponents.add(
                Component.literal("Hold SHIFT for more info")
            )
        } else {
            for (ammoStack in AdvancedAmmoClipItem.getAmmoStacks(stack)) {
                tooltipComponents.add(
                    Component
                        .literal("• ${ammoStack.count}x ")
                        .append(ammoStack.displayName)
                )
            }
        }

    }

}