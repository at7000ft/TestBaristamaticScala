
package com.baristamatic.service

import com.baristamatic.domain.Drink

import com.baristamatic.domain.InventoryEntry
import scala.collection.mutable.Map

/**
 * <p> Title: DrinkService.java </p>
 * <p> Description:
 *
 * </p>
 * <p> Dec 14, 2012</p>
 * @author RGH
 *
 *
 */

object DrinkService {
  var drinkNumberRefMap: Map[Int, String] = Map()

  def initData() = {
    Drink.initData
    InventoryEntry.iniData
    Drink.getAll().foreach { e =>
      drinkNumberRefMap += (e.number -> e.name)
      e.cost = getDrinkCost(e.recipe)
    }
  }

  def getAllDrinks: List[Drink] = {
    var drinks = Drink.getAll()
    drinks.foreach { d =>
      d.inStock = InventoryEntry.hasIngredientCount(d.recipe)
    }
    drinks
  }

  def getDrinkCost(recipe: Map[String, Int]): BigDecimal = {
    var totalCost: BigDecimal = BigDecimal(0, 2)

    for ((name, count) <- recipe) {
      var cost = InventoryEntry.getInventoryEntry(name).cost
      totalCost += cost * count
    }
    totalCost
  }
  
  def getDrink(number:Int) = {
    Drink.getDrink(drinkNumberRefMap(number));
  }
  
  def getInventory = {
    InventoryEntry.getAll
  }

  /**
   * Checks for ingredient availablity and decrements inventory if available.
   * Would make synchronized if service would be accessed by multiple client threads (like a servlet),
   * not needed for this example.
   *
   * @param number the number
   * @return true if ingredients available and consumes ingredients, else returns false
   */
  def createDrink(number: Int): Boolean = {
    var drink = Drink.getDrink(drinkNumberRefMap(number))
    if (!InventoryEntry.hasIngredientCount(drink.recipe))
      return false
    else
      InventoryEntry.consume(drink.recipe)
    return true

  }

  def reStock = initData

}