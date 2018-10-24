using System;
using AccountsApi.Data;

namespace ConsoleApp1
{
    class Program
    {
        static void Main(string[] args)
        {
            using (var db = new AccountsContext())
            {
                db.Database.EnsureCreated();
                Console.WriteLine("Hello World!");
                Console.ReadLine();
            }
            
        }
    }
}
