package com.ownwn.luckyhelper

import cc.polyfrost.oneconfig.utils.hypixel.LocrawInfo
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil
import net.minecraft.client.Minecraft
import net.minecraft.client.gui.Gui
import net.minecraft.client.gui.ScaledResolution
import net.minecraft.client.gui.inventory.GuiChest
import net.minecraftforge.client.event.GuiScreenEvent
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent
import org.lwjgl.opengl.GL11
import java.awt.Color


object BadItemWarn {


    @SubscribeEvent
    fun onRenderContainer(event: GuiScreenEvent.BackgroundDrawnEvent) {
        if (!isInLuckyBlock()) return
        if (event.gui !is GuiChest) return
        if (Minecraft.getMinecraft().currentScreen !is GuiChest) return

        val slots = (event.gui as GuiChest).inventorySlots.inventorySlots
        val doubleChest = if (slots.size == 90) true else if (slots.size == 63) false else return

        for (slot in slots) {
            if (slot.stack == null) continue
            highlightSlot(slot.xDisplayPosition, slot.yDisplayPosition, doubleChest) // counts player + chest slots

        }
    }

    // https://github.com/RabbitType99/NecromancyBuyHelper/blob/master/src/main/java/dev/RabbitType99/NecromancyBuyHelper/NecromancyBuyHelper.java
    private fun highlightSlot(x: Int, y: Int, doubleChest: Boolean) {
        val sr = ScaledResolution(Minecraft.getMinecraft())
        val xInGui = (sr.scaledWidth - 176) / 2 + x
        val yInGui = (sr.scaledHeight - if (doubleChest) 222 else 168) / 2 + y

        GL11.glTranslated(0.0, 0.0, 1.0)
        Gui.drawRect(xInGui, yInGui, xInGui + 16, yInGui + 16, Color.RED.rgb)
        GL11.glTranslated(0.0, 0.0, -1.0)

    }

    private fun isInLuckyBlock(): Boolean {
        val locraw = LocrawUtil.INSTANCE.locrawInfo
        return locraw?.gameType == LocrawInfo.GameType.SKYWARS && locraw.gameMode == "solo_insane_lucky"
    }
}