package part2oop

import scala.language.postfixOps

object MethodNotation {

  class Person(val name: String, val age: Int, favoriteMovie: String):

    // ------------------- Binary Operators --------------------
    // 'likes' can be used as an infix operator. No need for 'infix' keyword
    def likes(movie: String): Boolean =
      movie == favoriteMovie

    // Another example of a custom-defined binary operator. No need for 'infix' keyword
    def !!(progLanguage: String): String =
      s"$name wonders how $progLanguage can be so cool!"

    // Exercise 1: + operator that returns a person with a nickname
    def +(nickname: String) = new Person(s"$name $nickname", age, favoriteMovie)

    // ------------------- Unary Operators --------------------
    // Unary operators. Can only be defined on methods that don't take any parameters
    // Possible unary operators are +, -, ~, !

    def unary_- : String = s"$name's alter ego!"

    // Exercise 2: A unary + operator that increments a person's age by 1
    def unary_+ : Person = new Person(name, age + 1, favoriteMovie)

    // ------------------- Postfix Operators --------------------
    // Postfix operator (use of postfix operators is highly discouraged)
    // Deprecated since Scala 2.13. Also deprecated in Scala 3.
    // needs import scala.language.postfixOps
    def isAlive: Boolean = true

    // ------------------- Apply Method --------------------
    // Allows an object to be invoked like a function.
    // Example of the apply method. The empty parens () are needed even though
    // it has no arguments, otherwise there's a compiler error
    def apply(): String =
      s"Hi, my name is $name and I really enjoy $favoriteMovie"

    // Exercise 3. An apply method with an Int arg indicating how many times the person watched their favorite movie
    def apply(times: Int): String =
      s"$name watched $favoriteMovie $times times"

  end Person

  //---------------------------------------------------------------------------
  val mary: Person          = Person("Mary", 21, "Sholay")

  def main(args: Array[String]): Unit = {
    println(mary likes "Sholay")  // true
    println(mary.likes("Sholay")) // true
    println(-mary)                // Mary's alter ego!
    println(mary !! "Haskell")    // Mary wonders how Haskell can be so cool!
    println(mary isAlive)         // true (example of postfix operator)

    // apply method
    println(mary.apply())         // Hi, my name is Mary and I really enjoy Sholay
    println(mary())               // Hi, my name is Mary and I really enjoy Sholay

    // Exercises
    val maryWithNickname: Person = mary + "is a rock star"
    println(maryWithNickname.name) // Mary is a rock star

    val olderMary: Person = +mary
    println(olderMary.age) // 22

    println(mary.apply(2))  // Mary watched Sholay 2 times
    println(mary(2))        // Mary watched Sholay 2 times

  }
}
