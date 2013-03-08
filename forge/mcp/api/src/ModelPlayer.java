package net.minecraft.src;

import java.util.Collections;
import java.util.Random;
import java.util.Set;

import net.minecraft.client.model.ModelBiped;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.client.model.TextureOffset;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class ModelPlayer extends ModelBiped
{
    public final ModelPlayerAPI modelPlayerAPI = ModelPlayerAPI.create(this);

    public ModelPlayer(float var1)
    {
        super(var1);
        ModelPlayerAPI.beforeLocalConstructing(this, var1);
        ModelPlayerAPI.afterLocalConstructing(this, var1);
    }

    public final ModelPlayerBase getModelPlayerBase(String var1)
    {
        return this.modelPlayerAPI != null ? this.modelPlayerAPI.getModelPlayerBase(var1) : null;
    }

    public final Set getModelPlayerBaseIds(String var1)
    {
        return this.modelPlayerAPI != null ? this.modelPlayerAPI.getModelPlayerBaseIds() : Collections.emptySet();
    }

    public Object dynamic(String var1, Object[] var2)
    {
        return this.modelPlayerAPI != null ? this.modelPlayerAPI.dynamic(var1, var2) : null;
    }

    public ModelRenderer func_85181_a(Random var1)
    {
        ModelRenderer var2;

        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isGetRandomBoxModded)
        {
            var2 = ModelPlayerAPI.getRandomBox(this, var1);
        }
        else
        {
            var2 = super.func_85181_a(var1);
        }

        return var2;
    }

    public final ModelRenderer superGetRandomBox(Random var1)
    {
        return super.func_85181_a(var1);
    }

    public final ModelRenderer localGetRandomBox(Random var1)
    {
        return super.func_85181_a(var1);
    }

    public TextureOffset getTextureOffset(String var1)
    {
        TextureOffset var2;

        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isGetTextureOffsetModded)
        {
            var2 = ModelPlayerAPI.getTextureOffset(this, var1);
        }
        else
        {
            var2 = super.getTextureOffset(var1);
        }

        return var2;
    }

    public final TextureOffset superGetTextureOffset(String var1)
    {
        return super.getTextureOffset(var1);
    }

    public final TextureOffset localGetTextureOffset(String var1)
    {
        return super.getTextureOffset(var1);
    }

    /**
     * Sets the models various rotation angles then renders the model.
     */
    public void render(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isRenderModded)
        {
            ModelPlayerAPI.render(this, var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.render(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public final void superRender(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.render(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void localRender(Entity var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        super.render(var1, var2, var3, var4, var5, var6, var7);
    }

    /**
     * Renders the cloak of the current biped (in most cases, it's a player)
     */
    public void renderCloak(float var1)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isRenderCloakModded)
        {
            ModelPlayerAPI.renderCloak(this, var1);
        }
        else
        {
            super.renderCloak(var1);
        }
    }

    public final void superRenderCloak(float var1)
    {
        super.renderCloak(var1);
    }

    public final void localRenderCloak(float var1)
    {
        super.renderCloak(var1);
    }

    /**
     * renders the ears (specifically, deadmau5's)
     */
    public void renderEars(float var1)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isRenderEarsModded)
        {
            ModelPlayerAPI.renderEars(this, var1);
        }
        else
        {
            super.renderEars(var1);
        }
    }

    public final void superRenderEars(float var1)
    {
        super.renderEars(var1);
    }

    public final void localRenderEars(float var1)
    {
        super.renderEars(var1);
    }

    /**
     * Used for easily adding entity-dependent animations. The second and third float params here are the same second
     * and third as in the setRotationAngles method.
     */
    public void setLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isSetLivingAnimationsModded)
        {
            ModelPlayerAPI.setLivingAnimations(this, var1, var2, var3, var4);
        }
        else
        {
            super.setLivingAnimations(var1, var2, var3, var4);
        }
    }

    public final void superSetLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        super.setLivingAnimations(var1, var2, var3, var4);
    }

    public final void localSetLivingAnimations(EntityLiving var1, float var2, float var3, float var4)
    {
        super.setLivingAnimations(var1, var2, var3, var4);
    }

    /**
     * Sets the model's various rotation angles. For bipeds, par1 and par2 are used for animating the movement of arms
     * and legs, where par1 represents the time(so that arms and legs swing back and forth) and par2 represents how
     * "far" arms and legs can swing at most.
     */
    public void setRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isSetRotationAnglesModded)
        {
            ModelPlayerAPI.setRotationAngles(this, var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    public final void superSetRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
    }

    public final void localSetRotationAngles(float var1, float var2, float var3, float var4, float var5, float var6, Entity var7)
    {
        super.setRotationAngles(var1, var2, var3, var4, var5, var6, var7);
    }

    protected void setTextureOffset(String var1, int var2, int var3)
    {
        if (this.modelPlayerAPI != null && this.modelPlayerAPI.isSetTextureOffsetModded)
        {
            ModelPlayerAPI.setTextureOffset(this, var1, var2, var3);
        }
        else
        {
            super.setTextureOffset(var1, var2, var3);
        }
    }

    public final void realSetTextureOffset(String var1, int var2, int var3)
    {
        this.setTextureOffset(var1, var2, var3);
    }

    public final void superSetTextureOffset(String var1, int var2, int var3)
    {
        super.setTextureOffset(var1, var2, var3);
    }

    public final void localSetTextureOffset(String var1, int var2, int var3)
    {
        super.setTextureOffset(var1, var2, var3);
    }
}
