//
//  AppViewState.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 19/08/2025.
//

import ComposeApp

enum AppViewState<ResultProvider> where ResultProvider: ViewResultProvider {
    case loading
    case loaded
    case working
    case result(ResultProvider)
    
    init(from shared: ViewState) {
        switch shared {
        case is ViewState.Loaded:
            self = .loaded
        case is ViewState.Loading:
            self = .loading
        case is ViewState.Working:
            self = .working
        case let result as ViewState.Result:
            self = .result(result.result as! ResultProvider)
        default:
            fatalError("Not suported ViewState's member!")
        }
    }
}
