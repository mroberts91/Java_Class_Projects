using System;
using System.Collections.Generic;
using System.Text;

namespace AccountsApi.Models
{
    public class BankAccount
    {
        public int Id { get; set; }

        public double Balance { get; set; }
        public int Deposits { get; set; }
        public int Withdrawals { get; set; }
        public double APR { get; set; }
        public AccountStatus Status { get; set; }
    }
}
