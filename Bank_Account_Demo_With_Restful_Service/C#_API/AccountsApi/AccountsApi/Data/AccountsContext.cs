using System;
using Microsoft.EntityFrameworkCore;
using AccountsApi.Models;

namespace AccountsApi.Data
{
    public class AccountsContext : DbContext
    {
        public DbSet<BankAccount> SavingsAccounts { get; set; }

        public AccountsContext(DbContextOptions<AccountsContext> options) : base(options)
        {

        }

        //protected override void OnConfiguring(DbContextOptionsBuilder optionsBuilder)
        //{
        //    //optionsBuilder.UseInMemoryDatabase("accounts");
        //    optionsBuilder.UseSqlServer(@"Server=tcp:robertsondev.database.windows.net,1433;Initial Catalog=Accounts;Persist Security Info=False;User ID=mroberts91;Password=&*88mediline;MultipleActiveResultSets=False;Encrypt=True;TrustServerCertificate=False;Connection Timeout=30;");
        //}

        protected override void OnModelCreating(ModelBuilder modelBuilder)
        {
            modelBuilder.Entity<BankAccount>()
                .HasKey(b => b.Id);
            modelBuilder.Entity<BankAccount>()
                .Property(p => p.Id)
                .ValueGeneratedNever();
              
        }
    }
}
