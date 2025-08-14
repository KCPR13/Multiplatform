import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        KoinSharedSetupKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            ContentView()
        }
    }
}
