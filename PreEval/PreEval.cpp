#include <iostream>
#include <fstream>

using namespace std;

void expandVector(double* &inputVector, int &capacity, int size)
{
    capacity *= 2;
    double* tempVector = inputVector;
    inputVector = new double[capacity];
    for (int i = 0; i < size; i++)
    {
        inputVector[i] = tempVector[i];
    }
    delete [] tempVector;
}

int main(int argc, char* argv[])
{

    ifstream input;
    string data;
    
    int size = 0;
    int capacity = 5;
    double* dataVector = new double[capacity];
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
            else if ((!isdigit(data[i]) && data[i] != '.') || (i == 0 && data[i] == '.' && data.length() == 1))
            {
                fprintf(stderr, "Error: File contains non-numeric data\n");
                return 1;
            }
        }
        dataVector[size] = stod(data);
        size++;
        if (size >= capacity)
        {
            expandVector(dataVector, capacity, size);
        }
    }

    for (int i = 0; i < size; i++)
    {
        sum += dataVector[i];
    }

    cout << "The sum of the numbers in the file is: " << sum << endl;

    delete [] dataVector;

    return 0;
}