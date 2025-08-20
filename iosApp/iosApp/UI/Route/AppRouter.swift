//
//  AppRouter.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 19/08/2025.
//

import SwiftUI
import ComposeApp

final class AppRouter: Router, ObservableObject {
    static let shared: AppRouter = AppRouter()
    
    @MainActor @Published var path: NavigationPath = NavigationPath()
    
    override init() { }
    
    override func handleRoute(from: Routes) {
        let route = AppRoutes(from: from)
        print("Route \(from) detected")
        
        DispatchQueue.main.async {
            self.path.append(route)
        }
    }
}
