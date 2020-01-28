package ca.slackinjack.autobg;

import ca.slackinjack.autobg.config.ConfigLoader;
import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.ClientChatReceivedEvent;
import net.minecraftforge.fml.client.FMLClientHandler;
import net.minecraftforge.fml.common.eventhandler.SubscribeEvent;
import net.minecraftforge.fml.common.network.FMLNetworkEvent;

public class AutoBGTrigger {
    private boolean serverIsHypixel;
    private ConfigLoader c;
    
    public AutoBGTrigger(ConfigLoader c) {
        this.c = c;
    }

    @SubscribeEvent
    public void trigger(ClientChatReceivedEvent event) {
        final Minecraft mc = Minecraft.getMinecraft();
        String message = event.message.getUnformattedText();
        Thread sendChats = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Thread.sleep(795);
                    mc.thePlayer.sendChatMessage("/chat a");
                    Thread.sleep(250);
                    mc.thePlayer.sendChatMessage(c.autoBGText);
                    if (c.switchToPartyChat) {
                        Thread.sleep(200);
                        mc.thePlayer.sendChatMessage("/chat p");
                        Thread.sleep(500);
                    }
                } catch (Exception e) {
                }
            }
        });

        if (this.c.autoBGEnabled && this.serverIsHypixel) {
            if (message.contains("1st Killer - ") || message.contains("1st Place - ") || message.contains("1st - ") || message.contains("Winner: ") || message.contains("Winners: ")) {
                sendChats.start();
            }
        }
    }

    @SubscribeEvent
    public void onServerConnect(FMLNetworkEvent.ClientConnectedToServerEvent event) {
        if (event.isLocal) { return; }
        if (FMLClientHandler.instance().getClient().getCurrentServerData() == null) { return; }
        String ip = FMLClientHandler.instance().getClient().getCurrentServerData().serverIP;
        if (ip.toLowerCase().contains("hypixel")) {
            this.serverIsHypixel = true;
        }
    }

    @SubscribeEvent
    public void onServerDisconnect(FMLNetworkEvent.ClientDisconnectionFromServerEvent event) {
        this.serverIsHypixel = false;
    }
}
