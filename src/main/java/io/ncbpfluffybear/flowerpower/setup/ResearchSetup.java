package io.ncbpfluffybear.flowerpower.setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.researches.Research;
import io.ncbpfluffybear.flowerpower.FlowerPowerItems;
import io.ncbpfluffybear.flowerpower.FlowerPowerPlugin;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;

/**
 * Registers all of the research
 * @author NCBPFluffyBear
 */
public final class ResearchSetup {

    private ResearchSetup() {}

    public static void setup() {

        register("magic_crafting", 2711, "Magic Crafting", 5, FlowerPowerItems.MAGIC_BASIN.item(), FlowerPowerItems.MAGICAL_WAND.item());
        register("experience_cauldron", 2712, "Experience Cauldron", 5, FlowerPowerItems.EXPERIENCE_CAULDRON.item());
        register("glistening_resources", 2713, "Glistening Resources", 10,
                FlowerPowerItems.MAGIC_CREAM.item(), FlowerPowerItems.GLISTENING_POPPY.item(), FlowerPowerItems.GLISTENING_DANDELION.item(),
                FlowerPowerItems.GLISTENING_OXEYE_DAISY.item(), FlowerPowerItems.GLISTENING_ALLIUM.item(), FlowerPowerItems.RED_CRYSTAL.item(),
                FlowerPowerItems.YELLOW_CRYSTAL.item(), FlowerPowerItems.WHITE_CRYSTAL.item(), FlowerPowerItems.PURPLE_CRYSTAL.item()
        );
        register("experience_storage", 2714, "Experience Storage", 50, FlowerPowerItems.EXPERIENCE_TOME.item());
        register("attribute_charms", 2715, "Attribute Charms", 50, FlowerPowerItems.MOVEMENT_SPEED_CHARM.item(),
                FlowerPowerItems.ATTACK_SPEED_CHARM.item(), FlowerPowerItems.FLY_SPEED_CHARM.item(), FlowerPowerItems.DAMAGE_CHARM.item(),
                FlowerPowerItems.HEALTH_CHARM.item(), FlowerPowerItems.KNOCKBACK_RESISTANCE_CHARM.item()
        );
        register("recall_teleportation", 2716, "Recall Teleportation", 30, FlowerPowerItems.RECALL_CHARM.item());
        register("infinity_magic", 2717, "Infinity Magic", 30, FlowerPowerItems.INFINITY_APPLE.item(), FlowerPowerItems.INFINITY_BANDAGE.item());
        register("faster_flower_growth", 2718, "Faster Flower Growth", 10, FlowerPowerItems.OVERGROWTH_SEED.item());

    }

    private static void register(String key, int id, String name, int defaultCost, ItemStack... items) {
        Research research = new Research(new NamespacedKey(FlowerPowerPlugin.getInstance(), key), id, name, defaultCost);

        for (ItemStack item : items) {
            SlimefunItem sfItem = SlimefunItem.getByItem(item);

            if (sfItem != null) {
                research.addItems(sfItem);
            }
        }

        research.register();
    }
}
