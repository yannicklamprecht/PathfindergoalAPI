package com.github.ysl3000.bukkit.test

import com.github.ysl3000.bukkit.pathfinding.PathfinderGoalAPI
import com.github.ysl3000.bukkit.pathfinding.entity.Insentient
import com.github.ysl3000.bukkit.pathfinding.goals.PathfinderGoalGimmiCookie
import com.github.ysl3000.bukkit.pathfinding.goals.PathfinderGoalMoveToLocation
import com.github.ysl3000.bukkit.pathfinding.goals.TalkToStrangers
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderGoal
import com.github.ysl3000.bukkit.pathfinding.pathfinding.PathfinderManager
import org.bukkit.Bukkit
import org.bukkit.Location
import org.bukkit.entity.Player
import org.bukkit.entity.Zombie
import org.bukkit.event.EventHandler
import org.bukkit.event.Listener
import org.bukkit.event.player.AsyncPlayerChatEvent
import org.bukkit.plugin.java.JavaPlugin

import java.util.*
import java.util.concurrent.TimeUnit

/**
 * Created by ysl3000
 */
class PathfinderTestPlugin : JavaPlugin(), Listener {

    private var pathfinderManager: PathfinderManager? = null

    private val commandMap = HashMap<String, (p: Player, args: List<String>) -> Boolean>()

    override fun onEnable() {

        this.pathfinderManager = PathfinderGoalAPI.getAPI()

        commandMap["chat"] = this::chatCommand
        commandMap["cookie"] = this::deliverCookieCommand
        commandMap["move"] = this::moveToLocationCommand
        commandMap["print"] = this::printGoalCommand

        Bukkit.getServer().pluginManager.registerEvents(this, this)
    }


    @EventHandler
    fun onChat(event: AsyncPlayerChatEvent) {

        val player = event.player

        val args = Arrays.asList<String>(*event.message.split(" ".toRegex()).dropLastWhile { it.isEmpty() }.toTypedArray())

        if (args.size > 0) {

            val command = args[0]

            val iCommand = commandMap[command]

            var param: List<String> = ArrayList()

            if (args.size > 1) {
                param = args.subList(1, args.size)
            }

            val finalParam = param
            Bukkit.getScheduler().runTask(this, {
                iCommand?.invoke(player, finalParam)
            } as Runnable)
        }
    }


    private fun clear(insentient: Insentient?) {
        insentient?.let {
            insentient.clearPathfinderGoals()
            insentient.clearTargetPathfinderGoals()
        }
    }

    private fun chatCommand(p: Player, args: List<String>): Boolean {
        val creature = p.world.spawn<Zombie>(p.location, Zombie::class.java)
        val pathfinderGoalEntity = this.pathfinderManager?.getPathfindeGoalEntity(creature)
        clear(pathfinderGoalEntity)
        pathfinderGoalEntity?.let { insentient: Insentient ->
            {

                insentient.addPathfinderGoal(0,
                        TalkToStrangers(insentient, args, TimeUnit.SECONDS.toMillis(20)))
            }
        }

        return true
    }

    private fun deliverCookieCommand(p: Player, args: List<String>): Boolean {

        val creature = p.world
                .spawn<Zombie>(Location(p.world, 235.0, 70.0, 246.0), Zombie::class.java)
        val pathfinderGoalEntity = this.pathfinderManager?.getPathfindeGoalEntity(creature)
        clear(pathfinderGoalEntity)
        pathfinderGoalEntity?.addPathfinderGoal(0, PathfinderGoalGimmiCookie(pathfinderGoalEntity, creature))
        return true
    }


    private fun moveToLocationCommand(p: Player, args: List<String>): Boolean {

        val creature = p.world.spawn<Zombie>(p.location, Zombie::class.java)
        val pathfinderGoalEntity = this.pathfinderManager?.getPathfindeGoalEntity(creature)

        clear(pathfinderGoalEntity)
        pathfinderGoalEntity?.addPathfinderGoal(0,
                PathfinderGoalMoveToLocation(
                        pathfinderGoalEntity, Location(p.world, 235.0, 70.0, 246.0),
                        2.0, 0.0))
        return true
    }


    private fun printGoalCommand(p: Player, args: List<String>): Boolean {
        val creature = p.world.spawn<Zombie>(p.location, Zombie::class.java)

        val pathfinderGoalEntity = this.pathfinderManager?.getPathfindeGoalEntity(creature)
        clear(pathfinderGoalEntity)
        pathfinderGoalEntity?.addPathfinderGoal(0,
                PathfinderGoalPrint()
        )

        return true
    }

    private inner class PathfinderGoalPrint : PathfinderGoal {

        private var shE = true
        private var shT = true

        override fun shouldExecute(): Boolean {
            println("called should execute")
            shE = shE.not()
            return shE
        }

        override fun shouldTerminate(): Boolean {
            println("Called should terminate")
            shT = shE.not()
            return shT
        }

        override fun init() {
            println("Called Init")
        }

        override fun execute() {
            println("Called execute")
        }

        override fun reset() {
            println("Called reset")
        }
    }

}
