package mod.coremod.asm;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.objectweb.asm.ClassReader;
import org.objectweb.asm.ClassWriter;
import org.objectweb.asm.tree.ClassNode;
import org.objectweb.asm.tree.FieldNode;

import cpw.mods.fml.common.asm.transformers.AccessTransformer;
import cpw.mods.fml.relauncher.FMLRelauncher;
import cpw.mods.fml.relauncher.IFMLLoadingPlugin.TransformerExclusions;

public class EAMAccessTransformer extends AccessTransformer {
        private static EAMAccessTransformer instance;
        private static List mapFiles = new LinkedList();
        public EAMAccessTransformer() throws IOException {
                super();
                instance = this;
                // add your access transformers here!
                mapFiles.add("eam_at.cfg");
                Iterator it = mapFiles.iterator();
                while (it.hasNext()) {
                        String file = (String)it.next();
                        this.readMapFile(file);
                }
                
        }
        public static void addTransformerMap(String mapFileName) {
                if (instance == null) {
                        mapFiles.add(mapFileName);
                }
                else {
                        instance.readMapFile(mapFileName);
                }
        }
        private void readMapFile(String name) {
                System.out.println("Adding transformer map: " + name);
                try {
                        // get a method from AccessTransformer
                        Method e = AccessTransformer.class.getDeclaredMethod("readMapFile", new Class[]{String.class});
                        e.setAccessible(true);
                        // run it with the file given.
                        e.invoke(this, new Object[]{name});
                        
                }
                catch (Exception ex) {
                        throw new RuntimeException(ex);
                }
        }
        
        
        /**
        * Generates method for setting the placed position for the mob spawner item
        */
       public byte[] fieldadder(String name, byte[] bytes)
       {
           ClassMapping classmap = new ClassMapping("net/minecraft/entity/player/EntityPlayer");
           if(classmap.isClass(name))
           {
        	   ClassNode clazz = new ClassNode();
        	   ClassReader reader = new ClassReader(bytes);
        	   reader.accept(clazz, 0);
        	      
        	   Type playerInformationType = Type.getType(PlayerInformation.class);
        	     
        	   clazz.fields.add(new FieldNode(ACC_PUBLIC, "modPlayerInfo", playerInformationType.getDescriptor(), null, null));

        	   ClassWriter writer = new ClassWriter(COMPUTE_MAXS | COMPUTE_FRAMES);
        	   clazz.accept(writer);
        	   return writer.toByteArray();
           }
           return bytes;
       }
        
        @Override
        public byte[] transform(String name, byte[] bytes)
        {
            try
            {
                if(FMLRelauncher.side().equals("CLIENT"))
                {
                    bytes = fieldadder(name, bytes);          }
            }
            catch(Exception e)
            {
                throw new RuntimeException(e);
            }

            return bytes;
        }
        
}