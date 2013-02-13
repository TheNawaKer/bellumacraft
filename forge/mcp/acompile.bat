@echo off
runtime\bin\python\python_mcp runtime\recompile.py %*
runtime\bin\python\python_mcp runtime\reobfuscate.py %*
rd partage /S /Q
mkdir partage
xcopy /E /Y /I reobf\minecraft\mod partage\mod
xcopy /E /Y /I src\minecraft\mod\legendaire45\texture partage\mod\legendaire45\texture
cd partage
rar a -r retrogame.zip mod/
rd mod /S /Q
