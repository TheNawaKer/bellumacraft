package mod.legendaire45.blocks;

import java.util.Random;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.block.BlockCrops;
import net.minecraft.block.material.Material;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.world.World;
import net.minecraftforge.common.ForgeDirection;
import net.minecraftforge.common.IPlantable;

public class BlockCropBeer extends BlockCrops {

    public BlockCropBeer (int id) {
        super(id, 2);
        setBlockBounds(0.0F, 0.0F, 0.0F, 1.0F, 1.0F, 1.0F);
        setTickRandomly(true);
    }

    public AxisAlignedBB getCollisionBoundingBoxFromPool (World world, int x,
            int y, int z) {
        return null;
    }

    public int getRenderType () {
        return 6;
    }

    public boolean isOpaqueCube () {
        return false;
    }

    public int getBlockTextureFromSideAndMetadata (int side, int metadata) {
        return 2 + metadata;
    }

    public void updateTick (World world, int x, int y, int z, Random random) {
        if (world.getBlockMetadata(x, y, z) == 1) {
            return;
        }

        if (random.nextInt(isFertile(world, x, y - 1, z) ? 12 : 25) != 0) {
            return;
        }

        world.setBlockMetadataWithNotify(x, y, z, 1);
    }

    @Override
    public void onNeighborBlockChange (World world, int x, int y, int z,
            int neighborId) {
        if (!canBlockStay(world, x, y, z)) {
            dropBlockAsItem(world, x, y, z, world.getBlockMetadata(x, y, z), 0);
            world.setBlockWithNotify(x, y, z, 0);
        }
    }

    @Override
    public boolean canBlockStay (World world, int x, int y, int z) {
        Block soil = blocksList[world.getBlockId(x, y - 1, z)];
        return (world.getFullBlockLightValue(x, y, z) >= 8 || world
                .canBlockSeeTheSky(x, y, z))
                && (soil != null && soil.canSustainPlant(world, x, y - 1, z,
                        ForgeDirection.UP, (IPlantable) mod_retrogame.seedBeer));
    }

    public int idDropped (int metadata, Random random, int par2) {
        switch (metadata) {
        case 0:
            return mod_retrogame.seedBeer.itemID;
        case 1:
            return mod_retrogame.wheatBeer.itemID;
        default:
            // Error case!
            return -1; // air
        }
    }

    public int idPicked (World world, int x, int y, int z) {
        return mod_retrogame.seedBeer.itemID;
    }
}