test-argonaut-json
==================

Test the [typelevel.org](http://typelevel.org/) [argonaut](http://argonaut.io/) library for purely functional json in Scala.

This is a very simple way to parse and serialise json in scala. Works with case classes (your typical ADT's), nested case classes with the use of simple codec implicits.

To-do: Need to compare this to the Play2 json libray. The Play2 library can also handle class hierarchies from a sealed trait using the extra library: "org.julienrf" %% "play-json-variants" % "0.2".
See my test-scalaz repo (TestJsonPrettyPrint.scala, EndoFluent.scala) for using Play2 json.
