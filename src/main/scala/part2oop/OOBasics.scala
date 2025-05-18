package part2oop

/**
 * Key takeaways
 * 1. The 'val' keyword in an argument of a class constructor marks it as
 *    a field of the class; otherwise it's just a constructor argument.
 * 2. Auxiliary constructors are named 'this'. They are seldom used in
 *    Scala because we can instead use default values for class constructor
 *    arguments.
 * 3. Comparing two class instances for equality
 */
object OOBasics {

  // The 'val' keyword creates it as a field of the class; without it the code
  // aPerson.name won't compile, as 'name' will just be a constructor
  // argument, nothing more. (age is just a constructor argument.)
  class Person (val name: String, age: Int) { // constructor signature

    // fields in ADDITION to constructor arguments
    val allCaps = name.toUpperCase()

    // methods
    def greet (name: String): String = s"${this.name} says: Hi, $name"

    // overloaded method
    def greet: String = s"Hi, my name is {name}"

    // auxiliary constructors
    def this(name: String) = // constructor with just the name
      this(name, 0) // auxiliary constructors can only be defined in terms of other constructors

    // Another auxiliary constructor defined in terms of the previous aux constructor
    def this() = this("Jane Doe")

    // Auxiliary constructors are rarely used in Scala because it's the same
    // as using default values in the constructor arguments
  }

  val aPerson = new Person ("John", 21)
  val john = aPerson.name // class parameter != field
  val johnSayHiToDaniel = aPerson.greet("Daniel")
  val johnSaysHi = aPerson.greet
  val genericPerson = new Person()

  //---------------------------------------------------------------------------
  def main(args: Array[String]): Unit = {

    // Exercise 1
    val charlesDickens = new Writer("Charles", "Dickens", 1812)
    val charlesDickensImpostor = new Writer("Charles", "Dickens", 2021)

    val novel = new Novel("Great Expectations", 1861, charlesDickens)
    val newEdition = novel.copy(1871)

    println(charlesDickens.fullName) // Charles Dickens
    println(novel.authorAge) // 49
    println(novel.isWrittenBy(charlesDickensImpostor)) // false
    println(novel.isWrittenBy(charlesDickens)) // true
    println(newEdition.authorAge) //59

    // Exercise 2.
    val counter = new Counter()
    counter.print() // 0
    counter.increment().print() // 1
    counter.increment() // always returns new instances
    counter.print() // 0

    counter.increment(10).print() // 10
    counter.increment_v2(200000).print() // can cause stack overflow due to stack-recursive call
  }
}

//---------------------------------------------------------------------------
// Exercise 1

class Writer(firstName: String, lastName: String, val yearOfBirth: Int):
  def fullName: String = s"$firstName $lastName"
end Writer

class Novel(val title: String, val yearOfRelease: Int, author: Writer) {

  def authorAge: Int = yearOfRelease - author.yearOfBirth

  // Returns true if the novel is written by the given author; false otherwise
  def isWrittenBy(author: Writer): Boolean = author == this.author

  def copy(newYear: Int): Novel = new Novel(this.title, newYear, this.author)
}

//---------------------------------------------------------------------------
// Exercise 2. Immutable Counter

/**
 * Key points:
 * 1. Don't make the count a field of the class, which means don't use a val in the constructor
 * 2. Set default counter value to 0.
 * 3. Clamp the counter below at 0.
 */
class Counter(count: Int = 0):
  def increment(): Counter        = new Counter(count + 1)

  def decrement(): Counter =
    if (count == 0) this // clamp counter at 0
    else            new Counter(count - 1)

  // We wouldn't have needed the if condition check if only Scala had a natural number
  // type Nat as in Idris or Unison
  def increment(n: Int): Counter = {
    if (n <= 0) this // Don't allow negative increments (which are decrements)
    else        new Counter(count + n)
  }

  // Alternative implementation: Stack-recursive calls to increment one at a time
  // Crashes with stack overflow if we call with a large number, say n = 20,000
  def increment_v2(n: Int): Counter =
    if (n <= 0) this // Don't allow negative increments (which are decrements)
    else        increment().increment(n - 1)

  // My implementation
  def decrement(n: Int): Counter = {
    if (n <= 0)               this  // Don't allow negative decrements, as they amount to increments
    else if (count - n <= 0)  new Counter(0)
    else                      new Counter(count - n)
  }

  // Instructor's implementation
  def decrement_v2(n: Int): Counter =
    if (n <= 0) this
    else        decrement().decrement(n - 1)

  // print method has empty parens even though it has no arguments because it's
  // an action method, unlike getters. That's a convention
  def print(): Unit = println(s"Current count: $count")


