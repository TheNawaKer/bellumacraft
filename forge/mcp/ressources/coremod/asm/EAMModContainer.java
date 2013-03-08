package mod.coremod.asm;

import java.util.Arrays;
import java.util.Random;

import com.google.common.eventbus.EventBus;
import com.google.common.eventbus.Subscribe;

import cpw.mods.fml.common.DummyModContainer;
import cpw.mods.fml.common.LoadController;
import cpw.mods.fml.common.ModMetadata;
import cpw.mods.fml.common.event.FMLInitializationEvent;
import cpw.mods.fml.common.event.FMLServerStartingEvent;

public class EAMModContainer extends DummyModContainer {
        public EAMModContainer() {
                super(new ModMetadata());
                /* ModMetadata is the same as mcmod.info */
                ModMetadata myMeta = super.getMetadata();
                myMeta.authorList = Arrays.asList(new String[] { "Legendaire45" });
                myMeta.description = "Epic Adventure Mod Core";
                myMeta.modId = "EAM_core";
                myMeta.version = "1.4.7";
                myMeta.name = myMeta.description;
                myMeta.url = "http://retrogamefr.com";
        }
        
        public boolean registerBus(EventBus bus, LoadController controller) {
        bus.register(this);
        return true;
        }
        
}