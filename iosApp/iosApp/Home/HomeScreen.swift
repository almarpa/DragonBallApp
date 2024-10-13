//
//  HomeScreen.swift
//  iosApp
//
//  Created by Arístides Guimerá Orozco on 21/9/24.
//  Copyright © 2024 orgName. All rights reserved.
//

import SwiftUI
import Shared
import KMPObservableViewModelSwiftUI
import KMPNativeCoroutinesAsync

struct HomeScreen: View {
    
    @StateViewModel
    var homeViewModel = HomeViewModel(repository: DiHelper().repository)
    
    var body: some View {
        if !homeViewModel.characters.isEmpty{
            NavigationStack{
                VStack{
                    Text("Vegeta Edition").font(/*@START_MENU_TOKEN@*/.title/*@END_MENU_TOKEN@*/).bold().foregroundColor(Color(.backgroundTertiary))
                ScrollView{
                    LazyVStack{
                        ForEach(homeViewModel.characters, id:\.self){ character in
                            NavigationLink(destination: {DetailScreen(id:character.id )}){
                                CharacterItem(item: character)
                            }
                        }
                    }
                }
                }.background(Color(.backgroundPrimary))
            }
            
        } else {
            ProgressView()
        }
    }
}

#Preview {
    HomeScreen()
}
