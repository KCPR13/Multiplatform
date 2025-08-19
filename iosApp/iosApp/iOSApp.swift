import SwiftUI

import ComposeApp

@main
struct iOSApp: App {
    init() { // << --- Initialize Koin here
        print("iOSApp: Initializing Koin...") // For debugging
        KoinKt.doInitKoin()
        print("iOSApp: Koin initialization call completed.") // For debugging
    }

    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}