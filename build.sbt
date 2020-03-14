import java.io.File

// Name of the project
name := "JavaFX 14 Hello World"

// Project version
version := "1.0.0-SNAPSHOT"

// Version of Scala used by the project
scalaVersion := "2.13.1"

// Add dependency on JavaFX library

scalacOptions ++= Seq(
  "-unchecked",
  "-deprecation",
  "-Xcheckinit",
  "-encoding", "utf8",
  "-feature"
)

// Fork a new JVM for 'run' and 'test:run', to avoid JavaFX double initialization problems.
fork := true

// Determine OS version of JavaFX binaries
lazy val osName = System.getProperty("os.name") match {
  case n if n.startsWith("Linux") => "linux"
  case n if n.startsWith("Mac") => "mac"
  case n if n.startsWith("Windows") => "win"
  case _ => throw new Exception("Unknown platform!")
}

// Add JavaFX dependencies
lazy val javaFXModules = Seq("base", "controls", "fxml", "graphics", "media", "swing", "web")
libraryDependencies ++= javaFXModules.map {m=>
  "org.openjfx" % s"javafx-$m" % "14" classifier osName
}

// When forking, we need to tell the running process how to find the necessary modules (SBT currently doesn't appear to
// do this for us, which is very odd):
//
// This clearly isn't a solution for running a finished product. It might be worth looking at the "sbt-native-packager"
// plugin to verify whether that addresses the issue of installing and running a product on a user's machine.
val javaFxModulesPaths = taskKey[String]("Get the joined paths to OpenJFX jars.")

javaFxModulesPaths := {
  val files = update.value.select(module = moduleFilter(
      organization = "org.openjfx",
    ))
  files.map(_.absolutePath).mkString(File.pathSeparator)
}

javaOptions ++= Seq(
  "--module-path", javaFxModulesPaths.value,
  "--add-modules", "ALL-MODULE-PATH"
)
