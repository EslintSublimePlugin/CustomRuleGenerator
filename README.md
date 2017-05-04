Custom Rule Generator
====================================
  This is a standalone custom rule generator tool developed using javaFX in java. It is used to generate a template based on
rule name, rule description, error flag and selectors. Documentation for using each selector is also generated while generating template.</br>
Currently it generates 4 files 

- Rule file
- Test file
- Rule configuration file ( This file has test rule name that needs to be applied on test file)
- Rule metadata file (This file contains basic info. about rule)

All of those files are created and are put under TEST/RULENAME/ directory.</br>
TEST directory is located in the same folder as the jar file.

License
-------
Apache License, Version 2.0


Dependencies
-------
Java : version >= 1.8.x <br />
Note : Make sure java is available in the path

Usage
------
Download Jar file from here [RuleGenerator.jar](https://github.com/EslintSublimePlugin/CustomRuleGenerator/blob/master/out/artifacts/RuleImport_jar/RuleImport.jar?raw=true) </br>
Execute the following command

```java

    java -jar JAR_FILE_NAME
    
```
