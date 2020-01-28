package ca.slackinjack.autobg.config;

import ca.slackinjack.autobg.AutoBG;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiScreen;
import net.minecraftforge.common.config.ConfigElement;
import net.minecraftforge.common.config.Configuration;
import net.minecraftforge.fml.client.config.GuiConfig;

public class ConfigGUI extends GuiConfig {

    public ConfigGUI(GuiScreen parent) {
        super(parent, new ConfigElement(AutoBG.getInstance().getConfigLoader().getConfig().getCategory(Configuration.CATEGORY_GENERAL)).getChildElements(), AutoBG.MODID, false, false, "AutoBG Config");
    }

    @Override
    public void initGui() {
        super.initGui();
    }

    @Override
    public void drawScreen(int mouseX, int mouseY, float partialTicks) {
        super.drawScreen(mouseX, mouseY, partialTicks);
    }

    @Override
    protected void actionPerformed(GuiButton button) {
        super.actionPerformed(button);
    }
}
