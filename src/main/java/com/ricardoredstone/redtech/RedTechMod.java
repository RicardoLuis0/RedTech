package com.ricardoredstone.redtech;

import com.ricardoredstone.redtech.base.Proxy;
import com.ricardoredstone.redtech.implementation.proxy.ClientProxy;
import com.ricardoredstone.redtech.implementation.proxy.ServerProxy;
import com.ricardoredstone.redtech.implementation.registry.ModBlocks;
import com.ricardoredstone.redtech.implementation.registry.ModItems;
import com.ricardoredstone.redtech.implementation.registry.ModMulti;
import com.ricardoredstone.redtech.implementation.registry.ModRegistry;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.tileentity.TileEntityType;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.RegistryEvent;
import net.minecraftforge.eventbus.api.IEventBus;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.event.lifecycle.FMLCommonSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.fml.loading.FMLEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(RedTechMod.MOD_ID)
public final class RedTechMod {
    public static final String MOD_ID = "redtech";

    public static final Proxy PROXY;
    public static final Logger LOGGER;

    public static final ModRegistry MOD_REGISTRY;

    public static final ModBlocks BLOCKS;
    public static final ModItems ITEMS;
    public static final ModMulti MULTI;

    static {
        LOGGER = LogManager.getLogger();//initialize logger

        switch (FMLEnvironment.dist) {//initialize proxy
            case CLIENT:
                PROXY = new ClientProxy();
                break;
            case DEDICATED_SERVER:
                PROXY = new ServerProxy();
                break;
            default:
                throw new IllegalStateException("INVALID SIDE");
        }
        MOD_REGISTRY= new ModRegistry();
        MULTI = new ModMulti();
        BLOCKS = new ModBlocks();
        ITEMS = new ModItems();
    }

    public RedTechMod() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setupCommon);
        bus.addListener(this::setupClient);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        MOD_REGISTRY.setupCommon(event);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        MOD_REGISTRY.setupClient(event);
    }

    // You can use EventBusSubscriber to automatically subscribe events on the contained class (this is subscribing to the MOD Event bus for receiving Registry Events)
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            MOD_REGISTRY.registerBlocks(event);
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            MOD_REGISTRY.registerItems(event);
        }

        @SubscribeEvent
        public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            MOD_REGISTRY.registerTileEntities(event);
        }
    }
}
