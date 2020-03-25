package com.ricardoredstone.redtech.implementation.proxy;

import com.ricardoredstone.redtech.base.Proxy;
import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.server.MinecraftServer;
import net.minecraft.world.World;
import net.minecraftforge.fml.loading.FMLCommonLaunchHandler;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class CommonProxy extends Proxy {

    @Override
    public World getWorld() {
        throw new IllegalStateException("Method only available in Client-Side");
        //MinecraftServer
    }

}
