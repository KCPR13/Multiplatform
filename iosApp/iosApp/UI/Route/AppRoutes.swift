//
//  AppRoutes.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 19/08/2025.
//

import ComposeApp

enum AppRoutes {
    case showLandmarkDetails
    case addLandmark
    
    init(from shared: Routes) {
        switch shared {
        case is Routes.LandmarkDetails:
            self = .showLandmarkDetails
        case is Routes.AddLandmark:
            self = .addLandmark
        default:
            fatalError("Not handled route: \(shared)")
        }
    }
}
