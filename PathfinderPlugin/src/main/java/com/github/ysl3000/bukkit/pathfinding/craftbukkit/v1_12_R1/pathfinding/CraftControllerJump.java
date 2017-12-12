package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_12_R1.pathfinding;


import net.minecraft.server.v1_12_R1.ControllerJump;

/**
 * Created by ysl3000
 */
public class CraftControllerJump implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerJump {


    private ControllerJump controllerJump;

    public CraftControllerJump(ControllerJump controllerJump) {
        this.controllerJump = controllerJump;
    }

    @Override
    public void jump() {
        controllerJump.a();
    }

    @Override
    public void setJumping() {
        controllerJump.b();
    }


}
