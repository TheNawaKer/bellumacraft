package mod.legendaire45.world;

import java.util.Random;

import mod.legendaire45.mod_retrogame;
import net.minecraft.block.Block;
import net.minecraft.world.World;
import net.minecraft.world.chunk.IChunkProvider;
import net.minecraft.world.gen.feature.WorldGenMinable;
import cpw.mods.fml.common.IWorldGenerator;

public class WorldGenOre implements IWorldGenerator {

	@Override
	public void generate(Random random, int chunkX, int chunkZ, World world,IChunkProvider chunkGenerator, IChunkProvider chunkProvider){
        
		switch(world.provider.dimensionId){
            case 1:
                generateNether(world, random, chunkX * 16, chunkZ * 16);
            case 0:
                generateSurface(world, random, chunkX * 16, chunkZ * 16);
            case -1:
                generateEnd(world, random, chunkX * 16, chunkZ * 16);
		}
		
	}
	
	private void generateSurface(World world, Random random, int chunkX, int chunkZ)
	{
		for(int i = 0; i < 4; i++)
			 
        {
            int randPosX = chunkX + random.nextInt(16);
 
            int randPosY = random.nextInt(20);
 
            int randPosZ = chunkZ + random.nextInt(16);
 
            (new WorldGenMinable(mod_retrogame.rubyOre.blockID, 6)).generate(world, random, randPosX, randPosY, randPosZ);
 
        }
		
		for(int i = 0; i < 4; i++)
			 
        {
            int randPosX = chunkX + random.nextInt(16);
 
            int randPosY = random.nextInt(20);
 
            int randPosZ = chunkZ + random.nextInt(16);
 
            (new WorldGenMinable(mod_retrogame.saphirOre.blockID, 6)).generate(world, random, randPosX, randPosY, randPosZ);
 
        }
	}
	
	private void generateNether(World world, Random random, int chunkX, int chunkZ){
		
	}
	
	private void generateEnd(World world, Random random, int chunkX, int chunkZ){
		
	}
	

}
