package mod.legendaire45.tools;

import java.io.File;

import net.minecraft.client.Minecraft;
import net.minecraftforge.client.event.sound.SoundLoadEvent;
import net.minecraftforge.event.ForgeSubscribe;

public class NewSound
{
    @ForgeSubscribe
    public void onSound(SoundLoadEvent event)
    {
    	Minecraft mc = Minecraft.getMinecraft();
    	File file = new File(mc.mcDataDir, "resources/sound3/mob/horse/hurt1.ogg");
    	File h1 = new File(mc.mcDataDir, "resources/sound3/mob/horse/horsegrunt1.ogg");
    	File h2 = new File(mc.mcDataDir, "resources/sound3/mob/horse/horsegrunt2.ogg");
    	File h3 = new File(mc.mcDataDir, "resources/sound3/mob/horse/horsegrunt3.ogg");
    	
    	event.manager.soundPoolSounds.addSound("mob/horse/hurt1.ogg", file);
    	event.manager.soundPoolSounds.addSound("mob/horse/horsegrunt1.ogg", h1);
    	event.manager.soundPoolSounds.addSound("mob/horse/horsegrunt2.ogg", h2);
    	event.manager.soundPoolSounds.addSound("mob/horse/horsegrunt3.ogg", h3);
    }
}
