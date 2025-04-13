package com.ownwn.luckyhelper

import cc.polyfrost.oneconfig.events.EventManager
import cc.polyfrost.oneconfig.utils.commands.CommandManager
import com.ownwn.luckyhelper.command.LuckyCommand
import net.minecraftforge.fml.common.Mod
import net.minecraftforge.fml.common.event.FMLInitializationEvent

@Mod(
    modid = LuckyHelper.MODID,
    name = LuckyHelper.NAME,
    version = LuckyHelper.VERSION,
    modLanguageAdapter = "cc.polyfrost.oneconfig.utils.KotlinLanguageAdapter"
)
object LuckyHelper {
    const val MODID = "@ID@"
    const val NAME = "@NAME@"
    const val VERSION = "@VER@"

    @Mod.EventHandler
    fun onInit(event: FMLInitializationEvent?) {
        CommandManager.INSTANCE.registerCommand(LuckyCommand)

        EventManager.INSTANCE.register(BadItemWarn)
    }
}