///usr/bin/env jbang "$0" "$@" ; exit $?
// Update the Quarkus version to what you want here or run jbang with
// `-Dquarkus.version=<version>` to override it.
//DEPS io.quarkus:quarkus-bom:${quarkus.version:2.6.3.Final}@pom
//DEPS io.quarkus:quarkus-picocli
//DEPS io.quarkus:quarkus-qute
//DEPS com.google.code.gson:gson:2.8.9
//JAVA 15+
//SOURCES Catalog.java
//SOURCES TemplateEngine.java
//FILES catalog.md.qute
//Q:CONFIG quarkus.banner.enabled=false
//Q:CONFIG quarkus.log.level=ERROR

import com.google.gson.Gson;
import io.quarkus.qute.Template;
import picocli.CommandLine;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@CommandLine.Command(description = """
    Generates a JBang catalog documentation based on the given jbang-catalog.json file.
    Documentation template can be customized with the -T/--template flags.
    Name and logo can be customized with the -n/--name and -cl/--catalog-logo flags.
    Catalog, alias and template sections can be turned off conditionally with the -rc=false, -ra=false, -rt=false flags
    """)
public class CatalogGenerator implements Runnable {

    private static final String TEMPLATE_CATALOG_MD_QUTE = "catalog.md.qute";
    private static final String CATALOG_MD = "catalog.md";
    private static final String JBANG_CATALOG = "jbang-catalog.json";

    @CommandLine.Option(names = {"-h", "--help"}, usageHelp = true, description = "Display help/info")
    boolean helpRequested;

    @CommandLine.Option(names = {"-f", "--file"}, description = "JBang catalog to use as the base of the documentation", defaultValue = JBANG_CATALOG)
    String catalog;

    @CommandLine.Option(names = {"-n", "--name"}, description = "Name of the JBang catalog, will be rendered in an H1 text.")
    String catalogName;

    @CommandLine.Option(names = {"-O", "--output"}, description = "Output file name", defaultValue = CATALOG_MD)
    String output;

    @CommandLine.Option(names = {"-T", "--template"}, description = "Template to be used when rendering.", defaultValue = TEMPLATE_CATALOG_MD_QUTE)
    String templateFile;

    @CommandLine.Option(names = {"-cl", "--catalog-logo"}, description = "Catalog logo to be used in the final documentation")
    String catalogLogo;

    @CommandLine.Option(names = {"-rc", "--render-catalogs"}, description = "Whether to render the catalogs section or not", defaultValue = "true")
    boolean renderCatalogs;

    @CommandLine.Option(names = {"-ra", "--render-aliases"}, description = "Whether to render the aliases section or not", defaultValue = "true")
    boolean renderAliases;

    @CommandLine.Option(names = {"-rt", "--render-templates"}, description = "Whether to render the templates section or not", defaultValue = "true")
    boolean renderTemplates;

    @Inject
    CommandLine.IFactory factory;

    @Override
    public void run() {
        System.out.println("Reading catalog " + catalog + " and generating documentation with template: " + templateFile);
        if (catalogName != null && !catalogName.isBlank()) {
            System.out.println("Catalog's name: " + catalogName);
        }
        Path catalogFile = new File(catalog).toPath();
        Path destination = new File(output).toPath();
        try {
            renderCatalogDocumentation(catalogFile, destination);
            System.out.println("Documentation generated to the following file: " + output);
        } catch (IOException e) {
            System.err.println("Error during rendering catalog documentation: " + e);
        }
    }

    private void renderCatalogDocumentation(Path catalogFile, Path destination)
        throws IOException {
        Template template = TemplateEngine.getCatalogTemplate(templateFile);
        Catalog catalog = new Gson().fromJson(Files.newBufferedReader(catalogFile), Catalog.class);
        String result = template
            .data("catalog", catalog)
            .data("catalogLogo", catalogLogo)
            .data("catalogName", catalogName)
            .data("generationDate", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")))
            .data("renderCatalogs", renderCatalogs)
            .data("renderAliases", renderAliases)
            .data("renderTemplates", renderTemplates)
            .render();
        Files.write(destination, result.getBytes());
    }
}