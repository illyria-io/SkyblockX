package net.savagelabs.skyblockx.command.skyblock.cmd

import net.savagelabs.skyblockx.SkyblockX
import net.savagelabs.skyblockx.command.CommandInfo
import net.savagelabs.skyblockx.command.CommandRequirementsBuilder
import net.savagelabs.skyblockx.command.SCommand
import net.savagelabs.skyblockx.core.Permission
import net.savagelabs.skyblockx.persist.Message
import kotlin.time.ExperimentalTime

class CmdSbCalc : SCommand() {

    init {
        aliases.add("calc")


        commandRequirements = CommandRequirementsBuilder().withPermission(Permission.ADMIN_CALC).build()
    }

    @ExperimentalTime
    override fun perform(info: CommandInfo) {
        info.message(Message.instance.commandSkyblockCalcStart)
        SkyblockX.skyblockX.runIslandCalc()

    }

    override fun getHelpInfo(): String {
        return Message.instance.commandSkyblockCalcHelp
    }
}