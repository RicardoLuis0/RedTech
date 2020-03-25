package com.ricardoredstone.redtech.implementation.registry;

import com.ricardoredstone.redtech.implementation.blocks.machines.grinder.BurnerGrinderContainer;
import com.ricardoredstone.redtech.implementation.blocks.machines.grinder.BurnerGrinderScreen;
import net.minecraft.client.gui.ScreenManager;
import net.minecraft.inventory.container.ContainerType;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;

public class ModContainers {
    public static ContainerType<BurnerGrinderContainer> BURNER_GRINDER = BurnerGrinderContainer.createContainerType();

    public static void registerContainerTypes(final RegistryEvent.Register<ContainerType<?>> event){
        event.getRegistry().register(BURNER_GRINDER);
    }

    public static void registerScreens(final FMLClientSetupEvent event){
        ScreenManager.registerFactory(BURNER_GRINDER, BurnerGrinderScreen::new);
        //TODO
    }
}
