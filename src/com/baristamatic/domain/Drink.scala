/**
 *
 */
package com.baristamatic.domain

import scala.collection.mutable.Map
  

/**
 * <p> Title: Drink.java </p>
 * <p> Description:
 *
 * </p>
 * <p> Dec 14, 2012</p>
 * @author RGH
 *
 *
 */

//The companion object to the Drink class, everything is static
object Drink {
  var drinkMap: Map[String, Drink] = Map();
  
  //A method without parameters can be defined "def initData = {" or "def initData() = {"
  def initData = {
    var recipe: Map[String, Int] = Map()
    // Caffe Americano
    recipe += ("espresso" -> 3)
    drinkMap += ("caffeAmericano" -> new Drink(1, "caffeAmericano", "Caffe Americano", recipe))
    // Caffe Latte
    recipe = Map()
    recipe += ("espresso" -> 2)
    recipe += ("steamedMilk" -> 1)
    drinkMap += ("caffeLatte" -> new Drink(2, "caffeLatte", "Caffe Latte", recipe));
    // Caffe Mocha
    recipe = Map()
    recipe += ("espresso" -> 1);
    recipe += ("cocoa" -> 1);
    recipe += ("steamedMilk" -> 1);
    recipe += ("whippedCream" -> 1);
    drinkMap += ("caffeMocha" -> new Drink(3, "caffeMocha", "Caffe Mocha", recipe));
    // Cappuccino
    recipe = Map()
    recipe += ("espresso" -> 2);
    recipe += ("steamedMilk" -> 1);
    recipe += ("foamedMilk" -> 1);
    drinkMap += ("cappuccino" -> new Drink(4, "cappuccino", "Cappuccino", recipe));
    // Coffee
    recipe = Map()
    recipe += ("coffee" -> new Integer(3));
    recipe += ("sugar" -> new Integer(1));
    recipe += ("cream" -> new Integer(1));
    drinkMap += ("coffee" -> new Drink(5, "coffee", "Coffee", recipe));
    // DecafCoffee
    recipe = Map()
    recipe += ("decafCoffee" -> new Integer(3));
    recipe += ("sugar" -> new Integer(1));
    recipe += ("cream" -> new Integer(1));
    drinkMap += ("decafCoffee" -> new Drink(6, "decafCoffee", "DecafCoffee", recipe));
  }
  
  def getAll(): List[Drink] = {
    drinkMap.values.toList
  }
  
  def getDrink(name: String) = {
    drinkMap(name)
  }
}

/*
 * class definition with the primary constructor. val/var constructor parameters are automatically
 * added as public val/var instance variables.
 * If constructor parameters are not declared var or val then they are just temp parameters use during construction.
 */
class Drink(val number: Int, val name: String, val displayName: String, val recipe: Map[String, Int]) {
  var inStock: Boolean = _
  var cost: BigDecimal = _
  //Don't need {} if just returning a value
  override def  toString = "[Drink: number=" + number + " name=" + name + " inStock=" + inStock + " cost=" + cost + "]"
    
  
}