class CoffeeMachine {

    var state: String = "none"
    var num = 0
    var flag = false
    var input = "none"
    var counter = 0

    var mlOfWater = 400
    var mlOfMilk = 540
    var gOfCoffeeBeans = 120
    var disposableCups = 9
    var money = 550

    enum class inputState {
        action,
        quantity
    }

    fun clearFields() {
        state = "none"
        num = 0
        flag = false
        input = "none"
        counter = 0
    }

    enum class states {
        buy, fill, take, remaining
    }

    fun isDigitPresent(currencyCode: String): Boolean {
        val chars = currencyCode.toCharArray()
        for (character in chars) {
            if (Character.isDigit(character)) {
                return true
            }
        }
        return false
    }

    fun handleAction(action: String) {
        var actionState = isDigitPresent(action)
        when (actionState) {
            true -> {
                num = action.toString().toInt()
                input = inputState.quantity.name
            }
            false -> {
                state = action.toString()
                input = inputState.action.name
            }
        }
        if (input == inputState.action.name ) {
            when (action) {
                states.buy.name -> buy()
                states.fill.name -> fill()
                states.take.name -> take()
                states.remaining.name -> remaining()
            }
        } else if (input == inputState.quantity.name) {
            when (state) {
                states.buy.name -> buy()
                states.fill.name -> {
                    counter++
                    fill()
                }
            }
        }
    }

    fun buy() {
        if (!flag && num == 0) {
            print("\nWhat do you want to buy? 1 - espresso, 2 - latte, 3 - cappuccino, back - to main menu:")
            return
        }
        when (num.toString()) {
            "1" -> {
                if (mlOfWater < 250)
                    println("Sorry, not enough water!\n")
                else if (gOfCoffeeBeans < 16)
                    println("Sorry, not enough coffee beans!\n")
                else {
                    mlOfWater -= 250
                    gOfCoffeeBeans -= 16
                    money += 4
                    disposableCups--
                    println("I have enough resources, making you a coffee!\n")
                }
            }
            "2" -> {
                if (mlOfWater < 350)
                    println("Sorry, not enough water!\n")
                else if (mlOfMilk < 75)
                    println("Sorry, not enough milk!\n")
                else if (gOfCoffeeBeans < 20)
                    println("Sorry, not enough coffee beans!\n")
                else {
                    mlOfWater -= 350
                    mlOfMilk -= 75
                    gOfCoffeeBeans -= 20
                    money += 7
                    disposableCups--
                    println("I have enough resources, making you a coffee!\n")
                }
            }
            "3" -> {
                if (mlOfWater < 200)
                    println("Sorry, not enough water!\n")
                else if (mlOfMilk < 100)
                    println("Sorry, not enough milk!\n")
                else if (gOfCoffeeBeans < 12)
                    println("Sorry, not enough coffee beans!\n")
                else {
                    mlOfWater -= 200
                    mlOfMilk -= 100
                    gOfCoffeeBeans -= 12
                    money += 6
                    disposableCups--
                    println("I have enough resources, making you a coffee!\n")
                }
            }
            "back" -> return
        }
        clearFields()
    }

    fun fill() {
        when (this.counter) {
            0 -> print("Write how many ml of water do you want to add:")
            1 -> {
                mlOfWater += num
                print("Write how many ml of milk do you want to add:")
            }
            2 -> {
                mlOfMilk += num
                print("Write how many grams of coffee beans do you want to add:")
            }
            3 -> {
                gOfCoffeeBeans += num
                print("Write how many disposable cups of coffee do you want to add:")
            }
            4 -> {
                disposableCups += num
                clearFields()
            }
            else -> return
        }
    }

    fun take() {
        println("\nI gave you $$money\n")
        money = 0
        clearFields()
    }

    fun remaining() {
        println("\nThe coffee machine has:")
        println("$mlOfWater ml of water")
        println("$mlOfMilk ml of milk")
        println("$gOfCoffeeBeans g of coffee beans")
        println("$disposableCups disposable cups")
        println("$$money of money\n")
        clearFields()
    }

}

    fun main() {
        var action = "none"
        val machine = CoffeeMachine()
        while (action != "exit") {
            if (machine.state == "none")
                print("Write action (buy, fill, take, remaining, exit):")
            action = readLine()!!.toString()
            machine.handleAction(action)
        }
    }



