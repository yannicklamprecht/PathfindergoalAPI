package com.github.ysl3000.bukkit.pathfinding.craftbukkit.v1_13_R1.pathfinding;

import  net.minecraft.server.v1_13_R1.ControllerMove;

/**
 * Created by ysl3000
 */
public class CraftControllerMove implements com.github.ysl3000.bukkit.pathfinding.pathfinding.ControllerMove {


    private ControllerMove controllerMove;

    public CraftControllerMove(ControllerMove controllerMove) {
        this.controllerMove = controllerMove;
    }


    @Override
    public boolean isOperationMove() {
        return controllerMove.b();
    }

    @Override
    public void move(double motionX, double motionY, double motionZ, double speed) {
        controllerMove.a(motionX,motionY,motionZ,speed);
    }

    @Override
    public void move(float forward, float sideward) {
        controllerMove.a(forward,sideward);
    }

    @Override
    public void update() {
        controllerMove.a();
    }

    @Override
    public double getX() {
        return controllerMove.d();
    }

    @Override
    public double getY() {
        return controllerMove.e();
    }

    @Override
    public double getZ() {
        return controllerMove.f();
    }

    @Override
    public double getSpeed() {
        return controllerMove.c();
    }

}
