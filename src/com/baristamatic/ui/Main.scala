
package com.baristamatic.ui
import com.baristamatic.service.DrinkService
import com.baristamatic.domain.Drink
import java.util.Scanner

/**
 * <p> Title: Main.java </p>
 * <p> Description:
 *
 * </p>
 * <p> Dec 14, 2012</p>
 * @author RGH
 *
 *
 */

object Main extends App {
  val RESTOCK_COMMAND_STRING = "r"
  val QUIT_COMMAND_STRING = "q"
  var validDrinkNumbers: List[String] = List()
  val validCommands = List(RESTOCK_COMMAND_STRING, QUIT_COMMAND_STRING)

  init
  mainLoop

  def init = {
    DrinkService.initData()
    initValidDrinkNumbers(DrinkService.getAllDrinks)
  }

  def initValidDrinkNumbers(drinks: List[Drink]) = {
    //Lists are immutable, ::= adds new element to copy of list and returns ref)
    drinks.foreach { drink => validDrinkNumbers ::= drink.number.toString() }
  }

  //Scala has no continue or break keyword
  def mainLoop = {
    var commandString = ""
    val input: Scanner = new Scanner(System.in)
    while (true) {
      showInventory()
      showMenu()
      commandString = input.nextLine().trim();
      if (isValidEntry(commandString)) {
        commandString match {
          case QUIT_COMMAND_STRING => System.exit(0)
          case RESTOCK_COMMAND_STRING => DrinkService.reStock
          case _ => createDrink(commandString)
        }
      } else {
        println("Invalid selection: " + commandString)
      }
    }
  }

  def createDrink(commandString: String) = {
    val drink = DrinkService.getDrink(commandString.toInt)
    if (DrinkService.createDrink(commandString.toInt)) {
      println("Dispensing: " + drink.displayName + "\n");
    } else {
      println("Out of stock: " + drink.displayName + "\n");
    }
  }

  def isValidEntry(entry: String): Boolean = {
    if ((entry.length() == 1 && validCommands.contains(entry)) || ((entry forall Character.isDigit) && validDrinkNumbers.contains(entry))) {
      return true //valid
    }
    return false //invalid
  }
  
  def showInventory() = {
    val inventory = DrinkService.getInventory
    println("Inventory:")
    inventory.foreach(inv => println(inv.displayName + " - " + inv.count))
  }
  
  def showMenu() = {
    println("\nMenu:")
    val drinks = DrinkService.getAllDrinks
    drinks.foreach(drink => println(drink.number + "," + drink.displayName + ",$" + drink.cost + "," + drink.inStock))
  }
}