package net.savagelabs.skyblockx.command.island.cmd.member

import me.rayzr522.jsonmessage.JSONMessage
import net.savagelabs.savagepluginx.command.Command
import net.savagelabs.skyblockx.command.SCommandInfo
import net.savagelabs.skyblockx.command.SCommandRequirements
import net.savagelabs.skyblockx.command.SCommandRequirementsBuilder
import net.savagelabs.skyblockx.core.IslandPermission
import net.savagelabs.skyblockx.core.Permission
import net.savagelabs.skyblockx.core.color
import net.savagelabs.skyblockx.persist.Message

class CmdMemberList : Command<SCommandInfo, SCommandRequirements>() {

    init {
        aliases.add("list")

        commandRequirements =
            SCommandRequirementsBuilder()
                .withPermission(Permission.MEMBER)
                .asIslandMember(true)
                .withIslandPermission(IslandPermission.MEMBER_LIST)
                .build()
    }


    override fun perform(info: SCommandInfo) {
        info.message(Message.instance.commandMemberListHeader)

        JSONMessage.create(color(String.format(Message.instance.commandMemberListFormat, 1, info.island!!.ownerTag)))
            .tooltip(color(Message.instance.commandMemberListRemoveTooltip))
            .runCommand("/is member kick ${info.island!!.ownerTag}")
            .send(info.player!!)

        for ((index, member) in info.iPlayer!!.getIsland()!!.getIslandMembers().filter { member -> member.isLeader().not() }.withIndex()) {
            JSONMessage.create(color(String.format(Message.instance.commandMemberListFormat, index + 2, member.name)))
                .tooltip(color(Message.instance.commandMemberListRemoveTooltip))
                .runCommand("/is member kick ${member.name}")
                .send(info.player!!)
        }

    }

    override fun getHelpInfo(): String {
        return Message.instance.commandMemberListHelp
    }

}