package com.github.ysl3000.bukkit.pathfinding.goals;

import com.github.ysl3000.bukkit.pathfinding.entity.Insentient;
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal;
import org.bukkit.entity.Player;

import java.util.Iterator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by ysl3000
 */
public class TalkToStrangers implements PathfinderGoal {


    private final int radius;
    private Insentient insentient;

    private List<String> messages;
    private long delay;

    private Iterator<String> currentMessage;
    private long lastTalk = 0;

    public TalkToStrangers(Insentient insentient, List<String> messages, long delay) {
        this.insentient = insentient;
        this.messages = messages;
        this.delay = delay;
        this.radius = 10;
        this.currentMessage = messages.iterator();
    }


    @Override
    public boolean shouldExecute() {
        return isAllowedToTalk() && hasPlayerInRadius() && (currentMessage == null || currentMessage.hasNext());
    }


    @Override
    public boolean shouldContinueUpdating() {
        return currentMessage.hasNext();
    }

    @Override
    public void init() {
        if (currentMessage == null) currentMessage = messages.iterator();
    }

    @Override
    public void execute() {
        if (currentMessage.hasNext() && isAllowedToTalk()) {
            String cMessage = currentMessage.next();
            getNearbyPlayers().forEach(e -> e.sendMessage(cMessage));
            this.lastTalk = System.currentTimeMillis();
        }
    }

    @Override
    public void reset() {
        currentMessage = null;
    }


    private boolean isAllowedToTalk() {
        return (lastTalk + delay) < System.currentTimeMillis();
    }

    private boolean hasPlayerInRadius() {
        return getNearbyPlayers().size() > 0;
    }


    private List<Player> getNearbyPlayers() {
        return insentient.getBukkitEntity().getNearbyEntities(radius, radius, radius).stream().filter(Player.class::isInstance).map(Player.class::cast).collect(Collectors.toList());
    }


}
