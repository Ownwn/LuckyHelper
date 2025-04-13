package com.ownwn.luckyhelper

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraft.client.gui.inventory.GuiInventory
import net.minecraft.inventory.Slot
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.GL11
import java.awt.Color


object BadItemWarn {


    @SubscribeEvent
    fun onRenderContainer(event: GuiScreenEvent.BackgroundDrawnEvent) {
        if (!isInLuckyBlock()) return

        if (event.gui !is GuiInventory && event.gui !is GuiChest) return

        val currentScreen = Minecraft.getMinecraft().currentScreen


        if (currentScreen is GuiInventory) { // thx kotlin inferrer
            highlightSlots(currentScreen.inventorySlots.inventorySlots)
        } else if (currentScreen is GuiChest) {
            highlightSlots(currentScreen.inventorySlots.inventorySlots)
        }


    }

    private fun highlightSlots(slots: List<Slot>) {
        val yOffset = when (slots.size) {
            90 -> 222 // double chest
            63 -> 168 // single chest
            45 -> 166 // player inventory
            else -> return
        }
        for (slot in slots.stream().filter(this::shouldHighlight)) {
            highlightSlot(slot.xDisplayPosition, slot.yDisplayPosition, yOffset)
        }
    }

    private fun shouldHighlight(slot: Slot): Boolean {
        val stack = slot.stack ?: return false
        return stack.displayName?.contains(Regex("Lit|Hot|Disco|Iron")) ?: false
    }

    // https://github.com/RabbitType99/NecromancyBuyHelper/blob/master/src/main/java/dev/RabbitType99/NecromancyBuyHelper/NecromancyBuyHelper.java
    private fun highlightSlot(x: Int, y: Int, yOffset: Int) {
        val sr = ScaledResolution(Minecraft.getMinecraft())
        val xInGui = (sr.scaledWidth - 176) / 2 + x
        val yInGui = (sr.scaledHeight - yOffset) / 2 + y

        GL11.glTranslated(0.0, 0.0, 1.0)
        Gui.drawRect(xInGui, yInGui, xInGui + 16, yInGui + 16, Color.RED.rgb)
        GL11.glTranslated(0.0, 0.0, -1.0)

    }

    private fun isInLuckyBlock(): Boolean {
        val locraw = LocrawUtil.INSTANCE.locrawInfo
        return locraw?.gameType == LocrawInfo.GameType.SKYWARS && locraw.gameMode == "solo_insane_lucky"
    }
}