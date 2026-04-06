package net.kaupenjoe.mccourse.event;

import com.mojang.blaze3d.pipeline.RenderPipeline;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.kaupenjoe.mccourse.MCCourse;
import net.kaupenjoe.mccourse.attachment.ModAttachmentTypes;
import net.kaupenjoe.mccourse.keybind.ModKeyMappings;
import net.kaupenjoe.mccourse.networking.packet.TestPayloadC2S;
import net.minecraft.client.DeltaTracker;
import net.minecraft.client.Minecraft;
import net.minecraft.client.gui.GuiGraphicsExtractor;
import net.minecraft.client.renderer.RenderPipelines;
import net.minecraft.network.chat.Component;
import net.minecraft.resources.Identifier;

public class ModClientEvents {
    public static void onEndTick(Minecraft client) {
        while (ModKeyMappings.K_KEYBIND.consumeClick()) {
            client.player.sendSystemMessage(Component.literal("I just pressed the Kaupen Key (Default: K)"));
            ClientPlayNetworking.send(new TestPayloadC2S("Kaupenjoe", 42));
        }
    }

    public static void addFirstHudElement(GuiGraphicsExtractor guiGraphicsExtractor, DeltaTracker tracker) {
        int x = guiGraphicsExtractor.guiWidth() / 2;
        int y = guiGraphicsExtractor.guiHeight();

        if(!Minecraft.getInstance().player.isCreative() && !Minecraft.getInstance().player.isSpectator()
                && Minecraft.getInstance().player.hasAttached(ModAttachmentTypes.MANA)) { // Needs fixing with a S2C Packet!
            for(int i = 0; i < 5; i++) {
                guiGraphicsExtractor.blitSprite(RenderPipelines.GUI_OPAQUE_TEXTURED_BACKGROUND, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_icon_bg"),
                        16, 16, 0, 0, x - 95 + i * 18, y - 55, 16, 16);
            }

            for(int i = 0; i < Minecraft.getInstance().player.getAttached(ModAttachmentTypes.MANA); i++) {
                guiGraphicsExtractor.blitSprite(RenderPipelines.GUI_OPAQUE_TEXTURED_BACKGROUND, Identifier.fromNamespaceAndPath(MCCourse.MOD_ID, "mana_icon"),
                        16, 16, 0, 0, x - 95 + i * 18, y - 55, 16, 16);
            }
        }
    }
}
