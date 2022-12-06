package ram.talia.hexal.common.casting.actions.spells.link

import at.petrak.hexcasting.api.misc.MediaConstants
import at.petrak.hexcasting.api.spell.*
import at.petrak.hexcasting.api.spell.casting.CastingContext
import at.petrak.hexcasting.api.spell.iota.Iota
import ram.talia.hexal.api.HexalAPI
import ram.talia.hexal.api.linkable.ILinkable
import ram.talia.hexal.api.linkable.LinkableRegistry

object OpSendIota : SpellAction {
	private const val COST_SEND_IOTA = MediaConstants.DUST_UNIT / 100
	override val argc = 2

	override fun execute(args: List<Iota>, ctx: CastingContext): Triple<RenderedSpell, Int, List<ParticleSpray>> {
		val linkThis = LinkableRegistry.linkableFromCastingContext(ctx)

		val linkedIndex = args.getPositiveIntUnder(0, linkThis.numLinked(), argc)
		val iota = args[1]

		val other = linkThis.getLinked(linkedIndex)

		return Triple(
			Spell(other, iota),
			COST_SEND_IOTA,
			listOf(ParticleSpray.burst(other.getPosition(), 1.5))
		)
	}

	private data class Spell(val other: ILinkable, val iota: Iota) : RenderedSpell {
		override fun cast(ctx: CastingContext) {
			HexalAPI.LOGGER.debug("sending $iota to $other")
			other.receiveIota(iota)
		}
	}
}