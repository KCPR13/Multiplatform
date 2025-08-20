//
//  LandmarksView+MapView.swift
//  iosApp
//
//  Created by Bartłomiej Bukowiecki on 19/08/2025.
//

import ComposeApp
import SwiftUI
import MapKit

extension LandmarksView {
    struct MapView: View, Equatable {
        typealias OnLandmarkSelected = (_ model: DomainLandmarkDataModel) -> Void
        
        @Environment(\.colorScheme) private var colorScheme
        
        let models: [DomainLandmarkDataModel]
        let onLandmarkSelected: OnLandmarkSelected
        
        var body: some View {
            Map {
                ForEach(models) { landmark in
                    Annotation(coordinate: CLLocationCoordinate2D(latitude: landmark.latitude, longitude: landmark.longitude)) {
                        Text("I am a content")
                            .padding(4)
                            .contentShape(RoundedRectangle(cornerRadius: 8))
                            .background {
                                RoundedRectangle(cornerRadius: 8)
                                    .fill(Color.red)
                            }
                            .onTapGesture {
                                onLandmarkSelected(landmark)
                            }
                    } label: {
                        Text(landmark.name)
                    }
                    .mapItemDetailSelectionAccessory(.sheet)
                }
            }
        }
        
        static func == (lhs: LandmarksView.MapView, rhs: LandmarksView.MapView) -> Bool {
            lhs.models == rhs.models
        }
    }
}

#if DEBUG
#Preview {
    LandmarksView.MapView(models: [
        
    ]) { model in
        print("[Preview] Landmark \(model.name) selected")
    }
}
#endif
