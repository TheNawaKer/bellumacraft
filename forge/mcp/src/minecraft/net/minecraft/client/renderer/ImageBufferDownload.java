package net.minecraft.client.renderer;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.DataBufferInt;
import java.awt.image.ImageObserver;

public class ImageBufferDownload implements IImageBuffer
{
    private int[] imageData;
    private int w23;
    private int h23;

    public BufferedImage parseUserSkin(BufferedImage par1BufferedImage)
    {
        if (par1BufferedImage == null)
        {
            return null;
        }
        else
        {
            this.w23 = par1BufferedImage.getWidth((ImageObserver)null);
            this.h23 = par1BufferedImage.getHeight((ImageObserver)null);
            BufferedImage var2 = new BufferedImage(this.w23, this.h23, 2);
            Graphics var3 = var2.getGraphics();
            var3.drawImage(par1BufferedImage, 0, 0, (ImageObserver)null);
            var3.dispose();
            this.imageData = ((DataBufferInt)var2.getRaster().getDataBuffer()).getData();

            if (this.w23 == 128 && this.h23 == 64)
            {
                this.setAreaOpaque(0, 0, 0, 0);
                this.setAreaTransparent(32, 0, 128, 64);
                this.setAreaOpaque(0, 16, 0, 0);
            }
            else if (this.w23 == 256 && this.h23 == 128)
            {
                this.setAreaOpaque(0, 0, 0, 0);
                this.setAreaTransparent(32, 0, 256, 128);
                this.setAreaOpaque(0, 16, 0, 0);
            }
            else if (this.w23 == 512 && this.h23 == 256)
            {
                this.setAreaOpaque(0, 0, 32, 16);
                this.setAreaTransparent(32, 0, 512, 256);
                this.setAreaOpaque(0, 16, 0, 0);
            }
            else
            {
                this.setAreaOpaque(0, 0, 32, 16);
                this.setAreaTransparent(32, 0, 64, 32);
                this.setAreaOpaque(0, 16, 64, 32);
            }

            boolean var4 = false;
            int var5;
            int var6;
            int var7;

            for (var5 = 32; var5 < 64; ++var5)
            {
                for (var6 = 0; var6 < 16; ++var6)
                {
                    var7 = this.imageData[var5 + var6 * 64];

                    if ((var7 >> 24 & 255) < 128)
                    {
                        var4 = true;
                    }
                }
            }

            if (!var4)
            {
                for (var5 = 32; var5 < 64; ++var5)
                {
                    for (var6 = 0; var6 < 16; ++var6)
                    {
                        var7 = this.imageData[var5 + var6 * 64];

                        if ((var7 >> 24 & 255) < 128)
                        {
                            boolean var8 = true;
                        }
                    }
                }
            }

            return var2;
        }
    }

    /**
     * Makes the given area of the image transparent if it was previously completely opaque (used to remove the outer
     * layer of a skin around the head if it was saved all opaque; this would be redundant so it's assumed that the skin
     * maker is just using an image editor without an alpha channel)
     */
    private void setAreaTransparent(int par1, int par2, int par3, int par4)
    {
        if (!this.hasTransparency(par1, par2, par3, par4))
        {
            for (int var5 = par1; var5 < par3; ++var5)
            {
                for (int var6 = par2; var6 < par4; ++var6)
                {
                    this.imageData[var5 + var6 * this.w23] &= 16777215;
                }
            }
        }
    }

    /**
     * Makes the given area of the image opaque
     */
    private void setAreaOpaque(int par1, int par2, int par3, int par4)
    {
        for (int var5 = par1; var5 < par3; ++var5)
        {
            for (int var6 = par2; var6 < par4; ++var6)
            {
                this.imageData[var5 + var6 * this.w23] |= -16777216;
            }
        }
    }

    /**
     * Returns true if the given area of the image contains transparent pixels
     */
    private boolean hasTransparency(int par1, int par2, int par3, int par4)
    {
        for (int var5 = par1; var5 < par3; ++var5)
        {
            for (int var6 = par2; var6 < par4; ++var6)
            {
                int var7 = this.imageData[var5 + var6 * this.w23];

                if ((var7 >> 24 & 255) < 128)
                {
                    return true;
                }
            }
        }

        return false;
    }
}
