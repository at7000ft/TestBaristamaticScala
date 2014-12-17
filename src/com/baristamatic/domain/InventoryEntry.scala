 
package com.baristamatic.domain

import scala.collection.mutable.Map
 

/**
 * <p> Title: InventoryEntry.java </p>
 * <p> Description:
 *
 * </p>
 * <p> Dec 15, 2012</p>
 * @author RGH
 *
 *
 */

object InventoryEntry {
  var inventoryMap: Map[String, InventoryEntry] = Map()

  def iniData = {
    inventoryMap += ("cocoa" -> new InventoryEntry("Cocoa", "cocoa", 0.90d, 10));
    inventoryMap += ("coffee" -> new InventoryEntry("Coffee", "coffee", 0.75d, 10));
    inventoryMap += ("cream" -> new InventoryEntry("Cream", "cream", 0.25d, 10));
    inventoryMap += ("decafCoffee" -> new InventoryEntry("DecafCoffee", "decafCoffee", 0.75d, 10));
    inventoryMap += ("espresso" -> new InventoryEntry("Espresso", "espresso", 1.10d, 10));
    inventoryMap += ("foamedMilk" -> new InventoryEntry("Foamed Milk", "foamedMilk", 0.35d, 10));
    inventoryMap += ("steamedMilk" -> new InventoryEntry("Steamed Milk", "steamedMilk", 0.35d, 10));
    inventoryMap += ("sugar" -> new InventoryEntry("Sugar", "sugar", 0.25d, 10));
    inventoryMap += ("whippedCream" -> new InventoryEntry("Whipped Cream", "whippedCream", 1.00d, 10));
  }

  def getAll = inventoryMap.values.toList

  def getInventoryEntry(name: String) = inventoryMap(name)
  
  def isIngredientAvailable(name:String, count: Int) = {
    val ie = inventoryMap(name)
    ie != null && ie.count >= count
  }
  
  def consume(recipe:Map[String,Int]) = {
    for ((name, count) <- recipe) {
      var ie = inventoryMap(name)
      var availableCount = ie.count
      if (availableCount >= count)
        ie.count -= count
      else
        ie.count = 0
    } 
  }
  
  def hasIngredientCount(recipe: Map[String, Int]):Boolean = {
//    for ((name,count) <- recipe) {
//      var availableCount = inventoryMap(name).count
//      if (availableCount < count)
//        return false
//    }
    recipe.foreach {case(name,recipeCount) => {
      var availableCount = inventoryMap(name).count
      var recCount = recipeCount
       if (availableCount < recipeCount)
        return false
      }
    }
    return true
  }

}

class InventoryEntry(val displayName: String, val name: String, val cost: Double, var count: Int) {

  override def toString = "InventoryEntry: [displayName=" + displayName + ", name=" + name + ", cost=" + cost + ", count=" + count + "]"

}