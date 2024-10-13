//
//  TransformationView.swift
//  iosApp
//
//  Created by Arístides Guimerá Orozco on 23/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import Kingfisher

struct TransformationView: View {
    let transformation:TransformationModel
    
    var body: some View {
        VStack{
            KFImage(URL(string: transformation.image))
                .placeholder{
                    ProgressView()
                }.resizable()
                .scaledToFit()
//                .frame(maxWidth: 100, maxHeight: 150)
            Text(transformation.name)
        }.padding(.horizontal, 24)
            .padding(.vertical, 12)
            .cornerRadius(20)
            .shadow(radius: 16)
            .overlay(RoundedRectangle(cornerRadius: 20).stroke(.gray))
        
    }
}

#Preview {
    TransformationView(transformation: TransformationModel(name:"pepe",image: "https://dragonball-api.com/characters/goku_normal.webp", ki:"2323"))
}
