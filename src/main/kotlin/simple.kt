import kotlinx.browser.document
import kotlinx.browser.window

fun main() {
    window.onload = {
        onLoad()
    }
}

fun onLoad() {
    val root = document.getElementById("root") ?: return
    root.textContent = "This is a text in the root DIV"
    root.setAttribute("style", "color: red;")
}
