package com.github.ysl3000.bukkit.test;

import org.bukkit.entity.Player;

import java.util.List;

/**
 * Created by ysl3000
 */
interface ICommand {
    boolean execute(Player p, List<String> args);
}
