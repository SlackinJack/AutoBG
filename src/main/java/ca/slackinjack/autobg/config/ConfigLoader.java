package ca.slackinjack.autobg.config;

import ca.slackinjack.autobg.AutoBG;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.event.ConfigChangedEvent;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;

public class ConfigLoader {
    private static Configuration c;
    public boolean autoBGEnabled;
    public String autoBGText = "";
    public boolean switchToPartyChat;

    public void preInit(FMLPreInitializationEvent event) {
        c = new Configuration(event.getSuggestedConfigurationFile(), AutoBG.VERSION);
        this.syncConfig();
        if (c.getLoadedConfigVersion() == null || !c.getLoadedConfigVersion().equals(AutoBG.VERSION)) {
            event.getSuggestedConfigurationFile().delete();
            c = new Configuration(event.getSuggestedConfigurationFile(), AutoBG.VERSION);
            c.save();
            this.syncConfig();
        }
        
        MinecraftForge.EVENT_BUS.register(new ConfigLoader());
    }

    @SubscribeEvent
    public void onConfigChanged(ConfigChangedEvent.OnConfigChangedEvent event) {
        if (event.modID.equals(AutoBG.MODID)) {
            c.save();
            this.syncConfig();
        }
    }

    private void syncConfig() {
        try {
            c.load();
            autoBGEnabled = c.getBoolean("Enabled AutoBG", Configuration.CATEGORY_GENERAL, true, "Enable the mod");
            autoBGText = c.getString("Custom AutoBG Text", Configuration.CATEGORY_GENERAL, "bg", "Use this field to customize your 'bg' text");
            switchToPartyChat = c.getBoolean("Party Chat on Finish", Configuration.CATEGORY_GENERAL, true, "Automatically switch back to party chat on send");
        } catch (Exception e) {
        }
    }
    
    public Configuration getConfig() {
        return this.c;
    }
}
