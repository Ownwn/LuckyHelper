package com.ownwn.luckyhelper

import cc.polyfrost.oneconfig.events.event.ChatReceiveEvent
import cc.polyfrost.oneconfig.events.event.TickEvent
import cc.polyfrost.oneconfig.libs.eventbus.Subscribe
import cc.polyfrost.oneconfig.utils.hypixel.LocrawUtil
import com.ownwn.luckyhelper.config.Config
import net.minecraft.client.Minecraft

object BadItemWarn {
    var thingy = false;

    @Subscribe
    fun onChat(event: ChatReceiveEvent) {
        println("event is ${event.fullyUnformattedMessage} and config is ${Config.test} and thingy is $thingy")
    }

    @Subscribe
    fun onTick(event: TickEvent) {
        val player = Minecraft.getMinecraft().thePlayer ?: return

        if (LocrawUtil.INSTANCE.locrawInfo?.gameMode != "solo_insane_lucky") {
            thingy = false
            return
        }

        thingy = true

    }

}