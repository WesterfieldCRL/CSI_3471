#include <iostream>
#include <fstream>
#include <string>
#include <random>

using namespace std;

int main() 
{
    ofstream out;
    int lines;
    
    srand(time(0));

    out.open("input.txt");

    lines = 1000000;

    for (int i = 0; i < lines; i++)
    {
        out << rand()%15 - rand()%3 << "\n";
    }

    out.close();

    return 0;
}