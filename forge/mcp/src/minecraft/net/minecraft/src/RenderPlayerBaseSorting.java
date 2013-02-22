package net.minecraft.src;

import java.util.HashMap;
import java.util.Map;

public final class RenderPlayerBaseSorting
{
    private String[] beforeLocalConstructingSuperiors = null;
    private String[] beforeLocalConstructingInferiors = null;
    private String[] afterLocalConstructingSuperiors = null;
    private String[] afterLocalConstructingInferiors = null;
    private Map dynamicBeforeSuperiors = null;
    private Map dynamicBeforeInferiors = null;
    private Map dynamicOverrideSuperiors = null;
    private Map dynamicOverrideInferiors = null;
    private Map dynamicAfterSuperiors = null;
    private Map dynamicAfterInferiors = null;
    private String[] beforeDoRenderShadowAndFireSuperiors = null;
    private String[] beforeDoRenderShadowAndFireInferiors = null;
    private String[] overrideDoRenderShadowAndFireSuperiors = null;
    private String[] overrideDoRenderShadowAndFireInferiors = null;
    private String[] afterDoRenderShadowAndFireSuperiors = null;
    private String[] afterDoRenderShadowAndFireInferiors = null;
    private String[] beforeDrawFirstPersonHandSuperiors = null;
    private String[] beforeDrawFirstPersonHandInferiors = null;
    private String[] overrideDrawFirstPersonHandSuperiors = null;
    private String[] overrideDrawFirstPersonHandInferiors = null;
    private String[] afterDrawFirstPersonHandSuperiors = null;
    private String[] afterDrawFirstPersonHandInferiors = null;
    private String[] beforeGetColorMultiplierSuperiors = null;
    private String[] beforeGetColorMultiplierInferiors = null;
    private String[] overrideGetColorMultiplierSuperiors = null;
    private String[] overrideGetColorMultiplierInferiors = null;
    private String[] afterGetColorMultiplierSuperiors = null;
    private String[] afterGetColorMultiplierInferiors = null;
    private String[] beforeGetDeathMaxRotationSuperiors = null;
    private String[] beforeGetDeathMaxRotationInferiors = null;
    private String[] overrideGetDeathMaxRotationSuperiors = null;
    private String[] overrideGetDeathMaxRotationInferiors = null;
    private String[] afterGetDeathMaxRotationSuperiors = null;
    private String[] afterGetDeathMaxRotationInferiors = null;
    private String[] beforeGetFontRendererFromRenderManagerSuperiors = null;
    private String[] beforeGetFontRendererFromRenderManagerInferiors = null;
    private String[] overrideGetFontRendererFromRenderManagerSuperiors = null;
    private String[] overrideGetFontRendererFromRenderManagerInferiors = null;
    private String[] afterGetFontRendererFromRenderManagerSuperiors = null;
    private String[] afterGetFontRendererFromRenderManagerInferiors = null;
    private String[] beforeHandleRotationFloatSuperiors = null;
    private String[] beforeHandleRotationFloatInferiors = null;
    private String[] overrideHandleRotationFloatSuperiors = null;
    private String[] overrideHandleRotationFloatInferiors = null;
    private String[] afterHandleRotationFloatSuperiors = null;
    private String[] afterHandleRotationFloatInferiors = null;
    private String[] beforeInheritRenderPassSuperiors = null;
    private String[] beforeInheritRenderPassInferiors = null;
    private String[] overrideInheritRenderPassSuperiors = null;
    private String[] overrideInheritRenderPassInferiors = null;
    private String[] afterInheritRenderPassSuperiors = null;
    private String[] afterInheritRenderPassInferiors = null;
    private String[] beforeLoadDownloadableImageTextureSuperiors = null;
    private String[] beforeLoadDownloadableImageTextureInferiors = null;
    private String[] overrideLoadDownloadableImageTextureSuperiors = null;
    private String[] overrideLoadDownloadableImageTextureInferiors = null;
    private String[] afterLoadDownloadableImageTextureSuperiors = null;
    private String[] afterLoadDownloadableImageTextureInferiors = null;
    private String[] beforeLoadTextureSuperiors = null;
    private String[] beforeLoadTextureInferiors = null;
    private String[] overrideLoadTextureSuperiors = null;
    private String[] overrideLoadTextureInferiors = null;
    private String[] afterLoadTextureSuperiors = null;
    private String[] afterLoadTextureInferiors = null;
    private String[] beforeRenderArrowsSuperiors = null;
    private String[] beforeRenderArrowsInferiors = null;
    private String[] overrideRenderArrowsSuperiors = null;
    private String[] overrideRenderArrowsInferiors = null;
    private String[] afterRenderArrowsSuperiors = null;
    private String[] afterRenderArrowsInferiors = null;
    private String[] beforeRenderLivingLabelSuperiors = null;
    private String[] beforeRenderLivingLabelInferiors = null;
    private String[] overrideRenderLivingLabelSuperiors = null;
    private String[] overrideRenderLivingLabelInferiors = null;
    private String[] afterRenderLivingLabelSuperiors = null;
    private String[] afterRenderLivingLabelInferiors = null;
    private String[] beforeRenderModelSuperiors = null;
    private String[] beforeRenderModelInferiors = null;
    private String[] overrideRenderModelSuperiors = null;
    private String[] overrideRenderModelInferiors = null;
    private String[] afterRenderModelSuperiors = null;
    private String[] afterRenderModelInferiors = null;
    private String[] beforeRenderNameSuperiors = null;
    private String[] beforeRenderNameInferiors = null;
    private String[] overrideRenderNameSuperiors = null;
    private String[] overrideRenderNameInferiors = null;
    private String[] afterRenderNameSuperiors = null;
    private String[] afterRenderNameInferiors = null;
    private String[] beforeRenderPlayerSuperiors = null;
    private String[] beforeRenderPlayerInferiors = null;
    private String[] overrideRenderPlayerSuperiors = null;
    private String[] overrideRenderPlayerInferiors = null;
    private String[] afterRenderPlayerSuperiors = null;
    private String[] afterRenderPlayerInferiors = null;
    private String[] beforeRenderPlayerScaleSuperiors = null;
    private String[] beforeRenderPlayerScaleInferiors = null;
    private String[] overrideRenderPlayerScaleSuperiors = null;
    private String[] overrideRenderPlayerScaleInferiors = null;
    private String[] afterRenderPlayerScaleSuperiors = null;
    private String[] afterRenderPlayerScaleInferiors = null;
    private String[] beforeRenderPlayerSleepSuperiors = null;
    private String[] beforeRenderPlayerSleepInferiors = null;
    private String[] overrideRenderPlayerSleepSuperiors = null;
    private String[] overrideRenderPlayerSleepInferiors = null;
    private String[] afterRenderPlayerSleepSuperiors = null;
    private String[] afterRenderPlayerSleepInferiors = null;
    private String[] beforeRenderSpecialsSuperiors = null;
    private String[] beforeRenderSpecialsInferiors = null;
    private String[] overrideRenderSpecialsSuperiors = null;
    private String[] overrideRenderSpecialsInferiors = null;
    private String[] afterRenderSpecialsSuperiors = null;
    private String[] afterRenderSpecialsInferiors = null;
    private String[] beforeRenderSwingProgressSuperiors = null;
    private String[] beforeRenderSwingProgressInferiors = null;
    private String[] overrideRenderSwingProgressSuperiors = null;
    private String[] overrideRenderSwingProgressInferiors = null;
    private String[] afterRenderSwingProgressSuperiors = null;
    private String[] afterRenderSwingProgressInferiors = null;
    private String[] beforeRotatePlayerSuperiors = null;
    private String[] beforeRotatePlayerInferiors = null;
    private String[] overrideRotatePlayerSuperiors = null;
    private String[] overrideRotatePlayerInferiors = null;
    private String[] afterRotatePlayerSuperiors = null;
    private String[] afterRotatePlayerInferiors = null;
    private String[] beforeSetArmorModelSuperiors = null;
    private String[] beforeSetArmorModelInferiors = null;
    private String[] overrideSetArmorModelSuperiors = null;
    private String[] overrideSetArmorModelInferiors = null;
    private String[] afterSetArmorModelSuperiors = null;
    private String[] afterSetArmorModelInferiors = null;
    private String[] beforeSetPassArmorModelSuperiors = null;
    private String[] beforeSetPassArmorModelInferiors = null;
    private String[] overrideSetPassArmorModelSuperiors = null;
    private String[] overrideSetPassArmorModelInferiors = null;
    private String[] afterSetPassArmorModelSuperiors = null;
    private String[] afterSetPassArmorModelInferiors = null;
    private String[] beforeSetRenderManagerSuperiors = null;
    private String[] beforeSetRenderManagerInferiors = null;
    private String[] overrideSetRenderManagerSuperiors = null;
    private String[] overrideSetRenderManagerInferiors = null;
    private String[] afterSetRenderManagerSuperiors = null;
    private String[] afterSetRenderManagerInferiors = null;
    private String[] beforeSetRenderPassModelSuperiors = null;
    private String[] beforeSetRenderPassModelInferiors = null;
    private String[] overrideSetRenderPassModelSuperiors = null;
    private String[] overrideSetRenderPassModelInferiors = null;
    private String[] afterSetRenderPassModelSuperiors = null;
    private String[] afterSetRenderPassModelInferiors = null;
    private String[] beforeRenderSpecialHeadArmorSuperiors = null;
    private String[] beforeRenderSpecialHeadArmorInferiors = null;
    private String[] overrideRenderSpecialHeadArmorSuperiors = null;
    private String[] overrideRenderSpecialHeadArmorInferiors = null;
    private String[] afterRenderSpecialHeadArmorSuperiors = null;
    private String[] afterRenderSpecialHeadArmorInferiors = null;
    private String[] beforeRenderSpecialHeadEarsSuperiors = null;
    private String[] beforeRenderSpecialHeadEarsInferiors = null;
    private String[] overrideRenderSpecialHeadEarsSuperiors = null;
    private String[] overrideRenderSpecialHeadEarsInferiors = null;
    private String[] afterRenderSpecialHeadEarsSuperiors = null;
    private String[] afterRenderSpecialHeadEarsInferiors = null;
    private String[] beforeRenderSpecialCloakSuperiors = null;
    private String[] beforeRenderSpecialCloakInferiors = null;
    private String[] overrideRenderSpecialCloakSuperiors = null;
    private String[] overrideRenderSpecialCloakInferiors = null;
    private String[] afterRenderSpecialCloakSuperiors = null;
    private String[] afterRenderSpecialCloakInferiors = null;
    private String[] beforeRenderSpecialItemInHandSuperiors = null;
    private String[] beforeRenderSpecialItemInHandInferiors = null;
    private String[] overrideRenderSpecialItemInHandSuperiors = null;
    private String[] overrideRenderSpecialItemInHandInferiors = null;
    private String[] afterRenderSpecialItemInHandSuperiors = null;
    private String[] afterRenderSpecialItemInHandInferiors = null;
    private String[] beforePositionSpecialItemInHandSuperiors = null;
    private String[] beforePositionSpecialItemInHandInferiors = null;
    private String[] overridePositionSpecialItemInHandSuperiors = null;
    private String[] overridePositionSpecialItemInHandInferiors = null;
    private String[] afterPositionSpecialItemInHandSuperiors = null;
    private String[] afterPositionSpecialItemInHandInferiors = null;

    public String[] getBeforeLocalConstructingSuperiors()
    {
        return this.beforeLocalConstructingSuperiors;
    }

    public String[] getBeforeLocalConstructingInferiors()
    {
        return this.beforeLocalConstructingInferiors;
    }

    public String[] getAfterLocalConstructingSuperiors()
    {
        return this.afterLocalConstructingSuperiors;
    }

    public String[] getAfterLocalConstructingInferiors()
    {
        return this.afterLocalConstructingInferiors;
    }

    public void setBeforeLocalConstructingSuperiors(String[] var1)
    {
        this.beforeLocalConstructingSuperiors = var1;
    }

    public void setBeforeLocalConstructingInferiors(String[] var1)
    {
        this.beforeLocalConstructingInferiors = var1;
    }

    public void setAfterLocalConstructingSuperiors(String[] var1)
    {
        this.afterLocalConstructingSuperiors = var1;
    }

    public void setAfterLocalConstructingInferiors(String[] var1)
    {
        this.afterLocalConstructingInferiors = var1;
    }

    public Map getDynamicBeforeSuperiors()
    {
        return this.dynamicBeforeSuperiors;
    }

    public Map getDynamicBeforeInferiors()
    {
        return this.dynamicBeforeInferiors;
    }

    public Map getDynamicOverrideSuperiors()
    {
        return this.dynamicOverrideSuperiors;
    }

    public Map getDynamicOverrideInferiors()
    {
        return this.dynamicOverrideInferiors;
    }

    public Map getDynamicAfterSuperiors()
    {
        return this.dynamicAfterSuperiors;
    }

    public Map getDynamicAfterInferiors()
    {
        return this.dynamicAfterInferiors;
    }

    public void setDynamicBeforeSuperiors(String var1, String[] var2)
    {
        this.dynamicBeforeSuperiors = this.setDynamic(var1, var2, this.dynamicBeforeSuperiors);
    }

    public void setDynamicBeforeInferiors(String var1, String[] var2)
    {
        this.dynamicBeforeInferiors = this.setDynamic(var1, var2, this.dynamicBeforeInferiors);
    }

    public void setDynamicOverrideSuperiors(String var1, String[] var2)
    {
        this.dynamicOverrideSuperiors = this.setDynamic(var1, var2, this.dynamicOverrideSuperiors);
    }

    public void setDynamicOverrideInferiors(String var1, String[] var2)
    {
        this.dynamicOverrideInferiors = this.setDynamic(var1, var2, this.dynamicOverrideInferiors);
    }

    public void setDynamicAfterSuperiors(String var1, String[] var2)
    {
        this.dynamicAfterSuperiors = this.setDynamic(var1, var2, this.dynamicAfterSuperiors);
    }

    public void setDynamicAfterInferiors(String var1, String[] var2)
    {
        this.dynamicAfterInferiors = this.setDynamic(var1, var2, this.dynamicAfterInferiors);
    }

    private Map setDynamic(String var1, String[] var2, Map var3)
    {
        if (var1 == null)
        {
            throw new IllegalArgumentException("Parameter \'name\' may not be null");
        }
        else if (var2 == null)
        {
            if (var3 != null)
            {
                ((Map)var3).remove(var1);
            }

            return (Map)var3;
        }
        else
        {
            if (var3 == null)
            {
                var3 = new HashMap();
            }

            ((Map)var3).put(var1, var2);
            return (Map)var3;
        }
    }

    public String[] getBeforeDoRenderShadowAndFireSuperiors()
    {
        return this.beforeDoRenderShadowAndFireSuperiors;
    }

    public String[] getBeforeDoRenderShadowAndFireInferiors()
    {
        return this.beforeDoRenderShadowAndFireInferiors;
    }

    public String[] getOverrideDoRenderShadowAndFireSuperiors()
    {
        return this.overrideDoRenderShadowAndFireSuperiors;
    }

    public String[] getOverrideDoRenderShadowAndFireInferiors()
    {
        return this.overrideDoRenderShadowAndFireInferiors;
    }

    public String[] getAfterDoRenderShadowAndFireSuperiors()
    {
        return this.afterDoRenderShadowAndFireSuperiors;
    }

    public String[] getAfterDoRenderShadowAndFireInferiors()
    {
        return this.afterDoRenderShadowAndFireInferiors;
    }

    public void setBeforeDoRenderShadowAndFireSuperiors(String[] var1)
    {
        this.beforeDoRenderShadowAndFireSuperiors = var1;
    }

    public void setBeforeDoRenderShadowAndFireInferiors(String[] var1)
    {
        this.beforeDoRenderShadowAndFireInferiors = var1;
    }

    public void setOverrideDoRenderShadowAndFireSuperiors(String[] var1)
    {
        this.overrideDoRenderShadowAndFireSuperiors = var1;
    }

    public void setOverrideDoRenderShadowAndFireInferiors(String[] var1)
    {
        this.overrideDoRenderShadowAndFireInferiors = var1;
    }

    public void setAfterDoRenderShadowAndFireSuperiors(String[] var1)
    {
        this.afterDoRenderShadowAndFireSuperiors = var1;
    }

    public void setAfterDoRenderShadowAndFireInferiors(String[] var1)
    {
        this.afterDoRenderShadowAndFireInferiors = var1;
    }

    public String[] getBeforeDrawFirstPersonHandSuperiors()
    {
        return this.beforeDrawFirstPersonHandSuperiors;
    }

    public String[] getBeforeDrawFirstPersonHandInferiors()
    {
        return this.beforeDrawFirstPersonHandInferiors;
    }

    public String[] getOverrideDrawFirstPersonHandSuperiors()
    {
        return this.overrideDrawFirstPersonHandSuperiors;
    }

    public String[] getOverrideDrawFirstPersonHandInferiors()
    {
        return this.overrideDrawFirstPersonHandInferiors;
    }

    public String[] getAfterDrawFirstPersonHandSuperiors()
    {
        return this.afterDrawFirstPersonHandSuperiors;
    }

    public String[] getAfterDrawFirstPersonHandInferiors()
    {
        return this.afterDrawFirstPersonHandInferiors;
    }

    public void setBeforeDrawFirstPersonHandSuperiors(String[] var1)
    {
        this.beforeDrawFirstPersonHandSuperiors = var1;
    }

    public void setBeforeDrawFirstPersonHandInferiors(String[] var1)
    {
        this.beforeDrawFirstPersonHandInferiors = var1;
    }

    public void setOverrideDrawFirstPersonHandSuperiors(String[] var1)
    {
        this.overrideDrawFirstPersonHandSuperiors = var1;
    }

    public void setOverrideDrawFirstPersonHandInferiors(String[] var1)
    {
        this.overrideDrawFirstPersonHandInferiors = var1;
    }

    public void setAfterDrawFirstPersonHandSuperiors(String[] var1)
    {
        this.afterDrawFirstPersonHandSuperiors = var1;
    }

    public void setAfterDrawFirstPersonHandInferiors(String[] var1)
    {
        this.afterDrawFirstPersonHandInferiors = var1;
    }

    public String[] getBeforeGetColorMultiplierSuperiors()
    {
        return this.beforeGetColorMultiplierSuperiors;
    }

    public String[] getBeforeGetColorMultiplierInferiors()
    {
        return this.beforeGetColorMultiplierInferiors;
    }

    public String[] getOverrideGetColorMultiplierSuperiors()
    {
        return this.overrideGetColorMultiplierSuperiors;
    }

    public String[] getOverrideGetColorMultiplierInferiors()
    {
        return this.overrideGetColorMultiplierInferiors;
    }

    public String[] getAfterGetColorMultiplierSuperiors()
    {
        return this.afterGetColorMultiplierSuperiors;
    }

    public String[] getAfterGetColorMultiplierInferiors()
    {
        return this.afterGetColorMultiplierInferiors;
    }

    public void setBeforeGetColorMultiplierSuperiors(String[] var1)
    {
        this.beforeGetColorMultiplierSuperiors = var1;
    }

    public void setBeforeGetColorMultiplierInferiors(String[] var1)
    {
        this.beforeGetColorMultiplierInferiors = var1;
    }

    public void setOverrideGetColorMultiplierSuperiors(String[] var1)
    {
        this.overrideGetColorMultiplierSuperiors = var1;
    }

    public void setOverrideGetColorMultiplierInferiors(String[] var1)
    {
        this.overrideGetColorMultiplierInferiors = var1;
    }

    public void setAfterGetColorMultiplierSuperiors(String[] var1)
    {
        this.afterGetColorMultiplierSuperiors = var1;
    }

    public void setAfterGetColorMultiplierInferiors(String[] var1)
    {
        this.afterGetColorMultiplierInferiors = var1;
    }

    public String[] getBeforeGetDeathMaxRotationSuperiors()
    {
        return this.beforeGetDeathMaxRotationSuperiors;
    }

    public String[] getBeforeGetDeathMaxRotationInferiors()
    {
        return this.beforeGetDeathMaxRotationInferiors;
    }

    public String[] getOverrideGetDeathMaxRotationSuperiors()
    {
        return this.overrideGetDeathMaxRotationSuperiors;
    }

    public String[] getOverrideGetDeathMaxRotationInferiors()
    {
        return this.overrideGetDeathMaxRotationInferiors;
    }

    public String[] getAfterGetDeathMaxRotationSuperiors()
    {
        return this.afterGetDeathMaxRotationSuperiors;
    }

    public String[] getAfterGetDeathMaxRotationInferiors()
    {
        return this.afterGetDeathMaxRotationInferiors;
    }

    public void setBeforeGetDeathMaxRotationSuperiors(String[] var1)
    {
        this.beforeGetDeathMaxRotationSuperiors = var1;
    }

    public void setBeforeGetDeathMaxRotationInferiors(String[] var1)
    {
        this.beforeGetDeathMaxRotationInferiors = var1;
    }

    public void setOverrideGetDeathMaxRotationSuperiors(String[] var1)
    {
        this.overrideGetDeathMaxRotationSuperiors = var1;
    }

    public void setOverrideGetDeathMaxRotationInferiors(String[] var1)
    {
        this.overrideGetDeathMaxRotationInferiors = var1;
    }

    public void setAfterGetDeathMaxRotationSuperiors(String[] var1)
    {
        this.afterGetDeathMaxRotationSuperiors = var1;
    }

    public void setAfterGetDeathMaxRotationInferiors(String[] var1)
    {
        this.afterGetDeathMaxRotationInferiors = var1;
    }

    public String[] getBeforeGetFontRendererFromRenderManagerSuperiors()
    {
        return this.beforeGetFontRendererFromRenderManagerSuperiors;
    }

    public String[] getBeforeGetFontRendererFromRenderManagerInferiors()
    {
        return this.beforeGetFontRendererFromRenderManagerInferiors;
    }

    public String[] getOverrideGetFontRendererFromRenderManagerSuperiors()
    {
        return this.overrideGetFontRendererFromRenderManagerSuperiors;
    }

    public String[] getOverrideGetFontRendererFromRenderManagerInferiors()
    {
        return this.overrideGetFontRendererFromRenderManagerInferiors;
    }

    public String[] getAfterGetFontRendererFromRenderManagerSuperiors()
    {
        return this.afterGetFontRendererFromRenderManagerSuperiors;
    }

    public String[] getAfterGetFontRendererFromRenderManagerInferiors()
    {
        return this.afterGetFontRendererFromRenderManagerInferiors;
    }

    public void setBeforeGetFontRendererFromRenderManagerSuperiors(String[] var1)
    {
        this.beforeGetFontRendererFromRenderManagerSuperiors = var1;
    }

    public void setBeforeGetFontRendererFromRenderManagerInferiors(String[] var1)
    {
        this.beforeGetFontRendererFromRenderManagerInferiors = var1;
    }

    public void setOverrideGetFontRendererFromRenderManagerSuperiors(String[] var1)
    {
        this.overrideGetFontRendererFromRenderManagerSuperiors = var1;
    }

    public void setOverrideGetFontRendererFromRenderManagerInferiors(String[] var1)
    {
        this.overrideGetFontRendererFromRenderManagerInferiors = var1;
    }

    public void setAfterGetFontRendererFromRenderManagerSuperiors(String[] var1)
    {
        this.afterGetFontRendererFromRenderManagerSuperiors = var1;
    }

    public void setAfterGetFontRendererFromRenderManagerInferiors(String[] var1)
    {
        this.afterGetFontRendererFromRenderManagerInferiors = var1;
    }

    public String[] getBeforeHandleRotationFloatSuperiors()
    {
        return this.beforeHandleRotationFloatSuperiors;
    }

    public String[] getBeforeHandleRotationFloatInferiors()
    {
        return this.beforeHandleRotationFloatInferiors;
    }

    public String[] getOverrideHandleRotationFloatSuperiors()
    {
        return this.overrideHandleRotationFloatSuperiors;
    }

    public String[] getOverrideHandleRotationFloatInferiors()
    {
        return this.overrideHandleRotationFloatInferiors;
    }

    public String[] getAfterHandleRotationFloatSuperiors()
    {
        return this.afterHandleRotationFloatSuperiors;
    }

    public String[] getAfterHandleRotationFloatInferiors()
    {
        return this.afterHandleRotationFloatInferiors;
    }

    public void setBeforeHandleRotationFloatSuperiors(String[] var1)
    {
        this.beforeHandleRotationFloatSuperiors = var1;
    }

    public void setBeforeHandleRotationFloatInferiors(String[] var1)
    {
        this.beforeHandleRotationFloatInferiors = var1;
    }

    public void setOverrideHandleRotationFloatSuperiors(String[] var1)
    {
        this.overrideHandleRotationFloatSuperiors = var1;
    }

    public void setOverrideHandleRotationFloatInferiors(String[] var1)
    {
        this.overrideHandleRotationFloatInferiors = var1;
    }

    public void setAfterHandleRotationFloatSuperiors(String[] var1)
    {
        this.afterHandleRotationFloatSuperiors = var1;
    }

    public void setAfterHandleRotationFloatInferiors(String[] var1)
    {
        this.afterHandleRotationFloatInferiors = var1;
    }

    public String[] getBeforeInheritRenderPassSuperiors()
    {
        return this.beforeInheritRenderPassSuperiors;
    }

    public String[] getBeforeInheritRenderPassInferiors()
    {
        return this.beforeInheritRenderPassInferiors;
    }

    public String[] getOverrideInheritRenderPassSuperiors()
    {
        return this.overrideInheritRenderPassSuperiors;
    }

    public String[] getOverrideInheritRenderPassInferiors()
    {
        return this.overrideInheritRenderPassInferiors;
    }

    public String[] getAfterInheritRenderPassSuperiors()
    {
        return this.afterInheritRenderPassSuperiors;
    }

    public String[] getAfterInheritRenderPassInferiors()
    {
        return this.afterInheritRenderPassInferiors;
    }

    public void setBeforeInheritRenderPassSuperiors(String[] var1)
    {
        this.beforeInheritRenderPassSuperiors = var1;
    }

    public void setBeforeInheritRenderPassInferiors(String[] var1)
    {
        this.beforeInheritRenderPassInferiors = var1;
    }

    public void setOverrideInheritRenderPassSuperiors(String[] var1)
    {
        this.overrideInheritRenderPassSuperiors = var1;
    }

    public void setOverrideInheritRenderPassInferiors(String[] var1)
    {
        this.overrideInheritRenderPassInferiors = var1;
    }

    public void setAfterInheritRenderPassSuperiors(String[] var1)
    {
        this.afterInheritRenderPassSuperiors = var1;
    }

    public void setAfterInheritRenderPassInferiors(String[] var1)
    {
        this.afterInheritRenderPassInferiors = var1;
    }

    public String[] getBeforeLoadDownloadableImageTextureSuperiors()
    {
        return this.beforeLoadDownloadableImageTextureSuperiors;
    }

    public String[] getBeforeLoadDownloadableImageTextureInferiors()
    {
        return this.beforeLoadDownloadableImageTextureInferiors;
    }

    public String[] getOverrideLoadDownloadableImageTextureSuperiors()
    {
        return this.overrideLoadDownloadableImageTextureSuperiors;
    }

    public String[] getOverrideLoadDownloadableImageTextureInferiors()
    {
        return this.overrideLoadDownloadableImageTextureInferiors;
    }

    public String[] getAfterLoadDownloadableImageTextureSuperiors()
    {
        return this.afterLoadDownloadableImageTextureSuperiors;
    }

    public String[] getAfterLoadDownloadableImageTextureInferiors()
    {
        return this.afterLoadDownloadableImageTextureInferiors;
    }

    public void setBeforeLoadDownloadableImageTextureSuperiors(String[] var1)
    {
        this.beforeLoadDownloadableImageTextureSuperiors = var1;
    }

    public void setBeforeLoadDownloadableImageTextureInferiors(String[] var1)
    {
        this.beforeLoadDownloadableImageTextureInferiors = var1;
    }

    public void setOverrideLoadDownloadableImageTextureSuperiors(String[] var1)
    {
        this.overrideLoadDownloadableImageTextureSuperiors = var1;
    }

    public void setOverrideLoadDownloadableImageTextureInferiors(String[] var1)
    {
        this.overrideLoadDownloadableImageTextureInferiors = var1;
    }

    public void setAfterLoadDownloadableImageTextureSuperiors(String[] var1)
    {
        this.afterLoadDownloadableImageTextureSuperiors = var1;
    }

    public void setAfterLoadDownloadableImageTextureInferiors(String[] var1)
    {
        this.afterLoadDownloadableImageTextureInferiors = var1;
    }

    public String[] getBeforeLoadTextureSuperiors()
    {
        return this.beforeLoadTextureSuperiors;
    }

    public String[] getBeforeLoadTextureInferiors()
    {
        return this.beforeLoadTextureInferiors;
    }

    public String[] getOverrideLoadTextureSuperiors()
    {
        return this.overrideLoadTextureSuperiors;
    }

    public String[] getOverrideLoadTextureInferiors()
    {
        return this.overrideLoadTextureInferiors;
    }

    public String[] getAfterLoadTextureSuperiors()
    {
        return this.afterLoadTextureSuperiors;
    }

    public String[] getAfterLoadTextureInferiors()
    {
        return this.afterLoadTextureInferiors;
    }

    public void setBeforeLoadTextureSuperiors(String[] var1)
    {
        this.beforeLoadTextureSuperiors = var1;
    }

    public void setBeforeLoadTextureInferiors(String[] var1)
    {
        this.beforeLoadTextureInferiors = var1;
    }

    public void setOverrideLoadTextureSuperiors(String[] var1)
    {
        this.overrideLoadTextureSuperiors = var1;
    }

    public void setOverrideLoadTextureInferiors(String[] var1)
    {
        this.overrideLoadTextureInferiors = var1;
    }

    public void setAfterLoadTextureSuperiors(String[] var1)
    {
        this.afterLoadTextureSuperiors = var1;
    }

    public void setAfterLoadTextureInferiors(String[] var1)
    {
        this.afterLoadTextureInferiors = var1;
    }

    public String[] getBeforeRenderArrowsSuperiors()
    {
        return this.beforeRenderArrowsSuperiors;
    }

    public String[] getBeforeRenderArrowsInferiors()
    {
        return this.beforeRenderArrowsInferiors;
    }

    public String[] getOverrideRenderArrowsSuperiors()
    {
        return this.overrideRenderArrowsSuperiors;
    }

    public String[] getOverrideRenderArrowsInferiors()
    {
        return this.overrideRenderArrowsInferiors;
    }

    public String[] getAfterRenderArrowsSuperiors()
    {
        return this.afterRenderArrowsSuperiors;
    }

    public String[] getAfterRenderArrowsInferiors()
    {
        return this.afterRenderArrowsInferiors;
    }

    public void setBeforeRenderArrowsSuperiors(String[] var1)
    {
        this.beforeRenderArrowsSuperiors = var1;
    }

    public void setBeforeRenderArrowsInferiors(String[] var1)
    {
        this.beforeRenderArrowsInferiors = var1;
    }

    public void setOverrideRenderArrowsSuperiors(String[] var1)
    {
        this.overrideRenderArrowsSuperiors = var1;
    }

    public void setOverrideRenderArrowsInferiors(String[] var1)
    {
        this.overrideRenderArrowsInferiors = var1;
    }

    public void setAfterRenderArrowsSuperiors(String[] var1)
    {
        this.afterRenderArrowsSuperiors = var1;
    }

    public void setAfterRenderArrowsInferiors(String[] var1)
    {
        this.afterRenderArrowsInferiors = var1;
    }

    public String[] getBeforeRenderLivingLabelSuperiors()
    {
        return this.beforeRenderLivingLabelSuperiors;
    }

    public String[] getBeforeRenderLivingLabelInferiors()
    {
        return this.beforeRenderLivingLabelInferiors;
    }

    public String[] getOverrideRenderLivingLabelSuperiors()
    {
        return this.overrideRenderLivingLabelSuperiors;
    }

    public String[] getOverrideRenderLivingLabelInferiors()
    {
        return this.overrideRenderLivingLabelInferiors;
    }

    public String[] getAfterRenderLivingLabelSuperiors()
    {
        return this.afterRenderLivingLabelSuperiors;
    }

    public String[] getAfterRenderLivingLabelInferiors()
    {
        return this.afterRenderLivingLabelInferiors;
    }

    public void setBeforeRenderLivingLabelSuperiors(String[] var1)
    {
        this.beforeRenderLivingLabelSuperiors = var1;
    }

    public void setBeforeRenderLivingLabelInferiors(String[] var1)
    {
        this.beforeRenderLivingLabelInferiors = var1;
    }

    public void setOverrideRenderLivingLabelSuperiors(String[] var1)
    {
        this.overrideRenderLivingLabelSuperiors = var1;
    }

    public void setOverrideRenderLivingLabelInferiors(String[] var1)
    {
        this.overrideRenderLivingLabelInferiors = var1;
    }

    public void setAfterRenderLivingLabelSuperiors(String[] var1)
    {
        this.afterRenderLivingLabelSuperiors = var1;
    }

    public void setAfterRenderLivingLabelInferiors(String[] var1)
    {
        this.afterRenderLivingLabelInferiors = var1;
    }

    public String[] getBeforeRenderModelSuperiors()
    {
        return this.beforeRenderModelSuperiors;
    }

    public String[] getBeforeRenderModelInferiors()
    {
        return this.beforeRenderModelInferiors;
    }

    public String[] getOverrideRenderModelSuperiors()
    {
        return this.overrideRenderModelSuperiors;
    }

    public String[] getOverrideRenderModelInferiors()
    {
        return this.overrideRenderModelInferiors;
    }

    public String[] getAfterRenderModelSuperiors()
    {
        return this.afterRenderModelSuperiors;
    }

    public String[] getAfterRenderModelInferiors()
    {
        return this.afterRenderModelInferiors;
    }

    public void setBeforeRenderModelSuperiors(String[] var1)
    {
        this.beforeRenderModelSuperiors = var1;
    }

    public void setBeforeRenderModelInferiors(String[] var1)
    {
        this.beforeRenderModelInferiors = var1;
    }

    public void setOverrideRenderModelSuperiors(String[] var1)
    {
        this.overrideRenderModelSuperiors = var1;
    }

    public void setOverrideRenderModelInferiors(String[] var1)
    {
        this.overrideRenderModelInferiors = var1;
    }

    public void setAfterRenderModelSuperiors(String[] var1)
    {
        this.afterRenderModelSuperiors = var1;
    }

    public void setAfterRenderModelInferiors(String[] var1)
    {
        this.afterRenderModelInferiors = var1;
    }

    public String[] getBeforeRenderNameSuperiors()
    {
        return this.beforeRenderNameSuperiors;
    }

    public String[] getBeforeRenderNameInferiors()
    {
        return this.beforeRenderNameInferiors;
    }

    public String[] getOverrideRenderNameSuperiors()
    {
        return this.overrideRenderNameSuperiors;
    }

    public String[] getOverrideRenderNameInferiors()
    {
        return this.overrideRenderNameInferiors;
    }

    public String[] getAfterRenderNameSuperiors()
    {
        return this.afterRenderNameSuperiors;
    }

    public String[] getAfterRenderNameInferiors()
    {
        return this.afterRenderNameInferiors;
    }

    public void setBeforeRenderNameSuperiors(String[] var1)
    {
        this.beforeRenderNameSuperiors = var1;
    }

    public void setBeforeRenderNameInferiors(String[] var1)
    {
        this.beforeRenderNameInferiors = var1;
    }

    public void setOverrideRenderNameSuperiors(String[] var1)
    {
        this.overrideRenderNameSuperiors = var1;
    }

    public void setOverrideRenderNameInferiors(String[] var1)
    {
        this.overrideRenderNameInferiors = var1;
    }

    public void setAfterRenderNameSuperiors(String[] var1)
    {
        this.afterRenderNameSuperiors = var1;
    }

    public void setAfterRenderNameInferiors(String[] var1)
    {
        this.afterRenderNameInferiors = var1;
    }

    public String[] getBeforeRenderPlayerSuperiors()
    {
        return this.beforeRenderPlayerSuperiors;
    }

    public String[] getBeforeRenderPlayerInferiors()
    {
        return this.beforeRenderPlayerInferiors;
    }

    public String[] getOverrideRenderPlayerSuperiors()
    {
        return this.overrideRenderPlayerSuperiors;
    }

    public String[] getOverrideRenderPlayerInferiors()
    {
        return this.overrideRenderPlayerInferiors;
    }

    public String[] getAfterRenderPlayerSuperiors()
    {
        return this.afterRenderPlayerSuperiors;
    }

    public String[] getAfterRenderPlayerInferiors()
    {
        return this.afterRenderPlayerInferiors;
    }

    public void setBeforeRenderPlayerSuperiors(String[] var1)
    {
        this.beforeRenderPlayerSuperiors = var1;
    }

    public void setBeforeRenderPlayerInferiors(String[] var1)
    {
        this.beforeRenderPlayerInferiors = var1;
    }

    public void setOverrideRenderPlayerSuperiors(String[] var1)
    {
        this.overrideRenderPlayerSuperiors = var1;
    }

    public void setOverrideRenderPlayerInferiors(String[] var1)
    {
        this.overrideRenderPlayerInferiors = var1;
    }

    public void setAfterRenderPlayerSuperiors(String[] var1)
    {
        this.afterRenderPlayerSuperiors = var1;
    }

    public void setAfterRenderPlayerInferiors(String[] var1)
    {
        this.afterRenderPlayerInferiors = var1;
    }

    public String[] getBeforeRenderPlayerScaleSuperiors()
    {
        return this.beforeRenderPlayerScaleSuperiors;
    }

    public String[] getBeforeRenderPlayerScaleInferiors()
    {
        return this.beforeRenderPlayerScaleInferiors;
    }

    public String[] getOverrideRenderPlayerScaleSuperiors()
    {
        return this.overrideRenderPlayerScaleSuperiors;
    }

    public String[] getOverrideRenderPlayerScaleInferiors()
    {
        return this.overrideRenderPlayerScaleInferiors;
    }

    public String[] getAfterRenderPlayerScaleSuperiors()
    {
        return this.afterRenderPlayerScaleSuperiors;
    }

    public String[] getAfterRenderPlayerScaleInferiors()
    {
        return this.afterRenderPlayerScaleInferiors;
    }

    public void setBeforeRenderPlayerScaleSuperiors(String[] var1)
    {
        this.beforeRenderPlayerScaleSuperiors = var1;
    }

    public void setBeforeRenderPlayerScaleInferiors(String[] var1)
    {
        this.beforeRenderPlayerScaleInferiors = var1;
    }

    public void setOverrideRenderPlayerScaleSuperiors(String[] var1)
    {
        this.overrideRenderPlayerScaleSuperiors = var1;
    }

    public void setOverrideRenderPlayerScaleInferiors(String[] var1)
    {
        this.overrideRenderPlayerScaleInferiors = var1;
    }

    public void setAfterRenderPlayerScaleSuperiors(String[] var1)
    {
        this.afterRenderPlayerScaleSuperiors = var1;
    }

    public void setAfterRenderPlayerScaleInferiors(String[] var1)
    {
        this.afterRenderPlayerScaleInferiors = var1;
    }

    public String[] getBeforeRenderPlayerSleepSuperiors()
    {
        return this.beforeRenderPlayerSleepSuperiors;
    }

    public String[] getBeforeRenderPlayerSleepInferiors()
    {
        return this.beforeRenderPlayerSleepInferiors;
    }

    public String[] getOverrideRenderPlayerSleepSuperiors()
    {
        return this.overrideRenderPlayerSleepSuperiors;
    }

    public String[] getOverrideRenderPlayerSleepInferiors()
    {
        return this.overrideRenderPlayerSleepInferiors;
    }

    public String[] getAfterRenderPlayerSleepSuperiors()
    {
        return this.afterRenderPlayerSleepSuperiors;
    }

    public String[] getAfterRenderPlayerSleepInferiors()
    {
        return this.afterRenderPlayerSleepInferiors;
    }

    public void setBeforeRenderPlayerSleepSuperiors(String[] var1)
    {
        this.beforeRenderPlayerSleepSuperiors = var1;
    }

    public void setBeforeRenderPlayerSleepInferiors(String[] var1)
    {
        this.beforeRenderPlayerSleepInferiors = var1;
    }

    public void setOverrideRenderPlayerSleepSuperiors(String[] var1)
    {
        this.overrideRenderPlayerSleepSuperiors = var1;
    }

    public void setOverrideRenderPlayerSleepInferiors(String[] var1)
    {
        this.overrideRenderPlayerSleepInferiors = var1;
    }

    public void setAfterRenderPlayerSleepSuperiors(String[] var1)
    {
        this.afterRenderPlayerSleepSuperiors = var1;
    }

    public void setAfterRenderPlayerSleepInferiors(String[] var1)
    {
        this.afterRenderPlayerSleepInferiors = var1;
    }

    public String[] getBeforeRenderSpecialsSuperiors()
    {
        return this.beforeRenderSpecialsSuperiors;
    }

    public String[] getBeforeRenderSpecialsInferiors()
    {
        return this.beforeRenderSpecialsInferiors;
    }

    public String[] getOverrideRenderSpecialsSuperiors()
    {
        return this.overrideRenderSpecialsSuperiors;
    }

    public String[] getOverrideRenderSpecialsInferiors()
    {
        return this.overrideRenderSpecialsInferiors;
    }

    public String[] getAfterRenderSpecialsSuperiors()
    {
        return this.afterRenderSpecialsSuperiors;
    }

    public String[] getAfterRenderSpecialsInferiors()
    {
        return this.afterRenderSpecialsInferiors;
    }

    public void setBeforeRenderSpecialsSuperiors(String[] var1)
    {
        this.beforeRenderSpecialsSuperiors = var1;
    }

    public void setBeforeRenderSpecialsInferiors(String[] var1)
    {
        this.beforeRenderSpecialsInferiors = var1;
    }

    public void setOverrideRenderSpecialsSuperiors(String[] var1)
    {
        this.overrideRenderSpecialsSuperiors = var1;
    }

    public void setOverrideRenderSpecialsInferiors(String[] var1)
    {
        this.overrideRenderSpecialsInferiors = var1;
    }

    public void setAfterRenderSpecialsSuperiors(String[] var1)
    {
        this.afterRenderSpecialsSuperiors = var1;
    }

    public void setAfterRenderSpecialsInferiors(String[] var1)
    {
        this.afterRenderSpecialsInferiors = var1;
    }

    public String[] getBeforeRenderSwingProgressSuperiors()
    {
        return this.beforeRenderSwingProgressSuperiors;
    }

    public String[] getBeforeRenderSwingProgressInferiors()
    {
        return this.beforeRenderSwingProgressInferiors;
    }

    public String[] getOverrideRenderSwingProgressSuperiors()
    {
        return this.overrideRenderSwingProgressSuperiors;
    }

    public String[] getOverrideRenderSwingProgressInferiors()
    {
        return this.overrideRenderSwingProgressInferiors;
    }

    public String[] getAfterRenderSwingProgressSuperiors()
    {
        return this.afterRenderSwingProgressSuperiors;
    }

    public String[] getAfterRenderSwingProgressInferiors()
    {
        return this.afterRenderSwingProgressInferiors;
    }

    public void setBeforeRenderSwingProgressSuperiors(String[] var1)
    {
        this.beforeRenderSwingProgressSuperiors = var1;
    }

    public void setBeforeRenderSwingProgressInferiors(String[] var1)
    {
        this.beforeRenderSwingProgressInferiors = var1;
    }

    public void setOverrideRenderSwingProgressSuperiors(String[] var1)
    {
        this.overrideRenderSwingProgressSuperiors = var1;
    }

    public void setOverrideRenderSwingProgressInferiors(String[] var1)
    {
        this.overrideRenderSwingProgressInferiors = var1;
    }

    public void setAfterRenderSwingProgressSuperiors(String[] var1)
    {
        this.afterRenderSwingProgressSuperiors = var1;
    }

    public void setAfterRenderSwingProgressInferiors(String[] var1)
    {
        this.afterRenderSwingProgressInferiors = var1;
    }

    public String[] getBeforeRotatePlayerSuperiors()
    {
        return this.beforeRotatePlayerSuperiors;
    }

    public String[] getBeforeRotatePlayerInferiors()
    {
        return this.beforeRotatePlayerInferiors;
    }

    public String[] getOverrideRotatePlayerSuperiors()
    {
        return this.overrideRotatePlayerSuperiors;
    }

    public String[] getOverrideRotatePlayerInferiors()
    {
        return this.overrideRotatePlayerInferiors;
    }

    public String[] getAfterRotatePlayerSuperiors()
    {
        return this.afterRotatePlayerSuperiors;
    }

    public String[] getAfterRotatePlayerInferiors()
    {
        return this.afterRotatePlayerInferiors;
    }

    public void setBeforeRotatePlayerSuperiors(String[] var1)
    {
        this.beforeRotatePlayerSuperiors = var1;
    }

    public void setBeforeRotatePlayerInferiors(String[] var1)
    {
        this.beforeRotatePlayerInferiors = var1;
    }

    public void setOverrideRotatePlayerSuperiors(String[] var1)
    {
        this.overrideRotatePlayerSuperiors = var1;
    }

    public void setOverrideRotatePlayerInferiors(String[] var1)
    {
        this.overrideRotatePlayerInferiors = var1;
    }

    public void setAfterRotatePlayerSuperiors(String[] var1)
    {
        this.afterRotatePlayerSuperiors = var1;
    }

    public void setAfterRotatePlayerInferiors(String[] var1)
    {
        this.afterRotatePlayerInferiors = var1;
    }

    public String[] getBeforeSetArmorModelSuperiors()
    {
        return this.beforeSetArmorModelSuperiors;
    }

    public String[] getBeforeSetArmorModelInferiors()
    {
        return this.beforeSetArmorModelInferiors;
    }

    public String[] getOverrideSetArmorModelSuperiors()
    {
        return this.overrideSetArmorModelSuperiors;
    }

    public String[] getOverrideSetArmorModelInferiors()
    {
        return this.overrideSetArmorModelInferiors;
    }

    public String[] getAfterSetArmorModelSuperiors()
    {
        return this.afterSetArmorModelSuperiors;
    }

    public String[] getAfterSetArmorModelInferiors()
    {
        return this.afterSetArmorModelInferiors;
    }

    public void setBeforeSetArmorModelSuperiors(String[] var1)
    {
        this.beforeSetArmorModelSuperiors = var1;
    }

    public void setBeforeSetArmorModelInferiors(String[] var1)
    {
        this.beforeSetArmorModelInferiors = var1;
    }

    public void setOverrideSetArmorModelSuperiors(String[] var1)
    {
        this.overrideSetArmorModelSuperiors = var1;
    }

    public void setOverrideSetArmorModelInferiors(String[] var1)
    {
        this.overrideSetArmorModelInferiors = var1;
    }

    public void setAfterSetArmorModelSuperiors(String[] var1)
    {
        this.afterSetArmorModelSuperiors = var1;
    }

    public void setAfterSetArmorModelInferiors(String[] var1)
    {
        this.afterSetArmorModelInferiors = var1;
    }

    public String[] getBeforeSetPassArmorModelSuperiors()
    {
        return this.beforeSetPassArmorModelSuperiors;
    }

    public String[] getBeforeSetPassArmorModelInferiors()
    {
        return this.beforeSetPassArmorModelInferiors;
    }

    public String[] getOverrideSetPassArmorModelSuperiors()
    {
        return this.overrideSetPassArmorModelSuperiors;
    }

    public String[] getOverrideSetPassArmorModelInferiors()
    {
        return this.overrideSetPassArmorModelInferiors;
    }

    public String[] getAfterSetPassArmorModelSuperiors()
    {
        return this.afterSetPassArmorModelSuperiors;
    }

    public String[] getAfterSetPassArmorModelInferiors()
    {
        return this.afterSetPassArmorModelInferiors;
    }

    public void setBeforeSetPassArmorModelSuperiors(String[] var1)
    {
        this.beforeSetPassArmorModelSuperiors = var1;
    }

    public void setBeforeSetPassArmorModelInferiors(String[] var1)
    {
        this.beforeSetPassArmorModelInferiors = var1;
    }

    public void setOverrideSetPassArmorModelSuperiors(String[] var1)
    {
        this.overrideSetPassArmorModelSuperiors = var1;
    }

    public void setOverrideSetPassArmorModelInferiors(String[] var1)
    {
        this.overrideSetPassArmorModelInferiors = var1;
    }

    public void setAfterSetPassArmorModelSuperiors(String[] var1)
    {
        this.afterSetPassArmorModelSuperiors = var1;
    }

    public void setAfterSetPassArmorModelInferiors(String[] var1)
    {
        this.afterSetPassArmorModelInferiors = var1;
    }

    public String[] getBeforeSetRenderManagerSuperiors()
    {
        return this.beforeSetRenderManagerSuperiors;
    }

    public String[] getBeforeSetRenderManagerInferiors()
    {
        return this.beforeSetRenderManagerInferiors;
    }

    public String[] getOverrideSetRenderManagerSuperiors()
    {
        return this.overrideSetRenderManagerSuperiors;
    }

    public String[] getOverrideSetRenderManagerInferiors()
    {
        return this.overrideSetRenderManagerInferiors;
    }

    public String[] getAfterSetRenderManagerSuperiors()
    {
        return this.afterSetRenderManagerSuperiors;
    }

    public String[] getAfterSetRenderManagerInferiors()
    {
        return this.afterSetRenderManagerInferiors;
    }

    public void setBeforeSetRenderManagerSuperiors(String[] var1)
    {
        this.beforeSetRenderManagerSuperiors = var1;
    }

    public void setBeforeSetRenderManagerInferiors(String[] var1)
    {
        this.beforeSetRenderManagerInferiors = var1;
    }

    public void setOverrideSetRenderManagerSuperiors(String[] var1)
    {
        this.overrideSetRenderManagerSuperiors = var1;
    }

    public void setOverrideSetRenderManagerInferiors(String[] var1)
    {
        this.overrideSetRenderManagerInferiors = var1;
    }

    public void setAfterSetRenderManagerSuperiors(String[] var1)
    {
        this.afterSetRenderManagerSuperiors = var1;
    }

    public void setAfterSetRenderManagerInferiors(String[] var1)
    {
        this.afterSetRenderManagerInferiors = var1;
    }

    public String[] getBeforeSetRenderPassModelSuperiors()
    {
        return this.beforeSetRenderPassModelSuperiors;
    }

    public String[] getBeforeSetRenderPassModelInferiors()
    {
        return this.beforeSetRenderPassModelInferiors;
    }

    public String[] getOverrideSetRenderPassModelSuperiors()
    {
        return this.overrideSetRenderPassModelSuperiors;
    }

    public String[] getOverrideSetRenderPassModelInferiors()
    {
        return this.overrideSetRenderPassModelInferiors;
    }

    public String[] getAfterSetRenderPassModelSuperiors()
    {
        return this.afterSetRenderPassModelSuperiors;
    }

    public String[] getAfterSetRenderPassModelInferiors()
    {
        return this.afterSetRenderPassModelInferiors;
    }

    public void setBeforeSetRenderPassModelSuperiors(String[] var1)
    {
        this.beforeSetRenderPassModelSuperiors = var1;
    }

    public void setBeforeSetRenderPassModelInferiors(String[] var1)
    {
        this.beforeSetRenderPassModelInferiors = var1;
    }

    public void setOverrideSetRenderPassModelSuperiors(String[] var1)
    {
        this.overrideSetRenderPassModelSuperiors = var1;
    }

    public void setOverrideSetRenderPassModelInferiors(String[] var1)
    {
        this.overrideSetRenderPassModelInferiors = var1;
    }

    public void setAfterSetRenderPassModelSuperiors(String[] var1)
    {
        this.afterSetRenderPassModelSuperiors = var1;
    }

    public void setAfterSetRenderPassModelInferiors(String[] var1)
    {
        this.afterSetRenderPassModelInferiors = var1;
    }

    public String[] getBeforeRenderSpecialHeadArmorSuperiors()
    {
        return this.beforeRenderSpecialHeadArmorSuperiors;
    }

    public String[] getBeforeRenderSpecialHeadArmorInferiors()
    {
        return this.beforeRenderSpecialHeadArmorInferiors;
    }

    public String[] getOverrideRenderSpecialHeadArmorSuperiors()
    {
        return this.overrideRenderSpecialHeadArmorSuperiors;
    }

    public String[] getOverrideRenderSpecialHeadArmorInferiors()
    {
        return this.overrideRenderSpecialHeadArmorInferiors;
    }

    public String[] getAfterRenderSpecialHeadArmorSuperiors()
    {
        return this.afterRenderSpecialHeadArmorSuperiors;
    }

    public String[] getAfterRenderSpecialHeadArmorInferiors()
    {
        return this.afterRenderSpecialHeadArmorInferiors;
    }

    public void setBeforeRenderSpecialHeadArmorSuperiors(String[] var1)
    {
        this.beforeRenderSpecialHeadArmorSuperiors = var1;
    }

    public void setBeforeRenderSpecialHeadArmorInferiors(String[] var1)
    {
        this.beforeRenderSpecialHeadArmorInferiors = var1;
    }

    public void setOverrideRenderSpecialHeadArmorSuperiors(String[] var1)
    {
        this.overrideRenderSpecialHeadArmorSuperiors = var1;
    }

    public void setOverrideRenderSpecialHeadArmorInferiors(String[] var1)
    {
        this.overrideRenderSpecialHeadArmorInferiors = var1;
    }

    public void setAfterRenderSpecialHeadArmorSuperiors(String[] var1)
    {
        this.afterRenderSpecialHeadArmorSuperiors = var1;
    }

    public void setAfterRenderSpecialHeadArmorInferiors(String[] var1)
    {
        this.afterRenderSpecialHeadArmorInferiors = var1;
    }

    public String[] getBeforeRenderSpecialHeadEarsSuperiors()
    {
        return this.beforeRenderSpecialHeadEarsSuperiors;
    }

    public String[] getBeforeRenderSpecialHeadEarsInferiors()
    {
        return this.beforeRenderSpecialHeadEarsInferiors;
    }

    public String[] getOverrideRenderSpecialHeadEarsSuperiors()
    {
        return this.overrideRenderSpecialHeadEarsSuperiors;
    }

    public String[] getOverrideRenderSpecialHeadEarsInferiors()
    {
        return this.overrideRenderSpecialHeadEarsInferiors;
    }

    public String[] getAfterRenderSpecialHeadEarsSuperiors()
    {
        return this.afterRenderSpecialHeadEarsSuperiors;
    }

    public String[] getAfterRenderSpecialHeadEarsInferiors()
    {
        return this.afterRenderSpecialHeadEarsInferiors;
    }

    public void setBeforeRenderSpecialHeadEarsSuperiors(String[] var1)
    {
        this.beforeRenderSpecialHeadEarsSuperiors = var1;
    }

    public void setBeforeRenderSpecialHeadEarsInferiors(String[] var1)
    {
        this.beforeRenderSpecialHeadEarsInferiors = var1;
    }

    public void setOverrideRenderSpecialHeadEarsSuperiors(String[] var1)
    {
        this.overrideRenderSpecialHeadEarsSuperiors = var1;
    }

    public void setOverrideRenderSpecialHeadEarsInferiors(String[] var1)
    {
        this.overrideRenderSpecialHeadEarsInferiors = var1;
    }

    public void setAfterRenderSpecialHeadEarsSuperiors(String[] var1)
    {
        this.afterRenderSpecialHeadEarsSuperiors = var1;
    }

    public void setAfterRenderSpecialHeadEarsInferiors(String[] var1)
    {
        this.afterRenderSpecialHeadEarsInferiors = var1;
    }

    public String[] getBeforeRenderSpecialCloakSuperiors()
    {
        return this.beforeRenderSpecialCloakSuperiors;
    }

    public String[] getBeforeRenderSpecialCloakInferiors()
    {
        return this.beforeRenderSpecialCloakInferiors;
    }

    public String[] getOverrideRenderSpecialCloakSuperiors()
    {
        return this.overrideRenderSpecialCloakSuperiors;
    }

    public String[] getOverrideRenderSpecialCloakInferiors()
    {
        return this.overrideRenderSpecialCloakInferiors;
    }

    public String[] getAfterRenderSpecialCloakSuperiors()
    {
        return this.afterRenderSpecialCloakSuperiors;
    }

    public String[] getAfterRenderSpecialCloakInferiors()
    {
        return this.afterRenderSpecialCloakInferiors;
    }

    public void setBeforeRenderSpecialCloakSuperiors(String[] var1)
    {
        this.beforeRenderSpecialCloakSuperiors = var1;
    }

    public void setBeforeRenderSpecialCloakInferiors(String[] var1)
    {
        this.beforeRenderSpecialCloakInferiors = var1;
    }

    public void setOverrideRenderSpecialCloakSuperiors(String[] var1)
    {
        this.overrideRenderSpecialCloakSuperiors = var1;
    }

    public void setOverrideRenderSpecialCloakInferiors(String[] var1)
    {
        this.overrideRenderSpecialCloakInferiors = var1;
    }

    public void setAfterRenderSpecialCloakSuperiors(String[] var1)
    {
        this.afterRenderSpecialCloakSuperiors = var1;
    }

    public void setAfterRenderSpecialCloakInferiors(String[] var1)
    {
        this.afterRenderSpecialCloakInferiors = var1;
    }

    public String[] getBeforeRenderSpecialItemInHandSuperiors()
    {
        return this.beforeRenderSpecialItemInHandSuperiors;
    }

    public String[] getBeforeRenderSpecialItemInHandInferiors()
    {
        return this.beforeRenderSpecialItemInHandInferiors;
    }

    public String[] getOverrideRenderSpecialItemInHandSuperiors()
    {
        return this.overrideRenderSpecialItemInHandSuperiors;
    }

    public String[] getOverrideRenderSpecialItemInHandInferiors()
    {
        return this.overrideRenderSpecialItemInHandInferiors;
    }

    public String[] getAfterRenderSpecialItemInHandSuperiors()
    {
        return this.afterRenderSpecialItemInHandSuperiors;
    }

    public String[] getAfterRenderSpecialItemInHandInferiors()
    {
        return this.afterRenderSpecialItemInHandInferiors;
    }

    public void setBeforeRenderSpecialItemInHandSuperiors(String[] var1)
    {
        this.beforeRenderSpecialItemInHandSuperiors = var1;
    }

    public void setBeforeRenderSpecialItemInHandInferiors(String[] var1)
    {
        this.beforeRenderSpecialItemInHandInferiors = var1;
    }

    public void setOverrideRenderSpecialItemInHandSuperiors(String[] var1)
    {
        this.overrideRenderSpecialItemInHandSuperiors = var1;
    }

    public void setOverrideRenderSpecialItemInHandInferiors(String[] var1)
    {
        this.overrideRenderSpecialItemInHandInferiors = var1;
    }

    public void setAfterRenderSpecialItemInHandSuperiors(String[] var1)
    {
        this.afterRenderSpecialItemInHandSuperiors = var1;
    }

    public void setAfterRenderSpecialItemInHandInferiors(String[] var1)
    {
        this.afterRenderSpecialItemInHandInferiors = var1;
    }

    public String[] getBeforePositionSpecialItemInHandSuperiors()
    {
        return this.beforePositionSpecialItemInHandSuperiors;
    }

    public String[] getBeforePositionSpecialItemInHandInferiors()
    {
        return this.beforePositionSpecialItemInHandInferiors;
    }

    public String[] getOverridePositionSpecialItemInHandSuperiors()
    {
        return this.overridePositionSpecialItemInHandSuperiors;
    }

    public String[] getOverridePositionSpecialItemInHandInferiors()
    {
        return this.overridePositionSpecialItemInHandInferiors;
    }

    public String[] getAfterPositionSpecialItemInHandSuperiors()
    {
        return this.afterPositionSpecialItemInHandSuperiors;
    }

    public String[] getAfterPositionSpecialItemInHandInferiors()
    {
        return this.afterPositionSpecialItemInHandInferiors;
    }

    public void setBeforePositionSpecialItemInHandSuperiors(String[] var1)
    {
        this.beforePositionSpecialItemInHandSuperiors = var1;
    }

    public void setBeforePositionSpecialItemInHandInferiors(String[] var1)
    {
        this.beforePositionSpecialItemInHandInferiors = var1;
    }

    public void setOverridePositionSpecialItemInHandSuperiors(String[] var1)
    {
        this.overridePositionSpecialItemInHandSuperiors = var1;
    }

    public void setOverridePositionSpecialItemInHandInferiors(String[] var1)
    {
        this.overridePositionSpecialItemInHandInferiors = var1;
    }

    public void setAfterPositionSpecialItemInHandSuperiors(String[] var1)
    {
        this.afterPositionSpecialItemInHandSuperiors = var1;
    }

    public void setAfterPositionSpecialItemInHandInferiors(String[] var1)
    {
        this.afterPositionSpecialItemInHandInferiors = var1;
    }
}
