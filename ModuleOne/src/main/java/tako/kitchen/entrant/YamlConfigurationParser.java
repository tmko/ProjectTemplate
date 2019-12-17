package tako.kitchen.entrant;


import lombok.extern.slf4j.Slf4j;
import org.yaml.snakeyaml.Yaml;
import org.yaml.snakeyaml.constructor.Constructor;

import java.io.InputStream;


@Slf4j
public class YamlConfigurationParser {
    private static final String LOADING = "loading yaml content";
    private static final String LOADED = "loaded [{}]";

    public static <T> T toPojo(InputStream in, Class<T> yamlPojoClass) {
        log.info(LOADING);
        Constructor constructor = new Constructor(yamlPojoClass);
        Yaml yaml = new Yaml(constructor);
        Object object = yaml.load(in);
        T result = yamlPojoClass.isInstance(object) ? yamlPojoClass.cast(object) : null;
        log.info(LOADED, result);

        return result;
    }

}