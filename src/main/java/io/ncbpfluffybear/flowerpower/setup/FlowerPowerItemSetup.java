package io.ncbpfluffybear.flowerpower.setup;

import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.implementation.SlimefunItems;
import io.github.thebusybiscuit.slimefun4.libraries.dough.collections.Pair;
import io.github.thebusybiscuit.slimefun4.libraries.dough.items.CustomItemStack;
import io.ncbpfluffybear.flowerpower.FlowerPowerItems;
import io.ncbpfluffybear.flowerpower.FlowerPowerPlugin;
import io.ncbpfluffybear.flowerpower.items.AttributeCharms;
import io.ncbpfluffybear.flowerpower.items.ExperienceTome;
import io.ncbpfluffybear.flowerpower.items.InfinityApple;
import io.ncbpfluffybear.flowerpower.items.InfinityBandage;
import io.ncbpfluffybear.flowerpower.items.OvergrowthSeed;
import io.ncbpfluffybear.flowerpower.items.RecallCharm;
import io.ncbpfluffybear.flowerpower.objects.NonplaceableBlock;
import io.ncbpfluffybear.flowerpower.items.MagicCream;
import io.ncbpfluffybear.flowerpower.multiblocks.ExperienceCauldron;
import io.ncbpfluffybear.flowerpower.multiblocks.MagicBasin;
import org.bukkit.Material;
import org.bukkit.block.BlockFace;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.PotionMeta;
import org.bukkit.potion.PotionData;
import org.bukkit.potion.PotionType;

import javax.annotation.Nonnull;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Registers all of the items
 * @author NCBPFluffyBear
 */
public class FlowerPowerItemSetup {

    // Placeholder item for magic basin item frames
    private static final ItemStack basinFrame = CustomItemStack.create(Material.ITEM_FRAME, null,
            "&7Place on the side of the Experience Cauldron");
    private static final List<Pair<SlimefunItemStack, Material>> allFlowers = new ArrayList<>(Arrays.asList(
            new Pair<>(FlowerPowerItems.GLISTENING_POPPY, Material.POPPY),
            new Pair<>(FlowerPowerItems.GLISTENING_DANDELION, Material.DANDELION),
            new Pair<>(FlowerPowerItems.GLISTENING_OXEYE_DAISY, Material.OXEYE_DAISY),
            new Pair<>(FlowerPowerItems.GLISTENING_ALLIUM, Material.ALLIUM)
    ));

    private static final ItemStack speedPotion = new ItemStack(Material.POTION);
    private static final ItemStack damagePotion = new ItemStack(Material.POTION);
    private static final ItemStack healthPotion = new ItemStack(Material.POTION);
    private static final ItemStack slownessPotion = new ItemStack(Material.POTION);

    private FlowerPowerItemSetup() {
    }

    public static void setup(@Nonnull FlowerPowerPlugin plugin) {

        // Multiblocks
        // This is a placeholder machine; can't be built due to frames
        new MagicBasin(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.MAGIC_BASIN, new ItemStack[]{
                null, basinFrame, null,
                basinFrame, FlowerPowerItems.EXPERIENCE_CAULDRON.item(), basinFrame,
                null, basinFrame, null
        }, BlockFace.SELF).register(plugin);

        // Blocks
        new ExperienceCauldron(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.EXPERIENCE_CAULDRON, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                SlimefunItems.FLASK_OF_KNOWLEDGE.item(), getItem(Material.IRON_BARS), SlimefunItems.FLASK_OF_KNOWLEDGE.item(),
                getItem(Material.BUCKET), getItem(Material.CAULDRON), getItem(Material.BUCKET),
                SlimefunItems.FLASK_OF_KNOWLEDGE.item(), getItem(Material.IRON_BARS), SlimefunItems.FLASK_OF_KNOWLEDGE.item()
        }).register(plugin);

        // Essentials
        new SlimefunItem(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.MAGICAL_WAND, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                null, null, getItem(Material.GLOWSTONE_DUST),
                null, getItem(Material.BLAZE_ROD), null,
                getItem(Material.OXEYE_DAISY), null, null
        }).register(plugin);

        new MagicCream(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.MAGIC_CREAM, RecipeType.MOB_DROP, new ItemStack[]{
                null, null, null,
                null, CustomItemStack.create(new ItemStack(Material.SLIME_SPAWN_EGG), "&aSlime", "&7Kill a Slime"), null,
                null, null, null
        }).register(plugin);

        // Glistening Flowers
        for (Pair<SlimefunItemStack, Material> flower : allFlowers) {
            new NonplaceableBlock(FlowerPowerItems.FLOWERPOWER_CATEGORY, flower.getFirstValue(), MagicBasin.BASIN_RECIPE, new ItemStack[]{
                    getItem(flower.getSecondValue()), FlowerPowerItems.MAGIC_CREAM.item(), null, null, null, null, null, null, null
            }).register(plugin);
        }

        // Overgrowth seed
        new OvergrowthSeed(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.OVERGROWTH_SEED, RecipeType.MAGIC_WORKBENCH, new ItemStack[]{
                getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS),
                getItem(Material.WHEAT_SEEDS), FlowerPowerItems.MAGIC_CREAM.item(), getItem(Material.WHEAT_SEEDS),
                getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS), getItem(Material.WHEAT_SEEDS)
        }).register(plugin);

        // Flower Crystals
        new NonplaceableBlock(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.RED_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.GLISTENING_POPPY.item(), FlowerPowerItems.MAGIC_CREAM.item(), getItem(Material.QUARTZ_BLOCK),
                getItem(Material.GHAST_TEAR), null, null,
                null, null, null
        }).register(plugin);

        new NonplaceableBlock(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.YELLOW_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.GLISTENING_DANDELION.item(), FlowerPowerItems.MAGIC_CREAM.item(), getItem(Material.QUARTZ_BLOCK),
                getItem(Material.GHAST_TEAR), null, null,
                null, null, null
        }).register(plugin);

        new NonplaceableBlock(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.WHITE_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.GLISTENING_OXEYE_DAISY.item(), FlowerPowerItems.MAGIC_CREAM.item(), getItem(Material.QUARTZ_BLOCK),
                getItem(Material.GHAST_TEAR), null, null,
                null, null, null
        }).register(plugin);

        new NonplaceableBlock(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.PURPLE_CRYSTAL, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.GLISTENING_ALLIUM.item(), FlowerPowerItems.MAGIC_CREAM.item(), getItem(Material.QUARTZ_BLOCK),
                getItem(Material.GHAST_TEAR), null, null,
                null, null, null
        }).register(plugin);

        // Charms
        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.MOVEMENT_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.RED_CRYSTAL.item(), speedPotion, SlimefunItems.TALISMAN_TRAVELLER.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.MOVEMENT_SPEED).register(plugin);

        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.ATTACK_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.PURPLE_CRYSTAL.item(), speedPotion, SlimefunItems.TALISMAN_HUNTER.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.ATTACK_SPEED).register(plugin);

        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.FLY_SPEED_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.RED_CRYSTAL.item(), getItem(Material.ELYTRA), SlimefunItems.TALISMAN_TRAVELLER.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.FLY_SPEED).register(plugin);

        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.DAMAGE_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.WHITE_CRYSTAL.item(), damagePotion, SlimefunItems.TALISMAN_WARRIOR.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.DAMAGE).register(plugin);

        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.HEALTH_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.YELLOW_CRYSTAL.item(), healthPotion, SlimefunItems.TALISMAN_KNIGHT.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.MAX_HEALTH).register(plugin);

        new AttributeCharms(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.KNOCKBACK_RESISTANCE_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                FlowerPowerItems.PURPLE_CRYSTAL.item(), slownessPotion, SlimefunItems.TALISMAN_TRAVELLER.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }, AttributeCharms.Charm.KNOCKBACK_RESISTANCE).register(plugin);

        new ExperienceTome(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.EXPERIENCE_TOME, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                getItem(Material.WRITABLE_BOOK), FlowerPowerItems.EXPERIENCE_CAULDRON.item(), SlimefunItems.ENCHANTMENT_RUNE.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }).register(plugin);

        new InfinityApple(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.INFINITY_APPLE, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                getItem(Material.APPLE), FlowerPowerItems.RED_CRYSTAL.item(), SlimefunItems.ENCHANTMENT_RUNE.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }).register(plugin);

        new InfinityBandage(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.INFINITY_BANDAGE, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                SlimefunItems.BANDAGE.item(), FlowerPowerItems.YELLOW_CRYSTAL.item(), SlimefunItems.ENCHANTMENT_RUNE.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }).register(plugin);

        new RecallCharm(FlowerPowerItems.FLOWERPOWER_CATEGORY, FlowerPowerItems.RECALL_CHARM, MagicBasin.BASIN_RECIPE, new ItemStack[]{
                SlimefunItems.MAGIC_EYE_OF_ENDER.item(), FlowerPowerItems.PURPLE_CRYSTAL.item(), SlimefunItems.ENCHANTMENT_RUNE.item(),
                getItem(Material.NETHER_STAR), null, null,
                null, null, null
        }).register(plugin);

    }

    private static ItemStack getItem(Material mat) {
        return new ItemStack(mat);
    }

    static {
        // Build vanilla potion types for recipes
        PotionMeta speedPotionMeta = (PotionMeta) speedPotion.getItemMeta();
        speedPotionMeta.setBasePotionType(PotionType.SWIFTNESS);
        speedPotion.setItemMeta(speedPotionMeta);

        PotionMeta damagePotionMeta = (PotionMeta) damagePotion.getItemMeta();
        damagePotionMeta.setBasePotionType(PotionType.STRONG_STRENGTH);
        damagePotion.setItemMeta(damagePotionMeta);

        PotionMeta healthPotionMeta = (PotionMeta) healthPotion.getItemMeta();
        healthPotionMeta.setBasePotionType(PotionType.STRONG_HEALING);
        healthPotion.setItemMeta(healthPotionMeta);

        PotionMeta slownessPotionMeta = (PotionMeta) slownessPotion.getItemMeta();
        slownessPotionMeta.setBasePotionType(PotionType.STRONG_SLOWNESS);
        slownessPotion.setItemMeta(slownessPotionMeta);

    }

}
