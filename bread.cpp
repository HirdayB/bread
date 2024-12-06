#include <iostream>
#include <fstream>
#include <vector>
#include <string>
#include <algorithm>

using namespace std;

// void hasBread(std::string text){
//     std::string bread = "bread";
//     std::transform(text.begin(), text.end(), text.begin(), ::tolower);
//     if (text.find(bread) != std::string::npos){
//         std::cout << "HAS BREAD" << endl;
//     } else {
//         std::cout << "DOESN'T HAVE BREAD" << endl;
//     }
// }

std::string::size_type findEnd(std::string text, int pos){
    std::string::size_type n = text.find('.', pos);
    if (text.find('?', pos) < n){
        n = text.find('?', pos);
    }
    if (text.find('!', pos) < n){
        n = text.find('!', pos);
    }
    return n;
}

void hasBread(std::string text){
    std::string transformed = text;
    std::transform(transformed.begin(), transformed.end(), transformed.begin(), ::tolower);
    std::string bread = "bread";
    std::string::size_type n = findEnd(text, 0);
    int begin = 0;
    while(n != std::string::npos){
        std::string sub = transformed.substr(begin, n+1 - begin);
        // std::cout << sub << endl;
        if (sub.find(bread) != std::string::npos){
            std::string sentence = text.substr(begin, n+1 - begin);
            std::cout << sentence << endl;
        //     std::cout << "HAS BREAD" << endl;
        // } else {
        //     std::cout << "DOESN'T HAVE BREAD" << endl;
        }
        begin = n+2;
        n = findEnd(text, n+1);
        if (n == std::string::npos){
            sub = transformed.substr(begin, transformed.size() - begin);
            // std::cout << sub << endl;
            if (sub.find(bread) != std::string::npos){
                std::string sentence = text.substr(begin, transformed.size() - begin);
                std::cout << sentence << endl;
            //     std::cout << "HAS BREAD" << endl;
            // } else {
            //     std::cout << "DOESN'T HAVE BREAD" << endl;
            }
        }
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
    std::cout << "Checking file 'dalton_trumbo.txt' for bread" << endl;
    ifstream dalton("texts/dalton_trumbo.txt");
    std::string daltonBread = reader(dalton);
    hasBread(daltonBread);

    std::cout << "Checking file 'fyodor_dostoevsky.txt' for bread" << endl;
    ifstream fyodor("texts/fyodor_dostoevsky.txt");
    std::string fyodorBread = reader(fyodor);
    hasBread(fyodorBread);

    std::cout << "Checking file 'kenneth_grahame.txt' for bread" << endl;
    ifstream kenneth("texts/kenneth_grahame.txt");
    std::string kennethBread = reader(kenneth);
    hasBread(kennethBread);

    std::cout << "Checking file 'margaret_atwood.txt' for bread" << endl;
    ifstream margaret("texts/margaret_atwood.txt");
    std::string margaretBread = reader(margaret);
    hasBread(margaretBread);

    std::cout << "Checking file 'terry_pratchett.txt' for bread" << endl;
    ifstream terry("texts/terry_pratchett.txt");
    std::string terryBread = reader(terry);
    hasBread(terryBread);

    std::cout << "Checking file 'ursula_leguin.txt' for bread" << endl;
    ifstream ursula("texts/ursula_leguin.txt");
    std::string ursulaBread = reader(ursula);
    hasBread(ursulaBread);

    // TEST CASES BELOW

    std::cout << "Press T to run test cases" << endl;

    std::string test; 
    
    std::cin >> test;
    if (test == "t" || test == "T"){

        std::cout << "Checking file 'no_bread.txt' for bread" << endl;
        ifstream no_bread("texts/tests/no_bread.txt");
        std::string no_breadBread = reader(no_bread);
        hasBread(no_breadBread);

        std::cout << "Checking file 'capitalised.txt' for bread" << endl;
        ifstream capital("texts/tests/capitalised.txt");
        std::string capitalBread = reader(capital);
        hasBread(capitalBread);

        std::cout << "Checking file 'mixed.txt' for bread" << endl;
        ifstream mixed("texts/tests/mixed.txt");
        std::string mixedBread = reader(mixed);
        hasBread(mixedBread);

        std::cout << "Checking file 'uppercase.txt' for bread" << endl;
        ifstream uppercase("texts/tests/uppercase.txt");
        std::string uppercaseBread = reader(uppercase);
        hasBread(uppercaseBread);

        std::cout << "Checking file 'multiple.txt' for bread" << endl;
        ifstream multiple("texts/tests/multiple.txt");
        std::string multipleBread = reader(multiple);
        hasBread(multipleBread);        
    }
}

void input(void){
    std::cout << "Insert the text you would like to check:" << endl;
    std::cin.ignore();
    std::string inputBread;
    std::getline(std::cin, inputBread);
    // std::cout << inputBread << endl;
    hasBread(inputBread);
}

void menu(void){
    std::cout << "Which mode would you like to run?" << endl;
    std::cout << "Readymade Bread : 1" << endl;
    std::cout << "Test Your Bread : 2" << endl;
    std::cout << "Highlighted Bread: 3" << endl;
    std::cout << "" << endl;
    
    std::string mode; 
    
    std::cin >> mode;
    if (mode == "1"){
        ready();
    } else if (mode == "2"){
        input();
    } else if (mode == "3"){
        ready();
    } else {
        std::cout << "Please input a valid option." << endl;
        menu();
    }

}

int main(void)
{
    menu();
    return EXIT_SUCCESS;
}