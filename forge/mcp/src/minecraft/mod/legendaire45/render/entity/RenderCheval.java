package mod.legendaire45.render.entity;

import mod.legendaire45.common.CommonProxy;
import mod.legendaire45.entity.mobs.EntityCheval;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.EntityLiving;



public class RenderCheval extends RenderLiving
{

	private static String chevalselle = CommonProxy.chevalselle;
	
    public RenderCheval(ModelBase par1ModelBase, ModelBase par2ModelBase, float par3)
    {
        super(par1ModelBase, par3);
        this.setRenderPassModel(par2ModelBase);
    }



    protected int renderSaddledHorse(EntityCheval par1EntityHorse, int par2, float par3)
    {
        this.loadTexture(chevalselle);
        return par2 == 0 && par1EntityHorse.getSaddled() ? 1 : -1;
    }



    public void renderLivingHorse(EntityCheval par1EntityHorse, double par2, double par4, double par6, float par8, float par9)

    {
        super.doRenderLiving(par1EntityHorse, par2, par4, par6, par8, par9);
    }



    /**

     * Queries whether should render the specified pass or not.

     */

    protected int shouldRenderPass(EntityLiving par1EntityLiving, int par2, float par3)
    {
        return this.renderSaddledHorse((EntityCheval)par1EntityLiving, par2, par3);
    }



    public void doRenderLiving(EntityLiving par1EntityLiving, double par2, double par4, double par6, float par8, float par9)
    {
        this.renderLivingHorse((EntityCheval)par1EntityLiving, par2, par4, par6, par8, par9);
    }
}