package net.minecraft.client.model;

import java.lang.reflect.Method;
import java.util.Random;

import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public abstract class ModelPlayerBase
{
    protected final ModelPlayer modelPlayer;
    private final ModelPlayerAPI modelPlayerAPI;
    private Method[] methods;

    public ModelPlayerBase(ModelPlayerAPI var1)
    {
        this.modelPlayerAPI = var1;
        this.modelPlayer = var1.modelPlayer;
    }

    public void beforeLocalConstructing(float var1) {}

    public void afterLocalConstructing(float var1) {}

    public Object dynamic(String var1, Object[] var2)
    {
        return this.modelPlayerAPI.dynamicOverwritten(var1, var2, this);
    }

    public final int hashCode()
    {
        return super.hashCode();
    }

    public void beforeGetRandomBox(Random var1) {}

    public ModelRenderer getRandomBox(Random var1)
    {
        ModelPlayerBase var2 = this.modelPlayerAPI.GetOverwrittenGetRandomBox(this);
        ModelRenderer var3;

        if (var2 == null)
        {
            var3 = this.modelPlayer.localGetRandomBox(var1);
        }
        else if (var2 != this)
        {
            var3 = var2.getRandomBox(var1);
        }
        else
        {
            var3 = null;
        }

        return var3;
    }

    public void afterGetRandomBox(Random var1) {}

    public void beforeGetTextureOffset(String var1) {}

    public TextureOffset getTextureOffset(String var1)
    {
        ModelPlayerBase var2 = this.modelPlayerAPI.GetOverwrittenGetTextureOffset(this);
        TextureOffset var3;

        if (var2 == null)
        {
            var3 = this.modelPlayer.localGetTextureOffset(var1);
        }
        else if (var2 != this)
        {
            var3 = var2.getTextureOffset(var1);
        }
        else
        {
            var3 = null;
        }

        return var3;
    }

    public void afterGetTextureOffset(String var1) {}

    public void beforeRender(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {}

    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        ModelPlayerBase var8 = this.modelPlayerAPI.GetOverwrittenRender(this);

        if (var8 == null)
        {
            this.modelPlayer.localRender(var1, var2, var3, var4, var5, var6, var7);
        }
        else if (var8 != this)
        {
            var8.render(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public void afterRender(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7) {}

    public void beforeRenderCloak(float var1) {}

    public void renderCloak(float var1)
    {
        ModelPlayerBase var2 = this.modelPlayerAPI.GetOverwrittenRenderCloak(this);

        if (var2 == null)
        {
            this.modelPlayer.localRenderCloak(var1);
        }
        else if (var2 != this)
        {
            var2.renderCloak(var1);
        }
    }

    public void afterRenderCloak(float var1) {}

    public void beforeRenderEars(float var1) {}

    public void renderEars(float var1)
    {
        ModelPlayerBase var2 = this.modelPlayerAPI.GetOverwrittenRenderEars(this);

        if (var2 == null)
        {
            this.modelPlayer.localRenderEars(var1);
        }
        else if (var2 != this)
        {
            var2.renderEars(var1);
        }
    }

    public void afterRenderEars(float var1) {}

    public void beforeSetLivingAnimations(EntityLiving var1, float var2, float var3, float var4) {}

    public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        ModelPlayerBase var5 = this.modelPlayerAPI.GetOverwrittenSetLivingAnimations(this);

        if (var5 == null)
        {
            this.modelPlayer.localSetLivingAnimations(var1, var2, var3, var4);
        }
        else if (var5 != this)
        {
            var5.setLivingAnimations(var1, var2, var3, var4);
        }
    }

    public void afterSetLivingAnimations(EntityLiving var1, float var2, float var3, float var4) {}

    public void beforeSetRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {}

    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        ModelPlayerBase var8 = this.modelPlayerAPI.GetOverwrittenSetRotationAngles(this);

        if (var8 == null)
        {
            this.modelPlayer.localSetRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
        else if (var8 != this)
        {
            var8.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public void afterSetRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7) {}

    public void beforeSetTextureOffset(String var1, int var2, int var3) {}

    public void setTextureOffset(String var1, int var2, int var3)
    {
        ModelPlayerBase var4 = this.modelPlayerAPI.GetOverwrittenSetTextureOffset(this);

        if (var4 == null)
        {
            this.modelPlayer.localSetTextureOffset(var1, var2, var3);
        }
        else if (var4 != this)
        {
            var4.setTextureOffset(var1, var2, var3);
        }
    }

    public void afterSetTextureOffset(String var1, int var2, int var3) {}
}
