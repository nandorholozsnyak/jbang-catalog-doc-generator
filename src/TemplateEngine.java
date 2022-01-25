import io.quarkus.qute.Engine;
import io.quarkus.qute.ReflectionValueResolver;
import io.quarkus.qute.Template;
import io.quarkus.qute.TemplateExtension;
import io.quarkus.qute.TemplateLocator;
import io.quarkus.qute.Variant;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Map;
import java.util.Optional;

/**
 * Mostly copied from the original JBang repository: https://github.com/jbangdev/jbang/blob/main/src/main/java/dev/jbang/util/TemplateEngine.java
 */
public class TemplateEngine {

    public static Template getCatalogTemplate(String template) {
        return Engine.builder()
            .addDefaults()
            .removeStandaloneLines(true)
            .addValueResolver(new ReflectionValueResolver())
            .addLocator(TemplateEngine::locate)
            .build()
            .getTemplate(template);
    }

    private static Optional<TemplateLocator.TemplateLocation> locate(String path) {
        Path p = Paths.get(path);
        if (p.isAbsolute() || Files.isReadable(p)) {
            return Optional.of(new FileTemplateLocation(p));
        } else {
            URL resource = locatePath(path);
            if (resource != null) {
                return Optional.of(new ResourceTemplateLocation(resource));
            }
        }
        return Optional.empty();
    }

    private static URL locatePath(String path) {
        ClassLoader cl = Thread.currentThread().getContextClassLoader();
        if (cl == null) {
            cl = CatalogGenerator.class.getClassLoader();
        }
        return cl.getResource(path);
    }

    static class ResourceTemplateLocation implements TemplateLocator.TemplateLocation {
        private final URL resource;
        private Optional<Variant> variant;

        public ResourceTemplateLocation(URL resource) {
            this.resource = resource;
            this.variant = Optional.empty();
        }

        @Override
        public Reader read() {
            try {
                return new InputStreamReader(resource.openStream(), StandardCharsets.UTF_8);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public Optional<Variant> getVariant() {
            return variant;
        }

    }

    static class FileTemplateLocation implements TemplateLocator.TemplateLocation {
        private final Path file;
        private Optional<Variant> variant;

        public FileTemplateLocation(Path file) {
            this.file = file;
            this.variant = Optional.empty();
        }

        @Override
        public Reader read() {
            try {
                return Files.newBufferedReader(file);
            } catch (IOException e) {
                return null;
            }
        }

        @Override
        public Optional<Variant> getVariant() {
            return variant;
        }

    }

    @TemplateExtension
    static Object get(Map<?, ?> map, Object key) {
        return map.get(key);
    }

    @TemplateExtension
    static boolean containsKey(Map<?, ?> map, Object key) {
        return map.containsKey(key);
    }
}
