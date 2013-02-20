
        System.out.println("**************************");
    	System.out.println(par1EntityPlayer);
    	System.out.println(par1EntityPlayer.inventory.getSlot());
    	System.out.println(par1EntityPlayer.inventory.currentItem);
        if(par1EntityPlayer.inventory.getSlot() != null)
        {
        	System.out.println("non null");
	        if(par1EntityPlayer.inventory.currentItem != 0)
	        {
	        	System.out.println("item ok");
		        ItemStack var41 = par1EntityPlayer.inventory.getSlot();
		        if(ItemToolEnum.isTool(var41))
		        {
		        	System.out.println("rendering");
			        float var40 = 0.625F;
			        GL11.glPushMatrix();
			        GL11.glTranslatef(0.1875F, 0.0F, 0.1875F);
			        GL11.glScalef(var40, -var40, var40);
	                if (par1EntityPlayer.isSneaking())
	                {
		            GL11.glRotatef(-220.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X	
	                }else
	                {
	                GL11.glRotatef(-180.0F, 1.0F, 0.0F, 0.0F);//Axe de Rotation X
	                }
			        GL11.glRotatef(310.0F, 0.0F, 1.0F, 0.0F);//Axe Y
			        GL11.glRotatef(0.0F, 0.0F, 0.0F, 1.0F);//Axe Z
			        this.renderManager.itemRenderer.renderItem((EntityLiving)par1EntityPlayer, var41, 0);
			        GL11.glPopMatrix();
		        }
	        }
        }