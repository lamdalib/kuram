# Kuram

<p align="center">
    <img src="/docs/icon.jpeg" width="256" height="256" />
</p>

Minimal Scala library for functional programming.

# Getting Kuram
Right now, Kuram is not a published project. If you want to use the library,
you should clone the project and publish it locally as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> publishLocal
...
[info]  published ivy to /home/USER/.ivy2/local/io.github.kattulib/kuram_3/0.1.0-SNAPSHOT

# on bash
$ sbt publishLocal
...
[info]  published ivy to /home/USER/.ivy2/local/io.github.kattulib/kuram_3/0.1.0-SNAPSHOT
```

Then you can add the library in the library dependencies as others.
```scala
libraryDependencies += "io.github.kattulib" %% "kuram" % "0.1.0-SNAPSHOT"
```

# Run Tests
You can run all tests as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> tests/test

# on bash
$ sbt tests/test
```

If you want to run specific test:
```bash
# on interactive sbt shell
sbt:kuram> tests/testOnly *MonoidSuite

# on bash
$ sbt "tests/testOnly *MonoidSuite"
```

# Run Examples
You can run all examples as on the following command:
```bash
# on interactive sbt shell
sbt:kuram> example/run
Multiple main classes detected. Select one to run:
 [1] kuram.example.combineMaps
 [2] kuram.example.combineOptions
 ...

# on bash
$ sbt "example/run"
```

If you want to run specific example:
```bash
# on interactive sbt shell
sbt:kuram> example/runMain kuram.example.combineOptions
[info] running kuram.example.combineOptions
Obtained: Some(6), Expected: Some(6)
Obtained: Some(30), Expected: Some(30)
Obtained: None, Expected: None

# on bash
$ sbt "example/runMain kuram.example.combineOptions"
...
```
