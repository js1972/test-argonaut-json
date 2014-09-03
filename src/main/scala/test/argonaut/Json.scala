package test.argonaut

import scalaz._, Scalaz._
import argonaut._, Argonaut._
import java.util.{Date, Calendar}

/**
 * SIMPLE EXAMPLE USING ARGONAUT (PURELY FUCNTIONAL JSON LIBRARY).
 * 
 * See http://argonaut.io/ part of typelevel.org
 * 
 * Be careful with compbining scalaz and argonaut.
 * Need to use "io.argonaut" %% "argonaut" % "6.1-M4" changing() to work with 
 * "org.scalaz" %% "scalaz-core" % "7.1.0".
 * 
 * 
 * To use argonaut we need to provide implicit conversions (codecs) for the types.
 * As can be seen below - for simple types this is pretty basic -> see
 * def PersonCodecJson.
 * 
 * With the Person2 case class however we use a field of type java.util.Date. I 
 * has to provide an implicit encoder and decoder for this (DateEncodeJson, 
 * DateDecodeJson). I use ```format.parse(_: String)``` as the "apply" method
 * for java.util.Date.
 * If we wrap types in Options argonaut seems to automatically handle it.
 * 
 * If we use nested case classes, so long as we define the implicit code for
 * them it all works.
 * 
 * See the Stuff and Person2 example below.
 */
object Json {

  def main(args: Array[String]): Unit = {  
    
    case class Person(name: String, age: Int, favourites: List[String])
    
    implicit def PersonCodecJson: CodecJson[Person] = 
      casecodec3(Person.apply, Person.unapply)("name", "age", "favourites")
      
    val p = Person("Jason Scott", 42, List("Red", "Green", "Blue"))
    p.favourites foreach println
    
    val json: Json = p.asJson
    
    println
    println(json.spaces2)
    
    println
    println
    
    // ADT's
    case class Stuff(first: String, second: String)
    case class Person2(id: Option[Long] = None, firstName: String, lastName: String, stuff: Stuff, birthDate: Option[Date] = None)
    
    val format = new java.text.SimpleDateFormat("dd-MM-yyyy")

    // codecs
    implicit def DateEncodeJson: EncodeJson[Date] = 
      jencode1L((d: Date) => d.toString())("date")  // in real life we'd use unix epoch time and not simply toString
    implicit def DateDecodeJson: DecodeJson[Date] = 
      jdecode1(format.parse(_: String))
    implicit def StuffCodecJson: CodecJson[Stuff] =
      casecodec2(Stuff.apply, Stuff.unapply)("first", "second")
    implicit def Person2CodecJson: CodecJson[Person2] = 
      casecodec5(Person2.apply, Person2.unapply)("id", "first_name", "last_name", "stuff", "birth_date")
      
    val now = Calendar.getInstance().getTime() 

    // Convert to json
    println(Person2(None, "Test", "Tester", Stuff("one", "two")).asJson.spaces2)
    println(Person2(Some(1), "Jason", "Scott", Stuff("one", "two"), now.some).asJson.spaces2)
  }

}