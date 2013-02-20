package net.minecraft.entity.player;

import java.io.ByteArrayOutputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;
import java.util.Set;

import net.minecraft.block.Block;
import net.minecraft.block.material.Material;
import net.minecraft.crash.CrashReportCategory;
import net.minecraft.entity.DataWatcher;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.EnumCreatureAttribute;
import net.minecraft.entity.IMerchant;
import net.minecraft.entity.ai.EntityAITasks;
import net.minecraft.entity.ai.EntityJumpHelper;
import net.minecraft.entity.ai.EntityLookHelper;
import net.minecraft.entity.ai.EntityMoveHelper;
import net.minecraft.entity.ai.EntitySenses;
import net.minecraft.entity.effect.EntityLightningBolt;
import net.minecraft.entity.item.EntityItem;
import net.minecraft.entity.projectile.EntityArrow;
import net.minecraft.inventory.Container;
import net.minecraft.inventory.ContainerBeacon;
import net.minecraft.inventory.ContainerBrewingStand;
import net.minecraft.inventory.ContainerChest;
import net.minecraft.inventory.ContainerDispenser;
import net.minecraft.inventory.ContainerEnchantment;
import net.minecraft.inventory.ContainerFurnace;
import net.minecraft.inventory.ContainerMerchant;
import net.minecraft.inventory.ContainerRepair;
import net.minecraft.inventory.ContainerWorkbench;
import net.minecraft.inventory.ICrafting;
import net.minecraft.inventory.IInventory;
import net.minecraft.inventory.InventoryEnderChest;
import net.minecraft.inventory.InventoryMerchant;
import net.minecraft.inventory.SlotCrafting;
import net.minecraft.item.EnumAction;
import net.minecraft.item.Item;
import net.minecraft.item.ItemInWorldManager;
import net.minecraft.item.ItemMapBase;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.nbt.NBTTagList;
import net.minecraft.network.NetServerHandler;
import net.minecraft.network.packet.Packet;
import net.minecraft.network.packet.Packet100OpenWindow;
import net.minecraft.network.packet.Packet101CloseWindow;
import net.minecraft.network.packet.Packet103SetSlot;
import net.minecraft.network.packet.Packet104WindowItems;
import net.minecraft.network.packet.Packet105UpdateProgressbar;
import net.minecraft.network.packet.Packet17Sleep;
import net.minecraft.network.packet.Packet18Animation;
import net.minecraft.network.packet.Packet200Statistic;
import net.minecraft.network.packet.Packet202PlayerAbilities;
import net.minecraft.network.packet.Packet204ClientInfo;
import net.minecraft.network.packet.Packet250CustomPayload;
import net.minecraft.network.packet.Packet29DestroyEntity;
import net.minecraft.network.packet.Packet38EntityStatus;
import net.minecraft.network.packet.Packet39AttachEntity;
import net.minecraft.network.packet.Packet3Chat;
import net.minecraft.network.packet.Packet41EntityEffect;
import net.minecraft.network.packet.Packet42RemoveEntityEffect;
import net.minecraft.network.packet.Packet43Experience;
import net.minecraft.network.packet.Packet56MapChunks;
import net.minecraft.network.packet.Packet70GameEvent;
import net.minecraft.network.packet.Packet8UpdateHealth;
import net.minecraft.pathfinding.PathNavigate;
import net.minecraft.potion.Potion;
import net.minecraft.potion.PotionEffect;
import net.minecraft.server.MinecraftServer;
import net.minecraft.server.ServerPlayerAPI;
import net.minecraft.server.ServerPlayerBase;
import net.minecraft.stats.AchievementList;
import net.minecraft.stats.StatBase;
import net.minecraft.tileentity.TileEntity;
import net.minecraft.tileentity.TileEntityBeacon;
import net.minecraft.tileentity.TileEntityBrewingStand;
import net.minecraft.tileentity.TileEntityDispenser;
import net.minecraft.tileentity.TileEntityFurnace;
import net.minecraft.util.AxisAlignedBB;
import net.minecraft.util.ChunkCoordinates;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EntityDamageSource;
import net.minecraft.util.FoodStats;
import net.minecraft.util.MathHelper;
import net.minecraft.util.MovingObjectPosition;
import net.minecraft.util.StringTranslate;
import net.minecraft.util.Vec3;
import net.minecraft.village.MerchantRecipeList;
import net.minecraft.world.ChunkCoordIntPair;
import net.minecraft.world.EnumGameType;
import net.minecraft.world.Explosion;
import net.minecraft.world.World;
import net.minecraft.world.WorldProvider;
import net.minecraft.world.WorldServer;
import net.minecraft.world.chunk.Chunk;

public class EntityPlayerMP extends EntityPlayer implements ICrafting
{
    private StringTranslate translator;

    /**
     * The NetServerHandler assigned to this player by the ServerConfigurationManager.
     */
    public NetServerHandler playerNetServerHandler;

    /** Reference to the MinecraftServer object. */
    public MinecraftServer mcServer;

    /** The ItemInWorldManager belonging to this player */
    public ItemInWorldManager theItemInWorldManager;

    /** player X position as seen by PlayerManager */
    public double managedPosX;

    /** player Z position as seen by PlayerManager */
    public double managedPosZ;

    /** LinkedList that holds the loaded chunks. */
    public final List loadedChunks;

    /** entities added to this list will  be packet29'd to the player */
    public final List destroyedItemsNetCache;

    /** set to getHealth */
    private int lastHealth;

    /** set to foodStats.GetFoodLevel */
    private int lastFoodLevel;

    /** set to foodStats.getSaturationLevel() == 0.0F each tick */
    private boolean wasHungry;

    /** Amount of experience the client was last set to */
    private int lastExperience;

    /** de-increments onUpdate, attackEntityFrom is ignored if this >0 */
    private int initialInvulnerability;

    /** must be between 3>x>15 (strictly between) */
    private int renderDistance;
    private int chatVisibility;
    private boolean chatColours;

    /**
     * The currently in use window ID. Incremented every time a window is opened.
     */
    public int currentWindowId;

    /**
     * poor mans concurency flag, lets hope the jvm doesn't re-order the setting of this flag wrt the inventory change
     * on the next line
     */
    public boolean playerInventoryBeingManipulated;
    public int ping;

    /**
     * Set when a player beats the ender dragon, used to respawn the player at the spawn point while retaining inventory
     * and XP
     */
    public boolean playerConqueredTheEnd;
    private static final Class forgeHooks = TryLoadType("net.minecraftforge.common.ForgeHooks");
    private static final Method onLivingDeath = TryLoadMethod(forgeHooks, "onLivingDeath", new Class[] {EntityLiving.class, DamageSource.class});
    private static final Constructor playerDropsEventConstructor;
    private static final Constructor chunkWatchEventWatchConstructor;
    private static final Field captureDropsField;
    private static final Field capturedDropsField;
    private static final Field eventBusField;
    private static final Method post;
    private static final Method getRandomizedSpawnPoint;
    private static final int chunkOffset;
    public final ServerPlayerAPI serverPlayerAPI = ServerPlayerAPI.create(this);

    public EntityPlayerMP(MinecraftServer par1MinecraftServer, World par2World, String par3Str, ItemInWorldManager par4ItemInWorldManager)
    {
        super(par2World);
        ServerPlayerAPI.beforeLocalConstructing(this, par1MinecraftServer, par2World, par3Str, par4ItemInWorldManager);
        this.translator = new StringTranslate("en_US");
        this.loadedChunks = new LinkedList();
        this.destroyedItemsNetCache = new LinkedList();
        this.lastHealth = -99999999;
        this.lastFoodLevel = -99999999;
        this.wasHungry = true;
        this.lastExperience = -99999999;
        this.initialInvulnerability = 60;
        this.renderDistance = 0;
        this.chatVisibility = 0;
        this.chatColours = true;
        this.currentWindowId = 0;
        this.playerConqueredTheEnd = false;
        par4ItemInWorldManager.thisPlayerMP = this;
        this.theItemInWorldManager = par4ItemInWorldManager;
        this.renderDistance = par1MinecraftServer.getConfigurationManager().getViewDistance();
        ChunkCoordinates var5 = GetRandomizedSpawnPoint(par2World);
        int var6 = var5.posX;
        int var7 = var5.posZ;
        int var8 = var5.posY;

        if (forgeHooks == null && !par2World.provider.hasNoSky && par2World.getWorldInfo().getGameType() != EnumGameType.ADVENTURE)
        {
            int var9 = Math.max(5, par1MinecraftServer.getSpawnProtectionSize() - 6);
            var6 += this.rand.nextInt(var9 * 2) - var9;
            var7 += this.rand.nextInt(var9 * 2) - var9;
            var8 = par2World.getTopSolidOrLiquidBlock(var6, var7);
        }

        this.setLocationAndAngles((double)var6 + 0.5D, (double)var8, (double)var7 + 0.5D, 0.0F, 0.0F);
        this.mcServer = par1MinecraftServer;
        this.stepHeight = 0.0F;
        this.username = par3Str;
        this.yOffset = 0.0F;
        ServerPlayerAPI.afterLocalConstructing(this, par1MinecraftServer, par2World, par3Str, par4ItemInWorldManager);
    }

    public final ServerPlayerBase getServerPlayerBase(String var1)
    {
        return this.serverPlayerAPI != null ? this.serverPlayerAPI.getServerPlayerBase(var1) : null;
    }

    public final Set getServerPlayerBaseIds(String var1)
    {
        return this.serverPlayerAPI != null ? this.serverPlayerAPI.getServerPlayerBaseIds() : Collections.emptySet();
    }

    /**
     * increases exhaustion level by supplied amount
     */
    public void addExhaustion(float var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAddExhaustionModded)
        {
            ServerPlayerAPI.addExhaustion(this, var1);
        }
        else
        {
            super.addExhaustion(var1);
        }
    }

    public final void superAddExhaustion(float var1)
    {
        super.addExhaustion(var1);
    }

    public final void localAddExhaustion(float var1)
    {
        super.addExhaustion(var1);
    }

    /**
     * This method increases the player's current amount of experience.
     */
    public void addExperience(int var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAddExperienceModded)
        {
            ServerPlayerAPI.addExperience(this, var1);
        }
        else
        {
            super.addExperience(var1);
        }
    }

    public final void superAddExperience(int var1)
    {
        super.addExperience(var1);
    }

    public final void localAddExperience(int var1)
    {
        super.addExperience(var1);
    }

    /**
     * Add experience levels to this player.
     */
    public void addExperienceLevel(int par1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAddExperienceLevelModded)
        {
            ServerPlayerAPI.addExperienceLevel(this, par1);
        }
        else
        {
            this.localAddExperienceLevel(par1);
        }
    }

    public final void superAddExperienceLevel(int var1)
    {
        super.addExperienceLevel(var1);
    }

    public final void localAddExperienceLevel(int var1)
    {
        super.addExperienceLevel(var1);
        this.lastExperience = -1;
    }

    /**
     * Adds a value to a movement statistic field - like run, walk, swin or climb.
     */
    public void addMovementStat(double var1, double var3, double var5)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAddMovementStatModded)
        {
            ServerPlayerAPI.addMovementStat(this, var1, var3, var5);
        }
        else
        {
            super.addMovementStat(var1, var3, var5);
        }
    }

    public final void superAddMovementStat(double var1, double var3, double var5)
    {
        super.addMovementStat(var1, var3, var5);
    }

    public final void localAddMovementStat(double var1, double var3, double var5)
    {
        super.addMovementStat(var1, var3, var5);
    }

    /**
     * Called when the entity is attacked.
     */
    public boolean attackEntityFrom(DamageSource par1DamageSource, int par2)
    {
        boolean var3;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAttackEntityFromModded)
        {
            var3 = ServerPlayerAPI.attackEntityFrom(this, par1DamageSource, par2);
        }
        else
        {
            var3 = this.localAttackEntityFrom(par1DamageSource, par2);
        }

        return var3;
    }

    public final boolean superAttackEntityFrom(DamageSource var1, int var2)
    {
        return super.attackEntityFrom(var1, var2);
    }

    public final boolean localAttackEntityFrom(DamageSource var1, int var2)
    {
        if (this.isEntityInvulnerable())
        {
            return false;
        }
        else
        {
            boolean var3 = this.mcServer.isDedicatedServer() && this.mcServer.isPVPEnabled() && "fall".equals(var1.damageType);

            if (!var3 && this.initialInvulnerability > 0 && var1 != DamageSource.outOfWorld)
            {
                return false;
            }
            else
            {
                if (!this.mcServer.isPVPEnabled() && var1 instanceof EntityDamageSource)
                {
                    Entity var4 = var1.getEntity();

                    if (var4 instanceof EntityPlayer)
                    {
                        return false;
                    }

                    if (var4 instanceof EntityArrow)
                    {
                        EntityArrow var5 = (EntityArrow)var4;

                        if (var5.shootingEntity instanceof EntityPlayer)
                        {
                            return false;
                        }
                    }
                }

                return super.attackEntityFrom(var1, var2);
            }
        }
    }

    /**
     * Attacks for the player the targeted entity with the currently equipped item.  The equipped item has hitEntity
     * called on it. Args: targetEntity
     */
    public void attackTargetEntityWithCurrentItem(Entity var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isAttackTargetEntityWithCurrentItemModded)
        {
            ServerPlayerAPI.attackTargetEntityWithCurrentItem(this, var1);
        }
        else
        {
            super.attackTargetEntityWithCurrentItem(var1);
        }
    }

    public final void superAttackTargetEntityWithCurrentItem(Entity var1)
    {
        super.attackTargetEntityWithCurrentItem(var1);
    }

    public final void localAttackTargetEntityWithCurrentItem(Entity var1)
    {
        super.attackTargetEntityWithCurrentItem(var1);
    }

    /**
     * Checks if the player has the ability to harvest a block (checks current inventory item for a tool if necessary)
     */
    public boolean canHarvestBlock(Block var1)
    {
        boolean var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isCanHarvestBlockModded)
        {
            var2 = ServerPlayerAPI.canHarvestBlock(this, var1);
        }
        else
        {
            var2 = super.canHarvestBlock(var1);
        }

        return var2;
    }

    public final boolean superCanHarvestBlock(Block var1)
    {
        return super.canHarvestBlock(var1);
    }

    public final boolean localCanHarvestBlock(Block var1)
    {
        return super.canHarvestBlock(var1);
    }

    public boolean canPlayerEdit(int var1, int var2, int var3, int var4, ItemStack var5)
    {
        boolean var6;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isCanPlayerEditModded)
        {
            var6 = ServerPlayerAPI.canPlayerEdit(this, var1, var2, var3, var4, var5);
        }
        else
        {
            var6 = super.canPlayerEdit(var1, var2, var3, var4, var5);
        }

        return var6;
    }

    public final boolean superCanPlayerEdit(int var1, int var2, int var3, int var4, ItemStack var5)
    {
        return super.canPlayerEdit(var1, var2, var3, var4, var5);
    }

    public final boolean localCanPlayerEdit(int var1, int var2, int var3, int var4, ItemStack var5)
    {
        return super.canPlayerEdit(var1, var2, var3, var4, var5);
    }

    /**
     * returns if this entity triggers Block.onEntityWalking on the blocks they walk on. used for spiders and wolves to
     * prevent them from trampling crops
     */
    protected boolean canTriggerWalking()
    {
        boolean var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isCanTriggerWalkingModded)
        {
            var1 = ServerPlayerAPI.canTriggerWalking(this);
        }
        else
        {
            var1 = super.canTriggerWalking();
        }

        return var1;
    }

    public final boolean realCanTriggerWalking()
    {
        return this.canTriggerWalking();
    }

    public final boolean superCanTriggerWalking()
    {
        return super.canTriggerWalking();
    }

    public final boolean localCanTriggerWalking()
    {
        return super.canTriggerWalking();
    }

    /**
     * Deals damage to the entity. If its a EntityPlayer then will take damage from the armor first and then health
     * second with the reduced value. Args: damageAmount
     */
    protected void damageEntity(DamageSource var1, int var2)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDamageEntityModded)
        {
            ServerPlayerAPI.damageEntity(this, var1, var2);
        }
        else
        {
            super.damageEntity(var1, var2);
        }
    }

    public final void realDamageEntity(DamageSource var1, int var2)
    {
        this.damageEntity(var1, var2);
    }

    public final void superDamageEntity(DamageSource var1, int var2)
    {
        super.damageEntity(var1, var2);
    }

    public final void localDamageEntity(DamageSource var1, int var2)
    {
        super.damageEntity(var1, var2);
    }

    /**
     * Displays the GUI for interacting with a chest inventory. Args: chestInventory
     */
    public void displayGUIChest(IInventory par1IInventory)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDisplayGUIChestModded)
        {
            ServerPlayerAPI.displayGUIChest(this, par1IInventory);
        }
        else
        {
            this.localDisplayGUIChest(par1IInventory);
        }
    }

    public final void superDisplayGUIChest(IInventory var1)
    {
        super.displayGUIChest(var1);
    }

    public final void localDisplayGUIChest(IInventory var1)
    {
        if (this.openContainer != this.inventoryContainer)
        {
            this.closeScreen();
        }

        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 0, var1.getInvName(), var1.getSizeInventory()));
        this.openContainer = new ContainerChest(this.inventory, var1);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    /**
     * Displays the dipsenser GUI for the passed in dispenser entity. Args: TileEntityDispenser
     */
    public void displayGUIDispenser(TileEntityDispenser par1TileEntityDispenser)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDisplayGUIDispenserModded)
        {
            ServerPlayerAPI.displayGUIDispenser(this, par1TileEntityDispenser);
        }
        else
        {
            this.localDisplayGUIDispenser(par1TileEntityDispenser);
        }
    }

    public final void superDisplayGUIDispenser(TileEntityDispenser var1)
    {
        super.displayGUIDispenser(var1);
    }

    public final void localDisplayGUIDispenser(TileEntityDispenser var1)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 3, var1.getInvName(), var1.getSizeInventory()));
        this.openContainer = new ContainerDispenser(this.inventory, var1);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    /**
     * Displays the furnace GUI for the passed in furnace entity. Args: tileEntityFurnace
     */
    public void displayGUIFurnace(TileEntityFurnace par1TileEntityFurnace)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDisplayGUIFurnaceModded)
        {
            ServerPlayerAPI.displayGUIFurnace(this, par1TileEntityFurnace);
        }
        else
        {
            this.localDisplayGUIFurnace(par1TileEntityFurnace);
        }
    }

    public final void superDisplayGUIFurnace(TileEntityFurnace var1)
    {
        super.displayGUIFurnace(var1);
    }

    public final void localDisplayGUIFurnace(TileEntityFurnace var1)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 2, var1.getInvName(), var1.getSizeInventory()));
        this.openContainer = new ContainerFurnace(this.inventory, var1);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    /**
     * Displays the crafting GUI for a workbench.
     */
    public void displayGUIWorkbench(int par1, int par2, int par3)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDisplayGUIWorkbenchModded)
        {
            ServerPlayerAPI.displayGUIWorkbench(this, par1, par2, par3);
        }
        else
        {
            this.localDisplayGUIWorkbench(par1, par2, par3);
        }
    }

    public final void superDisplayGUIWorkbench(int var1, int var2, int var3)
    {
        super.displayGUIWorkbench(var1, var2, var3);
    }

    public final void localDisplayGUIWorkbench(int var1, int var2, int var3)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 1, "Crafting", 9));
        this.openContainer = new ContainerWorkbench(this.inventory, this.worldObj, var1, var2, var3);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    /**
     * Called when player presses the drop item key
     */
    public EntityItem dropOneItem(boolean var1)
    {
        EntityItem var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDropOneItemModded)
        {
            var2 = ServerPlayerAPI.dropOneItem(this, var1);
        }
        else
        {
            var2 = super.dropOneItem(var1);
        }

        return var2;
    }

    public final EntityItem superDropOneItem(boolean var1)
    {
        return super.dropOneItem(var1);
    }

    public final EntityItem localDropOneItem(boolean var1)
    {
        return super.dropOneItem(var1);
    }

    /**
     * Args: itemstack - called when player drops an item stack that's not in his inventory (like items still placed in
     * a workbench while the workbench'es GUI gets closed)
     */
    public EntityItem dropPlayerItem(ItemStack var1)
    {
        EntityItem var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isDropPlayerItemModded)
        {
            var2 = ServerPlayerAPI.dropPlayerItem(this, var1);
        }
        else
        {
            var2 = super.dropPlayerItem(var1);
        }

        return var2;
    }

    public final EntityItem superDropPlayerItem(ItemStack var1)
    {
        return super.dropPlayerItem(var1);
    }

    public final EntityItem localDropPlayerItem(ItemStack var1)
    {
        return super.dropPlayerItem(var1);
    }

    /**
     * Called when the mob is falling. Calculates and applies fall damage.
     */
    protected void fall(float var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isFallModded)
        {
            ServerPlayerAPI.fall(this, var1);
        }
        else
        {
            super.fall(var1);
        }
    }

    public final void realFall(float var1)
    {
        this.fall(var1);
    }

    public final void superFall(float var1)
    {
        super.fall(var1);
    }

    public final void localFall(float var1)
    {
        super.fall(var1);
    }

    /**
     * Returns how strong the player is against the specified block at this moment
     */
    public float getCurrentPlayerStrVsBlock(Block var1)
    {
        float var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetCurrentPlayerStrVsBlockModded)
        {
            var2 = ServerPlayerAPI.getCurrentPlayerStrVsBlock(this, var1);
        }
        else
        {
            var2 = super.getCurrentPlayerStrVsBlock(var1);
        }

        return var2;
    }

    public final float superGetCurrentPlayerStrVsBlock(Block var1)
    {
        return super.getCurrentPlayerStrVsBlock(var1);
    }

    public final float localGetCurrentPlayerStrVsBlock(Block var1)
    {
        return super.getCurrentPlayerStrVsBlock(var1);
    }

    /**
     * Gets the squared distance to the position. Args: x, y, z
     */
    public double getDistanceSq(double var1, double var3, double var5)
    {
        double var7;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetDistanceSqModded)
        {
            var7 = ServerPlayerAPI.getDistanceSq(this, var1, var3, var5);
        }
        else
        {
            var7 = super.getDistanceSq(var1, var3, var5);
        }

        return var7;
    }

    public final double superGetDistanceSq(double var1, double var3, double var5)
    {
        return super.getDistanceSq(var1, var3, var5);
    }

    public final double localGetDistanceSq(double var1, double var3, double var5)
    {
        return super.getDistanceSq(var1, var3, var5);
    }

    /**
     * Gets how bright this entity is.
     */
    public float getBrightness(float var1)
    {
        float var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetBrightnessModded)
        {
            var2 = ServerPlayerAPI.getBrightness(this, var1);
        }
        else
        {
            var2 = super.getBrightness(var1);
        }

        return var2;
    }

    public final float superGetBrightness(float var1)
    {
        return super.getBrightness(var1);
    }

    public final float localGetBrightness(float var1)
    {
        return super.getBrightness(var1);
    }

    public float getEyeHeight()
    {
        float var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetEyeHeightModded)
        {
            var1 = ServerPlayerAPI.getEyeHeight(this);
        }
        else
        {
            var1 = this.localGetEyeHeight();
        }

        return var1;
    }

    public final float superGetEyeHeight()
    {
        return super.getEyeHeight();
    }

    public final float localGetEyeHeight()
    {
        return 1.62F;
    }

    public int getMaxHealth()
    {
        int var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetMaxHealthModded)
        {
            var1 = ServerPlayerAPI.getMaxHealth(this);
        }
        else
        {
            var1 = super.getMaxHealth();
        }

        return var1;
    }

    public final int superGetMaxHealth()
    {
        return super.getMaxHealth();
    }

    public final int localGetMaxHealth()
    {
        return super.getMaxHealth();
    }

    /**
     * This method returns a value to be applied directly to entity speed, this factor is less than 1 when a slowdown
     * potion effect is applied, more than 1 when a haste potion effect is applied and 2 for fleeing entities.
     */
    public float getSpeedModifier()
    {
        float var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isGetSpeedModifierModded)
        {
            var1 = ServerPlayerAPI.getSpeedModifier(this);
        }
        else
        {
            var1 = super.getSpeedModifier();
        }

        return var1;
    }

    public final float superGetSpeedModifier()
    {
        return super.getSpeedModifier();
    }

    public final float localGetSpeedModifier()
    {
        return super.getSpeedModifier();
    }

    /**
     * Heal living entity (param: amount of half-hearts)
     */
    public void heal(int var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isHealModded)
        {
            ServerPlayerAPI.heal(this, var1);
        }
        else
        {
            super.heal(var1);
        }
    }

    public final void superHeal(int var1)
    {
        super.heal(var1);
    }

    public final void localHeal(int var1)
    {
        super.heal(var1);
    }

    /**
     * Called when a player interacts with a mob. e.g. gets milk from a cow, gets into the saddle on a pig.
     */
    public boolean interact(EntityPlayer var1)
    {
        boolean var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isInteractModded)
        {
            var2 = ServerPlayerAPI.interact(this, var1);
        }
        else
        {
            var2 = super.interact(var1);
        }

        return var2;
    }

    public final boolean superInteract(EntityPlayer var1)
    {
        return super.interact(var1);
    }

    public final boolean localInteract(EntityPlayer var1)
    {
        return super.interact(var1);
    }

    /**
     * Checks if this entity is inside of an opaque block
     */
    public boolean isEntityInsideOpaqueBlock()
    {
        boolean var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isIsEntityInsideOpaqueBlockModded)
        {
            var1 = ServerPlayerAPI.isEntityInsideOpaqueBlock(this);
        }
        else
        {
            var1 = super.isEntityInsideOpaqueBlock();
        }

        return var1;
    }

    public final boolean superIsEntityInsideOpaqueBlock()
    {
        return super.isEntityInsideOpaqueBlock();
    }

    public final boolean localIsEntityInsideOpaqueBlock()
    {
        return super.isEntityInsideOpaqueBlock();
    }

    /**
     * Checks if this entity is inside water (if inWater field is true as a result of handleWaterMovement() returning
     * true)
     */
    public boolean isInWater()
    {
        boolean var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isIsInWaterModded)
        {
            var1 = ServerPlayerAPI.isInWater(this);
        }
        else
        {
            var1 = super.isInWater();
        }

        return var1;
    }

    public final boolean superIsInWater()
    {
        return super.isInWater();
    }

    public final boolean localIsInWater()
    {
        return super.isInWater();
    }

    /**
     * Checks if the current block the entity is within of the specified material type
     */
    public boolean isInsideOfMaterial(Material var1)
    {
        boolean var2;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isIsInsideOfMaterialModded)
        {
            var2 = ServerPlayerAPI.isInsideOfMaterial(this, var1);
        }
        else
        {
            var2 = super.isInsideOfMaterial(var1);
        }

        return var2;
    }

    public final boolean superIsInsideOfMaterial(Material var1)
    {
        return super.isInsideOfMaterial(var1);
    }

    public final boolean localIsInsideOfMaterial(Material var1)
    {
        return super.isInsideOfMaterial(var1);
    }

    /**
     * returns true if this entity is by a ladder, false otherwise
     */
    public boolean isOnLadder()
    {
        boolean var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isIsOnLadderModded)
        {
            var1 = ServerPlayerAPI.isOnLadder(this);
        }
        else
        {
            var1 = super.isOnLadder();
        }

        return var1;
    }

    public final boolean superIsOnLadder()
    {
        return super.isOnLadder();
    }

    public final boolean localIsOnLadder()
    {
        return super.isOnLadder();
    }

    /**
     * Returns whether player is sleeping or not
     */
    public boolean isPlayerSleeping()
    {
        boolean var1;

        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isIsPlayerSleepingModded)
        {
            var1 = ServerPlayerAPI.isPlayerSleeping(this);
        }
        else
        {
            var1 = super.isPlayerSleeping();
        }

        return var1;
    }

    public final boolean superIsPlayerSleeping()
    {
        return super.isPlayerSleeping();
    }

    public final boolean localIsPlayerSleeping()
    {
        return super.isPlayerSleeping();
    }

    /**
     * Causes this entity to do an upwards motion (jumping).
     */
    protected void jump()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isJumpModded)
        {
            ServerPlayerAPI.jump(this);
        }
        else
        {
            super.jump();
        }
    }

    public final void realJump()
    {
        this.jump();
    }

    public final void superJump()
    {
        super.jump();
    }

    public final void localJump()
    {
        super.jump();
    }

    /**
     * knocks back this entity
     */
    public void knockBack(Entity var1, int var2, double var3, double var5)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isKnockBackModded)
        {
            ServerPlayerAPI.knockBack(this, var1, var2, var3, var5);
        }
        else
        {
            super.knockBack(var1, var2, var3, var5);
        }
    }

    public final void superKnockBack(Entity var1, int var2, double var3, double var5)
    {
        super.knockBack(var1, var2, var3, var5);
    }

    public final void localKnockBack(Entity var1, int var2, double var3, double var5)
    {
        super.knockBack(var1, var2, var3, var5);
    }

    /**
     * Tries to moves the entity by the passed in displacement. Args: x, y, z
     */
    public void moveEntity(double var1, double var3, double var5)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isMoveEntityModded)
        {
            ServerPlayerAPI.moveEntity(this, var1, var3, var5);
        }
        else
        {
            super.moveEntity(var1, var3, var5);
        }
    }

    public final void superMoveEntity(double var1, double var3, double var5)
    {
        super.moveEntity(var1, var3, var5);
    }

    public final void localMoveEntity(double var1, double var3, double var5)
    {
        super.moveEntity(var1, var3, var5);
    }

    /**
     * Moves the entity based on the specified heading.  Args: strafe, forward
     */
    public void moveEntityWithHeading(float var1, float var2)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isMoveEntityWithHeadingModded)
        {
            ServerPlayerAPI.moveEntityWithHeading(this, var1, var2);
        }
        else
        {
            super.moveEntityWithHeading(var1, var2);
        }
    }

    public final void superMoveEntityWithHeading(float var1, float var2)
    {
        super.moveEntityWithHeading(var1, var2);
    }

    public final void localMoveEntityWithHeading(float var1, float var2)
    {
        super.moveEntityWithHeading(var1, var2);
    }

    /**
     * Used in both water and by flying objects
     */
    public void moveFlying(float var1, float var2, float var3)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isMoveFlyingModded)
        {
            ServerPlayerAPI.moveFlying(this, var1, var2, var3);
        }
        else
        {
            super.moveFlying(var1, var2, var3);
        }
    }

    public final void superMoveFlying(float var1, float var2, float var3)
    {
        super.moveFlying(var1, var2, var3);
    }

    public final void localMoveFlying(float var1, float var2, float var3)
    {
        super.moveFlying(var1, var2, var3);
    }

    /**
     * Called when the mob's health reaches 0.
     */
    public void onDeath(DamageSource par1DamageSource)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnDeathModded)
        {
            ServerPlayerAPI.onDeath(this, par1DamageSource);
        }
        else
        {
            this.localOnDeath(par1DamageSource);
        }
    }

    public final void superOnDeath(DamageSource var1)
    {
        super.onDeath(var1);
    }

    public final void localOnDeath(DamageSource var1)
    {
        if (!OnLivingDeath(this, var1))
        {
            this.mcServer.getConfigurationManager().func_92027_k(var1.getDeathMessage(this));

            if (!this.worldObj.getGameRules().getGameRuleBooleanValue("keepInventory"))
            {
                HandleBeforeDrops(this);
                this.inventory.dropAllItems();
                HandleAfterDrops(this, var1);
            }
        }
    }

    /**
     * Called frequently so the entity can update its state every tick as required. For example, zombies and skeletons
     * use this to react to sunlight and start to burn.
     */
    public void onLivingUpdate()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnLivingUpdateModded)
        {
            ServerPlayerAPI.onLivingUpdate(this);
        }
        else
        {
            super.onLivingUpdate();
        }
    }

    public final void superOnLivingUpdate()
    {
        super.onLivingUpdate();
    }

    public final void localOnLivingUpdate()
    {
        super.onLivingUpdate();
    }

    /**
     * This method gets called when the entity kills another one.
     */
    public void onKillEntity(EntityLiving var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnKillEntityModded)
        {
            ServerPlayerAPI.onKillEntity(this, var1);
        }
        else
        {
            super.onKillEntity(var1);
        }
    }

    public final void superOnKillEntity(EntityLiving var1)
    {
        super.onKillEntity(var1);
    }

    public final void localOnKillEntity(EntityLiving var1)
    {
        super.onKillEntity(var1);
    }

    /**
     * Called when a lightning bolt hits the entity.
     */
    public void onStruckByLightning(EntityLightningBolt var1)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnStruckByLightningModded)
        {
            ServerPlayerAPI.onStruckByLightning(this, var1);
        }
        else
        {
            super.onStruckByLightning(var1);
        }
    }

    public final void superOnStruckByLightning(EntityLightningBolt var1)
    {
        super.onStruckByLightning(var1);
    }

    public final void localOnStruckByLightning(EntityLightningBolt var1)
    {
        super.onStruckByLightning(var1);
    }

    /**
     * Called to update the entity's position/logic.
     */
    public void onUpdate()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnUpdateModded)
        {
            ServerPlayerAPI.onUpdate(this);
        }
        else
        {
            this.localOnUpdate();
        }
    }

    public final void superOnUpdate()
    {
        super.onUpdate();
    }

    public final void localOnUpdate()
    {
        this.theItemInWorldManager.updateBlockRemoving();
        --this.initialInvulnerability;
        this.openContainer.detectAndSendChanges();

        while (!this.destroyedItemsNetCache.isEmpty())
        {
            int var1 = Math.min(this.destroyedItemsNetCache.size(), 127);
            int[] var2 = new int[var1];
            Iterator var3 = this.destroyedItemsNetCache.iterator();
            int var4 = 0;

            while (var3.hasNext() && var4 < var1)
            {
                var2[var4++] = ((Integer)var3.next()).intValue();
                var3.remove();
            }

            this.playerNetServerHandler.sendPacketToPlayer(new Packet29DestroyEntity(var2));
        }

        if (!this.loadedChunks.isEmpty())
        {
            ArrayList var6 = new ArrayList();
            Iterator var7 = this.loadedChunks.iterator();
            ArrayList var8 = new ArrayList();

            while (var7.hasNext() && var6.size() < 5)
            {
                ChunkCoordIntPair var9 = (ChunkCoordIntPair)var7.next();
                var7.remove();

                if (var9 != null && this.worldObj.blockExists(var9.chunkXPos << 4, 0, var9.chunkZPos << 4))
                {
                    var6.add(this.worldObj.getChunkFromChunkCoords(var9.chunkXPos, var9.chunkZPos));
                    var8.addAll(((WorldServer)this.worldObj).getAllTileEntityInBox(var9.chunkXPos * 16, 0, var9.chunkZPos * 16, var9.chunkXPos * 16 + chunkOffset, 256, var9.chunkZPos * 16 + chunkOffset));
                }
            }

            if (!var6.isEmpty())
            {
                this.playerNetServerHandler.sendPacketToPlayer(new Packet56MapChunks(var6));
                Iterator var11 = var8.iterator();

                while (var11.hasNext())
                {
                    TileEntity var5 = (TileEntity)var11.next();
                    this.sendTileEntityToPlayer(var5);
                }

                var11 = var6.iterator();

                while (var11.hasNext())
                {
                    Chunk var10 = (Chunk)var11.next();
                    this.getServerForPlayer().getEntityTracker().func_85172_a(this, var10);
                    AddChunkWatchEvent(this, var10);
                }
            }
        }
    }

    public void onUpdateEntity()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isOnUpdateEntityModded)
        {
            ServerPlayerAPI.onUpdateEntity(this);
        }
        else
        {
            this.localOnUpdateEntity();
        }
    }

    public final void localOnUpdateEntity()
    {
        super.onUpdate();

        for (int var1 = 0; var1 < this.inventory.getSizeInventory(); ++var1)
        {
            ItemStack var2 = this.inventory.getStackInSlot(var1);

            if (var2 != null && Item.itemsList[var2.itemID].isMap() && this.playerNetServerHandler.packetSize() <= 5)
            {
                Packet var3 = ((ItemMapBase)Item.itemsList[var2.itemID]).createMapDataPacket(var2, this.worldObj, this);

                if (var3 != null)
                {
                    this.playerNetServerHandler.sendPacketToPlayer(var3);
                }
            }
        }

        if (this.getHealth() != this.lastHealth || this.lastFoodLevel != this.foodStats.getFoodLevel() || this.foodStats.getSaturationLevel() == 0.0F != this.wasHungry)
        {
            this.playerNetServerHandler.sendPacketToPlayer(new Packet8UpdateHealth(this.getHealth(), this.foodStats.getFoodLevel(), this.foodStats.getSaturationLevel()));
            this.lastHealth = this.getHealth();
            this.lastFoodLevel = this.foodStats.getFoodLevel();
            this.wasHungry = this.foodStats.getSaturationLevel() == 0.0F;
        }

        if (this.experienceTotal != this.lastExperience)
        {
            this.lastExperience = this.experienceTotal;
            this.playerNetServerHandler.sendPacketToPlayer(new Packet43Experience(this.experience, this.experienceTotal, this.experienceLevel));
        }
    }

    /**
     * (abstract) Protected helper method to read subclass entity data from NBT.
     */
    public void readEntityFromNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isReadEntityFromNBTModded)
        {
            ServerPlayerAPI.readEntityFromNBT(this, par1NBTTagCompound);
        }
        else
        {
            this.localReadEntityFromNBT(par1NBTTagCompound);
        }
    }

    public final void superReadEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);
    }

    public final void localReadEntityFromNBT(NBTTagCompound var1)
    {
        super.readEntityFromNBT(var1);

        if (var1.hasKey("playerGameType"))
        {
            this.theItemInWorldManager.setGameType(EnumGameType.getByID(var1.getInteger("playerGameType")));
        }
    }

    /**
     * Will get destroyed next tick.
     */
    public void setDead()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isSetDeadModded)
        {
            ServerPlayerAPI.setDead(this);
        }
        else
        {
            super.setDead();
        }
    }

    public final void superSetDead()
    {
        super.setDead();
    }

    public final void localSetDead()
    {
        super.setDead();
    }

    /**
     * Sets the x,y,z of the entity from the given parameters. Also seems to set up a bounding box.
     */
    public void setPosition(double var1, double var3, double var5)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isSetPositionModded)
        {
            ServerPlayerAPI.setPosition(this, var1, var3, var5);
        }
        else
        {
            super.setPosition(var1, var3, var5);
        }
    }

    public final void superSetPosition(double var1, double var3, double var5)
    {
        super.setPosition(var1, var3, var5);
    }

    public final void localSetPosition(double var1, double var3, double var5)
    {
        super.setPosition(var1, var3, var5);
    }

    /**
     * Swings the item the player is holding.
     */
    public void swingItem()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isSwingItemModded)
        {
            ServerPlayerAPI.swingItem(this);
        }
        else
        {
            super.swingItem();
        }
    }

    public final void superSwingItem()
    {
        super.swingItem();
    }

    public final void localSwingItem()
    {
        super.swingItem();
    }

    protected void updateEntityActionState()
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isUpdateEntityActionStateModded)
        {
            ServerPlayerAPI.updateEntityActionState(this);
        }
        else
        {
            super.updateEntityActionState();
        }
    }

    public final void realUpdateEntityActionState()
    {
        this.updateEntityActionState();
    }

    public final void superUpdateEntityActionState()
    {
        super.updateEntityActionState();
    }

    public final void localUpdateEntityActionState()
    {
        super.updateEntityActionState();
    }

    /**
     * (abstract) Protected helper method to write subclass entity data to NBT.
     */
    public void writeEntityToNBT(NBTTagCompound par1NBTTagCompound)
    {
        if (this.serverPlayerAPI != null && this.serverPlayerAPI.isWriteEntityToNBTModded)
        {
            ServerPlayerAPI.writeEntityToNBT(this, par1NBTTagCompound);
        }
        else
        {
            this.localWriteEntityToNBT(par1NBTTagCompound);
        }
    }

    public final void superWriteEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
    }

    public final void localWriteEntityToNBT(NBTTagCompound var1)
    {
        super.writeEntityToNBT(var1);
        var1.setInteger("playerGameType", this.theItemInWorldManager.getGameType().getID());
    }

    /**
     * Add a chat message to the player
     */
    public void addChatMessage(String par1Str)
    {
        StringTranslate var10000 = this.translator;
        StringTranslate var2 = StringTranslate.getInstance();
        String var3 = var2.translateKey(par1Str);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(var3));
    }

    public final void superAddChatMessage(String var1)
    {
        super.addChatMessage(var1);
    }

    public final boolean superAddEntityID(NBTTagCompound var1)
    {
        return super.addEntityID(var1);
    }

    public final void superAddPotionEffect(PotionEffect var1)
    {
        super.addPotionEffect(var1);
    }

    public final void superAddScore(int var1)
    {
        super.addScore(var1);
    }

    public void addSelfToInternalCraftingInventory()
    {
        this.openContainer.addCraftingToCrafters(this);
    }

    /**
     * Adds a value to a statistic field.
     */
    public void addStat(StatBase par1StatBase, int par2)
    {
        if (par1StatBase != null)
        {
            if (!par1StatBase.isIndependent)
            {
                while (par2 > 100)
                {
                    this.playerNetServerHandler.sendPacketToPlayer(new Packet200Statistic(par1StatBase.statId, 100));
                    par2 -= 100;
                }

                this.playerNetServerHandler.sendPacketToPlayer(new Packet200Statistic(par1StatBase.statId, par2));
            }
        }
    }

    public final void superAddStat(StatBase var1, int var2)
    {
        super.addStat(var1, var2);
    }

    public final void superAddToPlayerScore(Entity var1, int var2)
    {
        super.addToPlayerScore(var1, var2);
    }

    public final void superAddVelocity(double var1, double var3, double var5)
    {
        super.addVelocity(var1, var3, var5);
    }

    public final void realAlertWolves(EntityLiving var1, boolean var2)
    {
        this.alertWolves(var1, var2);
    }

    public final void superAlertWolves(EntityLiving var1, boolean var2)
    {
        super.alertWolves(var1, var2);
    }

    public final int realApplyArmorCalculations(DamageSource var1, int var2)
    {
        return this.applyArmorCalculations(var1, var2);
    }

    public final int superApplyArmorCalculations(DamageSource var1, int var2)
    {
        return super.applyArmorCalculations(var1, var2);
    }

    public final void superApplyEntityCollision(Entity var1)
    {
        super.applyEntityCollision(var1);
    }

    public final int realApplyPotionDamageCalculations(DamageSource var1, int var2)
    {
        return this.applyPotionDamageCalculations(var1, var2);
    }

    public final int superApplyPotionDamageCalculations(DamageSource var1, int var2)
    {
        return super.applyPotionDamageCalculations(var1, var2);
    }

    public final boolean superAttackEntityAsMob(Entity var1)
    {
        return super.attackEntityAsMob(var1);
    }

    public final boolean superCanAttackClass(Class var1)
    {
        return super.canAttackClass(var1);
    }

    public final boolean superCanAttackWithItem()
    {
        return super.canAttackWithItem();
    }

    public final boolean superCanBeCollidedWith()
    {
        return super.canBeCollidedWith();
    }

    public final boolean superCanBePushed()
    {
        return super.canBePushed();
    }

    public final boolean superCanBeSteered()
    {
        return super.canBeSteered();
    }

    public final boolean superCanBreatheUnderwater()
    {
        return super.canBreatheUnderwater();
    }

    /**
     * Returns true if the command sender is allowed to use the given command.
     */
    public boolean canCommandSenderUseCommand(int par1, String par2Str)
    {
        return "seed".equals(par2Str) && !this.mcServer.isDedicatedServer() ? true : (!"tell".equals(par2Str) && !"help".equals(par2Str) && !"me".equals(par2Str) ? this.mcServer.getConfigurationManager().areCommandsAllowed(this.username) : true);
    }

    public final boolean superCanCurrentToolHarvestBlock(int var1, int var2, int var3)
    {
        return super.canCurrentToolHarvestBlock(var1, var2, var3);
    }

    public final boolean realCanDespawn()
    {
        return this.canDespawn();
    }

    public final boolean superCanDespawn()
    {
        return super.canDespawn();
    }

    public final boolean superCanEat(boolean var1)
    {
        return super.canEat(var1);
    }

    public final boolean superCanEntityBeSeen(Entity var1)
    {
        return super.canEntityBeSeen(var1);
    }

    public final boolean superCanRenderOnFire()
    {
        return super.canRenderOnFire();
    }

    public final void superClearActivePotions()
    {
        super.clearActivePotions();
    }

    public final void superClearItemInUse()
    {
        super.clearItemInUse();
    }

    /**
     * Copies the values from the given player into this player if boolean par2 is true. Always clones Ender Chest
     * Inventory.
     */
    public void clonePlayer(EntityPlayer par1EntityPlayer, boolean par2)
    {
        super.clonePlayer(par1EntityPlayer, par2);
        this.lastExperience = -1;
        this.lastHealth = -1;
        this.lastFoodLevel = -1;
        this.destroyedItemsNetCache.addAll(((EntityPlayerMP)par1EntityPlayer).destroyedItemsNetCache);
    }

    public final void superClonePlayer(EntityPlayer var1, boolean var2)
    {
        super.clonePlayer(var1, var2);
    }

    public void closeInventory()
    {
        this.openContainer.onCraftGuiClosed(this);
        this.openContainer = this.inventoryContainer;
    }

    /**
     * sets current screen to null (used on escape buttons of GUIs)
     */
    public void closeScreen()
    {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet101CloseWindow(this.openContainer.windowId));
        this.closeInventory();
    }

    public final void superCloseScreen()
    {
        super.closeScreen();
    }

    public final void realCollideWithEntity(Entity var1)
    {
        this.collideWithEntity(var1);
    }

    public final void superCollideWithEntity(Entity var1)
    {
        super.collideWithEntity(var1);
    }

    public final void superCopyDataFrom(Entity var1, boolean var2)
    {
        super.copyDataFrom(var1, var2);
    }

    public final void realDamageArmor(int var1)
    {
        this.damageArmor(var1);
    }

    public final void superDamageArmor(int var1)
    {
        super.damageArmor(var1);
    }

    public final void realDealFireDamage(int var1)
    {
        this.dealFireDamage(var1);
    }

    public final void superDealFireDamage(int var1)
    {
        super.dealFireDamage(var1);
    }

    public final int realDecreaseAirSupply(int var1)
    {
        return this.decreaseAirSupply(var1);
    }

    public final int superDecreaseAirSupply(int var1)
    {
        return super.decreaseAirSupply(var1);
    }

    public final void realDespawnEntity()
    {
        this.despawnEntity();
    }

    public final void superDespawnEntity()
    {
        super.despawnEntity();
    }

    public final void superDestroyCurrentEquippedItem()
    {
        super.destroyCurrentEquippedItem();
    }

    public final void superDetachHome()
    {
        super.detachHome();
    }

    /**
     * Displays the GUI for interacting with an anvil.
     */
    public void displayGUIAnvil(int par1, int par2, int par3)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 8, "Repairing", 9));
        this.openContainer = new ContainerRepair(this.inventory, this.worldObj, par1, par2, par3, this);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    public final void superDisplayGUIAnvil(int var1, int var2, int var3)
    {
        super.displayGUIAnvil(var1, var2, var3);
    }

    /**
     * Displays the GUI for interacting with a beacon.
     */
    public void displayGUIBeacon(TileEntityBeacon par1TileEntityBeacon)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 7, par1TileEntityBeacon.getInvName(), par1TileEntityBeacon.getSizeInventory()));
        this.openContainer = new ContainerBeacon(this.inventory, par1TileEntityBeacon);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    public final void superDisplayGUIBeacon(TileEntityBeacon var1)
    {
        super.displayGUIBeacon(var1);
    }

    public final void superDisplayGUIBook(ItemStack var1)
    {
        super.displayGUIBook(var1);
    }

    /**
     * Displays the GUI for interacting with a brewing stand.
     */
    public void displayGUIBrewingStand(TileEntityBrewingStand par1TileEntityBrewingStand)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 5, par1TileEntityBrewingStand.getInvName(), par1TileEntityBrewingStand.getSizeInventory()));
        this.openContainer = new ContainerBrewingStand(this.inventory, par1TileEntityBrewingStand);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    public final void superDisplayGUIBrewingStand(TileEntityBrewingStand var1)
    {
        super.displayGUIBrewingStand(var1);
    }

    public final void superDisplayGUIEditSign(TileEntity var1)
    {
        super.displayGUIEditSign(var1);
    }

    public void displayGUIEnchantment(int par1, int par2, int par3)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 4, "Enchanting", 9));
        this.openContainer = new ContainerEnchantment(this.inventory, this.worldObj, par1, par2, par3);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
    }

    public final void superDisplayGUIEnchantment(int var1, int var2, int var3)
    {
        super.displayGUIEnchantment(var1, var2, var3);
    }

    public void displayGUIMerchant(IMerchant par1IMerchant)
    {
        this.incrementWindowID();
        this.closeInventory();
        this.openContainer = new ContainerMerchant(this.inventory, par1IMerchant, this.worldObj);
        this.openContainer.windowId = this.currentWindowId;
        this.openContainer.addCraftingToCrafters(this);
        InventoryMerchant var2 = ((ContainerMerchant)this.openContainer).getMerchantInventory();
        this.playerNetServerHandler.sendPacketToPlayer(new Packet100OpenWindow(this.currentWindowId, 6, var2.getInvName(), var2.getSizeInventory()));
        MerchantRecipeList var3 = par1IMerchant.getRecipes(this);

        if (var3 != null)
        {
            try
            {
                ByteArrayOutputStream var4 = new ByteArrayOutputStream();
                DataOutputStream var5 = new DataOutputStream(var4);
                var5.writeInt(this.currentWindowId);
                var3.writeRecipiesToStream(var5);
                this.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("MC|TrList", var4.toByteArray()));
            }
            catch (IOException var6)
            {
                var6.printStackTrace();
            }
        }
    }

    public final void superDisplayGUIMerchant(IMerchant var1)
    {
        super.displayGUIMerchant(var1);
    }

    public final void realDoBlockCollisions()
    {
        this.doBlockCollisions();
    }

    public final void superDoBlockCollisions()
    {
        super.doBlockCollisions();
    }

    public final boolean superDoesEntityNotTriggerPressurePlate()
    {
        return super.doesEntityNotTriggerPressurePlate();
    }

    public final void realDropEquipment(boolean var1, int var2)
    {
        this.dropEquipment(var1, var2);
    }

    public final void superDropEquipment(boolean var1, int var2)
    {
        super.dropEquipment(var1, var2);
    }

    public final void realDropFewItems(boolean var1, int var2)
    {
        this.dropFewItems(var1, var2);
    }

    public final void superDropFewItems(boolean var1, int var2)
    {
        super.dropFewItems(var1, var2);
    }

    public final EntityItem superDropItem(int var1, int var2)
    {
        return super.dropItem(var1, var2);
    }

    public final EntityItem superDropItemWithOffset(int var1, int var2, float var3)
    {
        return super.dropItemWithOffset(var1, var2, var3);
    }

    public final EntityItem superDropPlayerItemWithRandomChoice(ItemStack var1, boolean var2)
    {
        return super.dropPlayerItemWithRandomChoice(var1, var2);
    }

    public final void realDropRareDrop(int var1)
    {
        this.dropRareDrop(var1);
    }

    public final void superDropRareDrop(int var1)
    {
        super.dropRareDrop(var1);
    }

    public final void superEatGrassBonus()
    {
        super.eatGrassBonus();
    }

    public final EntityItem superEntityDropItem(ItemStack var1, float var2)
    {
        return super.entityDropItem(var1, var2);
    }

    public final void realEntityInit()
    {
        this.entityInit();
    }

    public final void superEntityInit()
    {
        super.entityInit();
    }

    public final boolean superEquals(Object var1)
    {
        return super.equals(var1);
    }

    public final void superExtinguish()
    {
        super.extinguish();
    }

    public final void superFaceEntity(Entity var1, float var2, float var3)
    {
        super.faceEntity(var1, var2, var3);
    }

    public final boolean superFunc_71066_bF()
    {
        return super.func_71066_bF();
    }

    public String func_71114_r()
    {
        String var1 = this.playerNetServerHandler.netManager.getSocketAddress().toString();
        var1 = var1.substring(var1.indexOf("/") + 1);
        var1 = var1.substring(0, var1.indexOf(":"));
        return var1;
    }

    public final int superFunc_82143_as()
    {
        return super.func_82143_as();
    }

    public final float superFunc_82146_a(Explosion var1, Block var2, int var3, int var4, int var5)
    {
        return super.func_82146_a(var1, var2, var3, var4, var5);
    }

    public final int superFunc_82148_at()
    {
        return super.func_82148_at();
    }

    public final void superFunc_82149_j(Entity var1)
    {
        super.func_82149_j(var1);
    }

    public final void realFunc_82162_bC()
    {
        this.func_82162_bC();
    }

    public final void superFunc_82162_bC()
    {
        super.func_82162_bC();
    }

    public final void realFunc_82164_bB()
    {
        this.func_82164_bB();
    }

    public final void superFunc_82164_bB()
    {
        super.func_82164_bB();
    }

    public final float superFunc_82243_bO()
    {
        return super.func_82243_bO();
    }

    public final void superFunc_85029_a(CrashReportCategory var1)
    {
        super.func_85029_a(var1);
    }

    public final boolean superFunc_85031_j(Entity var1)
    {
        return super.func_85031_j(var1);
    }

    public final void realFunc_85033_bc()
    {
        this.func_85033_bc();
    }

    public final void superFunc_85033_bc()
    {
        super.func_85033_bc();
    }

    public final float superGetAIMoveSpeed()
    {
        return super.getAIMoveSpeed();
    }

    public final EntityLiving superGetAITarget()
    {
        return super.getAITarget();
    }

    public final PotionEffect superGetActivePotionEffect(Potion var1)
    {
        return super.getActivePotionEffect(var1);
    }

    public final Collection superGetActivePotionEffects()
    {
        return super.getActivePotionEffects();
    }

    public final int superGetAge()
    {
        return super.getAge();
    }

    public final int superGetAir()
    {
        return super.getAir();
    }

    public final EntityLiving superGetAttackTarget()
    {
        return super.getAttackTarget();
    }

    public final ChunkCoordinates superGetBedLocation()
    {
        return super.getBedLocation();
    }

    public final float superGetBedOrientationInDegrees()
    {
        return super.getBedOrientationInDegrees();
    }

    public final AxisAlignedBB superGetBoundingBox()
    {
        return super.getBoundingBox();
    }

    public final int superGetBrightnessForRender(float var1)
    {
        return super.getBrightnessForRender(var1);
    }

    public final boolean superGetCanSpawnHere()
    {
        return super.getCanSpawnHere();
    }

    public int getChatVisibility()
    {
        return this.chatVisibility;
    }

    public final float superGetCollisionBorderSize()
    {
        return super.getCollisionBorderSize();
    }

    public final AxisAlignedBB superGetCollisionBox(Entity var1)
    {
        return super.getCollisionBox(var1);
    }

    public final String superGetCommandSenderName()
    {
        return super.getCommandSenderName();
    }

    public final EnumCreatureAttribute superGetCreatureAttribute()
    {
        return super.getCreatureAttribute();
    }

    public final ItemStack superGetCurrentArmor(int var1)
    {
        return super.getCurrentArmor(var1);
    }

    public final ItemStack superGetCurrentEquippedItem()
    {
        return super.getCurrentEquippedItem();
    }

    public final ItemStack superGetCurrentItemOrArmor(int var1)
    {
        return super.getCurrentItemOrArmor(var1);
    }

    public final DataWatcher superGetDataWatcher()
    {
        return super.getDataWatcher();
    }

    public final String realGetDeathSound()
    {
        return this.getDeathSound();
    }

    public final String superGetDeathSound()
    {
        return super.getDeathSound();
    }

    public final double superGetDistance(double var1, double var3, double var5)
    {
        return super.getDistance(var1, var3, var5);
    }

    public final double superGetDistanceSqToEntity(Entity var1)
    {
        return super.getDistanceSqToEntity(var1);
    }

    public final float superGetDistanceToEntity(Entity var1)
    {
        return super.getDistanceToEntity(var1);
    }

    public final int realGetDropItemId()
    {
        return this.getDropItemId();
    }

    public final int superGetDropItemId()
    {
        return super.getDropItemId();
    }

    public final String superGetEntityName()
    {
        return super.getEntityName();
    }

    public final EntitySenses superGetEntitySenses()
    {
        return super.getEntitySenses();
    }

    public final int realGetExperiencePoints(EntityPlayer var1)
    {
        return this.getExperiencePoints(var1);
    }

    public final int superGetExperiencePoints(EntityPlayer var1)
    {
        return super.getExperiencePoints(var1);
    }

    public final boolean realGetFlag(int var1)
    {
        return this.getFlag(var1);
    }

    public final boolean superGetFlag(int var1)
    {
        return super.getFlag(var1);
    }

    public final FoodStats superGetFoodStats()
    {
        return super.getFoodStats();
    }

    public final boolean superGetHasActivePotion()
    {
        return super.getHasActivePotion();
    }

    public final int superGetHealth()
    {
        return super.getHealth();
    }

    public final ItemStack superGetHeldItem()
    {
        return super.getHeldItem();
    }

    public final boolean superGetHideCape()
    {
        return super.getHideCape();
    }

    public final boolean realGetHideCape(int var1)
    {
        return this.getHideCape(var1);
    }

    public final boolean superGetHideCape(int var1)
    {
        return super.getHideCape(var1);
    }

    public final ChunkCoordinates superGetHomePosition()
    {
        return super.getHomePosition();
    }

    public final String realGetHurtSound()
    {
        return this.getHurtSound();
    }

    public final String superGetHurtSound()
    {
        return super.getHurtSound();
    }

    public final InventoryEnderChest superGetInventoryEnderChest()
    {
        return super.getInventoryEnderChest();
    }

    public final int superGetItemIcon(ItemStack var1, int var2)
    {
        return super.getItemIcon(var1, var2);
    }

    public final ItemStack superGetItemInUse()
    {
        return super.getItemInUse();
    }

    public final int superGetItemInUseCount()
    {
        return super.getItemInUseCount();
    }

    public final int superGetItemInUseDuration()
    {
        return super.getItemInUseDuration();
    }

    public final EntityJumpHelper superGetJumpHelper()
    {
        return super.getJumpHelper();
    }

    public final ItemStack[] superGetLastActiveItems()
    {
        return super.getLastActiveItems();
    }

    public final EntityLiving superGetLastAttackingEntity()
    {
        return super.getLastAttackingEntity();
    }

    public final String realGetLivingSound()
    {
        return this.getLivingSound();
    }

    public final String superGetLivingSound()
    {
        return super.getLivingSound();
    }

    public final Vec3 superGetLook(float var1)
    {
        return super.getLook(var1);
    }

    public final EntityLookHelper superGetLookHelper()
    {
        return super.getLookHelper();
    }

    public final Vec3 superGetLookVec()
    {
        return super.getLookVec();
    }

    public final int superGetMaxInPortalTime()
    {
        return super.getMaxInPortalTime();
    }

    public final int superGetMaxSpawnedInChunk()
    {
        return super.getMaxSpawnedInChunk();
    }

    public final float superGetMaximumHomeDistance()
    {
        return super.getMaximumHomeDistance();
    }

    public final double superGetMountedYOffset()
    {
        return super.getMountedYOffset();
    }

    public final EntityMoveHelper superGetMoveHelper()
    {
        return super.getMoveHelper();
    }

    public final PathNavigate superGetNavigator()
    {
        return super.getNavigator();
    }

    public final Entity[] superGetParts()
    {
        return super.getParts();
    }

    /**
     * Return the position for this command sender.
     */
    public ChunkCoordinates getPlayerCoordinates()
    {
        return new ChunkCoordinates(MathHelper.floor_double(this.posX), MathHelper.floor_double(this.posY + 0.5D), MathHelper.floor_double(this.posZ));
    }

    public final int superGetPortalCooldown()
    {
        return super.getPortalCooldown();
    }

    public final Vec3 superGetPosition(float var1)
    {
        return super.getPosition(var1);
    }

    public final Random superGetRNG()
    {
        return super.getRNG();
    }

    public final float superGetRenderSizeModifier()
    {
        return super.getRenderSizeModifier();
    }

    public final int superGetScore()
    {
        return super.getScore();
    }

    public WorldServer getServerForPlayer()
    {
        return (WorldServer)this.worldObj;
    }

    public final float superGetShadowSize()
    {
        return super.getShadowSize();
    }

    public final int superGetSleepTimer()
    {
        return super.getSleepTimer();
    }

    public final float realGetSoundPitch()
    {
        return this.getSoundPitch();
    }

    public final float superGetSoundPitch()
    {
        return super.getSoundPitch();
    }

    public final float realGetSoundVolume()
    {
        return this.getSoundVolume();
    }

    public final float superGetSoundVolume()
    {
        return super.getSoundVolume();
    }

    public final float superGetSwingProgress(float var1)
    {
        return super.getSwingProgress(var1);
    }

    public final int superGetTalkInterval()
    {
        return super.getTalkInterval();
    }

    public final String superGetTexture()
    {
        return super.getTexture();
    }

    public final int superGetTotalArmorValue()
    {
        return super.getTotalArmorValue();
    }

    public StringTranslate getTranslator()
    {
        return this.translator;
    }

    public final StringTranslate superGetTranslator()
    {
        return super.getTranslator();
    }

    public final int superGetVerticalFaceSpeed()
    {
        return super.getVerticalFaceSpeed();
    }

    public final double superGetYOffset()
    {
        return super.getYOffset();
    }

    public final void superHandleHealthUpdate(byte var1)
    {
        super.handleHealthUpdate(var1);
    }

    public final boolean superHandleLavaMovement()
    {
        return super.handleLavaMovement();
    }

    public final boolean superHandleWaterMovement()
    {
        return super.handleWaterMovement();
    }

    public final boolean superHasHome()
    {
        return super.hasHome();
    }

    public final int superHashCode()
    {
        return super.hashCode();
    }

    public void incrementWindowID()
    {
        this.currentWindowId = this.currentWindowId % 100 + 1;
    }

    public final void realIncrementWindowID()
    {
        this.incrementWindowID();
    }

    public final void superInitCreature()
    {
        super.initCreature();
    }

    public final boolean superInteractWith(Entity var1)
    {
        return super.interactWith(var1);
    }

    public final boolean realIsAIEnabled()
    {
        return this.isAIEnabled();
    }

    public final boolean superIsAIEnabled()
    {
        return super.isAIEnabled();
    }

    public final boolean superIsBlocking()
    {
        return super.isBlocking();
    }

    public final boolean superIsBurning()
    {
        return super.isBurning();
    }

    public final boolean superIsChild()
    {
        return super.isChild();
    }

    public final boolean realIsClientWorld()
    {
        return this.isClientWorld();
    }

    public final boolean superIsClientWorld()
    {
        return super.isClientWorld();
    }

    public final boolean superIsEating()
    {
        return super.isEating();
    }

    public final boolean superIsEntityAlive()
    {
        return super.isEntityAlive();
    }

    public final boolean superIsEntityEqual(Entity var1)
    {
        return super.isEntityEqual(var1);
    }

    public final boolean superIsEntityInvulnerable()
    {
        return super.isEntityInvulnerable();
    }

    public final boolean superIsEntityUndead()
    {
        return super.isEntityUndead();
    }

    public final boolean superIsInRangeToRenderDist(double var1)
    {
        return super.isInRangeToRenderDist(var1);
    }

    public final boolean superIsInRangeToRenderVec3D(Vec3 var1)
    {
        return super.isInRangeToRenderVec3D(var1);
    }

    public final boolean realIsMovementBlocked()
    {
        return this.isMovementBlocked();
    }

    public final boolean superIsMovementBlocked()
    {
        return super.isMovementBlocked();
    }

    public final boolean superIsOffsetPositionInLiquid(double var1, double var3, double var5)
    {
        return super.isOffsetPositionInLiquid(var1, var3, var5);
    }

    /**
     * returns if pvp is enabled or not
     */
    protected boolean isPVPEnabled()
    {
        return this.mcServer.isPVPEnabled();
    }

    public final boolean realIsPVPEnabled()
    {
        return this.isPVPEnabled();
    }

    public final boolean superIsPVPEnabled()
    {
        return super.isPVPEnabled();
    }

    public final boolean realIsPlayer()
    {
        return this.isPlayer();
    }

    public final boolean superIsPlayer()
    {
        return super.isPlayer();
    }

    public final boolean superIsPlayerFullyAsleep()
    {
        return super.isPlayerFullyAsleep();
    }

    public final boolean superIsPotionActive(int var1)
    {
        return super.isPotionActive(var1);
    }

    public final boolean superIsPotionActive(Potion var1)
    {
        return super.isPotionActive(var1);
    }

    public final boolean superIsPotionApplicable(PotionEffect var1)
    {
        return super.isPotionApplicable(var1);
    }

    public final boolean superIsRiding()
    {
        return super.isRiding();
    }

    public final boolean superIsSneaking()
    {
        return super.isSneaking();
    }

    public final boolean superIsSpawnForced()
    {
        return super.isSpawnForced();
    }

    public final boolean superIsSprinting()
    {
        return super.isSprinting();
    }

    public final boolean superIsUsingItem()
    {
        return super.isUsingItem();
    }

    public final boolean superIsWet()
    {
        return super.isWet();
    }

    public final boolean superIsWithinHomeDistance(int var1, int var2, int var3)
    {
        return super.isWithinHomeDistance(var1, var2, var3);
    }

    public final boolean superIsWithinHomeDistanceCurrentPosition()
    {
        return super.isWithinHomeDistanceCurrentPosition();
    }

    public final void realJoinEntityItemWithWorld(EntityItem var1)
    {
        this.joinEntityItemWithWorld(var1);
    }

    public final void superJoinEntityItemWithWorld(EntityItem var1)
    {
        super.joinEntityItemWithWorld(var1);
    }

    public final void realKill()
    {
        this.kill();
    }

    public final void superKill()
    {
        super.kill();
    }

    /**
     * Called when a player mounts an entity. e.g. mounts a pig, mounts a boat.
     */
    public void mountEntity(Entity par1Entity)
    {
        super.mountEntity(par1Entity);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet39AttachEntity(this, this.ridingEntity));
        this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
    }

    public final void superMountEntity(Entity var1)
    {
        super.mountEntity(var1);
    }

    public void mountEntityAndWakeUp()
    {
        if (this.ridingEntity != null)
        {
            this.mountEntity(this.ridingEntity);
        }

        if (this.riddenByEntity != null)
        {
            this.riddenByEntity.mountEntity(this);
        }

        if (this.sleeping)
        {
            this.wakeUpPlayer(true, false, false);
        }
    }

    public final NBTTagList realNewDoubleNBTList(double ... var1)
    {
        return this.newDoubleNBTList(var1);
    }

    public final NBTTagList superNewDoubleNBTList(double ... var1)
    {
        return super.newDoubleNBTList(var1);
    }

    public final NBTTagList realNewFloatNBTList(float ... var1)
    {
        return this.newFloatNBTList(var1);
    }

    public final NBTTagList superNewFloatNBTList(float ... var1)
    {
        return super.newFloatNBTList(var1);
    }

    protected void onChangedPotionEffect(PotionEffect par1PotionEffect)
    {
        super.onChangedPotionEffect(par1PotionEffect);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.entityId, par1PotionEffect));
    }

    public final void realOnChangedPotionEffect(PotionEffect var1)
    {
        this.onChangedPotionEffect(var1);
    }

    public final void superOnChangedPotionEffect(PotionEffect var1)
    {
        super.onChangedPotionEffect(var1);
    }

    public final void superOnCollideWithPlayer(EntityPlayer var1)
    {
        super.onCollideWithPlayer(var1);
    }

    /**
     * Called when the player performs a critical hit on the Entity. Args: entity that was hit critically
     */
    public void onCriticalHit(Entity par1Entity)
    {
        this.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(par1Entity, 6));
    }

    public final void superOnCriticalHit(Entity var1)
    {
        super.onCriticalHit(var1);
    }

    public final void realOnDeathUpdate()
    {
        this.onDeathUpdate();
    }

    public final void superOnDeathUpdate()
    {
        super.onDeathUpdate();
    }

    public void onEnchantmentCritical(Entity par1Entity)
    {
        this.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(par1Entity, 7));
    }

    public final void superOnEnchantmentCritical(Entity var1)
    {
        super.onEnchantmentCritical(var1);
    }

    public final void superOnEntityUpdate()
    {
        super.onEntityUpdate();
    }

    protected void onFinishedPotionEffect(PotionEffect par1PotionEffect)
    {
        super.onFinishedPotionEffect(par1PotionEffect);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet42RemoveEntityEffect(this.entityId, par1PotionEffect));
    }

    public final void realOnFinishedPotionEffect(PotionEffect var1)
    {
        this.onFinishedPotionEffect(var1);
    }

    public final void superOnFinishedPotionEffect(PotionEffect var1)
    {
        super.onFinishedPotionEffect(var1);
    }

    /**
     * Called whenever an item is picked up from walking over it. Args: pickedUpEntity, stackSize
     */
    public void onItemPickup(Entity par1Entity, int par2)
    {
        super.onItemPickup(par1Entity, par2);
        this.openContainer.detectAndSendChanges();
    }

    public final void superOnItemPickup(Entity var1, int var2)
    {
        super.onItemPickup(var1, var2);
    }

    /**
     * Used for when item use count runs out, ie: eating completed
     */
    protected void onItemUseFinish()
    {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet38EntityStatus(this.entityId, (byte)9));
        super.onItemUseFinish();
    }

    public final void realOnItemUseFinish()
    {
        this.onItemUseFinish();
    }

    public final void superOnItemUseFinish()
    {
        super.onItemUseFinish();
    }

    protected void onNewPotionEffect(PotionEffect par1PotionEffect)
    {
        super.onNewPotionEffect(par1PotionEffect);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet41EntityEffect(this.entityId, par1PotionEffect));
    }

    public final void realOnNewPotionEffect(PotionEffect var1)
    {
        this.onNewPotionEffect(var1);
    }

    public final void superOnNewPotionEffect(PotionEffect var1)
    {
        super.onNewPotionEffect(var1);
    }

    public final void superPerformHurtAnimation()
    {
        super.performHurtAnimation();
    }

    public final void superPlayLivingSound()
    {
        super.playLivingSound();
    }

    public final void superPlaySound(String var1, float var2, float var3)
    {
        super.playSound(var1, var2, var3);
    }

    public final void realPlayStepSound(int var1, int var2, int var3, int var4)
    {
        this.playStepSound(var1, var2, var3, var4);
    }

    public final void superPlayStepSound(int var1, int var2, int var3, int var4)
    {
        super.playStepSound(var1, var2, var3, var4);
    }

    public final void superPreparePlayerToSpawn()
    {
        super.preparePlayerToSpawn();
    }

    public final boolean realPushOutOfBlocks(double var1, double var3, double var5)
    {
        return this.pushOutOfBlocks(var1, var3, var5);
    }

    public final boolean superPushOutOfBlocks(double var1, double var3, double var5)
    {
        return super.pushOutOfBlocks(var1, var3, var5);
    }

    public final MovingObjectPosition superRayTrace(double var1, float var3)
    {
        return super.rayTrace(var1, var3);
    }

    public final void superReadFromNBT(NBTTagCompound var1)
    {
        super.readFromNBT(var1);
    }

    public final void superRemovePotionEffect(int var1)
    {
        super.removePotionEffect(var1);
    }

    public final void superRemovePotionEffectClient(int var1)
    {
        super.removePotionEffectClient(var1);
    }

    public final void superRenderBrokenItemStack(ItemStack var1)
    {
        super.renderBrokenItemStack(var1);
    }

    /**
     * on recieving this message the client (if permission is given) will download the requested textures
     */
    public void requestTexturePackLoad(String par1Str, int par2)
    {
        String var3 = par1Str + "\u0000" + par2;
        this.playerNetServerHandler.sendPacketToPlayer(new Packet250CustomPayload("MC|TPack", var3.getBytes()));
    }

    /**
     * sets the players height back to normal after doing things like sleeping and dieing
     */
    protected void resetHeight()
    {
        this.yOffset = 0.0F;
    }

    public final void realResetHeight()
    {
        this.resetHeight();
    }

    public final void superResetHeight()
    {
        super.resetHeight();
    }

    public final void superRespawnPlayer()
    {
        super.respawnPlayer();
    }

    public void sendChatToPlayer(String par1Str)
    {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet3Chat(par1Str));
    }

    public void sendContainerAndContentsToPlayer(Container par1Container, List par2List)
    {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet104WindowItems(par1Container.windowId, par2List));
        this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
    }

    public void sendContainerToPlayer(Container par1Container)
    {
        this.sendContainerAndContentsToPlayer(par1Container, par1Container.getInventory());
    }

    /**
     * Sends the player's abilities to the server (if there is one).
     */
    public void sendPlayerAbilities()
    {
        if (this.playerNetServerHandler != null)
        {
            this.playerNetServerHandler.sendPacketToPlayer(new Packet202PlayerAbilities(this.capabilities));
        }
    }

    public final void superSendPlayerAbilities()
    {
        super.sendPlayerAbilities();
    }

    /**
     * Sends two ints to the client-side Container. Used for furnace burning time, smelting progress, brewing progress,
     * and enchanting level. Normally the first int identifies which variable to update, and the second contains the new
     * value. Both are truncated to shorts in non-local SMP.
     */
    public void sendProgressBarUpdate(Container par1Container, int par2, int par3)
    {
        this.playerNetServerHandler.sendPacketToPlayer(new Packet105UpdateProgressbar(par1Container.windowId, par2, par3));
    }

    /**
     * Sends the contents of an inventory slot to the client-side Container. This doesn't have to match the actual
     * contents of that slot. Args: Container, slot number, slot contents
     */
    public void sendSlotContents(Container par1Container, int par2, ItemStack par3ItemStack)
    {
        if (!(par1Container.getSlot(par2) instanceof SlotCrafting))
        {
            if (!this.playerInventoryBeingManipulated)
            {
                this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(par1Container.windowId, par2, par3ItemStack));
            }
        }
    }

    /**
     * called from onUpdate for all tileEntity in specific chunks
     */
    private void sendTileEntityToPlayer(TileEntity par1TileEntity)
    {
        if (par1TileEntity != null)
        {
            Packet var2 = par1TileEntity.getDescriptionPacket();

            if (var2 != null)
            {
                this.playerNetServerHandler.sendPacketToPlayer(var2);
            }
        }
    }

    public final void realSendTileEntityToPlayer(TileEntity var1)
    {
        this.sendTileEntityToPlayer(var1);
    }

    public final void superSetAIMoveSpeed(float var1)
    {
        super.setAIMoveSpeed(var1);
    }

    public final void superSetAir(int var1)
    {
        super.setAir(var1);
    }

    public final void superSetAngles(float var1, float var2)
    {
        super.setAngles(var1, var2);
    }

    public final void superSetAttackTarget(EntityLiving var1)
    {
        super.setAttackTarget(var1);
    }

    public final void realSetBeenAttacked()
    {
        this.setBeenAttacked();
    }

    public final void superSetBeenAttacked()
    {
        super.setBeenAttacked();
    }

    public final void superSetCurrentItemOrArmor(int var1, ItemStack var2)
    {
        super.setCurrentItemOrArmor(var1, var2);
    }

    public final void superSetEating(boolean var1)
    {
        super.setEating(var1);
    }

    public final void superSetEntityHealth(int var1)
    {
        super.setEntityHealth(var1);
    }

    public final void superSetFire(int var1)
    {
        super.setFire(var1);
    }

    public final void realSetFlag(int var1, boolean var2)
    {
        this.setFlag(var1, var2);
    }

    public final void superSetFlag(int var1, boolean var2)
    {
        super.setFlag(var1, var2);
    }

    /**
     * Sets the player's game mode and sends it to them.
     */
    public void setGameType(EnumGameType par1EnumGameType)
    {
        this.theItemInWorldManager.setGameType(par1EnumGameType);
        this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(3, par1EnumGameType.getID()));
    }

    public final void superSetGameType(EnumGameType var1)
    {
        super.setGameType(var1);
    }

    public final void superSetHasActivePotion(boolean var1)
    {
        super.setHasActivePotion(var1);
    }

    public final void superSetHeadRotationYaw(float var1)
    {
        super.setHeadRotationYaw(var1);
    }

    public final void realSetHideCape(int var1, boolean var2)
    {
        this.setHideCape(var1, var2);
    }

    public final void superSetHideCape(int var1, boolean var2)
    {
        super.setHideCape(var1, var2);
    }

    public final void superSetHomeArea(int var1, int var2, int var3, int var4)
    {
        super.setHomeArea(var1, var2, var3, var4);
    }

    public final void superSetInPortal()
    {
        super.setInPortal();
    }

    public final void superSetInWeb()
    {
        super.setInWeb();
    }

    /**
     * sets the itemInUse when the use item button is clicked. Args: itemstack, int maxItemUseDuration
     */
    public void setItemInUse(ItemStack par1ItemStack, int par2)
    {
        super.setItemInUse(par1ItemStack, par2);

        if (par1ItemStack != null && par1ItemStack.getItem() != null && par1ItemStack.getItem().getItemUseAction(par1ItemStack) == EnumAction.eat)
        {
            this.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(this, 5));
        }
    }

    public final void superSetItemInUse(ItemStack var1, int var2)
    {
        super.setItemInUse(var1, var2);
    }

    public final void superSetJumping(boolean var1)
    {
        super.setJumping(var1);
    }

    public final void superSetLastAttackingEntity(Entity var1)
    {
        super.setLastAttackingEntity(var1);
    }

    public final void superSetLocationAndAngles(double var1, double var3, double var5, float var7, float var8)
    {
        super.setLocationAndAngles(var1, var3, var5, var7, var8);
    }

    public final void superSetMoveForward(float var1)
    {
        super.setMoveForward(var1);
    }

    public final void realSetOnFireFromLava()
    {
        this.setOnFireFromLava();
    }

    public final void superSetOnFireFromLava()
    {
        super.setOnFireFromLava();
    }

    /**
     * this function is called when a players inventory is sent to him, lastHealth is updated on any dimension
     * transitions, then reset.
     */
    public void setPlayerHealthUpdated()
    {
        this.lastHealth = -99999999;
    }

    public final void superSetPositionAndRotation(double var1, double var3, double var5, float var7, float var8)
    {
        super.setPositionAndRotation(var1, var3, var5, var7, var8);
    }

    public final void superSetPositionAndRotation2(double var1, double var3, double var5, float var7, float var8, int var9)
    {
        super.setPositionAndRotation2(var1, var3, var5, var7, var8, var9);
    }

    /**
     * Move the entity to the coordinates informed, but keep yaw/pitch values.
     */
    public void setPositionAndUpdate(double par1, double par3, double par5)
    {
        this.playerNetServerHandler.setPlayerLocation(par1, par3, par5, this.rotationYaw, this.rotationPitch);
    }

    public final void superSetPositionAndUpdate(double var1, double var3, double var5)
    {
        super.setPositionAndUpdate(var1, var3, var5);
    }

    public final void superSetRevengeTarget(EntityLiving var1)
    {
        super.setRevengeTarget(var1);
    }

    public final void realSetRotation(float var1, float var2)
    {
        this.setRotation(var1, var2);
    }

    public final void superSetRotation(float var1, float var2)
    {
        super.setRotation(var1, var2);
    }

    public final float superSetRotationYawHead()
    {
        return super.setRotationYawHead();
    }

    public final void superSetScore(int var1)
    {
        super.setScore(var1);
    }

    public final void realSetSize(float var1, float var2)
    {
        this.setSize(var1, var2);
    }

    public final void superSetSize(float var1, float var2)
    {
        super.setSize(var1, var2);
    }

    public final void superSetSneaking(boolean var1)
    {
        super.setSneaking(var1);
    }

    public final void superSetSpawnChunk(ChunkCoordinates var1, boolean var2)
    {
        super.setSpawnChunk(var1, var2);
    }

    public final void superSetSprinting(boolean var1)
    {
        super.setSprinting(var1);
    }

    public final void superSetVelocity(double var1, double var3, double var5)
    {
        super.setVelocity(var1, var3, var5);
    }

    public final void superSetWorld(World var1)
    {
        super.setWorld(var1);
    }

    public final boolean superShouldHeal()
    {
        return super.shouldHeal();
    }

    /**
     * Attempts to have the player sleep in a bed at the specified location.
     */
    public EnumStatus sleepInBedAt(int par1, int par2, int par3)
    {
        EnumStatus var4 = super.sleepInBedAt(par1, par2, par3);

        if (var4 == EnumStatus.OK)
        {
            Packet17Sleep var5 = new Packet17Sleep(this, 0, par1, par2, par3);
            this.getServerForPlayer().getEntityTracker().sendPacketToAllPlayersTrackingEntity(this, var5);
            this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
            this.playerNetServerHandler.sendPacketToPlayer(var5);
        }

        return var4;
    }

    public final EnumStatus superSleepInBedAt(int var1, int var2, int var3)
    {
        return super.sleepInBedAt(var1, var2, var3);
    }

    public final void superSpawnExplosionParticle()
    {
        super.spawnExplosionParticle();
    }

    public final void superStopUsingItem()
    {
        super.stopUsingItem();
    }

    public final String superToString()
    {
        return super.toString();
    }

    public final String superTranslateString(String var1, Object ... var2)
    {
        return super.translateString(var1, var2);
    }

    /**
     * Teleports the entity to another dimension. Params: Dimension number to teleport to
     */
    public void travelToDimension(int par1)
    {
        if (this.dimension == 1 && par1 == 1)
        {
            this.triggerAchievement(AchievementList.theEnd2);
            this.worldObj.removeEntity(this);
            this.playerConqueredTheEnd = true;
            this.playerNetServerHandler.sendPacketToPlayer(new Packet70GameEvent(4, 0));
        }
        else
        {
            if (this.dimension == 1 && par1 == 0)
            {
                this.triggerAchievement(AchievementList.theEnd);
                ChunkCoordinates var2 = this.mcServer.worldServerForDimension(par1).getEntrancePortalLocation();

                if (var2 != null)
                {
                    this.playerNetServerHandler.setPlayerLocation((double)var2.posX, (double)var2.posY, (double)var2.posZ, 0.0F, 0.0F);
                }

                par1 = 1;
            }
            else
            {
                this.triggerAchievement(AchievementList.portal);
            }

            this.mcServer.getConfigurationManager().transferPlayerToDimension(this, par1);
            this.lastExperience = -1;
            this.lastHealth = -1;
            this.lastFoodLevel = -1;
        }
    }

    public final void superTravelToDimension(int var1)
    {
        super.travelToDimension(var1);
    }

    public final void superTriggerAchievement(StatBase var1)
    {
        super.triggerAchievement(var1);
    }

    public final void superUnmountEntity(Entity var1)
    {
        super.unmountEntity(var1);
    }

    public final void realUpdateAITasks()
    {
        this.updateAITasks();
    }

    public final void superUpdateAITasks()
    {
        super.updateAITasks();
    }

    public final void realUpdateAITick()
    {
        this.updateAITick();
    }

    public final void superUpdateAITick()
    {
        super.updateAITick();
    }

    public final void realUpdateArmSwingProgress()
    {
        this.updateArmSwingProgress();
    }

    public final void superUpdateArmSwingProgress()
    {
        super.updateArmSwingProgress();
    }

    public void updateClientInfo(Packet204ClientInfo par1Packet204ClientInfo)
    {
        if (this.translator.getLanguageList().containsKey(par1Packet204ClientInfo.getLanguage()))
        {
            this.translator.setLanguage(par1Packet204ClientInfo.getLanguage());
        }

        int var2 = 256 >> par1Packet204ClientInfo.getRenderDistance();

        if (var2 > 3 && var2 < 15)
        {
            this.renderDistance = var2;
        }

        this.chatVisibility = par1Packet204ClientInfo.getChatVisibility();
        this.chatColours = par1Packet204ClientInfo.getChatColours();

        if (this.mcServer.isSinglePlayer() && this.mcServer.getServerOwner().equals(this.username))
        {
            this.mcServer.setDifficultyForAllWorlds(par1Packet204ClientInfo.getDifficulty());
        }

        this.setHideCape(1, !par1Packet204ClientInfo.getShowCape());
    }

    public final void superUpdateCloak()
    {
        super.updateCloak();
    }

    /**
     * Takes in the distance the entity has fallen this tick and whether its on the ground to update the fall distance
     * and deal fall damage if landing on the ground.  Args: distanceFallenThisTick, onGround
     */
    protected void updateFallState(double par1, boolean par3) {}

    public final void realUpdateFallState(double var1, boolean var3)
    {
        this.updateFallState(var1, var3);
    }

    public final void superUpdateFallState(double var1, boolean var3)
    {
        super.updateFallState(var1, var3);
    }

    /**
     * likeUpdateFallState, but called from updateFlyingState, rather than moveEntity
     */
    public void updateFlyingState(double par1, boolean par3)
    {
        super.updateFallState(par1, par3);
    }

    /**
     * updates item held by mouse
     */
    public void updateHeldItem()
    {
        if (!this.playerInventoryBeingManipulated)
        {
            this.playerNetServerHandler.sendPacketToPlayer(new Packet103SetSlot(-1, -1, this.inventory.getItemStack()));
        }
    }

    public final void realUpdateItemUse(ItemStack var1, int var2)
    {
        this.updateItemUse(var1, var2);
    }

    public final void superUpdateItemUse(ItemStack var1, int var2)
    {
        super.updateItemUse(var1, var2);
    }

    public final void realUpdatePotionEffects()
    {
        this.updatePotionEffects();
    }

    public final void superUpdatePotionEffects()
    {
        super.updatePotionEffects();
    }

    public final void superUpdateRidden()
    {
        super.updateRidden();
    }

    public final void superUpdateRiderPosition()
    {
        super.updateRiderPosition();
    }

    /**
     * Wake up the player if they're sleeping.
     */
    public void wakeUpPlayer(boolean par1, boolean par2, boolean par3)
    {
        if (this.isPlayerSleeping())
        {
            this.getServerForPlayer().getEntityTracker().sendPacketToAllAssociatedPlayers(this, new Packet18Animation(this, 3));
        }

        super.wakeUpPlayer(par1, par2, par3);

        if (this.playerNetServerHandler != null)
        {
            this.playerNetServerHandler.setPlayerLocation(this.posX, this.posY, this.posZ, this.rotationYaw, this.rotationPitch);
        }
    }

    public final void superWakeUpPlayer(boolean var1, boolean var2, boolean var3)
    {
        super.wakeUpPlayer(var1, var2, var3);
    }

    private static Class TryLoadType(String var0)
    {
        ClassLoader var1 = EntityPlayerMP.class.getClassLoader();

        try
        {
            return var1.loadClass(var0);
        }
        catch (ClassNotFoundException var3)
        {
            return null;
        }
    }

    private static Method TryLoadMethod(Class var0, String var1, Class ... var2)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getDeclaredMethod(var1, var2);
            }
            catch (NoSuchMethodException var4)
            {
                return null;
            }
        }
    }

    private static Constructor TryLoadConstructor(Class var0, Class ... var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getConstructor(var1);
            }
            catch (NoSuchMethodException var3)
            {
                return null;
            }
        }
    }

    private static Field TryLoadField(Class var0, String var1)
    {
        if (var0 == null)
        {
            return null;
        }
        else
        {
            try
            {
                return var0.getDeclaredField(var1);
            }
            catch (NoSuchFieldException var3)
            {
                return null;
            }
        }
    }

    private static ChunkCoordinates GetRandomizedSpawnPoint(World var0)
    {
        if (getRandomizedSpawnPoint == null)
        {
            return var0.getSpawnPoint();
        }
        else
        {
            try
            {
                return (ChunkCoordinates)getRandomizedSpawnPoint.invoke(var0.provider, new Object[0]);
            }
            catch (Exception var2)
            {
                throw new RuntimeException(getRandomizedSpawnPoint.getName(), var2);
            }
        }
    }

    private static void AddChunkWatchEvent(EntityPlayerMP var0, Chunk var1)
    {
        if (chunkWatchEventWatchConstructor != null && eventBusField != null && post != null)
        {
            try
            {
                Object var2 = chunkWatchEventWatchConstructor.newInstance(new Object[] {var1.getChunkCoordIntPair(), var0});
                post.invoke(eventBusField.get((Object)null), new Object[] {var2});
            }
            catch (Exception var3)
            {
                ;
            }
        }
    }

    private static boolean OnLivingDeath(EntityLiving var0, DamageSource var1)
    {
        if (onLivingDeath == null)
        {
            return false;
        }
        else
        {
            try
            {
                return ((Boolean)onLivingDeath.invoke((Object)null, new Object[] {var0, var1})).booleanValue();
            }
            catch (Exception var3)
            {
                throw new RuntimeException(onLivingDeath.getName(), var3);
            }
        }
    }

    private static void HandleBeforeDrops(Entity var0)
    {
        if (captureDropsField != null && capturedDropsField != null)
        {
            try
            {
                captureDropsField.set(var0, Boolean.valueOf(true));
                ((List)capturedDropsField.get(var0)).clear();
            }
            catch (Exception var2)
            {
                ;
            }
        }
    }

    private static void HandleAfterDrops(EntityPlayerMP var0, DamageSource var1)
    {
        if (captureDropsField != null && capturedDropsField != null && playerDropsEventConstructor != null && eventBusField != null)
        {
            try
            {
                captureDropsField.set(var0, Boolean.valueOf(false));
                Object var2 = playerDropsEventConstructor.newInstance(new Object[] {var0, var1, capturedDropsField.get(var0), Boolean.valueOf(var0.recentlyHit > 0)});

                if (!((Boolean)post.invoke(eventBusField.get((Object)null), new Object[] {var2})).booleanValue())
                {
                    Iterator var3 = ((List)capturedDropsField.get(var0)).iterator();

                    while (var3.hasNext())
                    {
                        EntityItem var4 = (EntityItem)var3.next();
                        var0.joinEntityItemWithWorld(var4);
                    }
                }
            }
            catch (Exception var5)
            {
                ;
            }
        }
    }

    public final void superWriteToNBT(NBTTagCompound var1)
    {
        super.writeToNBT(var1);
    }

    public final int superXpBarCap()
    {
        return super.xpBarCap();
    }

    public final HashMap getActivePotionsMapField()
    {
        return this.activePotionsMap;
    }

    public final void setActivePotionsMapField(HashMap var1)
    {
        this.activePotionsMap = var1;
    }

    public final EntityPlayer getAttackingPlayerField()
    {
        return this.attackingPlayer;
    }

    public final void setAttackingPlayerField(EntityPlayer var1)
    {
        this.attackingPlayer = var1;
    }

    public final boolean getCanPickUpLootField()
    {
        return this.canPickUpLoot;
    }

    public final void setCanPickUpLootField(boolean var1)
    {
        this.canPickUpLoot = var1;
    }

    public final int getCarryoverDamageField()
    {
        return this.carryoverDamage;
    }

    public final void setCarryoverDamageField(int var1)
    {
        this.carryoverDamage = var1;
    }

    public final boolean getChatColoursField()
    {
        return this.chatColours;
    }

    public final void setChatColoursField(boolean var1)
    {
        this.chatColours = var1;
    }

    public final int getChatVisibilityField()
    {
        return this.chatVisibility;
    }

    public final void setChatVisibilityField(int var1)
    {
        this.chatVisibility = var1;
    }

    public final int getCurrentWindowIdField()
    {
        return this.currentWindowId;
    }

    public final void setCurrentWindowIdField(int var1)
    {
        this.currentWindowId = var1;
    }

    public final DataWatcher getDataWatcherField()
    {
        return this.dataWatcher;
    }

    public final void setDataWatcherField(DataWatcher var1)
    {
        this.dataWatcher = var1;
    }

    public final boolean getDeadField()
    {
        return this.dead;
    }

    public final void setDeadField(boolean var1)
    {
        this.dead = var1;
    }

    public final float getDefaultPitchField()
    {
        return this.defaultPitch;
    }

    public final void setDefaultPitchField(float var1)
    {
        this.defaultPitch = var1;
    }

    public final int getEntityAgeField()
    {
        return this.entityAge;
    }

    public final void setEntityAgeField(int var1)
    {
        this.entityAge = var1;
    }

    public final String getEntityTypeField()
    {
        return this.entityType;
    }

    public final void setEntityTypeField(String var1)
    {
        this.entityType = var1;
    }

    public final float[] getEquipmentDropChancesField()
    {
        return this.equipmentDropChances;
    }

    public final void setEquipmentDropChancesField(float[] var1)
    {
        this.equipmentDropChances = var1;
    }

    public final int getExperienceValueField()
    {
        return this.experienceValue;
    }

    public final void setExperienceValueField(int var1)
    {
        this.experienceValue = var1;
    }

    public final float getField_70706_boField()
    {
        return this.field_70706_bo;
    }

    public final void setField_70706_boField(float var1)
    {
        this.field_70706_bo = var1;
    }

    public final boolean getField_70740_aAField()
    {
        return this.field_70740_aA;
    }

    public final void setField_70740_aAField(boolean var1)
    {
        this.field_70740_aA = var1;
    }

    public final float getField_70741_aBField()
    {
        return this.field_70741_aB;
    }

    public final void setField_70741_aBField(float var1)
    {
        this.field_70741_aB = var1;
    }

    public final float getField_70743_aDField()
    {
        return this.field_70743_aD;
    }

    public final void setField_70743_aDField(float var1)
    {
        this.field_70743_aD = var1;
    }

    public final float getField_70745_aFField()
    {
        return this.field_70745_aF;
    }

    public final void setField_70745_aFField(float var1)
    {
        this.field_70745_aF = var1;
    }

    public final boolean getField_70753_ayField()
    {
        return this.field_70753_ay;
    }

    public final void setField_70753_ayField(boolean var1)
    {
        this.field_70753_ay = var1;
    }

    public final float getField_70763_axField()
    {
        return this.field_70763_ax;
    }

    public final void setField_70763_axField(float var1)
    {
        this.field_70763_ax = var1;
    }

    public final float getField_70764_awField()
    {
        return this.field_70764_aw;
    }

    public final void setField_70764_awField(float var1)
    {
        this.field_70764_aw = var1;
    }

    public final float getField_70766_avField()
    {
        return this.field_70766_av;
    }

    public final void setField_70766_avField(float var1)
    {
        this.field_70766_av = var1;
    }

    public final float getField_70768_auField()
    {
        return this.field_70768_au;
    }

    public final void setField_70768_auField(float var1)
    {
        this.field_70768_au = var1;
    }

    public final int getField_82152_aqField()
    {
        return this.field_82152_aq;
    }

    public final void setField_82152_aqField(int var1)
    {
        this.field_82152_aq = var1;
    }

    public final int getField_82153_hField()
    {
        return this.field_82153_h;
    }

    public final void setField_82153_hField(int var1)
    {
        this.field_82153_h = var1;
    }

    public final int getFlyToggleTimerField()
    {
        return this.flyToggleTimer;
    }

    public final void setFlyToggleTimerField(int var1)
    {
        this.flyToggleTimer = var1;
    }

    public final FoodStats getFoodStatsField()
    {
        return this.foodStats;
    }

    public final void setFoodStatsField(FoodStats var1)
    {
        this.foodStats = var1;
    }

    public final int getHealthField()
    {
        return this.health;
    }

    public final void setHealthField(int var1)
    {
        this.health = var1;
    }

    public final boolean getInPortalField()
    {
        return this.inPortal;
    }

    public final void setInPortalField(boolean var1)
    {
        this.inPortal = var1;
    }

    public final boolean getInWaterField()
    {
        return this.inWater;
    }

    public final void setInWaterField(boolean var1)
    {
        this.inWater = var1;
    }

    public final int getInitialInvulnerabilityField()
    {
        return this.initialInvulnerability;
    }

    public final void setInitialInvulnerabilityField(int var1)
    {
        this.initialInvulnerability = var1;
    }

    public final boolean getIsImmuneToFireField()
    {
        return this.isImmuneToFire;
    }

    public final void setIsImmuneToFireField(boolean var1)
    {
        this.isImmuneToFire = var1;
    }

    public final boolean getIsInWebField()
    {
        return this.isInWeb;
    }

    public final void setIsInWebField(boolean var1)
    {
        this.isInWeb = var1;
    }

    public final boolean getIsJumpingField()
    {
        return this.isJumping;
    }

    public final void setIsJumpingField(boolean var1)
    {
        this.isJumping = var1;
    }

    public final int getLastDamageField()
    {
        return this.lastDamage;
    }

    public final void setLastDamageField(int var1)
    {
        this.lastDamage = var1;
    }

    public final int getLastExperienceField()
    {
        return this.lastExperience;
    }

    public final void setLastExperienceField(int var1)
    {
        this.lastExperience = var1;
    }

    public final int getLastFoodLevelField()
    {
        return this.lastFoodLevel;
    }

    public final void setLastFoodLevelField(int var1)
    {
        this.lastFoodLevel = var1;
    }

    public final int getLastHealthField()
    {
        return this.lastHealth;
    }

    public final void setLastHealthField(int var1)
    {
        this.lastHealth = var1;
    }

    public final float getMoveForwardField()
    {
        return this.moveForward;
    }

    public final void setMoveForwardField(float var1)
    {
        this.moveForward = var1;
    }

    public final float getMoveSpeedField()
    {
        return this.moveSpeed;
    }

    public final void setMoveSpeedField(float var1)
    {
        this.moveSpeed = var1;
    }

    public final float getMoveStrafingField()
    {
        return this.moveStrafing;
    }

    public final void setMoveStrafingField(float var1)
    {
        this.moveStrafing = var1;
    }

    public final int getNewPosRotationIncrementsField()
    {
        return this.newPosRotationIncrements;
    }

    public final void setNewPosRotationIncrementsField(int var1)
    {
        this.newPosRotationIncrements = var1;
    }

    public final double getNewPosXField()
    {
        return this.newPosX;
    }

    public final void setNewPosXField(double var1)
    {
        this.newPosX = var1;
    }

    public final double getNewPosYField()
    {
        return this.newPosY;
    }

    public final void setNewPosYField(double var1)
    {
        this.newPosY = var1;
    }

    public final double getNewPosZField()
    {
        return this.newPosZ;
    }

    public final void setNewPosZField(double var1)
    {
        this.newPosZ = var1;
    }

    public final double getNewRotationPitchField()
    {
        return this.newRotationPitch;
    }

    public final void setNewRotationPitchField(double var1)
    {
        this.newRotationPitch = var1;
    }

    public final double getNewRotationYawField()
    {
        return this.newRotationYaw;
    }

    public final void setNewRotationYawField(double var1)
    {
        this.newRotationYaw = var1;
    }

    public final int getNumTicksToChaseTargetField()
    {
        return this.numTicksToChaseTarget;
    }

    public final void setNumTicksToChaseTargetField(int var1)
    {
        this.numTicksToChaseTarget = var1;
    }

    public final Random getRandField()
    {
        return this.rand;
    }

    public final void setRandField(Random var1)
    {
        this.rand = var1;
    }

    public final float getRandomYawVelocityField()
    {
        return this.randomYawVelocity;
    }

    public final void setRandomYawVelocityField(float var1)
    {
        this.randomYawVelocity = var1;
    }

    public final int getRecentlyHitField()
    {
        return this.recentlyHit;
    }

    public final void setRecentlyHitField(int var1)
    {
        this.recentlyHit = var1;
    }

    public final int getRenderDistanceField()
    {
        return this.renderDistance;
    }

    public final void setRenderDistanceField(int var1)
    {
        this.renderDistance = var1;
    }

    public final int getScoreValueField()
    {
        return this.scoreValue;
    }

    public final void setScoreValueField(int var1)
    {
        this.scoreValue = var1;
    }

    public final boolean getSleepingField()
    {
        return this.sleeping;
    }

    public final void setSleepingField(boolean var1)
    {
        this.sleeping = var1;
    }

    public final float getSpeedInAirField()
    {
        return this.speedInAir;
    }

    public final void setSpeedInAirField(float var1)
    {
        this.speedInAir = var1;
    }

    public final float getSpeedOnGroundField()
    {
        return this.speedOnGround;
    }

    public final void setSpeedOnGroundField(float var1)
    {
        this.speedOnGround = var1;
    }

    public final EntityAITasks getTargetTasksField()
    {
        return this.targetTasks;
    }

    public final EntityAITasks getTasksField()
    {
        return this.tasks;
    }

    public final String getTextureField()
    {
        return this.texture;
    }

    public final void setTextureField(String var1)
    {
        this.texture = var1;
    }

    public final StringTranslate getTranslatorField()
    {
        return this.translator;
    }

    public final void setTranslatorField(StringTranslate var1)
    {
        this.translator = var1;
    }

    public final boolean getWasHungryField()
    {
        return this.wasHungry;
    }

    public final void setWasHungryField(boolean var1)
    {
        this.wasHungry = var1;
    }

    static
    {
        Class var0 = TryLoadType("net.minecraftforge.event.entity.player.PlayerDropsEvent");
        playerDropsEventConstructor = TryLoadConstructor(var0, new Class[] {EntityPlayer.class, DamageSource.class, ArrayList.class, Boolean.TYPE});
        Class var1 = TryLoadType("net.minecraftforge.event.world.ChunkWatchEvent.Watch");
        chunkWatchEventWatchConstructor = TryLoadConstructor(var1, new Class[] {ChunkCoordIntPair.class, EntityPlayerMP.class});
        captureDropsField = TryLoadField(Entity.class, "captureDrops");
        capturedDropsField = TryLoadField(Entity.class, "capturedDrops");
        getRandomizedSpawnPoint = TryLoadMethod(WorldProvider.class, "getRandomizedSpawnPoint", new Class[0]);
        Class var2 = TryLoadType("net.minecraftforge.common.MinecraftForge");
        eventBusField = TryLoadField(var2, "EVENT_BUS");
        Class var3 = TryLoadType("net.minecraftforge.event.Event");
        Class var4 = TryLoadType("net.minecraftforge.event.EventBus");
        post = TryLoadMethod(var4, "post", new Class[] {var3});
        chunkOffset = forgeHooks != null ? 15 : 16;
    }
}
