package io.github.lounode.extrabotany.common.item.equipment.tool.hammer;

import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.PickaxeItem;
import net.minecraft.world.item.Tier;
import net.minecraft.world.item.enchantment.Enchantments;
import net.minecraft.world.level.block.state.BlockState;

import vazkii.botania.api.BotaniaAPI;
import vazkii.botania.api.item.SortableTool;

public class ManasteelHammerItem extends PickaxeItem implements SortableTool {
	private static final int MANA_PER_DAMAGE = 60;

	public ManasteelHammerItem(Tier tier, int attackDamageModifier, float attackSpeedModifier, Properties properties) {
		super(tier, properties.attributes(PickaxeItem.createAttributes(tier, attackDamageModifier, attackSpeedModifier)));
	}

	public int getManaPerDamage() {
		return MANA_PER_DAMAGE;
	}

	@Override
	public int getSortingPriority(ItemStack stack, BlockState state) {
		Tier tier = getTier();
		int tierPriority = 0;
		if (tier == BotaniaAPI.instance().getManasteelItemTier()) {
			tierPriority = 10;
		} else if (tier == BotaniaAPI.instance().getElementiumItemTier()) {
			tierPriority = 11;
		} else if (tier == BotaniaAPI.instance().getTerrasteelItemTier()) {
			tierPriority = 20;
		}

		int efficiency = 0;
		for (var entry : stack.getEnchantments().entrySet()) {
			if (entry.getKey().is(Enchantments.EFFICIENCY)) {
				efficiency = entry.getIntValue();
				break;
			}
		}
		return tierPriority * 100 + efficiency;
	}
}
