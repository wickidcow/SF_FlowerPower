package io.ncbpfluffybear.flowerpower;

import io.github.thebusybiscuit.slimefun4.api.SlimefunAddon;
import io.ncbpfluffybear.flowerpower.setup.FlowerPowerItemSetup;
import io.ncbpfluffybear.flowerpower.setup.ResearchSetup;
import listeners.Events;
import org.bstats.bukkit.Metrics;
import org.bukkit.plugin.java.JavaPlugin;
import utils.Utils;

import javax.annotation.Nonnull;

/**
 * The main class of the FlowerPower addon.
 *
 * @author NCBPFluffyBear
 */
public class FlowerPowerPlugin extends JavaPlugin implements SlimefunAddon {

    private static FlowerPowerPlugin instance;

    @Override
    public void onEnable() {
        instance = this;

        new Metrics(this, 12349);

        Utils.registerEvents(new Events());
        FlowerPowerItemSetup.setup(this);
        ResearchSetup.setup();
    }

    @Override
    public String getBugTrackerURL() {
        return "https://github.com/wickidcow/SF_FlowerPower/issues";
    }

    @Nonnull
    @Override
    public JavaPlugin getJavaPlugin() {
        return this;
    }

    public static FlowerPowerPlugin getInstance() {
        return instance;
    }
}
