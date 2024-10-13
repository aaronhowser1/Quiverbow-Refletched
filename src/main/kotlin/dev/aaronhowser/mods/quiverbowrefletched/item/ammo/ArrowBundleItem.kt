package dev.aaronhowser.mods.quiverbowrefletched.item.ammo

import dev.aaronhowser.mods.quiverbowrefletched.item.component.ItemStackListComponent
import dev.aaronhowser.mods.quiverbowrefletched.registry.ModDataComponents
import net.minecraft.network.chat.Component
import net.minecraft.world.item.Item
import net.minecraft.world.item.ItemStack
import net.minecraft.world.item.Items
import net.minecraft.world.item.TooltipFlag

class ArrowBundleItem(
    properties: Properties = Properties()
        .stacksTo(1)
        .component(
            ModDataComponents.ADVANCED_AMMO_COMPONENT.get(),
            ItemStackListComponent(
                8,
                listOf(ItemStack(Items.ARROW, 8))
            )
        )
) : Item(properties) {

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