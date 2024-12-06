#include <iostream>
#include <fstream>
#include <vector>
#include <string>

using namespace std;

bool hasBread(std::string text){
    std::string bread = "bread";
    if (text.find(bread) != std::string::npos){
        return true;
    } else {
        return false;
    }
}

std::string reader(ifstream& text){
    std::string reader;
    std::string output;
    while (getline(text, reader)){
        // std::cout << reader << endl;
        output.append(reader + "\n");
    }
    return output;
}

void ready(){
    ifstream dalton("texts/dalton_trumbo.txt");
    std::string daltonBread = reader(dalton);
    // bool daltonHasBread = hasBread(daltonBread);
    // std::cout << daltonHasBread << endl;
    if (hasBread(daltonBread)){
        std::cout << "HAS BREAD" << endl;
    } else {
        std::cout << "DOESN'T HAVE BREAD" << endl;
    }
    
    // ifstream fyodor("texts/fyodor_dostoevsky.txt");
    // ifstream kenneth("texts/kenneth_grahame.txt");
    // ifstream margaret("texts/margaret_atwood.txt");
    // ifstream terry("texts/terry_pratchett.txt");
    // ifstream ursula("texts/ursula_leguin.txt");
    // hasBread(dalton);

}

int main(void)
{
    std::cout << "Which mode would you like to run?" << endl;
    std::cout << "Readymade Bread : 1" << endl;
    std::cout << "" << endl;
    std::cout << "" << endl;
    std::cout << "" << endl;
    
    std::string mode = ""; 
    
    std::cin >> mode;
    if (mode == "1"){
        ready();
    }
    
    return EXIT_SUCCESS;
}