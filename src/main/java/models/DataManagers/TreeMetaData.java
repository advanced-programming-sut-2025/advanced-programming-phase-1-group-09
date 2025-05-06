package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Items.Farming.Seed;
import models.GameWorld.Items.Farming.Tree;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TreeMetaData {
    private static final Map<String, Tree> trees = new HashMap<>();
    private static final Map<String, Seed> seeds = new HashMap<>();
    private static final Map<Seed, Tree> seedToTree = new HashMap<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(Tree.class, new TreeDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CropMetaData.class.getClassLoader().getResourceAsStream("JSON/trees.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<Tree> treeList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (Tree tree : treeList) {
                trees.put(tree.getName(), tree);
                Seed seed = new Seed(tree.getSource(), 10, tree.getSpawningChance());
                seeds.put(seed.getName(), seed);
                seedToTree.put(seed, tree);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load trees", e);
        }
    }

    public static Tree getTree(String name) {
        return trees.get(name);
    }

    public static Tree getTree(Seed seed) {
        return seedToTree.get(seed);
    }

    public static Seed getSeed(String seedName) {
        return seeds.get(seedName);
    }

    public static Collection<Seed> getAllSeeds() {
        return seeds.values();
    }

    public static Collection<Tree> getAllTrees() {
        return trees.values();
    }
}
