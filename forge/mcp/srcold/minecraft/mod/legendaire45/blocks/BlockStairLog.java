package mod.legendaire45.blocks;

import net.minecraft.block.Block;
import net.minecraft.block.BlockStairs;
import net.minecraft.creativetab.CreativeTabs;


public class BlockStairLog extends BlockStairs
{
        /** The block that is used as model for the stair. */
        private  Block modelBlock;
        public BlockStairLog(int par1,Block modelBlockx,int par2)
        {
                super(par1, modelBlockx, par2);
                blockIndexInTexture = par2;
                this.modelBlock = modelBlockx;
          //  this.setLightOpacity(255);
                this.setCreativeTab(CreativeTabs.tabBlock);
        }
        
        public static int getOrientation(int var0)
        {
            return var0 & 7;
        }
        
        public int getMobilityFlag()
        {
            return 0;
        }
        
        public int getBlockTextureFromSideAndMetadata(int var1, int var2)
        {
            int var3 = getOrientation(var2);

            if (var3 == 0)
            {
                switch (var1)
                {
                    case 0:
                        return this.blockIndexInTexture;

                    case 1:
                        return 11;

                    case 2:
                    case 3:
                    default:
                        return 10;

                    case 4:
                        return 12;
                }
            }
            else if (var3 == 1)
            {
                switch (var1)
                {
                    case 0:
                        return this.blockIndexInTexture;

                    case 1:
                        return 11;

                    case 5:
                        return 12;

                    default:
                        return 10;
                }
            }
            else if (var3 == 2)
            {
                switch (var1)
                {
                    case 0:
                        return this.blockIndexInTexture;

                    case 1:
                        return 11;

                    case 2:
                        return 12;

                    default:
                        return 10;
                }
            }
            else if (var3 == 3)
            {
                switch (var1)
                {
                    case 0:
                        return this.blockIndexInTexture;

                    case 1:
                        return 11;

                    case 2:
                    default:
                        return 10;

                    case 3:
                        return 12;
                }
            }
            
            if (var3 == 4)
            {
                switch (var1)
                {
                    case 0:
                        return 11;

                    case 1:
                        return 11;

                    case 2:
                    case 3:
                    default:
                        return 10;

                    case 4:
                        return 13;
                }
            }
            else if (var3 == 5)
            {
                switch (var1)
                {
                    case 0:
                        return 11;

                    case 1:
                        return 11;

                    case 5:
                        return 13;

                    default:
                        return 10;
                }
            }
            else if (var3 == 6)
            {
                switch (var1)
                {
                    case 0:
                        return 11;

                    case 1:
                        return 11;

                    case 2:
                        return 13;

                    default:
                        return 10;
                }
            }
            else if (var3 == 11)
            {
                switch (var1)
                {
                    case 0:
                        return 11;

                    case 1:
                        return 11;

                    case 2:
                    default:
                        return 10;

                    case 3:
                        return 13;
                }
            }
            else
            {
                return 10;
            }
            
        }

        /**
         * Returns the block texture based on the side being looked at.  Args: side
         */
        public int getBlockTextureFromSide(int var1)
        {
            switch (var1)
            {
                case 0:
                    return this.blockIndexInTexture;

                case 1:
                    return 11;

                case 2:
                default:
                    return 10;

                case 3:
                    return 12;
            }
        }
  
}