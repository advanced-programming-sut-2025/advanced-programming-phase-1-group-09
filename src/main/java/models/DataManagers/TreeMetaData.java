package models.DataManagers;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import models.GameWorld.Enums.SeasonName;
import models.GameWorld.Farming.Seed;
import models.GameWorld.Farming.TreeDefinition;

import java.io.IOException;
import java.io.InputStream;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class TreeMetaData {
    private static final Map<String, TreeDefinition> trees = new HashMap<>();
    private static final Map<String, Seed> seeds = new HashMap<>();
    private static final Map<Seed, TreeDefinition> seedToTree = new HashMap<>();

    static {
        try {
            ObjectMapper mapper = new ObjectMapper();
            SimpleModule module = new SimpleModule();
            module.addDeserializer(TreeDefinition.class, new TreeDeserializer());
            mapper.registerModule(module);

            InputStream inputStream = CropMetaData.class.getClassLoader().getResourceAsStream("JSON/trees.json");
            if (inputStream == null) {
                throw new RuntimeException("File not found in resources!");
            }

            List<TreeDefinition> treeDefinitionList = mapper.readValue(inputStream, new TypeReference<>() {});

            for (TreeDefinition treeDefinition : treeDefinitionList) {
                trees.put(treeDefinition.getName(), treeDefinition);
                Seed seed = new Seed(treeDefinition.getSource(), 10, treeDefinition.getSpawningChance(), false);
                seeds.put(seed.getName(), seed);
                seedToTree.put(seed, treeDefinition);
            }
        } catch (IOException e) {
            throw new RuntimeException("Failed to load trees", e);
        }
    }

    public static TreeDefinition getTree(String name) {
        return trees.get(name);
    }

    public static TreeDefinition getTree(Seed seed) {
        return seedToTree.get(seed);
    }

    public static Seed getSeed(String seedName) {
        return seeds.get(seedName);
    }

    public static Collection<Seed> getAllSeeds() {
        return seeds.values();
    }

    public static Collection<Seed> getSelectedSeeds(SeasonName season) {
        return seedToTree.entrySet().stream()
                .filter(entry -> {
                    TreeDefinition treeDefinition = entry.getValue();
                    return treeDefinition.getGrowingSeasons().contains(season);
                })
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());
    }

    public static Collection<TreeDefinition> getAllTrees() {
        return trees.values();
    }

    public static Collection<TreeDefinition> getSelectedTrees(SeasonName season) {
        return trees.values().stream()
                .filter(tree -> tree.getGrowingSeasons().contains(season))
                .collect(Collectors.toList());
    }
}
