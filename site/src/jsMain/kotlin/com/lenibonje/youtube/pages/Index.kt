package com.lenibonje.youtube.pages

import androidx.compose.runtime.*
import com.varabyte.kobweb.compose.css.CSSTransition
import com.varabyte.kobweb.compose.foundation.layout.Arrangement
import com.varabyte.kobweb.compose.foundation.layout.Column
import com.varabyte.kobweb.compose.foundation.layout.Row
import com.varabyte.kobweb.compose.ui.Alignment
import com.varabyte.kobweb.compose.ui.Modifier
import com.varabyte.kobweb.compose.ui.graphics.Colors
import com.varabyte.kobweb.compose.ui.modifiers.*
import com.varabyte.kobweb.compose.ui.toAttrs
import com.varabyte.kobweb.core.Page
import com.varabyte.kobweb.silk.components.forms.Button
import com.varabyte.kobweb.silk.components.icons.fa.FaFaceSmile
import com.varabyte.kobweb.silk.components.icons.fa.IconSize
import com.varabyte.kobweb.silk.components.icons.fa.IconStyle
import com.varabyte.kobweb.silk.components.style.ComponentStyle
import com.varabyte.kobweb.silk.components.style.focus
import com.varabyte.kobweb.silk.components.style.toModifier
import com.varabyte.kobweb.silk.theme.colors.rememberColorMode
import org.jetbrains.compose.web.attributes.InputType
import org.jetbrains.compose.web.attributes.placeholder
import org.jetbrains.compose.web.css.*
import org.jetbrains.compose.web.dom.Input
import org.jetbrains.compose.web.dom.P
import org.jetbrains.compose.web.dom.Text

@Page
@Composable
fun HomePage() {
    Column(
        modifier = Modifier.fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name by remember { mutableStateOf("") }
        var colorMode by rememberColorMode()

        Row(verticalAlignment = Alignment.CenterVertically) {
            FaFaceSmile(
                modifier = Modifier
                    .color(if (colorMode.isLight) Colors.Black else Theme.Blue.color)
                    .margin(right = 10.px),
                style = IconStyle.OUTLINE,
                size = IconSize.XXL
            )

            P(
                attrs = Modifier.fontSize(40.px).toAttrs()
            ) {
                Text(value = "Hello, $name")
            }
        }

        Input(
            type = InputType.Text,
            attrs = InputStyle.toModifier()
                .padding(topBottom = 10.px, leftRight = 20.px)
                .outline(
                    width = 0.px,
                    style = LineStyle.None,
                    color = Colors.Transparent
                )
                .backgroundColor(Theme.Gray.color)
                .fontSize(20.px)
                .transition(CSSTransition(property = "border", duration = 300.ms))
                .toAttrs {
                    placeholder("Enter name")
                    onInput { name = it.value }
                }
        )

        Button(modifier = Modifier.margin(top = 24.px),
            onClick = { colorMode = colorMode.opposite }
        ) {
            Text(value = "Change Theme")
        }
    }


}

val InputStyle by ComponentStyle {
    base {
        Modifier.border(
            width = 2.px,
            style = LineStyle.Solid,
            color = Theme.Gray.color
        )
    }

    focus {
        Modifier.border(
            width = 2.px,
            style = LineStyle.Solid,
            color = Theme.Blue.color
        )
    }
}

enum class Theme(val color: CSSColorValue) {
    Gray(color = rgb(r = 238, g = 238, b = 238)),
    Blue(color = rgb(r = 28, g = 181, b = 224)),
}