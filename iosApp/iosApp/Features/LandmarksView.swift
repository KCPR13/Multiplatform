//
//  LandmarksView.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 14/08/2025.
//

import SwiftUI
import Combine
import ComposeApp
import KMPNativeCoroutinesCombine
import KMPObservableViewModelSwiftUI

struct LandmarksView: View {
    @StateViewModel private var viewModel: LandmarksViewModel = Koin().landmarksViewModel()
    @MainActor @ObservedObject private var router: AppRouter = .shared
    
    var body: some View {
        NavigationStack(path: $router.path) {
            MapView(models: viewModel.landmarks) { model in
                viewModel.onInput(.selectLandmark(model))
            }
            .task {
                do {
                    try await viewModel.loadLandmarks()
                } catch { }
            }
            .toolbar {
                ToolbarItem(placement: .primaryAction) {
                    Button {
                        viewModel.onInput(.addLandmark)
                    } label: {
                        Image(systemName: "plus.app.fill")
                    }
                }
            }
            .navigationDestination(for: AppRoutes.self) { route in
                switch route {
                case .showLandmarkDetails:
                    EmptyView()
                case .addLandmark:
                    EmptyView()
                }
            }
        }
    }
}

#Preview {
    LandmarksView()
}
