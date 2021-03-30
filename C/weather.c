//Program to get weather report for given city

#include <string.h>
#include <stdio.h>
#include <stdlib.h>

int print_temperature(char* );
int main (int argc, char* args[])
{
	print_temperature(args[1]);
  return 0;
}

int print_temperature(char* arg)
{
  char city[30];
  char copy_city[30];
  char command[500];
  char data[1000];
  if(arg == NULL)
  {
    printf("Enter city name: ");
    scanf("%s", city);
    strcpy(copy_city, city);
  }
  else
  {
    strcpy(copy_city, arg);
  }
  sprintf(command, "curl \"http://api.openweathermap.org/data/2.5/find?q=%s&appid=b286785c5f4f54f7ef296483269b6358&units=metric\" >WeatherReport.dat -s", copy_city);
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
      printf("The temparature in %s is %s%cC.", copy_city, separated_data, 248);
    }
    separated_data = strtok(NULL, separators);
  }
  return(0);
}
