package mod.legendaire45.entity.model;

import net.minecraft.client.model.ModelBase;
import net.minecraft.client.model.ModelRenderer;
import net.minecraft.entity.Entity;
import net.minecraft.util.MathHelper;

public class ModelCheval extends ModelBase
{
  //fields
    ModelRenderer patte1;
    ModelRenderer patte2;
    ModelRenderer patte3;
    ModelRenderer patte4;
    ModelRenderer corps;
    ModelRenderer cou;
    ModelRenderer tete;
    ModelRenderer queue;
    ModelRenderer oreille1;
    ModelRenderer oreille2;
  
  public ModelCheval()
  {
    textureWidth = 64;
    textureHeight = 64;
    
      patte1 = new ModelRenderer(this, 48, 0);
      patte1.addBox(0F, 0F, 0F, 4, 12, 4);
      patte1.setRotationPoint(1F, 12F, -10F);
      patte1.setTextureSize(64, 64);
      patte1.mirror = true;
      setRotation(patte1, 0F, 0F, 0F);
      patte2 = new ModelRenderer(this, 48, 0);
      patte2.addBox(0F, 0F, 0F, 4, 12, 4);
      patte2.setRotationPoint(-5F, 12F, -10F);
      patte2.setTextureSize(64, 64);
      patte2.mirror = true;
      setRotation(patte2, 0F, 0F, 0F);
      patte3 = new ModelRenderer(this, 19, 0);
      patte3.addBox(0F, 0F, 0F, 4, 12, 4);
      patte3.setRotationPoint(1F, 12F, 6F);
      patte3.setTextureSize(64, 64);
      patte3.mirror = true;
      setRotation(patte3, 0F, 0F, 0F);
      patte4 = new ModelRenderer(this, 19, 0);
      patte4.addBox(0F, 0F, 0F, 4, 12, 4);
      patte4.setRotationPoint(-5F, 12F, 6F);
      patte4.setTextureSize(64, 64);
      patte4.mirror = true;
      setRotation(patte4, 0F, 0F, 0F);
      corps = new ModelRenderer(this, 0, 32);
      corps.addBox(0F, 0F, 0F, 10, 10, 20);
      corps.setRotationPoint(-5F, 2F, -10F);
      corps.setTextureSize(64, 64);
      corps.mirror = true;
      setRotation(corps, 0F, 0F, 0F);
      cou = new ModelRenderer(this, 0, 17);
      cou.addBox(0F, 0F, 0F, 4, 7, 6);
      cou.setRotationPoint(-2F, -1F, -13F);
      cou.setTextureSize(64, 64);
      cou.mirror = true;
      setRotation(cou, 0.4363323F, 0F, 0F);
      tete = new ModelRenderer(this, 26, 12);
      tete.addBox(0F, 0F, 0F, 5, 5, 11);
      tete.setRotationPoint(-2.5F, -4F, -19F);
      tete.setTextureSize(64, 64);
      tete.mirror = true;
      setRotation(tete, 0.296706F, 0F, 0F);
      queue = new ModelRenderer(this, 0, 0);
      queue.addBox(0F, 0F, 0F, 3, 14, 2);
      queue.setRotationPoint(-1.5F, 2F, 7.9F);
      queue.setTextureSize(64, 64);
      queue.mirror = true;
      setRotation(queue, 0.3141593F, 0F, 0F);
      oreille1 = new ModelRenderer(this, 38, 0);
      oreille1.addBox(0F, 0F, 0F, 1, 2, 2);
      oreille1.setRotationPoint(1F, -7.5F, -12F);
      oreille1.setTextureSize(64, 64);
      oreille1.mirror = true;
      setRotation(oreille1, 0.296706F, 0F, 0F);
      oreille2 = new ModelRenderer(this, 38, 0);
      oreille2.addBox(0F, 0F, 0F, 1, 2, 2);
      oreille2.setRotationPoint(-2F, -7.5F, -12F);
      oreille2.setTextureSize(64, 64);
      oreille2.mirror = true;
      setRotation(oreille2, 0.296706F, 0F, 0F);
  }
  
  public void render(Entity entity, float f, float f1, float f2, float f3, float f4, float f5)
  {
    super.render(entity, f, f1, f2, f3, f4, f5);
    setRotationAngles(f, f1, f2, f3, f4, f5,entity);
    patte1.render(f5);
    patte2.render(f5);
    patte3.render(f5);
    patte4.render(f5);
    corps.render(f5);
    cou.render(f5);
    tete.render(f5);
    queue.render(f5);
    oreille1.render(f5);
    oreille2.render(f5);
  }
  
  private void setRotation(ModelRenderer model, float x, float y, float z)
  {
    model.rotateAngleX = x;
    model.rotateAngleY = y;
    model.rotateAngleZ = z;
  }
  
  public void setRotationAngles(float f, float f1, float f2, float f3, float f4, float f5,Entity entity)
  {
	  this.patte2.rotateAngleX = MathHelper.cos(f * 0.6662F) * 1.4F * f1 * 0.5F;
      this.patte1.rotateAngleX = MathHelper.cos(f * 0.6662F + (float)Math.PI) * 1.4F * f1 * 0.5F;
  }

}
