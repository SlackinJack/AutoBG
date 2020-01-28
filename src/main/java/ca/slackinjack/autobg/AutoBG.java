package ca.slackinjack.autobg;

import ca.slackinjack.autobg.config.ConfigLoader;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod.EventHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLInitializationEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AutoBG.MODID, guiFactory = "ca.slackinjack.autobg.config.GUIConfigFactory", acceptedMinecraftVersions = "1.8.9")

public class AutoBG {
    public static final String MODID = "autobg";
    public static final String VERSION = "1.2.0";
    private final ConfigLoader c = new ConfigLoader();
    private final AutoBGTrigger t;
    private static AutoBG INSTANCE;
    
    public AutoBG() {
        INSTANCE = this;
        t = new AutoBGTrigger(c);
    }

    @EventHandler
    public void preInit(FMLPreInitializationEvent event) {
        this.c.preInit(event);
    }

    @EventHandler
    public void onInit(FMLInitializationEvent event) {
        MinecraftForge.EVENT_BUS.register(t);
    }
    
    public static AutoBG getInstance() {
        return INSTANCE;
    }
    
    public ConfigLoader getConfigLoader() {
        return this.c;
    }
}
