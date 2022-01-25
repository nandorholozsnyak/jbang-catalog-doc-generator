# JBang Catalog Generator

JBang catalog generator that can read and generate a "fancy" documentation page about your JBang catalog.

Requires:
- [jbang](https://www.jbang.dev/) (as latest as possible)

See the usage below.

```
Usage: <main class> [-h] [-ra] [-rc] [-rt] [-cl=<catalogLogo>] [-f=<catalog>]
                    [-n=<catalogName>] [-O=<output>] [-T=<templateFile>]
Generates a JBang catalog documentation based on the given jbang-catalog.json
file.
Documentation template can be customized with the -T/--template flags.
Name and logo can be customized with the -n/--name and -cl/--catalog-logo flags.
Catalog, alias and template sections can be turned off conditionally with the
-rc=false, -ra=false, -rt=false flags

      -cl, --catalog-logo=<catalogLogo>
                             Catalog logo to be used in the final documentation
  -f, --file=<catalog>       JBang catalog to use as the base of the
                               documentation
  -h, --help                 Display help/info
  -n, --name=<catalogName>   Name of the JBang catalog, will be rendered in an
                               H1 text.
  -O, --output=<output>      Output file name
      -ra, --render-aliases  Whether to render the aliases section or not
      -rc, --render-catalogs Whether to render the catalogs section or not
      -rt, --render-templates
                             Whether to render the templates section or not
  -T, --template=<templateFile>
                             Template to be used when rendering.

```

# Check the result [here](catalog.md)