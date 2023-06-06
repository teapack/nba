package cz.vratislavjindra.nba.common.design.system.compose.error

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import cz.vratislavjindra.nba.R
import cz.vratislavjindra.nba.common.architecture.infrastructure.AppError
import cz.vratislavjindra.nba.common.design.system.compose.spacing.VerticalSpacing
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun ErrorScreen(
    error: AppError,
    onTryAgain: () -> Unit,
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(all = Theme.dimensions.paddingDefault),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(id = error.errorMessageResId),
            color = Theme.colors.error,
        )
        VerticalSpacing()
        OutlinedButton(onClick = onTryAgain) {
            Text(text = stringResource(id = R.string.button_try_again))
        }
    }
}
