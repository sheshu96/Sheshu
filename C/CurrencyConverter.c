//Program to check current currency rate of INDIA and USA

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int print_currency();
int main(int argc, char* args[])
{
  print_currency(args[1]);
  return 0;
}

int print_currency(char* amount)
{
  float rupees;
  char command[100];
  char data[2000];
  sprintf(command, "curl \"https://v6.exchangerate-api.com/v6/d3e93de9147fd23b786c59f3/latest/INR\" >CurrencyConverter.dat -s");
  system(command);
  if(amount == NULL)
  {
    printf("How many rupees do you want to convert? ");
    scanf("%f", &rupees);
  }
  else
  {
    rupees = atof(amount);
  }
  FILE* fp_data = fopen("CurrencyConverter.dat", "r");
  fread(data, sizeof(data), 1, fp_data);
  char separators[5] = "{:,\"";
  char *separated_data = strtok(data, separators); 
  while(separated_data != NULL)
  {
    if(strcmp(separated_data, "USD") == 0)
    {
      separated_data = strtok(NULL, separators);
      float converted_currency = atof(separated_data) * rupees;
      printf("%.2f INR is %.2f USD.", rupees, converted_currency);
    }
    separated_data = strtok(NULL, separators);
  }
  return 0;
}
