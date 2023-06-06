package cz.vratislavjindra.nba.common.design.system.compose.utils

import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.lazy.LazyListState

// Check if scroll fraction is slightly over zero to overcome float precision issues.
private const val MIN_SCROLL_OFFSET = 0.01f

val LazyListState.isScrolled: Boolean
    get() = firstVisibleItemIndex > 0 || firstVisibleItemScrollOffset > MIN_SCROLL_OFFSET

val ScrollState.isScrolled: Boolean
    get() = value > MIN_SCROLL_OFFSET

val LazyListState.isScrolledToEnd: Boolean
    get() = layoutInfo.visibleItemsInfo.lastOrNull()?.index == layoutInfo.totalItemsCount - 1
