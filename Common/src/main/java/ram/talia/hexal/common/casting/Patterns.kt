@file:Suppress("unused")

package ram.talia.hexal.common.casting

import at.petrak.hexcasting.api.spell.Operator
import at.petrak.hexcasting.api.spell.math.HexDir
import at.petrak.hexcasting.api.spell.math.HexPattern
import at.petrak.hexcasting.common.casting.operators.selectors.*
import net.minecraft.resources.ResourceLocation
import ram.talia.hexal.api.HexalAPI.modLoc
import ram.talia.hexal.api.spell.casting.triggers.WispTriggerTypes
import ram.talia.hexal.common.casting.actions.*
import ram.talia.hexal.common.casting.actions.everbook.*
import ram.talia.hexal.common.casting.actions.spells.*
import ram.talia.hexal.common.casting.actions.spells.great.*
import ram.talia.hexal.common.casting.actions.spells.link.*
import ram.talia.hexal.common.casting.actions.spells.wisp.*
import ram.talia.hexal.common.entities.BaseWisp

object Patterns {

	@JvmField
	var PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Operator>> = ArrayList()
	@JvmField
	var PER_WORLD_PATTERNS: MutableList<Triple<HexPattern, ResourceLocation, Operator>> = ArrayList()

	// ============================ Type Comparison ===================================
	val COMPARE_BLOCKS = make(HexPattern.fromAngles("qaqqaea", HexDir.EAST), modLoc("compare/blocks"), OpCompareBlocks)
	val COMPARE_ENTITIES = make(HexPattern.fromAngles("qawde", HexDir.SOUTH_WEST), modLoc("compare/entities"), OpCompareEntities)
	val COMPARE_TYPES = make(HexPattern.fromAngles("awd", HexDir.SOUTH_WEST), modLoc("compare/types"), OpCompareTypes)

	// ========================== Misc Info Gathering =================================
	val CURRENT_TICK = make(HexPattern.fromAngles("ddwaa", HexDir.NORTH_WEST), modLoc("current_tick"), OpCurrentTick)
	val REMAINING_EVALS = make(HexPattern.fromAngles("eedqa", HexDir.WEST), modLoc("remaining_evals"), OpRemainingEvals)
	val BREATH = make(HexPattern.fromAngles("aqawdwaqawd", HexDir.NORTH_WEST), modLoc("breath"), OpGetBreath)
	val HEALTH =make(HexPattern.fromAngles("aqwawqa", HexDir.NORTH_WEST), modLoc("health"), OpGetHealth)
	val LIGHT_LEVEL = make(HexPattern.fromAngles("qedqde", HexDir.NORTH_EAST), modLoc("light_level"), OpGetLightLevel)

	// =============================== Misc Maths =====================================
	val FACTORIAL = make(HexPattern.fromAngles("wawdedwaw", HexDir.SOUTH_EAST), modLoc("factorial"), OpFactorial)

	// ================================ Everbook ======================================
	val EVERBOOK_READ = make(HexPattern.fromAngles("eweeewedqdeddw", HexDir.NORTH_EAST), modLoc("everbook/read"), OpEverbookRead)
	val EVERBOOK_WRITE = make(HexPattern.fromAngles("qwqqqwqaeaqaaw", HexDir.SOUTH_EAST), modLoc("everbook/write"), OpEverbookWrite)
	val EVERBOOK_DELETE = make(HexPattern.fromAngles("qwqqqwqaww", HexDir.SOUTH_EAST), modLoc("everbook/delete"), OpEverbookDelete)
	val EVERBOOK_TOGGLE_MACRO = make(HexPattern.fromAngles("eweeewedww", HexDir.SOUTH_WEST), modLoc("everbook/toggle_macro"), OpToggleMacro)

	// ============================== Misc Spells =====================================
	val SMELT = make(HexPattern.fromAngles("wqqqwqqadad", HexDir.EAST), modLoc("smelt"), OpSmelt)
	val FREEZE = make(HexPattern.fromAngles("weeeweedada", HexDir.WEST), modLoc("freeze"), OpFreeze)
	val FALLING_BLOCK = make(HexPattern.fromAngles("wqwawqwqwqwqwqw", HexDir.EAST), modLoc("falling_block"), OpFallingBlock)

	// =============================== Wisp Stuff =====================================
	val WISP_SUMMON_PROJECTILE = make(HexPattern.fromAngles("aqaeqeeeee", HexDir.NORTH_WEST), modLoc("wisp/summon/projectile"), OpSummonWisp(false))
	val WISP_SUMMON_TICKING = make(HexPattern.fromAngles("aqaweewaqawee", HexDir.NORTH_WEST), modLoc("wisp/summon/ticking"), OpSummonWisp(true))
	val WISP_MEDIA = make(HexPattern.fromAngles("aqaweewaqaweedw", HexDir.NORTH_WEST), modLoc("wisp/media"), OpWispMedia)
	val WISP_HEX = make(HexPattern.fromAngles("aweewaqaweewaawww", HexDir.SOUTH_EAST), modLoc("wisp/hex"), OpWispHex)

	// Set and Get Move Target WEST awqwawqaw
	val WISP_MOVE_TARGET_SET = make(HexPattern.fromAngles("awqwawqaw", HexDir.WEST), modLoc("wisp/move_target/set"), OpSetMoveTarget)
	val WISP_MOVE_TARGET_GET = make(HexPattern.fromAngles("ewdwewdew", HexDir.EAST), modLoc("wisp/move_target/get"), OpGetMoveTarget)

	// Entity purification and Zone distillations
	val GET_ENTITY_WISP = make(HexPattern.fromAngles("qqwdedwqqdaqaaww", HexDir.SOUTH_EAST),
		                         modLoc("get_entity/wisp"),
		                         OpGetEntityAt{ entity -> entity is BaseWisp })
	val ZONE_ENTITY_WISP = make(HexPattern.fromAngles("qqwdedwqqwdeddww", HexDir.SOUTH_EAST),
		                          modLoc("zone_entity/wisp"),
		                          OpGetEntitiesBy({ entity -> entity is BaseWisp }, false))
	val ZONE_ENTITY_NOT_WISP = make(HexPattern.fromAngles("eewaqaweewaqaaww", HexDir.NORTH_EAST),
		                              modLoc("zone_entity/not_wisp"),
		                              OpGetEntitiesBy({ entity -> entity is BaseWisp }, true))

	// Triggers
	val WISP_TRIGGER_TICK = make(HexPattern.fromAngles("aqawded", HexDir.NORTH_WEST), modLoc("wisp/trigger/tick"), OpWispSetTrigger(WispTriggerTypes.TICK_TRIGGER_TYPE))
	val WISP_TRIGGER_COMM = make(HexPattern.fromAngles("aqqqqqwdeddw", HexDir.EAST), modLoc("wisp/trigger/comm"), OpWispSetTrigger(WispTriggerTypes.COMM_TRIGGER_TYPE))
	val WISP_TRIGGER_MOVE = make(HexPattern.fromAngles("eqwawqwaqww", HexDir.EAST), modLoc("wisp/trigger/move"), OpWispSetTrigger(WispTriggerTypes.MOVE_TRIGGER_TYPE))

	// Great
	val CONSUME_WISP = make(HexPattern.fromAngles("wawqwawwwewwwewwwawqwawwwewwwewdeaweewaqaweewaawwww", HexDir.NORTH_WEST),
						              modLoc("wisp/consume"), OpConsumeWisp, true)

	// =============================== Link Stuff =====================================
	val LINK_ENTITY = make(HexPattern.fromAngles("eaqaaeqqqqqaweaqaaw", HexDir.EAST), modLoc("link/link_entity"), OpLinkEntity)
	val LINK_ENTITIES = make(HexPattern.fromAngles("eqqqqqawqeeeeedww", HexDir.EAST), modLoc("link/link_two_entities"), OpLinkEntities)
	val UNLINK = make(HexPattern.fromAngles("qdeddqeeeeedwqdeddw", HexDir.WEST), modLoc("link/unlink"), OpUnlink)
	val LINK_GET = make(HexPattern.fromAngles("eqqqqqaww", HexDir.EAST), modLoc("link/get"), OpGetLinked)
	val LINK_GET_INDEX = make(HexPattern.fromAngles("aeqqqqqawwd", HexDir.SOUTH_WEST), modLoc("link/get_index"), OpGetLinkedIndex)
	val LINK_NUM = make(HexPattern.fromAngles("qeeeeedww", HexDir.WEST), modLoc("link/num"), OpNumLinked)
	val LINK_COMM_SEND = make(HexPattern.fromAngles("qqqqqwdeddw", HexDir.NORTH_WEST), modLoc("link/comm/send"), OpSendIota)
	val LINK_COMM_READ = make(HexPattern.fromAngles("weeeeew", HexDir.NORTH_EAST), modLoc("link/comm/read"), OpReadReceivedIota)
	val LINK_COMM_NUM = make(HexPattern.fromAngles("aweeeeewaa", HexDir.SOUTH_EAST), modLoc("link/comm/num"), OpNumReceivedIota)
	val LINK_COMM_OPEN_TRANSMIT = make(HexPattern.fromAngles("qwdedwq", HexDir.WEST), modLoc("link/comm/open_transmit"), OpOpenTransmit)
	@JvmField
	val LINK_COMM_CLOSE_TRANSMIT = make(HexPattern.fromAngles("ewaqawe", HexDir.EAST), modLoc("link/comm/close_transmit"), OpCloseTransmit)

	private fun make (pattern: HexPattern, location: ResourceLocation, operator: Operator, isPerWorld: Boolean = false): Triple<HexPattern, ResourceLocation, Operator> {
		val triple = Triple(pattern, location, operator)
		if (isPerWorld)
			PER_WORLD_PATTERNS.add(triple)
		else
			PATTERNS.add(triple)
		return triple
	}
}