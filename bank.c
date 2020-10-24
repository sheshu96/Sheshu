//Read and print a bank customer record

#include<stdio.h>

void main()
{
	struct book
	{
		int accountNumber;
		char name[20];
		char password[20];
	}b;
	printf("Enter account nmber: ");
	scanf("%d", &b.accountNumber);
	printf("\nEnter name: ");
	scanf("%s", b.name);
	printf("\nEnter password: ");
	scanf("%s", b.password);
	printf("\nYour account number is: %d.", b.accountNumber);
	printf("\nYour name is: %s.", b.name);
	printf("\nYour password is: %s.", b.password);
}