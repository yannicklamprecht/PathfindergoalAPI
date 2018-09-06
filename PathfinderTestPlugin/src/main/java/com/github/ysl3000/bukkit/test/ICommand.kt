package com.github.ysl3000.bukkit.test

import org.bukkit.entity.Player

/**
 * Created by ysl3000
 */
interface ICommand {

    fun execute(p: Player, args: List<String>): Boolean
}
