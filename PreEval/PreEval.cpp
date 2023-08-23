#include <iostream>
#include <fstream>
#include <vector>
#include <iomanip>

using namespace std;

int main(int argc, char* argv[])
{

    ifstream input;
    string data;
    vector<double> dataVector;
    double sum = 0;
    char* fileFound;

    input.open(argv[1]);

    if (input.fail())
    {
        fprintf(stderr, "Error: File not found\n");
        return 1;
    }


    while (getline(input, data))
    {
        for (int i = 0; i < data.length(); i++)
        {
            if (i == 0 && data[i] == '-' && i != data.length()-1)
            {
                i++;
            }
            else if (!isdigit(data[i]) && data[i] != '.')
            {
                fprintf(stderr, "Error: File contains non-numeric data\n");
                return 1;
            }
        }
        dataVector.insert(dataVector.end(), stod(data));
    }

    for (int i = 0; i < dataVector.size(); i++)
    {
        sum += dataVector[i];
    }

    cout << "The sum of the numbers in the file is: " << fixed << setprecision(7) << sum << endl;

    return 0;
}