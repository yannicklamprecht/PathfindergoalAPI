package com.github.ysl3000.bukkit.test;

import java.util.List;
import org.bukkit.entity.Player;

/**
 * Created by ysl3000
 */
interface ICommand {

    boolean execute(Player p, List<String> args);
}
