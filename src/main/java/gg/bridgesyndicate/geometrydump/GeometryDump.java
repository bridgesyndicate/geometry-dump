package gg.bridgesyndicate.geometrydump;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.block.Block;
import org.bukkit.plugin.java.JavaPlugin;

import java.util.ArrayList;
import java.util.List;

public final class GeometryDump extends JavaPlugin {

    void dumpCageSchematic() {
        World world = Bukkit.getWorld("world");
        List<BridgeSchematicBlock> bridgeSchematicBlockList = new ArrayList();
        for (int x=-10; x < 10; x++) {
            for (int y=-10; y < 10; y++) {
                for (int z=-10; z < 10; z++) {
                    Block baseBlock =  world.getBlockAt(x, y, z);
                    if (!(baseBlock.getTypeId() == 0)) {
                        bridgeSchematicBlockList.add(new BridgeSchematicBlock(x, y, z,
                                baseBlock.getTypeId(), baseBlock.getData()));
                    }
                }
            }
        }
        ObjectMapper mapper = new ObjectMapper();
        try {
            System.out.println(mapper.writeValueAsString(bridgeSchematicBlockList));
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onEnable() {
        dumpCageSchematic();
        System.exit(0);
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }
}

class BridgeSchematicBlock {
    public int x;
    public int y;
    public int z;
    public int id;
    public int data;

    public BridgeSchematicBlock() { }

    public BridgeSchematicBlock(int x, int y, int z, int id, int data) {
        this.x = x;
        this.y = y;
        this.z = z;
        this.id = id;
        this.data = data;
    }
}
