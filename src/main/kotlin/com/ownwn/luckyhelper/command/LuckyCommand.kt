package com.ownwn.luckyhelper.command

import cc.polyfrost.oneconfig.utils.commands.annotations.Command
import cc.polyfrost.oneconfig.utils.commands.annotations.Main
import com.ownwn.luckyhelper.LuckyHelper
import com.ownwn.luckyhelper.config.Config


@Command(value = LuckyHelper.MODID, description = "Access the " + LuckyHelper.NAME + " GUI.")
object LuckyCommand {
    @Main
    private fun handle() {
        Config.openGui()
    }
}