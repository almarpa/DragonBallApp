//
//  LabelInformation.swift
//  iosApp
//
//  Created by Arístides Guimerá Orozco on 23/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct LabelInformation: View {
    let text:String
    
    var body: some View {
        Text(text).padding(.horizontal, 20).padding(.vertical, 8)
            .overlay(RoundedRectangle(cornerRadius: 20).stroke(Color(.backgroundPrimary), lineWidth: 3))
    }
}

#Preview {
    LabelInformation(text:"PEPITO")
}
