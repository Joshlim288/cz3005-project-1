# cz3005-project-1
## Execution Instructions
To run the project, navigate to `cz3005-project-1/out` and run `java -cp "lib/*;." main`

This requires the following `java -version`:

```
openjdk version "16.0.2" 2021-07-20
OpenJDK Runtime Environment Temurin-16.0.2+7 (build 16.0.2+7)
OpenJDK 64-Bit Server VM Temurin-16.0.2+7 (build 16.0.2+7, mixed mode, sharing)
```

If you encounter a linkage error, you will need either need to update your OpenJDK or recompile the project as follows:

Navigate to `cz3005-project-1/src` and run `javac -cp "lib/*;." -d ../out  *.java`

Navigate to `cz3005-project-1/out` and run `java -cp "lib/*;." main`

If you wish to change the data used, navigate to `cz3005-project-1/out/data` and modify the json files there

## Project Structure
Source code is located in the src folder

Output class files and data are located in the out folder
