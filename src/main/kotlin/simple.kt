import kotlinx.browser.document
import kotlinx.browser.window
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlinx.html.InputType
import kotlinx.html.button
import kotlinx.html.dom.append
import kotlinx.html.id
import kotlinx.html.input
import kotlinx.html.js.a
import kotlinx.html.js.b
import kotlinx.html.js.h1
import kotlinx.html.js.i
import kotlinx.html.js.onClickFunction
import kotlinx.html.js.p
import kotlinx.html.js.span
import kotlinx.html.style
import org.w3c.dom.Element
import org.w3c.dom.HTMLButtonElement
import org.w3c.dom.HTMLInputElement

fun main() {
    window.onload = {
        onLoad()
    }
}

fun onLoad() {
    val root = document.getElementById("root") ?: return
    root.textContent = "This is a text in the root DIV"
    root.setAttribute("style", "color: black;")

    console.log(root)
    println(root.innerHTML)

    root.append {
        h1 { +"This is a header" }
        p {
            +"This is a paragraph with "
            b { +"bold" }
            +" and "
            i { +"italic" }
            +" and "
            b {
                i {
                    span("a-span") { +"bold-italic-color" }
                }
            }
            +" and "
            span {
                style = "color: blue; font-size: 30px; font-family: monospace"
                +"a big mono text"
            }
            +" and "
            a {
                href = "//google.com"
                target = "_blank"
                +"a link"
            }
        }

        p {
            button {
                onClickFunction = {
                    window.alert("Clicked!")
                    (it.target as HTMLButtonElement).textContent = "You clicked me!"
                }
                +"Click me"
            }
        }
        p {
            input(InputType.button) {
                value = "Click me 2"
                onClickFunction = {
                    (it.target as HTMLInputElement).value = "You clicked me!"
                }
            }
        }

        p("counter") { id = "counter-interval" }

        p("counter") { id = "counter-coroutines" }
    }

    val intervalCounterElement = document.getElementById("counter-interval")!!
    var intervalCounter = 0
    window.setInterval({
        intervalCounterElement.textContent = "Interval: $intervalCounter"
        intervalCounter += 1
    }, 500)

    GlobalScope.launch {
        count(document.getElementById("counter-coroutines")!!)
    }
}

private suspend fun count(element: Element) = withContext(Dispatchers.Main){
    var counter = 0
    while (true) {
        element.textContent = "Coroutines: $counter"
        delay(500)
        counter += 1
    }
}
