package net.minecraft.src;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.logging.Logger;

import net.minecraft.client.gui.FontRenderer;
import net.minecraft.client.model.ModelBase;
import net.minecraft.client.renderer.entity.RenderManager;
import net.minecraft.client.renderer.entity.RenderPlayer;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityLiving;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.item.EnumAction;
import net.minecraft.item.ItemStack;

public final class RenderPlayerAPI
{
    private static final Class[] Class = new Class[] {RenderPlayerAPI.class};
    private static final Class[] Classes = new Class[] {RenderPlayerAPI.class, String.class};
    private static boolean isCreated;
    private static final Logger logger = Logger.getLogger("RenderPlayerAPI");
    private static final Map EmptySortMap = Collections.unmodifiableMap(new HashMap());
    private static final List beforeDoRenderShadowAndFireHookTypes = new LinkedList();
    private static final List overrideDoRenderShadowAndFireHookTypes = new LinkedList();
    private static final List afterDoRenderShadowAndFireHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeDoRenderShadowAndFireHooks;
    private final RenderPlayerBase[] overrideDoRenderShadowAndFireHooks;
    private final RenderPlayerBase[] afterDoRenderShadowAndFireHooks;
    public final boolean isDoRenderShadowAndFireModded;
    private static final Map allBaseBeforeDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final Map allBaseAfterDoRenderShadowAndFireSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDoRenderShadowAndFireInferiors = new Hashtable(0);
    private static final List beforeDrawFirstPersonHandHookTypes = new LinkedList();
    private static final List overrideDrawFirstPersonHandHookTypes = new LinkedList();
    private static final List afterDrawFirstPersonHandHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeDrawFirstPersonHandHooks;
    private final RenderPlayerBase[] overrideDrawFirstPersonHandHooks;
    private final RenderPlayerBase[] afterDrawFirstPersonHandHooks;
    public final boolean isDrawFirstPersonHandModded;
    private static final Map allBaseBeforeDrawFirstPersonHandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDrawFirstPersonHandInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDrawFirstPersonHandSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDrawFirstPersonHandInferiors = new Hashtable(0);
    private static final Map allBaseAfterDrawFirstPersonHandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDrawFirstPersonHandInferiors = new Hashtable(0);
    private static final List beforeGetColorMultiplierHookTypes = new LinkedList();
    private static final List overrideGetColorMultiplierHookTypes = new LinkedList();
    private static final List afterGetColorMultiplierHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeGetColorMultiplierHooks;
    private final RenderPlayerBase[] overrideGetColorMultiplierHooks;
    private final RenderPlayerBase[] afterGetColorMultiplierHooks;
    public final boolean isGetColorMultiplierModded;
    private static final Map allBaseBeforeGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetColorMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetColorMultiplierInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetColorMultiplierSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetColorMultiplierInferiors = new Hashtable(0);
    private static final List beforeGetDeathMaxRotationHookTypes = new LinkedList();
    private static final List overrideGetDeathMaxRotationHookTypes = new LinkedList();
    private static final List afterGetDeathMaxRotationHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeGetDeathMaxRotationHooks;
    private final RenderPlayerBase[] overrideGetDeathMaxRotationHooks;
    private final RenderPlayerBase[] afterGetDeathMaxRotationHooks;
    public final boolean isGetDeathMaxRotationModded;
    private static final Map allBaseBeforeGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetDeathMaxRotationSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetDeathMaxRotationInferiors = new Hashtable(0);
    private static final List beforeGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private static final List overrideGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private static final List afterGetFontRendererFromRenderManagerHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeGetFontRendererFromRenderManagerHooks;
    private final RenderPlayerBase[] overrideGetFontRendererFromRenderManagerHooks;
    private final RenderPlayerBase[] afterGetFontRendererFromRenderManagerHooks;
    public final boolean isGetFontRendererFromRenderManagerModded;
    private static final Map allBaseBeforeGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseAfterGetFontRendererFromRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterGetFontRendererFromRenderManagerInferiors = new Hashtable(0);
    private static final List beforeHandleRotationFloatHookTypes = new LinkedList();
    private static final List overrideHandleRotationFloatHookTypes = new LinkedList();
    private static final List afterHandleRotationFloatHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeHandleRotationFloatHooks;
    private final RenderPlayerBase[] overrideHandleRotationFloatHooks;
    private final RenderPlayerBase[] afterHandleRotationFloatHooks;
    public final boolean isHandleRotationFloatModded;
    private static final Map allBaseBeforeHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeHandleRotationFloatInferiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideHandleRotationFloatInferiors = new Hashtable(0);
    private static final Map allBaseAfterHandleRotationFloatSuperiors = new Hashtable(0);
    private static final Map allBaseAfterHandleRotationFloatInferiors = new Hashtable(0);
    private static final List beforeInheritRenderPassHookTypes = new LinkedList();
    private static final List overrideInheritRenderPassHookTypes = new LinkedList();
    private static final List afterInheritRenderPassHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeInheritRenderPassHooks;
    private final RenderPlayerBase[] overrideInheritRenderPassHooks;
    private final RenderPlayerBase[] afterInheritRenderPassHooks;
    public final boolean isInheritRenderPassModded;
    private static final Map allBaseBeforeInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeInheritRenderPassInferiors = new Hashtable(0);
    private static final Map allBaseOverrideInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideInheritRenderPassInferiors = new Hashtable(0);
    private static final Map allBaseAfterInheritRenderPassSuperiors = new Hashtable(0);
    private static final Map allBaseAfterInheritRenderPassInferiors = new Hashtable(0);
    private static final List beforeLoadDownloadableImageTextureHookTypes = new LinkedList();
    private static final List overrideLoadDownloadableImageTextureHookTypes = new LinkedList();
    private static final List afterLoadDownloadableImageTextureHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeLoadDownloadableImageTextureHooks;
    private final RenderPlayerBase[] overrideLoadDownloadableImageTextureHooks;
    private final RenderPlayerBase[] afterLoadDownloadableImageTextureHooks;
    public final boolean isLoadDownloadableImageTextureModded;
    private static final Map allBaseBeforeLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final Map allBaseAfterLoadDownloadableImageTextureSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLoadDownloadableImageTextureInferiors = new Hashtable(0);
    private static final List beforeLoadTextureHookTypes = new LinkedList();
    private static final List overrideLoadTextureHookTypes = new LinkedList();
    private static final List afterLoadTextureHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeLoadTextureHooks;
    private final RenderPlayerBase[] overrideLoadTextureHooks;
    private final RenderPlayerBase[] afterLoadTextureHooks;
    public final boolean isLoadTextureModded;
    private static final Map allBaseBeforeLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLoadTextureInferiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideLoadTextureInferiors = new Hashtable(0);
    private static final Map allBaseAfterLoadTextureSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLoadTextureInferiors = new Hashtable(0);
    private static final List beforeRenderArrowsHookTypes = new LinkedList();
    private static final List overrideRenderArrowsHookTypes = new LinkedList();
    private static final List afterRenderArrowsHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderArrowsHooks;
    private final RenderPlayerBase[] overrideRenderArrowsHooks;
    private final RenderPlayerBase[] afterRenderArrowsHooks;
    public final boolean isRenderArrowsModded;
    private static final Map allBaseBeforeRenderArrowsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderArrowsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderArrowsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderArrowsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderArrowsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderArrowsInferiors = new Hashtable(0);
    private static final List beforeRenderLivingLabelHookTypes = new LinkedList();
    private static final List overrideRenderLivingLabelHookTypes = new LinkedList();
    private static final List afterRenderLivingLabelHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderLivingLabelHooks;
    private final RenderPlayerBase[] overrideRenderLivingLabelHooks;
    private final RenderPlayerBase[] afterRenderLivingLabelHooks;
    public final boolean isRenderLivingLabelModded;
    private static final Map allBaseBeforeRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderLivingLabelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderLivingLabelInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderLivingLabelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderLivingLabelInferiors = new Hashtable(0);
    private static final List beforeRenderModelHookTypes = new LinkedList();
    private static final List overrideRenderModelHookTypes = new LinkedList();
    private static final List afterRenderModelHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderModelHooks;
    private final RenderPlayerBase[] overrideRenderModelHooks;
    private final RenderPlayerBase[] afterRenderModelHooks;
    public final boolean isRenderModelModded;
    private static final Map allBaseBeforeRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderModelInferiors = new Hashtable(0);
    private static final List beforeRenderNameHookTypes = new LinkedList();
    private static final List overrideRenderNameHookTypes = new LinkedList();
    private static final List afterRenderNameHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderNameHooks;
    private final RenderPlayerBase[] overrideRenderNameHooks;
    private final RenderPlayerBase[] afterRenderNameHooks;
    public final boolean isRenderNameModded;
    private static final Map allBaseBeforeRenderNameSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderNameInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderNameSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderNameInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderNameSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderNameInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerHookTypes = new LinkedList();
    private static final List overrideRenderPlayerHookTypes = new LinkedList();
    private static final List afterRenderPlayerHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderPlayerHooks;
    private final RenderPlayerBase[] overrideRenderPlayerHooks;
    private final RenderPlayerBase[] afterRenderPlayerHooks;
    public final boolean isRenderPlayerModded;
    private static final Map allBaseBeforeRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerScaleHookTypes = new LinkedList();
    private static final List overrideRenderPlayerScaleHookTypes = new LinkedList();
    private static final List afterRenderPlayerScaleHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderPlayerScaleHooks;
    private final RenderPlayerBase[] overrideRenderPlayerScaleHooks;
    private final RenderPlayerBase[] afterRenderPlayerScaleHooks;
    public final boolean isRenderPlayerScaleModded;
    private static final Map allBaseBeforeRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerScaleInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerScaleInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerScaleSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerScaleInferiors = new Hashtable(0);
    private static final List beforeRenderPlayerSleepHookTypes = new LinkedList();
    private static final List overrideRenderPlayerSleepHookTypes = new LinkedList();
    private static final List afterRenderPlayerSleepHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderPlayerSleepHooks;
    private final RenderPlayerBase[] overrideRenderPlayerSleepHooks;
    private final RenderPlayerBase[] afterRenderPlayerSleepHooks;
    public final boolean isRenderPlayerSleepModded;
    private static final Map allBaseBeforeRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderPlayerSleepInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderPlayerSleepInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSleepSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderPlayerSleepInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialsHookTypes = new LinkedList();
    private static final List overrideRenderSpecialsHookTypes = new LinkedList();
    private static final List afterRenderSpecialsHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSpecialsHooks;
    private final RenderPlayerBase[] overrideRenderSpecialsHooks;
    private final RenderPlayerBase[] afterRenderSpecialsHooks;
    public final boolean isRenderSpecialsModded;
    private static final Map allBaseBeforeRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialsInferiors = new Hashtable(0);
    private static final List beforeRenderSwingProgressHookTypes = new LinkedList();
    private static final List overrideRenderSwingProgressHookTypes = new LinkedList();
    private static final List afterRenderSwingProgressHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSwingProgressHooks;
    private final RenderPlayerBase[] overrideRenderSwingProgressHooks;
    private final RenderPlayerBase[] afterRenderSwingProgressHooks;
    public final boolean isRenderSwingProgressModded;
    private static final Map allBaseBeforeRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSwingProgressInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSwingProgressInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSwingProgressSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSwingProgressInferiors = new Hashtable(0);
    private static final List beforeRotatePlayerHookTypes = new LinkedList();
    private static final List overrideRotatePlayerHookTypes = new LinkedList();
    private static final List afterRotatePlayerHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRotatePlayerHooks;
    private final RenderPlayerBase[] overrideRotatePlayerHooks;
    private final RenderPlayerBase[] afterRotatePlayerHooks;
    public final boolean isRotatePlayerModded;
    private static final Map allBaseBeforeRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRotatePlayerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRotatePlayerInferiors = new Hashtable(0);
    private static final Map allBaseAfterRotatePlayerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRotatePlayerInferiors = new Hashtable(0);
    private static final List beforeSetArmorModelHookTypes = new LinkedList();
    private static final List overrideSetArmorModelHookTypes = new LinkedList();
    private static final List afterSetArmorModelHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeSetArmorModelHooks;
    private final RenderPlayerBase[] overrideSetArmorModelHooks;
    private final RenderPlayerBase[] afterSetArmorModelHooks;
    public final boolean isSetArmorModelModded;
    private static final Map allBaseBeforeSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetArmorModelInferiors = new Hashtable(0);
    private static final List beforeSetPassArmorModelHookTypes = new LinkedList();
    private static final List overrideSetPassArmorModelHookTypes = new LinkedList();
    private static final List afterSetPassArmorModelHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeSetPassArmorModelHooks;
    private final RenderPlayerBase[] overrideSetPassArmorModelHooks;
    private final RenderPlayerBase[] afterSetPassArmorModelHooks;
    public final boolean isSetPassArmorModelModded;
    private static final Map allBaseBeforeSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetPassArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetPassArmorModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetPassArmorModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetPassArmorModelInferiors = new Hashtable(0);
    private static final List beforeSetRenderManagerHookTypes = new LinkedList();
    private static final List overrideSetRenderManagerHookTypes = new LinkedList();
    private static final List afterSetRenderManagerHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeSetRenderManagerHooks;
    private final RenderPlayerBase[] overrideSetRenderManagerHooks;
    private final RenderPlayerBase[] afterSetRenderManagerHooks;
    public final boolean isSetRenderManagerModded;
    private static final Map allBaseBeforeSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderManagerInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderManagerSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderManagerInferiors = new Hashtable(0);
    private static final List beforeSetRenderPassModelHookTypes = new LinkedList();
    private static final List overrideSetRenderPassModelHookTypes = new LinkedList();
    private static final List afterSetRenderPassModelHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeSetRenderPassModelHooks;
    private final RenderPlayerBase[] overrideSetRenderPassModelHooks;
    private final RenderPlayerBase[] afterSetRenderPassModelHooks;
    public final boolean isSetRenderPassModelModded;
    private static final Map allBaseBeforeSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeSetRenderPassModelInferiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideSetRenderPassModelInferiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderPassModelSuperiors = new Hashtable(0);
    private static final Map allBaseAfterSetRenderPassModelInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialHeadArmorHookTypes = new LinkedList();
    private static final List overrideRenderSpecialHeadArmorHookTypes = new LinkedList();
    private static final List afterRenderSpecialHeadArmorHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSpecialHeadArmorHooks;
    private final RenderPlayerBase[] overrideRenderSpecialHeadArmorHooks;
    private final RenderPlayerBase[] afterRenderSpecialHeadArmorHooks;
    public final boolean isRenderSpecialHeadArmorModded;
    private static final Map allBaseBeforeRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadArmorSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadArmorInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialHeadEarsHookTypes = new LinkedList();
    private static final List overrideRenderSpecialHeadEarsHookTypes = new LinkedList();
    private static final List afterRenderSpecialHeadEarsHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSpecialHeadEarsHooks;
    private final RenderPlayerBase[] overrideRenderSpecialHeadEarsHooks;
    private final RenderPlayerBase[] afterRenderSpecialHeadEarsHooks;
    public final boolean isRenderSpecialHeadEarsModded;
    private static final Map allBaseBeforeRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadEarsSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialHeadEarsInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialCloakHookTypes = new LinkedList();
    private static final List overrideRenderSpecialCloakHookTypes = new LinkedList();
    private static final List afterRenderSpecialCloakHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSpecialCloakHooks;
    private final RenderPlayerBase[] overrideRenderSpecialCloakHooks;
    private final RenderPlayerBase[] afterRenderSpecialCloakHooks;
    public final boolean isRenderSpecialCloakModded;
    private static final Map allBaseBeforeRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialCloakInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialCloakInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialCloakSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialCloakInferiors = new Hashtable(0);
    private static final List beforeRenderSpecialItemInHandHookTypes = new LinkedList();
    private static final List overrideRenderSpecialItemInHandHookTypes = new LinkedList();
    private static final List afterRenderSpecialItemInHandHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforeRenderSpecialItemInHandHooks;
    private final RenderPlayerBase[] overrideRenderSpecialItemInHandHooks;
    private final RenderPlayerBase[] afterRenderSpecialItemInHandHooks;
    public final boolean isRenderSpecialItemInHandModded;
    private static final Map allBaseBeforeRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterRenderSpecialItemInHandInferiors = new Hashtable(0);
    private static final List beforePositionSpecialItemInHandHookTypes = new LinkedList();
    private static final List overridePositionSpecialItemInHandHookTypes = new LinkedList();
    private static final List afterPositionSpecialItemInHandHookTypes = new LinkedList();
    private final RenderPlayerBase[] beforePositionSpecialItemInHandHooks;
    private final RenderPlayerBase[] overridePositionSpecialItemInHandHooks;
    private final RenderPlayerBase[] afterPositionSpecialItemInHandHooks;
    public final boolean isPositionSpecialItemInHandModded;
    private static final Map allBaseBeforePositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseBeforePositionSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseOverridePositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseOverridePositionSpecialItemInHandInferiors = new Hashtable(0);
    private static final Map allBaseAfterPositionSpecialItemInHandSuperiors = new Hashtable(0);
    private static final Map allBaseAfterPositionSpecialItemInHandInferiors = new Hashtable(0);
    protected final RenderPlayer renderPlayer;
    private static final Set keys = new HashSet();
    private static final Map keysToVirtualIds = new HashMap();
    private static final Set dynamicTypes = new HashSet();
    private static final Map virtualDynamicHookMethods = new HashMap();
    private static final Map beforeDynamicHookMethods = new HashMap();
    private static final Map overrideDynamicHookMethods = new HashMap();
    private static final Map afterDynamicHookMethods = new HashMap();
    private static final List beforeLocalConstructingHookTypes = new LinkedList();
    private static final List afterLocalConstructingHookTypes = new LinkedList();
    private static final Map beforeDynamicHookTypes = new Hashtable(0);
    private static final Map overrideDynamicHookTypes = new Hashtable(0);
    private static final Map afterDynamicHookTypes = new Hashtable(0);
    private final RenderPlayerBase[] beforeLocalConstructingHooks;
    private final RenderPlayerBase[] afterLocalConstructingHooks;
    private final Map baseObjectsToId = new Hashtable();
    private final Map allBaseObjects = new Hashtable();
    private final Set unmodifiableAllBaseIds;
    private static final Map allBaseConstructors = new Hashtable();
    private static final Set unmodifiableAllIds = Collections.unmodifiableSet(allBaseConstructors.keySet());
    private static final Map allBaseBeforeLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeLocalConstructingInferiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingSuperiors = new Hashtable(0);
    private static final Map allBaseAfterLocalConstructingInferiors = new Hashtable(0);
    private static final Map allBaseBeforeDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseBeforeDynamicInferiors = new Hashtable(0);
    private static final Map allBaseOverrideDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseOverrideDynamicInferiors = new Hashtable(0);
    private static final Map allBaseAfterDynamicSuperiors = new Hashtable(0);
    private static final Map allBaseAfterDynamicInferiors = new Hashtable(0);
    private static boolean initialized = false;

    private static void log(String var0)
    {
        System.out.println(var0);
        logger.fine(var0);
    }

    public static void register(String var0, Class var1)
    {
        register(var0, var1, (RenderPlayerBaseSorting)null);
    }

    public static void register(String var0, Class var1, RenderPlayerBaseSorting var2)
    {
        try
        {
            register(var1, var0, var2);
        }
        catch (RuntimeException var4)
        {
            if (var0 != null)
            {
                log("RenderPlayerAPI: failed to register id \'" + var0 + "\'");
            }
            else
            {
                log("RenderPlayerAPI: failed to register RenderPlayerBase");
            }

            throw var4;
        }
    }

    private static void register(Class var0, String var1, RenderPlayerBaseSorting var2)
    {
        if (!isCreated)
        {
            log("RenderPlayerAPI 1.1 Created");
            isCreated = true;
        }

        if (var1 == null)
        {
            throw new NullPointerException("Argument \'id\' can not be null");
        }
        else if (var0 == null)
        {
            throw new NullPointerException("Argument \'baseClass\' can not be null");
        }
        else
        {
            Constructor var3 = (Constructor)allBaseConstructors.get(var1);

            if (var3 != null)
            {
                throw new IllegalArgumentException("The class \'" + var0.getName() + "\' can not be registered with the id \'" + var1 + "\' because the class \'" + var3.getDeclaringClass().getName() + "\' has allready been registered with the same id");
            }
            else
            {
                Constructor var4;

                try
                {
                    var4 = var0.getDeclaredConstructor(Classes);
                }
                catch (Throwable var8)
                {
                    try
                    {
                        var4 = var0.getDeclaredConstructor(Class);
                    }
                    catch (Throwable var7)
                    {
                        throw new IllegalArgumentException("Can not find necessary constructor with one argument of type \'" + RenderPlayerAPI.class.getName() + "\' and eventually a second argument of type \'String\' in the class \'" + var0.getName() + "\'", var8);
                    }
                }

                allBaseConstructors.put(var1, var4);

                if (var2 != null)
                {
                    addSorting(var1, allBaseBeforeLocalConstructingSuperiors, var2.getBeforeLocalConstructingSuperiors());
                    addSorting(var1, allBaseBeforeLocalConstructingInferiors, var2.getBeforeLocalConstructingInferiors());
                    addSorting(var1, allBaseAfterLocalConstructingSuperiors, var2.getAfterLocalConstructingSuperiors());
                    addSorting(var1, allBaseAfterLocalConstructingInferiors, var2.getAfterLocalConstructingInferiors());
                    addDynamicSorting(var1, allBaseBeforeDynamicSuperiors, var2.getDynamicBeforeSuperiors());
                    addDynamicSorting(var1, allBaseBeforeDynamicInferiors, var2.getDynamicBeforeInferiors());
                    addDynamicSorting(var1, allBaseOverrideDynamicSuperiors, var2.getDynamicOverrideSuperiors());
                    addDynamicSorting(var1, allBaseOverrideDynamicInferiors, var2.getDynamicOverrideInferiors());
                    addDynamicSorting(var1, allBaseAfterDynamicSuperiors, var2.getDynamicAfterSuperiors());
                    addDynamicSorting(var1, allBaseAfterDynamicInferiors, var2.getDynamicAfterInferiors());
                    addSorting(var1, allBaseBeforeDoRenderShadowAndFireSuperiors, var2.getBeforeDoRenderShadowAndFireSuperiors());
                    addSorting(var1, allBaseBeforeDoRenderShadowAndFireInferiors, var2.getBeforeDoRenderShadowAndFireInferiors());
                    addSorting(var1, allBaseOverrideDoRenderShadowAndFireSuperiors, var2.getOverrideDoRenderShadowAndFireSuperiors());
                    addSorting(var1, allBaseOverrideDoRenderShadowAndFireInferiors, var2.getOverrideDoRenderShadowAndFireInferiors());
                    addSorting(var1, allBaseAfterDoRenderShadowAndFireSuperiors, var2.getAfterDoRenderShadowAndFireSuperiors());
                    addSorting(var1, allBaseAfterDoRenderShadowAndFireInferiors, var2.getAfterDoRenderShadowAndFireInferiors());
                    addSorting(var1, allBaseBeforeDrawFirstPersonHandSuperiors, var2.getBeforeDrawFirstPersonHandSuperiors());
                    addSorting(var1, allBaseBeforeDrawFirstPersonHandInferiors, var2.getBeforeDrawFirstPersonHandInferiors());
                    addSorting(var1, allBaseOverrideDrawFirstPersonHandSuperiors, var2.getOverrideDrawFirstPersonHandSuperiors());
                    addSorting(var1, allBaseOverrideDrawFirstPersonHandInferiors, var2.getOverrideDrawFirstPersonHandInferiors());
                    addSorting(var1, allBaseAfterDrawFirstPersonHandSuperiors, var2.getAfterDrawFirstPersonHandSuperiors());
                    addSorting(var1, allBaseAfterDrawFirstPersonHandInferiors, var2.getAfterDrawFirstPersonHandInferiors());
                    addSorting(var1, allBaseBeforeGetColorMultiplierSuperiors, var2.getBeforeGetColorMultiplierSuperiors());
                    addSorting(var1, allBaseBeforeGetColorMultiplierInferiors, var2.getBeforeGetColorMultiplierInferiors());
                    addSorting(var1, allBaseOverrideGetColorMultiplierSuperiors, var2.getOverrideGetColorMultiplierSuperiors());
                    addSorting(var1, allBaseOverrideGetColorMultiplierInferiors, var2.getOverrideGetColorMultiplierInferiors());
                    addSorting(var1, allBaseAfterGetColorMultiplierSuperiors, var2.getAfterGetColorMultiplierSuperiors());
                    addSorting(var1, allBaseAfterGetColorMultiplierInferiors, var2.getAfterGetColorMultiplierInferiors());
                    addSorting(var1, allBaseBeforeGetDeathMaxRotationSuperiors, var2.getBeforeGetDeathMaxRotationSuperiors());
                    addSorting(var1, allBaseBeforeGetDeathMaxRotationInferiors, var2.getBeforeGetDeathMaxRotationInferiors());
                    addSorting(var1, allBaseOverrideGetDeathMaxRotationSuperiors, var2.getOverrideGetDeathMaxRotationSuperiors());
                    addSorting(var1, allBaseOverrideGetDeathMaxRotationInferiors, var2.getOverrideGetDeathMaxRotationInferiors());
                    addSorting(var1, allBaseAfterGetDeathMaxRotationSuperiors, var2.getAfterGetDeathMaxRotationSuperiors());
                    addSorting(var1, allBaseAfterGetDeathMaxRotationInferiors, var2.getAfterGetDeathMaxRotationInferiors());
                    addSorting(var1, allBaseBeforeGetFontRendererFromRenderManagerSuperiors, var2.getBeforeGetFontRendererFromRenderManagerSuperiors());
                    addSorting(var1, allBaseBeforeGetFontRendererFromRenderManagerInferiors, var2.getBeforeGetFontRendererFromRenderManagerInferiors());
                    addSorting(var1, allBaseOverrideGetFontRendererFromRenderManagerSuperiors, var2.getOverrideGetFontRendererFromRenderManagerSuperiors());
                    addSorting(var1, allBaseOverrideGetFontRendererFromRenderManagerInferiors, var2.getOverrideGetFontRendererFromRenderManagerInferiors());
                    addSorting(var1, allBaseAfterGetFontRendererFromRenderManagerSuperiors, var2.getAfterGetFontRendererFromRenderManagerSuperiors());
                    addSorting(var1, allBaseAfterGetFontRendererFromRenderManagerInferiors, var2.getAfterGetFontRendererFromRenderManagerInferiors());
                    addSorting(var1, allBaseBeforeHandleRotationFloatSuperiors, var2.getBeforeHandleRotationFloatSuperiors());
                    addSorting(var1, allBaseBeforeHandleRotationFloatInferiors, var2.getBeforeHandleRotationFloatInferiors());
                    addSorting(var1, allBaseOverrideHandleRotationFloatSuperiors, var2.getOverrideHandleRotationFloatSuperiors());
                    addSorting(var1, allBaseOverrideHandleRotationFloatInferiors, var2.getOverrideHandleRotationFloatInferiors());
                    addSorting(var1, allBaseAfterHandleRotationFloatSuperiors, var2.getAfterHandleRotationFloatSuperiors());
                    addSorting(var1, allBaseAfterHandleRotationFloatInferiors, var2.getAfterHandleRotationFloatInferiors());
                    addSorting(var1, allBaseBeforeInheritRenderPassSuperiors, var2.getBeforeInheritRenderPassSuperiors());
                    addSorting(var1, allBaseBeforeInheritRenderPassInferiors, var2.getBeforeInheritRenderPassInferiors());
                    addSorting(var1, allBaseOverrideInheritRenderPassSuperiors, var2.getOverrideInheritRenderPassSuperiors());
                    addSorting(var1, allBaseOverrideInheritRenderPassInferiors, var2.getOverrideInheritRenderPassInferiors());
                    addSorting(var1, allBaseAfterInheritRenderPassSuperiors, var2.getAfterInheritRenderPassSuperiors());
                    addSorting(var1, allBaseAfterInheritRenderPassInferiors, var2.getAfterInheritRenderPassInferiors());
                    addSorting(var1, allBaseBeforeLoadDownloadableImageTextureSuperiors, var2.getBeforeLoadDownloadableImageTextureSuperiors());
                    addSorting(var1, allBaseBeforeLoadDownloadableImageTextureInferiors, var2.getBeforeLoadDownloadableImageTextureInferiors());
                    addSorting(var1, allBaseOverrideLoadDownloadableImageTextureSuperiors, var2.getOverrideLoadDownloadableImageTextureSuperiors());
                    addSorting(var1, allBaseOverrideLoadDownloadableImageTextureInferiors, var2.getOverrideLoadDownloadableImageTextureInferiors());
                    addSorting(var1, allBaseAfterLoadDownloadableImageTextureSuperiors, var2.getAfterLoadDownloadableImageTextureSuperiors());
                    addSorting(var1, allBaseAfterLoadDownloadableImageTextureInferiors, var2.getAfterLoadDownloadableImageTextureInferiors());
                    addSorting(var1, allBaseBeforeLoadTextureSuperiors, var2.getBeforeLoadTextureSuperiors());
                    addSorting(var1, allBaseBeforeLoadTextureInferiors, var2.getBeforeLoadTextureInferiors());
                    addSorting(var1, allBaseOverrideLoadTextureSuperiors, var2.getOverrideLoadTextureSuperiors());
                    addSorting(var1, allBaseOverrideLoadTextureInferiors, var2.getOverrideLoadTextureInferiors());
                    addSorting(var1, allBaseAfterLoadTextureSuperiors, var2.getAfterLoadTextureSuperiors());
                    addSorting(var1, allBaseAfterLoadTextureInferiors, var2.getAfterLoadTextureInferiors());
                    addSorting(var1, allBaseBeforeRenderArrowsSuperiors, var2.getBeforeRenderArrowsSuperiors());
                    addSorting(var1, allBaseBeforeRenderArrowsInferiors, var2.getBeforeRenderArrowsInferiors());
                    addSorting(var1, allBaseOverrideRenderArrowsSuperiors, var2.getOverrideRenderArrowsSuperiors());
                    addSorting(var1, allBaseOverrideRenderArrowsInferiors, var2.getOverrideRenderArrowsInferiors());
                    addSorting(var1, allBaseAfterRenderArrowsSuperiors, var2.getAfterRenderArrowsSuperiors());
                    addSorting(var1, allBaseAfterRenderArrowsInferiors, var2.getAfterRenderArrowsInferiors());
                    addSorting(var1, allBaseBeforeRenderLivingLabelSuperiors, var2.getBeforeRenderLivingLabelSuperiors());
                    addSorting(var1, allBaseBeforeRenderLivingLabelInferiors, var2.getBeforeRenderLivingLabelInferiors());
                    addSorting(var1, allBaseOverrideRenderLivingLabelSuperiors, var2.getOverrideRenderLivingLabelSuperiors());
                    addSorting(var1, allBaseOverrideRenderLivingLabelInferiors, var2.getOverrideRenderLivingLabelInferiors());
                    addSorting(var1, allBaseAfterRenderLivingLabelSuperiors, var2.getAfterRenderLivingLabelSuperiors());
                    addSorting(var1, allBaseAfterRenderLivingLabelInferiors, var2.getAfterRenderLivingLabelInferiors());
                    addSorting(var1, allBaseBeforeRenderModelSuperiors, var2.getBeforeRenderModelSuperiors());
                    addSorting(var1, allBaseBeforeRenderModelInferiors, var2.getBeforeRenderModelInferiors());
                    addSorting(var1, allBaseOverrideRenderModelSuperiors, var2.getOverrideRenderModelSuperiors());
                    addSorting(var1, allBaseOverrideRenderModelInferiors, var2.getOverrideRenderModelInferiors());
                    addSorting(var1, allBaseAfterRenderModelSuperiors, var2.getAfterRenderModelSuperiors());
                    addSorting(var1, allBaseAfterRenderModelInferiors, var2.getAfterRenderModelInferiors());
                    addSorting(var1, allBaseBeforeRenderNameSuperiors, var2.getBeforeRenderNameSuperiors());
                    addSorting(var1, allBaseBeforeRenderNameInferiors, var2.getBeforeRenderNameInferiors());
                    addSorting(var1, allBaseOverrideRenderNameSuperiors, var2.getOverrideRenderNameSuperiors());
                    addSorting(var1, allBaseOverrideRenderNameInferiors, var2.getOverrideRenderNameInferiors());
                    addSorting(var1, allBaseAfterRenderNameSuperiors, var2.getAfterRenderNameSuperiors());
                    addSorting(var1, allBaseAfterRenderNameInferiors, var2.getAfterRenderNameInferiors());
                    addSorting(var1, allBaseBeforeRenderPlayerSuperiors, var2.getBeforeRenderPlayerSuperiors());
                    addSorting(var1, allBaseBeforeRenderPlayerInferiors, var2.getBeforeRenderPlayerInferiors());
                    addSorting(var1, allBaseOverrideRenderPlayerSuperiors, var2.getOverrideRenderPlayerSuperiors());
                    addSorting(var1, allBaseOverrideRenderPlayerInferiors, var2.getOverrideRenderPlayerInferiors());
                    addSorting(var1, allBaseAfterRenderPlayerSuperiors, var2.getAfterRenderPlayerSuperiors());
                    addSorting(var1, allBaseAfterRenderPlayerInferiors, var2.getAfterRenderPlayerInferiors());
                    addSorting(var1, allBaseBeforeRenderPlayerScaleSuperiors, var2.getBeforeRenderPlayerScaleSuperiors());
                    addSorting(var1, allBaseBeforeRenderPlayerScaleInferiors, var2.getBeforeRenderPlayerScaleInferiors());
                    addSorting(var1, allBaseOverrideRenderPlayerScaleSuperiors, var2.getOverrideRenderPlayerScaleSuperiors());
                    addSorting(var1, allBaseOverrideRenderPlayerScaleInferiors, var2.getOverrideRenderPlayerScaleInferiors());
                    addSorting(var1, allBaseAfterRenderPlayerScaleSuperiors, var2.getAfterRenderPlayerScaleSuperiors());
                    addSorting(var1, allBaseAfterRenderPlayerScaleInferiors, var2.getAfterRenderPlayerScaleInferiors());
                    addSorting(var1, allBaseBeforeRenderPlayerSleepSuperiors, var2.getBeforeRenderPlayerSleepSuperiors());
                    addSorting(var1, allBaseBeforeRenderPlayerSleepInferiors, var2.getBeforeRenderPlayerSleepInferiors());
                    addSorting(var1, allBaseOverrideRenderPlayerSleepSuperiors, var2.getOverrideRenderPlayerSleepSuperiors());
                    addSorting(var1, allBaseOverrideRenderPlayerSleepInferiors, var2.getOverrideRenderPlayerSleepInferiors());
                    addSorting(var1, allBaseAfterRenderPlayerSleepSuperiors, var2.getAfterRenderPlayerSleepSuperiors());
                    addSorting(var1, allBaseAfterRenderPlayerSleepInferiors, var2.getAfterRenderPlayerSleepInferiors());
                    addSorting(var1, allBaseBeforeRenderSpecialsSuperiors, var2.getBeforeRenderSpecialsSuperiors());
                    addSorting(var1, allBaseBeforeRenderSpecialsInferiors, var2.getBeforeRenderSpecialsInferiors());
                    addSorting(var1, allBaseOverrideRenderSpecialsSuperiors, var2.getOverrideRenderSpecialsSuperiors());
                    addSorting(var1, allBaseOverrideRenderSpecialsInferiors, var2.getOverrideRenderSpecialsInferiors());
                    addSorting(var1, allBaseAfterRenderSpecialsSuperiors, var2.getAfterRenderSpecialsSuperiors());
                    addSorting(var1, allBaseAfterRenderSpecialsInferiors, var2.getAfterRenderSpecialsInferiors());
                    addSorting(var1, allBaseBeforeRenderSwingProgressSuperiors, var2.getBeforeRenderSwingProgressSuperiors());
                    addSorting(var1, allBaseBeforeRenderSwingProgressInferiors, var2.getBeforeRenderSwingProgressInferiors());
                    addSorting(var1, allBaseOverrideRenderSwingProgressSuperiors, var2.getOverrideRenderSwingProgressSuperiors());
                    addSorting(var1, allBaseOverrideRenderSwingProgressInferiors, var2.getOverrideRenderSwingProgressInferiors());
                    addSorting(var1, allBaseAfterRenderSwingProgressSuperiors, var2.getAfterRenderSwingProgressSuperiors());
                    addSorting(var1, allBaseAfterRenderSwingProgressInferiors, var2.getAfterRenderSwingProgressInferiors());
                    addSorting(var1, allBaseBeforeRotatePlayerSuperiors, var2.getBeforeRotatePlayerSuperiors());
                    addSorting(var1, allBaseBeforeRotatePlayerInferiors, var2.getBeforeRotatePlayerInferiors());
                    addSorting(var1, allBaseOverrideRotatePlayerSuperiors, var2.getOverrideRotatePlayerSuperiors());
                    addSorting(var1, allBaseOverrideRotatePlayerInferiors, var2.getOverrideRotatePlayerInferiors());
                    addSorting(var1, allBaseAfterRotatePlayerSuperiors, var2.getAfterRotatePlayerSuperiors());
                    addSorting(var1, allBaseAfterRotatePlayerInferiors, var2.getAfterRotatePlayerInferiors());
                    addSorting(var1, allBaseBeforeSetArmorModelSuperiors, var2.getBeforeSetArmorModelSuperiors());
                    addSorting(var1, allBaseBeforeSetArmorModelInferiors, var2.getBeforeSetArmorModelInferiors());
                    addSorting(var1, allBaseOverrideSetArmorModelSuperiors, var2.getOverrideSetArmorModelSuperiors());
                    addSorting(var1, allBaseOverrideSetArmorModelInferiors, var2.getOverrideSetArmorModelInferiors());
                    addSorting(var1, allBaseAfterSetArmorModelSuperiors, var2.getAfterSetArmorModelSuperiors());
                    addSorting(var1, allBaseAfterSetArmorModelInferiors, var2.getAfterSetArmorModelInferiors());
                    addSorting(var1, allBaseBeforeSetPassArmorModelSuperiors, var2.getBeforeSetPassArmorModelSuperiors());
                    addSorting(var1, allBaseBeforeSetPassArmorModelInferiors, var2.getBeforeSetPassArmorModelInferiors());
                    addSorting(var1, allBaseOverrideSetPassArmorModelSuperiors, var2.getOverrideSetPassArmorModelSuperiors());
                    addSorting(var1, allBaseOverrideSetPassArmorModelInferiors, var2.getOverrideSetPassArmorModelInferiors());
                    addSorting(var1, allBaseAfterSetPassArmorModelSuperiors, var2.getAfterSetPassArmorModelSuperiors());
                    addSorting(var1, allBaseAfterSetPassArmorModelInferiors, var2.getAfterSetPassArmorModelInferiors());
                    addSorting(var1, allBaseBeforeSetRenderManagerSuperiors, var2.getBeforeSetRenderManagerSuperiors());
                    addSorting(var1, allBaseBeforeSetRenderManagerInferiors, var2.getBeforeSetRenderManagerInferiors());
                    addSorting(var1, allBaseOverrideSetRenderManagerSuperiors, var2.getOverrideSetRenderManagerSuperiors());
                    addSorting(var1, allBaseOverrideSetRenderManagerInferiors, var2.getOverrideSetRenderManagerInferiors());
                    addSorting(var1, allBaseAfterSetRenderManagerSuperiors, var2.getAfterSetRenderManagerSuperiors());
                    addSorting(var1, allBaseAfterSetRenderManagerInferiors, var2.getAfterSetRenderManagerInferiors());
                    addSorting(var1, allBaseBeforeSetRenderPassModelSuperiors, var2.getBeforeSetRenderPassModelSuperiors());
                    addSorting(var1, allBaseBeforeSetRenderPassModelInferiors, var2.getBeforeSetRenderPassModelInferiors());
                    addSorting(var1, allBaseOverrideSetRenderPassModelSuperiors, var2.getOverrideSetRenderPassModelSuperiors());
                    addSorting(var1, allBaseOverrideSetRenderPassModelInferiors, var2.getOverrideSetRenderPassModelInferiors());
                    addSorting(var1, allBaseAfterSetRenderPassModelSuperiors, var2.getAfterSetRenderPassModelSuperiors());
                    addSorting(var1, allBaseAfterSetRenderPassModelInferiors, var2.getAfterSetRenderPassModelInferiors());
                    addSorting(var1, allBaseBeforeRenderSpecialHeadArmorSuperiors, var2.getBeforeRenderSpecialHeadArmorSuperiors());
                    addSorting(var1, allBaseBeforeRenderSpecialHeadArmorInferiors, var2.getBeforeRenderSpecialHeadArmorInferiors());
                    addSorting(var1, allBaseOverrideRenderSpecialHeadArmorSuperiors, var2.getOverrideRenderSpecialHeadArmorSuperiors());
                    addSorting(var1, allBaseOverrideRenderSpecialHeadArmorInferiors, var2.getOverrideRenderSpecialHeadArmorInferiors());
                    addSorting(var1, allBaseAfterRenderSpecialHeadArmorSuperiors, var2.getAfterRenderSpecialHeadArmorSuperiors());
                    addSorting(var1, allBaseAfterRenderSpecialHeadArmorInferiors, var2.getAfterRenderSpecialHeadArmorInferiors());
                    addSorting(var1, allBaseBeforeRenderSpecialHeadEarsSuperiors, var2.getBeforeRenderSpecialHeadEarsSuperiors());
                    addSorting(var1, allBaseBeforeRenderSpecialHeadEarsInferiors, var2.getBeforeRenderSpecialHeadEarsInferiors());
                    addSorting(var1, allBaseOverrideRenderSpecialHeadEarsSuperiors, var2.getOverrideRenderSpecialHeadEarsSuperiors());
                    addSorting(var1, allBaseOverrideRenderSpecialHeadEarsInferiors, var2.getOverrideRenderSpecialHeadEarsInferiors());
                    addSorting(var1, allBaseAfterRenderSpecialHeadEarsSuperiors, var2.getAfterRenderSpecialHeadEarsSuperiors());
                    addSorting(var1, allBaseAfterRenderSpecialHeadEarsInferiors, var2.getAfterRenderSpecialHeadEarsInferiors());
                    addSorting(var1, allBaseBeforeRenderSpecialCloakSuperiors, var2.getBeforeRenderSpecialCloakSuperiors());
                    addSorting(var1, allBaseBeforeRenderSpecialCloakInferiors, var2.getBeforeRenderSpecialCloakInferiors());
                    addSorting(var1, allBaseOverrideRenderSpecialCloakSuperiors, var2.getOverrideRenderSpecialCloakSuperiors());
                    addSorting(var1, allBaseOverrideRenderSpecialCloakInferiors, var2.getOverrideRenderSpecialCloakInferiors());
                    addSorting(var1, allBaseAfterRenderSpecialCloakSuperiors, var2.getAfterRenderSpecialCloakSuperiors());
                    addSorting(var1, allBaseAfterRenderSpecialCloakInferiors, var2.getAfterRenderSpecialCloakInferiors());
                    addSorting(var1, allBaseBeforeRenderSpecialItemInHandSuperiors, var2.getBeforeRenderSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseBeforeRenderSpecialItemInHandInferiors, var2.getBeforeRenderSpecialItemInHandInferiors());
                    addSorting(var1, allBaseOverrideRenderSpecialItemInHandSuperiors, var2.getOverrideRenderSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseOverrideRenderSpecialItemInHandInferiors, var2.getOverrideRenderSpecialItemInHandInferiors());
                    addSorting(var1, allBaseAfterRenderSpecialItemInHandSuperiors, var2.getAfterRenderSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseAfterRenderSpecialItemInHandInferiors, var2.getAfterRenderSpecialItemInHandInferiors());
                    addSorting(var1, allBaseBeforePositionSpecialItemInHandSuperiors, var2.getBeforePositionSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseBeforePositionSpecialItemInHandInferiors, var2.getBeforePositionSpecialItemInHandInferiors());
                    addSorting(var1, allBaseOverridePositionSpecialItemInHandSuperiors, var2.getOverridePositionSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseOverridePositionSpecialItemInHandInferiors, var2.getOverridePositionSpecialItemInHandInferiors());
                    addSorting(var1, allBaseAfterPositionSpecialItemInHandSuperiors, var2.getAfterPositionSpecialItemInHandSuperiors());
                    addSorting(var1, allBaseAfterPositionSpecialItemInHandInferiors, var2.getAfterPositionSpecialItemInHandInferiors());
                }

                addMethod(var1, var0, beforeLocalConstructingHookTypes, "beforeLocalConstructing", new Class[0]);
                addMethod(var1, var0, afterLocalConstructingHookTypes, "afterLocalConstructing", new Class[0]);
                addMethod(var1, var0, beforeDoRenderShadowAndFireHookTypes, "beforeDoRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideDoRenderShadowAndFireHookTypes, "doRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterDoRenderShadowAndFireHookTypes, "afterDoRenderShadowAndFire", new Class[] {Entity.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeDrawFirstPersonHandHookTypes, "beforeDrawFirstPersonHand", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, overrideDrawFirstPersonHandHookTypes, "drawFirstPersonHand", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, afterDrawFirstPersonHandHookTypes, "afterDrawFirstPersonHand", new Class[] {EntityPlayer.class});
                addMethod(var1, var0, beforeGetColorMultiplierHookTypes, "beforeGetColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideGetColorMultiplierHookTypes, "getColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterGetColorMultiplierHookTypes, "afterGetColorMultiplier", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeGetDeathMaxRotationHookTypes, "beforeGetDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(var1, var0, overrideGetDeathMaxRotationHookTypes, "getDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(var1, var0, afterGetDeathMaxRotationHookTypes, "afterGetDeathMaxRotation", new Class[] {EntityLiving.class});
                addMethod(var1, var0, beforeGetFontRendererFromRenderManagerHookTypes, "beforeGetFontRendererFromRenderManager", new Class[0]);
                addMethod(var1, var0, overrideGetFontRendererFromRenderManagerHookTypes, "getFontRendererFromRenderManager", new Class[0]);
                addMethod(var1, var0, afterGetFontRendererFromRenderManagerHookTypes, "afterGetFontRendererFromRenderManager", new Class[0]);
                addMethod(var1, var0, beforeHandleRotationFloatHookTypes, "beforeHandleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, overrideHandleRotationFloatHookTypes, "handleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, afterHandleRotationFloatHookTypes, "afterHandleRotationFloat", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, beforeInheritRenderPassHookTypes, "beforeInheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideInheritRenderPassHookTypes, "inheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, afterInheritRenderPassHookTypes, "afterInheritRenderPass", new Class[] {EntityLiving.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeLoadDownloadableImageTextureHookTypes, "beforeLoadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(var1, var0, overrideLoadDownloadableImageTextureHookTypes, "loadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(var1, var0, afterLoadDownloadableImageTextureHookTypes, "afterLoadDownloadableImageTexture", new Class[] {String.class, String.class});
                addMethod(var1, var0, beforeLoadTextureHookTypes, "beforeLoadTexture", new Class[] {String.class});
                addMethod(var1, var0, overrideLoadTextureHookTypes, "loadTexture", new Class[] {String.class});
                addMethod(var1, var0, afterLoadTextureHookTypes, "afterLoadTexture", new Class[] {String.class});
                addMethod(var1, var0, beforeRenderArrowsHookTypes, "beforeRenderArrows", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderArrowsHookTypes, "renderArrows", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, afterRenderArrowsHookTypes, "afterRenderArrows", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderLivingLabelHookTypes, "beforeRenderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(var1, var0, overrideRenderLivingLabelHookTypes, "renderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(var1, var0, afterRenderLivingLabelHookTypes, "afterRenderLivingLabel", new Class[] {EntityLiving.class, String.class, Double.TYPE, Double.TYPE, Double.TYPE, Integer.TYPE});
                addMethod(var1, var0, beforeRenderModelHookTypes, "beforeRenderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideRenderModelHookTypes, "renderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterRenderModelHookTypes, "afterRenderModel", new Class[] {EntityLiving.class, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeRenderNameHookTypes, "beforeRenderName", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideRenderNameHookTypes, "renderName", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterRenderNameHookTypes, "afterRenderName", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeRenderPlayerHookTypes, "beforeRenderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideRenderPlayerHookTypes, "renderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterRenderPlayerHookTypes, "afterRenderPlayer", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeRenderPlayerScaleHookTypes, "beforeRenderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderPlayerScaleHookTypes, "renderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderPlayerScaleHookTypes, "afterRenderPlayerScale", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderPlayerSleepHookTypes, "beforeRenderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, overrideRenderPlayerSleepHookTypes, "renderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, afterRenderPlayerSleepHookTypes, "afterRenderPlayerSleep", new Class[] {EntityPlayer.class, Double.TYPE, Double.TYPE, Double.TYPE});
                addMethod(var1, var0, beforeRenderSpecialsHookTypes, "beforeRenderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSpecialsHookTypes, "renderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSpecialsHookTypes, "afterRenderSpecials", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderSwingProgressHookTypes, "beforeRenderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSwingProgressHookTypes, "renderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSwingProgressHookTypes, "afterRenderSwingProgress", new Class[] {EntityLiving.class, Float.TYPE});
                addMethod(var1, var0, beforeRotatePlayerHookTypes, "beforeRotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideRotatePlayerHookTypes, "rotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, afterRotatePlayerHookTypes, "afterRotatePlayer", new Class[] {EntityPlayer.class, Float.TYPE, Float.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeSetArmorModelHookTypes, "beforeSetArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideSetArmorModelHookTypes, "setArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, afterSetArmorModelHookTypes, "afterSetArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeSetPassArmorModelHookTypes, "beforeSetPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, overrideSetPassArmorModelHookTypes, "setPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, afterSetPassArmorModelHookTypes, "afterSetPassArmorModel", new Class[] {EntityPlayer.class, Integer.TYPE, Float.TYPE});
                addMethod(var1, var0, beforeSetRenderManagerHookTypes, "beforeSetRenderManager", new Class[] {RenderManager.class});
                addMethod(var1, var0, overrideSetRenderManagerHookTypes, "setRenderManager", new Class[] {RenderManager.class});
                addMethod(var1, var0, afterSetRenderManagerHookTypes, "afterSetRenderManager", new Class[] {RenderManager.class});
                addMethod(var1, var0, beforeSetRenderPassModelHookTypes, "beforeSetRenderPassModel", new Class[] {ModelBase.class});
                addMethod(var1, var0, overrideSetRenderPassModelHookTypes, "setRenderPassModel", new Class[] {ModelBase.class});
                addMethod(var1, var0, afterSetRenderPassModelHookTypes, "afterSetRenderPassModel", new Class[] {ModelBase.class});
                addMethod(var1, var0, beforeRenderSpecialHeadArmorHookTypes, "beforeRenderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSpecialHeadArmorHookTypes, "renderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSpecialHeadArmorHookTypes, "afterRenderSpecialHeadArmor", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderSpecialHeadEarsHookTypes, "beforeRenderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSpecialHeadEarsHookTypes, "renderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSpecialHeadEarsHookTypes, "afterRenderSpecialHeadEars", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderSpecialCloakHookTypes, "beforeRenderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSpecialCloakHookTypes, "renderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSpecialCloakHookTypes, "afterRenderSpecialCloak", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforeRenderSpecialItemInHandHookTypes, "beforeRenderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, overrideRenderSpecialItemInHandHookTypes, "renderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, afterRenderSpecialItemInHandHookTypes, "afterRenderSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE});
                addMethod(var1, var0, beforePositionSpecialItemInHandHookTypes, "beforePositionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addMethod(var1, var0, overridePositionSpecialItemInHandHookTypes, "positionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addMethod(var1, var0, afterPositionSpecialItemInHandHookTypes, "afterPositionSpecialItemInHand", new Class[] {EntityPlayer.class, Float.TYPE, EnumAction.class, ItemStack.class});
                addDynamicMethods(var1, var0);
                addDynamicKeys(var1, var0, beforeDynamicHookMethods, beforeDynamicHookTypes);
                addDynamicKeys(var1, var0, overrideDynamicHookMethods, overrideDynamicHookTypes);
                addDynamicKeys(var1, var0, afterDynamicHookMethods, afterDynamicHookTypes);
                System.out.println("RenderPlayerAPI: registered " + var1);
                logger.fine("RenderPlayerAPI: registered class \'" + var0.getName() + "\' with id \'" + var1 + "\'");
                initialized = false;
            }
        }
    }

    public static boolean unregister(String var0)
    {
        if (var0 == null)
        {
            return false;
        }
        else
        {
            Constructor var1 = (Constructor)allBaseConstructors.remove(var0);

            if (var1 == null)
            {
                return false;
            }
            else
            {
                beforeLocalConstructingHookTypes.remove(var0);
                afterLocalConstructingHookTypes.remove(var0);
                allBaseBeforeDoRenderShadowAndFireSuperiors.remove(var0);
                allBaseBeforeDoRenderShadowAndFireInferiors.remove(var0);
                allBaseOverrideDoRenderShadowAndFireSuperiors.remove(var0);
                allBaseOverrideDoRenderShadowAndFireInferiors.remove(var0);
                allBaseAfterDoRenderShadowAndFireSuperiors.remove(var0);
                allBaseAfterDoRenderShadowAndFireInferiors.remove(var0);
                beforeDoRenderShadowAndFireHookTypes.remove(var0);
                overrideDoRenderShadowAndFireHookTypes.remove(var0);
                afterDoRenderShadowAndFireHookTypes.remove(var0);
                allBaseBeforeDrawFirstPersonHandSuperiors.remove(var0);
                allBaseBeforeDrawFirstPersonHandInferiors.remove(var0);
                allBaseOverrideDrawFirstPersonHandSuperiors.remove(var0);
                allBaseOverrideDrawFirstPersonHandInferiors.remove(var0);
                allBaseAfterDrawFirstPersonHandSuperiors.remove(var0);
                allBaseAfterDrawFirstPersonHandInferiors.remove(var0);
                beforeDrawFirstPersonHandHookTypes.remove(var0);
                overrideDrawFirstPersonHandHookTypes.remove(var0);
                afterDrawFirstPersonHandHookTypes.remove(var0);
                allBaseBeforeGetColorMultiplierSuperiors.remove(var0);
                allBaseBeforeGetColorMultiplierInferiors.remove(var0);
                allBaseOverrideGetColorMultiplierSuperiors.remove(var0);
                allBaseOverrideGetColorMultiplierInferiors.remove(var0);
                allBaseAfterGetColorMultiplierSuperiors.remove(var0);
                allBaseAfterGetColorMultiplierInferiors.remove(var0);
                beforeGetColorMultiplierHookTypes.remove(var0);
                overrideGetColorMultiplierHookTypes.remove(var0);
                afterGetColorMultiplierHookTypes.remove(var0);
                allBaseBeforeGetDeathMaxRotationSuperiors.remove(var0);
                allBaseBeforeGetDeathMaxRotationInferiors.remove(var0);
                allBaseOverrideGetDeathMaxRotationSuperiors.remove(var0);
                allBaseOverrideGetDeathMaxRotationInferiors.remove(var0);
                allBaseAfterGetDeathMaxRotationSuperiors.remove(var0);
                allBaseAfterGetDeathMaxRotationInferiors.remove(var0);
                beforeGetDeathMaxRotationHookTypes.remove(var0);
                overrideGetDeathMaxRotationHookTypes.remove(var0);
                afterGetDeathMaxRotationHookTypes.remove(var0);
                allBaseBeforeGetFontRendererFromRenderManagerSuperiors.remove(var0);
                allBaseBeforeGetFontRendererFromRenderManagerInferiors.remove(var0);
                allBaseOverrideGetFontRendererFromRenderManagerSuperiors.remove(var0);
                allBaseOverrideGetFontRendererFromRenderManagerInferiors.remove(var0);
                allBaseAfterGetFontRendererFromRenderManagerSuperiors.remove(var0);
                allBaseAfterGetFontRendererFromRenderManagerInferiors.remove(var0);
                beforeGetFontRendererFromRenderManagerHookTypes.remove(var0);
                overrideGetFontRendererFromRenderManagerHookTypes.remove(var0);
                afterGetFontRendererFromRenderManagerHookTypes.remove(var0);
                allBaseBeforeHandleRotationFloatSuperiors.remove(var0);
                allBaseBeforeHandleRotationFloatInferiors.remove(var0);
                allBaseOverrideHandleRotationFloatSuperiors.remove(var0);
                allBaseOverrideHandleRotationFloatInferiors.remove(var0);
                allBaseAfterHandleRotationFloatSuperiors.remove(var0);
                allBaseAfterHandleRotationFloatInferiors.remove(var0);
                beforeHandleRotationFloatHookTypes.remove(var0);
                overrideHandleRotationFloatHookTypes.remove(var0);
                afterHandleRotationFloatHookTypes.remove(var0);
                allBaseBeforeInheritRenderPassSuperiors.remove(var0);
                allBaseBeforeInheritRenderPassInferiors.remove(var0);
                allBaseOverrideInheritRenderPassSuperiors.remove(var0);
                allBaseOverrideInheritRenderPassInferiors.remove(var0);
                allBaseAfterInheritRenderPassSuperiors.remove(var0);
                allBaseAfterInheritRenderPassInferiors.remove(var0);
                beforeInheritRenderPassHookTypes.remove(var0);
                overrideInheritRenderPassHookTypes.remove(var0);
                afterInheritRenderPassHookTypes.remove(var0);
                allBaseBeforeLoadDownloadableImageTextureSuperiors.remove(var0);
                allBaseBeforeLoadDownloadableImageTextureInferiors.remove(var0);
                allBaseOverrideLoadDownloadableImageTextureSuperiors.remove(var0);
                allBaseOverrideLoadDownloadableImageTextureInferiors.remove(var0);
                allBaseAfterLoadDownloadableImageTextureSuperiors.remove(var0);
                allBaseAfterLoadDownloadableImageTextureInferiors.remove(var0);
                beforeLoadDownloadableImageTextureHookTypes.remove(var0);
                overrideLoadDownloadableImageTextureHookTypes.remove(var0);
                afterLoadDownloadableImageTextureHookTypes.remove(var0);
                allBaseBeforeLoadTextureSuperiors.remove(var0);
                allBaseBeforeLoadTextureInferiors.remove(var0);
                allBaseOverrideLoadTextureSuperiors.remove(var0);
                allBaseOverrideLoadTextureInferiors.remove(var0);
                allBaseAfterLoadTextureSuperiors.remove(var0);
                allBaseAfterLoadTextureInferiors.remove(var0);
                beforeLoadTextureHookTypes.remove(var0);
                overrideLoadTextureHookTypes.remove(var0);
                afterLoadTextureHookTypes.remove(var0);
                allBaseBeforeRenderArrowsSuperiors.remove(var0);
                allBaseBeforeRenderArrowsInferiors.remove(var0);
                allBaseOverrideRenderArrowsSuperiors.remove(var0);
                allBaseOverrideRenderArrowsInferiors.remove(var0);
                allBaseAfterRenderArrowsSuperiors.remove(var0);
                allBaseAfterRenderArrowsInferiors.remove(var0);
                beforeRenderArrowsHookTypes.remove(var0);
                overrideRenderArrowsHookTypes.remove(var0);
                afterRenderArrowsHookTypes.remove(var0);
                allBaseBeforeRenderLivingLabelSuperiors.remove(var0);
                allBaseBeforeRenderLivingLabelInferiors.remove(var0);
                allBaseOverrideRenderLivingLabelSuperiors.remove(var0);
                allBaseOverrideRenderLivingLabelInferiors.remove(var0);
                allBaseAfterRenderLivingLabelSuperiors.remove(var0);
                allBaseAfterRenderLivingLabelInferiors.remove(var0);
                beforeRenderLivingLabelHookTypes.remove(var0);
                overrideRenderLivingLabelHookTypes.remove(var0);
                afterRenderLivingLabelHookTypes.remove(var0);
                allBaseBeforeRenderModelSuperiors.remove(var0);
                allBaseBeforeRenderModelInferiors.remove(var0);
                allBaseOverrideRenderModelSuperiors.remove(var0);
                allBaseOverrideRenderModelInferiors.remove(var0);
                allBaseAfterRenderModelSuperiors.remove(var0);
                allBaseAfterRenderModelInferiors.remove(var0);
                beforeRenderModelHookTypes.remove(var0);
                overrideRenderModelHookTypes.remove(var0);
                afterRenderModelHookTypes.remove(var0);
                allBaseBeforeRenderNameSuperiors.remove(var0);
                allBaseBeforeRenderNameInferiors.remove(var0);
                allBaseOverrideRenderNameSuperiors.remove(var0);
                allBaseOverrideRenderNameInferiors.remove(var0);
                allBaseAfterRenderNameSuperiors.remove(var0);
                allBaseAfterRenderNameInferiors.remove(var0);
                beforeRenderNameHookTypes.remove(var0);
                overrideRenderNameHookTypes.remove(var0);
                afterRenderNameHookTypes.remove(var0);
                allBaseBeforeRenderPlayerSuperiors.remove(var0);
                allBaseBeforeRenderPlayerInferiors.remove(var0);
                allBaseOverrideRenderPlayerSuperiors.remove(var0);
                allBaseOverrideRenderPlayerInferiors.remove(var0);
                allBaseAfterRenderPlayerSuperiors.remove(var0);
                allBaseAfterRenderPlayerInferiors.remove(var0);
                beforeRenderPlayerHookTypes.remove(var0);
                overrideRenderPlayerHookTypes.remove(var0);
                afterRenderPlayerHookTypes.remove(var0);
                allBaseBeforeRenderPlayerScaleSuperiors.remove(var0);
                allBaseBeforeRenderPlayerScaleInferiors.remove(var0);
                allBaseOverrideRenderPlayerScaleSuperiors.remove(var0);
                allBaseOverrideRenderPlayerScaleInferiors.remove(var0);
                allBaseAfterRenderPlayerScaleSuperiors.remove(var0);
                allBaseAfterRenderPlayerScaleInferiors.remove(var0);
                beforeRenderPlayerScaleHookTypes.remove(var0);
                overrideRenderPlayerScaleHookTypes.remove(var0);
                afterRenderPlayerScaleHookTypes.remove(var0);
                allBaseBeforeRenderPlayerSleepSuperiors.remove(var0);
                allBaseBeforeRenderPlayerSleepInferiors.remove(var0);
                allBaseOverrideRenderPlayerSleepSuperiors.remove(var0);
                allBaseOverrideRenderPlayerSleepInferiors.remove(var0);
                allBaseAfterRenderPlayerSleepSuperiors.remove(var0);
                allBaseAfterRenderPlayerSleepInferiors.remove(var0);
                beforeRenderPlayerSleepHookTypes.remove(var0);
                overrideRenderPlayerSleepHookTypes.remove(var0);
                afterRenderPlayerSleepHookTypes.remove(var0);
                allBaseBeforeRenderSpecialsSuperiors.remove(var0);
                allBaseBeforeRenderSpecialsInferiors.remove(var0);
                allBaseOverrideRenderSpecialsSuperiors.remove(var0);
                allBaseOverrideRenderSpecialsInferiors.remove(var0);
                allBaseAfterRenderSpecialsSuperiors.remove(var0);
                allBaseAfterRenderSpecialsInferiors.remove(var0);
                beforeRenderSpecialsHookTypes.remove(var0);
                overrideRenderSpecialsHookTypes.remove(var0);
                afterRenderSpecialsHookTypes.remove(var0);
                allBaseBeforeRenderSwingProgressSuperiors.remove(var0);
                allBaseBeforeRenderSwingProgressInferiors.remove(var0);
                allBaseOverrideRenderSwingProgressSuperiors.remove(var0);
                allBaseOverrideRenderSwingProgressInferiors.remove(var0);
                allBaseAfterRenderSwingProgressSuperiors.remove(var0);
                allBaseAfterRenderSwingProgressInferiors.remove(var0);
                beforeRenderSwingProgressHookTypes.remove(var0);
                overrideRenderSwingProgressHookTypes.remove(var0);
                afterRenderSwingProgressHookTypes.remove(var0);
                allBaseBeforeRotatePlayerSuperiors.remove(var0);
                allBaseBeforeRotatePlayerInferiors.remove(var0);
                allBaseOverrideRotatePlayerSuperiors.remove(var0);
                allBaseOverrideRotatePlayerInferiors.remove(var0);
                allBaseAfterRotatePlayerSuperiors.remove(var0);
                allBaseAfterRotatePlayerInferiors.remove(var0);
                beforeRotatePlayerHookTypes.remove(var0);
                overrideRotatePlayerHookTypes.remove(var0);
                afterRotatePlayerHookTypes.remove(var0);
                allBaseBeforeSetArmorModelSuperiors.remove(var0);
                allBaseBeforeSetArmorModelInferiors.remove(var0);
                allBaseOverrideSetArmorModelSuperiors.remove(var0);
                allBaseOverrideSetArmorModelInferiors.remove(var0);
                allBaseAfterSetArmorModelSuperiors.remove(var0);
                allBaseAfterSetArmorModelInferiors.remove(var0);
                beforeSetArmorModelHookTypes.remove(var0);
                overrideSetArmorModelHookTypes.remove(var0);
                afterSetArmorModelHookTypes.remove(var0);
                allBaseBeforeSetPassArmorModelSuperiors.remove(var0);
                allBaseBeforeSetPassArmorModelInferiors.remove(var0);
                allBaseOverrideSetPassArmorModelSuperiors.remove(var0);
                allBaseOverrideSetPassArmorModelInferiors.remove(var0);
                allBaseAfterSetPassArmorModelSuperiors.remove(var0);
                allBaseAfterSetPassArmorModelInferiors.remove(var0);
                beforeSetPassArmorModelHookTypes.remove(var0);
                overrideSetPassArmorModelHookTypes.remove(var0);
                afterSetPassArmorModelHookTypes.remove(var0);
                allBaseBeforeSetRenderManagerSuperiors.remove(var0);
                allBaseBeforeSetRenderManagerInferiors.remove(var0);
                allBaseOverrideSetRenderManagerSuperiors.remove(var0);
                allBaseOverrideSetRenderManagerInferiors.remove(var0);
                allBaseAfterSetRenderManagerSuperiors.remove(var0);
                allBaseAfterSetRenderManagerInferiors.remove(var0);
                beforeSetRenderManagerHookTypes.remove(var0);
                overrideSetRenderManagerHookTypes.remove(var0);
                afterSetRenderManagerHookTypes.remove(var0);
                allBaseBeforeSetRenderPassModelSuperiors.remove(var0);
                allBaseBeforeSetRenderPassModelInferiors.remove(var0);
                allBaseOverrideSetRenderPassModelSuperiors.remove(var0);
                allBaseOverrideSetRenderPassModelInferiors.remove(var0);
                allBaseAfterSetRenderPassModelSuperiors.remove(var0);
                allBaseAfterSetRenderPassModelInferiors.remove(var0);
                beforeSetRenderPassModelHookTypes.remove(var0);
                overrideSetRenderPassModelHookTypes.remove(var0);
                afterSetRenderPassModelHookTypes.remove(var0);
                allBaseBeforeRenderSpecialHeadArmorSuperiors.remove(var0);
                allBaseBeforeRenderSpecialHeadArmorInferiors.remove(var0);
                allBaseOverrideRenderSpecialHeadArmorSuperiors.remove(var0);
                allBaseOverrideRenderSpecialHeadArmorInferiors.remove(var0);
                allBaseAfterRenderSpecialHeadArmorSuperiors.remove(var0);
                allBaseAfterRenderSpecialHeadArmorInferiors.remove(var0);
                beforeRenderSpecialHeadArmorHookTypes.remove(var0);
                overrideRenderSpecialHeadArmorHookTypes.remove(var0);
                afterRenderSpecialHeadArmorHookTypes.remove(var0);
                allBaseBeforeRenderSpecialHeadEarsSuperiors.remove(var0);
                allBaseBeforeRenderSpecialHeadEarsInferiors.remove(var0);
                allBaseOverrideRenderSpecialHeadEarsSuperiors.remove(var0);
                allBaseOverrideRenderSpecialHeadEarsInferiors.remove(var0);
                allBaseAfterRenderSpecialHeadEarsSuperiors.remove(var0);
                allBaseAfterRenderSpecialHeadEarsInferiors.remove(var0);
                beforeRenderSpecialHeadEarsHookTypes.remove(var0);
                overrideRenderSpecialHeadEarsHookTypes.remove(var0);
                afterRenderSpecialHeadEarsHookTypes.remove(var0);
                allBaseBeforeRenderSpecialCloakSuperiors.remove(var0);
                allBaseBeforeRenderSpecialCloakInferiors.remove(var0);
                allBaseOverrideRenderSpecialCloakSuperiors.remove(var0);
                allBaseOverrideRenderSpecialCloakInferiors.remove(var0);
                allBaseAfterRenderSpecialCloakSuperiors.remove(var0);
                allBaseAfterRenderSpecialCloakInferiors.remove(var0);
                beforeRenderSpecialCloakHookTypes.remove(var0);
                overrideRenderSpecialCloakHookTypes.remove(var0);
                afterRenderSpecialCloakHookTypes.remove(var0);
                allBaseBeforeRenderSpecialItemInHandSuperiors.remove(var0);
                allBaseBeforeRenderSpecialItemInHandInferiors.remove(var0);
                allBaseOverrideRenderSpecialItemInHandSuperiors.remove(var0);
                allBaseOverrideRenderSpecialItemInHandInferiors.remove(var0);
                allBaseAfterRenderSpecialItemInHandSuperiors.remove(var0);
                allBaseAfterRenderSpecialItemInHandInferiors.remove(var0);
                beforeRenderSpecialItemInHandHookTypes.remove(var0);
                overrideRenderSpecialItemInHandHookTypes.remove(var0);
                afterRenderSpecialItemInHandHookTypes.remove(var0);
                allBaseBeforePositionSpecialItemInHandSuperiors.remove(var0);
                allBaseBeforePositionSpecialItemInHandInferiors.remove(var0);
                allBaseOverridePositionSpecialItemInHandSuperiors.remove(var0);
                allBaseOverridePositionSpecialItemInHandInferiors.remove(var0);
                allBaseAfterPositionSpecialItemInHandSuperiors.remove(var0);
                allBaseAfterPositionSpecialItemInHandInferiors.remove(var0);
                beforePositionSpecialItemInHandHookTypes.remove(var0);
                overridePositionSpecialItemInHandHookTypes.remove(var0);
                afterPositionSpecialItemInHandHookTypes.remove(var0);
                Iterator var2 = keysToVirtualIds.keySet().iterator();

                while (var2.hasNext())
                {
                    String var3 = (String)var2.next();

                    if (((String)keysToVirtualIds.get(var3)).equals(var0))
                    {
                        keysToVirtualIds.remove(var3);
                    }
                }

                boolean var7 = false;
                Class var4 = var1.getDeclaringClass();
                var2 = allBaseConstructors.keySet().iterator();

                while (var2.hasNext())
                {
                    String var5 = (String)var2.next();
                    Class var6 = ((Constructor)allBaseConstructors.get(var5)).getDeclaringClass();

                    if (!var5.equals(var0) && var6.equals(var4))
                    {
                        var7 = true;
                        break;
                    }
                }

                if (!var7)
                {
                    dynamicTypes.remove(var4);
                    virtualDynamicHookMethods.remove(var4);
                    beforeDynamicHookMethods.remove(var4);
                    overrideDynamicHookMethods.remove(var4);
                    afterDynamicHookMethods.remove(var4);
                }

                removeDynamicHookTypes(var0, beforeDynamicHookTypes);
                removeDynamicHookTypes(var0, overrideDynamicHookTypes);
                removeDynamicHookTypes(var0, afterDynamicHookTypes);
                allBaseBeforeDynamicSuperiors.remove(var0);
                allBaseBeforeDynamicInferiors.remove(var0);
                allBaseOverrideDynamicSuperiors.remove(var0);
                allBaseOverrideDynamicInferiors.remove(var0);
                allBaseAfterDynamicSuperiors.remove(var0);
                allBaseAfterDynamicInferiors.remove(var0);
                log("RenderPlayerAPI: unregistered id \'" + var0 + "\'");
                return true;
            }
        }
    }

    public static void removeDynamicHookTypes(String var0, Map var1)
    {
        Iterator var2 = var1.keySet().iterator();

        while (var2.hasNext())
        {
            ((List)var1.get(var2.next())).remove(var0);
        }
    }

    public static Set getRegisteredIds()
    {
        return unmodifiableAllIds;
    }

    private static void addSorting(String var0, Map var1, String[] var2)
    {
        if (var2 != null && var2.length > 0)
        {
            var1.put(var0, var2);
        }
    }

    private static void addDynamicSorting(String var0, Map var1, Map var2)
    {
        if (var2 != null && var2.size() > 0)
        {
            var1.put(var0, var2);
        }
    }

    private static boolean addMethod(String var0, Class var1, List var2, String var3, Class ... var4)
    {
        try
        {
            Method var5 = var1.getMethod(var3, var4);
            boolean var6 = var5.getDeclaringClass() != RenderPlayerBase.class;

            if (var6)
            {
                var2.add(var0);
            }

            return var6;
        }
        catch (Exception var7)
        {
            throw new RuntimeException("Can not reflect method \'" + var3 + "\' of class \'" + var1.getName() + "\'", var7);
        }
    }

    private static void addDynamicMethods(String var0, Class var1)
    {
        if (dynamicTypes.add(var1))
        {
            Map var2 = null;
            Map var3 = null;
            Map var4 = null;
            Map var5 = null;
            Method[] var6 = var1.getDeclaredMethods();

            for (int var7 = 0; var7 < var6.length; ++var7)
            {
                Method var8 = var6[var7];

                if (var8.getDeclaringClass() == var1)
                {
                    int var9 = var8.getModifiers();

                    if (!Modifier.isAbstract(var9) && !Modifier.isStatic(var9))
                    {
                        String var10 = var8.getName();

                        if (var10.length() >= 7 && var10.substring(0, 7).equalsIgnoreCase("dynamic"))
                        {
                            for (var10 = var10.substring(7); var10.charAt(0) == 95; var10 = var10.substring(1))
                            {
                                ;
                            }

                            boolean var11 = false;
                            boolean var12 = false;
                            boolean var13 = false;
                            boolean var14 = false;

                            if (var10.substring(0, 7).equalsIgnoreCase("virtual"))
                            {
                                var12 = true;
                                var10 = var10.substring(7);
                            }
                            else if (var10.length() >= 8 && var10.substring(0, 8).equalsIgnoreCase("override"))
                            {
                                var10 = var10.substring(8);
                                var13 = true;
                            }
                            else if (var10.length() >= 6 && var10.substring(0, 6).equalsIgnoreCase("before"))
                            {
                                var11 = true;
                                var10 = var10.substring(6);
                            }
                            else if (var10.length() >= 5 && var10.substring(0, 5).equalsIgnoreCase("after"))
                            {
                                var14 = true;
                                var10 = var10.substring(5);
                            }

                            if (var10.length() >= 1 && (var11 || var12 || var13 || var14))
                            {
                                var10 = var10.substring(0, 1).toLowerCase() + var10.substring(1);
                            }

                            while (var10.charAt(0) == 95)
                            {
                                var10 = var10.substring(1);
                            }

                            if (var10.length() == 0)
                            {
                                throw new RuntimeException("Can not process dynamic hook method with no key");
                            }

                            keys.add(var10);

                            if (var12)
                            {
                                if (keysToVirtualIds.containsKey(var10))
                                {
                                    throw new RuntimeException("Can not process more than one dynamic virtual method");
                                }

                                keysToVirtualIds.put(var10, var0);
                                var2 = addDynamicMethod(var10, var8, var2);
                            }
                            else if (var11)
                            {
                                var3 = addDynamicMethod(var10, var8, var3);
                            }
                            else if (var14)
                            {
                                var5 = addDynamicMethod(var10, var8, var5);
                            }
                            else
                            {
                                var4 = addDynamicMethod(var10, var8, var4);
                            }
                        }
                    }
                }
            }

            if (var2 != null)
            {
                virtualDynamicHookMethods.put(var1, var2);
            }

            if (var3 != null)
            {
                beforeDynamicHookMethods.put(var1, var3);
            }

            if (var4 != null)
            {
                overrideDynamicHookMethods.put(var1, var4);
            }

            if (var5 != null)
            {
                afterDynamicHookMethods.put(var1, var5);
            }
        }
    }

    private static void addDynamicKeys(String var0, Class var1, Map var2, Map var3)
    {
        Map var4 = (Map)var2.get(var1);

        if (var4 != null && var4.size() != 0)
        {
            String var6;

            for (Iterator var5 = var4.keySet().iterator(); var5.hasNext(); ((List)var3.get(var6)).add(var0))
            {
                var6 = (String)var5.next();

                if (!var3.containsKey(var6))
                {
                    var3.put(var6, new ArrayList(1));
                }
            }
        }
    }

    private static Map addDynamicMethod(String var0, Method var1, Map var2)
    {
        if (var2 == null)
        {
            var2 = new HashMap();
        }

        if (((Map)var2).containsKey(var0))
        {
            throw new RuntimeException("method with key \'" + var0 + "\' allready exists");
        }
        else
        {
            ((Map)var2).put(var0, var1);
            return (Map)var2;
        }
    }

    public static RenderPlayerAPI create(RenderPlayer var0)
    {
        if (allBaseConstructors.size() > 0)
        {
            if (!initialized)
            {
                initialize();
            }

            return new RenderPlayerAPI(var0);
        }
        else
        {
            return null;
        }
    }

    private static void initialize()
    {
        sortBases(beforeLocalConstructingHookTypes, allBaseBeforeLocalConstructingSuperiors, allBaseBeforeLocalConstructingInferiors, "beforeLocalConstructing");
        sortBases(afterLocalConstructingHookTypes, allBaseAfterLocalConstructingSuperiors, allBaseAfterLocalConstructingInferiors, "afterLocalConstructing");
        Iterator var0 = keys.iterator();

        while (var0.hasNext())
        {
            String var1 = (String)var0.next();
            sortDynamicBases(beforeDynamicHookTypes, allBaseBeforeDynamicSuperiors, allBaseBeforeDynamicInferiors, var1);
            sortDynamicBases(overrideDynamicHookTypes, allBaseOverrideDynamicSuperiors, allBaseOverrideDynamicInferiors, var1);
            sortDynamicBases(afterDynamicHookTypes, allBaseAfterDynamicSuperiors, allBaseAfterDynamicInferiors, var1);
        }

        sortBases(beforeDoRenderShadowAndFireHookTypes, allBaseBeforeDoRenderShadowAndFireSuperiors, allBaseBeforeDoRenderShadowAndFireInferiors, "beforeDoRenderShadowAndFire");
        sortBases(overrideDoRenderShadowAndFireHookTypes, allBaseOverrideDoRenderShadowAndFireSuperiors, allBaseOverrideDoRenderShadowAndFireInferiors, "overrideDoRenderShadowAndFire");
        sortBases(afterDoRenderShadowAndFireHookTypes, allBaseAfterDoRenderShadowAndFireSuperiors, allBaseAfterDoRenderShadowAndFireInferiors, "afterDoRenderShadowAndFire");
        sortBases(beforeDrawFirstPersonHandHookTypes, allBaseBeforeDrawFirstPersonHandSuperiors, allBaseBeforeDrawFirstPersonHandInferiors, "beforeDrawFirstPersonHand");
        sortBases(overrideDrawFirstPersonHandHookTypes, allBaseOverrideDrawFirstPersonHandSuperiors, allBaseOverrideDrawFirstPersonHandInferiors, "overrideDrawFirstPersonHand");
        sortBases(afterDrawFirstPersonHandHookTypes, allBaseAfterDrawFirstPersonHandSuperiors, allBaseAfterDrawFirstPersonHandInferiors, "afterDrawFirstPersonHand");
        sortBases(beforeGetColorMultiplierHookTypes, allBaseBeforeGetColorMultiplierSuperiors, allBaseBeforeGetColorMultiplierInferiors, "beforeGetColorMultiplier");
        sortBases(overrideGetColorMultiplierHookTypes, allBaseOverrideGetColorMultiplierSuperiors, allBaseOverrideGetColorMultiplierInferiors, "overrideGetColorMultiplier");
        sortBases(afterGetColorMultiplierHookTypes, allBaseAfterGetColorMultiplierSuperiors, allBaseAfterGetColorMultiplierInferiors, "afterGetColorMultiplier");
        sortBases(beforeGetDeathMaxRotationHookTypes, allBaseBeforeGetDeathMaxRotationSuperiors, allBaseBeforeGetDeathMaxRotationInferiors, "beforeGetDeathMaxRotation");
        sortBases(overrideGetDeathMaxRotationHookTypes, allBaseOverrideGetDeathMaxRotationSuperiors, allBaseOverrideGetDeathMaxRotationInferiors, "overrideGetDeathMaxRotation");
        sortBases(afterGetDeathMaxRotationHookTypes, allBaseAfterGetDeathMaxRotationSuperiors, allBaseAfterGetDeathMaxRotationInferiors, "afterGetDeathMaxRotation");
        sortBases(beforeGetFontRendererFromRenderManagerHookTypes, allBaseBeforeGetFontRendererFromRenderManagerSuperiors, allBaseBeforeGetFontRendererFromRenderManagerInferiors, "beforeGetFontRendererFromRenderManager");
        sortBases(overrideGetFontRendererFromRenderManagerHookTypes, allBaseOverrideGetFontRendererFromRenderManagerSuperiors, allBaseOverrideGetFontRendererFromRenderManagerInferiors, "overrideGetFontRendererFromRenderManager");
        sortBases(afterGetFontRendererFromRenderManagerHookTypes, allBaseAfterGetFontRendererFromRenderManagerSuperiors, allBaseAfterGetFontRendererFromRenderManagerInferiors, "afterGetFontRendererFromRenderManager");
        sortBases(beforeHandleRotationFloatHookTypes, allBaseBeforeHandleRotationFloatSuperiors, allBaseBeforeHandleRotationFloatInferiors, "beforeHandleRotationFloat");
        sortBases(overrideHandleRotationFloatHookTypes, allBaseOverrideHandleRotationFloatSuperiors, allBaseOverrideHandleRotationFloatInferiors, "overrideHandleRotationFloat");
        sortBases(afterHandleRotationFloatHookTypes, allBaseAfterHandleRotationFloatSuperiors, allBaseAfterHandleRotationFloatInferiors, "afterHandleRotationFloat");
        sortBases(beforeInheritRenderPassHookTypes, allBaseBeforeInheritRenderPassSuperiors, allBaseBeforeInheritRenderPassInferiors, "beforeInheritRenderPass");
        sortBases(overrideInheritRenderPassHookTypes, allBaseOverrideInheritRenderPassSuperiors, allBaseOverrideInheritRenderPassInferiors, "overrideInheritRenderPass");
        sortBases(afterInheritRenderPassHookTypes, allBaseAfterInheritRenderPassSuperiors, allBaseAfterInheritRenderPassInferiors, "afterInheritRenderPass");
        sortBases(beforeLoadDownloadableImageTextureHookTypes, allBaseBeforeLoadDownloadableImageTextureSuperiors, allBaseBeforeLoadDownloadableImageTextureInferiors, "beforeLoadDownloadableImageTexture");
        sortBases(overrideLoadDownloadableImageTextureHookTypes, allBaseOverrideLoadDownloadableImageTextureSuperiors, allBaseOverrideLoadDownloadableImageTextureInferiors, "overrideLoadDownloadableImageTexture");
        sortBases(afterLoadDownloadableImageTextureHookTypes, allBaseAfterLoadDownloadableImageTextureSuperiors, allBaseAfterLoadDownloadableImageTextureInferiors, "afterLoadDownloadableImageTexture");
        sortBases(beforeLoadTextureHookTypes, allBaseBeforeLoadTextureSuperiors, allBaseBeforeLoadTextureInferiors, "beforeLoadTexture");
        sortBases(overrideLoadTextureHookTypes, allBaseOverrideLoadTextureSuperiors, allBaseOverrideLoadTextureInferiors, "overrideLoadTexture");
        sortBases(afterLoadTextureHookTypes, allBaseAfterLoadTextureSuperiors, allBaseAfterLoadTextureInferiors, "afterLoadTexture");
        sortBases(beforeRenderArrowsHookTypes, allBaseBeforeRenderArrowsSuperiors, allBaseBeforeRenderArrowsInferiors, "beforeRenderArrows");
        sortBases(overrideRenderArrowsHookTypes, allBaseOverrideRenderArrowsSuperiors, allBaseOverrideRenderArrowsInferiors, "overrideRenderArrows");
        sortBases(afterRenderArrowsHookTypes, allBaseAfterRenderArrowsSuperiors, allBaseAfterRenderArrowsInferiors, "afterRenderArrows");
        sortBases(beforeRenderLivingLabelHookTypes, allBaseBeforeRenderLivingLabelSuperiors, allBaseBeforeRenderLivingLabelInferiors, "beforeRenderLivingLabel");
        sortBases(overrideRenderLivingLabelHookTypes, allBaseOverrideRenderLivingLabelSuperiors, allBaseOverrideRenderLivingLabelInferiors, "overrideRenderLivingLabel");
        sortBases(afterRenderLivingLabelHookTypes, allBaseAfterRenderLivingLabelSuperiors, allBaseAfterRenderLivingLabelInferiors, "afterRenderLivingLabel");
        sortBases(beforeRenderModelHookTypes, allBaseBeforeRenderModelSuperiors, allBaseBeforeRenderModelInferiors, "beforeRenderModel");
        sortBases(overrideRenderModelHookTypes, allBaseOverrideRenderModelSuperiors, allBaseOverrideRenderModelInferiors, "overrideRenderModel");
        sortBases(afterRenderModelHookTypes, allBaseAfterRenderModelSuperiors, allBaseAfterRenderModelInferiors, "afterRenderModel");
        sortBases(beforeRenderNameHookTypes, allBaseBeforeRenderNameSuperiors, allBaseBeforeRenderNameInferiors, "beforeRenderName");
        sortBases(overrideRenderNameHookTypes, allBaseOverrideRenderNameSuperiors, allBaseOverrideRenderNameInferiors, "overrideRenderName");
        sortBases(afterRenderNameHookTypes, allBaseAfterRenderNameSuperiors, allBaseAfterRenderNameInferiors, "afterRenderName");
        sortBases(beforeRenderPlayerHookTypes, allBaseBeforeRenderPlayerSuperiors, allBaseBeforeRenderPlayerInferiors, "beforeRenderPlayer");
        sortBases(overrideRenderPlayerHookTypes, allBaseOverrideRenderPlayerSuperiors, allBaseOverrideRenderPlayerInferiors, "overrideRenderPlayer");
        sortBases(afterRenderPlayerHookTypes, allBaseAfterRenderPlayerSuperiors, allBaseAfterRenderPlayerInferiors, "afterRenderPlayer");
        sortBases(beforeRenderPlayerScaleHookTypes, allBaseBeforeRenderPlayerScaleSuperiors, allBaseBeforeRenderPlayerScaleInferiors, "beforeRenderPlayerScale");
        sortBases(overrideRenderPlayerScaleHookTypes, allBaseOverrideRenderPlayerScaleSuperiors, allBaseOverrideRenderPlayerScaleInferiors, "overrideRenderPlayerScale");
        sortBases(afterRenderPlayerScaleHookTypes, allBaseAfterRenderPlayerScaleSuperiors, allBaseAfterRenderPlayerScaleInferiors, "afterRenderPlayerScale");
        sortBases(beforeRenderPlayerSleepHookTypes, allBaseBeforeRenderPlayerSleepSuperiors, allBaseBeforeRenderPlayerSleepInferiors, "beforeRenderPlayerSleep");
        sortBases(overrideRenderPlayerSleepHookTypes, allBaseOverrideRenderPlayerSleepSuperiors, allBaseOverrideRenderPlayerSleepInferiors, "overrideRenderPlayerSleep");
        sortBases(afterRenderPlayerSleepHookTypes, allBaseAfterRenderPlayerSleepSuperiors, allBaseAfterRenderPlayerSleepInferiors, "afterRenderPlayerSleep");
        sortBases(beforeRenderSpecialsHookTypes, allBaseBeforeRenderSpecialsSuperiors, allBaseBeforeRenderSpecialsInferiors, "beforeRenderSpecials");
        sortBases(overrideRenderSpecialsHookTypes, allBaseOverrideRenderSpecialsSuperiors, allBaseOverrideRenderSpecialsInferiors, "overrideRenderSpecials");
        sortBases(afterRenderSpecialsHookTypes, allBaseAfterRenderSpecialsSuperiors, allBaseAfterRenderSpecialsInferiors, "afterRenderSpecials");
        sortBases(beforeRenderSwingProgressHookTypes, allBaseBeforeRenderSwingProgressSuperiors, allBaseBeforeRenderSwingProgressInferiors, "beforeRenderSwingProgress");
        sortBases(overrideRenderSwingProgressHookTypes, allBaseOverrideRenderSwingProgressSuperiors, allBaseOverrideRenderSwingProgressInferiors, "overrideRenderSwingProgress");
        sortBases(afterRenderSwingProgressHookTypes, allBaseAfterRenderSwingProgressSuperiors, allBaseAfterRenderSwingProgressInferiors, "afterRenderSwingProgress");
        sortBases(beforeRotatePlayerHookTypes, allBaseBeforeRotatePlayerSuperiors, allBaseBeforeRotatePlayerInferiors, "beforeRotatePlayer");
        sortBases(overrideRotatePlayerHookTypes, allBaseOverrideRotatePlayerSuperiors, allBaseOverrideRotatePlayerInferiors, "overrideRotatePlayer");
        sortBases(afterRotatePlayerHookTypes, allBaseAfterRotatePlayerSuperiors, allBaseAfterRotatePlayerInferiors, "afterRotatePlayer");
        sortBases(beforeSetArmorModelHookTypes, allBaseBeforeSetArmorModelSuperiors, allBaseBeforeSetArmorModelInferiors, "beforeSetArmorModel");
        sortBases(overrideSetArmorModelHookTypes, allBaseOverrideSetArmorModelSuperiors, allBaseOverrideSetArmorModelInferiors, "overrideSetArmorModel");
        sortBases(afterSetArmorModelHookTypes, allBaseAfterSetArmorModelSuperiors, allBaseAfterSetArmorModelInferiors, "afterSetArmorModel");
        sortBases(beforeSetPassArmorModelHookTypes, allBaseBeforeSetPassArmorModelSuperiors, allBaseBeforeSetPassArmorModelInferiors, "beforeSetPassArmorModel");
        sortBases(overrideSetPassArmorModelHookTypes, allBaseOverrideSetPassArmorModelSuperiors, allBaseOverrideSetPassArmorModelInferiors, "overrideSetPassArmorModel");
        sortBases(afterSetPassArmorModelHookTypes, allBaseAfterSetPassArmorModelSuperiors, allBaseAfterSetPassArmorModelInferiors, "afterSetPassArmorModel");
        sortBases(beforeSetRenderManagerHookTypes, allBaseBeforeSetRenderManagerSuperiors, allBaseBeforeSetRenderManagerInferiors, "beforeSetRenderManager");
        sortBases(overrideSetRenderManagerHookTypes, allBaseOverrideSetRenderManagerSuperiors, allBaseOverrideSetRenderManagerInferiors, "overrideSetRenderManager");
        sortBases(afterSetRenderManagerHookTypes, allBaseAfterSetRenderManagerSuperiors, allBaseAfterSetRenderManagerInferiors, "afterSetRenderManager");
        sortBases(beforeSetRenderPassModelHookTypes, allBaseBeforeSetRenderPassModelSuperiors, allBaseBeforeSetRenderPassModelInferiors, "beforeSetRenderPassModel");
        sortBases(overrideSetRenderPassModelHookTypes, allBaseOverrideSetRenderPassModelSuperiors, allBaseOverrideSetRenderPassModelInferiors, "overrideSetRenderPassModel");
        sortBases(afterSetRenderPassModelHookTypes, allBaseAfterSetRenderPassModelSuperiors, allBaseAfterSetRenderPassModelInferiors, "afterSetRenderPassModel");
        sortBases(beforeRenderSpecialHeadArmorHookTypes, allBaseBeforeRenderSpecialHeadArmorSuperiors, allBaseBeforeRenderSpecialHeadArmorInferiors, "beforeRenderSpecialHeadArmor");
        sortBases(overrideRenderSpecialHeadArmorHookTypes, allBaseOverrideRenderSpecialHeadArmorSuperiors, allBaseOverrideRenderSpecialHeadArmorInferiors, "overrideRenderSpecialHeadArmor");
        sortBases(afterRenderSpecialHeadArmorHookTypes, allBaseAfterRenderSpecialHeadArmorSuperiors, allBaseAfterRenderSpecialHeadArmorInferiors, "afterRenderSpecialHeadArmor");
        sortBases(beforeRenderSpecialHeadEarsHookTypes, allBaseBeforeRenderSpecialHeadEarsSuperiors, allBaseBeforeRenderSpecialHeadEarsInferiors, "beforeRenderSpecialHeadEars");
        sortBases(overrideRenderSpecialHeadEarsHookTypes, allBaseOverrideRenderSpecialHeadEarsSuperiors, allBaseOverrideRenderSpecialHeadEarsInferiors, "overrideRenderSpecialHeadEars");
        sortBases(afterRenderSpecialHeadEarsHookTypes, allBaseAfterRenderSpecialHeadEarsSuperiors, allBaseAfterRenderSpecialHeadEarsInferiors, "afterRenderSpecialHeadEars");
        sortBases(beforeRenderSpecialCloakHookTypes, allBaseBeforeRenderSpecialCloakSuperiors, allBaseBeforeRenderSpecialCloakInferiors, "beforeRenderSpecialCloak");
        sortBases(overrideRenderSpecialCloakHookTypes, allBaseOverrideRenderSpecialCloakSuperiors, allBaseOverrideRenderSpecialCloakInferiors, "overrideRenderSpecialCloak");
        sortBases(afterRenderSpecialCloakHookTypes, allBaseAfterRenderSpecialCloakSuperiors, allBaseAfterRenderSpecialCloakInferiors, "afterRenderSpecialCloak");
        sortBases(beforeRenderSpecialItemInHandHookTypes, allBaseBeforeRenderSpecialItemInHandSuperiors, allBaseBeforeRenderSpecialItemInHandInferiors, "beforeRenderSpecialItemInHand");
        sortBases(overrideRenderSpecialItemInHandHookTypes, allBaseOverrideRenderSpecialItemInHandSuperiors, allBaseOverrideRenderSpecialItemInHandInferiors, "overrideRenderSpecialItemInHand");
        sortBases(afterRenderSpecialItemInHandHookTypes, allBaseAfterRenderSpecialItemInHandSuperiors, allBaseAfterRenderSpecialItemInHandInferiors, "afterRenderSpecialItemInHand");
        sortBases(beforePositionSpecialItemInHandHookTypes, allBaseBeforePositionSpecialItemInHandSuperiors, allBaseBeforePositionSpecialItemInHandInferiors, "beforePositionSpecialItemInHand");
        sortBases(overridePositionSpecialItemInHandHookTypes, allBaseOverridePositionSpecialItemInHandSuperiors, allBaseOverridePositionSpecialItemInHandInferiors, "overridePositionSpecialItemInHand");
        sortBases(afterPositionSpecialItemInHandHookTypes, allBaseAfterPositionSpecialItemInHandSuperiors, allBaseAfterPositionSpecialItemInHandInferiors, "afterPositionSpecialItemInHand");
        initialized = true;
    }

    public static void beforeLocalConstructing(RenderPlayer var0)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.beforeLocalConstructing();
        }
    }

    public static void afterLocalConstructing(RenderPlayer var0)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.afterLocalConstructing();
        }
    }

    private static void sortBases(List var0, Map var1, Map var2, String var3)
    {
        (new RenderPlayerBaseSorter(var0, var1, var2, var3)).Sort();
    }

    private static void sortDynamicBases(Map var0, Map var1, Map var2, String var3)
    {
        List var4 = (List)var0.get(var3);

        if (var4 != null && var4.size() > 1)
        {
            sortBases(var4, getDynamicSorters(var3, var4, var1), getDynamicSorters(var3, var4, var2), var3);
        }
    }

    private static Map getDynamicSorters(String var0, List var1, Map var2)
    {
        HashMap var3 = null;
        Iterator var4 = var1.iterator();

        while (var4.hasNext())
        {
            String var5 = (String)var4.next();
            Map var6 = (Map)var2.get(var5);

            if (var6 != null)
            {
                String[] var7 = (String[])var6.get(var0);

                if (var7 != null && var7.length > 0)
                {
                    if (var3 == null)
                    {
                        var3 = new HashMap(1);
                    }

                    var3.put(var5, var7);
                }
            }
        }

        return (Map)(var3 != null ? var3 : EmptySortMap);
    }

    private RenderPlayerAPI(RenderPlayer var1)
    {
        this.unmodifiableAllBaseIds = Collections.unmodifiableSet(this.allBaseObjects.keySet());
        this.renderPlayer = var1;
        Object[] var2 = new Object[] {this};
        Object[] var3 = new Object[] {this, null};
        Iterator var4 = allBaseConstructors.entrySet().iterator();

        while (var4.hasNext())
        {
            Entry var5 = (Entry)var4.next();
            Constructor var6 = (Constructor)var5.getValue();
            var3[1] = var5.getKey();
            RenderPlayerBase var7;

            try
            {
                if (var6.getParameterTypes().length == 1)
                {
                    var7 = (RenderPlayerBase)var6.newInstance(var2);
                }
                else
                {
                    var7 = (RenderPlayerBase)var6.newInstance(var3);
                }
            }
            catch (Exception var9)
            {
                throw new RuntimeException("Exception while creating a RenderPlayerBase of type \'" + var6.getDeclaringClass() + "\'", var9);
            }

            String var8 = (String)var5.getKey();
            this.allBaseObjects.put(var8, var7);
            this.baseObjectsToId.put(var7, var8);
        }

        this.beforeLocalConstructingHooks = this.create(beforeLocalConstructingHookTypes);
        this.afterLocalConstructingHooks = this.create(afterLocalConstructingHookTypes);
        this.beforeDoRenderShadowAndFireHooks = this.create(beforeDoRenderShadowAndFireHookTypes);
        this.overrideDoRenderShadowAndFireHooks = this.create(overrideDoRenderShadowAndFireHookTypes);
        this.afterDoRenderShadowAndFireHooks = this.create(afterDoRenderShadowAndFireHookTypes);
        this.isDoRenderShadowAndFireModded = this.beforeDoRenderShadowAndFireHooks != null || this.overrideDoRenderShadowAndFireHooks != null || this.afterDoRenderShadowAndFireHooks != null;
        this.beforeDrawFirstPersonHandHooks = this.create(beforeDrawFirstPersonHandHookTypes);
        this.overrideDrawFirstPersonHandHooks = this.create(overrideDrawFirstPersonHandHookTypes);
        this.afterDrawFirstPersonHandHooks = this.create(afterDrawFirstPersonHandHookTypes);
        this.isDrawFirstPersonHandModded = this.beforeDrawFirstPersonHandHooks != null || this.overrideDrawFirstPersonHandHooks != null || this.afterDrawFirstPersonHandHooks != null;
        this.beforeGetColorMultiplierHooks = this.create(beforeGetColorMultiplierHookTypes);
        this.overrideGetColorMultiplierHooks = this.create(overrideGetColorMultiplierHookTypes);
        this.afterGetColorMultiplierHooks = this.create(afterGetColorMultiplierHookTypes);
        this.isGetColorMultiplierModded = this.beforeGetColorMultiplierHooks != null || this.overrideGetColorMultiplierHooks != null || this.afterGetColorMultiplierHooks != null;
        this.beforeGetDeathMaxRotationHooks = this.create(beforeGetDeathMaxRotationHookTypes);
        this.overrideGetDeathMaxRotationHooks = this.create(overrideGetDeathMaxRotationHookTypes);
        this.afterGetDeathMaxRotationHooks = this.create(afterGetDeathMaxRotationHookTypes);
        this.isGetDeathMaxRotationModded = this.beforeGetDeathMaxRotationHooks != null || this.overrideGetDeathMaxRotationHooks != null || this.afterGetDeathMaxRotationHooks != null;
        this.beforeGetFontRendererFromRenderManagerHooks = this.create(beforeGetFontRendererFromRenderManagerHookTypes);
        this.overrideGetFontRendererFromRenderManagerHooks = this.create(overrideGetFontRendererFromRenderManagerHookTypes);
        this.afterGetFontRendererFromRenderManagerHooks = this.create(afterGetFontRendererFromRenderManagerHookTypes);
        this.isGetFontRendererFromRenderManagerModded = this.beforeGetFontRendererFromRenderManagerHooks != null || this.overrideGetFontRendererFromRenderManagerHooks != null || this.afterGetFontRendererFromRenderManagerHooks != null;
        this.beforeHandleRotationFloatHooks = this.create(beforeHandleRotationFloatHookTypes);
        this.overrideHandleRotationFloatHooks = this.create(overrideHandleRotationFloatHookTypes);
        this.afterHandleRotationFloatHooks = this.create(afterHandleRotationFloatHookTypes);
        this.isHandleRotationFloatModded = this.beforeHandleRotationFloatHooks != null || this.overrideHandleRotationFloatHooks != null || this.afterHandleRotationFloatHooks != null;
        this.beforeInheritRenderPassHooks = this.create(beforeInheritRenderPassHookTypes);
        this.overrideInheritRenderPassHooks = this.create(overrideInheritRenderPassHookTypes);
        this.afterInheritRenderPassHooks = this.create(afterInheritRenderPassHookTypes);
        this.isInheritRenderPassModded = this.beforeInheritRenderPassHooks != null || this.overrideInheritRenderPassHooks != null || this.afterInheritRenderPassHooks != null;
        this.beforeLoadDownloadableImageTextureHooks = this.create(beforeLoadDownloadableImageTextureHookTypes);
        this.overrideLoadDownloadableImageTextureHooks = this.create(overrideLoadDownloadableImageTextureHookTypes);
        this.afterLoadDownloadableImageTextureHooks = this.create(afterLoadDownloadableImageTextureHookTypes);
        this.isLoadDownloadableImageTextureModded = this.beforeLoadDownloadableImageTextureHooks != null || this.overrideLoadDownloadableImageTextureHooks != null || this.afterLoadDownloadableImageTextureHooks != null;
        this.beforeLoadTextureHooks = this.create(beforeLoadTextureHookTypes);
        this.overrideLoadTextureHooks = this.create(overrideLoadTextureHookTypes);
        this.afterLoadTextureHooks = this.create(afterLoadTextureHookTypes);
        this.isLoadTextureModded = this.beforeLoadTextureHooks != null || this.overrideLoadTextureHooks != null || this.afterLoadTextureHooks != null;
        this.beforeRenderArrowsHooks = this.create(beforeRenderArrowsHookTypes);
        this.overrideRenderArrowsHooks = this.create(overrideRenderArrowsHookTypes);
        this.afterRenderArrowsHooks = this.create(afterRenderArrowsHookTypes);
        this.isRenderArrowsModded = this.beforeRenderArrowsHooks != null || this.overrideRenderArrowsHooks != null || this.afterRenderArrowsHooks != null;
        this.beforeRenderLivingLabelHooks = this.create(beforeRenderLivingLabelHookTypes);
        this.overrideRenderLivingLabelHooks = this.create(overrideRenderLivingLabelHookTypes);
        this.afterRenderLivingLabelHooks = this.create(afterRenderLivingLabelHookTypes);
        this.isRenderLivingLabelModded = this.beforeRenderLivingLabelHooks != null || this.overrideRenderLivingLabelHooks != null || this.afterRenderLivingLabelHooks != null;
        this.beforeRenderModelHooks = this.create(beforeRenderModelHookTypes);
        this.overrideRenderModelHooks = this.create(overrideRenderModelHookTypes);
        this.afterRenderModelHooks = this.create(afterRenderModelHookTypes);
        this.isRenderModelModded = this.beforeRenderModelHooks != null || this.overrideRenderModelHooks != null || this.afterRenderModelHooks != null;
        this.beforeRenderNameHooks = this.create(beforeRenderNameHookTypes);
        this.overrideRenderNameHooks = this.create(overrideRenderNameHookTypes);
        this.afterRenderNameHooks = this.create(afterRenderNameHookTypes);
        this.isRenderNameModded = this.beforeRenderNameHooks != null || this.overrideRenderNameHooks != null || this.afterRenderNameHooks != null;
        this.beforeRenderPlayerHooks = this.create(beforeRenderPlayerHookTypes);
        this.overrideRenderPlayerHooks = this.create(overrideRenderPlayerHookTypes);
        this.afterRenderPlayerHooks = this.create(afterRenderPlayerHookTypes);
        this.isRenderPlayerModded = this.beforeRenderPlayerHooks != null || this.overrideRenderPlayerHooks != null || this.afterRenderPlayerHooks != null;
        this.beforeRenderPlayerScaleHooks = this.create(beforeRenderPlayerScaleHookTypes);
        this.overrideRenderPlayerScaleHooks = this.create(overrideRenderPlayerScaleHookTypes);
        this.afterRenderPlayerScaleHooks = this.create(afterRenderPlayerScaleHookTypes);
        this.isRenderPlayerScaleModded = this.beforeRenderPlayerScaleHooks != null || this.overrideRenderPlayerScaleHooks != null || this.afterRenderPlayerScaleHooks != null;
        this.beforeRenderPlayerSleepHooks = this.create(beforeRenderPlayerSleepHookTypes);
        this.overrideRenderPlayerSleepHooks = this.create(overrideRenderPlayerSleepHookTypes);
        this.afterRenderPlayerSleepHooks = this.create(afterRenderPlayerSleepHookTypes);
        this.isRenderPlayerSleepModded = this.beforeRenderPlayerSleepHooks != null || this.overrideRenderPlayerSleepHooks != null || this.afterRenderPlayerSleepHooks != null;
        this.beforeRenderSpecialsHooks = this.create(beforeRenderSpecialsHookTypes);
        this.overrideRenderSpecialsHooks = this.create(overrideRenderSpecialsHookTypes);
        this.afterRenderSpecialsHooks = this.create(afterRenderSpecialsHookTypes);
        this.isRenderSpecialsModded = this.beforeRenderSpecialsHooks != null || this.overrideRenderSpecialsHooks != null || this.afterRenderSpecialsHooks != null;
        this.beforeRenderSwingProgressHooks = this.create(beforeRenderSwingProgressHookTypes);
        this.overrideRenderSwingProgressHooks = this.create(overrideRenderSwingProgressHookTypes);
        this.afterRenderSwingProgressHooks = this.create(afterRenderSwingProgressHookTypes);
        this.isRenderSwingProgressModded = this.beforeRenderSwingProgressHooks != null || this.overrideRenderSwingProgressHooks != null || this.afterRenderSwingProgressHooks != null;
        this.beforeRotatePlayerHooks = this.create(beforeRotatePlayerHookTypes);
        this.overrideRotatePlayerHooks = this.create(overrideRotatePlayerHookTypes);
        this.afterRotatePlayerHooks = this.create(afterRotatePlayerHookTypes);
        this.isRotatePlayerModded = this.beforeRotatePlayerHooks != null || this.overrideRotatePlayerHooks != null || this.afterRotatePlayerHooks != null;
        this.beforeSetArmorModelHooks = this.create(beforeSetArmorModelHookTypes);
        this.overrideSetArmorModelHooks = this.create(overrideSetArmorModelHookTypes);
        this.afterSetArmorModelHooks = this.create(afterSetArmorModelHookTypes);
        this.isSetArmorModelModded = this.beforeSetArmorModelHooks != null || this.overrideSetArmorModelHooks != null || this.afterSetArmorModelHooks != null;
        this.beforeSetPassArmorModelHooks = this.create(beforeSetPassArmorModelHookTypes);
        this.overrideSetPassArmorModelHooks = this.create(overrideSetPassArmorModelHookTypes);
        this.afterSetPassArmorModelHooks = this.create(afterSetPassArmorModelHookTypes);
        this.isSetPassArmorModelModded = this.beforeSetPassArmorModelHooks != null || this.overrideSetPassArmorModelHooks != null || this.afterSetPassArmorModelHooks != null;
        this.beforeSetRenderManagerHooks = this.create(beforeSetRenderManagerHookTypes);
        this.overrideSetRenderManagerHooks = this.create(overrideSetRenderManagerHookTypes);
        this.afterSetRenderManagerHooks = this.create(afterSetRenderManagerHookTypes);
        this.isSetRenderManagerModded = this.beforeSetRenderManagerHooks != null || this.overrideSetRenderManagerHooks != null || this.afterSetRenderManagerHooks != null;
        this.beforeSetRenderPassModelHooks = this.create(beforeSetRenderPassModelHookTypes);
        this.overrideSetRenderPassModelHooks = this.create(overrideSetRenderPassModelHookTypes);
        this.afterSetRenderPassModelHooks = this.create(afterSetRenderPassModelHookTypes);
        this.isSetRenderPassModelModded = this.beforeSetRenderPassModelHooks != null || this.overrideSetRenderPassModelHooks != null || this.afterSetRenderPassModelHooks != null;
        this.beforeRenderSpecialHeadArmorHooks = this.create(beforeRenderSpecialHeadArmorHookTypes);
        this.overrideRenderSpecialHeadArmorHooks = this.create(overrideRenderSpecialHeadArmorHookTypes);
        this.afterRenderSpecialHeadArmorHooks = this.create(afterRenderSpecialHeadArmorHookTypes);
        this.isRenderSpecialHeadArmorModded = this.beforeRenderSpecialHeadArmorHooks != null || this.overrideRenderSpecialHeadArmorHooks != null || this.afterRenderSpecialHeadArmorHooks != null;
        this.beforeRenderSpecialHeadEarsHooks = this.create(beforeRenderSpecialHeadEarsHookTypes);
        this.overrideRenderSpecialHeadEarsHooks = this.create(overrideRenderSpecialHeadEarsHookTypes);
        this.afterRenderSpecialHeadEarsHooks = this.create(afterRenderSpecialHeadEarsHookTypes);
        this.isRenderSpecialHeadEarsModded = this.beforeRenderSpecialHeadEarsHooks != null || this.overrideRenderSpecialHeadEarsHooks != null || this.afterRenderSpecialHeadEarsHooks != null;
        this.beforeRenderSpecialCloakHooks = this.create(beforeRenderSpecialCloakHookTypes);
        this.overrideRenderSpecialCloakHooks = this.create(overrideRenderSpecialCloakHookTypes);
        this.afterRenderSpecialCloakHooks = this.create(afterRenderSpecialCloakHookTypes);
        this.isRenderSpecialCloakModded = this.beforeRenderSpecialCloakHooks != null || this.overrideRenderSpecialCloakHooks != null || this.afterRenderSpecialCloakHooks != null;
        this.beforeRenderSpecialItemInHandHooks = this.create(beforeRenderSpecialItemInHandHookTypes);
        this.overrideRenderSpecialItemInHandHooks = this.create(overrideRenderSpecialItemInHandHookTypes);
        this.afterRenderSpecialItemInHandHooks = this.create(afterRenderSpecialItemInHandHookTypes);
        this.isRenderSpecialItemInHandModded = this.beforeRenderSpecialItemInHandHooks != null || this.overrideRenderSpecialItemInHandHooks != null || this.afterRenderSpecialItemInHandHooks != null;
        this.beforePositionSpecialItemInHandHooks = this.create(beforePositionSpecialItemInHandHookTypes);
        this.overridePositionSpecialItemInHandHooks = this.create(overridePositionSpecialItemInHandHookTypes);
        this.afterPositionSpecialItemInHandHooks = this.create(afterPositionSpecialItemInHandHookTypes);
        this.isPositionSpecialItemInHandModded = this.beforePositionSpecialItemInHandHooks != null || this.overridePositionSpecialItemInHandHooks != null || this.afterPositionSpecialItemInHandHooks != null;
    }

    private RenderPlayerBase[] create(List var1)
    {
        if (var1.isEmpty())
        {
            return null;
        }
        else
        {
            RenderPlayerBase[] var2 = new RenderPlayerBase[var1.size()];

            for (int var3 = 0; var3 < var2.length; ++var3)
            {
                var2[var3] = this.getRenderPlayerBase((String)var1.get(var3));
            }

            return var2;
        }
    }

    private void beforeLocalConstructing()
    {
        if (this.beforeLocalConstructingHooks != null)
        {
            for (int var1 = this.beforeLocalConstructingHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeLocalConstructingHooks[var1].beforeLocalConstructing();
            }
        }
    }

    private void afterLocalConstructing()
    {
        if (this.afterLocalConstructingHooks != null)
        {
            for (int var1 = 0; var1 < this.afterLocalConstructingHooks.length; ++var1)
            {
                this.afterLocalConstructingHooks[var1].afterLocalConstructing();
            }
        }
    }

    public RenderPlayerBase getRenderPlayerBase(String var1)
    {
        return (RenderPlayerBase)this.allBaseObjects.get(var1);
    }

    public Set getRenderPlayerBaseIds()
    {
        return this.unmodifiableAllBaseIds;
    }

    public Object dynamic(String var1, Object[] var2)
    {
        var1 = var1.replace('.', '_').replace(' ', '_');
        this.executeAll(var1, var2, beforeDynamicHookTypes, beforeDynamicHookMethods, true);
        Object var3 = this.dynamicOverwritten(var1, var2, (RenderPlayerBase)null);
        this.executeAll(var1, var2, afterDynamicHookTypes, afterDynamicHookMethods, false);
        return var3;
    }

    public Object dynamicOverwritten(String var1, Object[] var2, RenderPlayerBase var3)
    {
        List var4 = (List)overrideDynamicHookTypes.get(var1);
        String var5 = null;

        if (var4 != null)
        {
            if (var3 != null)
            {
                var5 = (String)this.baseObjectsToId.get(var3);
                int var6 = var4.indexOf(var5);

                if (var6 > 0)
                {
                    var5 = (String)var4.get(var6 - 1);
                }
                else
                {
                    var5 = null;
                }
            }
            else if (var4.size() > 0)
            {
                var5 = (String)var4.get(var4.size() - 1);
            }
        }

        Map var9;

        if (var5 == null)
        {
            var5 = (String)keysToVirtualIds.get(var1);

            if (var5 == null)
            {
                return null;
            }

            var9 = virtualDynamicHookMethods;
        }
        else
        {
            var9 = overrideDynamicHookMethods;
        }

        Map var7 = (Map)var9.get(((Constructor)allBaseConstructors.get(var5)).getDeclaringClass());

        if (var7 == null)
        {
            return null;
        }
        else
        {
            Method var8 = (Method)var7.get(var1);
            return var7 == null ? null : this.execute(this.getRenderPlayerBase(var5), var8, var2);
        }
    }

    private void executeAll(String var1, Object[] var2, Map var3, Map var4, boolean var5)
    {
        List var6 = (List)var3.get(var1);

        if (var6 != null)
        {
            int var7 = var5 ? var6.size() - 1 : 0;

            while (true)
            {
                if (var5)
                {
                    if (var7 < 0)
                    {
                        break;
                    }
                }
                else if (var7 >= var6.size())
                {
                    break;
                }

                String var8 = (String)var6.get(var7);
                RenderPlayerBase var9 = this.getRenderPlayerBase(var8);
                Class var10 = var9.getClass();
                Map var11 = (Map)var4.get(var10);

                if (var11 != null)
                {
                    Method var12 = (Method)var11.get(var1);

                    if (var12 != null)
                    {
                        this.execute(var9, var12, var2);
                    }
                }

                var7 += var5 ? -1 : 1;
            }
        }
    }

    private Object execute(RenderPlayerBase var1, Method var2, Object[] var3)
    {
        try
        {
            return var2.invoke(var1, var3);
        }
        catch (Exception var5)
        {
            throw new RuntimeException("Exception while invoking dynamic method", var5);
        }
    }

    public static void doRenderShadowAndFire(RenderPlayer var0, Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
        else
        {
            var0.localDoRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
    }

    private void doRenderShadowAndFire(Entity var1, double var2, double var4, double var6, float var8, float var9)
    {
        int var10;

        if (this.beforeDoRenderShadowAndFireHooks != null)
        {
            for (var10 = this.beforeDoRenderShadowAndFireHooks.length - 1; var10 >= 0; --var10)
            {
                this.beforeDoRenderShadowAndFireHooks[var10].beforeDoRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
            }
        }

        if (this.overrideDoRenderShadowAndFireHooks != null)
        {
            this.overrideDoRenderShadowAndFireHooks[this.overrideDoRenderShadowAndFireHooks.length - 1].doRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }
        else
        {
            this.renderPlayer.localDoRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
        }

        if (this.afterDoRenderShadowAndFireHooks != null)
        {
            for (var10 = 0; var10 < this.afterDoRenderShadowAndFireHooks.length; ++var10)
            {
                this.afterDoRenderShadowAndFireHooks[var10].afterDoRenderShadowAndFire(var1, var2, var4, var6, var8, var9);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenDoRenderShadowAndFire(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDoRenderShadowAndFireHooks.length; ++var2)
        {
            if (this.overrideDoRenderShadowAndFireHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDoRenderShadowAndFireHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void drawFirstPersonHand(RenderPlayer var0, EntityPlayer var1)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.drawFirstPersonHand(var1);
        }
        else
        {
            var0.localDrawFirstPersonHand(var1);
        }
    }

    private void drawFirstPersonHand(EntityPlayer var1)
    {
        int var2;

        if (this.beforeDrawFirstPersonHandHooks != null)
        {
            for (var2 = this.beforeDrawFirstPersonHandHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeDrawFirstPersonHandHooks[var2].beforeDrawFirstPersonHand(var1);
            }
        }

        if (this.overrideDrawFirstPersonHandHooks != null)
        {
            this.overrideDrawFirstPersonHandHooks[this.overrideDrawFirstPersonHandHooks.length - 1].drawFirstPersonHand(var1);
        }
        else
        {
            this.renderPlayer.localDrawFirstPersonHand(var1);
        }

        if (this.afterDrawFirstPersonHandHooks != null)
        {
            for (var2 = 0; var2 < this.afterDrawFirstPersonHandHooks.length; ++var2)
            {
                this.afterDrawFirstPersonHandHooks[var2].afterDrawFirstPersonHand(var1);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenDrawFirstPersonHand(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideDrawFirstPersonHandHooks.length; ++var2)
        {
            if (this.overrideDrawFirstPersonHandHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideDrawFirstPersonHandHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int getColorMultiplier(RenderPlayer var0, EntityLiving var1, float var2, float var3)
    {
        int var4;

        if (var0.renderPlayerAPI != null)
        {
            var4 = var0.renderPlayerAPI.getColorMultiplier(var1, var2, var3);
        }
        else
        {
            var4 = var0.localGetColorMultiplier(var1, var2, var3);
        }

        return var4;
    }

    private int getColorMultiplier(EntityLiving var1, float var2, float var3)
    {
        int var4;

        if (this.beforeGetColorMultiplierHooks != null)
        {
            for (var4 = this.beforeGetColorMultiplierHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeGetColorMultiplierHooks[var4].beforeGetColorMultiplier(var1, var2, var3);
            }
        }

        if (this.overrideGetColorMultiplierHooks != null)
        {
            var4 = this.overrideGetColorMultiplierHooks[this.overrideGetColorMultiplierHooks.length - 1].getColorMultiplier(var1, var2, var3);
        }
        else
        {
            var4 = this.renderPlayer.localGetColorMultiplier(var1, var2, var3);
        }

        if (this.afterGetColorMultiplierHooks != null)
        {
            for (int var5 = 0; var5 < this.afterGetColorMultiplierHooks.length; ++var5)
            {
                this.afterGetColorMultiplierHooks[var5].afterGetColorMultiplier(var1, var2, var3);
            }
        }

        return var4;
    }

    protected RenderPlayerBase GetOverwrittenGetColorMultiplier(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetColorMultiplierHooks.length; ++var2)
        {
            if (this.overrideGetColorMultiplierHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetColorMultiplierHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float getDeathMaxRotation(RenderPlayer var0, EntityLiving var1)
    {
        float var2;

        if (var0.renderPlayerAPI != null)
        {
            var2 = var0.renderPlayerAPI.getDeathMaxRotation(var1);
        }
        else
        {
            var2 = var0.localGetDeathMaxRotation(var1);
        }

        return var2;
    }

    private float getDeathMaxRotation(EntityLiving var1)
    {
        if (this.beforeGetDeathMaxRotationHooks != null)
        {
            for (int var2 = this.beforeGetDeathMaxRotationHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeGetDeathMaxRotationHooks[var2].beforeGetDeathMaxRotation(var1);
            }
        }

        float var4;

        if (this.overrideGetDeathMaxRotationHooks != null)
        {
            var4 = this.overrideGetDeathMaxRotationHooks[this.overrideGetDeathMaxRotationHooks.length - 1].getDeathMaxRotation(var1);
        }
        else
        {
            var4 = this.renderPlayer.localGetDeathMaxRotation(var1);
        }

        if (this.afterGetDeathMaxRotationHooks != null)
        {
            for (int var3 = 0; var3 < this.afterGetDeathMaxRotationHooks.length; ++var3)
            {
                this.afterGetDeathMaxRotationHooks[var3].afterGetDeathMaxRotation(var1);
            }
        }

        return var4;
    }

    protected RenderPlayerBase GetOverwrittenGetDeathMaxRotation(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetDeathMaxRotationHooks.length; ++var2)
        {
            if (this.overrideGetDeathMaxRotationHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetDeathMaxRotationHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static FontRenderer getFontRendererFromRenderManager(RenderPlayer var0)
    {
        FontRenderer var1;

        if (var0.renderPlayerAPI != null)
        {
            var1 = var0.renderPlayerAPI.getFontRendererFromRenderManager();
        }
        else
        {
            var1 = var0.localGetFontRendererFromRenderManager();
        }

        return var1;
    }

    private FontRenderer getFontRendererFromRenderManager()
    {
        if (this.beforeGetFontRendererFromRenderManagerHooks != null)
        {
            for (int var1 = this.beforeGetFontRendererFromRenderManagerHooks.length - 1; var1 >= 0; --var1)
            {
                this.beforeGetFontRendererFromRenderManagerHooks[var1].beforeGetFontRendererFromRenderManager();
            }
        }

        FontRenderer var3;

        if (this.overrideGetFontRendererFromRenderManagerHooks != null)
        {
            var3 = this.overrideGetFontRendererFromRenderManagerHooks[this.overrideGetFontRendererFromRenderManagerHooks.length - 1].getFontRendererFromRenderManager();
        }
        else
        {
            var3 = this.renderPlayer.localGetFontRendererFromRenderManager();
        }

        if (this.afterGetFontRendererFromRenderManagerHooks != null)
        {
            for (int var2 = 0; var2 < this.afterGetFontRendererFromRenderManagerHooks.length; ++var2)
            {
                this.afterGetFontRendererFromRenderManagerHooks[var2].afterGetFontRendererFromRenderManager();
            }
        }

        return var3;
    }

    protected RenderPlayerBase GetOverwrittenGetFontRendererFromRenderManager(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideGetFontRendererFromRenderManagerHooks.length; ++var2)
        {
            if (this.overrideGetFontRendererFromRenderManagerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideGetFontRendererFromRenderManagerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float handleRotationFloat(RenderPlayer var0, EntityLiving var1, float var2)
    {
        float var3;

        if (var0.renderPlayerAPI != null)
        {
            var3 = var0.renderPlayerAPI.handleRotationFloat(var1, var2);
        }
        else
        {
            var3 = var0.localHandleRotationFloat(var1, var2);
        }

        return var3;
    }

    private float handleRotationFloat(EntityLiving var1, float var2)
    {
        if (this.beforeHandleRotationFloatHooks != null)
        {
            for (int var3 = this.beforeHandleRotationFloatHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeHandleRotationFloatHooks[var3].beforeHandleRotationFloat(var1, var2);
            }
        }

        float var5;

        if (this.overrideHandleRotationFloatHooks != null)
        {
            var5 = this.overrideHandleRotationFloatHooks[this.overrideHandleRotationFloatHooks.length - 1].handleRotationFloat(var1, var2);
        }
        else
        {
            var5 = this.renderPlayer.localHandleRotationFloat(var1, var2);
        }

        if (this.afterHandleRotationFloatHooks != null)
        {
            for (int var4 = 0; var4 < this.afterHandleRotationFloatHooks.length; ++var4)
            {
                this.afterHandleRotationFloatHooks[var4].afterHandleRotationFloat(var1, var2);
            }
        }

        return var5;
    }

    protected RenderPlayerBase GetOverwrittenHandleRotationFloat(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideHandleRotationFloatHooks.length; ++var2)
        {
            if (this.overrideHandleRotationFloatHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideHandleRotationFloatHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int inheritRenderPass(RenderPlayer var0, EntityLiving var1, int var2, float var3)
    {
        int var4;

        if (var0.renderPlayerAPI != null)
        {
            var4 = var0.renderPlayerAPI.inheritRenderPass(var1, var2, var3);
        }
        else
        {
            var4 = var0.localInheritRenderPass(var1, var2, var3);
        }

        return var4;
    }

    private int inheritRenderPass(EntityLiving var1, int var2, float var3)
    {
        int var4;

        if (this.beforeInheritRenderPassHooks != null)
        {
            for (var4 = this.beforeInheritRenderPassHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeInheritRenderPassHooks[var4].beforeInheritRenderPass(var1, var2, var3);
            }
        }

        if (this.overrideInheritRenderPassHooks != null)
        {
            var4 = this.overrideInheritRenderPassHooks[this.overrideInheritRenderPassHooks.length - 1].inheritRenderPass(var1, var2, var3);
        }
        else
        {
            var4 = this.renderPlayer.localInheritRenderPass(var1, var2, var3);
        }

        if (this.afterInheritRenderPassHooks != null)
        {
            for (int var5 = 0; var5 < this.afterInheritRenderPassHooks.length; ++var5)
            {
                this.afterInheritRenderPassHooks[var5].afterInheritRenderPass(var1, var2, var3);
            }
        }

        return var4;
    }

    protected RenderPlayerBase GetOverwrittenInheritRenderPass(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideInheritRenderPassHooks.length; ++var2)
        {
            if (this.overrideInheritRenderPassHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideInheritRenderPassHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static boolean loadDownloadableImageTexture(RenderPlayer var0, String var1, String var2)
    {
        boolean var3;

        if (var0.renderPlayerAPI != null)
        {
            var3 = var0.renderPlayerAPI.loadDownloadableImageTexture(var1, var2);
        }
        else
        {
            var3 = var0.localLoadDownloadableImageTexture(var1, var2);
        }

        return var3;
    }

    private boolean loadDownloadableImageTexture(String var1, String var2)
    {
        if (this.beforeLoadDownloadableImageTextureHooks != null)
        {
            for (int var3 = this.beforeLoadDownloadableImageTextureHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeLoadDownloadableImageTextureHooks[var3].beforeLoadDownloadableImageTexture(var1, var2);
            }
        }

        boolean var5;

        if (this.overrideLoadDownloadableImageTextureHooks != null)
        {
            var5 = this.overrideLoadDownloadableImageTextureHooks[this.overrideLoadDownloadableImageTextureHooks.length - 1].loadDownloadableImageTexture(var1, var2);
        }
        else
        {
            var5 = this.renderPlayer.localLoadDownloadableImageTexture(var1, var2);
        }

        if (this.afterLoadDownloadableImageTextureHooks != null)
        {
            for (int var4 = 0; var4 < this.afterLoadDownloadableImageTextureHooks.length; ++var4)
            {
                this.afterLoadDownloadableImageTextureHooks[var4].afterLoadDownloadableImageTexture(var1, var2);
            }
        }

        return var5;
    }

    protected RenderPlayerBase GetOverwrittenLoadDownloadableImageTexture(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideLoadDownloadableImageTextureHooks.length; ++var2)
        {
            if (this.overrideLoadDownloadableImageTextureHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideLoadDownloadableImageTextureHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void loadTexture(RenderPlayer var0, String var1)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.loadTexture(var1);
        }
        else
        {
            var0.localLoadTexture(var1);
        }
    }

    private void loadTexture(String var1)
    {
        int var2;

        if (this.beforeLoadTextureHooks != null)
        {
            for (var2 = this.beforeLoadTextureHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeLoadTextureHooks[var2].beforeLoadTexture(var1);
            }
        }

        if (this.overrideLoadTextureHooks != null)
        {
            this.overrideLoadTextureHooks[this.overrideLoadTextureHooks.length - 1].loadTexture(var1);
        }
        else
        {
            this.renderPlayer.localLoadTexture(var1);
        }

        if (this.afterLoadTextureHooks != null)
        {
            for (var2 = 0; var2 < this.afterLoadTextureHooks.length; ++var2)
            {
                this.afterLoadTextureHooks[var2].afterLoadTexture(var1);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenLoadTexture(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideLoadTextureHooks.length; ++var2)
        {
            if (this.overrideLoadTextureHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideLoadTextureHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderArrows(RenderPlayer var0, EntityLiving var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderArrows(var1, var2);
        }
        else
        {
            var0.localRenderArrows(var1, var2);
        }
    }

    private void renderArrows(EntityLiving var1, float var2)
    {
        int var3;

        if (this.beforeRenderArrowsHooks != null)
        {
            for (var3 = this.beforeRenderArrowsHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderArrowsHooks[var3].beforeRenderArrows(var1, var2);
            }
        }

        if (this.overrideRenderArrowsHooks != null)
        {
            this.overrideRenderArrowsHooks[this.overrideRenderArrowsHooks.length - 1].renderArrows(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderArrows(var1, var2);
        }

        if (this.afterRenderArrowsHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderArrowsHooks.length; ++var3)
            {
                this.afterRenderArrowsHooks[var3].afterRenderArrows(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderArrows(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderArrowsHooks.length; ++var2)
        {
            if (this.overrideRenderArrowsHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderArrowsHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderLivingLabel(RenderPlayer var0, EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
        else
        {
            var0.localRenderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
    }

    private void renderLivingLabel(EntityLiving var1, String var2, double var3, double var5, double var7, int var9)
    {
        int var10;

        if (this.beforeRenderLivingLabelHooks != null)
        {
            for (var10 = this.beforeRenderLivingLabelHooks.length - 1; var10 >= 0; --var10)
            {
                this.beforeRenderLivingLabelHooks[var10].beforeRenderLivingLabel(var1, var2, var3, var5, var7, var9);
            }
        }

        if (this.overrideRenderLivingLabelHooks != null)
        {
            this.overrideRenderLivingLabelHooks[this.overrideRenderLivingLabelHooks.length - 1].renderLivingLabel(var1, var2, var3, var5, var7, var9);
        }
        else
        {
            this.renderPlayer.localRenderLivingLabel(var1, var2, var3, var5, var7, var9);
        }

        if (this.afterRenderLivingLabelHooks != null)
        {
            for (var10 = 0; var10 < this.afterRenderLivingLabelHooks.length; ++var10)
            {
                this.afterRenderLivingLabelHooks[var10].afterRenderLivingLabel(var1, var2, var3, var5, var7, var9);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderLivingLabel(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderLivingLabelHooks.length; ++var2)
        {
            if (this.overrideRenderLivingLabelHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderLivingLabelHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderModel(RenderPlayer var0, EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderModel(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            var0.localRenderModel(var1, var2, var3, var4, var5, var6, var7);
        }
    }

    private void renderModel(EntityLiving var1, float var2, float var3, float var4, float var5, float var6, float var7)
    {
        int var8;

        if (this.beforeRenderModelHooks != null)
        {
            for (var8 = this.beforeRenderModelHooks.length - 1; var8 >= 0; --var8)
            {
                this.beforeRenderModelHooks[var8].beforeRenderModel(var1, var2, var3, var4, var5, var6, var7);
            }
        }

        if (this.overrideRenderModelHooks != null)
        {
            this.overrideRenderModelHooks[this.overrideRenderModelHooks.length - 1].renderModel(var1, var2, var3, var4, var5, var6, var7);
        }
        else
        {
            this.renderPlayer.localRenderModel(var1, var2, var3, var4, var5, var6, var7);
        }

        if (this.afterRenderModelHooks != null)
        {
            for (var8 = 0; var8 < this.afterRenderModelHooks.length; ++var8)
            {
                this.afterRenderModelHooks[var8].afterRenderModel(var1, var2, var3, var4, var5, var6, var7);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderModel(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderModelHooks.length; ++var2)
        {
            if (this.overrideRenderModelHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderModelHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderName(RenderPlayer var0, EntityPlayer var1, double var2, double var4, double var6)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderName(var1, var2, var4, var6);
        }
        else
        {
            var0.localRenderName(var1, var2, var4, var6);
        }
    }

    private void renderName(EntityPlayer var1, double var2, double var4, double var6)
    {
        int var8;

        if (this.beforeRenderNameHooks != null)
        {
            for (var8 = this.beforeRenderNameHooks.length - 1; var8 >= 0; --var8)
            {
                this.beforeRenderNameHooks[var8].beforeRenderName(var1, var2, var4, var6);
            }
        }

        if (this.overrideRenderNameHooks != null)
        {
            this.overrideRenderNameHooks[this.overrideRenderNameHooks.length - 1].renderName(var1, var2, var4, var6);
        }
        else
        {
            this.renderPlayer.localRenderName(var1, var2, var4, var6);
        }

        if (this.afterRenderNameHooks != null)
        {
            for (var8 = 0; var8 < this.afterRenderNameHooks.length; ++var8)
            {
                this.afterRenderNameHooks[var8].afterRenderName(var1, var2, var4, var6);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderName(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderNameHooks.length; ++var2)
        {
            if (this.overrideRenderNameHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderNameHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderPlayer(RenderPlayer var0, EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderPlayer(var1, var2, var4, var6, var8, var9);
        }
        else
        {
            var0.localRenderPlayer(var1, var2, var4, var6, var8, var9);
        }
    }

    private void renderPlayer(EntityPlayer var1, double var2, double var4, double var6, float var8, float var9)
    {
        int var10;

        if (this.beforeRenderPlayerHooks != null)
        {
            for (var10 = this.beforeRenderPlayerHooks.length - 1; var10 >= 0; --var10)
            {
                this.beforeRenderPlayerHooks[var10].beforeRenderPlayer(var1, var2, var4, var6, var8, var9);
            }
        }

        if (this.overrideRenderPlayerHooks != null)
        {
            this.overrideRenderPlayerHooks[this.overrideRenderPlayerHooks.length - 1].renderPlayer(var1, var2, var4, var6, var8, var9);
        }
        else
        {
            this.renderPlayer.localRenderPlayer(var1, var2, var4, var6, var8, var9);
        }

        if (this.afterRenderPlayerHooks != null)
        {
            for (var10 = 0; var10 < this.afterRenderPlayerHooks.length; ++var10)
            {
                this.afterRenderPlayerHooks[var10].afterRenderPlayer(var1, var2, var4, var6, var8, var9);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayer(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderPlayerHooks.length; ++var2)
        {
            if (this.overrideRenderPlayerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderPlayerScale(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderPlayerScale(var1, var2);
        }
        else
        {
            var0.localRenderPlayerScale(var1, var2);
        }
    }

    private void renderPlayerScale(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderPlayerScaleHooks != null)
        {
            for (var3 = this.beforeRenderPlayerScaleHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderPlayerScaleHooks[var3].beforeRenderPlayerScale(var1, var2);
            }
        }

        if (this.overrideRenderPlayerScaleHooks != null)
        {
            this.overrideRenderPlayerScaleHooks[this.overrideRenderPlayerScaleHooks.length - 1].renderPlayerScale(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderPlayerScale(var1, var2);
        }

        if (this.afterRenderPlayerScaleHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderPlayerScaleHooks.length; ++var3)
            {
                this.afterRenderPlayerScaleHooks[var3].afterRenderPlayerScale(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayerScale(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderPlayerScaleHooks.length; ++var2)
        {
            if (this.overrideRenderPlayerScaleHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerScaleHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderPlayerSleep(RenderPlayer var0, EntityPlayer var1, double var2, double var4, double var6)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderPlayerSleep(var1, var2, var4, var6);
        }
        else
        {
            var0.localRenderPlayerSleep(var1, var2, var4, var6);
        }
    }

    private void renderPlayerSleep(EntityPlayer var1, double var2, double var4, double var6)
    {
        int var8;

        if (this.beforeRenderPlayerSleepHooks != null)
        {
            for (var8 = this.beforeRenderPlayerSleepHooks.length - 1; var8 >= 0; --var8)
            {
                this.beforeRenderPlayerSleepHooks[var8].beforeRenderPlayerSleep(var1, var2, var4, var6);
            }
        }

        if (this.overrideRenderPlayerSleepHooks != null)
        {
            this.overrideRenderPlayerSleepHooks[this.overrideRenderPlayerSleepHooks.length - 1].renderPlayerSleep(var1, var2, var4, var6);
        }
        else
        {
            this.renderPlayer.localRenderPlayerSleep(var1, var2, var4, var6);
        }

        if (this.afterRenderPlayerSleepHooks != null)
        {
            for (var8 = 0; var8 < this.afterRenderPlayerSleepHooks.length; ++var8)
            {
                this.afterRenderPlayerSleepHooks[var8].afterRenderPlayerSleep(var1, var2, var4, var6);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderPlayerSleep(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderPlayerSleepHooks.length; ++var2)
        {
            if (this.overrideRenderPlayerSleepHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderPlayerSleepHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderSpecials(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderSpecials(var1, var2);
        }
        else
        {
            var0.localRenderSpecials(var1, var2);
        }
    }

    private void renderSpecials(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderSpecialsHooks != null)
        {
            for (var3 = this.beforeRenderSpecialsHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSpecialsHooks[var3].beforeRenderSpecials(var1, var2);
            }
        }

        if (this.overrideRenderSpecialsHooks != null)
        {
            this.overrideRenderSpecialsHooks[this.overrideRenderSpecialsHooks.length - 1].renderSpecials(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderSpecials(var1, var2);
        }

        if (this.afterRenderSpecialsHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderSpecialsHooks.length; ++var3)
            {
                this.afterRenderSpecialsHooks[var3].afterRenderSpecials(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecials(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSpecialsHooks.length; ++var2)
        {
            if (this.overrideRenderSpecialsHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialsHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static float renderSwingProgress(RenderPlayer var0, EntityLiving var1, float var2)
    {
        float var3;

        if (var0.renderPlayerAPI != null)
        {
            var3 = var0.renderPlayerAPI.renderSwingProgress(var1, var2);
        }
        else
        {
            var3 = var0.localRenderSwingProgress(var1, var2);
        }

        return var3;
    }

    private float renderSwingProgress(EntityLiving var1, float var2)
    {
        if (this.beforeRenderSwingProgressHooks != null)
        {
            for (int var3 = this.beforeRenderSwingProgressHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSwingProgressHooks[var3].beforeRenderSwingProgress(var1, var2);
            }
        }

        float var5;

        if (this.overrideRenderSwingProgressHooks != null)
        {
            var5 = this.overrideRenderSwingProgressHooks[this.overrideRenderSwingProgressHooks.length - 1].renderSwingProgress(var1, var2);
        }
        else
        {
            var5 = this.renderPlayer.localRenderSwingProgress(var1, var2);
        }

        if (this.afterRenderSwingProgressHooks != null)
        {
            for (int var4 = 0; var4 < this.afterRenderSwingProgressHooks.length; ++var4)
            {
                this.afterRenderSwingProgressHooks[var4].afterRenderSwingProgress(var1, var2);
            }
        }

        return var5;
    }

    protected RenderPlayerBase GetOverwrittenRenderSwingProgress(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSwingProgressHooks.length; ++var2)
        {
            if (this.overrideRenderSwingProgressHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSwingProgressHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void rotatePlayer(RenderPlayer var0, EntityPlayer var1, float var2, float var3, float var4)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.rotatePlayer(var1, var2, var3, var4);
        }
        else
        {
            var0.localRotatePlayer(var1, var2, var3, var4);
        }
    }

    private void rotatePlayer(EntityPlayer var1, float var2, float var3, float var4)
    {
        int var5;

        if (this.beforeRotatePlayerHooks != null)
        {
            for (var5 = this.beforeRotatePlayerHooks.length - 1; var5 >= 0; --var5)
            {
                this.beforeRotatePlayerHooks[var5].beforeRotatePlayer(var1, var2, var3, var4);
            }
        }

        if (this.overrideRotatePlayerHooks != null)
        {
            this.overrideRotatePlayerHooks[this.overrideRotatePlayerHooks.length - 1].rotatePlayer(var1, var2, var3, var4);
        }
        else
        {
            this.renderPlayer.localRotatePlayer(var1, var2, var3, var4);
        }

        if (this.afterRotatePlayerHooks != null)
        {
            for (var5 = 0; var5 < this.afterRotatePlayerHooks.length; ++var5)
            {
                this.afterRotatePlayerHooks[var5].afterRotatePlayer(var1, var2, var3, var4);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRotatePlayer(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRotatePlayerHooks.length; ++var2)
        {
            if (this.overrideRotatePlayerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRotatePlayerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static int setArmorModel(RenderPlayer var0, EntityPlayer var1, int var2, float var3)
    {
        int var4;

        if (var0.renderPlayerAPI != null)
        {
            var4 = var0.renderPlayerAPI.setArmorModel(var1, var2, var3);
        }
        else
        {
            var4 = var0.localSetArmorModel(var1, var2, var3);
        }

        return var4;
    }

    private int setArmorModel(EntityPlayer var1, int var2, float var3)
    {
        int var4;

        if (this.beforeSetArmorModelHooks != null)
        {
            for (var4 = this.beforeSetArmorModelHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeSetArmorModelHooks[var4].beforeSetArmorModel(var1, var2, var3);
            }
        }

        if (this.overrideSetArmorModelHooks != null)
        {
            var4 = this.overrideSetArmorModelHooks[this.overrideSetArmorModelHooks.length - 1].setArmorModel(var1, var2, var3);
        }
        else
        {
            var4 = this.renderPlayer.localSetArmorModel(var1, var2, var3);
        }

        if (this.afterSetArmorModelHooks != null)
        {
            for (int var5 = 0; var5 < this.afterSetArmorModelHooks.length; ++var5)
            {
                this.afterSetArmorModelHooks[var5].afterSetArmorModel(var1, var2, var3);
            }
        }

        return var4;
    }

    protected RenderPlayerBase GetOverwrittenSetArmorModel(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetArmorModelHooks.length; ++var2)
        {
            if (this.overrideSetArmorModelHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetArmorModelHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setPassArmorModel(RenderPlayer var0, EntityPlayer var1, int var2, float var3)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.setPassArmorModel(var1, var2, var3);
        }
        else
        {
            var0.localSetPassArmorModel(var1, var2, var3);
        }
    }

    private void setPassArmorModel(EntityPlayer var1, int var2, float var3)
    {
        int var4;

        if (this.beforeSetPassArmorModelHooks != null)
        {
            for (var4 = this.beforeSetPassArmorModelHooks.length - 1; var4 >= 0; --var4)
            {
                this.beforeSetPassArmorModelHooks[var4].beforeSetPassArmorModel(var1, var2, var3);
            }
        }

        if (this.overrideSetPassArmorModelHooks != null)
        {
            this.overrideSetPassArmorModelHooks[this.overrideSetPassArmorModelHooks.length - 1].setPassArmorModel(var1, var2, var3);
        }
        else
        {
            this.renderPlayer.localSetPassArmorModel(var1, var2, var3);
        }

        if (this.afterSetPassArmorModelHooks != null)
        {
            for (var4 = 0; var4 < this.afterSetPassArmorModelHooks.length; ++var4)
            {
                this.afterSetPassArmorModelHooks[var4].afterSetPassArmorModel(var1, var2, var3);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetPassArmorModel(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetPassArmorModelHooks.length; ++var2)
        {
            if (this.overrideSetPassArmorModelHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetPassArmorModelHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setRenderManager(RenderPlayer var0, RenderManager var1)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.setRenderManager(var1);
        }
        else
        {
            var0.localSetRenderManager(var1);
        }
    }

    private void setRenderManager(RenderManager var1)
    {
        int var2;

        if (this.beforeSetRenderManagerHooks != null)
        {
            for (var2 = this.beforeSetRenderManagerHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeSetRenderManagerHooks[var2].beforeSetRenderManager(var1);
            }
        }

        if (this.overrideSetRenderManagerHooks != null)
        {
            this.overrideSetRenderManagerHooks[this.overrideSetRenderManagerHooks.length - 1].setRenderManager(var1);
        }
        else
        {
            this.renderPlayer.localSetRenderManager(var1);
        }

        if (this.afterSetRenderManagerHooks != null)
        {
            for (var2 = 0; var2 < this.afterSetRenderManagerHooks.length; ++var2)
            {
                this.afterSetRenderManagerHooks[var2].afterSetRenderManager(var1);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetRenderManager(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetRenderManagerHooks.length; ++var2)
        {
            if (this.overrideSetRenderManagerHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetRenderManagerHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void setRenderPassModel(RenderPlayer var0, ModelBase var1)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.setRenderPassModel(var1);
        }
        else
        {
            var0.localSetRenderPassModel(var1);
        }
    }

    private void setRenderPassModel(ModelBase var1)
    {
        int var2;

        if (this.beforeSetRenderPassModelHooks != null)
        {
            for (var2 = this.beforeSetRenderPassModelHooks.length - 1; var2 >= 0; --var2)
            {
                this.beforeSetRenderPassModelHooks[var2].beforeSetRenderPassModel(var1);
            }
        }

        if (this.overrideSetRenderPassModelHooks != null)
        {
            this.overrideSetRenderPassModelHooks[this.overrideSetRenderPassModelHooks.length - 1].setRenderPassModel(var1);
        }
        else
        {
            this.renderPlayer.localSetRenderPassModel(var1);
        }

        if (this.afterSetRenderPassModelHooks != null)
        {
            for (var2 = 0; var2 < this.afterSetRenderPassModelHooks.length; ++var2)
            {
                this.afterSetRenderPassModelHooks[var2].afterSetRenderPassModel(var1);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenSetRenderPassModel(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideSetRenderPassModelHooks.length; ++var2)
        {
            if (this.overrideSetRenderPassModelHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideSetRenderPassModelHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderSpecialHeadArmor(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderSpecialHeadArmor(var1, var2);
        }
        else
        {
            var0.localRenderSpecialHeadArmor(var1, var2);
        }
    }

    private void renderSpecialHeadArmor(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderSpecialHeadArmorHooks != null)
        {
            for (var3 = this.beforeRenderSpecialHeadArmorHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSpecialHeadArmorHooks[var3].beforeRenderSpecialHeadArmor(var1, var2);
            }
        }

        if (this.overrideRenderSpecialHeadArmorHooks != null)
        {
            this.overrideRenderSpecialHeadArmorHooks[this.overrideRenderSpecialHeadArmorHooks.length - 1].renderSpecialHeadArmor(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderSpecialHeadArmor(var1, var2);
        }

        if (this.afterRenderSpecialHeadArmorHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderSpecialHeadArmorHooks.length; ++var3)
            {
                this.afterRenderSpecialHeadArmorHooks[var3].afterRenderSpecialHeadArmor(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialHeadArmor(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSpecialHeadArmorHooks.length; ++var2)
        {
            if (this.overrideRenderSpecialHeadArmorHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialHeadArmorHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderSpecialHeadEars(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderSpecialHeadEars(var1, var2);
        }
        else
        {
            var0.localRenderSpecialHeadEars(var1, var2);
        }
    }

    private void renderSpecialHeadEars(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderSpecialHeadEarsHooks != null)
        {
            for (var3 = this.beforeRenderSpecialHeadEarsHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSpecialHeadEarsHooks[var3].beforeRenderSpecialHeadEars(var1, var2);
            }
        }

        if (this.overrideRenderSpecialHeadEarsHooks != null)
        {
            this.overrideRenderSpecialHeadEarsHooks[this.overrideRenderSpecialHeadEarsHooks.length - 1].renderSpecialHeadEars(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderSpecialHeadEars(var1, var2);
        }

        if (this.afterRenderSpecialHeadEarsHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderSpecialHeadEarsHooks.length; ++var3)
            {
                this.afterRenderSpecialHeadEarsHooks[var3].afterRenderSpecialHeadEars(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialHeadEars(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSpecialHeadEarsHooks.length; ++var2)
        {
            if (this.overrideRenderSpecialHeadEarsHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialHeadEarsHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderSpecialCloak(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderSpecialCloak(var1, var2);
        }
        else
        {
            var0.localRenderSpecialCloak(var1, var2);
        }
    }

    private void renderSpecialCloak(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderSpecialCloakHooks != null)
        {
            for (var3 = this.beforeRenderSpecialCloakHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSpecialCloakHooks[var3].beforeRenderSpecialCloak(var1, var2);
            }
        }

        if (this.overrideRenderSpecialCloakHooks != null)
        {
            this.overrideRenderSpecialCloakHooks[this.overrideRenderSpecialCloakHooks.length - 1].renderSpecialCloak(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderSpecialCloak(var1, var2);
        }

        if (this.afterRenderSpecialCloakHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderSpecialCloakHooks.length; ++var3)
            {
                this.afterRenderSpecialCloakHooks[var3].afterRenderSpecialCloak(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialCloak(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSpecialCloakHooks.length; ++var2)
        {
            if (this.overrideRenderSpecialCloakHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialCloakHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void renderSpecialItemInHand(RenderPlayer var0, EntityPlayer var1, float var2)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.renderSpecialItemInHand(var1, var2);
        }
        else
        {
            var0.localRenderSpecialItemInHand(var1, var2);
        }
    }

    private void renderSpecialItemInHand(EntityPlayer var1, float var2)
    {
        int var3;

        if (this.beforeRenderSpecialItemInHandHooks != null)
        {
            for (var3 = this.beforeRenderSpecialItemInHandHooks.length - 1; var3 >= 0; --var3)
            {
                this.beforeRenderSpecialItemInHandHooks[var3].beforeRenderSpecialItemInHand(var1, var2);
            }
        }

        if (this.overrideRenderSpecialItemInHandHooks != null)
        {
            this.overrideRenderSpecialItemInHandHooks[this.overrideRenderSpecialItemInHandHooks.length - 1].renderSpecialItemInHand(var1, var2);
        }
        else
        {
            this.renderPlayer.localRenderSpecialItemInHand(var1, var2);
        }

        if (this.afterRenderSpecialItemInHandHooks != null)
        {
            for (var3 = 0; var3 < this.afterRenderSpecialItemInHandHooks.length; ++var3)
            {
                this.afterRenderSpecialItemInHandHooks[var3].afterRenderSpecialItemInHand(var1, var2);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenRenderSpecialItemInHand(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overrideRenderSpecialItemInHandHooks.length; ++var2)
        {
            if (this.overrideRenderSpecialItemInHandHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overrideRenderSpecialItemInHandHooks[var2 - 1];
            }
        }

        return var1;
    }

    public static void positionSpecialItemInHand(RenderPlayer var0, EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        if (var0.renderPlayerAPI != null)
        {
            var0.renderPlayerAPI.positionSpecialItemInHand(var1, var2, var3, var4);
        }
        else
        {
            var0.localPositionSpecialItemInHand(var1, var2, var3, var4);
        }
    }

    private void positionSpecialItemInHand(EntityPlayer var1, float var2, EnumAction var3, ItemStack var4)
    {
        int var5;

        if (this.beforePositionSpecialItemInHandHooks != null)
        {
            for (var5 = this.beforePositionSpecialItemInHandHooks.length - 1; var5 >= 0; --var5)
            {
                this.beforePositionSpecialItemInHandHooks[var5].beforePositionSpecialItemInHand(var1, var2, var3, var4);
            }
        }

        if (this.overridePositionSpecialItemInHandHooks != null)
        {
            this.overridePositionSpecialItemInHandHooks[this.overridePositionSpecialItemInHandHooks.length - 1].positionSpecialItemInHand(var1, var2, var3, var4);
        }
        else
        {
            this.renderPlayer.localPositionSpecialItemInHand(var1, var2, var3, var4);
        }

        if (this.afterPositionSpecialItemInHandHooks != null)
        {
            for (var5 = 0; var5 < this.afterPositionSpecialItemInHandHooks.length; ++var5)
            {
                this.afterPositionSpecialItemInHandHooks[var5].afterPositionSpecialItemInHand(var1, var2, var3, var4);
            }
        }
    }

    protected RenderPlayerBase GetOverwrittenPositionSpecialItemInHand(RenderPlayerBase var1)
    {
        for (int var2 = 0; var2 < this.overridePositionSpecialItemInHandHooks.length; ++var2)
        {
            if (this.overridePositionSpecialItemInHandHooks[var2] == var1)
            {
                if (var2 == 0)
                {
                    return null;
                }

                return this.overridePositionSpecialItemInHandHooks[var2 - 1];
            }
        }

        return var1;
    }
}
