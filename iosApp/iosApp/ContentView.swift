import SwiftUI
import shared

struct ContentView: View {
    @ObservedObject var viewModel: ViewModel

    var body: some View {
        Text(viewModel.text)
    }
}

class ViewModel: ObservableObject {
    @Published var text = "Loading..."
    init() {
        Greeting().greet { greeting, error in
            DispatchQueue.main.async {
                if let greeting = greeting {
                    self.text = greeting
                } else {
                    self.text = error?.localizedDescription ?? "error"
                }
            }
        }
    }
}
