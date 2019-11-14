package com.ricardoredstone.redtech;

import com.ricardoredstone.redtech.base.Proxy;
import com.ricardoredstone.redtech.implementation.proxy.ClientProxy;
import com.ricardoredstone.redtech.implementation.proxy.ServerProxy;
import com.ricardoredstone.redtech.implementation.registry.*;
import net.minecraft.block.Block;
import net.minecraft.item.Item;
import net.minecraft.item.crafting.IRecipeSerializer;
import net.minecraft.tileentity.TileEntityType;
import net.minecraft.util.ResourceLocation;
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

@Mod(RedTechMod.MOD_ID)
public final class RedTechMod {
    public static final String MOD_ID = "redtech";

    public static final Proxy PROXY;
    public static final Logger LOGGER;

    public static final ModRegistry REGISTRY;

    public static final ModBlocks BLOCKS;
    public static final ModItems ITEMS;
    public static final ModMetals MULTI;
    public static final ModRecipeSerializers RECIPE_SERIALIZERS;
    public static final ModTileEntities TILE_ENTITIES;


    public static ResourceLocation makeResourceLocation(String name) {
        return new ResourceLocation(MOD_ID, name);
    }
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
        REGISTRY = new ModRegistry();
        MULTI = new ModMetals();
        BLOCKS = new ModBlocks();
        ITEMS = new ModItems();
        TILE_ENTITIES = new ModTileEntities();
        RECIPE_SERIALIZERS = new ModRecipeSerializers();
    }

    public RedTechMod() {
        final IEventBus bus = FMLJavaModLoadingContext.get().getModEventBus();
        MinecraftForge.EVENT_BUS.register(this);
        bus.addListener(this::setupCommon);
        bus.addListener(this::setupClient);
    }

    private void setupCommon(final FMLCommonSetupEvent event) {
        REGISTRY.setupCommon(event);
    }

    private void setupClient(final FMLClientSetupEvent event) {
        REGISTRY.setupClient(event);
    }

    @SuppressWarnings("unused")
    @Mod.EventBusSubscriber(bus = Mod.EventBusSubscriber.Bus.MOD)
    public static final class RegistryEvents {
        @SubscribeEvent
        public static void registerBlocks(final RegistryEvent.Register<Block> event) {
            REGISTRY.registerBlocks(event);
        }

        @SubscribeEvent
        public static void registerItems(final RegistryEvent.Register<Item> event) {
            REGISTRY.registerItems(event);
        }

        @SubscribeEvent
        public static void registerTileEntities(final RegistryEvent.Register<TileEntityType<?>> event) {
            REGISTRY.registerTileEntities(event);
        }

        @SubscribeEvent
        public static void registerRecipeSerializers(final RegistryEvent.Register<IRecipeSerializer<?>> event) {
            REGISTRY.registerRecipeSerializers(event);
        }
    }
}
