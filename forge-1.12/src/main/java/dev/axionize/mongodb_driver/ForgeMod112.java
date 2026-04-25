package dev.axionize.mongodb_driver;

import net.minecraftforge.fml.common.Mod;

@Mod(value = "mongodb_driver", modid = "mongodb_driver", acceptableRemoteVersions = "*")
public class ForgeMod112
{
    public ForgeMod112() {
        try { new ForgeSetup113(); } catch (Throwable e) {}
        try { new ForgeSetup117(); } catch (Throwable e) {}
    }
}
