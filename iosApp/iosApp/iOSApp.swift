import SwiftUI
import ComposeApp

@main
struct iOSApp: App {
    init() {
        UiModuleDiKt.registerRouter(AppRouter.shared)
        KoinSharedSetupKt.doInitKoin()
    }
    
    var body: some Scene {
        WindowGroup {
            LandmarksView()
        }
    }
}
