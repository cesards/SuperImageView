# Tooling

## Table of contents

- Gradle Versions Plugin
- Checkstyle
- Lint


## [Gradle Versions Plugin](https://github.com/ben-manes/gradle-versions-plugin)

This plugin provides a task to determine which dependencies have updates. Additionally, the plugin checks for updates to Gradle itself.

Run:
```
./gradlew dependencyUpdates
```

to display a report of the project dependencies that are up-to-date, exceed the latest version found, have upgrades, or failed to be resolved. When a dependency cannot be resolved the exception is logged at the info level.


## Checkstyle

Execute `./gradlew checkstyle`


## Lint

- Every module has its correspondant lint.xml files if needed.

To run lint, you can use:
```
gradlew lint --profile
```
or
```
gradlew lint --profile
```
this will generate reports under the `<module>/build/reports/lint-results.xml`.