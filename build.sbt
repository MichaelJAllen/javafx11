import java.io.File

// Name of the project
name := "JavaFX 11 Hello World"

// Project version
version := "1.0.0-SNAPSHOT"

// Version of Scala used by the project
scalaVersion := "2.13.0"

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
  "org.openjfx" % s"javafx-$m" % "11" classifier osName
}
