// Extended from MyMath

class NewMath extends MyMath
{
	public void add(int number1, int number2, int number3)
	{
		System.out.println(number1 + number2 + number3);
	}
	public void add(int number1, int number2)
	{
		System.out.println("By adding two numbers: " + number1 + number2);
	}
	public void square(int number)
	{
		System.out.println(number * number);
	}
}
