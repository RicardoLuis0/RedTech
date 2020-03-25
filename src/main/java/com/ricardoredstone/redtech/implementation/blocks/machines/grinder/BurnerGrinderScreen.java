package com.ricardoredstone.redtech.implementation.blocks.machines.grinder;

import com.mojang.blaze3d.platform.GlStateManager;
import com.ricardoredstone.redtech.RedTechMod;
import net.minecraft.client.gui.screen.inventory.ContainerScreen;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.util.ResourceLocation;
import net.minecraft.util.text.ITextComponent;

public class BurnerGrinderScreen extends ContainerScreen<BurnerGrinderContainer> {
    private static final ResourceLocation guiTexture= RedTechMod.makeResourceLocation("textures/gui/machines/burner_grinder.png");

    public BurnerGrinderScreen(BurnerGrinderContainer container, PlayerInventory playerInventory, ITextComponent title){
        super(container,playerInventory,title);
    }

    @Override
    public void render(int mouseX, int mouseY, float partialTicks){
        renderBackground();
        super.render(mouseX, mouseY, partialTicks);
        renderHoveredToolTip(mouseX,mouseY);
    }

    @Override
    protected void drawGuiContainerForegroundLayer(int mouseX, int mouseY) {
        String title = this.title.getFormattedText();
        this.font.drawString(title, (float)(this.xSize / 2 - this.font.getStringWidth(title) / 2), 6.0F, 4210752);
        this.font.drawString(this.playerInventory.getDisplayName().getFormattedText(), 8.0F, (float)(this.ySize - 96 + 2), 4210752);
    }

    @Override
    protected void drawGuiContainerBackgroundLayer(float partialTicks, int mouseX, int mouseY) {
        if(minecraft==null) return;
        GlStateManager.color4f(1.0F, 1.0F, 1.0F, 1.0F);
        minecraft.getTextureManager().bindTexture(guiTexture);
        int x=guiLeft;
        int y=guiTop;
        this.blit(x,y,0,0,xSize,ySize);
        int temp;
        if (container.isBurning()) {
            temp = container.getBurnLeftScaled();
            this.blit(x + 56, y + 36 + 12 - temp, 176, 12 - temp, 14, temp + 1);
        }

        temp = container.getCookProgressionScaled();
        this.blit(x + 79, y + 34, 176, 14, temp + 1, 16);
    }
/*
    public void init() {
        super.init();
    }
*/

}
