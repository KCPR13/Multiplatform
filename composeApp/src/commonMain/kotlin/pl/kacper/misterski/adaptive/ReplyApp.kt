/*
 * Copyright 2022 Google LLC
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pl.kacper.misterski.adaptive

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeDrawing
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material3.adaptive.ExperimentalMaterial3AdaptiveApi
import androidx.compose.material3.adaptive.currentWindowAdaptiveInfo
import androidx.compose.material3.adaptive.currentWindowSize
import androidx.compose.material3.adaptive.layout.AnimatedPane
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffold
import androidx.compose.material3.adaptive.layout.ListDetailPaneScaffoldRole
import androidx.compose.material3.adaptive.navigation.rememberListDetailPaneScaffoldNavigator
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffold
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteScaffoldDefaults
import androidx.compose.material3.adaptive.navigationsuite.NavigationSuiteType
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.toSize
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.reply.data.Email
import kotlinx.coroutines.launch
import pl.kacper.misterski.adaptive.back.BackHandler

//CODELAB: https://codelabs.developers.google.com/jetpack-compose-adaptability#0
private val WINDOW_WIDTH_LARGE = 1200.dp

@Composable
fun ReplyApp() {
    val viewModel = ReplyHomeViewModel()
    val replyHomeUIState by viewModel.uiState.collectAsStateWithLifecycle()

    ReplyNavigationWrapperUI {
        val adaptiveInfo = currentWindowAdaptiveInfo()
        val sizeClassText =
            "${adaptiveInfo.windowSizeClass.windowWidthSizeClass}\n" +
                    "${adaptiveInfo.windowSizeClass.windowHeightSizeClass}"

        Column(Modifier.padding(16.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)) {
            Text(
                text = sizeClassText,
                modifier = Modifier.padding(
                    WindowInsets.safeDrawing.asPaddingValues()
                )
            )

            ReplyAppContent(
                replyHomeUIState = replyHomeUIState,
                onEmailClick = viewModel::setSelectedEmail
            )
        }

    }
}

@Composable
private fun ReplyNavigationWrapperUI(
    content: @Composable () -> Unit = {}
) {
    var selectedDestination: ReplyDestination by remember {
        mutableStateOf(ReplyDestination.Inbox)
    }

    val windowSize = with(LocalDensity.current) {
        currentWindowSize().toSize().toDpSize()
    }
    val navLayoutType = if (windowSize.width >= WINDOW_WIDTH_LARGE) {
        NavigationSuiteType.NavigationDrawer
    } else {
        NavigationSuiteScaffoldDefaults.calculateFromAdaptiveInfo(currentWindowAdaptiveInfo())
    }

    NavigationSuiteScaffold(
        navigationSuiteItems = {
            ReplyDestination.entries.forEach {
                item(
                    label =  { Text(it.label)} ,
                    icon = { Icon(  it.icon, it.label) },
                    selected = it == selectedDestination,
                    onClick = { selectedDestination = it },
                )
            }
        },
        layoutType = navLayoutType
    ) {
        content()
    }
}


@OptIn(ExperimentalMaterial3AdaptiveApi::class, ExperimentalComposeUiApi::class)
@Composable
fun ReplyAppContent(
    replyHomeUIState: ReplyHomeUIState,
    onEmailClick: (Email) -> Unit,
) {
    val selectedEmail = replyHomeUIState.selectedEmail
    val navigator = rememberListDetailPaneScaffoldNavigator<Long>()
    val coroutineScope = rememberCoroutineScope()

    BackHandler(navigator.canNavigateBack()) {
        coroutineScope.launch { navigator.navigateBack() }
    }

    ListDetailPaneScaffold(
        directive = navigator.scaffoldDirective,
        value = navigator.scaffoldValue,
        listPane = {
           AnimatedPane {
               ReplyListPane(
                   replyHomeUIState = replyHomeUIState,
                   onEmailClick = { email ->
                       onEmailClick(email)
                       coroutineScope.launch {
                           navigator.navigateTo(ListDetailPaneScaffoldRole.Detail, email.id)
                       }
                   }
               )
           }
        },
        detailPane = {
            AnimatedPane {
                if (selectedEmail != null) {
                    ReplyDetailPane(selectedEmail)
                }
            }
        }
    )
}
