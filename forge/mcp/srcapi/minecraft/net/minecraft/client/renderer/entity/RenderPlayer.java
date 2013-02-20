package net.minecraft.client.renderer.entity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.client.Minecraft;
import net.minecraft.client.entity.EntityPlayerSP;
import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelPlayer;
import net.minecraft.client.renderer.RenderBlocks;
import net.minecraft.client.renderer.Tessellator;
import net.minecraft.client.renderer.tileentity.TileEntitySkullRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.EnumArmorMaterial;
import net.minecraft.item.Item;
import net.minecraft.item.ItemArmor;
import net.minecraft.item.ItemBlock;
import net.minecraft.item.ItemStack;
import net.minecraft.util.MathHelper;

import org.lwjgl.opengl.GL11;

public class RenderPlayer extends RenderLiving
{
    private ModelBiped modelBipedMain;
    private ModelBiped modelArmorChestplate;
    private ModelBiped modelArmor;
    public static String[] armorFilenamePrefix = new String[] {"cloth", "chain", "iron", "diamond", "gold"};
    public static float NAME_TAG_RANGE = 64.0F;
    public static float NAME_TAG_RANGE_SNEAK = 32.0F;
    private static final Class forgeHooksClient = TryLoadType("net.minecraftforge.client.ForgeHooksClient");
    private static final Method getArmorTexture = TryLoadMethod(forgeHooksClient, "getArmorTexture", new Class[] {ItemStack.class, String.class});
    private static final Class itemRenderType = TryLoadType("net.minecraftforge.client.IItemRenderer$ItemRenderType");
    private static final Object equipped = TryLoadEnum(itemRenderType, "EQUIPPED");
    private static final Class itemRendererHelper = TryLoadType("net.minecraftforge.client.IItemRenderer$ItemRendererHelper");
    private static final Object block3D = TryLoadEnum(itemRendererHelper, "BLOCK_3D");
    private static final Class minecraftForgeClient = TryLoadType("net.minecraftforge.client.MinecraftForgeClient");
    private static final Method getItemRenderer = TryLoadMethod(minecraftForgeClient, "getItemRenderer", new Class[] {ItemStack.class, itemRenderType});
    private static final Class iItemRenderer = TryLoadType("net.minecraftforge.client.IItemRenderer");
    private static final Method shouldUseRenderHelper = TryLoadMethod(iItemRenderer, "shouldUseRenderHelper", new Class[] {itemRenderType, ItemStack.class, itemRendererHelper});
    private static final Method getRenderPasses = TryLoadMethod(Item.class, "getRenderPasses", new Class[] {Integer.TYPE});
    public final RenderPlayerAPI renderPlayerAPI = RenderPlayerAPI.create(this);

    public RenderPlayer()
    {
        super(new ModelPlayer(0.0F), 0.5F);
        RenderPlayerAPI.beforeLocalConstructing(this);
        this.modelBipedMain = (ModelBiped)this.mainModel;
        this.modelArmorChestplate = new ModelPlayer(1.0F);
        this.modelArmor = new ModelPlayer(0.5F);
        RenderPlayerAPI.afterLocalConstructing(this);
    }

    public final RenderPlayerBase getRenderPlayerBase(String var1)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.getRenderPlayerBase(var1) : null;
    }

    public final Set getRenderPlayerBaseIds(String var1)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.getRenderPlayerBaseIds() : Collections.emptySet();
    }

    public Object dynamic(String var1, Object[] var2)
    {
        return this.renderPlayerAPI != null ? this.renderPlayerAPI.dynamic(var1, var2) : null;
    }

    /**
     * Renders the entity's shadow and fire (if its on fire). Args: entity, x, y, z, yaw, partialTickTime
     */
    public void doRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isDoRenderShadowAndFireModded)
        {
            RenderPlayerAPI.doRenderShadowAndFire(this, var1, var2, var4, var6, var8, var9);
        }
        else
        {
            super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
    }

    public final void superDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
    }

    public final void localDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
    }

    public void func_82441_a(EntityPlayer par1EntityPlayer)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isDrawFirstPersonHandModded)
        {
            RenderPlayerAPI.drawFirstPersonHand(this, par1EntityPlayer);
        }
        else
        {
            this.localDrawFirstPersonHand(par1EntityPlayer);
        }
    }

    public final void localDrawFirstPersonHand(EntityPlayer var1)
    {
        float var2 = 1.0F;
        GL11.glColor3f(var2, var2, var2);
        this.modelBipedMain.onGround = 0.0F;
        this.modelBipedMain.setRotationAngles(0.0F, 0.0F, 0.0F, 0.0F, 0.0F, 0.0625F, var1);
        this.modelBipedMain.bipedRightArm.render(0.0625F);
    }

    /**
     * Returns an ARGB int color back. Args: entityLiving, lightBrightness, partialTickTime
     */
    protected int getColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetColorMultiplierModded)
        {
            var4 = RenderPlayerAPI.getColorMultiplier(this, var1, var2, var3);
        }
        else
        {
            var4 = super.getColorMultiplier(var1, var2, var3);
        }

        return var4;
    }

    public final int realGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return this.getColorMultiplier(var1, var2, var3);
    }

    public final int superGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return super.getColorMultiplier(var1, var2, var3);
    }

    public final int localGetColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        return super.getColorMultiplier(var1, var2, var3);
    }

    protected float getDeathMaxRotation(EntityLiving var1)
    {
        float var2;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetDeathMaxRotationModded)
        {
            var2 = RenderPlayerAPI.getDeathMaxRotation(this, var1);
        }
        else
        {
            var2 = super.getDeathMaxRotation(var1);
        }

        return var2;
    }

    public final float realGetDeathMaxRotation(EntityLiving var1)
    {
        return this.getDeathMaxRotation(var1);
    }

    public final float superGetDeathMaxRotation(EntityLiving var1)
    {
        return super.getDeathMaxRotation(var1);
    }

    public final float localGetDeathMaxRotation(EntityLiving var1)
    {
        return super.getDeathMaxRotation(var1);
    }

    /**
     * Returns the font renderer from the set render manager
     */
    public FontRenderer getFontRendererFromRenderManager()
    {
        FontRenderer var1;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isGetFontRendererFromRenderManagerModded)
        {
            var1 = RenderPlayerAPI.getFontRendererFromRenderManager(this);
        }
        else
        {
            var1 = super.getFontRendererFromRenderManager();
        }

        return var1;
    }

    public final FontRenderer superGetFontRendererFromRenderManager()
    {
        return super.getFontRendererFromRenderManager();
    }

    public final FontRenderer localGetFontRendererFromRenderManager()
    {
        return super.getFontRendererFromRenderManager();
    }

    /**
     * Defines what float the third param in setRotationAngles of ModelBase is
     */
    protected float handleRotationFloat(EntityLiving var1, float var2)
    {
        float var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isHandleRotationFloatModded)
        {
            var3 = RenderPlayerAPI.handleRotationFloat(this, var1, var2);
        }
        else
        {
            var3 = super.handleRotationFloat(var1, var2);
        }

        return var3;
    }

    public final float realHandleRotationFloat(EntityLiving var1, float var2)
    {
        return this.handleRotationFloat(var1, var2);
    }

    public final float superHandleRotationFloat(EntityLiving var1, float var2)
    {
        return super.handleRotationFloat(var1, var2);
    }

    public final float localHandleRotationFloat(EntityLiving var1, float var2)
    {
        return super.handleRotationFloat(var1, var2);
    }

    protected int inheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isInheritRenderPassModded)
        {
            var4 = RenderPlayerAPI.inheritRenderPass(this, var1, var2, var3);
        }
        else
        {
            var4 = super.inheritRenderPass(var1, var2, var3);
        }

        return var4;
    }

    public final int realInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.inheritRenderPass(var1, var2, var3);
    }

    public final int superInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.inheritRenderPass(var1, var2, var3);
    }

    public final int localInheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.inheritRenderPass(var1, var2, var3);
    }

    /**
     * loads the specified downloadable texture or alternative built in texture
     */
    protected boolean loadDownloadableImageTexture(String var1, String var2)
    {
        boolean var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isLoadDownloadableImageTextureModded)
        {
            var3 = RenderPlayerAPI.loadDownloadableImageTexture(this, var1, var2);
        }
        else
        {
            var3 = super.loadDownloadableImageTexture(var1, var2);
        }

        return var3;
    }

    public final boolean realLoadDownloadableImageTexture(String var1, String var2)
    {
        return this.loadDownloadableImageTexture(var1, var2);
    }

    public final boolean superLoadDownloadableImageTexture(String var1, String var2)
    {
        return super.loadDownloadableImageTexture(var1, var2);
    }

    public final boolean localLoadDownloadableImageTexture(String var1, String var2)
    {
        return super.loadDownloadableImageTexture(var1, var2);
    }

    /**
     * loads the specified texture
     */
    protected void loadTexture(String var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isLoadTextureModded)
        {
            RenderPlayerAPI.loadTexture(this, var1);
        }
        else
        {
            super.loadTexture(var1);
        }
    }

    public final void realLoadTexture(String var1)
    {
        this.loadTexture(var1);
    }

    public final void superLoadTexture(String var1)
    {
        super.loadTexture(var1);
    }

    public final void localLoadTexture(String var1)
    {
        super.loadTexture(var1);
    }

    /**
     * renders arrows the Entity has been attacked with, attached to it
     */
    protected void renderArrowsStuckInEntity(EntityLiving var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderArrowsModded)
        {
            RenderPlayerAPI.renderArrows(this, var1, var2);
        }
        else
        {
            super.renderArrowsStuckInEntity(var1, var2);
        }
    }

    public final void realRenderArrows(EntityLiving var1, float var2)
    {
        this.renderArrowsStuckInEntity(var1, var2);
    }

    public final void superRenderArrows(EntityLiving var1, float var2)
    {
        super.renderArrowsStuckInEntity(var1, var2);
    }

    public final void localRenderArrows(EntityLiving var1, float var2)
    {
        super.renderArrowsStuckInEntity(var1, var2);
    }

    /**
     * Draws the debug or playername text above a living
     */
    protected void renderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderLivingLabelModded)
        {
            RenderPlayerAPI.renderLivingLabel(this, var1, var2, var3, var5, var7, var9);
        }
        else
        {
            super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
    }

    public final void realRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        this.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    public final void superRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    public final void localRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        super.renderLivingLabel(var1, var2, var3, var5, var7, var9);
    }

    /**
     * Renders the model in RenderLiving
     */
    protected void renderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderModelModded)
        {
            RenderPlayerAPI.renderModel(this, var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.renderModel(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public final void realRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        this.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void superRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void localRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.renderModel(var1, var2, var3, var4, var5, var6, var7);
    }

    /**
     * Used to render a player's name above their head
     */
    protected void renderName(EntityPlayer par1EntityPlayer, double par2, double par4, double par6)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderNameModded)
        {
            RenderPlayerAPI.renderName(this, par1EntityPlayer, par2, par4, par6);
        }
        else
        {
            this.localRenderName(par1EntityPlayer, par2, par4, par6);
        }
    }

    public final void realRenderName(EntityPlayer var1, double var2, double var4, double var6)
    {
        this.renderName(var1, var2, var4, var6);
    }

    public final void localRenderName(EntityPlayer var1, double var2, double var4, double var6)
    {
        if (Minecraft.isGuiEnabled() && var1 != this.renderManager.livingPlayer && !var1.getHasActivePotion())
        {
            float var8 = 1.6F;
            float var9 = 0.01666668F * var8;
            double var10 = var1.getDistanceSqToEntity(this.renderManager.livingPlayer);
            float var12 = var1.isSneaking() ? (forgeHooksClient != null ? NAME_TAG_RANGE : 32.0F) : (forgeHooksClient != null ? NAME_TAG_RANGE_SNEAK : 64.0F);

            if (var10 < (double)(var12 * var12))
            {
                String var13 = var1.username;

                if (var1.isSneaking())
                {
                    FontRenderer var14 = this.getFontRendererFromRenderManager();
                    GL11.glPushMatrix();
                    GL11.glTranslatef((float)var2 + 0.0F, (float)var4 + 2.3F, (float)var6);
                    GL11.glNormal3f(0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(-this.renderManager.playerViewY, 0.0F, 1.0F, 0.0F);
                    GL11.glRotatef(this.renderManager.playerViewX, 1.0F, 0.0F, 0.0F);
                    GL11.glScalef(-var9, -var9, var9);
                    GL11.glDisable(GL11.GL_LIGHTING);
                    GL11.glTranslatef(0.0F, 0.25F / var9, 0.0F);
                    GL11.glDepthMask(false);
                    GL11.glEnable(GL11.GL_BLEND);
                    GL11.glBlendFunc(GL11.GL_SRC_ALPHA, GL11.GL_ONE_MINUS_SRC_ALPHA);
                    Tessellator var15 = Tessellator.instance;
                    GL11.glDisable(GL11.GL_TEXTURE_2D);
                    var15.startDrawingQuads();
                    int var16 = var14.getStringWidth(var13) / 2;
                    var15.setColorRGBA_F(0.0F, 0.0F, 0.0F, 0.25F);
                    var15.addVertex((double)(-var16 - 1), -1.0D, 0.0D);
                    var15.addVertex((double)(-var16 - 1), 8.0D, 0.0D);
                    var15.addVertex((double)(var16 + 1), 8.0D, 0.0D);
                    var15.addVertex((double)(var16 + 1), -1.0D, 0.0D);
                    var15.draw();
                    GL11.glEnable(GL11.GL_TEXTURE_2D);
                    GL11.glDepthMask(true);
                    var14.drawString(var13, -var14.getStringWidth(var13) / 2, 0, 553648127);
                    GL11.glEnable(GL11.GL_LIGHTING);
                    GL11.glDisable(GL11.GL_BLEND);
                    GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
                    GL11.glPopMatrix();
                }
                else if (var1.isPlayerSleeping())
                {
                    this.renderLivingLabel(var1, var13, var2, var4 - 1.5D, var6, 64);
                }
                else
                {
                    this.renderLivingLabel(var1, var13, var2, var4, var6, 64);
                }
            }
        }
    }

    public void renderPlayer(EntityPlayer par1EntityPlayer, double par2, double par4, double par6, float par8, float par9)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerModded)
        {
            RenderPlayerAPI.renderPlayer(this, par1EntityPlayer, par2, par4, par6, par8, par9);
        }
        else
        {
            this.localRenderPlayer(par1EntityPlayer, par2, par4, par6, par8, par9);
        }
    }

    public final void localRenderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        float var10 = 1.0F;
        GL11.glColor3f(var10, var10, var10);
        ItemStack var11 = var1.inventory.getCurrentItem();
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = var11 != null ? 1 : 0;

        if (var11 != null && var1.getItemInUseCount() > 0)
        {
            EnumAction var12 = var11.getItemUseAction();

            if (var12 == EnumAction.block)
            {
                this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 3;
            }
            else if (var12 == EnumAction.bow)
            {
                this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = true;
            }
        }

        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = var1.isSneaking();
        double var14 = var4 - (double)var1.yOffset;

        if (var1.isSneaking() && !(var1 instanceof EntityPlayerSP))
        {
            var14 -= 0.125D;
        }

        super.doRenderLiving(var1, var2, var14, var6, var8, var9);
        this.modelArmorChestplate.aimedBow = this.modelArmor.aimedBow = this.modelBipedMain.aimedBow = false;
        this.modelArmorChestplate.isSneak = this.modelArmor.isSneak = this.modelBipedMain.isSneak = false;
        this.modelArmorChestplate.heldItemRight = this.modelArmor.heldItemRight = this.modelBipedMain.heldItemRight = 0;
    }

    protected void renderPlayerScale(EntityPlayer par1EntityPlayer, float par2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerScaleModded)
        {
            RenderPlayerAPI.renderPlayerScale(this, par1EntityPlayer, par2);
        }
        else
        {
            this.localRenderPlayerScale(par1EntityPlayer, par2);
        }
    }

    public final void realRenderPlayerScale(EntityPlayer var1, float var2)
    {
        this.renderPlayerScale(var1, var2);
    }

    public final void localRenderPlayerScale(EntityPlayer var1, float var2)
    {
        float var3 = 0.9375F;
        GL11.glScalef(var3, var3, var3);
    }

    /**
     * Renders player with sleeping offset if sleeping
     */
    protected void renderPlayerSleep(EntityPlayer par1EntityPlayer, double par2, double par4, double par6)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderPlayerSleepModded)
        {
            RenderPlayerAPI.renderPlayerSleep(this, par1EntityPlayer, par2, par4, par6);
        }
        else
        {
            this.localRenderPlayerSleep(par1EntityPlayer, par2, par4, par6);
        }
    }

    public final void realRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        this.renderPlayerSleep(var1, var2, var4, var6);
    }

    public final void localRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        if (var1.isEntityAlive() && var1.isPlayerSleeping())
        {
            super.renderLivingAt(var1, var2 + (double)var1.field_71079_bU, var4 + (double)var1.field_71082_cx, var6 + (double)var1.field_71089_bV);
        }
        else
        {
            super.renderLivingAt(var1, var2, var4, var6);
        }
    }

    /**
     * Method for adding special render rules
     */
    protected void renderSpecials(EntityPlayer par1EntityPlayer, float par2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialsModded)
        {
            RenderPlayerAPI.renderSpecials(this, par1EntityPlayer, par2);
        }
        else
        {
            this.localRenderSpecials(par1EntityPlayer, par2);
        }
    }

    public final void realRenderSpecials(EntityPlayer var1, float var2)
    {
        this.renderSpecials(var1, var2);
    }

    public final void localRenderSpecials(EntityPlayer var1, float var2)
    {
        float var3 = 1.0F;
        GL11.glColor3f(var3, var3, var3);
        super.renderEquippedItems(var1, var2);
        this.renderArrowsStuckInEntity(var1, var2);
        this.renderSpecialHeadArmor(var1, var2);
        this.renderSpecialHeadEars(var1, var2);
        this.renderSpecialCloak(var1, var2);
        this.renderSpecialItemInHand(var1, var2);
    }

    protected float renderSwingProgress(EntityLiving var1, float var2)
    {
        float var3;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSwingProgressModded)
        {
            var3 = RenderPlayerAPI.renderSwingProgress(this, var1, var2);
        }
        else
        {
            var3 = super.renderSwingProgress(var1, var2);
        }

        return var3;
    }

    public final float realRenderSwingProgress(EntityLiving var1, float var2)
    {
        return this.renderSwingProgress(var1, var2);
    }

    public final float superRenderSwingProgress(EntityLiving var1, float var2)
    {
        return super.renderSwingProgress(var1, var2);
    }

    public final float localRenderSwingProgress(EntityLiving var1, float var2)
    {
        return super.renderSwingProgress(var1, var2);
    }

    /**
     * Rotates the player if the player is sleeping. This method is called in rotateCorpse.
     */
    protected void rotatePlayer(EntityPlayer par1EntityPlayer, float par2, float par3, float par4)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRotatePlayerModded)
        {
            RenderPlayerAPI.rotatePlayer(this, par1EntityPlayer, par2, par3, par4);
        }
        else
        {
            this.localRotatePlayer(par1EntityPlayer, par2, par3, par4);
        }
    }

    public final void realRotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        this.rotatePlayer(var1, var2, var3, var4);
    }

    public final void localRotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        if (var1.isEntityAlive() && var1.isPlayerSleeping())
        {
            GL11.glRotatef(var1.getBedOrientationInDegrees(), 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(this.getDeathMaxRotation(var1), 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(270.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            super.rotateCorpse(var1, var2, var3, var4);
        }
    }

    /**
     * Set the specified armor model as the player model. Args: player, armorSlot, partialTick
     */
    protected int setArmorModel(EntityPlayer par1EntityPlayer, int par2, float par3)
    {
        int var4;

        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetArmorModelModded)
        {
            var4 = RenderPlayerAPI.setArmorModel(this, par1EntityPlayer, par2, par3);
        }
        else
        {
            var4 = this.localSetArmorModel(par1EntityPlayer, par2, par3);
        }

        return var4;
    }

    public final int realSetArmorModel(EntityPlayer var1, int var2, float var3)
    {
        return this.setArmorModel(var1, var2, var3);
    }

    public final int localSetArmorModel(EntityPlayer var1, int var2, float var3)
    {
        ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);

        if (var4 != null)
        {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor)
            {
                ItemArmor var6 = (ItemArmor)var5;
                this.loadTexture(GetArmorTexture(var4, "/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (var2 == 2 ? 2 : 1) + ".png"));
                ModelBiped var7 = var2 == 2 ? this.modelArmor : this.modelArmorChestplate;
                var7.bipedHead.showModel = var2 == 0;
                var7.bipedHeadwear.showModel = var2 == 0;
                var7.bipedBody.showModel = var2 == 1 || var2 == 2;
                var7.bipedRightArm.showModel = var2 == 1;
                var7.bipedLeftArm.showModel = var2 == 1;
                var7.bipedRightLeg.showModel = var2 == 2 || var2 == 3;
                var7.bipedLeftLeg.showModel = var2 == 2 || var2 == 3;
                this.setRenderPassModel(var7);

                if (var7 != null)
                {
                    var7.onGround = this.mainModel.onGround;
                }

                if (var7 != null)
                {
                    var7.isRiding = this.mainModel.isRiding;
                }

                if (var7 != null)
                {
                    var7.isChild = this.mainModel.isChild;
                }

                float var8 = 1.0F;

                if (var6.getArmorMaterial() == EnumArmorMaterial.CLOTH)
                {
                    int var9 = var6.getColor(var4);
                    float var10 = (float)(var9 >> 16 & 255) / 255.0F;
                    float var11 = (float)(var9 >> 8 & 255) / 255.0F;
                    float var12 = (float)(var9 & 255) / 255.0F;
                    GL11.glColor3f(var8 * var10, var8 * var11, var8 * var12);

                    if (var4.isItemEnchanted())
                    {
                        return 31;
                    }

                    return 16;
                }

                GL11.glColor3f(var8, var8, var8);

                if (var4.isItemEnchanted())
                {
                    return 15;
                }

                return 1;
            }
        }

        return -1;
    }

    protected void func_82439_b(EntityPlayer par1EntityPlayer, int par2, float par3)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetPassArmorModelModded)
        {
            RenderPlayerAPI.setPassArmorModel(this, par1EntityPlayer, par2, par3);
        }
        else
        {
            this.localSetPassArmorModel(par1EntityPlayer, par2, par3);
        }
    }

    public final void realSetPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        this.func_82439_b(var1, var2, var3);
    }

    public final void localSetPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        ItemStack var4 = var1.inventory.armorItemInSlot(3 - var2);

        if (var4 != null)
        {
            Item var5 = var4.getItem();

            if (var5 instanceof ItemArmor)
            {
                ItemArmor var6 = (ItemArmor)var5;
                this.loadTexture(GetArmorTexture(var4, "/armor/" + armorFilenamePrefix[var6.renderIndex] + "_" + (var2 == 2 ? 2 : 1) + "_b.png"));
                float var7 = 1.0F;
                GL11.glColor3f(var7, var7, var7);
            }
        }
    }

    /**
     * Sets the RenderManager.
     */
    public void setRenderManager(RenderManager var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetRenderManagerModded)
        {
            RenderPlayerAPI.setRenderManager(this, var1);
        }
        else
        {
            super.setRenderManager(var1);
        }
    }

    public final void superSetRenderManager(RenderManager var1)
    {
        super.setRenderManager(var1);
    }

    public final void localSetRenderManager(RenderManager var1)
    {
        super.setRenderManager(var1);
    }

    /**
     * Sets the model to be used in the current render pass (the first render pass is done after the primary model is
     * rendered) Args: model
     */
    public void setRenderPassModel(ModelBase var1)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isSetRenderPassModelModded)
        {
            RenderPlayerAPI.setRenderPassModel(this, var1);
        }
        else
        {
            super.setRenderPassModel(var1);
        }
    }

    public final void superSetRenderPassModel(ModelBase var1)
    {
        super.setRenderPassModel(var1);
    }

    public final void localSetRenderPassModel(ModelBase var1)
    {
        super.setRenderPassModel(var1);
    }

    protected void renderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialHeadArmorModded)
        {
            RenderPlayerAPI.renderSpecialHeadArmor(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialHeadArmor(var1, var2);
        }
    }

    public final void realRenderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        this.renderSpecialHeadArmor(var1, var2);
    }

    public final void localRenderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        ItemStack var3 = var1.inventory.armorItemInSlot(3);

        if (var3 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedHead.postRender(0.0625F);
            float var4;

            if (IsHeadItemBlock(var3))
            {
                if (IsForge3D(var3) || RenderBlocks.renderItemIn3d(Block.blocksList[var3.itemID].getRenderType()))
                {
                    var4 = 0.625F;
                    GL11.glTranslatef(0.0F, -0.25F, 0.0F);
                    GL11.glRotatef(90.0F, 0.0F, 1.0F, 0.0F);
                    GL11.glScalef(var4, -var4, -var4);
                }

                this.renderManager.itemRenderer.renderItem(var1, var3, 0);
            }
            else if (var3.getItem().itemID == Item.skull.itemID)
            {
                var4 = 1.0625F;
                GL11.glScalef(var4, -var4, -var4);
                String var5 = "";

                if (var3.hasTagCompound() && var3.getTagCompound().hasKey("SkullOwner"))
                {
                    var5 = var3.getTagCompound().getString("SkullOwner");
                }

                TileEntitySkullRenderer.skullRenderer.func_82393_a(-0.5F, 0.0F, -0.5F, 1, 180.0F, var3.getItemDamage(), var5);
            }

            GL11.glPopMatrix();
        }
    }

    protected void renderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialHeadEarsModded)
        {
            RenderPlayerAPI.renderSpecialHeadEars(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialHeadEars(var1, var2);
        }
    }

    public final void realRenderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        this.renderSpecialHeadEars(var1, var2);
    }

    public final void localRenderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        if (var1.username.equals("deadmau5") && this.loadDownloadableImageTexture(var1.skinUrl, (String)null))
        {
            for (int var3 = 0; var3 < 2; ++var3)
            {
                float var4 = var1.prevRotationYaw + (var1.rotationYaw - var1.prevRotationYaw) * var2 - (var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2);
                float var5 = var1.prevRotationPitch + (var1.rotationPitch - var1.prevRotationPitch) * var2;
                GL11.glPushMatrix();
                GL11.glRotatef(var4, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(var5, 1.0F, 0.0F, 0.0F);
                GL11.glTranslatef(0.375F * (float)(var3 * 2 - 1), 0.0F, 0.0F);
                GL11.glTranslatef(0.0F, -0.375F, 0.0F);
                GL11.glRotatef(-var5, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-var4, 0.0F, 1.0F, 0.0F);
                float var6 = 1.333333F;
                GL11.glScalef(var6, var6, var6);
                this.modelBipedMain.renderEars(0.0625F);
                GL11.glPopMatrix();
            }
        }
    }

    protected void renderSpecialCloak(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialCloakModded)
        {
            RenderPlayerAPI.renderSpecialCloak(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialCloak(var1, var2);
        }
    }

    public final void realRenderSpecialCloak(EntityPlayer var1, float var2)
    {
        this.renderSpecialCloak(var1, var2);
    }

    public final void localRenderSpecialCloak(EntityPlayer var1, float var2)
    {
        if (this.loadDownloadableImageTexture(var1.playerCloakUrl, (String)null) && !var1.getHasActivePotion() && !var1.getHideCape())
        {
            GL11.glPushMatrix();
            GL11.glTranslatef(0.0F, 0.0F, 0.125F);
            double var3 = var1.field_71091_bM + (var1.field_71094_bP - var1.field_71091_bM) * (double)var2 - (var1.prevPosX + (var1.posX - var1.prevPosX) * (double)var2);
            double var5 = var1.field_71096_bN + (var1.field_71095_bQ - var1.field_71096_bN) * (double)var2 - (var1.prevPosY + (var1.posY - var1.prevPosY) * (double)var2);
            double var7 = var1.field_71097_bO + (var1.field_71085_bR - var1.field_71097_bO) * (double)var2 - (var1.prevPosZ + (var1.posZ - var1.prevPosZ) * (double)var2);
            float var9 = var1.prevRenderYawOffset + (var1.renderYawOffset - var1.prevRenderYawOffset) * var2;
            double var10 = (double)MathHelper.sin(var9 * (float)Math.PI / 180.0F);
            double var12 = (double)(-MathHelper.cos(var9 * (float)Math.PI / 180.0F));
            float var14 = (float)var5 * 10.0F;

            if (var14 < -6.0F)
            {
                var14 = -6.0F;
            }

            if (var14 > 32.0F)
            {
                var14 = 32.0F;
            }

            float var15 = (float)(var3 * var10 + var7 * var12) * 100.0F;
            float var16 = (float)(var3 * var12 - var7 * var10) * 100.0F;

            if (var15 < 0.0F)
            {
                var15 = 0.0F;
            }

            float var17 = var1.prevCameraYaw + (var1.cameraYaw - var1.prevCameraYaw) * var2;
            var14 += MathHelper.sin((var1.prevDistanceWalkedModified + (var1.distanceWalkedModified - var1.prevDistanceWalkedModified) * var2) * 6.0F) * 32.0F * var17;

            if (var1.isSneaking())
            {
                var14 += 25.0F;
            }

            GL11.glRotatef(6.0F + var15 / 2.0F + var14, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(var16 / 2.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-var16 / 2.0F, 0.0F, 1.0F, 0.0F);
            GL11.glRotatef(180.0F, 0.0F, 1.0F, 0.0F);
            this.modelBipedMain.renderCloak(0.0625F);
            GL11.glPopMatrix();
        }
    }

    protected void renderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isRenderSpecialItemInHandModded)
        {
            RenderPlayerAPI.renderSpecialItemInHand(this, var1, var2);
        }
        else
        {
            this.localRenderSpecialItemInHand(var1, var2);
        }
    }

    public final void realRenderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        this.renderSpecialItemInHand(var1, var2);
    }

    public final void localRenderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        ItemStack var3 = var1.inventory.getCurrentItem();

        if (var3 != null)
        {
            GL11.glPushMatrix();
            this.modelBipedMain.bipedRightArm.postRender(0.0625F);
            GL11.glTranslatef(-0.0625F, 0.4375F, 0.0625F);

            if (var1.fishEntity != null)
            {
                var3 = new ItemStack(Item.stick);
            }

            EnumAction var4 = null;

            if (var1.getItemInUseCount() > 0)
            {
                var4 = var3.getItemUseAction();
            }

            this.positionSpecialItemInHand(var1, var2, var4, var3);
            int var5;
            float var7;
            float var8;

            if (var3.getItem().requiresMultipleRenderPasses())
            {
                for (var5 = 0; var5 <= GetRenderPasses(var3); ++var5)
                {
                    int var6 = var3.getItem().getColorFromItemStack(var3, var5);
                    var7 = (float)(var6 >> 16 & 255) / 255.0F;
                    var8 = (float)(var6 >> 8 & 255) / 255.0F;
                    float var9 = (float)(var6 & 255) / 255.0F;
                    GL11.glColor4f(var7, var8, var9, 1.0F);
                    this.renderManager.itemRenderer.renderItem(var1, var3, var5);
                }
            }
            else
            {
                var5 = var3.getItem().getColorFromItemStack(var3, 0);
                float var10 = (float)(var5 >> 16 & 255) / 255.0F;
                var7 = (float)(var5 >> 8 & 255) / 255.0F;
                var8 = (float)(var5 & 255) / 255.0F;
                GL11.glColor4f(var10, var7, var8, 1.0F);
                this.renderManager.itemRenderer.renderItem(var1, var3, 0);
            }

            GL11.glPopMatrix();
        }
    }

    protected void positionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        if (this.renderPlayerAPI != null && this.renderPlayerAPI.isPositionSpecialItemInHandModded)
        {
            RenderPlayerAPI.positionSpecialItemInHand(this, var1, var2, var3, var4);
        }
        else
        {
            this.localPositionSpecialItemInHand(var1, var2, var3, var4);
        }
    }

    public final void realPositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        this.positionSpecialItemInHand(var1, var2, var3, var4);
    }

    public final void localPositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        float var5;

        if (IsHandItemBlock(var4) && (IsForge3D(var4) || RenderBlocks.renderItemIn3d(Block.blocksList[var4.itemID].getRenderType())))
        {
            var5 = 0.5F;
            GL11.glTranslatef(0.0F, 0.1875F, -0.3125F);
            var5 *= 0.75F;
            GL11.glRotatef(20.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(-var5, -var5, var5);
        }
        else if (var4.itemID == Item.bow.itemID)
        {
            var5 = 0.625F;
            GL11.glTranslatef(0.0F, 0.125F, 0.3125F);
            GL11.glRotatef(-20.0F, 0.0F, 1.0F, 0.0F);
            GL11.glScalef(var5, -var5, var5);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else if (Item.itemsList[var4.itemID].isFull3D())
        {
            var5 = 0.625F;

            if (Item.itemsList[var4.itemID].shouldRotateAroundWhenRendering())
            {
                GL11.glRotatef(180.0F, 0.0F, 0.0F, 1.0F);
                GL11.glTranslatef(0.0F, -0.125F, 0.0F);
            }

            if (var1.getItemInUseCount() > 0 && var3 == EnumAction.block)
            {
                GL11.glTranslatef(0.05F, 0.0F, -0.1F);
                GL11.glRotatef(-50.0F, 0.0F, 1.0F, 0.0F);
                GL11.glRotatef(-10.0F, 1.0F, 0.0F, 0.0F);
                GL11.glRotatef(-60.0F, 0.0F, 0.0F, 1.0F);
            }

            GL11.glTranslatef(0.0F, 0.1875F, 0.0F);
            GL11.glScalef(var5, -var5, var5);
            GL11.glRotatef(-100.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(45.0F, 0.0F, 1.0F, 0.0F);
        }
        else
        {
            var5 = 0.375F;
            GL11.glTranslatef(0.25F, 0.1875F, -0.1875F);
            GL11.glScalef(var5, var5, var5);
            GL11.glRotatef(60.0F, 0.0F, 0.0F, 1.0F);
            GL11.glRotatef(-90.0F, 1.0F, 0.0F, 0.0F);
            GL11.glRotatef(20.0F, 0.0F, 0.0F, 1.0F);
        }
    }

    protected void renderEquippedItems(EntityLiving par1EntityLiving, float par2)
    {
        this.renderSpecials((EntityPlayer)par1EntityLiving, par2);
    }

    public final void realRenderEquippedItems(EntityLiving var1, float var2)
    {
        this.renderEquippedItems(var1, var2);
    }

    public final void superRenderEquippedItems(EntityLiving var1, float var2)
    {
        super.renderEquippedItems(var1, var2);
    }

    protected void func_82408_c(EntityLiving par1EntityLiving, int par2, float par3)
    {
        this.func_82439_b((EntityPlayer)par1EntityLiving, par2, par3);
    }

    public final void realFunc_82408_c(EntityLiving var1, int var2, float var3)
    {
        this.func_82408_c(var1, var2, var3);
    }

    public final void superFunc_82408_c(EntityLiving var1, int var2, float var3)
    {
        super.func_82408_c(var1, var2, var3);
    }

    /**
     * Passes the specialRender and renders it
     */
    protected void passSpecialRender(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.renderName((EntityPlayer)par1EntityLiving, par2, par4, par6);
    }

    public final void realPassSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        this.passSpecialRender(var1, var2, var4, var6);
    }

    public final void superPassSpecialRender(EntityLiving var1, double var2, double var4, double var6)
    {
        super.passSpecialRender(var1, var2, var4, var6);
    }

    protected void rotateCorpse(EntityLiving par1EntityLiving, float par2, float par3, float par4)
    {
        this.rotatePlayer((EntityPlayer)par1EntityLiving, par2, par3, par4);
    }

    public final void realRotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        this.rotateCorpse(var1, var2, var3, var4);
    }

    public final void superRotateCorpse(EntityLiving var1, float var2, float var3, float var4)
    {
        super.rotateCorpse(var1, var2, var3, var4);
    }

    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderPlayer((EntityPlayer)par1EntityLiving, par2, par4, par6, par8, par9);
    }

    public final void superDoRenderLiving(EntityLiving var1, double var2, double var4, double var6, float var8, float var9)
    {
        super.doRenderLiving(var1, var2, var4, var6, var8, var9);
    }

    /**
     * Actually renders the given argument. This is a synthetic bridge method, always casting down its argument and then
     * handing it off to a worker function which does the actual work. In all probabilty, the class Render is generic
     * (Render<T extends Entity) and this method has signature public void doRender(T entity, double d, double d1,
     * double d2, float f, float f1). But JAD is pre 1.5 so doesn't do that.
     */
    public void doRender(Entity par1Entity, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderPlayer((EntityPlayer)par1Entity, par2, par4, par6, par8, par9);
    }

    /**
     * Allows the render to do any OpenGL state modifications necessary before the model is rendered. Args:
     * entityLiving, partialTickTime
     */
    protected void preRenderCallback(EntityLiving par1EntityLiving, float par2)
    {
        this.renderPlayerScale((EntityPlayer)par1EntityLiving, par2);
    }

    public final void realPreRenderCallback(EntityLiving var1, float var2)
    {
        this.preRenderCallback(var1, var2);
    }

    public final void superPreRenderCallback(EntityLiving var1, float var2)
    {
        super.preRenderCallback(var1, var2);
    }

    /**
     * Sets a simple glTranslate on a LivingEntity.
     */
    protected void renderLivingAt(EntityLiving par1EntityLiving, double par2, double par4, double par6)
    {
        this.renderPlayerSleep((EntityPlayer)par1EntityLiving, par2, par4, par6);
    }

    public final void realRenderLivingAt(EntityLiving var1, double var2, double var4, double var6)
    {
        this.renderLivingAt(var1, var2, var4, var6);
    }

    public final void superRenderLivingAt(EntityLiving var1, double var2, double var4, double var6)
    {
        super.renderLivingAt(var1, var2, var4, var6);
    }

    /**
     * Queries whether should render the specified pass or not.
     */
    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.setArmorModel((EntityPlayer)par1EntityLiving, par2, par3);
    }

    public final int realShouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return this.shouldRenderPass(var1, var2, var3);
    }

    public final int superShouldRenderPass(EntityLiving var1, int var2, float var3)
    {
        return super.shouldRenderPass(var1, var2, var3);
    }

    private static Class TryLoadType(String var0)
    {
        ClassLoader var1 = RenderPlayer.class.getClassLoader();

        try
        {
            return var1.loadClass(var0);
        }
        catch (ClassNotFoundException var3)
        {
            return null;
        }
    }

    private static Method TryLoadMethod(Class var0, String var1, Class ... var2)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getDeclaredMethod(var1, var2);
            }
            catch (NoSuchMethodException var4)
            {
                return null;
            }
        }
    }

    private static Object TryLoadEnum(Class var0, String var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getField(var1).get((Object)null);
            }
            catch (IllegalAccessException var3)
            {
                return null;
            }
            catch (NoSuchFieldException var4)
            {
                return null;
            }
        }
    }

    private static String GetArmorTexture(ItemStack var0, String var1)
    {
        if (getArmorTexture == null)
        {
            return var1;
        }
        else
        {
            try
            {
                return (String)getArmorTexture.invoke((Object)null, new Object[] {var0, var1});
            }
            catch (Exception var3)
            {
                throw new RuntimeException(getArmorTexture.getName(), var3);
            }
        }
    }

    private static boolean IsHeadItemBlock(ItemStack var0)
    {
        return getItemRenderer != null && shouldUseRenderHelper != null ? var0 != null && var0.getItem() instanceof ItemBlock : var0.getItem().itemID < 256;
    }

    private static boolean IsForge3D(ItemStack var0)
    {
        if (getItemRenderer != null && shouldUseRenderHelper != null)
        {
            try
            {
                Object var1 = getItemRenderer.invoke((Object)null, new Object[] {var0, equipped});
                return var1 != null && ((Boolean)shouldUseRenderHelper.invoke(var1, new Object[] {equipped, var0, block3D})).booleanValue();
            }
            catch (Throwable var2)
            {
                return false;
            }
        }
        else
        {
            return false;
        }
    }

    private static int GetRenderPasses(ItemStack var0)
    {
        if (getRenderPasses == null)
        {
            return 1;
        }
        else
        {
            try
            {
                return ((Integer)getRenderPasses.invoke(var0.getItem(), new Object[] {Integer.valueOf(var0.getItemDamage())})).intValue();
            }
            catch (Throwable var2)
            {
                return 1;
            }
        }
    }

    private static boolean IsHandItemBlock(ItemStack var0)
    {
        return getItemRenderer != null && shouldUseRenderHelper != null ? var0.getItem() instanceof ItemBlock : var0.itemID < 256;
    }

    public static int addNewArmourPrefix(String var0)
    {
        ArrayList var1 = new ArrayList(Arrays.asList(armorFilenamePrefix));
        var1.add(var0);
        armorFilenamePrefix = (String[])((String[])var1.toArray(new String[0]));
        return var1.indexOf(var0);
    }

    public final String[] getArmorFilenamePrefixField()
    {
        return armorFilenamePrefix;
    }

    public final void setArmorFilenamePrefixField(String[] var1)
    {
        armorFilenamePrefix = var1;
    }

    public final ModelBase getMainModelField()
    {
        return this.mainModel;
    }

    public final void setMainModelField(ModelBase var1)
    {
        this.mainModel = var1;
    }

    public final ModelBiped getModelArmorField()
    {
        return this.modelArmor;
    }

    public final void setModelArmorField(ModelBiped var1)
    {
        this.modelArmor = var1;
    }

    public final ModelBiped getModelArmorChestplateField()
    {
        return this.modelArmorChestplate;
    }

    public final void setModelArmorChestplateField(ModelBiped var1)
    {
        this.modelArmorChestplate = var1;
    }

    public final ModelBiped getModelBipedMainField()
    {
        return this.modelBipedMain;
    }

    public final void setModelBipedMainField(ModelBiped var1)
    {
        this.modelBipedMain = var1;
    }

    public final RenderBlocks getRenderBlocksField()
    {
        return this.renderBlocks;
    }

    public final void setRenderBlocksField(RenderBlocks var1)
    {
        this.renderBlocks = var1;
    }

    public final RenderManager getRenderManagerField()
    {
        return this.renderManager;
    }

    public final void setRenderManagerField(RenderManager var1)
    {
        this.renderManager = var1;
    }

    public final ModelBase getRenderPassModelField()
    {
        return this.renderPassModel;
    }

    public final void setRenderPassModelField(ModelBase var1)
    {
        this.renderPassModel = var1;
    }

    public final float getShadowOpaqueField()
    {
        return this.shadowOpaque;
    }

    public final void setShadowOpaqueField(float var1)
    {
        this.shadowOpaque = var1;
    }

    public final float getShadowSizeField()
    {
        return this.shadowSize;
    }

    public final void setShadowSizeField(float var1)
    {
        this.shadowSize = var1;
    }

    static
    {
        Class var0 = TryLoadType("ModLoader");

        if (var0 == null)
        {
            var0 = TryLoadType("net.minecraft.src.ModLoader");
        }

        if (var0 != null)
        {
            try
            {
                Field var1 = var0.getDeclaredField("hasInit");
                var1.setAccessible(true);

                if (!((Boolean)var1.get((Object)null)).booleanValue())
                {
                    ;
                }

                Method var2 = var0.getDeclaredMethod("init", new Class[0]);
                var2.setAccessible(true);
                var2.invoke((Object)null, new Object[0]);
            }
            catch (Throwable var3)
            {
                ;
            }
        }
    }
}
