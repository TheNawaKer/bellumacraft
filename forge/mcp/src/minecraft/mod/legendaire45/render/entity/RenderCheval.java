package mod.legendaire45.render.entity;

import mod.legendaire45.entity.mobs.EntityCheval;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderLiving;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;

public class RenderCheval extends RenderLiving

{

	public RenderCheval(ModelBase modelbase, float f)

	{

		super(modelbase, f);

		}

	

	public void renderCow(EntityCheval entitycheval, double d, double d1, double d2,

			float f, float f1)

	{

		super.doRenderLiving(entitycheval, d, d1, d2, f, f1);

		}

	

	public void doRenderLiving(EntityLiving entityliving, double d, double d1, double d2,

			float f, float f1)

	{

		renderCow((EntityCheval)entityliving, d, d1, d2, f, f1);

		}

	

	public void doRender(Entity entity, double d, double d1, double d2,

			float f, float f1)

	{

		renderCow((EntityCheval)entity, d, d1, d2, f, f1);

		}

	}

