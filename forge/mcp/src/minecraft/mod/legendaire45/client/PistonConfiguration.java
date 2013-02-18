package mod.legendaire45.client;

import mod.legendaire45.mod_retrogame;

public class PistonConfiguration {
	
	
	public static int allowed(int par0)
	{
        if (par0 == mod_retrogame.beer.blockID)
        {
            return 1;
        }
        if (par0 == mod_retrogame.blockTrampoline.blockID)
        {
            return 1;
        }
        
		return 2;
	}

}
