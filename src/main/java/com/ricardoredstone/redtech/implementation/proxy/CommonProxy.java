package com.ricardoredstone.redtech.implementation.proxy;

import com.ricardoredstone.redtech.base.Proxy;
import net.minecraft.world.World;

public class CommonProxy extends Proxy {

    @Override
    public World getClientWorld() {
        throw new IllegalStateException("Method only available in Client-Side");
    }

}
