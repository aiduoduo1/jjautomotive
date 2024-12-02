package com.jj.automotive.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

/**
 * 车载专用按钮
 * 
 * @param text 按钮文本
 * @param onClick 点击回调
 * @param modifier 修饰符
 * @param enabled 是否启用
 * @param isDriving 是否在驾驶模式
 */
@Composable
fun CarButton(
    text: String,
    onClick: () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isDriving: Boolean = false
) {
    val buttonHeight = if (isDriving) 72.dp else 56.dp
    val fontSize = if (isDriving) 20.sp else 16.sp
    val horizontalPadding = if (isDriving) 32.dp else 24.dp

    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = buttonHeight)
            .defaultMinSize(minWidth = 200.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary,
            disabledContainerColor = MaterialTheme.colorScheme.surfaceVariant,
            disabledContentColor = MaterialTheme.colorScheme.onSurfaceVariant
        ),
        shape = MaterialTheme.shapes.medium
    ) {
        Text(
            text = text,
            fontSize = fontSize,
            fontWeight = FontWeight.Medium,
            modifier = Modifier.padding(horizontal = horizontalPadding)
        )
    }
}

/**
 * 带图标的车载按钮
 */
@Composable
fun CarIconButton(
    text: String,
    onClick: () -> Unit,
    icon: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    enabled: Boolean = true,
    isDriving: Boolean = false
) {
    val buttonHeight = if (isDriving) 72.dp else 56.dp
    val fontSize = if (isDriving) 20.sp else 16.sp
    val spacing = if (isDriving) 16.dp else 12.dp

    Button(
        onClick = onClick,
        modifier = modifier
            .heightIn(min = buttonHeight)
            .defaultMinSize(minWidth = 200.dp),
        enabled = enabled,
        colors = ButtonDefaults.buttonColors(
            containerColor = MaterialTheme.colorScheme.primary,
            contentColor = MaterialTheme.colorScheme.onPrimary
        )
    ) {
        Row(
            modifier = Modifier.padding(horizontal = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(spacing)
        ) {
            icon()
            Text(
                text = text,
                fontSize = fontSize,
                fontWeight = FontWeight.Medium
            )
        }
    }
}

// 预览函数
@Preview(showBackground = true)
@Composable
private fun CarButtonPreview() {
    MaterialTheme {
        Column(
            modifier = Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            // 普通按钮
            CarButton(
                text = "普通模式",
                onClick = { }
            )
            
            // 驾驶模式按钮
            CarButton(
                text = "驾驶模式",
                onClick = { },
                isDriving = true
            )
            
            // 禁用状态
            CarButton(
                text = "禁用状态",
                onClick = { },
                enabled = false
            )
        }
    }
} 