package com.ownwn.luckyhelper.config

import cc.polyfrost.oneconfig.config.Config
import cc.polyfrost.oneconfig.config.annotations.Switch
import cc.polyfrost.oneconfig.config.data.Mod
import cc.polyfrost.oneconfig.config.data.ModType
import com.ownwn.luckyhelper.LuckyHelper

object Config : Config(
    Mod(LuckyHelper.NAME, ModType.HYPIXEL),
    "luckyhelper.json",
    true,
    false
) {

    @Switch(
        name = "test",
        description = "test",
        category = "Features"
    )
    var test: Boolean = false


    init {
        initialize()
    }
}