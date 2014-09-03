name := "test-argonaut-json"
 
version := "1.0"
 
scalaVersion := "2.11.1"

resolvers += "Typesafe Repo" at "http://repo.typesafe.com/typesafe/releases/"

libraryDependencies += "org.scalaz" %% "scalaz-core" % "7.1.0"

libraryDependencies += "org.scalaz" %% "scalaz-effect" % "7.1.0"

libraryDependencies += "io.argonaut" %% "argonaut" % "6.1-M4" changing()
