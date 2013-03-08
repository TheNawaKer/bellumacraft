package mod.coremod.asm;

import java.util.Map;

import cpw.mods.fml.relauncher.IFMLLoadingPlugin;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

@TransformerExclusions({"mod.coremod.asm"})
public class EAMLoadPlugin implements IFMLLoadingPlugin {

	@Override
	public String[] getLibraryRequestClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String[] getASMTransformerClass() {
		return new String[] {"mod.coremod.asm.EAMAccessTransformer"};
	}

	@Override
    public String getModContainerClass() { 
            return "mod.coremod.asm.EAMModContainer";
    }

	@Override
	public String getSetupClass() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void injectData(Map<String, Object> data) {
		// TODO Auto-generated method stub

	}

}
