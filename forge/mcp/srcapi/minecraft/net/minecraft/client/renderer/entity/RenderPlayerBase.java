package net.minecraft.client.renderer.entity;

import java.lang.reflect.Method;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public abstract class RenderPlayerBase
{
    protected final RenderPlayer renderPlayer;
    private final RenderPlayerAPI renderPlayerAPI;
    private Method[] methods;

    public RenderPlayerBase(RenderPlayerAPI var1)
    {
        this.renderPlayerAPI = var1;
        this.renderPlayer = var1.renderPlayer;
    }

    public void beforeLocalConstructing() {}

    public void afterLocalConstructing() {}

    public Object dynamic(String var1, Object[] var2)
    {
        return this.renderPlayerAPI.dynamicOverwritten(var1, var2, this);
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    public void beforeDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9) {}

    public void doRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        RenderPlayerBase var10 = this.renderPlayerAPI.GetOverwrittenDoRenderShadowAndFire(this);

        if (var10 == null)
        {
            this.renderPlayer.localDoRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
        else if (var10 != this)
        {
            var10.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
    }

    public void afterDoRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9) {}

    public void beforeDrawFirstPersonHand(EntityPlayer var1) {}

    public void drawFirstPersonHand(EntityPlayer var1)
    {
        RenderPlayerBase var2 = this.renderPlayerAPI.GetOverwrittenDrawFirstPersonHand(this);

        if (var2 == null)
        {
            this.renderPlayer.localDrawFirstPersonHand(var1);
        }
        else if (var2 != this)
        {
            var2.drawFirstPersonHand(var1);
        }
    }

    public void afterDrawFirstPersonHand(EntityPlayer var1) {}

    public void beforeGetColorMultiplier(EntityLiving var1, float var2, float var3) {}

    public int getColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        RenderPlayerBase var4 = this.renderPlayerAPI.GetOverwrittenGetColorMultiplier(this);
        int var5;

        if (var4 == null)
        {
            var5 = this.renderPlayer.localGetColorMultiplier(var1, var2, var3);
        }
        else if (var4 != this)
        {
            var5 = var4.getColorMultiplier(var1, var2, var3);
        }
        else
        {
            var5 = 0;
        }

        return var5;
    }

    public void afterGetColorMultiplier(EntityLiving var1, float var2, float var3) {}

    public void beforeGetDeathMaxRotation(EntityLiving var1) {}

    public float getDeathMaxRotation(EntityLiving var1)
    {
        RenderPlayerBase var2 = this.renderPlayerAPI.GetOverwrittenGetDeathMaxRotation(this);
        float var3;

        if (var2 == null)
        {
            var3 = this.renderPlayer.localGetDeathMaxRotation(var1);
        }
        else if (var2 != this)
        {
            var3 = var2.getDeathMaxRotation(var1);
        }
        else
        {
            var3 = 0.0F;
        }

        return var3;
    }

    public void afterGetDeathMaxRotation(EntityLiving var1) {}

    public void beforeGetFontRendererFromRenderManager() {}

    public FontRenderer getFontRendererFromRenderManager()
    {
        RenderPlayerBase var1 = this.renderPlayerAPI.GetOverwrittenGetFontRendererFromRenderManager(this);
        FontRenderer var2;

        if (var1 == null)
        {
            var2 = this.renderPlayer.localGetFontRendererFromRenderManager();
        }
        else if (var1 != this)
        {
            var2 = var1.getFontRendererFromRenderManager();
        }
        else
        {
            var2 = null;
        }

        return var2;
    }

    public void afterGetFontRendererFromRenderManager() {}

    public void beforeHandleRotationFloat(EntityLiving var1, float var2) {}

    public float handleRotationFloat(EntityLiving var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenHandleRotationFloat(this);
        float var4;

        if (var3 == null)
        {
            var4 = this.renderPlayer.localHandleRotationFloat(var1, var2);
        }
        else if (var3 != this)
        {
            var4 = var3.handleRotationFloat(var1, var2);
        }
        else
        {
            var4 = 0.0F;
        }

        return var4;
    }

    public void afterHandleRotationFloat(EntityLiving var1, float var2) {}

    public void beforeInheritRenderPass(EntityLiving var1, int var2, float var3) {}

    public int inheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        RenderPlayerBase var4 = this.renderPlayerAPI.GetOverwrittenInheritRenderPass(this);
        int var5;

        if (var4 == null)
        {
            var5 = this.renderPlayer.localInheritRenderPass(var1, var2, var3);
        }
        else if (var4 != this)
        {
            var5 = var4.inheritRenderPass(var1, var2, var3);
        }
        else
        {
            var5 = 0;
        }

        return var5;
    }

    public void afterInheritRenderPass(EntityLiving var1, int var2, float var3) {}

    public void beforeLoadDownloadableImageTexture(String var1, String var2) {}

    public boolean loadDownloadableImageTexture(String var1, String var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenLoadDownloadableImageTexture(this);
        boolean var4;

        if (var3 == null)
        {
            var4 = this.renderPlayer.localLoadDownloadableImageTexture(var1, var2);
        }
        else if (var3 != this)
        {
            var4 = var3.loadDownloadableImageTexture(var1, var2);
        }
        else
        {
            var4 = false;
        }

        return var4;
    }

    public void afterLoadDownloadableImageTexture(String var1, String var2) {}

    public void beforeLoadTexture(String var1) {}

    public void loadTexture(String var1)
    {
        RenderPlayerBase var2 = this.renderPlayerAPI.GetOverwrittenLoadTexture(this);

        if (var2 == null)
        {
            this.renderPlayer.localLoadTexture(var1);
        }
        else if (var2 != this)
        {
            var2.loadTexture(var1);
        }
    }

    public void afterLoadTexture(String var1) {}

    public void beforeRenderArrows(EntityLiving var1, float var2) {}

    public void renderArrows(EntityLiving var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderArrows(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderArrows(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderArrows(var1, var2);
        }
    }

    public void afterRenderArrows(EntityLiving var1, float var2) {}

    public void beforeRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9) {}

    public void renderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        RenderPlayerBase var10 = this.renderPlayerAPI.GetOverwrittenRenderLivingLabel(this);

        if (var10 == null)
        {
            this.renderPlayer.localRenderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
        else if (var10 != this)
        {
            var10.renderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
    }

    public void afterRenderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9) {}

    public void beforeRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7) {}

    public void renderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        RenderPlayerBase var8 = this.renderPlayerAPI.GetOverwrittenRenderModel(this);

        if (var8 == null)
        {
            this.renderPlayer.localRenderModel(var1, var2, var3, var4, var5, var6, var7);
        }
        else if (var8 != this)
        {
            var8.renderModel(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public void afterRenderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7) {}

    public void beforeRenderName(EntityPlayer var1, double var2, double var4, double var6) {}

    public void renderName(EntityPlayer var1, double var2, double var4, double var6)
    {
        RenderPlayerBase var8 = this.renderPlayerAPI.GetOverwrittenRenderName(this);

        if (var8 == null)
        {
            this.renderPlayer.localRenderName(var1, var2, var4, var6);
        }
        else if (var8 != this)
        {
            var8.renderName(var1, var2, var4, var6);
        }
    }

    public void afterRenderName(EntityPlayer var1, double var2, double var4, double var6) {}

    public void beforeRenderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9) {}

    public void renderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        RenderPlayerBase var10 = this.renderPlayerAPI.GetOverwrittenRenderPlayer(this);

        if (var10 == null)
        {
            this.renderPlayer.localRenderPlayer(var1, var2, var4, var6, var8, var9);
        }
        else if (var10 != this)
        {
            var10.renderPlayer(var1, var2, var4, var6, var8, var9);
        }
    }

    public void afterRenderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9) {}

    public void beforeRenderPlayerScale(EntityPlayer var1, float var2) {}

    public void renderPlayerScale(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderPlayerScale(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderPlayerScale(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderPlayerScale(var1, var2);
        }
    }

    public void afterRenderPlayerScale(EntityPlayer var1, float var2) {}

    public void beforeRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6) {}

    public void renderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        RenderPlayerBase var8 = this.renderPlayerAPI.GetOverwrittenRenderPlayerSleep(this);

        if (var8 == null)
        {
            this.renderPlayer.localRenderPlayerSleep(var1, var2, var4, var6);
        }
        else if (var8 != this)
        {
            var8.renderPlayerSleep(var1, var2, var4, var6);
        }
    }

    public void afterRenderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6) {}

    public void beforeRenderSpecials(EntityPlayer var1, float var2) {}

    public void renderSpecials(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSpecials(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderSpecials(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderSpecials(var1, var2);
        }
    }

    public void afterRenderSpecials(EntityPlayer var1, float var2) {}

    public void beforeRenderSwingProgress(EntityLiving var1, float var2) {}

    public float renderSwingProgress(EntityLiving var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSwingProgress(this);
        float var4;

        if (var3 == null)
        {
            var4 = this.renderPlayer.localRenderSwingProgress(var1, var2);
        }
        else if (var3 != this)
        {
            var4 = var3.renderSwingProgress(var1, var2);
        }
        else
        {
            var4 = 0.0F;
        }

        return var4;
    }

    public void afterRenderSwingProgress(EntityLiving var1, float var2) {}

    public void beforeRotatePlayer(EntityPlayer var1, float var2, float var3, float var4) {}

    public void rotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        RenderPlayerBase var5 = this.renderPlayerAPI.GetOverwrittenRotatePlayer(this);

        if (var5 == null)
        {
            this.renderPlayer.localRotatePlayer(var1, var2, var3, var4);
        }
        else if (var5 != this)
        {
            var5.rotatePlayer(var1, var2, var3, var4);
        }
    }

    public void afterRotatePlayer(EntityPlayer var1, float var2, float var3, float var4) {}

    public void beforeSetArmorModel(EntityPlayer var1, int var2, float var3) {}

    public int setArmorModel(EntityPlayer var1, int var2, float var3)
    {
        RenderPlayerBase var4 = this.renderPlayerAPI.GetOverwrittenSetArmorModel(this);
        int var5;

        if (var4 == null)
        {
            var5 = this.renderPlayer.localSetArmorModel(var1, var2, var3);
        }
        else if (var4 != this)
        {
            var5 = var4.setArmorModel(var1, var2, var3);
        }
        else
        {
            var5 = 0;
        }

        return var5;
    }

    public void afterSetArmorModel(EntityPlayer var1, int var2, float var3) {}

    public void beforeSetPassArmorModel(EntityPlayer var1, int var2, float var3) {}

    public void setPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        RenderPlayerBase var4 = this.renderPlayerAPI.GetOverwrittenSetPassArmorModel(this);

        if (var4 == null)
        {
            this.renderPlayer.localSetPassArmorModel(var1, var2, var3);
        }
        else if (var4 != this)
        {
            var4.setPassArmorModel(var1, var2, var3);
        }
    }

    public void afterSetPassArmorModel(EntityPlayer var1, int var2, float var3) {}

    public void beforeSetRenderManager(RenderManager var1) {}

    public void setRenderManager(RenderManager var1)
    {
        RenderPlayerBase var2 = this.renderPlayerAPI.GetOverwrittenSetRenderManager(this);

        if (var2 == null)
        {
            this.renderPlayer.localSetRenderManager(var1);
        }
        else if (var2 != this)
        {
            var2.setRenderManager(var1);
        }
    }

    public void afterSetRenderManager(RenderManager var1) {}

    public void beforeSetRenderPassModel(ModelBase var1) {}

    public void setRenderPassModel(ModelBase var1)
    {
        RenderPlayerBase var2 = this.renderPlayerAPI.GetOverwrittenSetRenderPassModel(this);

        if (var2 == null)
        {
            this.renderPlayer.localSetRenderPassModel(var1);
        }
        else if (var2 != this)
        {
            var2.setRenderPassModel(var1);
        }
    }

    public void afterSetRenderPassModel(ModelBase var1) {}

    public void beforeRenderSpecialHeadArmor(EntityPlayer var1, float var2) {}

    public void renderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSpecialHeadArmor(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderSpecialHeadArmor(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderSpecialHeadArmor(var1, var2);
        }
    }

    public void afterRenderSpecialHeadArmor(EntityPlayer var1, float var2) {}

    public void beforeRenderSpecialHeadEars(EntityPlayer var1, float var2) {}

    public void renderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSpecialHeadEars(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderSpecialHeadEars(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderSpecialHeadEars(var1, var2);
        }
    }

    public void afterRenderSpecialHeadEars(EntityPlayer var1, float var2) {}

    public void beforeRenderSpecialCloak(EntityPlayer var1, float var2) {}

    public void renderSpecialCloak(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSpecialCloak(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderSpecialCloak(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderSpecialCloak(var1, var2);
        }
    }

    public void afterRenderSpecialCloak(EntityPlayer var1, float var2) {}

    public void beforeRenderSpecialItemInHand(EntityPlayer var1, float var2) {}

    public void renderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        RenderPlayerBase var3 = this.renderPlayerAPI.GetOverwrittenRenderSpecialItemInHand(this);

        if (var3 == null)
        {
            this.renderPlayer.localRenderSpecialItemInHand(var1, var2);
        }
        else if (var3 != this)
        {
            var3.renderSpecialItemInHand(var1, var2);
        }
    }

    public void afterRenderSpecialItemInHand(EntityPlayer var1, float var2) {}

    public void beforePositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4) {}

    public void positionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        RenderPlayerBase var5 = this.renderPlayerAPI.GetOverwrittenPositionSpecialItemInHand(this);

        if (var5 == null)
        {
            this.renderPlayer.localPositionSpecialItemInHand(var1, var2, var3, var4);
        }
        else if (var5 != this)
        {
            var5.positionSpecialItemInHand(var1, var2, var3, var4);
        }
    }

    public void afterPositionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4) {}
}
