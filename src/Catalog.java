import com.google.gson.annotations.SerializedName;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Catalog {
    @SerializedName(value = "base-ref", alternate = {"baseRef"})
    public String baseRef;
    public String description;
    public Map<String, CatalogRef> catalogs = new HashMap<>();
    public Map<String, Alias> aliases = new HashMap<>();
    public Map<String, JbangTemplate> templates = new HashMap<>();

}

class CatalogRef {
    @SerializedName(value = "catalog-ref", alternate = {"catalogRef"})
    public String catalogRef;
    public String description;
}

class Alias {
    @SerializedName(value = "script-ref", alternate = {"scriptRef"})
    public String scriptRef;
    public String description;
    public List<String> arguments;
    @SerializedName(value = "java-options", alternate = {"javaOptions"})
    public List<String> javaOptions;
    public List<String> dependencies;
    public List<String> repositories;
    public List<String> classpaths;
    public Map<String, String> properties;
    @SerializedName(value = "java")
    public String javaVersion;
    @SerializedName(value = "main")
    public String mainClass;
}

class JbangTemplate {
    @SerializedName(value = "file-refs")
    public Map<String, String> fileRefs;
    public String description;
    public Map<String, TemplateProperty> properties;
}

class TemplateProperty {
    public String description;
    @SerializedName(value = "default")
    public String defaultValue;
}