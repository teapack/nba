package cz.vratislavjindra.nba.common.design.system.compose.layout

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.WindowInsetsSides
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.only
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyListScope
import androidx.compose.foundation.lazy.LazyListState
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp
import cz.vratislavjindra.nba.common.design.system.compose.theme.Theme

@Composable
fun NbaList(
    state: LazyListState,
    paddingValues: PaddingValues,
    content: LazyListScope.() -> Unit,
) {
    val navigationBarsPadding = WindowInsets.navigationBars.only(
        sides = WindowInsetsSides.Horizontal + WindowInsetsSides.Bottom
    ).asPaddingValues().calculateBottomPadding()
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        state = state,
        contentPadding = PaddingValues(
            start = paddingValues.calculateStartPadding(
                layoutDirection = LayoutDirection.Ltr
            ) + Theme.dimensions.paddingDefault,
            top = paddingValues.calculateTopPadding() + Theme.dimensions.paddingDefault,
            end = paddingValues.calculateEndPadding(
                layoutDirection = LayoutDirection.Rtl
            ) + Theme.dimensions.paddingDefault,
            bottom = navigationBarsPadding + Theme.dimensions.paddingDefault
        ),
        verticalArrangement = Arrangement.spacedBy(space = 16.dp),
    ) {
        content()
    }
}