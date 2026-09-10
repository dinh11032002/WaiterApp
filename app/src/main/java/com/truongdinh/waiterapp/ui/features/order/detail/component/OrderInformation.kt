package com.truongdinh.waiterapp.ui.features.order.detail.component

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.truongdinh.waiterapp.ui.features.order.detail.OrderDetailUiModel
import com.truongdinh.waiterapp.ui.theme.AppSpacing
import com.truongdinh.waiterapp.util.toDisplayDateTime

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun OrderInformation(
    uiModel: OrderDetailUiModel ,
    modifier: Modifier = Modifier
) {
    Column {
        Text(
            text = uiModel.tableName,
            style = MaterialTheme.typography.titleLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(AppSpacing.md))
        Text(
            text = "Nhân viên: ${uiModel.staffName}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(AppSpacing.md))
        Text(
            text = "Trạng thái: ${uiModel.status.value}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
        Spacer(modifier = Modifier.height(AppSpacing.md))
        Text(
            text = "Ngày: ${uiModel.createdAt.toDisplayDateTime()}",
            style = MaterialTheme.typography.bodyLarge,
            color = MaterialTheme.colorScheme.onBackground
        )
    }
}