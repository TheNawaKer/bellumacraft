@echo off
runtime\bin\python\python_mcp runtime\recompile.py %*
runtime\bin\python\python_mcp runtime\reobfuscate.py %*
cd ressources
rar a -r mod.zip mod/
cd ..
move /Y ressources\mod.zip reobf\minecraft\
cd reobf/minecraft
rar a -r mod.zip mod/
cd ..
cd ..
move /Y reobf\minecraft\mod.zip partage\
