package mod.legendaire45.client;

import java.util.EnumSet;

import mod.legendaire45.mod_retrogame;
import net.minecraft.client.Minecraft;
import net.minecraft.client.settings.KeyBinding;
import net.minecraft.item.Item;

import org.lwjgl.input.Keyboard;

import cpw.mods.fml.client.FMLClientHandler;
import cpw.mods.fml.client.registry.KeyBindingRegistry.KeyHandler;
import cpw.mods.fml.common.TickType;

public class KeyHandlerBow extends KeyHandler {
        
		private Minecraft mc = FMLClientHandler.instance().getClient();
        static KeyBinding bow = new KeyBinding("BOW", Keyboard.KEY_C);

        public KeyHandlerBow() {
                //the first value is an array of KeyBindings, the second is whether or not the call 
                //keyDown should repeat as long as the key is down
                super(new KeyBinding[]{bow}, new boolean[]{false});
        }

        @Override
        public String getLabel() {
                return "bow key";
        }

        @Override
        public void keyDown(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd, boolean isRepeat) {
        	if (tickEnd)
        	{
	        	if(this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem] != null)
	        	{
	                if(this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID == Item.bow.itemID)
	                {
	                	this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID = mod_retrogame.firebow.itemID;
	                }
	                else if(this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID == mod_retrogame.firebow.itemID)
	                {
	                	this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID = mod_retrogame.teleportbow.itemID;
	                }
	                else if(this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID == mod_retrogame.teleportbow.itemID)
	                {
	                	this.mc.thePlayer.inventory.mainInventory[this.mc.thePlayer.inventory.currentItem].itemID = Item.bow.itemID;
	                }
	        	}
        	}
        }

        @Override
        public void keyUp(EnumSet<TickType> types, KeyBinding kb, boolean tickEnd) {

                //do whatever
        }

        @Override
        public EnumSet<TickType> ticks() {
                return EnumSet.of(TickType.CLIENT);
                //I am unsure if any different TickTypes have any different effects.
        }
}