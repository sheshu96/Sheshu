//Program to get meaning for a given word form dictionary

#include <stdio.h>
#include <stdlib.h>
#include <string.h>

int print_meaning(char* );
int main(int argc, char* args[])
{
	print_meaning(args[1]);
	return 0;
}

int print_meaning(char* arg_word)
{
	char word[30];
	char copy_word[30];
	char data[1000];
	char command[100];
	if(arg_word == NULL)
	{
		printf("Enter a word: ");
		scanf("%s", word);
		strcpy(copy_word, word);
	}
	else
	{
		strcpy(copy_word, arg_word);
	}
	sprintf(command, "curl \"https://api.dictionaryapi.dev/api/v2/entries/en/%s\" >DictionaryFile.dat -s", copy_word);
	system(command);
	FILE* fp_data = fopen("DictionaryFile.dat", "r");
	fread(data, sizeof(data), 1, fp_data);
	char separators[4] = "{,\"";
	char *separated_data = strtok(data, separators);
	while(separated_data != NULL)
	{
		if(strcmp(separated_data, "audio") == 0)
		{
			for(int counter = 0; counter < 2; counter++)
			{
				separated_data = strtok(NULL, separators);
			}
			sprintf(command, "vlc -I null --play-and-exit \"%s\"", separated_data);
			system(command);
		}
		if(strcmp(separated_data, "definition") == 0)
		{
			for(int counter = 0; counter < 2; counter++)
			{
				separated_data = strtok(NULL, separators);
			}
			printf("The meaning of %s is: %s", copy_word, separated_data);
		}
		separated_data = strtok(NULL, separators);
	}
	return 0;
}
