package com.ricardoredstone.redtech.implementation.proxy;

import mcp.MethodsReturnNonnullByDefault;
import net.minecraft.client.Minecraft;
import net.minecraft.world.World;

import javax.annotation.ParametersAreNonnullByDefault;

@ParametersAreNonnullByDefault
@MethodsReturnNonnullByDefault
public class ClientProxy extends CommonProxy {

    @Override
    public World getWorld() {
        return Minecraft.getInstance().world;
    }

}
