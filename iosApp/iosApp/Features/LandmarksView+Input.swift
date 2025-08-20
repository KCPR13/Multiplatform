//
//  LandmarksView+Input.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 19/08/2025.
//

import ComposeApp

extension LandmarksView {
    enum Input {
        case selectLandmark(_ model: DomainLandmarkDataModel)
        case addLandmark
        
        func toShared() -> LandmarksViewModel.Input {
            switch self {
            case .selectLandmark(let model):
                LandmarksViewModel.InputSelectedLandmark(model: model)
            case .addLandmark:
                LandmarksViewModel.InputAddLandmark()
            }
        }
    }
}

extension LandmarksViewModel {
    func onInput(_ input: LandmarksView.Input) {
        self.onInput(input: input.toShared())
    }
}
