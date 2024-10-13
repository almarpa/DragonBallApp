//
//  IconInformation.swift
//  iosApp
//
//  Created by Arístides Guimerá Orozco on 23/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI

struct IconInformation: View {
    let text:String
    let Icon:Image
    
    var body: some View {
        VStack{
            Icon
                .resizable()
                .frame(width: 50, height: 50)
            Text(text)
        }
        
    }
}

#Preview {
    IconInformation(text: "Pepito", Icon: Image("strong"))
}
