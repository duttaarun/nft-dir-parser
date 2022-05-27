# nft-dir-parser
Parses directory of  CID and pushes the output to excel

- `mvn clean package` - Will compile the classes and build jar
- Export the runnable jar using STS or eclipse (Maven assembly build will be configured soon). Make sure you put in the log4j2.xml from src/main/resources separately inside the jar as STS excludes that.
- `java -jar <jar-name>.jar <cid-of-uploaded-files> <template-name>` - Run the jar with the CID hash of the folder container files and template xlsx file name.
- The template file is provided with the project.

