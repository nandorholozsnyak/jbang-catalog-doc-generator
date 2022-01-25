

<p align="center">
  <img src="https://raw.githubusercontent.com/jbangdev/jbang/main/images/jbang_logo.svg">
</p>

### Catalogs

**Catalog reference**: https://github.com/jbangdev/jbang-catalog/blob/HEAD/jbang-catalog.json <br>

---


**Catalog reference**: https://github.com/nandorholozsnyak/jbang-catalog/tree/feature/aws-lambda-templates <br>

---




### Aliases
##### cli
###### Usage: `jbang cli [args...]`
**Script reference**: https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/2.6.1.Final/quarkus-cli-2.6.1.Final-runner.jar <br>

---

##### qs
###### Usage: `jbang qs [args...]`
**Script reference**: https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/2.6.1.Final/quarkus-cli-2.6.1.Final-runner.jar <br>

---

##### quarkus
###### Usage: `jbang quarkus [args...]`
**Script reference**: https://repo1.maven.org/maven2/io/quarkus/quarkus-cli/2.6.1.Final/quarkus-cli-2.6.1.Final-runner.jar <br>

---


### Templates
##### q-aws-lambda-tf
###### Usage: `jbang init -t=q-aws-lambda-tf script.java [args...]`
**Description**: Quarkus AWS Lambda template with Terraform template. Use the -Dnative-function flag to have native image based Terraform resources<br><br>
**File references**:
- *{filename}* - [aws/aws-lambda.java.qute](aws/aws-lambda.java.qute)
- *generate-jar.sh* - [aws/generate-jar.sh](aws/generate-jar.sh)
- *generate-native-image.sh* - [aws/generate-native-image.sh](aws/generate-native-image.sh)
- *application.properties* - [aws/application.properties](aws/application.properties)
- *lambda-aws.tf* - [aws/lambda-aws.tf.qute](aws/lambda-aws.tf.qute)

**Template properties**:
- *tf-providers* - *If enabled extra Terraform related providers will be generated (false)*
- *tf-provider.aws.version* - *Version of the AWS Terraform provider (3.71.0)*
- *tf-provider.archive.version* - *Version of the Archive Terraform provider (2.2.0)*
- *tf-provider.null.version* - *Version of the NULL Terraform provider (3.1.0)*
- *tf-provider.aws.region* - *AWS Region (eu-central-1)*
- *native-function* - *Generate native executable based lambda function or not (false)*
---

<h4 style="text-align: center;">Created with JBang Catalog @ 2022-01-25 21:16</h4>
