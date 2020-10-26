//Program to get weather report for given city

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int print_temperature();
int main ()
{
  	print_temperature();
  	return 0;
}

int print_temperature()
{
	char city[30];
    char command[500];
    char data[1000];
    printf("Enter city name: ");
    scanf("%s", city);
    sprintf(command, "curl \"http://api.openweathermap.org/data/2.5/find?q=%s&appid=b286785c5f4f54f7ef296483269b6358&units=metric\" >WeatherReport.dat -s", city);
    system(command);
    FILE* fp_data = fopen("WeatherReport.dat", "r");
    fread(data, sizeof(data), 1, fp_data);
    char separators[5] = "{:[,";
    char* separated_data = strtok(data, separators);
    while(separated_data != NULL)
    {
   		if(strcmp(separated_data, "\"temp\"") == 0)
   		{
	   		separated_data = strtok(NULL, separators);
   			printf("The temparature in %s is %s.", city, separated_data);
   		}
   		separated_data = strtok(NULL, separators);
    }
    return(0);
}