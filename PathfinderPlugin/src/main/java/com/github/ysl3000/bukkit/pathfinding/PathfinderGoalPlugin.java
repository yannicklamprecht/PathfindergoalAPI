package com.github.ysl3000.bukkit.pathfinding;

import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public class PathfinderGoalPlugin extends JavaPlugin {

    private PathfinderManager craftPathfinderManager;

    @Override
    public void onDisable() {

        this.craftPathfinderManager=null;

    }

    @Override
    public void onEnable() {

        String version = Bukkit.getServer().getClass().getPackage().getName().replace(".", ",").split(",")[3];

        // TODO version version differentiation

        switch (version){

            case "v1_12_R1": this.craftPathfinderManager= new com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding.CraftPathfinderManager(); break;
            case "v1_13_R1": this.craftPathfinderManager= new com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding.CraftPathfinderManager(); break;

            default:
                System.out.println("This version of Minecraft is not supported");
                Bukkit.getPluginManager().disablePlugin(this);
        }
        PathfinderGoalAPI.setApi(this.craftPathfinderManager);
    }
}
