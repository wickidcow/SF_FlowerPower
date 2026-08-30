package io.ncbpfluffybear.flowerpower.multiblocks;

import io.github.thebusybiscuit.slimefun4.api.events.PlayerRightClickEvent;
import io.github.thebusybiscuit.slimefun4.api.items.ItemGroup;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItem;
import io.github.thebusybiscuit.slimefun4.api.items.SlimefunItemStack;
import io.github.thebusybiscuit.slimefun4.api.recipes.RecipeType;
import io.github.thebusybiscuit.slimefun4.core.multiblocks.MultiBlockMachine;
import io.github.thebusybiscuit.slimefun4.utils.SlimefunUtils;
import io.ncbpfluffybear.flowerpower.FlowerPowerItems;
import me.mrCookieSlime.Slimefun.api.BlockStorage;
import org.bukkit.Effect;
import org.bukkit.Material;
import org.bukkit.Sound;
import org.bukkit.block.Block;
import org.bukkit.block.data.Levelled;
import org.bukkit.entity.Entity;
import org.bukkit.entity.ItemFrame;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.CauldronLevelChangeEvent;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import utils.ItemStackComparator;
import utils.Utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * A multiblock that stores experience through the cauldron's water level and
 * also stores the handler for the Magic Basin.
 *
 * @author NCBPFluffyBear
 */
public class ExperienceCauldron extends SlimefunItem implements Listener {

    private static final int EXP_PER_LEVEL = 50;
    private static final MultiBlockMachine MAGIC_BASIN = (MultiBlockMachine) MagicBasin.BASIN_RECIPE.getMachine();
    private static final int MAX_CAULDRON_LEVEL = 3;

    public ExperienceCauldron(ItemGroup category, SlimefunItemStack item, RecipeType recipeType, ItemStack[] recipe) {
        super(category, item, recipeType, recipe);
        Utils.registerEvents(this);
    }

    @EventHandler(ignoreCancelled = true)
    private void onCauldronInteract(PlayerRightClickEvent e) {
        Optional<Block> optB = e.getClickedBlock();
        if (optB.isEmpty()) {
            return;
        }

        Block b = optB.get();
        SlimefunItem sfItem = BlockStorage.check(b);
        if (sfItem == null || !isItem(sfItem.getItem())) {
            return;
        }

        if (b.getType() != Material.CAULDRON && b.getType() != Material.WATER_CAULDRON) {
            return;
        }

        if (e.getHand() == EquipmentSlot.OFF_HAND) {
            return;
        }

        Player p = e.getPlayer();
        ItemStack handItem = p.getInventory().getItemInMainHand();
        int cauldronLevel = getCauldronLevel(b);

        if (handItem.getType() != Material.ITEM_FRAME) {
            e.cancel();
        } else {
            return;
        }

        if (SlimefunUtils.isItemSimilar(handItem, FlowerPowerItems.MAGICAL_WAND, false, false)) {
            if (cauldronLevel == 0) {
                Utils.send(p, "&cThis Experience Cauldron is out of experience!");
                return;
            }

            List<ItemFrame> itemFrames = new ArrayList<>();
            for (Entity en : b.getWorld().getNearbyEntities(b.getLocation(), 1.5, 1, 1.5)) {
                if (en instanceof ItemFrame frame && itemFrames.size() < 4) {
                    itemFrames.add(frame);
                }
            }

            if (itemFrames.size() != 4) {
                Utils.send(p, "&cYou need 4 item frames on each side of the Experience Cauldron");
                return;
            }

            ItemStack output = getOutput(getFrameItems(itemFrames));
            if (output != null) {
                craft(b, itemFrames, output);
                return;
            }

            Utils.send(p, "&cInvalid Recipe!");
            return;
        }

        int exp = Utils.getTotalExperience(p);
        if (p.isSneaking()) {
            if (cauldronLevel == 0) {
                Utils.send(p, "&cThis Experience Cauldron is already empty");
                return;
            }

            p.giveExp(EXP_PER_LEVEL);
            changeLevel(b, -1);
            p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL, 1, 0.1f);
            return;
        }

        if (exp < EXP_PER_LEVEL) {
            Utils.send(p, "&cYou do not have enough exp to deposit");
            return;
        }

        if (cauldronLevel == MAX_CAULDRON_LEVEL) {
            Utils.send(p, "&cThis Experience Cauldron is full");
            return;
        }

        p.giveExp(-EXP_PER_LEVEL);
        changeLevel(b, 1);
        p.playSound(p.getLocation(), Sound.ITEM_BUCKET_FILL, 1, 1);
    }

    @EventHandler(ignoreCancelled = true)
    private void onCauldronLevelChange(CauldronLevelChangeEvent e) {
        SlimefunItem sfItem = BlockStorage.check(e.getBlock());
        if (sfItem != null && isItem(sfItem.getItem())) {
            e.setCancelled(true);
        }
    }

    private static void changeLevel(Block b, int change) {
        if (b.getType() == Material.CAULDRON) {
            if (change <= 0) {
                return;
            }

            b.setType(Material.WATER_CAULDRON);
            Levelled cauldron = (Levelled) b.getBlockData();
            cauldron.setLevel(change);
            b.setBlockData(cauldron);
            return;
        }

        Levelled cauldron = (Levelled) b.getBlockData();
        int newLevel = cauldron.getLevel() + change;
        if (newLevel <= 0) {
            b.setType(Material.CAULDRON);
        } else {
            cauldron.setLevel(Math.min(newLevel, cauldron.getMaximumLevel()));
            b.setBlockData(cauldron);
        }
    }

    private static int getCauldronLevel(Block b) {
        if (b.getType() == Material.CAULDRON) {
            return 0;
        }
        if (b.getType() == Material.WATER_CAULDRON) {
            return ((Levelled) b.getBlockData()).getLevel();
        }
        return 0;
    }

    private static List<ItemStack> getFrameItems(List<ItemFrame> itemFrames) {
        List<ItemStack> frameItems = new ArrayList<>();
        for (ItemFrame frame : itemFrames) {
            ItemStack frameItem = frame.getItem();
            if (frameItem.getType() != Material.AIR) {
                SlimefunItem sfFrameItem = SlimefunItem.getByItem(frameItem);
                frameItems.add(sfFrameItem != null ? sfFrameItem.getItem() : frameItem);
            }
        }
        return frameItems;
    }

    private static ItemStack checkRecipe(List<ItemStack> frameItems, ItemStack[] recipeInputs) {
        List<ItemStack> inputItems = new ArrayList<>(frameItems);
        List<ItemStack> recipeItems = new ArrayList<>();

        for (ItemStack recipeItem : recipeInputs) {
            if (recipeItem != null) {
                recipeItems.add(recipeItem);
            }
        }

        inputItems.sort(new ItemStackComparator());
        recipeItems.sort(new ItemStackComparator());

        if (inputItems.size() == recipeItems.size()) {
            for (int i = inputItems.size() - 1; i >= 0; i--) {
                if (SlimefunUtils.isItemSimilar(inputItems.get(i), recipeItems.get(i), false, true)) {
                    inputItems.remove(i);
                    recipeItems.remove(i);
                } else {
                    return null;
                }
            }
        }

        if (inputItems.isEmpty() && recipeItems.isEmpty()) {
            return RecipeType.getRecipeOutputList(MAGIC_BASIN, recipeInputs);
        }
        return null;
    }

    private ItemStack getOutput(List<ItemStack> frameItems) {
        for (ItemStack[] recipeInputs : RecipeType.getRecipeInputList(MAGIC_BASIN)) {
            ItemStack output = checkRecipe(frameItems, recipeInputs);
            if (output != null) {
                return output;
            }
        }
        return null;
    }

    private static void craft(Block b, List<ItemFrame> itemFrames, ItemStack output) {
        for (ItemFrame frame : itemFrames) {
            frame.setItem(new ItemStack(Material.AIR));
        }

        changeLevel(b, -1);
        b.getWorld().playSound(b.getLocation(), Sound.BLOCK_BEACON_POWER_SELECT, 0.5F, 1F);

        Utils.runSync(() -> {
            b.getWorld().playEffect(b.getLocation(), Effect.POTION_BREAK, 1);
            b.getWorld().dropItem(b.getLocation().add(0, 1, 0), output);
        }, 20);
    }
}
