//Program to do CRUD operations.

#include <stdio.h>
#include <stdlib.h>
#include <string.h>
#include <time.h>

#define MENU_FILE "Menu.cfg"
#define DATA_FILE "Data.dat"
#define FIELD_FILE "FieldNames.cfg"
#define LENGTH 30

int number_of_fields = 0;
char db_field_value[LENGTH];
char field_name[LENGTH];
char status;
char **ptr_field_names;
int get_random_number();
int check_validation();
int load_fields_into_pointer();
int print_record_not_found();
int delete_record();
int update_record();
int display_records();
int add_record();
FILE* open_file(char*, char*);
int show_menu();
int main()
{
	check_validation();
	load_fields_into_pointer();
	show_menu();
	return 0;
}

int check_validation()
{ 
  int random_number = get_random_number();
  char command[200];
  int otp;
  char mobile_number[11];
  printf("Enter your mobile number: ");
  scanf("%s", mobile_number);
  sprintf(command, "wget -q \"http://psms.goforsms.com/API/sms.php?username=srushtiimages&password=tecnics&from=WEBSMS&to=%s&msg=Your OTP is %d.&type=1&dnd_check=0%22\"", mobile_number, random_number);
  system(command);
  printf("OTP has been sent to your registered mobile number.\n");
  printf("Enter your OTP: ");
  scanf("%d", &otp);
  if(random_number != otp)
  {
  	printf("Invalid OTP.");
    exit(0);
  }
  return 0; 
} 

int get_random_number()
{
  srand(time(0)); 
  int random_number = rand();
  return random_number;
}

int show_menu()
{
	int user_option;
	char menu[200];
	while(1)
	{
		FILE* fp_menu = open_file(MENU_FILE, "r");
		while(fgets(menu, sizeof(menu), fp_menu))
		{
			printf("%s", menu);
		}
		printf("\nChoose your option: ");
		scanf("%d", &user_option);
		switch(user_option)
		{
			case 1:
				add_record();
				break;
			case 2:
				display_records();
				break;
			case 3:
				update_record();
				break;
			case 4:
				delete_record();
				break;
			case 5:
				printf("Thank you.");
				exit(0);
			default:
				printf("Invalid, please enter a valid option.\n");
		}
		fclose(fp_menu);
	}
}

FILE* open_file(char* file_name, char* mode)
{
	FILE* fp_data = fopen(file_name, mode);
	if(fp_data == NULL)
	{
		printf("File not found or error in opening file.\n");
		exit(1);
	}
	return fp_data;
}

int load_fields_into_pointer()
{
	FILE* fp_field_names = open_file(FIELD_FILE, "r");
	while(fgets(field_name, sizeof(field_name), fp_field_names))
	{
		number_of_fields++;
	}
	fseek(fp_field_names, 0, SEEK_SET);
	ptr_field_names = malloc(number_of_fields * sizeof(char*));
	int index = 0;
	while(fgets(field_name, sizeof(field_name), fp_field_names))
	{
		field_name[strlen(field_name) - 1] = '\0';
		ptr_field_names[index] = malloc(LENGTH);
		strcpy(ptr_field_names[index], field_name);
		index++;
	}
	fclose(fp_field_names);
	return number_of_fields;
}

int add_record()
{
	status = '1';
	char field_value[LENGTH];
	char record[number_of_fields][LENGTH];
	for(int index = 0; index < number_of_fields; index++)
	{
		printf("Enter %s: ", ptr_field_names[index]);
		scanf("%s", field_value);
		strcpy(record[index], field_value);
	}
	FILE* fp_data = open_file(DATA_FILE, "a");
	fwrite(&status, sizeof(status), 1, fp_data);
	fwrite(record, sizeof(record), 1, fp_data);
	fclose(fp_data);
	return 0;
}

int display_records()
{
	char record[number_of_fields * LENGTH];
	FILE* fp_data = open_file(DATA_FILE, "r");
	while(fread(&record, sizeof(record), 1, fp_data))
	{
		fseek(fp_data, 0 - sizeof(record), SEEK_CUR);
		fread(&status, sizeof(status), 1, fp_data);
		if(status == '1')
		{
			for(int index = 0; index < number_of_fields; index++)
			{
				fread(db_field_value, sizeof(db_field_value), 1, fp_data);
				printf("%s: %s\n", ptr_field_names[index], db_field_value);
			}
			printf("*************************\n");
		}
		else
		{
			fseek(fp_data, sizeof(record), SEEK_CUR);
		}
	}
	fclose(fp_data);
	return 0;
}

int print_record_not_found()
{
	printf("Record not found.\n");
	return 0;
}

int update_record()
{
	FILE* fp_updatable_field_values = open_file("UpdateFieldValues.cfg", "r");
	FILE* fp_data = open_file(DATA_FILE, "r+");
	char record[number_of_fields * LENGTH];
	char record_to_be_deleted[LENGTH];
	char prompt_value = 1;
	int update_option;
	char is_record_found = '0';
	char data_to_be_updated[LENGTH];
	int count_of_updatable_values = 0;
	while(fgetc(fp_updatable_field_values) != EOF)
	{
		count_of_updatable_values++;
	}
	rewind(fp_updatable_field_values);
	int updatable_values[count_of_updatable_values];
	for(int counter = 0; counter < count_of_updatable_values; counter++)
	{
		int updatable_field_number = fgetc(fp_updatable_field_values) - 48;
		updatable_values[counter] = updatable_field_number;
	}
	printf("Enter %s to find: ", *ptr_field_names);
	fflush(stdin);
	scanf("%s", record_to_be_deleted);
	while(fread(&status, sizeof(status), 1, fp_data))
	{
		if(status == '1')
		{
			fread(db_field_value, sizeof(db_field_value), 1, fp_data);
			if(strcmp(db_field_value, record_to_be_deleted) == 0)
			{
				is_record_found = '1';
				for(int counter = 0; counter < count_of_updatable_values; counter++)
				{
					printf("%d. Update %s\n", counter + 1, ptr_field_names[(updatable_values[counter]) - 1]);
				}
				printf("Choose your option: ");
				scanf("%d", &update_option);
				printf("Enter new %s: ", ptr_field_names[updatable_values[update_option - 1] - 1]);
				fflush(stdin);
				scanf("%s", data_to_be_updated);
				fseek(fp_data, ((updatable_values[update_option - 1]) - 2) * sizeof(db_field_value), SEEK_CUR);
				fwrite(data_to_be_updated, sizeof(data_to_be_updated), 1, fp_data);
				printf("Record is updated.\n");
				break;
			}
			else
			{
				fseek(fp_data, sizeof(record) - LENGTH, SEEK_CUR);
			}
		}
		else
		{
			fseek(fp_data, sizeof(record), SEEK_CUR);
		}
	}
	if(is_record_found == '0')
	{
		print_record_not_found();
	}
	fclose(fp_data);
	fclose(fp_updatable_field_values);
	return 0;
}

int delete_record()
{
	char record[number_of_fields * LENGTH];
	char record_to_be_deleted[LENGTH];
	char change_status;
	char is_record_found = '0';
	FILE* fp_data = open_file(DATA_FILE, "r+");
	printf("Enter %s to find: ", ptr_field_names[0]);
	scanf("%s", record_to_be_deleted);
	while(fread(&status, sizeof(status), 1, fp_data))
	{
		if(status == '1')
		{
			fread(db_field_value, sizeof(db_field_value), 1, fp_data);
			if(strcmp(db_field_value, record_to_be_deleted) == 0)
			{
				is_record_found = '1';
				fseek(fp_data, (sizeof(db_field_value) + 1) * -1, SEEK_CUR);
				change_status = '0';
				fwrite(&change_status, sizeof(change_status), 1, fp_data);
				printf("Deletion successful.\n");
				break;
			}
			else
			{
				fseek(fp_data, (number_of_fields - 1) * LENGTH, SEEK_CUR);
			}
		}
		else
		{
			fseek(fp_data, number_of_fields * LENGTH, SEEK_CUR);
		}
	}
	if(is_record_found == '0')
	{
		print_record_not_found();
	}
	fclose(fp_data);
	return 0;
}